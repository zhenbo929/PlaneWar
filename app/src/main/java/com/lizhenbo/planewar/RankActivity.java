package com.lizhenbo.planewar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.lizhenbo.adapter.RankAdapter;
import com.lizhenbo.sql.MySQLiteOpenHelper;

public class RankActivity extends Activity {

    private ListView listview_rank;
    private ImageView imageview_rank_bg;
    private RankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initiUI();
    }

    private void initiUI() {
        MySQLiteOpenHelper sqLiteOpenHelper=new MySQLiteOpenHelper(this);
        adapter=new RankAdapter(this,sqLiteOpenHelper.getNum10(),true);
        imageview_rank_bg = (ImageView) findViewById(R.id.imageview_rank_bg);
        listview_rank = (ListView) findViewById(R.id.listview_rank);
        imageview_rank_bg.setImageBitmap(SplashActivity.bitmap_background);
        listview_rank.setAdapter(adapter);
    }

}
