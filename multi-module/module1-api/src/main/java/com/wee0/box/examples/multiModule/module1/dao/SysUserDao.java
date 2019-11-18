package com.wee0.box.examples.multiModule.module1.dao;

import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.sql.annotation.BoxDao;
import com.wee0.box.sql.dao.IBaseDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/10/7 10:12
 * @Description 系统用户管理
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxDao
public interface SysUserDao extends IBaseDao<SysUserEntity, String> {

    List<SysUserEntity> findAll();

    List<SysUserEntity> finaAllByPage(Map<String, Object> params);

    SysUserEntity findById(String id);

    Map<String, Object> findLimit1();

    List<SysUserEntity> findByCreateTime1(Date createTime);

    List<SysUserEntity> findByCreateTime2(Date createTime);

    Integer updatePassword(String password, String id);

    List<Map<String, Object>> nativeQuery(String sql);

}
