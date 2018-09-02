package com.itmuch.cloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorFilter extends ZuulFilter {

    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";

    private Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";

    @Override
    public String filterType() {
        return "post";
    }

    /**
     * zuul中提供了一个默认的异常处理的过滤器SendErrorFilter
     *
     *      它的order是0，该过滤器主要是对捕捉异常后的做请求
     *
     *      转发，转发到/error的请求。
     *
     *      故而，我们自定义的异常应该在SendErrorFilter执行
     *
     *      之前执行，即order < 0
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return -1;
    }

    /**
     * 该方法表示是否启用该过滤器，这里也是参考SendErrorFilter进行设置的
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey(ERROR_STATUS_CODE_KEY);
    }

    /**
     * 执行的逻辑
     *
     * @return
     */
    @Override
    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//
//        try {
//            HttpServletRequest request = ctx.getRequest();
//
//            int statusCode = (Integer) ctx.get(ERROR_STATUS_CODE_KEY);
//            String message = (String) ctx.get("error.message");
//            if (ctx.containsKey("error.exception")) {
//                Throwable e = (Exception) ctx.get("error.exception");
//                Throwable re = getOriginException(e);
//                if(re instanceof java.net.ConnectException){
//                    message = "Real Service Connection refused";
//                    log.warn("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
//                }else if(re instanceof java.net.SocketTimeoutException){
//                    message = "Real Service Timeout";
//                    log.warn("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
//                }else if(re instanceof com.netflix.client.ClientException){
//                    message = re.getMessage();
//                    log.warn("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
//                }else{
//                    log.warn("Error during filtering",e);
//                }
//            }
//
//            if(StringUtils.isBlank(message))message = DEFAULT_ERR_MSG;
//
//            request.setAttribute("javax.servlet.error.status_code", statusCode);
//            request.setAttribute("javax.servlet.error.message", message);
//
//            ctx.setResponseBody(message);
//            ctx.setResponseStatusCode(statusCode);
//
//        } catch (Exception e) {
//            String error = "Error during filtering[ErrorFilter]";
//            log.error(error,e);
//            ctx.setResponseBody(error);
//            ctx.setResponseStatusCode(500);
//        }

        RequestContext ctx= RequestContext.getCurrentContext();
        Throwable throwable= (Exception) ctx.get("error.exception");

        log.error("this is a ErrorFilter : {} ", throwable.getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable);
        ctx.set("error.message",DEFAULT_ERR_MSG);

        return null;

    }

    private Throwable getOriginException(Throwable e){
        e = e.getCause();
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }
}