package com.example.dammetruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;


import com.example.dammetruyen.adapter.RecyclerAdapter;
import com.example.dammetruyen.api.ApiLayAnhChapVe;
import com.example.dammetruyen.interfaces.LayAnhVe;
import com.example.dammetruyen.object.ChapTruyen;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {
    RecyclerView lsvDanhSachAnh;
    ArrayList<String> arrUrlAnh;
    String idChap;
    int position, soChap, soChapDangDoc;
    ArrayList<ChapTruyen> arrChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        inIt();
        anhXa();
        setUp();
        setClick();
        getData(idChap);

    }

    private void getData(String idChap) {
        new ApiLayAnhChapVe(this, idChap).execute();
    }

    private void inIt() {
        Bundle bundle = getIntent().getBundleExtra("data");
        idChap = bundle.getString("idChap");
        position = bundle.getInt("position");
        arrChap = bundle.getParcelableArrayList("arrChap");
    }

    private void anhXa() {
        // Lookup the recyclerview in activity layout
        lsvDanhSachAnh = findViewById(R.id.lsvDanhSachAnh);

    }

    private void setUp() {
    }

    private void setClick() {
    }

    public void left(View view) {
        if (position <= 0) {
            position = 0;
        } else position -= 1;
        getData(arrChap.get(position).getId());
    }

    public void right(View view) {
        if (position >= arrChap.size()-1) {
            position = arrChap.size()-1;
        } else position += 1;
        getData(arrChap.get(position).getId());
    }


    @Override
    public void batDau() {
        Toast.makeText(this, "Dang Lay ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        arrUrlAnh = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                arrUrlAnh.add(array.getString(i));
            }
            setRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }

    public void setRecyclerView() {
        // Create adapter passing in the sample user data
        RecyclerAdapter adapter = new RecyclerAdapter(arrUrlAnh);
        // Attach the adapter to the recyclerview to populate items
        lsvDanhSachAnh.setAdapter(adapter);
        // Set layout manager to position the items
        lsvDanhSachAnh.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }




}