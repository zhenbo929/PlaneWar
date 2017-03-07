package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

/**
 * 小飞机
 */
public class TinyPlane extends FlyingObject implements IEnemy {

	private int speed;// 移动速度

	public TinyPlane(int speedAdd) {
		this.image_current = SplashActivity.bitmap_enemy1_fly;
		this.images_boom = SplashActivity.bitmap_enemy1_blowup;
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.y = -height;
		this.x = (int) (Math.random() * (SplashActivity.WIDTH - width));
		this.speed = (int) (Math.random() * 3 + 1 + speedAdd);// 初始化速度[1,3]
	}

	/**
	 * 得到分数
	 */
	@Override
	public int getScore() {
		return 1000;
	}

	/**
	 * 是否越界
	 */
	@Override
	public boolean outOfBounds() {
		return y > SplashActivity.HEIGHT;
	}

	/**
	 * 移动一步
	 */
	@Override
	public void step() {
		y += speed;
	}
}
