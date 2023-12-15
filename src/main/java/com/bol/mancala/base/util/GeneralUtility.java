package com.bol.mancala.base.util;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomErrorResult;

import java.util.List;

public class GeneralUtility {

    public static void validateProperty(int actual, int expected, String propertyName, List<CustomErrorResult> errorResults) {
        if (actual != expected) {
            errorResults.add(new CustomErrorResult(CustomErrorCode.VALIDATION_FAILED,
                    propertyName + " must be " + expected, propertyName));
        }
    }
}
