/**
 * 2014年12月5日 上午11:03:02
 */
package com.jeesite.modules.love.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @author anxlng
 *
 */
@Component
public class AppLoginInterceptor extends HandlerInterceptorAdapter {

	final Logger logger = Logger.getLogger(getClass());


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.debug("request validate...");
		return true;
//		String reqName = request.getParameter(RequestKey.MESSAGE_NAME.getValue());
//		logger.debug("reqName:-----------------" + reqName);
//		ReqMessage req = ReqMessage.getInstance(reqName);
//		if (req == null) {
//			// 1. 可以跳转到错误提示需要登陆的处理中
//			// 2. 当然这里抛出异常也是可以的， 抛出的异常建议 继承 CoreServiceException
//			throw new WebServiceException("request message name[" + reqName + "] is null!");
//		}
//		String mdStr = request.getParameter(RequestKey.APP_VERSION.getValue())
//				+ request.getParameter(RequestKey.APP_SYSTEM.getValue())
//				+ request.getParameter(RequestKey.APP_MODEL.getValue())
//				+ request.getParameter(RequestKey.OS_VERSION.getValue())
//				+ request.getParameter(RequestKey.DEVICE_ID.getValue())
//				+ request.getParameter(RequestKey.APP_POS.getValue())
//				+ request.getParameter(RequestKey.POS_TIME.getValue())
//				+ request.getParameter(RequestKey.IS_POS.getValue())
//				+ request.getParameter(RequestKey.USER_TOKEN.getValue())
//				+ request.getParameter(RequestKey.POS_MODE.getValue())
//				+ request.getParameter(RequestKey.REQ_NUM.getValue())
//				+ request.getParameter(RequestKey.MESSAGE_NAME.getValue())
//				+ request.getParameter(RequestKey.PHARMACY_ID.getValue());
//		MD5 md5 = new MD5();
//		int version = parseVersion(request.getParameter(RequestKey.APP_VERSION.getValue()));
//		if (version >= 202) {
//			// MD5校验
//			String md5code = md5.GetMD5Code(mdStr + ConfigManager.getConfig("md_key"));
//			String a = request.getParameter(RequestKey.MD5_CODE.getValue());
//			logger.debug("a:-----------------" + a);
//			logger.debug("md5code:-----------------" + md5code);
//			if (!"version_key".equals(request.getParameter(RequestKey.MESSAGE_NAME.getValue()))) {
//				if (!md5code.equals(a)) {
//					throw new CoreServiceException(CoreServiceExcepType.MD5_CODE, "****** is not success");
//				}
//			}
//		}
//
//		// 公共请求参数 bean
//
//		// 这里判断 reqName 是否具有权限
//		boolean isPass = validateReqName(request, req);
//		if (isPass) {
//			logger.debug("request[message_name=" + reqName + " ] validate succeed...");
//		} else {
//			logger.debug("request[message_name=" + reqName + " ] validate falied...");
//		}
//		return isPass;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*
		 * logger.debug("post handle"); if (modelAndView != null) { logger.warn(
		 * "modelAndView not null:" + modelAndView.isReference()); } if (handler
		 * != null) { logger.warn(handler.getClass()); }
		 */
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}




}
