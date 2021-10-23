package com.example.dammetruyen.api;

import android.os.AsyncTask;

import com.example.dammetruyen.interfaces.LayAnhVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayAnhChapVe extends AsyncTask<Void, Void, Void> {
    String data;
    LayAnhVe layAnhVe;
    String idChap;

    public ApiLayAnhChapVe(LayAnhVe layAnhVe, String idChap) {
        this.layAnhVe = layAnhVe;
        this.idChap = idChap;
        this.layAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dammetruyen53.000webhostapp.com/layAnhChap.php?idChap="+idChap)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();

        } catch (IOException e) {
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null) {
            this.layAnhVe.biLoi();

        } else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
