package com.lizhenbo.plane;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lizhenbo.entity.FlyingObject;

/**
 * �����ɻ��ı�ը��3�ֵл���ս����
 */
public class ToDoom {
	private Bitmap[] booms = {};
	private int index;
	private int i;
	private Bitmap image_current;
	private int intevel = 10;//??
	private int x, y;

	public ToDoom(FlyingObject object) {
		booms = object.getImages_boom();
		image_current = object.getImage_current();
		x = object.getX();
		y = object.getY();
		index = 0;
		i = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Bitmap getImage_current() {
		return image_current;
	}

	/**
	 * ??
	 */
	public boolean burnDown() {
		i++;
		if (i % intevel == 0) {
			if (index == booms.length) {
				return true;
			}
			image_current = booms[index++];
		}
		return false;
	}

	/**
	 * ��ǰͼƬ����
	 */
	public void draw(Canvas canvas, Paint paint) {
		if (image_current != null) {
			canvas.drawBitmap(image_current, x, y, paint);
		}
	}

}
