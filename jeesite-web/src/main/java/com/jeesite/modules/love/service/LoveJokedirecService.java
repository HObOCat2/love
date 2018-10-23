/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.love.entity.LoveJokedirec;
import com.jeesite.modules.love.dao.LoveJokedirecDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * loveService
 * @author hobocat
 * @version 2018-10-17
 */
@Service
@Transactional(readOnly=true)
public class LoveJokedirecService extends CrudService<LoveJokedirecDao, LoveJokedirec> {
	
	/**
	 * 获取单条数据
	 * @param loveJokedirec
	 * @return
	 */
	@Override
	public LoveJokedirec get(LoveJokedirec loveJokedirec) {
		return super.get(loveJokedirec);
	}
	
	/**
	 * 查询分页数据
	 * @param loveJokedirec 查询条件
	 * @param loveJokedirec.page 分页对象
	 * @return
	 */
	@Override
	public Page<LoveJokedirec> findPage(LoveJokedirec loveJokedirec) {
		return super.findPage(loveJokedirec);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param loveJokedirec
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(LoveJokedirec loveJokedirec) {
		super.save(loveJokedirec);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(loveJokedirec.getId(), "loveJokedirec_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(loveJokedirec.getId(), "loveJokedirec_file");
	}
	
	/**
	 * 更新状态
	 * @param loveJokedirec
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(LoveJokedirec loveJokedirec) {
		super.updateStatus(loveJokedirec);
	}
	
	/**
	 * 删除数据
	 * @param loveJokedirec
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(LoveJokedirec loveJokedirec) {
		super.delete(loveJokedirec);
	}
	
}