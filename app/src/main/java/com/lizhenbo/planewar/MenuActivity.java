package com.lizhenbo.planewar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends Activity {

    private Button btn_start;
    private Button btn_rank;
    private Button btn_set;
    private Button btn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 初始化UI
        initiUI();
        // 给菜单按钮设置监听
        setListener();
    }

    /**
     * 给菜单按钮设置监听
     */
    private void setListener() {
        // 设置监听
        btn_start.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, GameActivity.class));


            }
        });
        btn_rank.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, RankActivity.class));
            }
        });
        btn_set.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SetActivity.class));
            }
        });
        btn_about.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, AboutActivity.class));
            }
        });
    }

    /**
     * 初始化UI
     */
    private void initiUI() {
        // 找到控件
        ImageView imageview_menu_bg = (ImageView) findViewById(R.id.imageview_menu_bg);
        ImageView imageview_menu_hero = (ImageView) findViewById(R.id.imageview_menu_hero);
        ImageView imageview_menu_logo = (ImageView) findViewById(R.id.imageview_menu_logo);
        btn_start = (Button) findViewById(R.id.btn_menu_start);
        btn_rank = (Button) findViewById(R.id.btn_menu_rank);
        btn_set = (Button) findViewById(R.id.btn_menu_set);
        btn_about = (Button) findViewById(R.id.btn_menu_about);

        // 设置图片
        imageview_menu_bg.setImageBitmap(SplashActivity.bitmap_background);
        imageview_menu_hero.setImageBitmap(SplashActivity.bitmap_hero_fly[0]);
        // 加载动画
        Animation animation_menu_hero = AnimationUtils.loadAnimation(this,
                R.anim.animation_hero_01);// 英雄播放的动画
        Animation animation_menu_logo = AnimationUtils.loadAnimation(this,
                R.anim.animation_logo_01);// logo播放的动画
        Animation animation_menu_btn_1 = AnimationUtils.loadAnimation(this,
                R.anim.animation_btn_01);// btn播放的动画_1
        Animation animation_menu_btn_2 = AnimationUtils.loadAnimation(this,
                R.anim.animation_btn_02);// btn播放的动画_2
        animation_menu_hero.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // 设置动画
        imageview_menu_hero.setAnimation(animation_menu_hero);
        imageview_menu_logo.setAnimation(animation_menu_logo);
        btn_start.setAnimation(animation_menu_btn_1);
        btn_set.setAnimation(animation_menu_btn_1);
        btn_rank.setAnimation(animation_menu_btn_2);
        btn_about.setAnimation(animation_menu_btn_2);
        // 播放动画
        animation_menu_hero.start();
        animation_menu_logo.start();
        animation_menu_btn_1.start();
        animation_menu_btn_2.start();
    }


}
