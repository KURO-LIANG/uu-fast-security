/**
 * Copyright (c) 2016-2019 炫酷游 All rights reserved.
 *
 * https://www.xkygame.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 常量
 *
 * @author KURO clarence_liang@163.com
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
    public static final String SQL_PRE = "im_pre";

    //shiro权限校验
    public static final String HEADER_AUTH = "token";

    /**
     * 登录人机验证
     */
    public static final String Char = "0123456789abcdef";
    public static final String ChannelUrl = "https://channel2.vaptcha.com/";
    public static final String ValidateUrl = "https://offline.vaptcha.com/";
    public static final String VerifyUrl = "https://0.vaptcha.com/verify";
    // 手势验证
    public static final String ValidateSuccess = "0103";
    public static final String ValidateFail = "0104";
    // 二次验证
    public static final int VerifySuccess = 1;
    public static final int VerifyFail = 0;
    public static final String ActionGet = "get";
    public static final String OfflineMode = "offline";
    public static final String PHONE_REG = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16([0-9]))|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";


    /**
    * @Description: 设备型号
    * @Author: liang_qing
    * @Email: clarence_liang@163.com
    * @Date: 2021/8/10 上午10:23
    * @Return
    **/
    public enum deviceModel{
        GZH("智核","GZH"),
        GZH_MINI("智核mini","GZHMini"),
        ANDROIDIOT("普通设备","androidiot");

        private String label;
        private String model;

        public String getLabel() {
            return label;
        }

        public String getModel() {
            return model;
        }

        deviceModel(String label, String model) {
            this.label = label;
            this.model = model;
        }

    }
    /**
    * @Description: 设备连接方式
    * @Author: liang_qing
    * @Email: clarence_liang@163.com
    * @Date: 2021/8/10 上午10:23
    * @Return
    **/
    public enum connectionType{
        NETWORK("网关"),
        WIFI("wifi");

        private String label;

        public String getLabel() {
            return label;
        }

        connectionType(String label) {
            this.label = label;
        }
    }

    public enum controlSource{
        ADMIN("智家后台_"),
        MINI("智家小程序_");

        private String label;

        public String getLabel() {
            return label;
        }

        controlSource(String label) {
            this.label = label;
        }
    }
    public enum SMS_STATUS{
        SUCCESS(1,"发送成功"),
        FAIL(0,"发送失败");

        private int value;
        private String label;

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        SMS_STATUS(int value, String label) {
            this.value = value;
            this.label = label;
        }
    }
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
     * 家庭管理员
     */
    public enum FAMILY_ADMIN {
        /**
         * 是家庭管理员
         */
    	YES(1),
        /**
         * 不是家庭管理员
         */
    	NO(0);

        private int value;

     FAMILY_ADMIN(int value) {
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

    /**
     * hash表结构
     */
    public enum RedisKey{
        /**
         * 微信第三方平台推送票据
         */
        component_verify_ticket("component","verify_ticket"),
        /**
         * 微信第三方平台access_token
         */
        component_access_token("component","access_token"),

        /**
         * 微信第三方平台预授权码 每个预授权码有效期为 10 分钟
         */
        component_pre_auth_code("component","pre_auth_code"),

        /**
         * 接口调用令牌 完整的key -> 如：
         * authorizer_access_token:appId
         */
        authorizer_access_token("authorizer_access_token"),
        /**
         * 接口调用刷新令牌 完整的key -> 如：
         * authorizer_refresh_token:appId
         */
        authorizer_refresh_token("authorizer_refresh_token"),
        /**
         * 商品库存 完整的key -> 如：
         * product_amount:productId -> amount
         */
        product_amount("product_amount");


        private String key;
        private String field;

        RedisKey(String key,String field){this.key = key;this.field = field;}

        RedisKey(String key){this.key = key;}

        public String getField(){return field;}

        public String getKey(){return key;}

    }

    public static String getRoomPermission(String roomId) {
        if(StringUtils.isEmpty(roomId)) return "houseroom:";

        return "houseroom:" + roomId;
    }
    public static String getDevicePermission(String deviceId) {
        if(StringUtils.isEmpty(deviceId)) return "device:";

        return "device:" + deviceId;
    }
    public static String getContextualPermission(String contextualId) {
        if(StringUtils.isEmpty(contextualId)) return "contextual:";

        return "contextual:" + contextualId;
    }

    public enum SMS_TYPE{
        VERCODE(0,"短信验证码","验证码${code}，您正在进行身份验证，打死不要告诉别人哦！");

        private int value;
        private String label;
        private String template;

        public String getLabel() {
            return label;
        }

        public String getTemplate() {
            return template;
        }

        public int getValue() {
            return value;
        }

        SMS_TYPE(int value, String label, String template) {
            this.value = value;
            this.label = label;
            this.template = template;
        }
    }

    public enum familyLogType{
        ADD_FAMILY(10001,"添加家庭",10),
        UPDATE_FAMILY(10002,"修改家庭",10),
        DEL_FAMILY(10003,"删除家庭",10),
        ADD_ROOM(11001,"添加房间",11),
        UPDATE_ROOM(11002,"修改房间",11),
        DEL_ROOM(11003,"删除房间",11),
        ADD_OR_UPDATE_DEVICE(12001,"添加/修改设备",12),
        CONTROL_DEVICE(12002,"控制设备",12),
        DEL_DEVICE(12003,"解绑设备",12),
        ADD_OR_UPDATE_SCENE(13001,"添加/修改情景模式",13),
        CONTROL_SCENE(13002,"控制情景模式",13),
        DEL_SCENE(13003,"删除情景模式",13),
        ADD_MEMBER(14001,"添加成员",14),
        UPDATE_MEMBER(14002,"修改成员",14),
        DEL_MEMBER(14003,"删除成员",14),
        QUIT_FAMILY(14004,"退出家庭",14);

        private int value;
        private String label;
        private int group;

        public static familyLogType byValue(Integer value) {
            for (familyLogType item : familyLogType.values()) {
                if (value.equals(item.getValue())) {
                    return item;
                }
            }

            return null;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        public int getGroup() {
            return group;
        }

        familyLogType(int value, String label, int group) {
            this.value = value;
            this.label = label;
            this.group = group;
        }
    }

    public enum LOG_STATUS {
        SUCCESS(1,"操作成功"),
        FAIL(0,"操作失败");

        private int value;
        private String label;

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        LOG_STATUS(int value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum deviceAction{
        OPEN("1","开启"),
        CLOSE("0","关闭"),
        STOP("-1","停止");

        private String value;
        private String label;

        public static deviceAction byValue(String value) {
            for (deviceAction item : deviceAction.values()) {
                if (value.equals(item.getValue())) {
                    return item;
                }
            }

            return null;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        deviceAction(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum airConditionMode{
        AUTO("1","自动"),
        COLD("2","制冷"),
        DEHUMIDIFICATION("3","除湿"),
        AIR_SUPPLY("4","送风"),
        HEATING("5","制热");

        private String value;
        private String label;

        public static airConditionMode byValue(String value) {
            for (airConditionMode item : airConditionMode.values()) {
                if (value.equals(item.getValue())) {
                    return item;
                }
            }

            return null;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        airConditionMode(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum airConditionWindSpeed{
        AUTO("1","自动"),
        COLD("2","小"),
        DEHUMIDIFICATION("3","中"),
        AIR_SUPPLY("4","大");

        private String value;
        private String label;

        public static airConditionWindSpeed byValue(String value) {
            for (airConditionWindSpeed item : airConditionWindSpeed.values()) {
                if (value.equals(item.getValue())) {
                    return item;
                }
            }

            return null;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        airConditionWindSpeed(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum airConditionWindDirection{
        HAND("0","手动"),
        AUTO("1","自动");

        private String value;
        private String label;

        public static airConditionWindDirection byValue(String value) {
            for (airConditionWindDirection item : airConditionWindDirection.values()) {
                if (value.equals(item.getValue())) {
                    return item;
                }
            }

            return null;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        airConditionWindDirection(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    /**
    * @Description:
     *
    * 情景开关 ZHKEY {ID}A-1
     * 情景开关 ZHKEY {ID}A-2
     * 情景开关 ZHKEY {ID}A-3
     * 情景开关 ZHKEY {ID}A-4
     * 情景开关 ZHKEY {ID}A-5
     * 情景开关 ZHKEY {ID}A-6
     * 人体感应 ZHHS {ID}B
     * 温湿度传感器 ZHTEMP {ID}C
     * 多路开关 ZHSWITCH {ID}D-1
     * 多路开关 ZHSWITCH {ID}D-2
     * 红外转发器 HXD019DI {ID}I
     *
    * @Author: liang_qing
    * @Email: clarence_liang@163.com
    * @Date: 2021/8/9 下午6:25
    * @Return
    **/
    public enum GZH_DEVICES {
        DEVICE_1("外出","情景开关","ZHKEY","A-1"),
        DEVICE_2("回家","情景开关","ZHKEY","A-2"),
        DEVICE_3("灯光","情景开关","ZHKEY","A-3"),
        DEVICE_4("空调","情景开关","ZHKEY","A-4"),
        DEVICE_5("窗帘","情景开关","ZHKEY","A-5"),
        DEVICE_6("自定义","情景开关","ZHKEY","A-6"),
        DEVICE_7("人体感应","人体感应","ZHHS","B"),
        DEVICE_8("温湿度传感器","温湿度传感器","ZHTEMP","C"),
        DEVICE_9("多路开关1","多路开关","ZHSWITCH","D-1"),
        DEVICE_10("多路开关2","多路开关","ZHSWITCH","D-2"),
        DEVICE_11("红外转发器","红外转发器","HXD019DI","I");

        private String alias;
        private String type;
        private String model;
        private String id;

        public String getAlias() {
            return alias;
        }

        public String getModel() {
            return model;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        GZH_DEVICES(String alias, String type, String model, String id) {
            this.alias = alias;
            this.type = type;
            this.model = model;
            this.id = id;
        }
    }

    public enum editFlag{
        allow(1,"允许配置"),
        not_allow(0,"禁止配置");

        private Integer value;
        private String label;

        editFlag(Integer value, String label) {
            this.value = value;
            this.label = label;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
