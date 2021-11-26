package com.wee0.box.examples.allInOne.tools;

import com.wee0.box.sql.ds.DsManager;
import com.wee0.box.sql.ds.impl.SimpleDsProperty;
import com.wee0.box.sql.template.ISqlTemplateHelper;
import com.wee0.box.sql.template.SqlTemplateHelper;
import com.wee0.box.util.shortcut.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/10/26 7:36
 * @Description 功能描述
 * <pre>
 * 补充说明
 * </pre>
 **/
public class TestGenerate {

    @BeforeClass
    public static void setup() {
        SimpleDsProperty _dsProperty = new SimpleDsProperty();
        _dsProperty.setDriverClassName("com.mysql.jdbc.Driver");
        _dsProperty.setUrl("jdbc:mysql://testHost:3306/box_example?charset=utf8mb4&useSSL=false&useAffectedRows=true&useUnicode=true&useOldAliasMetadataBehavior=true");
        _dsProperty.setUsername("box");
        _dsProperty.setPassword("box");
        DsManager.impl().addDataSourceByProperty(_dsProperty);
        DsManager.impl().init();
    }

    @Test
    public void test() {
        Map<String, Object> _dataModel = new HashMap<>();
        // 实体类所属包名
        _dataModel.put("entityPackage", "com.wee0.box.examples.multiModule.module1.entity");
        // 实体类继承的公共基类
        _dataModel.put("entityBase", "com.wee0.box.examples.multiModule.module1.entity.BaseEntity");
        // 作者名称
        _dataModel.put("author", "baihw");
        // 文件创建时间
        _dataModel.put("createDate", DateUtils.getCurrentDate());
        // 生成的实体类保存文件夹位置
        File _entityDir = new File("D:\\test");
        // 不需要在实体类中包含的列，通常是因为在公共基类中已经统一定义了。
        Set<String> _excludeColumns = new HashSet<>(12);
        _excludeColumns.add("ID");
        _excludeColumns.add("CREATE_TIME");
        _excludeColumns.add("CREATE_USER");
        _excludeColumns.add("UPDATE_TIME");
        _excludeColumns.add("UPDATE_USER");
        _excludeColumns.add("IS_DELETED");
        // 不需要在实体类中包含的表，这里排除关系表的生成。
        Set<String> _excludeTables = new HashSet<>(12);
        _excludeTables.add("sys_user_role_rel");
        _excludeTables.add("sys_role_permission_rel");
        // 自定义命名策略
        ISqlTemplateHelper.INamePolicy _namePolicy = new ISqlTemplateHelper.INamePolicy() {
            @Override
            public String renameEntity(String original, String current) {
                // 统一加上Entity后缀。
                return current + "Entity";
            }
        };
        // 调用数据库模板助手类实例生成实体类的方法
        SqlTemplateHelper.impl().generateEntities(_dataModel, "entity.ftl", _entityDir, _excludeColumns, _excludeTables, _namePolicy);
    }
}
