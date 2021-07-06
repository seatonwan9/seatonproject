package com.xearth.sp.seatonproject.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *全局异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 日志记录工具
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 400);
        map.put("rspMsg", e.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 400);
        map.put("rspMsg", e.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }
    /**
     * 400 - Bad Request
     * 方法中对象
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        StringBuilder stringBuilder = getExceptionResult(result);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 400);
        map.put("rspMsg", stringBuilder);
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, Object> handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        Map<String, Object> map = new HashMap<String, Object>();
        BindingResult result = e.getBindingResult();
        StringBuilder stringBuilder = getExceptionResult(result);
        map.put("rspCode", 400);
        map.put("rspMsg",stringBuilder);
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    private StringBuilder getExceptionResult(BindingResult result) {
        StringBuilder stringBuilder = new StringBuilder();
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                stringBuilder.append(fieldError.getField() + "->" + fieldError.getDefaultMessage() + ";");
            });
        }
        return stringBuilder;
    }


    /**
     * 400 - Bad Request
     * 方法中单个字段
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        String message = e.getConstraintName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 400);
        map.put("rspMsg", message);
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, Object> handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 400);
        map.put("rspMsg", e.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 405);
        map.put("rspMsg", e.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Map<String, Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("不支持当前媒体类型", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 415);
        map.put("rspMsg", e.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }


    /**
     * 获取其它异常。包括500
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> defaultErrorHandler(Exception e) {
        logger.error("Exception", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rspCode", 500);
        //判断异常源是否为空
        if (e.getClass().getTypeName().equals("org.springframework.dao.DataIntegrityViolationException")) {
            int errorCode = ((org.hibernate.exception.ConstraintViolationException) e.getCause()).getErrorCode();
            switch (errorCode) {
                case 1451:
                    map.put("rspMsg", "存在相关联的数据，删除失败！");
                    break;
                default:
                    map.put("rspMsg", e.getMessage());
                    break;
            }
        } else {
            map.put("rspMsg", e.getMessage());
        }
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }
}
