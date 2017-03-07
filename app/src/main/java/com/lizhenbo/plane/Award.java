package com.lizhenbo.plane;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.planewar.SplashActivity;


/**
 * ������(���е�˫��������ը������)
 *
 */
public class Award extends FlyingObject{

	
	private int speed=2;
	private int type;
	
	public Award(int type){
		this.setType(type);
		if(this.getType()==0){
			this.image_current= SplashActivity.bitmap_enemy5_fly;
		}else if (this.getType()==1) {
			this.image_current=SplashActivity.bitmap_enemy4_fly;
		}
		this.width = image_current.getWidth();
		this.height = image_current.getHeight();
		this.y = -height;
		this.x = (int)(Math.random()*(SplashActivity.WIDTH - width));
	}
	
	
	@Override
	public boolean outOfBounds() {
		return y>SplashActivity.HEIGHT;
	}

	@Override
	public void step() {
		y += speed;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

}
