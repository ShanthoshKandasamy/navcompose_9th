package com.bank.navcompose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.bank.navcompose.ui.components.AppScreen
import com.bank.navcompose.ui.components.screens.BottomNavScreen
import com.bank.navcompose.ui.theme.NavComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavComposeTheme{
                //AppScreen()
                // bottom Nav
                BottomNavScreen()
            }
        }
    }
}

