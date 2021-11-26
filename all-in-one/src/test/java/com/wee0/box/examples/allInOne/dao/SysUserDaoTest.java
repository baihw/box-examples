package com.wee0.box.examples.allInOne.dao;

import com.wee0.box.examples.allInOne.entity.SysUserEntity;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/10 15:35
 * @Description 功能描述
 * <pre>
 * 补充说明
 * </pre>
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.wee0.box")
public class SysUserDaoTest {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(SysUserDaoTest.class);

    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void test1() {
        List<SysUserEntity> _users = sysUserDao.queryAll();
        log.debug("queryAll: {}", _users);
        log.debug("findAll: {}", sysUserDao.findAll());
//        String _id = "";
//        log.debug("deleteById: {}", sysUserDao.deleteById(_id));
    }

}
