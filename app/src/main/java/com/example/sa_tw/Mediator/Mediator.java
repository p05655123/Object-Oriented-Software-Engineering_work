package com.example.sa_tw.Mediator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sa_tw.Command.*;
import com.example.sa_tw.Factory.BackIMG1;
import com.example.sa_tw.Home;
import com.example.sa_tw.Introduction;
import com.example.sa_tw.INFO;
import com.example.sa_tw.MainActivity;
import com.example.sa_tw.Deposit;
import com.example.sa_tw.OTP.*;
import com.example.sa_tw.OTPinput;
import com.example.sa_tw.Refrigerator.Rent.Big;
import com.example.sa_tw.Refrigerator.Rent.Middle;
import com.example.sa_tw.Refrigerator.Rent.Rent;
import com.example.sa_tw.Refrigerator.Open.Open;
import com.example.sa_tw.Refrigerator.Refrigerator;
import com.example.sa_tw.Refrigerator.MyRefrigerator.MyRefrigerator;
import com.example.sa_tw.Refrigerator.Rent.Small;
import com.example.sa_tw.SignUp_GUI;
import com.example.sa_tw.User.*;
import com.example.sa_tw.DBMgr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mediator<Sting> {
    private Deposit deposit_GUI;
    private INFO info_GUI;
    private SignUp_GUI signUp_GUI;
    private static Mediator mediator = new Mediator();
    private DBMgr dbMgr = DBMgr.getInstance();
    private MainActivity mainActivity;
    private Home home_GUI;
    private OTPinput otp_GUI ;
    private OTP otp ;
    private Mailsever mailsever=new Mailsever();
    private Person person;
    private Wallet wallet;
    protected Command command;
    private String temp_acc;
    private Refrigerator refrigerator_GUI;
    private GetRecord getrecord;
    private Rent rent_GUI;
    private Big big_GUI;
    private Middle middle_GUI;
    private Small small_GUI;
    private BackIMG1 backIMG1;
    private Open open_GUI;
    private MyRefrigerator myRefrigerator_GUI;
    String place1;
    String size1;
    double cost;
    int days;
    ArrayList<String> size_id = new ArrayList<String>();
    ArrayList<String> size = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<String> rent_or_not = new ArrayList<String>();

    ArrayList<String> r_size_id = new ArrayList<String>();
    ArrayList<String> r_place = new ArrayList<String>();
    ArrayList<String> r_date = new ArrayList<String>();
    ArrayList<String> r_countdown = new ArrayList<String>();
    JSONObject temp2;


    public static Mediator getInstance(){
        return mediator;
    }
    public void setup(){
        dbMgr.setMediator(this);
        person=Person.getInstance();
        wallet=Wallet.getInstance();
        getrecord=GetRecord.getInstance();
        otp_GUI = OTPinput.getInstance();
        otp = OTP.getInstance();
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        dbMgr.setActivity(mainActivity);
    }

    public void setOtp_GUI(OTPinput otp_GUI){
        this.otp_GUI=otp_GUI;
    }
    public void setDeposit_GUI(Deposit deposit_GUI){
        this.deposit_GUI=deposit_GUI;
    }
    public void setInfo_GUI(INFO info_GUI){
        this.info_GUI=info_GUI;
    }
    public void setSignUp_GUI(SignUp_GUI signUp_GUI){
        this.signUp_GUI=signUp_GUI;
    }
    public void setRefrigerator_GUI(Refrigerator refrigerator_GUI){
        this.refrigerator_GUI = refrigerator_GUI;
    }
    public void setRent_GUI(Rent rent_GUI){
        this.rent_GUI = rent_GUI;
    }
    public void setOpen_GUI(Open open_GUI){
        this.open_GUI = open_GUI;
    }
    public void setBig_GUI(Big big_GUI){
        this.big_GUI = big_GUI;
    }
    public void setMiddle_GUI(Middle middle_GUI) { this.middle_GUI = middle_GUI; }
    public void setSmall_GUI(Small small_GUI) { this.small_GUI = small_GUI;}
    public void setMyRefrigerator_GUI(MyRefrigerator myRefrigerator_GUI){
        this.myRefrigerator_GUI = myRefrigerator_GUI;
    }
    public void setHome_GUI(Home home_GUI) {
        this.home_GUI = home_GUI;
    }

    public void db_load_finish(JSONObject db_result) {
        command.execute(db_result);
    }

    public void make_toast(String msg){
        Toast.makeText(mainActivity,msg,Toast.LENGTH_SHORT).show();
    }
    public void setDbMgr_activity(Activity activity){
        dbMgr.setActivity(activity);
    }
    public String get_email(){
        return person.getEmail();
    }

    /** Deposit **/
    public void update_wallet(){
        dbMgr.update_wallet(person.getAcc(),wallet.getBalance());
    }
    public void depoist(String money){
        command = new Do_deposit(this);
        int temp = Integer.parseInt(money) ;
        wallet.deposit(temp);
        deposit_GUI.finish();
    }
    /**-------**/

    /** Withdraw **/
    public void update_wallet2(){
        dbMgr.update_wallet2(person.getAcc(),wallet.getBalance());
    }
    public void withdraw(int money){
        command = new Do_withdraw(this);
        int temp = money ;
        wallet.withdraw(temp);
    }
    /**-------**/

    /**OTP**/
    public void creat_OTP(String place , String size_id , int days , double cost){
        this.place1 = place;
        this.size1 = size_id;
        this.cost = cost;
        this.days = days;
        otp.creat_password();
    }
    public void send_OTP(String otp){
        mailsever.sendEmail(person.getEmail(),otp);
    }
    public void check_OTP(String enter_otp){
        otp.check_Password(enter_otp);
    }
    public void OTP_checked(){
        ConfirmOrder();
        update_refrigerator();
        withdraw((int) cost);

        otp_GUI.finish();
        show_open_GUI();
    }
    public void update_refrigerator(){
        dbMgr.update_refrigerator(size1,place1,"1");
    }
    public void ConfirmOrder(){
        command = new Get_item(this);
        dbMgr.intoorder(person.getName_id(), size1, place1,days);
    }
    /**-------**/

    /**Login**/
    public void login(String acc,String pwd){
        command=new Login(this);
        dbMgr.login(acc,pwd);
        temp_acc=acc;

    }
    public void login_success(){
        command= new Login_success(this);
        dbMgr.login_success(temp_acc);
    }
    public void setuserInfo(String name_id,String name,String email,int balance){
        person.setAcc(temp_acc);
        person.setEmail(email);
        person.setName(name);
        person.setName_id(name_id);
        wallet.setBalance(balance);

    }

    /**Refrigerator**/
    public void load_refrigerator(String size , String place) throws JSONException {
        command = new Load_refrigerator(this);
        dbMgr.load_refrigerator(size , place);
        setRefrigerator(null);
    }

    public void setRefrigerator(JSONArray refrigerator) throws JSONException{
        if(refrigerator != null){
            size_id.clear();
            size.clear();
            place.clear();
            rent_or_not.clear();
            for(int i = 0 ; i < refrigerator.length() ; i++){
                temp2 = refrigerator.getJSONObject(i);
                String a1 = temp2.getString("SIZE_ID");
                String b1 = temp2.getString("SIZE");
                String c1 = temp2.getString("PLACE");
                String d1 = temp2.getString("RENT_OR_NOT");
                size_id.add(a1);
                size.add(b1);
                place.add(c1);
                rent_or_not.add(d1);
            }
            rent_GUI.setSize_id(size_id);
            rent_GUI.setSize(size);
            rent_GUI.setPlace(place);
            rent_GUI.setRent_or_not(rent_or_not);
            if(size.get(0).equals("Big")){
                rent_GUI.B_Rent_Or_Not(rent_or_not , size_id);
            }else if(size.get(0).equals("Middle")){
                rent_GUI.M_Rent_Or_Not(rent_or_not , size_id);
            }else if(size.get(0).equals("Small")){
                rent_GUI.S_Rent_Or_Not(rent_or_not , size_id);
            }
        }else{
            rent_GUI.setSize_id(null);
            rent_GUI.setSize(null);
            rent_GUI.setPlace(null);
            rent_GUI.setRent_or_not(null);
        }
    }

    /**MyRefrigerator Record**/
    public void load_record() throws JSONException {
        command=new Load_record(this);
        dbMgr.load_record(person.getName_id());
        set_recodlistGUI_records(null);
    }

    public void set_recodlistGUI_records(JSONArray records) throws JSONException {
        if(records != null){
            r_size_id.clear();
            r_place.clear();
            r_date.clear();
            r_countdown.clear();
            for(int i = 0 ; i < records.length() ; i++){
                temp2 = records.getJSONObject(i);
                String a1 = temp2.getString("SIZE_ID");
                String b1 = temp2.getString("PLACE");
                String c1 = temp2.getString("DATE");
                String d1 = temp2.getString("COUNTDOWN");
                r_size_id.add(a1);
                r_place.add(b1);
                Log.e("XD","e04：" + r_place);
                r_date.add(c1);
                r_countdown.add(d1);
            }
            getrecord.setSize_id(r_size_id);
            getrecord.setPlace(r_place);
            getrecord.setDate(r_date);
            getrecord.setCountdown(r_countdown);
        }else{
            getrecord.setSize_id(null);
            getrecord.setPlace(null);
            getrecord.setDate(null);
            getrecord.setCountdown(null);
        }
    }
    public void output_item_All(){
        myRefrigerator_GUI.set_size_id(getrecord.getSize_id());
        //Log.e("XD","XDDD：" + getrecord.getItem_All());
    }
    public void output_Address(){
        myRefrigerator_GUI.set_place(getrecord.getPlace());
    }
    public void output_State(){
        myRefrigerator_GUI.set_date(getrecord.getDate());
    }
    public void output_Date(){
        myRefrigerator_GUI.set_countdown(getrecord.getCountdown());
    }
    /**-------**/

    /**Info**/
    public void reload_balance(){
        command = new reload_balance(this);
        dbMgr.reload_balance(person.getAcc());
    }
    public void reset_balance(int balance){
        wallet.setBalance(balance);
    }
    public void output_acc(){
        info_GUI.set_acc(person.getAcc());
    }
    public void output_name(){
        info_GUI.set_name(person.getName());
    }
    public void output_email(){
        info_GUI.set_email(person.getEmail());
    }
    public void output_balance(){
        command=new Login(this);
        info_GUI.set_balance(wallet.getBalance());
    }
    /**-------**/

    /**SignUp**/
    public void check_signup_data(String acc,String email){
        command = new canregister(this);
        dbMgr.check_register_data(acc,email);
    }
    public void register(){
        command=new Creat_account(this);
        dbMgr.creat_account(signUp_GUI.get_name(),signUp_GUI.get_email(),signUp_GUI.get_acc(),signUp_GUI.get_pwd());
        signUp_GUI.finish();

    }
    /**-------**/

    /**-------**/

    /**Show GUI**/
    public void show_home_GUI(){
        Intent intent = new Intent();
        intent.setClass(mainActivity , Home.class);
        mainActivity.startActivity(intent);
    }
    public void open_show_home_GUI(){
        Intent intent = new Intent();
        intent.setClass(open_GUI , Home.class);
        open_GUI.startActivity(intent);
    }
    public void show_rent_GUI(){
        Intent intent = new Intent();
        intent.setClass(refrigerator_GUI , Rent.class);
        refrigerator_GUI.startActivity(intent);
    }
    public void show_big_GUI(String p ,String id){
        Intent intent = new Intent();
        intent.setClass(rent_GUI , Big.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",p);
        bundle.putString("id",id);
        intent.putExtras(bundle);
        rent_GUI.startActivity(intent);
    }
    public void show_middle_GUI(String p,String id){
        Intent intent = new Intent();
        intent.setClass(rent_GUI , Middle.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",p);
        bundle.putString("id",id);
        intent.putExtras(bundle);
        rent_GUI.startActivity(intent);
    }
    public void show_small_GUI(String p,String id){
        Intent intent = new Intent();
        intent.setClass(rent_GUI , Small.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",p);
        bundle.putString("id",id);
        intent.putExtras(bundle);
        rent_GUI.startActivity(intent);
    }
    public void show_open_GUI(){
        Intent intent = new Intent();
        intent.setClass(refrigerator_GUI, Open.class);
        refrigerator_GUI.startActivity(intent);
    }
    public void show_myrefrigerator_GUI(){
        Intent intent = new Intent();
        intent.setClass(refrigerator_GUI, MyRefrigerator.class);
        refrigerator_GUI.startActivity(intent);
    }
    public void show_deposit_GUI() {
        Intent intent = new Intent();
        intent.setClass(home_GUI, Deposit.class);
        home_GUI.startActivity(intent);
    }
    public void show_Refrigerator_GUI(){
        Intent intent = new Intent();
        intent.setClass(home_GUI , Refrigerator.class);
        home_GUI.startActivity(intent);
    }

    public void B_show_otp_GUI(){
        Intent intent = new Intent();
        intent.setClass(big_GUI , OTPinput.class);
        big_GUI.startActivity(intent);
    }
    public void M_show_otp_GUI(){
        Intent intent = new Intent();
        intent.setClass(middle_GUI , OTPinput.class);
        middle_GUI.startActivity(intent);
    }
    public void S_show_otp_GUI(){
        Intent intent = new Intent();
        intent.setClass(small_GUI , OTPinput.class);
        small_GUI.startActivity(intent);
    }
    public void show_info_GUI(){
        Intent intent = new Intent();
        intent.setClass(home_GUI, INFO.class);
        home_GUI.startActivity(intent);
    }
    public void show_signup_GUI(){
        Intent intent = new Intent();
        intent.setClass(mainActivity, SignUp_GUI.class);
        mainActivity.startActivity(intent);
    }
    public void show_introduction_GUI(){
        Intent intent = new Intent();
        intent.setClass(home_GUI , Introduction.class);
        home_GUI.startActivity(intent);
    }

}
