package com.lizhenbo.plane;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lizhenbo.planewar.SplashActivity;

/**
 * ��ͼʵ�ֵ�ͼ�ƶ�
 * @author anzhuo
 *
 */
public class Map {

	Bitmap bitmap;
	int x;
	float y;
	int width;
	int height;
	float speed=0.7f;
	public Map(int type) {
		this.bitmap = SplashActivity.bitmap_background;
		this.x = 0;
		this.y = -type*SplashActivity.HEIGHT;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * ��ǰͼƬ����
	 */
	public void draw(Canvas canvas, Paint paint) {
		if (bitmap != null) {
			canvas.drawBitmap(bitmap, x, y, paint);
		}
	}
	/**
	 * �ƶ�һ��
	 */
	public void step() {
		y += speed;
		if(outOfBounds()){
			y=-SplashActivity.HEIGHT;
		}
	}
	/**
	 * �Ƿ�Խ��
	 */
	public boolean outOfBounds() {
		return y > SplashActivity.HEIGHT;
	}

	
	
}
