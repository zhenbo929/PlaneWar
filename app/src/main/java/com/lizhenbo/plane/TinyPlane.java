package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

/**
 * С�ɻ�
 */
public class TinyPlane extends FlyingObject implements IEnemy {

	private int speed;// �ƶ��ٶ�

	public TinyPlane(int speedAdd) {
		this.image_current = SplashActivity.bitmap_enemy1_fly;
		this.images_boom = SplashActivity.bitmap_enemy1_blowup;
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.y = -height;
		this.x = (int) (Math.random() * (SplashActivity.WIDTH - width));
		this.speed = (int) (Math.random() * 3 + 1 + speedAdd);// ��ʼ���ٶ�[1,3]
	}

	/**
	 * �õ�����
	 */
	@Override
	public int getScore() {
		return 1000;
	}

	/**
	 * �Ƿ�Խ��
	 */
	@Override
	public boolean outOfBounds() {
		return y > SplashActivity.HEIGHT;
	}

	/**
	 * �ƶ�һ��
	 */
	@Override
	public void step() {
		y += speed;
	}
}
