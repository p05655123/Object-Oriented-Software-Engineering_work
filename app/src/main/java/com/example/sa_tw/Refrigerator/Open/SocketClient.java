package com.example.sa_tw.Refrigerator.Open;
import android.os.AsyncTask;

import java.net.InetSocketAddress;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class SocketClient extends AsyncTask<String,Float,String> {
    @Override
    protected String doInBackground(String... strings) {
        Socket client = new Socket();
        // 連線的ip(冰箱電腦的ip)
        String address = "140.125.216.121";
        // 連線的port
        int port = 8865;
        InetSocketAddress isa = new InetSocketAddress(address, port);
            try {
                client.connect(isa, 10000);
                BufferedOutputStream out = new BufferedOutputStream(client
                        .getOutputStream());
                // 送出字串
                out.write("The refrigerator is open.".getBytes());
                System.out.println("open msg is sent.");
                out.flush();
                out.close();
                client.close();
            } catch (java.io.IOException e) {
                System.out.println("連線有問題 !");
                System.out.println("IOException :" + e.toString());
            }
        return "YES";
    }
}
