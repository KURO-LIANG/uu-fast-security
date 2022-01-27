/**
 * Copyright (c) 2016-2019 炫酷游 All rights reserved.
 *
 * https://www.xkygame.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.validator;

import com.uu.exception.SCException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author KURO clarence_liang@163.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SCException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SCException(message);
        }
    }
}
