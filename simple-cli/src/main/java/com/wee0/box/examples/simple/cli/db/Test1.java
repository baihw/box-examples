package com.wee0.box.examples.simple.cli.db;

import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.sql.SqlHelper;
import com.wee0.box.sql.ds.DsHelper;
import com.wee0.box.util.IClassUtils;
import com.wee0.box.util.impl.SimpleClassUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2021/2/27 13:19
 * @Description 功能描述
 * <pre>
 * 补充说明
 * </pre>
 **/
public class Test1 {

    static {
        // 使用不依赖spring环境的工具类实现
        System.setProperty(IClassUtils.class.getName(), SimpleClassUtils.class.getName());
    }


    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Test1.class);



    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        DataSource _ds = createH2DS();
        try (Connection _conn = _ds.getConnection()) {
            String _sql = "select * from sys_user;";
            Object[] _sqlParams = null;
            List<Map<String, Object>> _maps = SqlHelper.impl().queryMapList(_conn, _sql, _sqlParams);
            log.debug("_maps: {}", _maps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static DataSource createMysqlDS() {
        final String _driver = "com.mysql.jdbc.Driver";
        final String _url = "jdbc:mysql://testHost:3306/box_example?charset=utf8mb4&useSSL=false&useAffectedRows=true&useUnicode=true&useOldAliasMetadataBehavior=true&allowMultiQueries=true";
        final String _userName = "box";
        final String _password = "box";
        DataSource _ds = DsHelper.impl().createDataSource(_driver, _url, _userName, _password);
        return _ds;
    }

    static DataSource createH2DS() {
        final String _driver = "org.h2.Driver";
        final String _url = "jdbc:h2:mem:box_test;MODE=MYSQL;INIT=RUNSCRIPT FROM './src/main/resources/db/migration/h2/V1__init.sql'";
        final String _userName = "box";
        final String _password = "box";
        DataSource _ds = DsHelper.impl().createDataSource(_driver, _url, _userName, _password);
        return _ds;
    }
}
