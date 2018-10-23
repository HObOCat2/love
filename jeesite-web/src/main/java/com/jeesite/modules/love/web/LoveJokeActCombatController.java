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
import com.jeesite.modules.love.entity.LoveJokeActCombat;
import com.jeesite.modules.love.service.LoveJokeActCombatService;

/**
 * loveController
 * @author hobocat
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/love/loveJokeActCombat")
public class LoveJokeActCombatController extends BaseController {

	@Autowired
	private LoveJokeActCombatService loveJokeActCombatService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public LoveJokeActCombat get(String jokeId, boolean isNewRecord) {
		return loveJokeActCombatService.get(jokeId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("love:loveJokeActCombat:view")
	@RequestMapping(value = {"list", ""})
	public String list(LoveJokeActCombat loveJokeActCombat, Model model) {
		model.addAttribute("loveJokeActCombat", loveJokeActCombat);
		return "modules/love/loveJokeActCombatList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("love:loveJokeActCombat:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<LoveJokeActCombat> listData(LoveJokeActCombat loveJokeActCombat, HttpServletRequest request, HttpServletResponse response) {
		loveJokeActCombat.setPage(new Page<>(request, response));
		Page<LoveJokeActCombat> page = loveJokeActCombatService.findPage(loveJokeActCombat); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("love:loveJokeActCombat:view")
	@RequestMapping(value = "form")
	public String form(LoveJokeActCombat loveJokeActCombat, Model model) {
		model.addAttribute("loveJokeActCombat", loveJokeActCombat);
		return "modules/love/loveJokeActCombatForm";
	}

	/**
	 * 保存love
	 */
	@RequiresPermissions("love:loveJokeActCombat:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated LoveJokeActCombat loveJokeActCombat) {
		loveJokeActCombatService.save(loveJokeActCombat);
		return renderResult(Global.TRUE, text("保存love成功！"));
	}
	
	/**
	 * 停用love
	 */
	@RequiresPermissions("love:loveJokeActCombat:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(LoveJokeActCombat loveJokeActCombat) {
		loveJokeActCombat.setStatus(LoveJokeActCombat.STATUS_DISABLE);
		loveJokeActCombatService.updateStatus(loveJokeActCombat);
		return renderResult(Global.TRUE, text("停用love成功"));
	}
	
	/**
	 * 启用love
	 */
	@RequiresPermissions("love:loveJokeActCombat:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(LoveJokeActCombat loveJokeActCombat) {
		loveJokeActCombat.setStatus(LoveJokeActCombat.STATUS_NORMAL);
		loveJokeActCombatService.updateStatus(loveJokeActCombat);
		return renderResult(Global.TRUE, text("启用love成功"));
	}
	
	/**
	 * 删除love
	 */
	@RequiresPermissions("love:loveJokeActCombat:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(LoveJokeActCombat loveJokeActCombat) {
		loveJokeActCombatService.delete(loveJokeActCombat);
		return renderResult(Global.TRUE, text("删除love成功！"));
	}
	
}