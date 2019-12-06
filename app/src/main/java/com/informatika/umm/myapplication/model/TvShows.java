package com.informatika.umm.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * MADE_Submission_2
 * created by : Robin Nusantara on 11/30/2019 11 2019
 * 10:22 Sat
 **/
public class TvShows implements Parcelable {

    private String tvShowsPoster;
    private String tvShowsBackdrop;
    private String tvShowsTitle;
    private Double tvShowsScore;
    private String tvShowsRelease;
    private String tvShowsOverview;

    public String getTvShowsPoster() {
        return tvShowsPoster;
    }

    public String getTvShowsTitles() {
        return tvShowsTitle;
    }

    public Double getTvShowsScore() {
        return tvShowsScore;
    }

    public String getTvShowsRelease() {
        return tvShowsRelease;
    }

    public String getTvShowsBackdrop() {
        return tvShowsBackdrop;
    }

    public String getTvShowsOverview() {
        return tvShowsOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tvShowsPoster);
        dest.writeString(this.tvShowsBackdrop);
        dest.writeString(this.tvShowsTitle);
        dest.writeDouble(this.tvShowsScore);
        dest.writeString(this.tvShowsRelease);
        dest.writeString(this.tvShowsOverview);
    }

    protected TvShows(Parcel in) {
        this.tvShowsPoster = in.readString();
        this.tvShowsBackdrop = in.readString();
        this.tvShowsTitle = in.readString();
        this.tvShowsScore = in.readDouble();
        this.tvShowsRelease = in.readString();
        this.tvShowsOverview = in.readString();
    }

    public static final Creator<TvShows> CREATOR = new Creator<TvShows>() {
        @Override
        public TvShows createFromParcel(Parcel source) {
            return new TvShows(source);
        }

        @Override
        public TvShows[] newArray(int size) {
            return new TvShows[size];
        }
    };

    public TvShows(JSONObject object) {
        try {
            String tvShowsPoster = object.getString("poster_path");
            String tvShowsBackdrop = object.getString("backdrop_path");
            String tvShowsTitle = object.getString("name");
            Double tvShowsScore = object.getDouble("vote_average");
            String tvShowsRelease = object.getString("first_air_date");
            String tvShowsOverview = object.getString("overview");

            this.tvShowsPoster = tvShowsPoster;
            this.tvShowsBackdrop = tvShowsBackdrop;
            this.tvShowsTitle = tvShowsTitle;
            this.tvShowsScore = tvShowsScore;
            this.tvShowsRelease = tvShowsRelease;
            this.tvShowsOverview = tvShowsOverview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
