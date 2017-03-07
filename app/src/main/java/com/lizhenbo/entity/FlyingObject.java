package com.lizhenbo.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lizhenbo.plane.Bullet;
/**
 * 飞行物的抽象父类 子类包括：大飞机，中飞机，小飞机，战机，子弹，奖励（双倍火力，炸弹）
 */
public abstract class FlyingObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Bitmap image_current;// 当前图片
	protected Bitmap[] images_boom;// 爆炸图片

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
	 * 当前图片绘制
	 */
	public void draw(Canvas canvas, Paint paint) {
		if (image_current != null) {
			canvas.drawBitmap(image_current, x, y, paint);
		}
	}

	/**
	 * 检查是否出界
	 */
	public abstract boolean outOfBounds();

	/**
	 * 飞行物移动一步
	 */
	public abstract void step();

	/**
	 * 检查当前飞行物体是否被子弹击中
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
