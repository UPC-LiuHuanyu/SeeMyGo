package com.example.lhy.cons;

/**
 * Created by 刘焕宇 on 16/7/14.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class NetCons {

    public static final String BANNER_URL = "http://www.test.detu.com/api/mobile2/get_zzn_carousel";

    public static final String EXTRA_TAIL = "?identifier=com.detu.qumeng&appversion=26&mobiledevice=s&mobilesystem=android";

    public static final String CHANNELS_URL = "http://www.test.detu.com/api/mobile2/get_zzn_home_channels";


    /**
     * 根据频道id获取图片/视频
     *
     * @param int channelid 频道id
     * @param int pageindex 页码
     * @param int pagesize 每页条数
     * @param string order 排序方式(hot根据热度倒序排序, recommend根据运营推荐顺序排序[默认排序], uploadtime根据上传时间正序排序)
     */
    public static final String SPECIFIC_CHANNEL_URL = " http://www.test.detu.com/api/mobile2/get_collection_by_channel";

    //public static final String SPECIFIC_CHANNEL_PARAMS = "?channelid=19&pageindex=1&pagesize=20&order=recommend";
    public static final String SPECIFIC_CHANNEL_PARAMS = "?channelid=%d&pageindex=%d&pagesize=%d&order=%s";

    public static String getUrl(int channelid, int pageindex, int pagesize, String order) {
        return String.format(SPECIFIC_CHANNEL_URL + SPECIFIC_CHANNEL_PARAMS, channelid, pageindex, pagesize, order);
    }

}
