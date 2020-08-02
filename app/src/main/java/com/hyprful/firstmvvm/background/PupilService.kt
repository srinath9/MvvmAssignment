package com.hyprful.firstmvvm.background

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.lifecycle.Observer
import com.hyprful.firstmvvm.Constant.BASE_URL
import com.hyprful.firstmvvm.DataRepository
import com.hyprful.firstmvvm.view.activity.MainActivity
import com.hyprful.firstmvvm.db.AppDatabase
import com.hyprful.firstmvvm.db.entity.PupilEntity
import com.hyprful.firstmvvm.api.repository.PupilServerService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class PupilService : JobIntentService() {


    override fun onCreate() {
        super.onCreate()
        initiateParaters()
    }

    private fun initiateParaters() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("X-Request-ID", "d142d449-fac0-41e0-b7a4-b3bd3bc1b569")
                .addHeader("User-Agent", "Bridge Android Tech Test")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }

        val build = httpClient.build()

        var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(build)
            .build()

         pupilServerService = retrofit.create(PupilServerService::class.java)

    }


    override fun onHandleWork(intent: Intent) {
        Log.i("PupilService", "Executing work: $intent")

        if (intent.hasExtra("pupils")){
            val pupilEntitys = intent.getParcelableArrayListExtra<PupilEntity>("pupils")!!;
            syncServer(pupilEntitys)
        }else{
            Log.i("pupilserver", "Empty data");

        }

    }

    private fun syncServer(pupilEntitys: ArrayList<PupilEntity>) {
//        pupilEntitys.forEach {
//            pupilServerService.create(it.generatePupil()).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ value ->
////                    println("Received: $value")
//                    Log.i("syncServer", "value created "+value.toString())
//                },
//                    { error -> println("Error: $error") },
//                    { println("Completed!") }
//                )
//
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
//        toast("All work complete")
    }

    private lateinit var pupilServerService: PupilServerService
    val mHandler = Handler()

    // Helper for showing tests
    fun toast(text: CharSequence?) {
        mHandler.post { Toast.makeText(this@PupilService, text, Toast.LENGTH_SHORT).show() }
    }

    companion object {
        const val JOB_ID = 1000

        /**
         * Convenience method for enqueuing work in to this service.
         */
        fun enqueueWork(context: Context, work: Intent) {
            pushDbData(context, work)
//            enqueueWork( context, PupilService::class.java, JOB_ID, work )
        }

        private fun pushDbData(context: Context, work: Intent) {
            val database = AppDatabase.getDatabase(context)
            val dataRepository = DataRepository(database)
            dataRepository.allPupil.observe(( context as MainActivity), object : Observer<List<PupilEntity>>{
                override fun onChanged(data: List<PupilEntity>) {
                    work.putParcelableArrayListExtra("pupils" , data as ArrayList<out Parcelable>)

                    enqueueWork( context, PupilService::class.java, JOB_ID, work )

                }

            });
        }
    }
}