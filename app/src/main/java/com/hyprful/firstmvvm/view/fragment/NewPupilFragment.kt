package com.hyprful.firstmvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hyprful.firstmvvm.R
import com.hyprful.firstmvvm.databinding.NewPupilFragmentBinding
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.viewmodel.NewPupilFragmentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class NewPupilFragment : DaggerFragment(){

    @Inject
    lateinit var newPupilFragmentViewModel: NewPupilFragmentViewModel
    var pupil : Pupil = Pupil()

    var TAG = javaClass.canonicalName

    companion object {
        fun newInstance() =
            NewPupilFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: NewPupilFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.new_pupil_fragment, container, false)

        binding.data = newPupilFragmentViewModel.data
        binding.fragment = this

        return binding.root
    }

    fun submit(){
        newPupilFragmentViewModel.createPupil().observe(viewLifecycleOwner,
            object : Observer<Boolean> {
                override fun onChanged(result: Boolean) {
                    if(result){
                        Toast.makeText(activity, "Pupil Created", Toast.LENGTH_SHORT).show()
                    }
                    onBackPressed()

                }
            }
        )

//        newPupilFragmentViewModel.getDbPupil().observe(viewLifecycleOwner,
//            object : Observer<List<PupilEntity>>{
//                override fun onChanged(t: List<PupilEntity>?) {
//                    Log.i("all data" , t.toString())
//                }
//
//            })


    }

    fun onBackPressed() {
        activity!!.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i(TAG, newPupilFragmentViewModel.toString())

    }
}