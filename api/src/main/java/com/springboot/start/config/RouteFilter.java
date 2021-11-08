package com.springboot.start.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author FireWang
 * @date 2021/11/08
 * 过滤器，用于过滤请求的url，判断用户登录状态
 */
@Component
@Slf4j
public class RouteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            //取出用户缓存做登录状态校验,校验不通过则直接跳转登录页
            //HttpSession session = request.getSession();
            //UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");

            /**
             * 示例页面路径：/DMIL/login/index.html
             * request.getRequestURI()返回：/DMIL/login/index.html
             * request.getContextPath()返回：/DMIL
             * request.getServletPath()返回：/login/index.html
             */

            //过滤器白名单，白名单内的请求可直接访问
            if (dofiltration(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            log.info("当前会话请求url：" + request.getRequestURI());

            //过滤后，对访问连接进行处理
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.info("过滤器异常：" + e.getMessage());
        }
    }

    /**
     * ajax请求超时处理
     *
     * @param response
     */
    private void setTimeOut(HttpServletResponse response) {
        try {
            JSONObject json = new JSONObject();
            json.put("obj1", "登录超时，请重新登录！");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.write(json.toString());
        } catch (Exception e) {
            log.info("过滤器异常：" + e.getMessage());
            e.printStackTrace();
            response.setHeader("sessionStatus", "timeout");
        }
    }

    /**
     * 接口访问跳过过滤规则
     *
     * @param request
     */
    private boolean dofiltration(HttpServletRequest request) {
        /**
         * 示例页面路径：/springboot-start/login/index.html
         * request.getRequestURI()返回：/springboot-start/login/index.html
         * request.getContextPath()返回：/springboot-start
         * request.getServletPath()返回：/login/index.html
         */
        try {
            //跳过静态资源访问
            String staticpath = request.getContextPath() + "/static";

            //登录页面跳过过滤规则
            String login = "/springboot-start/login", dologin = "/springboot-start/dologin", dologout = "/springboot-start/dologout";

            //接口访问跳过过滤规则--WebService接口
            String webService = "/WebService/";

            if (request.getRequestURI().startsWith(staticpath) ||
                    request.getRequestURI().equals(login) ||
                    request.getRequestURI().equals(dologin) ||
                    request.getRequestURI().equals(dologout) ||
                    request.getRequestURI().contains(webService)) {
                return true;
            }
        } catch (Exception e) {
            log.info("过滤器异常：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }

}

