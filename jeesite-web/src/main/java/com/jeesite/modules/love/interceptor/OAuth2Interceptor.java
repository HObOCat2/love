package com.jeesite.modules.love.interceptor;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description jeesite
 *
 * @author 40938
 * @history
 * 			<TABLE id="HistoryTable" border="1">
 *          <TR>
 *          <TD>时间</TD>
 *          <TD>描述</TD>
 *          <TD>作者</TD>
 *          </TR>
 *          <TR>
 *          <TD>2017年7月29日</TD>
 *          <TD>version2.0</TD>
 *          <TD>40938</TD>
 *          </TR>
 *          </TABLE>
 */
@Component
public class OAuth2Interceptor implements HandlerInterceptor {

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("**执行顺序: 3、afterCompletion**");
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作(手机端视图拦截器)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2,
			ModelAndView modelAndView) throws Exception {
		System.out.println("**执行顺序: 2、postHandle**");
	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("**执行顺序: 1、preHandle**");
		
		HttpSession session = request.getSession();
		session.setAttribute("openId", "ovyePwY69X6sr1z4amg6ulh4kIXA");
		Object objUid = session.getAttribute("openId");
		return true;
	}

}

/**
 * Copyright © 2017,傲视幻科 All rights reserved.
 */