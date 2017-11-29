package com.wangzhihao.blackmarket.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Component
public class WechatSessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(WechatSessionFilter.class);

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-User-Session-Key");
        logger.info("X-User-Session-Key:{}", token);
        chain.doFilter(req, response);
    }
}
