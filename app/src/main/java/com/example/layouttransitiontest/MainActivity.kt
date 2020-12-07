package com.example.layouttransitiontest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

  private lateinit var mBtnDel: View
  private lateinit var mBtnAdd: View
  private lateinit var mContainer: ViewGroup

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    mContainer = findViewById<View>(R.id.ll_container) as ViewGroup
    mBtnAdd = findViewById<View>(R.id.btnadd)
    mBtnAdd.setOnClickListener {
      val button = Button(this@MainActivity)
      button.setPadding(20, 20, 20, 20)
      button.text = "tempBtn"
      mContainer.addView(button, 2)
    }

    mBtnDel = findViewById<View>(R.id.btndel)
    mBtnDel.setOnClickListener {
      val count: Int = mContainer.getChildCount()
      if (count >= 3) {
        mContainer.removeViewAt(2)
      }
    }
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