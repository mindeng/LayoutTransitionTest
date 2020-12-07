package com.example.layouttransitiontest

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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
      val button = TextView(this@MainActivity)
      button.setPadding(20, 20, 20, 20)
      button.text = "❤️"
      button.textSize = 60f

      button.setOnClickListener {

      }

      val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT)
      mContainer.addView(button, mContainer.childCount, params)

    }

    mBtnDel = findViewById<View>(R.id.btndel)
    mBtnDel.setOnClickListener {
      val count: Int = mContainer.childCount
      if (count >= 1) {
        mContainer.removeViewAt(count - 1)
      }
    }
  }

  private fun createBubbleTransition(): LayoutTransition {
    val layoutTransition = LayoutTransition()
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