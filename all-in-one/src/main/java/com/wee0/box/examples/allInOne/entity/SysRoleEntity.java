package com.wee0.box.examples.allInOne.entity;

import com.wee0.box.sql.annotation.BoxColumn;
import com.wee0.box.sql.annotation.BoxTable;


/**
 * @author baihw
 * @CreateDate 2019-11-15
 * @Description <pre>
 * 补充说明
 * </pre>
 **/
@BoxTable(name = "SYS_ROLE")
public class SysRoleEntity extends BaseEntity {

    /**
     * 角色名称
     */
    @BoxColumn(name = "ROLE_NAME")
    private String roleName;

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 角色描述
     */
    @BoxColumn(name = "ROLE_MEMO")
    private String roleMemo;

    public String getRoleMemo() {
        return this.roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

}
