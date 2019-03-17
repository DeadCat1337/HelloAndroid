package com.example.kotlintest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val timer = object : CountDownTimer(2000, 100) {
        override fun onFinish() {
            //TimerActivity.start(this);
            //val intent = Intent(this, TimerActivity::class.java)
            //startActivity(intent)
            //findViewById<Button>(R.id.start_button).text = "Expired"
            startTimerActivity()
        }

        override fun onTick(value: Long) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //timer.start()
        val start = findViewById<Button>(R.id.start_button)
        start.text = "Just Started"
        /*start.setOnClickListener {
            //start.text = "Clicked"
            //timer.start()
            //startTimerActivity()
        }*/
    }

    private fun startTimerActivity() {
        val intent = Intent(this, TimerActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onResume() {
        super.onResume()
        timer.start()
    }
}
