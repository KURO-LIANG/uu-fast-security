package com.uu.modules.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：
 * @Date: 2021/6/7
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo {

    private String requestIp;

    private String deviceInfo;

}
