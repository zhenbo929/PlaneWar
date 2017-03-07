package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.planewar.SplashActivity;

/**
 * �ӵ�
 */
public class Bullet extends FlyingObject {

	private int speed = 20;// �ƶ��ٶ�
	private boolean bomb;// �ӵ���״̬���Ƿ��Ѿ���ը

	/**
	 * ���췽��
	 * 
	 * @param type
	 *            �ӵ������ͣ�1����һ���ӵ���2����˫������
	 * @param x
	 *            ս����ǰλ�����ĵ��λ��
	 * @param y
	 *            ս����ǰλ�����ĵ��λ��
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
	 * �ƶ�һ��
	 */
	@Override
	public void step() {
		y -= speed;
	}

	/**
	 * �Ƿ�Խ��
	 */
	@Override
	public boolean outOfBounds() {
		return y < -height;
	}

}
