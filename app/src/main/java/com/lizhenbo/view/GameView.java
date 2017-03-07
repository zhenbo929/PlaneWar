package com.lizhenbo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
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

	/** ��Ϸ�ĵ�ǰ״̬: START RUNNING PAUSE GAME_OVER */
	private int state;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;

	private Paint paint;// ����
	private int score = 0;// �÷�
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
		// ����init()��������ʼ����
		init();
	}

	/** ��ʼ������ */
	public void init() {
		hero = new Hero();
		paint = new Paint();
		map1 = new Map(0);
		map2 = new Map(1);
	}

	/**
	 * ��������ͼ�Σ�ʹ�û���paint�ڻ���canvas�ϻ���
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// ������

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
		// ��ս��
		if (hero != null) {
			hero.draw(canvas, paint);
		}
		if (award != null) {
			award.draw(canvas, paint);
		}
	}

	/**
	 * ս�����϶��¼�
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
	 * ��ͣ��ť����ͣ��Ϸ
	 */
	public void pauseGame() {
		if (state == RUNNING) {
			state = PAUSE;
		}
	}

	/**
	 * ������Ϸ����ͣ�������Ϸ
	 */
	public void continueGame() {
		if (state == PAUSE) {
			state = RUNNING;
		}
	}

	/**
	 * ը����ť���������еĵл�
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

	// ���ӷ���
	public void addScore(FlyingObject flyingObject) {
		IEnemy e = (IEnemy) flyingObject; // ǿ������ת��
		score += e.getScore(); // �ӷ�
		sendMsgToMain(1);
	}

	// �����̷߳���Ϣ�ķ���
	private void sendMsgToMain(int what) {
		if (what == 1) {
			Message msg = GameActivity.handler.obtainMessage(what);
			msg.obj = score;
			GameActivity.handler.sendMessage(msg);
		} else if (what == 2) {
			GameActivity.handler.sendEmptyMessage(what);
			Log.i("TAG", "֪ͨUI���õ�ը����");
		} else if (what == 3) {
			Message msg = GameActivity.handler.obtainMessage(what);
			msg.obj = score;
			GameActivity.handler.sendMessage(msg);
		}
	}

	/**
	 * ������Ϸ
	 */
	public void startGame() {
		state = RUNNING;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) {
					enterAction();// �л�����
					stepAction();// �ƶ�һ��
					shootAction();// ����ӵ�
					bangAction();// �ӵ��Ƿ���ел�
					getAward();// ս���Ƿ�õ�����
					outOfBoundsAction();// ɾ��Խ��л����ӵ�������
					checkGameOverAction();// �����Ϸ����
					emberAction();// ײ���ĵл���ս����ը
					postInvalidate();// ֪ͨ���£����»���
				}
			}
		}, 10, 10);
	}

	int flyEnteredIndex = 0; // ��ʱ
	public static int gamelevel=35;

	/** �л����� */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex == gamelevel) {// 500����--10*30
			FlyingObject obj = nextOne();// �������һ��������
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
			flyEnteredIndex = 0;// ��ʱ����
		}
	}

	// �������һ��������
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
	 * �ƶ�һ��
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

	int shootIndex = 0; // �����ʱ

	/** ��� */
	public void shootAction() {
		if (hero != null) {
			shootIndex++;
			if (shootIndex == 15) {// 150���뷢һ��
				Bullet[] bs = hero.shoot();// Ӣ�۴���ӵ�
				bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // ����
				System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
						bs.length); // ׷������
				shootIndex = 0;// ��ʱ����
			}

		}
	}

	/** �ӵ����������ײ��� */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) { // ���������ӵ�
			Bullet b = bullets[i];
			bang(b);
		}
	}

	// �ӵ��ͷ�����֮�����ײ���
	public void bang(Bullet bullet) {
		int index = -1; // ���еķ���������
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) { // �ж��Ƿ����
				index = i; // ��¼�����еķ����������
				break;
			}
		}
		if (index != -1) { // �л��еķ�����
			FlyingObject one = flyings[index]; // ��¼�����еķ�����
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = one;
			flyings = Arrays.copyOf(flyings, flyings.length - 1); // ɾ�����һ��������(�������е�)
			// �ӷ���
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
			if (one instanceof IAward) { // ��Ϊ���������ý���
				IAward a = (IAward) one;
				int type = a.getType(); // ��ȡ��������
				switch (type) {
				case 0:
					award = new Award(0); // ����˫������
					break;
				case 1:
					award = new Award(1); // ���ü���
					break;
				}
			}
			// �������ɻҽ�
			ToDoom toDoom = new ToDoom(one);
			toDooms = Arrays.copyOf(toDooms, toDooms.length + 1);
			toDooms[toDooms.length - 1] = toDoom;
		}
	}

	/**
	 * ����Ƿ�õ�����
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

	/** ɾ��Խ������Ｐ�ӵ� */
	public void outOfBoundsAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // ���ŵķ�����
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f; // ��Խ�������
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // ����Խ��ķ����ﶼ����

		index = 0; // ����Ϊ0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // ����Խ����ӵ�����

		if (award != null && award.outOfBounds()) {
			award = null;
		}

	}

	/** �����Ϸ���� */
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

	// �����Ϸ�Ƿ����
	public boolean isGameOver() {

		boolean b = false;
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // ���Ӣ�ۻ���������Ƿ���ײ
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
	 * ���屬ը
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
