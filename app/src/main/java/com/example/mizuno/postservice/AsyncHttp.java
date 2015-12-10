package com.example.mizuno.postservice;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mizuno on 2015/11/05.
 */
public class AsyncHttp extends AsyncTask<String, Integer, Boolean> {

    //HttpURLConnectionを利用したPOSTプログラム
    HttpURLConnection urlConnection = null; //HTTPコネクション管理用
    Boolean flg = false;

    String name;
    double value;

    public AsyncHttp(String name, double value){
        this.name = name;
        this.value = value;
    }

    //非同期処理ここから
    @Override
    protected Boolean doInBackground(String... contents) {
        //URLの設定（AndroidからホストPCのローカルに接続するには10.0.2.2を利用する
        String urlinput = "http://j11000.sangi01.net/cakephp/scores/post";
        try {
            //HttpURLConnectionの利用手順
           /*
           1.url.openConnection()を呼び出し接続開始
           取得できる型はURLConnection型なので、キャストする必要あり
           2.ヘッダーの設定
           3.bodyを設定する場合はHttpURLConnection.setDoOutputにbodyが存在することを明示
           4.connect()で接続を確立する
           5.レスポンスをgetInputStream()で取得する
           * */
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST用パラメータ
            String postDataSample = "name="+name+"&score="+value;

            //POSTパラメータ設定
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();


            //レスポンスを受け取る
            //InputStream is = urlConnection.getInputStream();
            urlConnection.getInputStream();

            flg = true;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

        return flg;
    }

}
