package com.wee0.box.examples.multiModule.module1.entity;

import com.wee0.box.sql.annotation.BoxColumn;
import com.wee0.box.sql.annotation.BoxTable;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/10/7 10:06
 * @Description 系统用户实体
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxTable(name = "SYS_USER")
public class SysUserEntity extends BaseEntity {

    /**
     * 用户名
     */
    @BoxColumn(name = "USER_NAME")
    protected String userName;

    /**
     * 密码
     */
    protected String password;

    /**
     * 用户昵称
     */
    @BoxColumn(name = "NICK_NAME")
    protected String nickName;

    /**
     * 性别
     */
    protected int sex;

    /**
     * 邮箱
     */
    protected String email;

    /**
     * 手机
     */
    protected String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
