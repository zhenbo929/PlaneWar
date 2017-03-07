package com.lizhenbo.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lizhenbo.plane.Bullet;

/**
 * ������ĳ����� �����������ɻ����зɻ���С�ɻ���ս�����ӵ���������˫��������ը����
 */
public abstract class FlyingObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Bitmap image_current;// ��ǰͼƬ
	protected Bitmap[] images_boom;// ��ըͼƬ

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
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

	public Bitmap getImage_current() {
		return image_current;
	}

	public void setImage_current(Bitmap image_current) {
		this.image_current = image_current;
	}

	public Bitmap[] getImages_boom() {
		return images_boom;
	}

	public void setImages_boom(Bitmap[] images_boom) {
		this.images_boom = images_boom;
	}

	/**
	 * ��ǰͼƬ����
	 */
	public void draw(Canvas canvas, Paint paint) {
		if (image_current != null) {
			canvas.drawBitmap(image_current, x, y, paint);
		}
	}

	/**
	 * ����Ƿ����
	 */
	public abstract boolean outOfBounds();

	/**
	 * �������ƶ�һ��
	 */
	public abstract void step();

	/**
	 * ��鵱ǰ���������Ƿ��ӵ�����
	 */
	public boolean shootBy(Bullet bullet) {
		if (bullet.isBomb()) {
			return false;
		}
		int x = bullet.x + bullet.width / 2; // �ӵ����������ĵ�
		int y = bullet.y; // �ӵ�������
		boolean shoot = this.x < x && x < this.x + width && this.y < y
				&& y < this.y + height;
		if (shoot) {
			bullet.setBomb(true);
		}
		return shoot;
	}

}
