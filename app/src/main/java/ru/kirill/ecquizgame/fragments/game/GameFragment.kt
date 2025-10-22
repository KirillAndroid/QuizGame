package ru.kirill.ecquizgame.fragments.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirill.ecquizgame.QuizGameApp
import ru.kirill.ecquizgame.databinding.GameFragmentBinding
import ru.kirill.ecquizgame.fragments.stats.NavigateToStats

class GameFragment : Fragment() {
    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: GameViewModel = (requireActivity().application as QuizGameApp).viewModel
        lateinit var uiState: GameUiState

        val update: () -> Unit = {
            uiState.update(binding.questionTextview,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.forthChoiceButton,
                binding.checkButton,
                binding.nextButton)
            uiState.navigate(requireActivity() as NavigateToStats) //todo
        }

        binding.firstChoiceButton.setOnClickListener {
            Log.d("dd33", "firstChoiceButton")
            uiState = viewModel.chooseFirst()
            update.invoke()
        }
        binding.secondChoiceButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            update.invoke()
        }
        binding.thirdChoiceButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            update.invoke()
        }
        binding.forthChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFourth()
            update.invoke()
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check()
            update.invoke()
        }
        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            update.invoke()
        }

        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            GameUiState.Empty
        }
        update.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}