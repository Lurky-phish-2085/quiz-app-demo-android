package xyz.lurkyphish2085.quizappdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import xyz.lurkyphish2085.quizappdemo.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private var _navController: NavController? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val navController get() = _navController!!
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(layoutInflater, container, false)
        _navController = NavHostFragment.findNavController(this)
        bindViews()
        setupButtons()

        binding.quizChoicesRadiogrp.setOnCheckedChangeListener { radioGroup, id ->
            run {
                val checkedButton = radioGroup.findViewById<RadioButton>(id)
                sharedViewModel.answerQuiz(checkedButton.text.toString())
            }
        }

        return binding.root
    }

    private fun bindViews() {
        sharedViewModel.progressValue.observe(viewLifecycleOwner) {
            ObjectAnimator.ofInt(binding.progressBar, "progress", it)
                .setDuration(300)
                .start()
        }

        sharedViewModel.quizCountText.observe(viewLifecycleOwner) {
            binding.quizCountTv.text = it.toString()
        }

        sharedViewModel.quizQuestion.observe(viewLifecycleOwner) {
            binding.questionTv.text = it.toString()
        }

        sharedViewModel.quizChoices.observe(viewLifecycleOwner) {
            binding.quizChoicesRadiogrp.removeAllViews()
            it.forEach { choice ->
                run {
                    val radioButton = RadioButton(context)
                    radioButton.text = choice
                    binding.quizChoicesRadiogrp.addView(radioButton)
                }
            }
        }

        sharedViewModel.quizHasNext.observe(viewLifecycleOwner) {
            if (it) {
                binding.nextQuizBtn.text = "NEXT"
            } else {
                binding.nextQuizBtn.text = "FINISH"
            }
        }

        sharedViewModel.quizHasPrev.observe(viewLifecycleOwner) {
            binding.prevQuizBtn.isEnabled = it
        }
    }

    private fun setupButtons() {
        binding.nextQuizBtn.setOnClickListener {
            if ((it as Button).text.equals("FINISH")) {
                navController.navigate(R.id.action_quizFragment_to_resultFragment)
            }

            sharedViewModel.nextQuizItem()
        }

        binding.prevQuizBtn.setOnClickListener {
            sharedViewModel.prevQuizItem()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}