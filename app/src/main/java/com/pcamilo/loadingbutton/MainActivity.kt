package com.pcamilo.loadingbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import br.com.loadingbutton.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        startBtn.setOnClickListener {
            if (this.btnLoading.btnIsLoading()) {
                btnLoading.isLoading(isLoading = true)
            } else {
                btnLoading.isLoading(isLoading = false)
            }
        }
    }

}
