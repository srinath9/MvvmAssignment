package com.hyprful.firstmvvm.db.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.hyprful.firstmvvm.api.model.Pupil;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "pupils")
public class PupilEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "latitude")
    private Double latitude;

    @ColumnInfo(name = "longitude")
    private Double longitude;

    @ColumnInfo(name = "pupil_id")
    private Integer pupilId;

    @ColumnInfo(name = "synced", defaultValue = "false")
    private Boolean synced;



    public PupilEntity(@NotNull Pupil pupil) {
        name = pupil.getName();
        country = pupil.getCountry();
        latitude = pupil.getLatitude();
        longitude = pupil.getLongitude();
        pupilId = pupil.getPupilId();
        synced = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPupilId() {
        return pupilId;
    }

    public void setPupilId(Integer pupilId) {
        this.pupilId = pupilId;
    }


    public Pupil generatePupil() {
        return  new Pupil(this);
    }

    public Boolean getSynced() {
        return synced;
    }

    public void setSynced(Boolean synced) {
        this.synced = synced;
    }

    @Override
    public String toString() {
        return "PupilEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pupilId=" + pupilId +
                ", synced=" + synced +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.country);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeValue(this.pupilId);
        dest.writeValue(this.synced);
    }

    public PupilEntity() {
    }

    @Ignore
    public PupilEntity(String name, String country, Double latitude, Double longitude, Integer pupilId, Boolean synced) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pupilId = pupilId;
        this.synced = synced;
    }

    protected PupilEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.country = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.pupilId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.synced = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PupilEntity> CREATOR = new Parcelable.Creator<PupilEntity>() {
        @Override
        public PupilEntity createFromParcel(Parcel source) {
            return new PupilEntity(source);
        }

        @Override
        public PupilEntity[] newArray(int size) {
            return new PupilEntity[size];
        }
    };
}
