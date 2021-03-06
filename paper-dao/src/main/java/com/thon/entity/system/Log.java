package com.thon.entity.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thon.commons.persistence.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

/**
 * 日志Entity
 * @author thon
 * @date Dec 31, 2013 4:07:31 PM
 */
@Entity
@Table(name = "sys_log")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Log extends IdEntity {

	private String type; 		// 日志类型（1：接入日志；2：错误日志）
	private User createBy;		// 创建者
	private Date createDate;	// 日志创建时间
	private String remoteAddr; 	// 操作用户的IP地址
	private String requestUri; 	// 操作的URI
	private String method; 		// 操作的方式
	private String params; 		// 操作提交的数据
	private String userAgent;	// 操作用户代理信息
	private String exception; 	// 异常信息
	
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	
	public Log(){
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}