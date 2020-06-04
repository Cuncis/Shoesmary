package com.cuncis.shoesmary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuncis.shoesmary.ui.home.HomeActivity
import kotlinx.coroutines.*

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashscreenActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}