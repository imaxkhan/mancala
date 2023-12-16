package com.bol.mancala.base.config.oop;


import com.bol.mancala.base.exception.CustomException;

/**
 * lovely method that is being handled by AOP and is being used
 * for dynamic validation at DTO level
 */
public interface IValidation {
    void validate() throws CustomException;
}
