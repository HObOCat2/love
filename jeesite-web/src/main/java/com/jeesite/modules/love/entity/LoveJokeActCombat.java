/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * loveEntity
 * @author hobocat
 * @version 2018-10-17
 */
@Table(name="joke_act_combat", alias="a", columns={
		@Column(name="joke_name", attrName="jokeName", label="joke_name", queryType=QueryType.LIKE),
		@Column(name="joke_sort", attrName="jokeSort", label="joke_sort"),
		@Column(name="img_url", attrName="imgUrl", label="img_url"),
		@Column(name="joke_type", attrName="jokeType", label="joke_type"),
		@Column(name="joke_desc", attrName="jokeDesc", label="joke_desc", queryType=QueryType.LIKE),
		@Column(name="joke_content", attrName="jokeContent", label="joke_content", queryType=QueryType.LIKE),
		@Column(name="joke_id", attrName="jokeId", label="joke_id", isPK=true),
		@Column(name="time_str", attrName="timeStr", label="time_str"),
		@Column(name="update_time", attrName="updateTime", label="update_time"),
	}, orderBy="a.joke_id DESC"
)
public class LoveJokeActCombat extends DataEntity<LoveJokeActCombat> {
	
	private static final long serialVersionUID = 1L;
	private String jokeName;		// joke_name
	private Long jokeSort;		// joke_sort
	private String imgUrl;		// img_url
	private String jokeType;		// joke_type
	private String jokeDesc;		// joke_desc
	private String jokeContent;		// joke_content
	private String jokeId;		// joke_id
	private Date timeStr;		// time_str
	private Date updateTime;		// update_time
	
	public LoveJokeActCombat() {
		this(null);
	}

	public LoveJokeActCombat(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="joke_name长度不能超过 255 个字符")
	public String getJokeName() {
		return jokeName;
	}

	public void setJokeName(String jokeName) {
		this.jokeName = jokeName;
	}
	
	public Long getJokeSort() {
		return jokeSort;
	}

	public void setJokeSort(Long jokeSort) {
		this.jokeSort = jokeSort;
	}
	
	@Length(min=0, max=1000, message="img_url长度不能超过 1000 个字符")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Length(min=0, max=11, message="joke_type长度不能超过 11 个字符")
	public String getJokeType() {
		return jokeType;
	}

	public void setJokeType(String jokeType) {
		this.jokeType = jokeType;
	}
	
	@Length(min=0, max=255, message="joke_desc长度不能超过 255 个字符")
	public String getJokeDesc() {
		return jokeDesc;
	}

	public void setJokeDesc(String jokeDesc) {
		this.jokeDesc = jokeDesc;
	}
	
	@Length(min=0, max=2000, message="joke_content长度不能超过 2000 个字符")
	public String getJokeContent() {
		return jokeContent;
	}

	public void setJokeContent(String jokeContent) {
		this.jokeContent = jokeContent;
	}
	
	public String getJokeId() {
		return jokeId;
	}

	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(Date timeStr) {
		this.timeStr = timeStr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}