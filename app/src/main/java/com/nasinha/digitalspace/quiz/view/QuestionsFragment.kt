package com.nasinha.digitalspace.quiz.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.quiz.data.Constants
import com.nasinha.digitalspace.quiz.model.QuestionsModel
import kotlinx.android.synthetic.main.fragment_questions.*
import java.util.*
import kotlin.collections.ArrayList


class QuestionsFragment : Fragment(), View.OnClickListener {

    private var _currentPosition: Int = 1
    private var _questionsList: ArrayList<QuestionsModel>? = null
    private var _selectedOptionPosition: Int = 0
    private lateinit var _view: View
    private var _correctAnswers: Int = 0
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 0
    private var countDownMillis: Long = 30000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.fragment_questions, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizToolBar = view.findViewById<MaterialToolbar>(R.id.quizTopAppBarQuestions)

        quizToolBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        _questionsList = Constants.getQuestions()

        setQuestion()

        val optionOne = _view.findViewById<TextView>(R.id.btnAnswer1)
        val optionTwo = _view.findViewById<TextView>(R.id.btnAnswer2)
        val optionThree = _view.findViewById<TextView>(R.id.btnAnswer3)
        val optionFour = _view.findViewById<TextView>(R.id.btnAnswer4)
        val answerButton = _view.findViewById<MaterialButton>(R.id.btnNext)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        answerButton.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {

        val question = _questionsList!![_currentPosition - 1]

        defaultOptionsView()
        startCountDown()

        val answerButton = _view.findViewById<MaterialButton>(R.id.btnNext)

        if (_currentPosition == _questionsList!!.size) {
            answerButton.text = "FIM"
        } else {
            answerButton.text = "Responder"
        }

        val questionNumber = _view.findViewById<TextView>(R.id.txtQuestionNumber)
        questionNumber.text = "$_currentPosition" + "/" + _questionsList!!.size

        val questionText = _view.findViewById<TextView>(R.id.txtQuestion)
        questionText.text = question.question

        val optionOne = _view.findViewById<TextView>(R.id.btnAnswer1)
        val optionTwo = _view.findViewById<TextView>(R.id.btnAnswer2)
        val optionThree = _view.findViewById<TextView>(R.id.btnAnswer3)
        val optionFour = _view.findViewById<TextView>(R.id.btnAnswer4)

        optionOne.text = question.answer1
        optionTwo.text = question.answer2
        optionThree.text = question.answer3
        optionFour.text = question.answer4
    }

    private fun startCountDown() {
        val optionOne = _view.findViewById<TextView>(R.id.btnAnswer1)
        val optionTwo = _view.findViewById<TextView>(R.id.btnAnswer2)
        val optionThree = _view.findViewById<TextView>(R.id.btnAnswer3)
        val optionFour = _view.findViewById<TextView>(R.id.btnAnswer4)

        countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val minutes = (timeLeftInMillis / 1000) / 60
                val seconds = (timeLeftInMillis / 1000) % 60
                val timeFormatted =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

                val countdown = _view.findViewById<TextView>(R.id.txtChronometer)
                val imageClock = _view.findViewById<ImageView>(R.id.imageClock)

                countdown.text = timeFormatted

                if (seconds <= 10000) {
                    changeCountDownColor()
                }

                if (seconds == 0L) {
                    optionOne.isEnabled = false
                    optionTwo.isEnabled = false
                    optionThree.isEnabled = false
                    optionFour.isEnabled = false
                }
            }

            override fun onFinish() {
                timeLeftInMillis = 0
            }
        }
        countDownTimer.start()
    }

    private fun changeCountDownColor() {
        val countdown = _view.findViewById<TextView>(R.id.txtChronometer)
        val imageClock = _view.findViewById<ImageView>(R.id.imageClock)

        if (timeLeftInMillis < 10000) {
            imageClock.setColorFilter(_view.context.resources.getColor(R.color.colorIncorrectOption))
            countdown.setTextColor(Color.parseColor("#FF3034"))
        } else {
            countdown.setTextColor(Color.parseColor("#FFFFFF"))
            imageClock.setColorFilter(_view.context.resources.getColor(R.color.colorWhite))
        }
    }

    private fun answerQuestion(answer: Int, drawableView: Int) {
        val optionOne = _view.findViewById<TextView>(R.id.btnAnswer1)
        val optionTwo = _view.findViewById<TextView>(R.id.btnAnswer2)
        val optionThree = _view.findViewById<TextView>(R.id.btnAnswer3)
        val optionFour = _view.findViewById<TextView>(R.id.btnAnswer4)

        when (answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(_view.context, drawableView)
            }
        }
    }

    private fun defaultOptionsView() {
        val optionOne = _view.findViewById<TextView>(R.id.btnAnswer1)
        val optionTwo = _view.findViewById<TextView>(R.id.btnAnswer2)
        val optionThree = _view.findViewById<TextView>(R.id.btnAnswer3)
        val optionFour = _view.findViewById<TextView>(R.id.btnAnswer4)

        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#FFFFFF"))
            option.background =
                ContextCompat.getDrawable(_view.context, R.drawable.txt_question_stroke)
            option.isEnabled = true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAnswer1 -> {
                selectedOption(btnAnswer1, 1)
            }
            R.id.btnAnswer2 -> {
                selectedOption(btnAnswer2, 2)
            }
            R.id.btnAnswer3 -> {
                selectedOption(btnAnswer3, 3)
            }
            R.id.btnAnswer4 -> {
                selectedOption(btnAnswer4, 4)
            }
            R.id.btnNext -> {
                if (btnNext.text == "Responder") {
                    btnAnswer1.isEnabled = false
                    btnAnswer2.isEnabled = false
                    btnAnswer3.isEnabled = false
                    btnAnswer4.isEnabled = false
                    countDownTimer.cancel()
                }
                if (_selectedOptionPosition == 0) {
                    _currentPosition++
                    when {
                        _currentPosition <= _questionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val navController = findNavController()
                            val bundle = bundleOf(
                                "CORRECT_ANSWERS" to _correctAnswers,
                                "TOTAL_QUESTIONS" to _questionsList!!.size
                            )
                            if (_correctAnswers >= 5) {
                                navController.navigate(
                                    R.id.action_questionsFragment_to_quizScoreFragment, bundle
                                )
                            } else {
                                navController.navigate(
                                    R.id.action_questionsFragment_to_quizScoreLostFragment, bundle
                                )
                            }
                        }
                    }
                } else {
                    val question = _questionsList?.get(_currentPosition - 1)

                    if (question!!.correctAnswer != _selectedOptionPosition) {
                        answerQuestion(_selectedOptionPosition, R.drawable.incorrect_question)
                    } else {
                        _correctAnswers++
                    }
                    answerQuestion(question.correctAnswer, R.drawable.correct_question)

                    if (_currentPosition == _questionsList!!.size) {
                        btnNext.text = "FIM"
                        countDownTimer.cancel()
                        btnAnswer1.isEnabled = false
                        btnAnswer2.isEnabled = false
                        btnAnswer3.isEnabled = false
                        btnAnswer4.isEnabled = false
                        countDownTimer.cancel()
                    } else {
                        btnNext.text = "Próxima Questão"
                    }
                    _selectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOption(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        _selectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(_view.context, R.drawable.selected_question)
    }

}


