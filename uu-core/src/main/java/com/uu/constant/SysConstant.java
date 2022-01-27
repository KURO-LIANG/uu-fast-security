package com.uu.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统常量
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public class SysConstant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 数据权限过滤
     */
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
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * 数据库删除标记
     */
    public final static String DEL_FLAG = "del_flag";

    /**
     * 菜单类型
     *
     * @author crazySea
     * @email 960236576@qq.com
     * @date 2016年11月15日 下午1:24:29
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
     *
     * @author crazySea
     * @email 960236576@qq.com
     * @date 2016年12月3日 上午12:07:22
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
     * 定时任务系统bean
     *
     * @author kuro
     * @email clarence_liang@163.com
     * @date 2020年09月04日 18:49:55
     */
    public enum scheduleBean {
        /**
         * 夜审
         */
        AUTO_NIGHT_AUDIT("autoNightAudit", "夜审"),
        COUPON_ACTIVE_NOTICE("couponActiveNotice", "优惠券活动通知"),
        CHECK_OUT_REMINDER("checkOutReminder", "退房提醒");


        private String name;
        private String label;

        scheduleBean(String name, String label) {
            this.name = name;
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public String getLabel() {
            return label;
        }


    }

    /**
     * 云存储服务商
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
        QCLOUD(3),
        /**
         * 私有云
         */
        PRIVATECLOUD(4);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 性别类型
     */
    public enum sexType {
        /**
         * 保密
         */
        SECRET(0, "保密"),
        /**
         * 菜单
         */
        MALE(1, "男"),
        /**
         * 按钮
         */
        FEMALE(2, "女");

        @Getter
        @Setter
        private Integer value;

        @Getter
        @Setter
        private String label;

        sexType(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    /**
     * 数据库删除标记：0-正常，1-删除
     */
    public enum deleteFlag {

        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 删除
         */
        DELETE(1);

        @Getter
        private int value;

        deleteFlag(int value) {
            this.value = value;
        }

    }
}
