package com.example.layouttransitiontest

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
  private lateinit var mBtnDel: View
  private lateinit var mBtnAdd: View
  private lateinit var mContainer: ViewGroup

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.layout2)
    mContainer = findViewById<View>(R.id.bubbleArea) as ViewGroup

    findViewById<ViewGroup>(android.R.id.content).layoutTransition = createLayoutTransition()
    findViewById<ViewGroup>(R.id.layoutContainer).layoutTransition = createLayoutTransition()

//    findViewById<ViewGroup>(R.id.areaComments).layoutTransition = createLayoutTransition()
//    findViewById<ViewGroup>(R.id.areaGifts).layoutTransition = createLayoutTransition()

    mContainer.layoutTransition = createBubbleTransition()

    mBtnAdd = findViewById<View>(R.id.btnadd)
    mBtnAdd.setOnClickListener {
      val bubble = TextView(this@MainActivity)
      bubble.setPadding(20, 20, 20, 20)
      bubble.text = "❤️"
      bubble.textSize = 40f

      bubble.setOnClickListener {
        Log.i("min", "click")
//        val scaleAnimator = ObjectAnimator.ofFloat(null, View.SCALE_X, 1f, 2f, 2f)
//            .setDuration(1500)
//        updateLayout(scaleAnimator)

//        it.layoutParams = it.layoutParams.apply {
//          width *= 2
//          height *= 2
//          Log.i("min", "width: $width, height: $height")
//        }

//        button.textSize *= 2

//        it.animate().scaleX(2f).scaleY(2f).withEndAction {
//          Log.i("min", "width: ${it.width}, height: ${it.height}, x: ${it.x}, y: ${it.y}")
//        }.apply {
//          setUpdateListener {
//            Log.i("min", "value: ${it.animatedValue}")
//            button.layoutParams.apply {
//              width = (button.width * it.animatedValue as Float).toInt()
//              height = (button.height * it.animatedValue as Float).toInt()
//            }
//            button.requestLayout()
//          }
//        }.start()

//        it.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->


//        val heightAnim = ValueAnimator.ofInt(it.height, it.height * 2).apply {
//          addUpdateListener {
//            bubble.layoutParams.apply {
//              height = it.animatedValue as Int
//            }
//            bubble.requestLayout()
//          }
//
//          duration = 500
//        }

//        val scaleAnim: Animator = ObjectAnimator.ofPropertyValuesHolder(bubble,
//            PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f),
//            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f))
//        scaleAnim.duration = 500

//        AnimatorSet().apply {
//          playTogether(scaleAnim, heightAnim)
//        }.start()

      }

      val bubbleContainer = RelativeLayout(this).apply {
//        setBackgroundColor(Color.BLUE)
      }
      bubbleContainer.addView(bubble,
          RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
              .WRAP_CONTENT).apply {
            addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            addRule(RelativeLayout.CENTER_VERTICAL)
          })

      val button = Button(this).apply {
        text = "Scale"
        setOnClickListener {
          val xs = bubbleContainer.width.toFloat() / bubbleContainer.height
          val newH = bubbleContainer.height + 100
          val newW = (newH * xs).toInt()
          bubbleContainer.layoutParams.apply {
            width = newW
            height = newH
            Log.i("min", "width: $width, height: $height, x: ${it.x}, y: ${it.y}")
          }
          bubbleContainer.requestLayout()
        }
      }

      bubbleContainer.addView(button,
          RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
              .WRAP_CONTENT).apply {
            addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            addRule(RelativeLayout.CENTER_VERTICAL)
          })


      val params = ViewGroup.LayoutParams(500,
          ViewGroup.LayoutParams.WRAP_CONTENT)
      mContainer.addView(bubbleContainer, mContainer.childCount, params)

    }

    mBtnDel = findViewById<View>(R.id.btndel)
    mBtnDel.setOnClickListener {
      val count: Int = mContainer.childCount
      if (count >= 1) {
        mContainer.removeViewAt(count - 1)
      }
    }
  }

  private fun updateLayout(animator: ObjectAnimator) {
    mContainer.layoutTransition = createBubbleChangingTransition(animator)
  }

  private fun createBubbleChangingTransition(animator: ObjectAnimator): LayoutTransition? {
    return LayoutTransition().apply {
      enableTransitionType(LayoutTransition.CHANGING)
      setAnimator(LayoutTransition.CHANGING, animator)
    }
  }

  private fun createBubbleTransition(): LayoutTransition {
    val layoutTransition = LayoutTransition()
//    layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    val addAnimator = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 0f, 400f, 0f)
        .setDuration(1500)
    layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, addAnimator)

//    val removeAnimator = ObjectAnimator.ofFloat(null, View.SCALE_X, 0.5f, 1f)
//        .setDuration(1500)

    val removeAnimator = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 400f, 0f, 0f)
        .setDuration(1500)
    layoutTransition.setAnimator(LayoutTransition.APPEARING, removeAnimator)
    return layoutTransition
  }

  private fun createLayoutTransition(): LayoutTransition {
    val layoutTransition = LayoutTransition()
    layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    return layoutTransition
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }
}