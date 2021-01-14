package com.nasinha.digitalspace.quiz.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.quiz.db.QuizDatabase
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import com.nasinha.digitalspace.quiz.viewmodel.QuizViewModel
import java.util.*
import kotlin.collections.ArrayList

class QuestionsFragment : Fragment(), View.OnClickListener {
    private var _currentPosition: Int = 1
    private var _selectedOptionPosition: Int = 0
    private var _correctAnswers: Int = 0
    private var _isAnswered: Boolean = false //user selected an option
    private var _goToNextQuestion: Boolean = false //user already clicked on "answer"

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerBarAnimation: Animator
    private var timeLeftInMillis: Long = 0
    private var finished = false

    private lateinit var _view: View
    private lateinit var _viewModel: QuizViewModel

    lateinit var txtQuestion: TextView
    lateinit var txtQuestionNumber: TextView
    lateinit var txtOptionOne: TextView
    lateinit var txtOptionTwo: TextView
    lateinit var txtOptionThree: TextView
    lateinit var txtOptionFour: TextView
    lateinit var btnAnswer: MaterialButton
    lateinit var txtChronometer: TextView
    lateinit var imageClock: ImageView
    lateinit var countdownBar: ProgressBar
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            stopTimer()
            navController.popBackStack()
        }
        callback.isEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.fragment_questions, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
            this,
            QuizViewModel.QuizViewModelFactory(QuizRepository(QuizDatabase.getDatabase(view.context).quizDao()))
        ).get(QuizViewModel::class.java)

        txtQuestion = view.findViewById(R.id.txtQuestion)
        txtQuestionNumber = view.findViewById(R.id.txtQuestionNumber)
        txtOptionOne = view.findViewById(R.id.txtAnswer1)
        txtOptionTwo = view.findViewById(R.id.txtAnswer2)
        txtOptionThree = view.findViewById(R.id.txtAnswer3)
        txtOptionFour = view.findViewById(R.id.txtAnswer4)
        btnAnswer = view.findViewById(R.id.btnNext)
        txtChronometer = view.findViewById(R.id.txtChronometer)
        imageClock = view.findViewById(R.id.imgClock)
        countdownBar = view.findViewById(R.id.pbCountDown)
        navController = findNavController()

        setQuestion()

        view.findViewById<ImageButton>(R.id.btnBackQuizQuestions).setOnClickListener {
            activity?.onBackPressed()
        }

        txtOptionOne.setOnClickListener(this)
        txtOptionTwo.setOnClickListener(this)
        txtOptionThree.setOnClickListener(this)
        txtOptionFour.setOnClickListener(this)
        btnAnswer.setOnClickListener(this)
    }

    private fun setQuestion() {
        btnAnswer.isEnabled = true
        _viewModel.questionsList.observe(viewLifecycleOwner) {
            val question = it[_currentPosition - 1]
            defaultOptionsView()
            startCountDown()

            btnAnswer.text = getString(R.string.Responder)
            txtQuestionNumber.text = getString(R.string.contagem_questoes, _currentPosition, it.size)
            txtQuestion.text = question.question
            txtOptionOne.text = question.optionOne
            txtOptionTwo.text = question.optionTwo
            txtOptionThree.text = question.optionThree
            txtOptionFour.text = question.optionFour
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                txtOptionOne.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            2 -> {
                txtOptionTwo.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            3 -> {
                txtOptionThree.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            4 -> {
                txtOptionFour.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, txtOptionOne)
        options.add(1, txtOptionTwo)
        options.add(2, txtOptionThree)
        options.add(3, txtOptionFour)

        for (option in options) {
            option.background =
                ContextCompat.getDrawable(_view.context, R.drawable.txt_question_stroke)
            option.isEnabled = true
        }
    }

    override fun onClick(v: View?) {
        _viewModel.questionsList.observe(viewLifecycleOwner) {
            val question = it[_currentPosition - 1]

            when (v?.id) {
                R.id.txtAnswer1 -> {
                    selectedOption(txtOptionOne, 1)
                }
                R.id.txtAnswer2 -> {
                    selectedOption(txtOptionTwo, 2)
                }
                R.id.txtAnswer3 -> {
                    selectedOption(txtOptionThree, 3)
                }
                R.id.txtAnswer4 -> {
                    selectedOption(txtOptionFour, 4)
                }
                R.id.btnNext -> {
                    if (_goToNextQuestion) { //second click
                        nextQuestion()
                        optionsSwitch()
                    } else if (_isAnswered) { //first click
                        stopTimer()
                        _goToNextQuestion = true
                        if (question.correctAnswer != _selectedOptionPosition) {
                            answerView(_selectedOptionPosition, R.drawable.incorrect_question)
                        } else {
                            _correctAnswers++
                        }
                        question.correctAnswer?.let { answerView(it, R.drawable.correct_question) }

                        if (_currentPosition == NUMBER_QUESTIONS) {
                            btnAnswer.text = getString(R.string.fim)
                        } else {
                            btnAnswer.text = getString(R.string.proxima_questao)
                        }
                        optionsSwitch()
                    }
                }
            }
        }
    }

    private fun nextQuestion() {
        countdownBar.setProgress(0)
        _currentPosition++
        _goToNextQuestion = false
        _isAnswered = false

        if (_currentPosition <= NUMBER_QUESTIONS) {
                setQuestion()
        } else {
            val astronautImage: Int
            val resultsMessage: String

            if (_correctAnswers >= 5) {
                astronautImage = R.drawable.astronauta_quiz
                resultsMessage = getString(R.string.parab_ns)
            } else {
                astronautImage = R.drawable.astronauta_perdido_branco
                resultsMessage = getString(R.string.perdido_no_espaco)
            }

            val bundle = bundleOf(
                "CORRECT_ANSWERS" to _correctAnswers,
                "TOTAL_QUESTIONS" to NUMBER_QUESTIONS,
                "ASTRONAUT_IMAGE" to astronautImage,
                "RESULTS_MESSAGE" to resultsMessage
            )
            navController.navigate(
                R.id.action_questionsFragment_to_scoreFragment, bundle
            )
        }
    }

    private fun optionsSwitch() {
        txtOptionOne.isEnabled = !(txtOptionOne.isEnabled)
        txtOptionTwo.isEnabled = !(txtOptionTwo.isEnabled)
        txtOptionThree.isEnabled = !(txtOptionThree.isEnabled)
        txtOptionFour.isEnabled = !(txtOptionFour.isEnabled)
    }

    private fun selectedOption(tv: TextView, selectedOptionNumber: Int) {
        _isAnswered = true
        defaultOptionsView()
        _selectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(_view.context, R.drawable.selected_question)
    }

    //timer-related methods

    private fun startCountDown() {
        startCountDownBar()

        countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val minutes = (timeLeftInMillis / 1000) / 60
                val seconds = (timeLeftInMillis / 1000) % 60
                val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

                txtChronometer.text = timeFormatted

                changeCountDownColor()
            }

            override fun onFinish() {
                finished = true
                timeIsOverDialog()
                btnAnswer.isEnabled = false
                timeLeftInMillis = 0
            }
        }
        countDownTimer.start()
    }

    private fun timeIsOverDialog() {
        val dialog = AlertDialog.Builder(_view.context)
        val view: View =
            requireActivity().layoutInflater.inflate(R.layout.time_over_alert, null)
        dialog.setView(view)
        val btnTimeNextQuestion = view.findViewById<Button>(R.id.btnTimeNextQuestion)
        if (_currentPosition == NUMBER_QUESTIONS) btnTimeNextQuestion.text = getString(R.string.fim)

        val alert = dialog.create()

        btnTimeNextQuestion.setOnClickListener {
            nextQuestion()
            alert.dismiss()
        }
        alert.setCancelable(false)
        alert.show()

        alert?.setOnKeyListener { dialog, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.onBackPressed()
                dialog?.dismiss()
                true
            }
            false
        }
    }

    private fun startCountDownBar() {
        countdownBar.max = 15000
        val currentProgress = 15000

        timerBarAnimation = ObjectAnimator.ofInt(countdownBar, "progress", currentProgress).setDuration(15000)
        timerBarAnimation.start()
    }

    private fun stopTimer() {
        timerBarAnimation.cancel()
        countDownTimer.cancel()
    }

    private fun changeCountDownColor() {
        if (timeLeftInMillis <= 6000) {
            txtChronometer.setTextColor(Color.parseColor("#FF3034"))
        } else {
            txtChronometer.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    companion object {
        const val NUMBER_QUESTIONS = 10
    }
}


