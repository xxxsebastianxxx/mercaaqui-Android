package com.example.mercaaqui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mercaaqui.R
import com.example.mercaaqui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        var animation = AnimationUtils.loadAnimation (this, R.anim.anim)
        img_splash.startAnimation(animation)

        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }
            override fun onAnimationRepeat(animation: Animation?) {

            }
            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()
            }
        })


    }
}