package com.example.lhy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.player.panoplayer.PanoPlayer;
import com.player.panoplayer.PanoPlayerUrl;
import com.player.renderer.PanoPlayerSurfaceView;

public class MainActivity extends AppCompatActivity {

    PanoPlayerSurfaceView ppsview;
    private PanoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultOptions();

        mPlayer = new PanoPlayer(ppsview, this);
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();

        //播放 方式一:  setXmlContent(String content);  content 必须是如下格式的XML 文本 才可以播放


        //播放方式二:  setXmlUrl(String url); url 地址 必须返回的是 如上格式 的XML 文本才可以播放

        //panoplayerurl.setXmlUrl("http://www.detu.com/live/xinlan/live-test.xml");

        String PanoPlayer_Template = "<DetuVr> "
                + "<settings init=\"pano1\" initmode=\"flat\" enablevr=\"false\"  title=\"\"/>"
                + "<scenes>"
                + "<scene name=\"pano1\"  title=\"\"    thumburl=\"\"   >"
                + "<image type=\"video\" url=\"%s\" rx=\"0\" ry=\"0\" rz=\"0\"/>"
                + "<view hlookat=\"0\" vlookat=\"0\" fov=\"100\" vrfov=\"95\" vrz=\"0.5\" righteye=\"0.1\" fovmax=\"130\" defovmax=\"95\" gyroEnable=\"false\"/>"
                + "</scene>"
                + "</scenes>"
                + "</DetuVr>";
        String xmlstring = String.format(PanoPlayer_Template, "http://hls5.l.cztv.com/channels/lantian/wchannel102/720p.m3u8");

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

        Log.i("520it", "myURL=" + myURL);
        panoplayerurl.setXmlContent(myURL);
        mPlayer.setGyroEnable(true);
        mPlayer.Play(panoplayerurl);


    }

    private void defaultOptions() {
        setContentView(R.layout.activity_main);
        ppsview = (PanoPlayerSurfaceView) findViewById(R.id.glview);
        PanoPlayer renderer = new PanoPlayer(ppsview, this);
        renderer.setGyroEnable(true);
        ppsview.setRenderer(renderer);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.NONE)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
