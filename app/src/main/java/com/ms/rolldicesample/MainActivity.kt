package com.ms.rolldicesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imgDice: ImageView
    private val diceImages = mutableListOf<Int>()
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgDice = findViewById(R.id.imgDice)
        val btnRoll = findViewById<Button>(R.id.btnRoll)
        btnRoll.setOnClickListener {
            // disable the button click
            btnRoll.isEnabled = false
            getRandomValue()
        }

        // add dice images to list
        diceImages.add(R.drawable.ic_dice_one)
        diceImages.add(R.drawable.ic_dice_two)
        diceImages.add(R.drawable.ic_dice_three)
        diceImages.add(R.drawable.ic_dice_four)
        diceImages.add(R.drawable.ic_dice_five)
        diceImages.add(R.drawable.ic_dice_six)

        // initialize the animation
        animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.shake_anim)

    }

    private fun getRandomValue() {
        val random = Random().nextInt(6) // returns number between 0-5

        // start the animation
        imgDice.startAnimation(animation)

        // now we will get when the animation starts and when it ends

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                // when animation ends we will set the dice image
                imgDice.setImageResource(diceImages.elementAt(random)) // this will set the dice image randomly
                // enable the button click
                btnRoll.isEnabled = true
            }

            override fun onAnimationStart(p0: Animation?) {
                // when animation starts we will set default dice image
                imgDice.setImageResource(R.drawable.ic_dice)
            }
        })
    }
}