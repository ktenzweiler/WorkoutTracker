package com.kodingwithkyle.workouttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodingwithkyle.workouttracker.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private val mMainFragment by lazy {
        MainFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mMainFragment)
                .commitNow()
        }
    }

    fun onListItemClick(i: Int) {
        mMainFragment.onListItemClick(i)
    }

    fun showMainFragment() {
        supportFragmentManager.apply {
            beginTransaction().replace(R.id.container, mMainFragment).commit()
        }
    }
}