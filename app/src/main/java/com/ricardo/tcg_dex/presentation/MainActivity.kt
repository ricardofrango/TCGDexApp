package com.ricardo.tcg_dex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ricardo.tcg_dex.presentation.screen_card_details.cardDetailsComposable
import com.ricardo.tcg_dex.presentation.screen_cards_list.cardsListComposable
import com.ricardo.tcg_dex.ui.theme.TCGDexAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TCGDexAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = TCGDexScreen.CardList.route
                    ) {
                        cardsListComposable(Modifier.padding(innerPadding), navController::navigate)
                        cardDetailsComposable(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
