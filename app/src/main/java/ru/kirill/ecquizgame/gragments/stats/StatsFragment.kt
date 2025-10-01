package ru.kirill.ecquizgame.gragments.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirill.ecquizgame.gragments.game.NavigateToGame
import ru.kirill.ecquizgame.QuizGameApp
import ru.kirill.ecquizgame.databinding.StatisticsFragmentBinding

class StatsFragment : Fragment(){

    private var _binding: StatisticsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatisticsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity().application as QuizGameApp).statsViewModel
        val statsUiState = viewModel.statsUiState()
        statsUiState.update(binding.statisticsTextView, binding.newGameButton)
//        binding.statisticsTextView.update(StatsTextViewState)
        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
