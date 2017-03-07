package com.lizhenbo.planewar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lizhenbo.media.MediaPlayerSoundPoolDemo;
import com.lizhenbo.sql.MySQLiteOpenHelper;
import com.lizhenbo.view.GameView;
import com.lizhenbo.view.MyDialog;

public class GameActivity extends Activity {

    GameView gameView;
    private ImageButton imagebtn_game_pause;
    private static ImageButton imagebtn_game_bomb;
    private static TextView textview_game_score;
    private static TextView textview_game_bombnum;
    private static int bombnum = 0; //
    public static Handler handler;

    MyDialog dialog_pause;
    MyDialog dialog_over;

    MySQLiteOpenHelper sqLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // 初始化dialog
        initiDialog();
        // 初始化数据
        initiData();
        // 初始化UI
        initiUI();
        gameView.startGame();
        MediaPlayerSoundPoolDemo.playBGM(this, R.raw.bg_music);
    }

    /**
     * 初始化dialog
     */
    private void initiDialog() {

        dialog_pause = new MyDialog(this, R.style.mydialog_style);
        dialog_over = new MyDialog(this, R.style.mydialog_style);

    }

    /**
     * 初始化数据
     */
    private void initiData() {
        bombnum = 0;
        sqLiteOpenHelper = new MySQLiteOpenHelper(GameActivity.this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                if (what == 1) {
                    int score = (Integer) msg.obj;
                    textview_game_score.setText(":" + score);
                } else if (what == 2) {

                    Log.i("TAG",bombnum+"原来的");

                    bombnum++;
                    if (bombnum >= 1) {
                        imagebtn_game_bomb.setVisibility(View.VISIBLE);
                        textview_game_bombnum.setVisibility(View.VISIBLE);
                        textview_game_bombnum.setText("x" + bombnum);
                    }
                } else if (what == 3) {
                    // 游戏结束
                    final int score = (Integer) msg.obj;
                    dialog_over.show();
                    // dialog_over.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    // WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    MediaPlayerSoundPoolDemo.stopBGM();
                    dialog_over.setCancelable(false);
                    View view = View.inflate(GameActivity.this,
                            R.layout.inflate_dialog_gameover, null);
                    dialog_over.setContentView(view);

                    TextView textview_gameover_title = (TextView) view
                            .findViewById(R.id.textview_gameover_title);
                    final EditText edittext_gameover_name = (EditText) view
                            .findViewById(R.id.edittext_gameover_name);

                    Button btn_gameover_yes = (Button) view
                            .findViewById(R.id.btn_gameover_yes);
                    if (sqLiteOpenHelper.isInsert(score)) {
                        textview_gameover_title.setText("新纪录：" + score);

                        btn_gameover_yes
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String name = edittext_gameover_name
                                                .getText().toString().trim();
                                        // 插入到数据库中
                                        sqLiteOpenHelper.insert(name, score);
                                        dialog_over.dismiss();
                                        finish();
                                    }
                                });
                    } else {
                        textview_gameover_title.setText("分数：" + score);
                        edittext_gameover_name.setVisibility(View.GONE);
                        btn_gameover_yes
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog_over.dismiss();
                                        finish();
                                    }
                                });
                    }

                }
            }
        };
    }

    /**
     * 初始化UI
     */
    private void initiUI() {
        imagebtn_game_pause = (ImageButton) findViewById(R.id.imagebtn_game_pause);
        imagebtn_game_bomb = (ImageButton) findViewById(R.id.imagebtn_game_bomb);
        textview_game_score = (TextView) findViewById(R.id.textview_game_score);
        textview_game_bombnum = (TextView) findViewById(R.id.textview_game_bombnum);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "segoesc.ttf");
        textview_game_score.setTypeface(typeface);
        textview_game_bombnum.setTypeface(typeface);
        gameView = (GameView) findViewById(R.id.gameView1);
        imagebtn_game_pause.setImageBitmap(SplashActivity.bitmap_game_pause);
        imagebtn_game_bomb.setImageBitmap(SplashActivity.bitmap_bomb);
        imagebtn_game_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.pauseGame();
                MediaPlayerSoundPoolDemo.pauseBGM();
                // 弹出提示框
                dialog_pause.show();
                dialog_pause.setCancelable(false);
                View view = View.inflate(GameActivity.this,
                        R.layout.inflate_dialog_gamepause, null);
                dialog_pause.setContentView(view);

                Button btn_pause_continue = (Button) view
                        .findViewById(R.id.btn_pause_continue);
                Button btn_pause_backmenu = (Button) view
                        .findViewById(R.id.btn_pause_backmenu);
                btn_pause_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_pause.dismiss();
                        gameView.continueGame();
                        MediaPlayerSoundPoolDemo.continueBGM();
                        // MediaPlayerDemo.playBGM(context, resid)
                    }
                });

                btn_pause_backmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_pause.dismiss();
                        finish();

                        MediaPlayerSoundPoolDemo.stopBGM();
                    }
                });
            }
        });
        imagebtn_game_bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bombnum--;
                if (bombnum <= 0) {
                    textview_game_bombnum.setVisibility(View.GONE);
                    imagebtn_game_bomb.setVisibility(View.GONE);
                    bombnum = 0;				} else {
                    textview_game_bombnum.setText("x" + bombnum);
                }
                gameView.bombAll();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}
