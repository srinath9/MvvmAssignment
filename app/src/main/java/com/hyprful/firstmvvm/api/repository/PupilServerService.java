package com.hyprful.firstmvvm.api.repository;


import com.hyprful.firstmvvm.api.model.Pupil;
import com.hyprful.firstmvvm.api.model.PupilResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PupilServerService {


    @GET("/pupils")
    Observable<PupilResponse> getPupilList(@Query("page") int page);


    @GET("/pupils/{id}")
    Observable<Pupil> getPupilDetails(@Path("id") int id);

    @POST("/pupils")
    Observable<Pupil> create(@Body Pupil pupil);
}
