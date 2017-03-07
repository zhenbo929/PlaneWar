package com.lizhenbo.planewar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.InputStream;

public class SplashActivity extends Activity {
    public static Bitmap bitmap_res;// 图片资源
    public static Bitmap bitmap_background;// 背景图片
    public static Bitmap bitmap_bomb;// 炸弹按钮
    public static Bitmap bitmap_bullet;// 子弹
    public static Bitmap bitmap_bullet_double;// 双倍子弹
    public static Bitmap[] bitmap_enemy1_blowup;// 4小敌机
    public static Bitmap bitmap_enemy1_fly;//
    public static Bitmap[] bitmap_enemy2_blowup;// 7大敌机
    public static Bitmap[] bitmap_enemy2_fly;// 2
    public static Bitmap bitmap_enemy2_hit;//
    public static Bitmap[] bitmap_enemy3_blowup;// 4中敌机
    public static Bitmap bitmap_enemy3_fly;//
    public static Bitmap bitmap_enemy3_hit;//
    public static Bitmap[] bitmap_hero_blowup;// 4战机爆炸
    public static Bitmap[] bitmap_hero_fly;// 2战机
    public static Bitmap bitmap_enemy4_fly;// 炸弹（飞行）
    public static Bitmap bitmap_enemy5_fly;// 双倍子弹
    public static Bitmap bitmap_game_pause;// 2暂停键
    public static int WIDTH;// 屏幕宽度
    public static int HEIGHT;// 屏幕高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 获得屏幕尺寸
        getScreenSize();
        // 加载所有图片资源
        getBitmap();
        // 初始化ui
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {

        ImageView imageview_splash_bg = (ImageView) findViewById(R.id.imageview_splash_bg);
        ImageView imageview_splash_bossplane = (ImageView) findViewById(R.id.imageview_splash_bossplane);
        ImageView imageview_splash_bigphlane = (ImageView) findViewById(R.id.imageview_splash_bigphlane);
        ImageView imageview_splash_tinyplane_1 = (ImageView) findViewById(R.id.imageview_splash_tinyplane_1);
        ImageView imageview_splash_tinyplane_2 = (ImageView) findViewById(R.id.imageview_splash_tinyplane_2);
        ImageView imageview_splash_hero = (ImageView) findViewById(R.id.imageview_splash_hero);
        ImageView imageview_splash_logo = (ImageView) findViewById(R.id.imageview_splash_logo);
        imageview_splash_bg.setImageBitmap(bitmap_background);
        imageview_splash_bossplane.setImageBitmap(bitmap_enemy2_fly[0]);
        imageview_splash_bigphlane.setImageBitmap(bitmap_enemy3_fly);
        imageview_splash_tinyplane_1.setImageBitmap(bitmap_enemy1_fly);
        imageview_splash_tinyplane_2.setImageBitmap(bitmap_enemy1_fly);
        imageview_splash_hero.setImageBitmap(bitmap_hero_fly[0]);
        // 加载设置和播放logo的动画

        Animation animation_splash_logo = AnimationUtils.loadAnimation(this,
                R.anim.animation_splash_01);
        imageview_splash_logo.setAnimation(animation_splash_logo);
        animation_splash_logo.start();
        // 为logo动画播放设置监听，播放完毕后跳转到菜单页面，并且finish该页面
        //animation_splash_logo.setAnimationListener();
        animation_splash_logo.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            // 播放完毕后跳转到菜单页面，并且finish该页面
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,
                        MenuActivity.class));
                finish();
            }
        });
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
    }

    /**
     * 获取需要的资源图片
     */
    private void getBitmap() {
        android.graphics.Matrix matrix_rotate_2 = new android.graphics.Matrix();// 逆时针90（部分位图需要做旋转调整）
        matrix_rotate_2.setRotate(-90);
        // 从assets文件夹中获取总资源图
        bitmap_res = getBitampFromAssetsFile(this, "game_arts.png");
        // 再依次从总资源图中扣出各个资源图片
        bitmap_background = scaleXY(Bitmap.createBitmap(bitmap_res, 0, 0, 640,
                1136))[0];// 背景图片
        bitmap_bomb = scaleXY(Bitmap.createBitmap(bitmap_res, 951, 1727, 70,
                82, matrix_rotate_2, false))[0];
        bitmap_bullet = scaleXY(Bitmap
                .createBitmap(bitmap_res, 1009, 0, 12, 28))[0];
        bitmap_bullet_double = scaleXY(Bitmap.createBitmap(bitmap_res, 981, 0,
                28, 12, matrix_rotate_2, false))[0];
        bitmap_enemy1_blowup = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 102, 1622, 68, 50),
                Bitmap.createBitmap(bitmap_res, 218, 1546, 68, 62),
                Bitmap.createBitmap(bitmap_res, 951, 1809, 68, 76),
                Bitmap.createBitmap(bitmap_res, 286, 1596, 64, 60) });
        bitmap_enemy1_fly = scaleXY(Bitmap.createBitmap(bitmap_res, 218, 1608,
                68, 50))[0];
        bitmap_enemy2_blowup = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 640, 221, 341, 220,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 640, 881, 341, 220,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 640, 661, 341, 220,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 640, 441, 341, 220,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 640, 0, 341, 221,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 0, 1355, 269, 191,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 599, 1320, 269, 191,
                        matrix_rotate_2, false) });
        bitmap_enemy2_fly = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 332, 1136, 219, 328),
                Bitmap.createBitmap(bitmap_res, 0, 1136, 332, 219,
                        matrix_rotate_2, false) });
        bitmap_enemy2_hit = scaleXY(Bitmap.createBitmap(bitmap_res, 640, 1101,
                340, 219, matrix_rotate_2, false))[0];
        bitmap_enemy3_blowup = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 737, 1603, 120, 92,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 737, 1511, 120, 92,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 481, 1588, 92, 122),
                Bitmap.createBitmap(bitmap_res, 849, 1727, 102, 92,
                        matrix_rotate_2, false) });
        bitmap_enemy3_fly = scaleXY(Bitmap.createBitmap(bitmap_res, 733, 1695,
                116, 92, matrix_rotate_2, false))[0];
        bitmap_enemy3_hit = scaleXY(Bitmap.createBitmap(bitmap_res, 481, 1464,
                92, 124))[0];
        bitmap_hero_blowup = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 317, 1464, 164, 132,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 573, 1511, 164, 132,
                        matrix_rotate_2, false),
                Bitmap.createBitmap(bitmap_res, 868, 1320, 132, 164),
                Bitmap.createBitmap(bitmap_res, 0, 1546, 102, 88,
                        matrix_rotate_2, false) });
        bitmap_hero_fly = scaleXY(new Bitmap[] {
                Bitmap.createBitmap(bitmap_res, 868, 1484, 132, 164),
                Bitmap.createBitmap(bitmap_res, 573, 1643, 160, 132,
                        matrix_rotate_2, false) });
        bitmap_enemy4_fly = scaleXY(Bitmap.createBitmap(bitmap_res, 857, 1648,
                137, 79, matrix_rotate_2, false))[0];
        bitmap_enemy5_fly = scaleXY(Bitmap.createBitmap(bitmap_res, 102, 1546,
                116, 76, matrix_rotate_2, false))[0];
        bitmap_game_pause = scaleXY(
                Bitmap.createBitmap(bitmap_res, 0, 1634, 58, 56,
                        matrix_rotate_2, false))[0];

    }

    /**
     * 进行屏幕适配，对资源图片进行缩放
     *
     * @param bitmap
     *            原图片（可变长参数列表）
     * @return 缩放后的图片
     */
    private Bitmap[] scaleXY(Bitmap... bitmap) {
        // 准备一个bitmap数组
        Bitmap[] bitmaps = new Bitmap[bitmap.length];
        // 计算缩放的比例
        float scale_x = (float) (SplashActivity.WIDTH / 640.0);
        float scale_y = (float) (SplashActivity.HEIGHT / 1130.0);
        Matrix matrix_rotate_1 = new Matrix();// 缩放
        matrix_rotate_1.setScale(scale_x, scale_y);
        // 对图片进行缩放
        for (int i = 0; i < bitmap.length; i++) {
            bitmaps[i] = Bitmap.createBitmap(bitmap[i], 0, 0,
                    bitmap[i].getWidth(), bitmap[i].getHeight(),
                    matrix_rotate_1, false);
        }
        // 返回得到的bitmap数组
        return bitmaps;

    }

    /**
     * 加载asssets文件夹下的所有图片资源
     *
     * @param context
     *            上下文
     * @param filename
     *            要读取的文件名
     * @return 以bitmap返回读取的文件
     */
    private Bitmap getBitampFromAssetsFile(Context context, String filename) {
        Bitmap image = null;
        try {
            // 获得AssetManager
            AssetManager am = context.getAssets();
            // 打开读取文件的读取流
            InputStream is = am.open(filename);
            // 把流转换为bitmap位图
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
        }
        // 返回读取的bitmap位图
        return image;
    }
}
