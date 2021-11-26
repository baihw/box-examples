package com.wee0.box.examples.allInOne.entity;

import com.wee0.box.beans.annotation.BoxIgnore;
import com.wee0.box.sql.annotation.BoxColumn;
import com.wee0.box.sql.annotation.BoxId;
import com.wee0.box.sql.entity.AbstractEntity;

import java.util.Date;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/10/7 10:02
 * @Description 基础实体对象
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxIgnore
public class BaseEntity extends AbstractEntity<String> {

    @BoxId
    @BoxColumn(name = "ID")
    protected String id;

    /**
     * 创建时间
     */
    @BoxColumn(name = "CREATE_TIME")
    protected Date createTime;

    /**
     * 创建用户
     */
    @BoxColumn(name = "CREATE_USER")
    protected String createUser;

    /**
     * 修改时间
     */
    @BoxColumn(name = "UPDATE_TIME")
    protected Date updateTime;

    /**
     * 修改用户
     */
    @BoxColumn(name = "UPDATE_USER")
    protected String updateUser;

    /**
     * 是否标记删除:0-未标记删除；1-标记删除；
     */
    @BoxColumn(name = "IS_DELETED")
    protected Boolean isDeleted;


    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
