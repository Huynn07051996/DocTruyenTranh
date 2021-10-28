package com.example.dammetruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dammetruyen.adapter.ChapTruyenAdapter;
import com.example.dammetruyen.api.ApiLayChapTruyen;
import com.example.dammetruyen.interfaces.LayChapVe;
import com.example.dammetruyen.object.ChapTruyen;
import com.example.dammetruyen.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {
    TextView txvTenChapTruyen;
    TextView txvTheLoaiTruyen;
    ImageView imgAnhChapTruyen;
    TruyenTranh truyenTranh;
    ListView lsvDanhSachChapTruyen;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        inIt();
        anhXa();
        setUp();
        setClick();
        new ApiLayChapTruyen(this, truyenTranh.getId()).execute();
    }

    private void inIt() {
        Bundle bundle = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) bundle.getSerializable("truyen");

        //Tao du lieu ao
        arrChap = new ArrayList<>();
        /*for(int i =0; i<=20;i++){
            arrChap.add(new ChapTruyen("Chapter "+i,"10-01-2021"));
        }
        chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);

         */

    }

    private void anhXa() {
        txvTenChapTruyen = findViewById(R.id.txvTenChapTruyen);
        txvTheLoaiTruyen = findViewById(R.id.txvTheLoaiTruyen);
        imgAnhChapTruyen = findViewById(R.id.imgAnhChapTruyen);

        lsvDanhSachChapTruyen = findViewById(R.id.lsvDanhSachChapTruyen);

    }

    private void setUp() {
        txvTenChapTruyen.setText(truyenTranh.getTenTruyen());
        txvTheLoaiTruyen.setText(truyenTranh.getTheLoaiTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhChapTruyen);

//        lsvDanhSachChapTruyen.setAdapter(chapTruyenAdapter);


    }

    private void setClick() {
        lsvDanhSachChapTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("idChap", arrChap.get(position).getId());
                bundle.putInt("position", position);
                bundle.putParcelableArrayList("arrChap", arrChap);
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Lay chap ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
            lsvDanhSachChapTruyen.setAdapter(chapTruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }
}