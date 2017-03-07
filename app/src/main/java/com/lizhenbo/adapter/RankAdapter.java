package com.lizhenbo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lizhenbo.planewar.R;

public class RankAdapter extends CursorAdapter{

	public RankAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	public RankAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
	}

	@Override
	public int getCount() {

		return Math.min(10, super.getCount());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		return View.inflate(context, R.layout.inflate_item_rank, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ImageView imageView_rank = (ImageView) view.findViewById(R.id.imageview_rank);
		TextView textview_rank_num=(TextView) view.findViewById(R.id.textview_rank_num);
		TextView textView_rank_name = (TextView) view.findViewById(R.id.textview_rank_name);
		TextView textView_rank_score = (TextView) view.findViewById(R.id.textview_rank_score);
		switch (cursor.getPosition()) {
		case 0:
			textview_rank_num.setVisibility(View.INVISIBLE);
			imageView_rank.setVisibility(View.VISIBLE);
			imageView_rank.setImageResource(R.drawable.ranking_number_1);
			break;
		case 1:
			textview_rank_num.setVisibility(View.INVISIBLE);
			imageView_rank.setVisibility(View.VISIBLE);
			imageView_rank.setImageResource(R.drawable.ranking_number_2);
			break;
		case 2:
			textview_rank_num.setVisibility(View.INVISIBLE);
			imageView_rank.setVisibility(View.VISIBLE);
			imageView_rank.setImageResource(R.drawable.ranking_number_3);
			break;
		case 3:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("4");
			break;
		case 4:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("5");
			break;
		case 5:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("6");
			break;
		case 6:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("7");
			break;
		case 7:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("8");
			break;
		case 8:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("9");
			break;
		case 9:
			imageView_rank.setVisibility(View.INVISIBLE);
			textview_rank_num.setVisibility(View.VISIBLE);
			textview_rank_num.setText("10");
			break;


		}
		textView_rank_name.setText(cursor.getString(1));
		textView_rank_score.setText(cursor.getLong(2)+"");


	}

}
