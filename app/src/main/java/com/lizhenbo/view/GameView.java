package com.lizhenbo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lizhenbo.entity.FlyingObject;
import com.lizhenbo.entity.IAward;
import com.lizhenbo.entity.IEnemy;
import com.lizhenbo.media.MediaPlayerSoundPoolDemo;
import com.lizhenbo.plane.Award;
import com.lizhenbo.plane.BigPlane;
import com.lizhenbo.plane.BossPlane;
import com.lizhenbo.plane.Bullet;
import com.lizhenbo.plane.Hero;
import com.lizhenbo.plane.Map;
import com.lizhenbo.plane.TinyPlane;
import com.lizhenbo.plane.ToDoom;
import com.lizhenbo.planewar.GameActivity;
import com.lizhenbo.planewar.R;
import com.lizhenbo.planewar.SplashActivity;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameView extends View {

	/** 游戏的当前状态: START RUNNING PAUSE GAME_OVER */
	private int state;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;

	private Paint paint;// 画笔
	private int score = 0;// 得分
	private FlyingObject[] flyings = {};
	private Bullet[] bullets = {};
	private Hero hero;
	private ToDoom[] toDooms = {};
	private Award award = null;
	private Map map1;
	private Map map2;
	private Timer timer;
	Context context;

	public GameView(Context context) {
		this(context, null);
	}

	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		// 调用init()方法来初始数据
		init();
	}

	/** 初始化数据 */
	public void init() {
		hero = new Hero();
		paint = new Paint();
		map1 = new Map(0);
		map2 = new Map(1);
	}

	/**
	 * 用来绘制图形，使用画笔paint在画布canvas上绘制
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 画背景

		map1.draw(canvas, paint);
		map2.draw(canvas, paint);

		// canvas.drawBitmap(SplashActivity.bitmap_background, 0, 0, paint);
		for (ToDoom toDoom : toDooms) {
			toDoom.draw(canvas, paint);
		}
		for (Bullet bullet : bullets) {
			if (!bullet.isBomb()) {
				bullet.draw(canvas, paint);
			}
		}
		for (FlyingObject flyingObject : flyings) {
			flyingObject.draw(canvas, paint);
		}
		// 画战机
		if (hero != null) {
			hero.draw(canvas, paint);
		}
		if (award != null) {
			award.draw(canvas, paint);
		}
	}

	/**
	 * 战机的拖动事件
	 */
	private int x_x = 0;
	private int y_y = 0;

	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		int action = event.getAction();
		if (hero != null) {
			if (action == 0) {// ����
				float event_x = event.getX();
				float event_y = event.getY();
				int current_x = hero.getX();
				int current_y = hero.getY();
				x_x = (int) (current_x - event_x);
				y_y = (int) (current_y - event_y);
			} else if (action == 2) {
				float current_x = event.getX() + x_x;
				float current_y = event.getY() + y_y;
				if (current_x >= 0
						&& current_x <= SplashActivity.WIDTH - hero.getWidth()) {
					hero.setX((int) (current_x));
				}
				if (current_y >= 0
						&& current_y <= SplashActivity.HEIGHT
								- hero.getHeight()) {
					hero.setY((int) (current_y));
				}
			}
		}
		return true;
	}

	/**
	 * 暂停按钮：暂停游戏
	 */
	public void pauseGame() {
		if (state == RUNNING) {
			state = PAUSE;
		}
	}

	/**
	 * 继续游戏：暂停后继续游戏
	 */
	public void continueGame() {
		if (state == PAUSE) {
			state = RUNNING;
		}
	}

	/**
	 * 炸弹按钮：销毁所有的敌机
	 */
	public void bombAll() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject flyingObject = flyings[i];
			ToDoom ember = new ToDoom(flyingObject);
			toDooms = Arrays.copyOf(toDooms, toDooms.length + 1);
			toDooms[toDooms.length - 1] = ember;
			addScore(flyingObject);
			if (flyingObject instanceof TinyPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.enemy_blowup);
			} else if (flyingObject instanceof BigPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.enemy_blowup);

			} else if (flyingObject instanceof BossPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.boss_blowup);
			}
		}
		flyings = Arrays.copyOf(flyings, 0);
	}

	// 增加分数
	public void addScore(FlyingObject flyingObject) {
		IEnemy e = (IEnemy) flyingObject; // 强制类型转换
		score += e.getScore(); // 加分
		sendMsgToMain(1);
	}

	// 给主线程发消息的方法
	private void sendMsgToMain(int what) {
		if (what == 1) {
			Message msg = GameActivity.handler.obtainMessage(what);
			msg.obj = score;
			GameActivity.handler.sendMessage(msg);
		} else if (what == 2) {
			GameActivity.handler.sendEmptyMessage(what);
		} else if (what == 3) {
			Message msg = GameActivity.handler.obtainMessage(what);
			msg.obj = score;
			GameActivity.handler.sendMessage(msg);
		}
	}

	/**
	 * 启动游戏
	 */
	public void startGame() {
		state = RUNNING;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) {
					enterAction();// 敌机出现
					stepAction();// 移动一步
					shootAction();// 射击子弹
					bangAction();// 子弹是否击中敌机
					getAward();// 战机是否得到奖励
					outOfBoundsAction();// 删除越界敌机，子弹及奖励
					checkGameOverAction();// 检查游戏结束
					emberAction();// 撞击的敌机及战机爆炸
					postInvalidate();// 通知更新，重新绘制
				}
			}
		}, 10, 10);
	}

	int flyEnteredIndex = 0; // 计时
	public static int gamelevel=35;

	/** 敌机出现 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex == gamelevel) {// 500毫秒--10*30
			FlyingObject obj = nextOne();// 随机生成一个飞行物
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
			flyEnteredIndex = 0;// 计时清零
		}
	}

	// 随机生成一个飞行物
	private FlyingObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(20);
		int speedAdd = score / 100000;
		if (type == 0) {
			MediaPlayerSoundPoolDemo.playSoundPool(context, R.raw.boss_appear);
			return new BossPlane(speedAdd);// 1
		} else if (type <= 3) {
			return new BigPlane(speedAdd);// 3
		} else {
			return new TinyPlane(speedAdd);// 16
		}
	}

	/**
	 * 移动一步
	 */
	public void stepAction() {

		map1.step();
		map2.step();

		if (hero != null) {
			hero.step();
		}
		for (FlyingObject flyingObject : flyings) {
			flyingObject.step();
		}
		for (Bullet bullet : bullets) {
			bullet.step();
		}
		if (award != null) {
			award.step();
		}

	}

	int shootIndex = 0; // 射击计时

	/** 射击 */
	public void shootAction() {
		if (hero != null) {
			shootIndex++;
			if (shootIndex == 15) {/// 150毫秒发一颗
				Bullet[] bs = hero.shoot();// 英雄打出子弹
				bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 扩容
				System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
						bs.length); // 追加数组
				shootIndex = 0;// 计时清零
			}

		}
	}

	/** 子弹与飞行物碰撞检测 */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) { // 遍历所有子弹
			Bullet b = bullets[i];
			bang(b);
		}
	}

	// 子弹和飞行物之间的碰撞检查
	public void bang(Bullet bullet) {
		int index = -1; // 击中的飞行物索引
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) { // 判断是否击中
				index = i; // 记录被击中的飞行物的索引
				break;
			}
		}
		if (index != -1) { // 有击中的飞行物
			FlyingObject one = flyings[index]; // 记录被击中的飞行物
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = one;
			flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)
			// 加分数
			addScore(one);
			if (one instanceof TinyPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.enemy_blowup);
			} else if (one instanceof BigPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.enemy_blowup);

			} else if (one instanceof BossPlane) {
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.boss_blowup);
			}
			if (one instanceof IAward) { // 若为奖励，设置奖励
				IAward a = (IAward) one;
				int type = a.getType(); // 获取奖励类型
				switch (type) {
				case 0:
					award = new Award(0); // 设置双倍火力
					break;
				case 1:
					award = new Award(1); // 设置加命
					break;
				}
			}
			// 飞行物变成灰烬
			ToDoom toDoom = new ToDoom(one);
			toDooms = Arrays.copyOf(toDooms, toDooms.length + 1);
			toDooms[toDooms.length - 1] = toDoom;
		}
	}

	/**
	 * 检查是否得到奖励
	 */
	public void getAward() {
		if (award != null && hero != null) {
			if (hero.getAward(award)) {
				int type = award.getType();
				if (type == 0) {
					hero.addDoubleFire();

				} else if (type == 1) {

					sendMsgToMain(2);
				}
				award = null;
			}

		}
	}

	/** 删除越界飞行物及子弹 */
	public void outOfBoundsAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // 活着的飞行物
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f; // 不越界的留着
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // 将不越界的飞行物都留着

		index = 0; // 重置为0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // 将不越界的子弹留着

		if (award != null && award.outOfBounds()) {
			award = null;
		}

	}

	/** 检查游戏结束 */
	public void checkGameOverAction() {

		if (hero != null && isGameOver()) {

			new Thread() {
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					state = GAME_OVER;
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					toDooms = new ToDoom[0];
					award = null;
					sendMsgToMain(3);
				};
			}.start();
			hero = null;

		}
	}

	// 检查游戏是否结束
	public boolean isGameOver() {

		boolean b = false;
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // 检查英雄机与飞行物是否碰撞
				MediaPlayerSoundPoolDemo.playSoundPool(context,
						R.raw.hero_blowup);
				if (obj instanceof TinyPlane) {
					MediaPlayerSoundPoolDemo.playSoundPool(context,
							R.raw.enemy_blowup);
				} else if (obj instanceof BigPlane) {
					MediaPlayerSoundPoolDemo.playSoundPool(context,
							R.raw.enemy_blowup);

				} else if (obj instanceof BossPlane) {
					MediaPlayerSoundPoolDemo.playSoundPool(context,
							R.raw.boss_blowup);
				}
				b = true;
				index = i;
				ToDoom toDoomhero = new ToDoom(hero);
				toDooms = Arrays.copyOf(toDooms, toDooms.length + 1);
				toDooms[toDooms.length - 1] = toDoomhero;
			}
		}
		if (index != -1) {
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);

			ToDoom toDoomobj = new ToDoom(t);
			toDooms = Arrays.copyOf(toDooms, toDooms.length + 1);
			toDooms[toDooms.length - 1] = toDoomobj;
		}

		return b;
	}

	/**
	 * 物体爆炸
	 */
	private void emberAction() {
		ToDoom[] live = new ToDoom[toDooms.length];
		int index = 0;
		for (int i = 0; i < toDooms.length; i++) {
			ToDoom ember = toDooms[i];
			if (!ember.burnDown()) {
				live[index++] = ember;
			}
		}
		toDooms = Arrays.copyOf(live, index);
	}

}
