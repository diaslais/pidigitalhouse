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
import com.nasinha.digitalspace.quiz.entity.Score
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import com.nasinha.digitalspace.quiz.viewmodel.QuizViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.NUMBER_QUESTIONS
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class QuestionsFragment : Fragment(), View.OnClickListener {
    private var _currentPosition: Int = 1
    private var _selectedOptionPosition: Int = 0
    private var _correctAnswers: Int = 0
    private var _isAnswered: Boolean = false //user selected an option
    private var _goToNextQuestion: Boolean = false //user already clicked on "answer"
    private var alternativeAnswers = arrayListOf(1, 2, 3, 4)
    private var minimized = false
    private var mistakes = 0

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerBarAnimation: Animator
    private var timeLeftInMillis: Long = 0
    private lateinit var dialog: AlertDialog.Builder
    private lateinit var alert: AlertDialog
    private var dialogIsInitialized = false

    private lateinit var _view: View
    private lateinit var _viewModel: QuizViewModel

    private lateinit var txtQuestion: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtOptionOne: TextView
    private lateinit var txtOptionTwo: TextView
    private lateinit var txtOptionThree: TextView
    private lateinit var txtOptionFour: TextView
    private lateinit var btnAnswer: MaterialButton
    private lateinit var txtChronometer: TextView
    private lateinit var imageClock: ImageView
    private lateinit var countdownBar: ProgressBar
    private lateinit var navController: NavController
    private lateinit var heartOne: ImageView
    private lateinit var heartTwo: ImageView
    private lateinit var heartThree: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            stopTimer()
            navController.popBackStack()
        }
        callback.isEnabled = true
    }

    override fun onStop() {
        super.onStop()

        stopTimer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        minimized = true
        if (dialogIsInitialized) {
            alert.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        if (minimized) {
            navController.popBackStack()
        }
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

        dialog = AlertDialog.Builder(_view.context)
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
        heartOne = view.findViewById(R.id.imgHeart1)
        heartTwo = view.findViewById(R.id.imgHeart2)
        heartThree = view.findViewById(R.id.imgHeart3)
        navController = findNavController()
        val btnBack = view.findViewById<ImageButton>(R.id.btnBackQuizQuestions)

        setQuestion()

        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        txtOptionOne.setOnClickListener(this)
        txtOptionTwo.setOnClickListener(this)
        txtOptionThree.setOnClickListener(this)
        txtOptionFour.setOnClickListener(this)
        btnAnswer.setOnClickListener(this)
    }

    private fun setQuestion() {
        alternativeAnswers.shuffle()
        btnAnswer.isEnabled = true

        setHearts()

        _viewModel.questionsList.observe(viewLifecycleOwner) {
            val question = it[_currentPosition - 1]
            defaultOptionsView()
            startCountDown()

            btnAnswer.text = getString(R.string.Responder)
            txtQuestionNumber.text = getString(R.string.contagem_questoes, _currentPosition, NUMBER_QUESTIONS)
            txtQuestion.text = question.question

            val optionsList = arrayListOf(
                question.optionOne,
                question.optionTwo,
                question.optionThree,
                question.optionFour
            )

            //set options text shuffled
            txtOptionOne.text = optionsList[alternativeAnswers[0] - 1]
            txtOptionTwo.text = optionsList[alternativeAnswers[1] - 1]
            txtOptionThree.text = optionsList[alternativeAnswers[2] - 1]
            txtOptionFour.text = optionsList[alternativeAnswers[3] - 1]
        }
    }

    private fun setHearts() {
        when (mistakes) {
            0 -> {
                heartOne.setImageResource(R.drawable.heart_blue)
                heartTwo.setImageResource(R.drawable.heart_blue)
                heartThree.setImageResource(R.drawable.heart_blue)
            }
            1 -> {
                heartOne.setImageResource(R.drawable.heart_grey)
                heartTwo.setImageResource(R.drawable.heart_blue)
                heartThree.setImageResource(R.drawable.heart_blue)
            }
            2 -> {
                heartOne.setImageResource(R.drawable.heart_grey)
                heartTwo.setImageResource(R.drawable.heart_grey)
                heartThree.setImageResource(R.drawable.heart_blue)
            }
            3 -> {
                goToResultScreen()
            }
        }
    }

    private fun answerView(answer: Int?, drawableView: Int) {
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
                        optionsToggle()
                    } else if (_isAnswered) { //first click
                        stopTimer()
                        _goToNextQuestion = true
                        if (question.correctAnswer != alternativeAnswers[_selectedOptionPosition - 1]) {
                            mistakes++
                            answerView(_selectedOptionPosition, R.drawable.incorrect_question)
                        } else {
                            _correctAnswers++
                        }
                        answerView(alternativeAnswers.indexOf(question.correctAnswer) + 1, R.drawable.correct_question)

                        if (_currentPosition == NUMBER_QUESTIONS) {
                            btnAnswer.text = getString(R.string.fim)
                        } else {
                            btnAnswer.text = getString(R.string.proxima_questao)
                        }
                        optionsToggle()
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
            goToResultScreen()
        }
    }

    private fun goToResultScreen() {
        //add to database
        val date = getCurrentDateTime()
        addScoreToDatabase(date, _correctAnswers)

        val astronautImage: Int
        val resultsTitle: String
        val resultsMessage: String

        if (mistakes >= 3) {
            astronautImage = R.drawable.img_lost
            resultsMessage = getString(R.string.perdido_no_espaco)
            resultsTitle = getString(R.string.fim_jogo)
        } else{
            astronautImage = R.drawable.img_win2
            resultsMessage = getString(R.string.parab_ns)
            resultsTitle = getString(R.string.muito_bem)
        }

        val bundle = bundleOf(
                "CORRECT_ANSWERS" to _correctAnswers,
                "TOTAL_QUESTIONS" to NUMBER_QUESTIONS,
                "RESULTS_TITLE" to resultsTitle,
                "ASTRONAUT_IMAGE" to astronautImage,
                "RESULTS_MESSAGE" to resultsMessage
        )
        navController.navigate(
                R.id.action_questionsFragment_to_resultFragment, bundle
        )
    }

    private fun addScoreToDatabase(date: String, points: Int) {
        val userId = AuthUtil.getUserId(requireActivity().application)!!
        val currentScore = Score(null, date, points, userId)

        _viewModel.addScore(currentScore).observe(viewLifecycleOwner) {}

    }

    private fun getCurrentDateTime(): String {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return formatter.format(currentDate)
    }

    private fun optionsToggle() {
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
                timeIsOverDialog()
                btnAnswer.isEnabled = false
                timeLeftInMillis = 0
            }
        }
        countDownTimer.start()
    }

    private fun timeIsOverDialog() {
        mistakes++

        val view: View =
            requireActivity().layoutInflater.inflate(R.layout.time_over_alert, null)
        dialog.setView(view)
        val btnTimeNextQuestion = view.findViewById<Button>(R.id.btnTimeNextQuestion)
        if (_currentPosition == NUMBER_QUESTIONS) btnTimeNextQuestion.text = getString(R.string.fim)

        alert = dialog.create()
        dialogIsInitialized = true

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
            txtChronometer.setTextColor(Color.parseColor("#FF4D51"))
        } else {
            txtChronometer.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
}