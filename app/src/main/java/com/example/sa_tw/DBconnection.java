package com.example.sa_tw;


import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.constraintlayout.widget.ConstraintLayout;

public class DBconnection extends AsyncTask<String, Integer, String> {
//    private String ip = "http://140.125.220.218/xampp/";
//    private String ip = "http://172.20.10.2";//印哥手機
//    private String ip = "http://140.125.220.132/xampp/";
//    private String ip = "http://140.125.217.179/xampp/";
    private String ip = "http://140.125.216.107/xampp/";
    private String result="";
    private HttpURLConnection connection;
    private DBMgr dbMgr ;
    private ProgressBar progressBar;



    protected void onPreExecute(){
        this.dbMgr=DBMgr.getInstance();
        ConstraintLayout constraintLayout = (ConstraintLayout) dbMgr.getActivity().findViewById(R.id.menu);
        progressBar = new ProgressBar(dbMgr.getActivity());

        progressBar.setX(500);
        progressBar.setY(1000);
        constraintLayout.addView(progressBar);

    }
    protected String doInBackground(String... parameter){
        post(parameter);
        return result;
    }
    protected void onPostExecute(String result){
        progressBar.setVisibility(View.INVISIBLE);
        dbMgr.set_result(result);

    }
    private void post(String... data)  {
        try {
            String url_s = ip+data[0];//PHP的網址
            String  paremeter = data[1] ;//要post的資料
            //Log.e("post", "post: "+ paremeter);
            URL url = new URL(url_s);
            connection =(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            String post_string = paremeter;

            outputStream.writeBytes(post_string);
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            int status = connection.getResponseCode();
            if(status == 200){
                if(inputStream != null){
                    InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader in = new BufferedReader(reader);
                    String line="";
                    while ((line=in.readLine()) != null){
                        result+=line + "\n";
                    }
                }else {
                    result="{\"Error\":\"NO data\"}";
                }
            }else {
                result ="{\"Error\":\"DB connection fail check your net\"}";
            }
            inputStream.close();
            connection.disconnect();
        }catch (IOException e){
            Log.e("DB connection", e.toString() );
            result = "{\"Error\":\"DB connection fail\"}";
        }

    }

}



