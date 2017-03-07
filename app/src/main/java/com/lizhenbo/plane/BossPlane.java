package com.lizhenbo.plane;

import android.graphics.Bitmap;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IAward;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.planewar.SplashActivity;

import java.util.Random;

/**
 * ��ɻ�
 */
public class BossPlane extends FlyingObject implements IEnemy, IAward {

	private int speed = 1;
	private int life;// ��boss������ֵ
	private int awardType;
	private Bitmap[] bitmap_enemy2_fly;// ����״̬��bitmap����
	protected int index = 0;// ����״̬ʱ��ǰ��ͼƬ�������±�

	public BossPlane(int speedAdd) {
		this.speed = this.speed + speedAdd / 4;
		this.life = 8;// ��boss������ֵ8
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
	 * ��ý���
	 */
	@Override
	public int getType() {
		return awardType;
	}

	/**
	 * �õ�����
	 */
	@Override
	public int getScore() {
		return 10000;
	}

	/**
	 * �Ƿ�Խ��
	 */
	@Override
	public boolean outOfBounds() {
		return false;
	}

	/***
	 * ����״̬��ÿ�ƶ�һ����Ҫ�ı䵱ǰͼƬ
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
