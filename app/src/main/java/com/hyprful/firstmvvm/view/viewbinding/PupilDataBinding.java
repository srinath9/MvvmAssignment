package com.hyprful.firstmvvm.view.viewbinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hyprful.firstmvvm.BR;
import com.hyprful.firstmvvm.api.model.Pupil;

public class PupilDataBinding extends BaseObservable {
    private Pupil pupil = new Pupil();

    String name;
    String country;
    String latitude;
    String longitude;


    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    @Bindable
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        pupil.setName(name);
        // Notify observers of a new value.
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCountry(){
        return pupil.getCountry();
    }

    public void setCountry(String country){
        this.country = country;
        pupil.setCountry(country);
        // Notify observers of a new value.
        notifyPropertyChanged(BR.country);
    }
    @Bindable
    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String lat){
        if(lat != null && lat.length() > 0) {
            try {
                double latitude = Double.parseDouble(lat);
                pupil.setLatitude(latitude);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            pupil.setLatitude(0.0);
        }
        this.latitude = lat;

        // Notify observers of a new value.
        notifyPropertyChanged(BR.latitude);
    }


    @Bindable
    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String lon){
        if(lon != null && lon.length() > 0) {
            try {
                double longitude = Double.parseDouble(lon);
                pupil.setLongitude(longitude);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            pupil.setLongitude(0.0);
        }
        this.longitude = lon;
        // Notify observers of a new value.
        notifyPropertyChanged(BR.longitude);
    }
}
