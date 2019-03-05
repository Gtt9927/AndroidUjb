package com.example.androidujb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView.findViewById(R.id.image_view);
        Animation animation = AnimationUtils.loadAnimation( this,R.anim.welcome_alpha);
        imageView.startAnimation(animation);
    }

    public class CheckVersionInfoTask extends AsyncTask<Void,Void,String>{

        private  Context context;
        private ProgressBar progressBar;
        private HttpURLConnection connection;

        private CheckVersionInfoTask(Context context) {
            this.context = context;
            progressBar = new ProgressBar(this.context);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                String path = "http://api.teq6.com/dz/app/device";
                URL url = null;
                url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void  onPostExecute(String s){
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled(){
            progressBar.setVisibility(View.GONE);
        }
    }


}
