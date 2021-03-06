/**
 *
 *
 * http://www.xkygame.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.utils;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
	public static final String SQL_FILTER = "sql_filter";
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";

    /**
     * 订单 未支付状态
     */
    public static final String ORDER_UNPAY = "1";
    /**
     * 订单 支付成功
     */
    public static final String  ORDER_PAY_SUCCESS = "2";
    public static final String WX_PAYTYPE_FRO_WX = "JSAPI"; // 微信支付类型 微信端
    public static final String WX_PAYTYPE_FRO_WEB = "MWEB"; // 微信支付类型 浏览器H5端

    public static final int ORDER_STATE_UNPAY = -1; //  未支付
    public static final int ORDER_STATE_CANCEL = 0; //  已取消
    public static final int ORDER_STATE_PAY_SUCCESS = 1; //  已支付
    public static final int ORDER_STATE_SEND = 2; //  派送中
    public static final int ORDER_STATE_CONFIRM = 3; //  （已确认）已签收
    public static final String user_token = "user_token:";

    /**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final String WECHAT_LOGIN_SALT = "icode@XKY";

}
