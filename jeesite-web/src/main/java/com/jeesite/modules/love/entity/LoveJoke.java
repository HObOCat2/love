/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * loveEntity
 * @author hobocat
 * @version 2018-10-17
 */
@Table(name="joke", alias="a", columns={
		@Column(name="joke_name", attrName="jokeName", label="joke_name", queryType=QueryType.LIKE),
		@Column(name="direc_id", attrName="direcId", label="direc_id"),
		@Column(name="direc_name", attrName="direcName", label="direc_name", queryType=QueryType.LIKE),
		@Column(name="joke_type", attrName="jokeType", label="joke_type"),
		@Column(name="big_name", attrName="bigName", label="big_name", queryType=QueryType.LIKE),
		@Column(name="big_id", attrName="bigId", label="big_id"),
		@Column(name="joke_content", attrName="jokeContent", label="joke_content", queryType=QueryType.LIKE),
		@Column(name="joke_id", attrName="jokeId", label="joke_id", isPK=true),
		@Column(name="use_flag", attrName="useFlag", label="use_flag"),
		@Column(name="joke_sort", attrName="jokeSort", label="joke_sort"),
	}, orderBy="a.joke_id DESC"
)
public class LoveJoke extends DataEntity<LoveJoke> {
	
	private static final long serialVersionUID = 1L;
	private String jokeName;		// joke_name
	private String direcId;		// direc_id
	private String direcName;		// direc_name
	private String jokeType;		// joke_type
	private String bigName;		// big_name
	private String bigId;		// big_id
	private String jokeContent;		// joke_content
	private String jokeId;		// joke_id
	private String useFlag;		// use_flag
	private Long jokeSort;		// joke_sort
	
	public LoveJoke() {
		this(null);
	}

	public LoveJoke(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="joke_name长度不能超过 255 个字符")
	public String getJokeName() {
		return jokeName;
	}

	public void setJokeName(String jokeName) {
		this.jokeName = jokeName;
	}
	
	@NotBlank(message="direc_id不能为空")
	@Length(min=0, max=32, message="direc_id长度不能超过 32 个字符")
	public String getDirecId() {
		return direcId;
	}

	public void setDirecId(String direcId) {
		this.direcId = direcId;
	}
	
	@Length(min=0, max=255, message="direc_name长度不能超过 255 个字符")
	public String getDirecName() {
		return direcName;
	}

	public void setDirecName(String direcName) {
		this.direcName = direcName;
	}
	
	@NotBlank(message="joke_type不能为空")
	@Length(min=0, max=11, message="joke_type长度不能超过 11 个字符")
	public String getJokeType() {
		return jokeType;
	}

	public void setJokeType(String jokeType) {
		this.jokeType = jokeType;
	}
	
	@Length(min=0, max=255, message="big_name长度不能超过 255 个字符")
	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	
	@NotBlank(message="big_id不能为空")
	@Length(min=0, max=32, message="big_id长度不能超过 32 个字符")
	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
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
	
	@Length(min=0, max=11, message="use_flag长度不能超过 11 个字符")
	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	
	public Long getJokeSort() {
		return jokeSort;
	}

	public void setJokeSort(Long jokeSort) {
		this.jokeSort = jokeSort;
	}
	
}