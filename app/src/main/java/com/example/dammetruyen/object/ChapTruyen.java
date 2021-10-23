package com.example.dammetruyen.object;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen implements Parcelable {
    private String tenSoChap, ngayDang, id;

    public ChapTruyen() {
    }

    public ChapTruyen(String tenSoChap, String ngayDang) {
        this.tenSoChap = tenSoChap;
        this.ngayDang = ngayDang;
    }

    public ChapTruyen(JSONObject o) throws JSONException {
        tenSoChap = o.getString("tenSoChap");
        ngayDang = o.getString("ngayDang");
        id = o.getString("id");


    }

    protected ChapTruyen(Parcel in) {
        tenSoChap = in.readString();
        ngayDang = in.readString();
        id = in.readString();
    }


    public static final Creator<ChapTruyen> CREATOR = new Creator<ChapTruyen>() {
        @Override
        public ChapTruyen createFromParcel(Parcel in) {
            return new ChapTruyen(in);
        }

        @Override
        public ChapTruyen[] newArray(int size) {
            return new ChapTruyen[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSoChap() {
        return tenSoChap;
    }

    public void setTenSoChap(String tenSoChap) {
        this.tenSoChap = tenSoChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tenSoChap);
        dest.writeString(ngayDang);
        dest.writeString(id);
    }
}
