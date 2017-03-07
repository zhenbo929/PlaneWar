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

	public static void pauseBGM() {
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
			// int maxStreams, ָ��֧�ֶ��ٸ�����
			// int streamType, ָ����������
			// int srcQuality ָ������Ʒ��
			soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		}
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				// int soundID, ָ�������ĸ�����
				// float leftVolume, ָ��������
				// float rightVolume, ָ��������
				// int priority, ָ���������������ȼ�����ֵԽ�����ȼ�Խ��
				// int loop, ָ���Ƿ�ѭ����0Ϊ��ѭ����-1Ϊѭ��
				// float rate ָ�����ŵı��ʣ���ֵ�ɴ�0.5��2��1Ϊ��������
				soundPool.play(sampleId, 1.0f, 1.0f, 1, 0, 1.0f);
			}
		});
		// int resId ��resId����Ӧ����Դ��������
		// int priority �ò���Ŀǰû���κ����ã�Android���齫�ò�����Ϊ1�����ֺ�δ���ļ�����

		soundPool.load(context, resid, 1);
	}

	public static void releaseSoundPool() {
		if (soundPool != null) {
			// �ͷ���Դ
			soundPool.release();
			soundPool = null;
		}
	}

}
