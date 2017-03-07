package com.lizhenbo.planewar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends Activity {

    private ImageView imageview_rank_bg;
    private ImageView bossplane;
    private ImageView bigplane;
    private ImageView tinyplane;
    private ImageView heroplane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageview_rank_bg = (ImageView) findViewById(R.id.imageview_about_bg);
        imageview_rank_bg.setImageBitmap(SplashActivity.bitmap_background);
        tinyplane = (ImageView) findViewById(R.id.imageview_about_tinyplane);
        bigplane = (ImageView) findViewById(R.id.imageview_about_bigplane);
        bossplane = (ImageView) findViewById(R.id.imageview_about_bossplane);
        heroplane = (ImageView) findViewById(R.id.imageview_about_heroplane);


        tinyplane.setImageBitmap(SplashActivity.bitmap_enemy1_fly);
        bigplane.setImageBitmap(SplashActivity.bitmap_enemy3_fly);
        bossplane.setImageBitmap(SplashActivity.bitmap_enemy2_fly[0]);
        heroplane.setImageBitmap(SplashActivity.bitmap_hero_fly[0]);


    }
}
