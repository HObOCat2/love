/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.love.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * loveEntity
 * @author hobocat
 * @version 2018-10-17
 */
@Table(name="jokedirec", alias="a", columns={
		@Column(name="direc_id", attrName="direcId", label="direc_id", isPK=true),
		@Column(name="direc_name", attrName="direcName", label="direc_name", queryType=QueryType.LIKE),
		@Column(name="sort_num", attrName="sortNum", label="sort_num"),
		@Column(name="big_id", attrName="bigId", label="big_id"),
		@Column(name="big_name", attrName="bigName", label="big_name", queryType=QueryType.LIKE),
	}, orderBy="a.direc_id DESC"
)
public class LoveJokedirec extends DataEntity<LoveJokedirec> {
	
	private static final long serialVersionUID = 1L;
	private String direcId;		// direc_id
	private String direcName;		// direc_name
	private Long sortNum;		// sort_num
	private String bigId;		// big_id
	private String bigName;		// big_name
	
	public LoveJokedirec() {
		this(null);
	}

	public LoveJokedirec(String id){
		super(id);
	}
	
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
	
	public Long getSortNum() {
		return sortNum;
	}

	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=32, message="big_id长度不能超过 32 个字符")
	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}
	
	@Length(min=0, max=255, message="big_name长度不能超过 255 个字符")
	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	
}