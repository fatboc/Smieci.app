package com.example.smieci.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class slide1 extends AppCompatActivity {

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        CharSequence text = "Przesuń pasek, by zobaczyć różnicę";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        seekBar = (SeekBar) findViewById(R.id.seekBar1);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float pxHeight = displayMetrics.heightPixels;

        final float scale = context.getResources().getDisplayMetrics().density;

        int dpHeight = Math.round(pxHeight / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        if (dpHeight>400)
        {
            ImageView img1 = (ImageView) findViewById(R.id.image);
            ImageView img2 = (ImageView) findViewById(R.id.imageb);

            ViewGroup.LayoutParams lp1 = img1.getLayoutParams();
            ViewGroup.LayoutParams lp2 = img2.getLayoutParams();

            int pixels = (int) (350 * scale + 0.5f);

            lp1.height = pixels;
            lp2.height = pixels;

            img1.setLayoutParams(lp1);
            img2.setLayoutParams(lp2);

        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                FrameLayout target = (FrameLayout) findViewById(R.id.target);

                progress = progresValue;

                ViewGroup.LayoutParams lp = target.getLayoutParams();
                lp.height = progress;
                target.setLayoutParams(lp);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "http://smiecinawysokosci.pl");
                    startActivity(Intent.createChooser(shareIntent, "Podziel się używając:"));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
