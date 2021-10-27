package com.example.dammetruyen.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TruyenTranh implements Serializable {
    private String tenTruyen;
    private String tenChap;
    private String linkAnh;
    private String theLoaiTruyen;
    private String id;

    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh, String theLoaiTruyen) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.linkAnh = linkAnh;
        this.theLoaiTruyen = theLoaiTruyen;
    }

    public TruyenTranh() {
    }

    /*
    {
    "id":""
    "tenTruyen":""
    "tenChap":""
    "linkAnh":""
    "theLoai":""
    }
    */

    public TruyenTranh(JSONObject o) throws JSONException {
        id = o.getString("id");
        tenTruyen = o.getString("tenTruyen");
        tenChap = o.getString("tenChap");
        linkAnh = o.getString("linkAnh");
        theLoaiTruyen = o.getString("theLoai");

    }

    public String getTheLoaiTruyen() {
        return theLoaiTruyen;
    }

    public void setTheLoaiTruyen(String theLoaiTruyen) {
        this.theLoaiTruyen = theLoaiTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
