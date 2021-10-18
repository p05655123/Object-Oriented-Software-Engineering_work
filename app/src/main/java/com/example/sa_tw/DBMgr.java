package com.example.sa_tw;
import android.app.Activity;

import com.example.sa_tw.Mediator.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class DBMgr {
    //這裡有Singleton Pattern
    private Mediator mediator;
    private static DBMgr instance;
    public static DBMgr getInstance(){
        if(instance == null) {
            instance = new DBMgr();
        }
        return instance;
    }
    private DBconnection dBconnection;
    private String db_result="";
    private Activity activity;
    private DBMgr(){}
    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }
    private void reorganize_dbconnection(){
        dBconnection=new DBconnection();
    }

    /**SQL方法**/
    public void login(String acc,String pwd){
        reorganize_dbconnection();
        String url = "login.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8")).append("&");
            stringBuilder.append("password=").append(URLEncoder.encode(pwd,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void login_success(String acc){
        reorganize_dbconnection();
        String url = "get_user_info.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void load_refrigerator(String size, String place){
        reorganize_dbconnection();
        String url = "get_refrigerator.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("size=").append(URLEncoder.encode(size,"UTF-8")).append("&");
            stringBuilder.append("place=").append(URLEncoder.encode(place,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }

    public void update_wallet(String acc,int balance_int){
        reorganize_dbconnection();
        String balance = String.valueOf(balance_int);
        String url = "update_wallet.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8")).append("&");
            stringBuilder.append("balance=").append(URLEncoder.encode(balance,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void update_wallet2(String acc,int balance_int){
        reorganize_dbconnection();
        String balance = String.valueOf(balance_int);
        String url = "update_wallet2.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8")).append("&");
            stringBuilder.append("balance=").append(URLEncoder.encode(balance,"UTF-8")).append("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void intoorder(String id , String sid , String place,int days){
        reorganize_dbconnection();
        String url = "intoorder.php";
        String day = String.valueOf(days);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("name_id=").append(URLEncoder.encode(id,"UTF-8")).append("&");
            stringBuilder.append("size_id=").append(URLEncoder.encode(sid,"UTF-8")).append("&");
            stringBuilder.append("place=").append(URLEncoder.encode(place,"UTF-8")).append("&");
            stringBuilder.append("countdown=").append(URLEncoder.encode(String.valueOf(day),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void update_refrigerator(String n , String place ,String rent){
        reorganize_dbconnection();
        String url = "update_refrigerator.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("size_id=").append(URLEncoder.encode(n,"UTF-8")).append("&");
            stringBuilder.append("place=").append(URLEncoder.encode(place,"UTF-8")).append("&");
            stringBuilder.append("rent_or_not=").append(URLEncoder.encode(rent,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void load_record(String acc){
        reorganize_dbconnection();
        String url = "get_record.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("user_id=").append(URLEncoder.encode(acc,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void check_register_data(String acc,String email){
        reorganize_dbconnection();
        String url = "check_register_data.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("email=").append(URLEncoder.encode(email,"UTF-8")).append("&");
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());

    }
    public void creat_account(String name,String email,String acc,String pwd){
        reorganize_dbconnection();
        String url = "create_account.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("name=").append(URLEncoder.encode(name,"UTF-8")).append("&");
            stringBuilder.append("email=").append(URLEncoder.encode(email,"UTF-8")).append("&");
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8")).append("&");
            stringBuilder.append("password=").append(URLEncoder.encode(pwd,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    public void reload_balance(String acc){
        reorganize_dbconnection();
        String url = "get_balance.php";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("account=").append(URLEncoder.encode(acc,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dBconnection.execute(url,stringBuilder.toString());
    }
    /**------------**/
    public void setActivity(Activity activity){
        this.activity=activity;
    }
    public Activity getActivity(){
        return activity;
    }
    public void set_result(String result){//接收DB傳回來的資訊
        this.db_result=result;
        try {
            mediator.db_load_finish(new JSONObject(result));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}
