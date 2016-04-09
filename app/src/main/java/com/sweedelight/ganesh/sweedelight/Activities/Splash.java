package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sweedelight.ganesh.sweedelight.BuildConfig;
import com.sweedelight.ganesh.sweedelight.R;

public class Splash extends AppCompatActivity {
    private ImageView imageView;
    private Animation animation;
    private Animation animation2;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        imageView= (ImageView)findViewById(R.id.imageView);
        animation= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splash_anim);
        animation2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splashfadeout);
        animation.setDuration(2000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation2);
                onStartup();
                finish();

                // Intent intent= new Intent(getBaseContext(), ImageSlider.class);
                //   startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    private void onStartup()
    {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        int count = prefs.getInt("launch_count", 0);
        prefs.edit().putInt("launch_count", count + 1).apply();
        prefs.edit().putInt("last_version", BuildConfig.VERSION_CODE).apply();
        showTutorial();



    }
    private void showTutorial()
    {
        Boolean firstRun = prefs.getBoolean("pref_first_run", true);

        if (firstRun)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("pref_first_run", false);
            //editor.putLong("last_hint_timestamp", DateHelper.getStartOfToday()).apply();
            editor.apply();

            Intent intent = new Intent(this, ImageSlider.class);
            this.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Location.class);
            this.startActivity(intent);
        }
    }
}
