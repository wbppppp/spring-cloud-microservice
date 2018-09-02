package com.itmuch.cloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/8/21 0021.
 */
public class MyZuulFilter extends ZuulFilter {

    Logger LOGGER = LoggerFactory.getLogger(MyZuulFilter.class);

    //过滤器类型:pre route post error
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    //是否启用该过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器处理逻辑
    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();

        LOGGER.info("请求uri-->{},url-->{}",uri,url);

        String token = request.getParameter("token");

        //根据请求中是否携带token来判断是否对请求进行路由
        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            return null;
        } else {
            try {
                doSomething();
            }catch(Exception e){
//                error.status_code和error_exception必循有，
//                异常处理过滤器要通过判断是否有error.status_code这个key来判断是否启用该过滤器
//                error.message可有可无，没值的就直接使用捕捉到的异常信息
                ctx.set("error.status_code",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ctx.set("error.exception", e);
                ctx.set("error.message", "系统繁忙");
            }

            //假设没有上面的try-catch,最终会直接返回个页面token is empty
            //使用上面的异常捕捉，并设置error.status_code等就会经过异常处理过滤器
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            return null;
        }



    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}
