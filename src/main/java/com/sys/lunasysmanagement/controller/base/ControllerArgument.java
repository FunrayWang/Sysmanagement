package com.sys.lunasysmanagement.controller.base;

import com.sys.lunasysmanagement.constant.ResponseCode;
import com.sys.lunasysmanagement.constant.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * AOP实现参数统一校验.
 *
 * @author wangfangrui
 * @date 2019/8/29 9:40
 */
@Component
@Aspect
@Slf4j
public class ControllerArgument {


    @Pointcut("execution(public * com.sys.lunasysmanagement.controller.*.*(..))\")")
    public void aopMethod() {
    }

    /**
     * 字段校验统一入口
     */
    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        BindingResult bindingResult = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                log.info("-------------------------------参数异常捕获--------------------------------");
                String errorInfo = "";
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors) {
                    errorInfo = errorInfo + error.getDefaultMessage() + ";";
                }
                return Result.fail(ResponseCode.ERROR_PARAM.getCode(), errorInfo, null);
            }
        }
        return joinPoint.proceed();

    }
}


