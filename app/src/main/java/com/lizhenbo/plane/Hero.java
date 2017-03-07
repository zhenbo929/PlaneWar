package com.lizhenbo.plane;

import android.graphics.Bitmap;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.planewar.SplashActivity;

/**
 * 战机
 */
public class Hero extends FlyingObject {

	protected Bitmap[] bitmap_heros;// 战机飞行状态的bitmap数组
	protected int index = 0;// 飞行状态数组下标

	private int doubleFire;// 是否是双倍火力，0是单倍火力，大于0是双倍火力

	public Hero() {
		this.doubleFire = 0;
		this.images_boom = SplashActivity.bitmap_hero_blowup;
		this.bitmap_heros = SplashActivity.bitmap_hero_fly;
		this.image_current = bitmap_heros[0];
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.x = SplashActivity.WIDTH / 2 - width / 2;
		this.y = SplashActivity.HEIGHT - height;
	}

	public int isDoubleFire() {
		return doubleFire;
	}

	public void addDoubleFire() {
		doubleFire = 60;
	}

	/**
	 * 是否越界,(这里没用,但是父类的又必须继承，唉╮(╯▽╰)╭)
	 */
	@Override
	public boolean outOfBounds() {
		return x < 0 || x > SplashActivity.WIDTH - width || y < 0
				|| y > SplashActivity.HEIGHT - height;
	}

	/**
	 * 发射子弹
	 */
	public Bullet[] shoot() {
		int xStep = width / 4;// 飞机宽度分为4份
		int yStep = 20;
		if (doubleFire > 0) {// 双倍火力
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(2, x + xStep, y - yStep);
			bullets[1] = new Bullet(2, x + 3 * xStep, y - yStep);
			doubleFire -= 1;
			return bullets;
		} else {// 单倍火力
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(1, x + 2 * xStep, y - yStep);
			return bullets;
		}
	}
	/**
	 * 移动一步
	 */
	@Override
	public void step() {
		if (bitmap_heros.length > 0) {
			image_current = bitmap_heros[index++ / 10 % bitmap_heros.length];
		}
	}

	/**
	 * 是否被撞击
	 */
	public boolean hit(FlyingObject flyingObject) {
		return hitOrGet(flyingObject);
	}
	/**
	 * 是否得到奖励
	 */
	public boolean getAward(Award award) {
		return hitOrGet(award);
	}

	/**
	 * 碰撞算法
	 */
	public boolean hitOrGet(FlyingObject other) {
		int x1 = other.getX() - this.width / 4;
		int x2 = other.getX() + other.getWidth() + this.width / 4;
		int y1 = other.getY() - this.height / 4;
		int y2 = other.getY() + other.getHeight() + this.height / 4;
		return this.x + this.width / 2 > x1 && this.x + this.width / 2 < x2
				&& this.y + this.height / 2 > y1
				&& this.y + this.width / 2 < y2;
	}

}
