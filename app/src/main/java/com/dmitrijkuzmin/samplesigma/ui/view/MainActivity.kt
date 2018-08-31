package com.dmitrijkuzmin.samplesigma.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dmitrijkuzmin.samplesigma.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }
}
