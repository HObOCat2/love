/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.mybatis.mapper.SqlMap;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.love.entity.LoveJokeActCombat;
import com.jeesite.modules.love.dao.LoveJokeActCombatDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * loveService
 * @author hobocat
 * @version 2018-10-17
 */
@Service
@Transactional(readOnly=true)
public class LoveJokeActCombatService extends CrudService<LoveJokeActCombatDao, LoveJokeActCombat> {
	
	/**
	 * 获取单条数据
	 * @param loveJokeActCombat
	 * @return
	 */
	@Override
	public LoveJokeActCombat get(LoveJokeActCombat loveJokeActCombat) {
		return super.get(loveJokeActCombat);
	}
	
	/**
	 * 查询分页数据
	 * @param loveJokeActCombat 查询条件
	 * @param loveJokeActCombat.page 分页对象
	 * @return
	 */
	@Override
	public Page<LoveJokeActCombat> findPage(LoveJokeActCombat loveJokeActCombat) {
		return super.findPage(loveJokeActCombat);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param loveJokeActCombat
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(LoveJokeActCombat loveJokeActCombat) {
		super.save(loveJokeActCombat);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(loveJokeActCombat.getId(), "loveJokeActCombat_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(loveJokeActCombat.getId(), "loveJokeActCombat_file");
	}
	
	/**
	 * 更新状态
	 * @param loveJokeActCombat
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(LoveJokeActCombat loveJokeActCombat) {
		super.updateStatus(loveJokeActCombat);
	}
	
	/**
	 * 删除数据
	 * @param loveJokeActCombat
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(LoveJokeActCombat loveJokeActCombat) {
		super.delete(loveJokeActCombat);
	}
	
	
	@Override
	public List<LoveJokeActCombat> findList(LoveJokeActCombat entity) {
		// TODO Auto-generated method stub
		
		return super.findList(entity);
	}
	
}