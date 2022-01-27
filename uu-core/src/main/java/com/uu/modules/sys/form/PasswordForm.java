package com.uu.modules.sys.form;

import lombok.Data;

/**
 * 密码表单
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
@Data
public class PasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
