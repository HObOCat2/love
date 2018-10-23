/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beust.jcommander.Parameter;
import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.love.entity.LoveJoke;

/**
 * loveDAO接口
 * @author hobocat
 * @version 2018-10-17
 */
@MyBatisDao(value="LoveJokeDao")
public interface LoveJokeDao extends CrudDao<LoveJoke> {
	
	
	public List<LoveJoke> findByKey(@Param(value = "k") String k);
	
	
	public List<LoveJoke> findByDirecId(@Param(value = "k")String dId);
	
	
}