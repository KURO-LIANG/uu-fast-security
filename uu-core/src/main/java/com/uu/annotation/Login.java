/**
 * Copyright (c) 2016-2019 炫酷游 All rights reserved.
 *
 * https://www.xkygame.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 *
 * @author KURO clarence_liang@163.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
