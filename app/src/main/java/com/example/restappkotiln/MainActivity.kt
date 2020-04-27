package com.example.restappkotiln

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private var mp: MediaPlayer? = null
    private var set: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()
        mp = MediaPlayer.create(this, R.raw.beep)

        button_start.setOnClickListener { v ->
            if(::mRunnable.isInitialized) mHandler.removeCallbacks(mRunnable)

            var timeValue = editText_time.text.toString().toInt()
            textView_time.text = timeValue.toString()

            mRunnable = Runnable {
                timeValue--
                when (timeValue) {
                    0 -> {
                        textView_time.text = timeValue.toString()
                        set++
                        textView_set.text = set.toString()
                        mp?.start()
                        mHandler.removeCallbacks(mRunnable)
                    }
                    else -> {
                        textView_time.text = timeValue.toString()

                        mHandler.postDelayed(
                            mRunnable, // Runnable
                            1000 // Delay in milliseconds
                        )
                    }
                }
            }
            mHandler.postDelayed(
                mRunnable, // Runnable
                1000 // Delay in milliseconds
            )
        }

        button_stop.setOnClickListener { v ->
            mHandler.removeCallbacks(mRunnable)
        }

        button_resetSet.setOnClickListener { v ->
            set = 0
            textView_set.text = set.toString()
        }

        button_plus.setOnClickListener { v ->
            set++
            textView_set.text = set.toString()
        }
    }
}