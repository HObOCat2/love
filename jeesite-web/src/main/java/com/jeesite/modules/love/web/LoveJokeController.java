/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.love.entity.LoveJoke;
import com.jeesite.modules.love.service.LoveJokeService;

/**
 * loveController
 * @author hobocat
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/love/loveJoke")
public class LoveJokeController extends BaseController {

	@Autowired
	private LoveJokeService loveJokeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public LoveJoke get(String jokeId, boolean isNewRecord) {
		return loveJokeService.get(jokeId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("love:loveJoke:view")
	@RequestMapping(value = {"list", ""})
	public String list(LoveJoke loveJoke, Model model) {
		model.addAttribute("loveJoke", loveJoke);
		return "modules/love/loveJokeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("love:loveJoke:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<LoveJoke> listData(LoveJoke loveJoke, HttpServletRequest request, HttpServletResponse response) {
		loveJoke.setPage(new Page<>(request, response));
		Page<LoveJoke> page = loveJokeService.findPage(loveJoke); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("love:loveJoke:view")
	@RequestMapping(value = "form")
	public String form(LoveJoke loveJoke, Model model) {
		model.addAttribute("loveJoke", loveJoke);
		return "modules/love/loveJokeForm";
	}

	/**
	 * 保存love
	 */
	@RequiresPermissions("love:loveJoke:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated LoveJoke loveJoke) {
		loveJokeService.save(loveJoke);
		return renderResult(Global.TRUE, text("保存love成功！"));
	}
	
	/**
	 * 停用love
	 */
	@RequiresPermissions("love:loveJoke:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(LoveJoke loveJoke) {
		loveJoke.setStatus(LoveJoke.STATUS_DISABLE);
		loveJokeService.updateStatus(loveJoke);
		return renderResult(Global.TRUE, text("停用love成功"));
	}
	
	/**
	 * 启用love
	 */
	@RequiresPermissions("love:loveJoke:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(LoveJoke loveJoke) {
		loveJoke.setStatus(LoveJoke.STATUS_NORMAL);
		loveJokeService.updateStatus(loveJoke);
		return renderResult(Global.TRUE, text("启用love成功"));
	}
	
	/**
	 * 删除love
	 */
	@RequiresPermissions("love:loveJoke:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(LoveJoke loveJoke) {
		loveJokeService.delete(loveJoke);
		return renderResult(Global.TRUE, text("删除love成功！"));
	}
	
}