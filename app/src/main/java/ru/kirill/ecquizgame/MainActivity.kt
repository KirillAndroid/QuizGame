package ru.kirill.ecquizgame

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kirill.ecquizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var uiState: GameUiState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val update: () -> Unit = {
            uiState.update(binding.questionTextview,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.forthChoiceButton,
                binding.checkButton,
                binding.nextButton)
        }

        val viewModel: GameViewModel = (application as QuizGameApp).viewModel
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
}