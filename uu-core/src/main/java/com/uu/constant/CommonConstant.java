package com.uu.constant;

/**
 * @Description：
 * @Date: 2021/6/10
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
public class CommonConstant {

    public enum serverForInner{
        YES("SERVER_TOKEN","SLOWCOM");

        private String header;
        private String value;

        public String getHeader() {
            return header;
        }

        public String getValue() {
            return value;
        }

        serverForInner(String header, String value) {
            this.header = header;
            this.value = value;
        }
    }

    public enum isYes{
        YES(1),
        NO(0);

        private Integer value;

        public Integer getValue() {
            return value;
        }

        isYes(Integer value) {
            this.value = value;
        }
    }
}
