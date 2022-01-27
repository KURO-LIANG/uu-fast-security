package com.uu.constant;

import lombok.Getter;

/**
 * 系统参数相关Key
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public class SysConfigConstant {
    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    /**
     * 短信配置KEY
     */
    public final static String SMS_CONFIG_KEY = "SMS_CONFIG_KEY";
    /**
     * 微信小程序配置KEY
     */
    public final static String WE_CHAT_MINI_APP_CONFIG_KEY = "WE_CHAT_MINI_APP_CONFIG_KEY";
    /**
     * 微信小程序支付配置KEY
     */
    public final static String WE_CHAT_MINI_PAY_CONFIG_KEY = "WE_CHAT_MINI_PAY_CONFIG_KEY";
    /**
     * 微信公众号支付配置KEY
     */
    public final static String WE_CHAT_PAY_CONFIG_KEY = "WE_CHAT_PAY_CONFIG_KEY";
    /**
     * 推送配置KEY
     */
    public final static String PUSH_CONFIG_KEY = "PUSH_CONFIG_KEY";
    /**
     * 关于我们KEY
     */
    public static final String ABOUT_US = "ABOUT_US";

    /**
     * 微信小程序状态
     */
    public enum weChatMiniStatus {
        /**
         * 正常
         */
        NORMAL(0, "正常"),
        /**
         * 异常
         */
        ABNORMAL(1, "异常");

        @Getter
        private int value;

        @Getter
        private String label;

        weChatMiniStatus(int value, String label) {
            this.value = value;
            this.label = label;
        }
    }
}
