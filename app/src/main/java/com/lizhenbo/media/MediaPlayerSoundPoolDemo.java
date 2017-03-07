package com.lizhenbo.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class MediaPlayerSoundPoolDemo {

	public static MediaPlayer mediaPlayer;
	private static boolean isPause;
	static int currentPosition;

	public static void playBGM(Context context, int resid) {

		mediaPlayer = MediaPlayer.create(context, resid);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		isPause = false;
	}

	public static void stopBGM() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public static void pauseBGM() {// 暂停
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			currentPosition = mediaPlayer.getCurrentPosition();
			mediaPlayer.pause();
			isPause = true;
		}

	}

	public static void continueBGM() {
		if (mediaPlayer != null && isPause) {

			mediaPlayer.seekTo(currentPosition);
			mediaPlayer.start();
			currentPosition = 0;
			isPause = false;
		}
	}

	public static SoundPool soundPool;

	public static void playSoundPool(Context context, int resid) {
		if (soundPool == null) {
			// int maxStreams, 指定支持多少个声音
			// int streamType, 指定声音类型
			// int srcQuality 指定声音品质
			soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		}
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				// int soundID, 指定播放哪个声音
				// float leftVolume, 指定左音量
				// float rightVolume, 指定右音量
				// int priority, 指定播放声音的优先级，数值越大，优先级越高
				// int loop, 指定是否循环，0为不循环，-1为循环
				// float rate 指定播放的比率，数值可从0.5到2，1为正常比率
				soundPool.play(sampleId, 1.0f, 1.0f, 1, 0, 1.0f);
			}
		});
		// int resId 从resId所对应的资源加载声音
		// int priority 该参数目前没有任何作用，Android建议将该参数设为1，保持和未来的兼容性
		soundPool.load(context, resid, 1);
	}

	public static void releaseSoundPool() {
		if (soundPool != null) {
			// 释放资源
			soundPool.release();
			soundPool = null;
		}
	}

}
