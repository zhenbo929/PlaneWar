package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

/**
 * 中飞机
 */
public class BigPlane extends FlyingObject implements IEnemy {

	private int speed;// 移动速度
	private int life;// 大飞机的生命值

	public BigPlane(int speedAdd) {
		this.life = 4;// 大飞机的生命值4
		this.image_current = SplashActivity.bitmap_enemy3_fly;
		this.images_boom = SplashActivity.bitmap_enemy3_blowup;
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.y = -height;
		this.x = (int) (Math.random() * (SplashActivity.WIDTH - width));
		this.speed = (int) (Math.random() * 3 + 1 + speedAdd);// ��ʼ���ٶ�[1,3]
	}

	/**
	 * 得到分数
	 */
	@Override
	public int getScore() {
		return 6000;
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
