package com.wee0.box.examples.multiModule.module1.dao;

import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.sql.annotation.BoxDao;
import com.wee0.box.sql.dao.IBaseDao;

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
public interface SysUserDao extends IBaseDao<SysUserEntity, Integer> {

    List<SysUserEntity> findAll();

    SysUserEntity findOne(String id);

    int updatePassword(SysUserEntity sysUserEntity);

    Map selectUserMapLimitOne();

    List<Map> nativeQuery1(String sql);

    List<Map> nativeQuery2(Map sqlMap);

}
