package com.lizhenbo.planewar;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.lizhenbo.view.GameView;

public class SetActivity extends Activity {

    private ImageView imageview_rank_bg;

    CheckBox checkBox;
    SeekBar seekBar;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    AudioManager audioManager;    //多媒体控制服务


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        imageview_rank_bg = (ImageView) findViewById(R.id.imageview_set_bg);
        imageview_rank_bg.setImageBitmap(SplashActivity.bitmap_background);

        //获得组件
        checkBox=(CheckBox)findViewById(R.id.checkbox_set_volume);
        seekBar=(SeekBar)findViewById(R.id.seekbar_set_volume);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup_set_gamelevl);
        radioButton1=(RadioButton)findViewById(R.id.radio0_set_gamelevl);
        radioButton2=(RadioButton)findViewById(R.id.radio1_set_gamelevl);
        radioButton3=(RadioButton)findViewById(R.id.radio2_set_gamelevl);

        if(GameView.gamelevel==50){

            radioButton1.setChecked(true);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
        }else if(GameView.gamelevel==35){
            radioButton1.setChecked(false);
            radioButton2.setChecked(true);
            radioButton3.setChecked(false);
        }else if (GameView.gamelevel==20) {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(true);
        }

        //获得音量控制服务
        //获得多媒体控制服务
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
        //获得当前音量
        int volCurrent=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //获得最大音量
        int volMax=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //根据获得的音量，设置seekbar的属性
        //根据获得的音量，设置seekbar的属性
        seekBar.setMax(volMax);
        seekBar.setProgress(volCurrent);
        //如果音量>0，那么我们选中check打开音乐，否则不选中check关闭音乐
        if(volCurrent==0){
            checkBox.setChecked(false);
        }else{
            checkBox.setChecked(true);
        }
        //给check设置监听
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //check没被选中，音乐关闭
                if(isChecked==false){
                    //进度条归0
                    seekBar.setProgress(0);
                    //音量同时减到0
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FX_FOCUS_NAVIGATION_DOWN);

                }
                //check被选中，音乐打开
                if(isChecked){
                    //进度条到一个默认值7
                    seekBar.setProgress(7);
                    //音量设置到7
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 7, AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio0_set_gamelevl){
                    GameView.gamelevel=50;
                    radioButton1.setChecked(true);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                }else if(checkedId==R.id.radio1_set_gamelevl){
                    GameView.gamelevel=35;
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(true);
                    radioButton3.setChecked(false);
                }else if (checkedId==R.id.radio2_set_gamelevl) {
                    GameView.gamelevel=20;
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                }
            }
        });
        //给进度条设置拖动监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //得到进度条的值
                int progress=seekBar.getProgress();
                //通过AudioManager来设置音量
                /**
                 * 第3个参数：是用于判断是非显示音量控制toast。left和up为显示  right和down为不显示。
                 * */
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                //如果音量=0，那么我们音乐打开，否则音乐关闭
                if(progress==0){
                    checkBox.setChecked(false);
                }else{
                    checkBox.setChecked(true);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });


    }
}
