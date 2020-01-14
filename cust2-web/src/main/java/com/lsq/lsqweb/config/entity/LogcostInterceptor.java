package com.lsq.lsqweb.config.entity;

import com.lsq.lsqweb.util.HttpUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LogcostInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();
    //preHandle是在请求执行前执行的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        System.out.println("被拦截了");

        HttpSession session = request.getSession();
        if (session != null && "login".equals(session.getAttribute("login"))){
            return true;
        }

        // 检查token
        String token = request.getParameter("token");
        System.out.println(token);
        if (!StringUtils.isEmpty(token)){
            String reqUrl = "http://127.0.0.1:8090/checkToken";
            String content = "token=" + token;
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("token",token);
            String result = HttpUtil.doPost(reqUrl,paramMap);
            System.out.println("调用结果为:"+result);
        }

        return false;
        //返回true,postHandler和afterCompletion方法才能执行
        // 否则false为拒绝执行，起到拦截器控制作用
    }

    //postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interception cost="+(System.currentTimeMillis()-start));
    }

    //afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true，
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //该方法通常用于清理资源等工作
    }
}
