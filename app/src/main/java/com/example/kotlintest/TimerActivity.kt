package com.example.kotlintest

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class TimerActivity : AppCompatActivity() {
    lateinit var tv : TextView
    lateinit var b1: Button
    var cnt = 0
    private val timer = object : CountDownTimer(1000000, 1000) {
        override fun onFinish() {
            //cnt++
            //tv.text = "" + cnt
            setStringedTime(cnt)
            start()
        }

        override fun onTick(value: Long) {
            cnt++
            setStringedTime(cnt)
            if(cnt == 1000){
                tv.text = "тысяча"
                cancel()
            }
            if(cnt > 1000)
                cnt = 1
        }
    }
    var isRunning: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        tv = findViewById(R.id.text1)
        b1 = findViewById(R.id.button1)
        b1.setOnClickListener {
            if(isRunning) {
                timer.cancel()
                b1.text = "Stopped"
            } else {
                timer.start()
                b1.text = "Running"
            }
            isRunning = !isRunning
        }
        tv.text = "Nothing is here for now"
        b1.text = "Just was born"
    }

    override fun onSaveInstanceState(outState : Bundle) {
        outState.putInt("COUNTER", cnt)
        outState.putBoolean("IS_RUNNING", isRunning)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        cnt = savedInstanceState?.getInt("COUNTER") ?: 0
        isRunning = savedInstanceState?.getBoolean("IS_RUNNING") ?: false
        tv.text = "" + cnt
        if(isRunning) {
            b1.text = "Running from restored"
            timer.start()
        } else {
            b1.text = "Stopped from restored"
        }
    }

    /*override fun onResume() {
        super.onResume()
        tv.text = "Resume"
    }*/

    override fun onPause() {
        super.onPause()
        tv.text = "Paused"
    }

    /*override fun onStart() {
        super.onStart()
        tv?.text = "Hello?"
    }*/

    val units = arrayOf("", "один", "два", "три", "четыре", "пять",
        "шесть", "семь", "восемь", "девять", "десять",
        "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val tens = arrayOf("", "десять ", "двадцать ", "тридцать ", "сорок ", "пятьдесят ",
        "шестьдясят ", "семьдесят ", "восемьдесят ", "девяносто ")
    val hundreds = arrayOf("", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ",
        "шестьсот ", "семьсот ", "восемьсот ", "девятьсот ")

    fun setStringedTime(x : Int){
        var number : String = ""
        var digits = x.toString().toList().map { (it - '0').toInt()}.reversed()

        if(digits.size > 2)number += hundreds[digits[2]]
        //if(digits.size > 1)number += tens[digits[1]]
        //number += units[digits[0]]

        if(x%100 < 20) number += units[x%20]
        else{
            if(digits.size > 1)number += tens[digits[1]]
            number += units[digits[0]]
        }
        tv.text = number
        //b1.text = "" + x + "; \n" + digits.toString()
    }
}