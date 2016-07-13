package com.demo.panoplayer;

import org.opencv.android.OpenCVLoader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.demo.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.player.data.panoramas.PanoramaData;
import com.player.panoplayer.IPanoPlayerListener;
import com.player.panoplayer.IPanoPlayerVideoPluginListener;
import com.player.panoplayer.PanoPlayer;
import com.player.panoplayer.PanoPlayer.PanoPlayerErrorCode;
import com.player.panoplayer.PanoPlayer.PanoPlayerErrorStatus;
import com.player.panoplayer.PanoPlayer.PanoVideoPluginStatus;
import com.player.panoplayer.PanoPlayerUrl;
import com.player.panoplayer.Plugin;
import com.player.panoplayer.plugin.VideoPlugin;
import com.player.renderer.PanoPlayerSurfaceView;

import detutv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends Activity implements IPanoPlayerListener,
		IPanoPlayerVideoPluginListener {

	private PanoPlayer panoplayer_renderer;
	private PanoPlayerSurfaceView glview;
	private SeekBar sb_progress;
	private Button btn_play;
	private TextView maxtimelable;
	private TextView curtimelable;
	protected Handler handler = new Handler();
	private PanoVideoPluginStatus playerStatus = PanoVideoPluginStatus.VIDEO_STATUS_STOP;
	private boolean isSeekBarDragging;
	private VideoPlugin videoplugin;
	private boolean isplaylive = false;
	private boolean isGyroEnable = false;
	static {
		if (!OpenCVLoader.initDebug()) {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Vitamio.initialize(MainActivity.this);
		setContentView(R.layout.activity_main);
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.NONE).cacheInMemory()
				.cacheOnDisc().build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// .writeDebugLogs()
				.tasksProcessingOrder(QueueProcessingType.FIFO).build();
		ImageLoader.getInstance().init(config);

		btn_play = (Button) findViewById(R.id.btn_play);
		sb_progress = (SeekBar) findViewById(R.id.sb_progress);
		glview = (PanoPlayerSurfaceView) findViewById(R.id.glview);
		maxtimelable = (TextView) findViewById(R.id.lable2);
		curtimelable = (TextView) findViewById(R.id.lable1);
		panoplayer_renderer = new PanoPlayer(glview, this);
		panoplayer_renderer.setListener(this);
		panoplayer_renderer.setVideoPluginListener(this);
		glview.setRenderer(panoplayer_renderer);
		findViewById(R.id.videolay).setVisibility(View.GONE);
		playLive();
		findViewById(R.id.dre_live).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				playLive();
			}
		});
		//

		//

		findViewById(R.id.dre_video).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				isplaylive = false;

				PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
				panoplayerurl
						.SetVideoUrlImage(
								"http://v3.cztv.com/cztv/vod/2016/03/15/f71522061dc84e10bc012c5243585e0f/h264_1500k_mp4.mp4",
								"");
				panoplayer_renderer.Play(panoplayerurl);
			}
		});

		findViewById(R.id.Gyro).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (panoplayer_renderer != null) {
					isGyroEnable = !isGyroEnable;
					panoplayer_renderer.setGyroEnable(isGyroEnable);
				}
			}
		});
		btn_play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.e("", "click btn_play");
				switch (playerStatus) {
				case VIDEO_STATUS_PAUSE:
					videoplugin.start();
					Log.e("", "click btn_play to start");
					break;
				case VIDEO_STATUS_STOP:
					videoplugin.start();
					Log.e("", "click btn_play to start");
					break;
				case VIDEO_STATUS_PLAYING:
					videoplugin.pause();
					Log.e("", "click btn_play to pause");
					break;
				default:
					break;
				}
			}
		});

		sb_progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar sb) {
				isSeekBarDragging = false;
				videoplugin.seekTo(sb.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				isSeekBarDragging = true;
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int progress,
					boolean fromUser) {
			}
		});

		findViewById(R.id.btn_full).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams lp = (LayoutParams) glview
						.getLayoutParams();
				lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
				glview.setLayoutParams(lp);
			}
		});

	}

	public void playLive(){
		isplaylive=true;
		PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();

		//播放 方式一:  setXmlContent(String content);  content 必须是如下格式的XML 文本 才可以播放


		//播放方式二:  setXmlUrl(String url); url 地址 必须返回的是 如上格式 的XML 文本才可以播放

		//panoplayerurl.setXmlUrl("http://www.detu.com/live/xinlan/live-test.xml");

		String PanoPlayer_Template = "<DetuVr> "
				+ "<settings init=\"pano1\" initmode=\"flat\" enablevr=\"false\"  title=\"\"/>"
				+ 	"<scenes>"
				+ 		"<scene name=\"pano1\"  title=\"\"    thumburl=\"\"   >"
				+ 			"<image type=\"video\" url=\"%s\" rx=\"0\" ry=\"0\" rz=\"0\"/>"
				+            "<view hlookat=\"0\" vlookat=\"0\" fov=\"100\" vrfov=\"95\" vrz=\"0.5\" righteye=\"0.1\" fovmax=\"130\" defovmax=\"95\" gyroEnable=\"false\"/>"
				+ 		"</scene>"
				+ 	"</scenes>"
				+ "</DetuVr>";
		String xmlstring = String.format(PanoPlayer_Template,"http://hls5.l.cztv.com/channels/lantian/wchannel102/720p.m3u8");

		String myURL = "<DetuVr> \n" +
				"\t<settings init=\"pano1\" initmode=\"default\" enablevr=\"false\" title=\"\" /> \n" +
				"\t<scenes> \n" +
				"\t\t<!-- pano --> \n" +
				"\t\t<scene name=\"pano-132079\" title=\"广东现代国际展览中心\" thumburl=\"http://media.qicdn.detu.com/pano256151468285576336023987/thumb/500_500/panofile.jpg\"> \n" +
				"\t\t\t<view hlookat=\"0\" vlookat=\"0\" fov=\"65\" vrfov=\"90\" vrz=\"0.5\" righteye=\"0\" fovmax=\"130\" defovmax=\"95\" viewmode=\"default\" /> \n" +
				"\t\t\t<preview url=\"http://media.qicdn.detu.com/pano256151468285576336023987/oper/panofile_preview_detunew.jpg\" /> \n" +
				"\t\t\t<image type=\"cube\" url=\"http://media.qicdn.detu.com/pano256151468285576336023987/oper/panofile_html_%s.jpg\" /> \n" +
				"\t\t</scene> \n" +
				"\t</scenes> \n" +
				"</DetuVr>";
		panoplayerurl.setXmlContent(myURL);


 		panoplayer_renderer.Play(panoplayerurl);
	}

	@Override
	public void PluginVideOnPlayerError(PanoPlayerErrorStatus s, String errorstr) {
		Log.d("PanoPlay", "PluginVideOnPlayerError" + errorstr);
	}

	@Override
	public void PluginVideoOnProgressChanged(final int curTime, int bufTime,
			final int maxTime) {
		if (!isSeekBarDragging) {
			sb_progress.setMax(maxTime);
			sb_progress.setSecondaryProgress(bufTime);
			sb_progress.setProgress(curTime);
			handler.post(new Runnable() {
				@Override
				public void run() {
					maxtimelable.setText(formatDuring(maxTime));
					curtimelable.setText(formatDuring(curTime));
				}
			});
		}
	}

	public String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) + days
				* 24;
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;

		String HH = (hours > 0) ? String.valueOf(hours) : "00";
		String mm = (minutes > 0) ? String.valueOf(minutes) : "00";
		String ss = (seconds > 0) ? String.valueOf(seconds) : "00";

		HH = (HH.length() == 1) ? ("0" + HH) : (HH);
		mm = (mm.length() == 1) ? ("0" + mm) : (mm);
		ss = (ss.length() == 1) ? ("0" + ss) : (ss);
		return HH + " : " + mm + " : " + ss;
	}

	@Override
	public void PluginVideoOnSeekFinished() {
		Log.d("PanoPlay", "PluginVideoOnSeekFinished");
	}

	@Override
	public void PluginVideoOnStatusChanged(PanoVideoPluginStatus s) {
		playerStatus = s;
		switch (s) {
		case VIDEO_STATUS_PAUSE:
			btn_play.post(new Runnable() {
				public void run() {
					btn_play.setText("暂停");
				}
			});
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to pause");
			break;
		case VIDEO_STATUS_STOP:
			btn_play.post(new Runnable() {
				public void run() {
					btn_play.setText("停止");
				}
			});
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to stop");
			sb_progress.setProgress(0);
			break;
		case VIDEO_STATUS_PLAYING:
			btn_play.post(new Runnable() {
				public void run() {
					btn_play.setText("播放");
				}
			});
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to play");
			break;
		case VIDEO_STATUS_FINISH:
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to FINISH");
			break;
		case VIDEO_STATUS_BUFFER_EMPTY:
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to BUFFER_EMPTY");
			break;
		default:
			Log.d("PanoPlay", "PluginVideoOnStatusChanged to UNPREPARED;");
			break;
		}
	}

	@Override
	public void PanoPlayOnEnter(PanoramaData arg0) {
		Log.d("PanoPlay", "PanoPlayOnEnter");
	}

	@Override
	public void PanoPlayOnError(PanoPlayerErrorCode e) {
		Log.d("PanoPlay", "PanoPlayOnError" + e);
	}

	@Override
	public void PanoPlayOnLeave(PanoramaData arg0) {
		Log.d("PanoPlay", "PanoPlayOnLeave");
	}

	@Override
	public void PanoPlayOnLoaded() {
		Log.d("PanoPlay", "PanoPlayOnLoaded");
		findViewById(R.id.videolay).setVisibility(View.GONE);
		Plugin plugin = panoplayer_renderer.getCurPlugin();
		if (plugin instanceof VideoPlugin && !isplaylive) {
			videoplugin = (VideoPlugin) plugin;
			findViewById(R.id.videolay).setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void PanoPlayOnLoading() {

	}

	@Override
	public void PluginVideoOnInit() {
		Plugin plugin = panoplayer_renderer.getCurPlugin();
		if (plugin != null && plugin instanceof VideoPlugin) {
			videoplugin = (VideoPlugin) plugin;
			videoplugin.setLogLevel(IjkMediaPlayer.IJK_LOG_DEFAULT);
		}
	}
}
