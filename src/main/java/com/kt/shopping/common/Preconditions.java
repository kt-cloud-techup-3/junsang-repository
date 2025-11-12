package com.kt.shopping.common;

import com.kt.shopping.common.api.CustomException;
import com.kt.shopping.common.api.ErrorCode;

public class Preconditions {
    public static void validate(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new CustomException(errorCode);
        }
    }
}
