package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

/**
 * �зɻ�
 */
public class BigPlane extends FlyingObject implements IEnemy {

	private int speed;// �ƶ��ٶ�
	private int life;// ��ɻ�������ֵ

	public BigPlane(int speedAdd) {
		this.life = 4;// ��ɻ�������ֵ4
		this.image_current = SplashActivity.bitmap_enemy3_fly;
		this.images_boom = SplashActivity.bitmap_enemy3_blowup;
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
		return 6000;
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

	/**
	 * ��д���౻���еķ����� ÿ�α���������ֵlife--
	 */
	@Override
	public boolean shootBy(Bullet bullet) {
		if (super.shootBy(bullet)) {
			life--;
		}
		return life == 0;
	}

}
