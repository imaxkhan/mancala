package com.bol.mancala.base.config.aop;


import com.bol.mancala.base.config.oop.IValidation;
import com.bol.mancala.base.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestControllerAdvice * )")
    public void controllerAdvice() {
        /*
        This method contains controller Advice
         */
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController * || @org.springframework.stereotype.Controller *)")
    public void controller() {
        /*
        This method contains all controller class methods
         */
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
        /*
        This method contains all service class methods
         */
    }

    @Pointcut("execution(public * *(..))")
    public void allMethod() {
        /*
        This method contains all methods
         */
    }

    @Before(value = "controller()")
    public void validate(JoinPoint joinPoint) throws CustomException {
        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            if (signatureArg instanceof IValidation) {
                ((IValidation) signatureArg).validate();
            }
        }
    }
}
