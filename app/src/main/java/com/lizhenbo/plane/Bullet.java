package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.planewar.SplashActivity;

/**
 * 子弹
 */
public class Bullet extends FlyingObject {

	private int speed = 20;// 移动速度
	private boolean bomb;// 子弹的状态，是否已经爆炸

	/**
	 * 构造方法
	 *
	 * @param type
	 *            子弹的类型，1代表一个子弹，2代表双倍火力
	 * @param x
	 *            战机当前位置中心点的位置
	 * @param y
	 *            战机当前位置中心点的位置
	 */
	public Bullet(int type, int x, int y) {
		if (type == 1) {// ��������
			this.image_current = SplashActivity.bitmap_bullet;
			this.width = image_current.getWidth();
			this.height = image_current.getHeight();
			this.x = x - width / 2;
			this.y = y;
		} else if (type == 2) {// ˫������
			this.image_current = SplashActivity.bitmap_bullet_double;
			this.width = image_current.getWidth();
			this.height = image_current.getHeight();
			this.x = x - width / 2;
			this.y = y;
		}
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public boolean isBomb() {
		return bomb;
	}

	/**
	 * 移动一步
	 */
	@Override
	public void step() {
		y -= speed;
	}

	/**
	 * 是否越界
	 */
	@Override
	public boolean outOfBounds() {
		return y < -height;
	}

}
