package in.eventalk.eventalk;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RestOperation extends AsyncTask<String, Void, String>{

    String content;
    String error;
    public String response = "";
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        String Url = strings[0].toString();
//        switch (strings[1].toString()){
//            case "1": response= getFBData(url);break;

        try {
            URL url = new URL(Url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line).append("/n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }finally {
                urlConnection.disconnect();
            }

        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }





}
