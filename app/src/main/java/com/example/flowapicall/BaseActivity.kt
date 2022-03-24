package com.example.flowapicall

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }
}
