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
@BoxTable(name = "SYS_PERMISSION")
public class SysPermissionEntity extends BaseEntity {

    /**
     * 权限编码
     */
    @BoxColumn(name = "PERMISSION_CODE")
    private String permissionCode;

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 权限类别（01:自定义, 02:路径, 03:菜单）
     */
    @BoxColumn(name = "PERMISSION_TYPE")
    private String permissionType;

    public String getPermissionType() {
        return this.permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    /**
     * 权限描述
     */
    @BoxColumn(name = "PERMISSION_MEMO")
    private String permissionMemo;

    public String getPermissionMemo() {
        return this.permissionMemo;
    }

    public void setPermissionMemo(String permissionMemo) {
        this.permissionMemo = permissionMemo;
    }

}
