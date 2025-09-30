package ru.kirill.ecquizgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.kirill.ecquizgame.gragments.game.GameScreen
import ru.kirill.ecquizgame.gragments.game.NavigateToGame
import ru.kirill.ecquizgame.gragments.stats.NavigateToStats
import ru.kirill.ecquizgame.gragments.stats.StatsScreen

class MainActivity : AppCompatActivity(), Navigate{
    private lateinit var uiState: GameUiState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        val binding = GameFragmentBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_container)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        if (savedInstanceState == null) {
            navigateToGame()
        }
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }
}

interface Navigate : NavigateToStats, NavigateToGame {
    fun navigate(screen: Screen)

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToStats() {
        navigate(StatsScreen)
    }
}

