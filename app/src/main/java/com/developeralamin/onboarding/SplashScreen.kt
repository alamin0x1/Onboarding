package com.developeralamin.onboarding

import android.app.TaskStackBuilder
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        preferences = this.getSharedPreferences("splash", MODE_PRIVATE)
        editor = preferences.edit()

        Handler(Looper.myLooper()!!).postDelayed({
            if (preferences.getBoolean("isMain", false)) {
                startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
                finish()
            } else {
                editor.putBoolean("isMain", true)
                editor.apply()

                TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(Intent(this, HomeActivity::class.java))
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .startActivities()
            }
        }, 2000)
    }
}