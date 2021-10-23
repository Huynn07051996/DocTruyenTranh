package com.example.dammetruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dammetruyen.api.ApiLayTruyen;
import com.example.dammetruyen.interfaces.LayTruyenVe;
import com.example.dammetruyen.adapter.TruyenTranhAdapter;
import com.example.dammetruyen.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDanhSachTruyen;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    TruyenTranhAdapter adapter;
    EditText edtTimKiemTruyenTranh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inIt();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();

    }

    private void inIt() {
        truyenTranhArrayList = new ArrayList<>();
//        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }

    private void anhXa() {
        gdvDanhSachTruyen = findViewById(R.id.gdvDanhSachTruyen);
        edtTimKiemTruyenTranh = findViewById(R.id.edtTimKiemTruyenTranh);
    }

    private void setUp() {
        gdvDanhSachTruyen.setAdapter(adapter);
    }

    private void setClick() {
        edtTimKiemTruyenTranh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = edtTimKiemTruyenTranh.getText().toString();
                adapter.sortTruyen(string);
            }
        });
        gdvDanhSachTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang Lay ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
            gdvDanhSachTruyen.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();

    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }
}