package com.hyprful.firstmvvm.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hyprful.firstmvvm.R
import com.hyprful.firstmvvm.view.fragment.MainFragment
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.view.fragment.NewPupilFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity() {

  var MAINFRAGMENT_TAG = MainFragment::class.simpleName;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, MainFragment.newInstance(), MAINFRAGMENT_TAG)
          .commit()
    }

    fab.setOnClickListener { moveToNewPupilScreen() }

//    val intentData = Intent(this, PupilService::class.java)
//    PupilService.enqueueWork(this, intentData)

  }

  fun moveToNewPupilScreen(){
    fab.visibility = View.GONE

    supportFragmentManager.beginTransaction()
      .replace(R.id.container, NewPupilFragment.newInstance())
      .addToBackStack(MAINFRAGMENT_TAG)
      .commit()
  }

    /** Shows the project detail fragment  */
    fun show(pupil: Pupil) {
      Log.i("MAINACTIVITY", pupil.toString())

    }


  override fun onBackPressed() {
    super.onBackPressed()
    fab.visibility = View.VISIBLE
  }

}