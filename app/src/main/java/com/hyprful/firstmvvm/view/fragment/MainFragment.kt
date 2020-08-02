package com.hyprful.firstmvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyprful.firstmvvm.view.activity.MainActivity
import com.hyprful.firstmvvm.R
import com.hyprful.firstmvvm.api.model.ApiResponse
import com.hyprful.firstmvvm.api.model.SuccessApiResponse
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.api.model.PupilResponse
import com.hyprful.firstmvvm.view.adapter.PupilAdapter
import com.hyprful.firstmvvm.view.callback.PupilClickCallBack
import com.hyprful.firstmvvm.viewmodel.MainFragmentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

  var TAG = javaClass.canonicalName

  @Inject
  lateinit var pupilAdapter: PupilAdapter

  @Inject
  lateinit var mainFragmentViewModel: MainFragmentViewModel

  var pupilList : ArrayList<Pupil> = ArrayList();

  companion object {
    fun newInstance() =
      MainFragment()
  }

//  private lateinit var viewModel: MainViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View {
    val inflate = inflater.inflate(R.layout.main_fragment, container, false)
    var rvPupil = inflate.findViewById<RecyclerView>(R.id.rv_pupil)

    pupilAdapter.pupilClickCallback = pupilClickCallback
    pupilAdapter.pupilsList = pupilList
    rvPupil.adapter = pupilAdapter
    rvPupil.layoutManager = LinearLayoutManager(activity )
    return inflate
  }


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    mainFragmentViewModel.fetchData().observe(viewLifecycleOwner,
      object : Observer<ApiResponse<PupilResponse>> {
        override fun onChanged(apiResponse: ApiResponse<PupilResponse>) {
//            binding.setIsLoading(false)
          Log.i(TAG, apiResponse.toString());
          if ( apiResponse is SuccessApiResponse){
            pupilList.clear()
            pupilList.addAll(apiResponse.result.pupils)
//            pupilAdapter.setPupilList(pupils)
            pupilAdapter.notifyDataSetChanged()
          }else{
            Log.w(TAG, apiResponse.error.toString())
            Toast.makeText(activity, "Server Error ${apiResponse.error.message}", Toast.LENGTH_SHORT).show()

          }
        }
      }
    )



  }

  private val pupilClickCallback: PupilClickCallBack = object : PupilClickCallBack {
    override fun onClick(pupil: Pupil) {
      if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
        (activity as MainActivity).show(pupil)
      }
    }
  }

}