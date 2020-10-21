package com.autumn.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Proxy;
import java.net.http.HttpRequest;

@ControllerAdvice
public class controllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截所有的Exception
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception{
        //指定日志中的记录格式
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(),e.getMessage());
        //若exception存在responsestatus的注解则直接抛出让Spring框架来处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
            throw e;
        }
        //MVC结构中的viewer部分，负责渲染html页面呈现给用户
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
