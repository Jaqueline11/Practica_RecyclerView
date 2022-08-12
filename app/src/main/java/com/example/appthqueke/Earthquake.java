package com.example.appthqueke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Earthquake implements Parcelable {

    private String id;
    private String place;
    private double magnitud;
    private long time;
    private double latitud;
    private double longitud;


    public Earthquake(String id, String place, double magnitud, long time, double latitud, double longitud) {
        this.id = id;
        this.place = place;
        this.magnitud = magnitud;
        this.time = time;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected Earthquake(Parcel in) {
        id = in.readString();
        place = in.readString();
        magnitud = in.readDouble();
        time = in.readLong();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return Double.compare(that.magnitud, magnitud) == 0 && time == that.time && Double.compare(that.latitud, latitud) == 0 && Double.compare(that.longitud, longitud) == 0 && id.equals(that.id) && place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, magnitud, time, latitud, longitud);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(place);
        parcel.writeDouble(magnitud);
        parcel.writeLong(time);
        parcel.writeDouble(latitud);
        parcel.writeDouble(longitud);
    }
}
