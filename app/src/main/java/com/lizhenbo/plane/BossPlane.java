package com.lizhenbo.plane;

import android.graphics.Bitmap;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IAward;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

import java.util.Random;

/**
 * 大飞机
 */
public class BossPlane extends FlyingObject implements IEnemy, IAward {

	private int speed = 1;
	private int life;// 大boss的生命值
	private int awardType;
	private Bitmap[] bitmap_enemy2_fly;// 方向状态的bitmap数组
	protected int index = 0;// 方向状态时当前的图片的数组下标

	public BossPlane(int speedAdd) {
		this.speed = this.speed + speedAdd / 4;
		this.life = 8;// 大boss的生命值8
		this.bitmap_enemy2_fly = SplashActivity.bitmap_enemy2_fly;
		this.image_current = bitmap_enemy2_fly[0];
		this.images_boom = SplashActivity.bitmap_enemy2_blowup;
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.y = -height;
		Random rand = new Random();
		this.x = rand.nextInt(SplashActivity.WIDTH - width);
		this.awardType = rand.nextInt(2);
	}

	/**
	 * 获得奖励
	 */
	@Override
	public int getType() {
		return awardType;
	}

	/**
	 * 得到分数
	 */
	@Override
	public int getScore() {
		return 10000;
	}

	/**
	 * 是否越界
	 */
	@Override
	public boolean outOfBounds() {
		return false;
	}

	/***
	 * 飞行状态是每移动一步还要改变当前图片
	 */
	@Override
	public void step() { // �ƶ�
		y += speed;
		if (bitmap_enemy2_fly.length > 0) {
			image_current = bitmap_enemy2_fly[index++ / 10
					% bitmap_enemy2_fly.length];// 1++/10%2
		}
	}

	/**
	 * 重写父类被击中的方法， 每次被击中生命值life--
	 */
	@Override
	public boolean shootBy(Bullet bullet) {
		if (super.shootBy(bullet)) {
			life--;
		}
		return life == 0;
	}

}
