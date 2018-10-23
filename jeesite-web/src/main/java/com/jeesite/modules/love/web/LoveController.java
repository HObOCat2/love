/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeesite.common.cache.CacheUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.love.entity.Code2Session;
import com.jeesite.modules.love.entity.LoveJoke;
import com.jeesite.modules.love.entity.LoveJokeActCombat;
import com.jeesite.modules.love.entity.LoveJokedirec;
import com.jeesite.modules.love.service.LoveJokeActCombatService;
import com.jeesite.modules.love.service.LoveJokeService;
import com.jeesite.modules.love.service.LoveJokedirecService;
import com.jeesite.modules.love.utils.RestTemplateFactory;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * loveController
 * @author hobocat
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${frontPath}/app/love")
public class LoveController extends BaseController {

	private static String appId = Global.getConfig("WXminiApp.AppID");
	
	private static String appSecret = Global.getConfig("WXminiApp.AppSecret");	
	
	@Autowired
	private LoveJokeService loveJokeService;
	
	@Autowired
	private LoveJokeActCombatService loveJokeActCombatService;
	
	@Autowired
	private LoveJokedirecService loveJokedirecService;
	
	
	
	/**
	 * 查询所有分类
	 */
	@RequiresGuest
	@RequestMapping(value = "listDirec")
	@ResponseBody
	public Object listDirec( HttpServletRequest request, HttpServletResponse response) {
		String port = Global.getConfig("server.port");	
		
		List<LoveJokedirec> dirList = loveJokedirecService.findList(new LoveJokedirec());
		return dirList;
		
	}
	
	/**
	 * 查询聊天记录
	 */
	@RequiresGuest
	@RequestMapping(value = "getJoke")
	@ResponseBody
	public Object listDirec(String type, String k, String sid, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		
		
		if (StringUtils.isNotEmpty(type)) {
			if (Integer.valueOf(type) <= 2 ) {
				LoveJokeActCombat combat = new LoveJokeActCombat();
				combat.setJokeType(type);
				List<LoveJokeActCombat> list = loveJokeActCombatService.findList(combat);
				return list;
			} else if ("3".equals(type)) {
				//关键词搜索
				List<LoveJoke> list = loveJokeService.findByKey(k);
				return list;
			} else if("4".equals(type)){
				//分类搜索
				List<LoveJoke> list = loveJokeService.findByDirecId(k);
				return list;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 查询所有分类
	 */
	@RequestMapping(value = "getOpenIdInfo")
	@ResponseBody
	public Object getOpenIdInfo(String code, HttpServletRequest request, HttpServletResponse response) {
		
		if (StringUtils.isNotBlank(code)) {
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + 
						 "&secret= "+ appSecret +"&js_code="+ code +
						 "&grant_type=authorization_code";
			String content = RestTemplateFactory
					.getResponse(RestTemplateFactory.generateGet(url, new HashMap<String, String>()));
			ObjectMapper mapper = new ObjectMapper();
			Code2Session c2s = null;
			try {
				c2s = mapper.readValue(content, Code2Session.class);
				logger.debug(">>>>Code2Session info=" + c2s.toString());
				if (c2s.isSucc()) {
					//存储
					
				}
				
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
}