package com.game.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.protocol.TsApiRTBProtocol;
import com.game.rtb.vo.Ad;
import com.game.rtb.vo.Juping;
import com.game.rtb.vo.MaterialMeta;
import com.game.rtb.vo.Tracking;
import com.game.rtb.vo.TsApiResponse;

import lombok.extern.log4j.Log4j;


/**
* @author chentao
* @version 2019年7月7日 上午15:18:03
*/

@Controller
@Log4j
@RequestMapping(value = "/")
public class TestBaiduAdController {
	
	
	@RequestMapping(value = "/testInputStream", method=RequestMethod.POST)
	public void testInputStream(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info("开始运行……testInputStream方法");
		TsApiResponse tar_v = new TsApiResponse();
		tar_v.respContent = "test";
		tar_v.request_id = "3egU9yarDfox0qmzHFROPvNndhCSpVIX";
		tar_v.error_code = 0;
		tar_v.adslot_id = "J6q9JOsxp";
		
		List<MaterialMeta> material_metas = new ArrayList<MaterialMeta>();
		MaterialMeta materialMeta = new MaterialMeta();
		materialMeta.material_type=2;	// 物料类型 视频(1),图片(2)
		materialMeta.click_url="test"; // *点击行为地址，用户点击后，在客户端进行响应，会经过多次302跳转最终到达目标地址
		materialMeta.video_url="test"; // 广告视频物料地址
		materialMeta.video_duration=20; // 广告视频物料时长
		materialMeta.material_width=20; // 物料的宽度:如果是图片,表示图片的宽度;如果是视频(含有视频截图),则为视频宽度;如果是图文或文本,则不会填充此字段
		materialMeta.material_height=20; // 物料的高度:如果是图片,表示图片的高度;如果是视频(含有视频截图),则为视频高度;如果是图文或文本,则不会填充此字段
		materialMeta.material_size=20; // 图片、视频物料大小,单位kb
		materialMeta.material_md5="test";
		List<String> icon_src = new ArrayList<String>();
		List<String> image_src = new ArrayList<String>();
		icon_src.add("files/2018/08/14/b7cd25e6ly1fsdwnbssosj20j60eedh7.jpg");
		image_src.add("https://wx3.sinaimg.cn/mw690/b7cd25e6ly1fsdwnbssosj20j60eedh7.jpg");
		materialMeta.icon_src= icon_src;
		materialMeta.image_src= image_src;
				
		material_metas.add(materialMeta);
		
		List<Ad> ads = new ArrayList<Ad>();
		Ad ad = new Ad();
		ad.title="test";
		ad.brand_name="test";
		ad.description="test";
		ad.icon_url="test";
		ad.material_metas=material_metas;
		
		List<String> win_notice_url = new ArrayList<String>();
		List<String> third_monitor_url = new ArrayList<String>();
		win_notice_url.add("http://jpaccess.baidu.com/win_third?app_id=c03dd227&adslot_id=5844685&type=win&search_key=win_34737e5a8dfb7d12");
		third_monitor_url.add("http://jpaccess.baidu.com/win_third?app_id=c03dd227&adslot_id=5844685&type=third&search_key=third_34737e5a8dfb7d12");
		
		ad.win_notice_url=win_notice_url;
		ad.third_monitor_url=third_monitor_url;
		
		List<Tracking> ad_tracking = new ArrayList<Tracking>();
		Tracking tracking = new Tracking();
		tracking.tracking_event=null;
		tracking.tracking_url="test";
		ad_tracking.add(tracking);
		log.info("查看对象"+tracking);
		
//		ad.ad_tracking =ad_tracking;
		ad.ad_tracking =null;
		ad.ad_key="test";
		ads.add(ad);
		log.info("查看对象"+ad);
		
		tar_v.ads =ads;
		tar_v.expiration_time = 30;
		tar_v.search_key = "test";
		tar_v.jp_adlogo = "test";
		tar_v.jp_adtext = "test";
		log.info("查看对象"+tar_v);
		
		Juping.TsApiResponse ss=TsApiRTBProtocol.clientPack(tar_v);
		InputStream byteInputStream = new ByteArrayInputStream(ss.toByteArray());
		log.info("查看输入流"+byteInputStream);
		
		response.reset(); // 必要地清除response中的缓存信息
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-disposition",  "attachment;");
		 javax.servlet.ServletOutputStream out = response.getOutputStream();
         byte[] content = new byte[1024];
         int length = 0;
         while ((length = byteInputStream.read(content)) != -1) {
             out.write(content, 0, length);
         }
         out.flush();
         log.info("查看输出流"+out);
         
         out.close();
         byteInputStream.close();
		  
	}

}
