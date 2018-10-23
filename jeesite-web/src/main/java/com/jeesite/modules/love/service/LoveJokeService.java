/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.service;

import java.util.List;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.dao.BaseDao;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.mybatis.mapper.SqlMap;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.love.entity.LoveJoke;
import com.jeesite.modules.love.dao.LoveJokeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * loveService
 * @author hobocat
 * @version 2018-10-17
 */
@Service
@Transactional(readOnly=true)
public class LoveJokeService extends CrudService<LoveJokeDao, LoveJoke> {
	
	@Autowired
	private LoveJokeDao jokeDao; 
	
	
	/**
	 * 获取单条数据
	 * @param loveJoke
	 * @return
	 */
	@Override
	public LoveJoke get(LoveJoke loveJoke) {
		return super.get(loveJoke);
	}
	
	/**
	 * 查询分页数据
	 * @param loveJoke 查询条件
	 * @param loveJoke.page 分页对象
	 * @return
	 */
	@Override
	public Page<LoveJoke> findPage(LoveJoke loveJoke) {
		return super.findPage(loveJoke);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param loveJoke
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(LoveJoke loveJoke) {
		super.save(loveJoke);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(loveJoke.getId(), "loveJoke_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(loveJoke.getId(), "loveJoke_file");
	}
	
	/**
	 * 更新状态
	 * @param loveJoke
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(LoveJoke loveJoke) {
		super.updateStatus(loveJoke);
	}
	
	/**
	 * 删除数据
	 * @param loveJoke
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(LoveJoke loveJoke) {
		super.delete(loveJoke);
	}

	
	public List<LoveJoke> findByKey(String k) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(k)) {
			return jokeDao.findByKey(k);
		}
		return null;
	}

	public List<LoveJoke> findByDirecId(String k) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(k)) {
			return jokeDao.findByDirecId(k);
		}
		return null;
	}
	
	
	
}