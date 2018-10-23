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
import com.jeesite.modules.love.entity.LoveJokedirec;
import com.jeesite.modules.love.service.LoveJokedirecService;

/**
 * loveController
 * @author hobocat
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/love/loveJokedirec")
public class LoveJokedirecController extends BaseController {

	@Autowired
	private LoveJokedirecService loveJokedirecService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public LoveJokedirec get(String direcId, boolean isNewRecord) {
		return loveJokedirecService.get(direcId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("love:loveJokedirec:view")
	@RequestMapping(value = {"list", ""})
	public String list(LoveJokedirec loveJokedirec, Model model) {
		model.addAttribute("loveJokedirec", loveJokedirec);
		return "modules/love/loveJokedirecList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("love:loveJokedirec:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<LoveJokedirec> listData(LoveJokedirec loveJokedirec, HttpServletRequest request, HttpServletResponse response) {
		loveJokedirec.setPage(new Page<>(request, response));
		Page<LoveJokedirec> page = loveJokedirecService.findPage(loveJokedirec); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("love:loveJokedirec:view")
	@RequestMapping(value = "form")
	public String form(LoveJokedirec loveJokedirec, Model model) {
		model.addAttribute("loveJokedirec", loveJokedirec);
		return "modules/love/loveJokedirecForm";
	}

	/**
	 * 保存love
	 */
	@RequiresPermissions("love:loveJokedirec:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated LoveJokedirec loveJokedirec) {
		loveJokedirecService.save(loveJokedirec);
		return renderResult(Global.TRUE, text("保存love成功！"));
	}
	
	/**
	 * 停用love
	 */
	@RequiresPermissions("love:loveJokedirec:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(LoveJokedirec loveJokedirec) {
		loveJokedirec.setStatus(LoveJokedirec.STATUS_DISABLE);
		loveJokedirecService.updateStatus(loveJokedirec);
		return renderResult(Global.TRUE, text("停用love成功"));
	}
	
	/**
	 * 启用love
	 */
	@RequiresPermissions("love:loveJokedirec:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(LoveJokedirec loveJokedirec) {
		loveJokedirec.setStatus(LoveJokedirec.STATUS_NORMAL);
		loveJokedirecService.updateStatus(loveJokedirec);
		return renderResult(Global.TRUE, text("启用love成功"));
	}
	
	/**
	 * 删除love
	 */
	@RequiresPermissions("love:loveJokedirec:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(LoveJokedirec loveJokedirec) {
		loveJokedirecService.delete(loveJokedirec);
		return renderResult(Global.TRUE, text("删除love成功！"));
	}
	
}