//package com.wee0.box.examples.simple.cli.db;
//
//import org.apache.ibatis.mapping.Environment;
//import org.apache.ibatis.session.Configuration;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.apache.ibatis.transaction.TransactionFactory;
//import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
//import org.apache.ibatis.type.TypeAliasRegistry;
//
//import javax.sql.DataSource;
//
///**
// * @author <a href="78026399@qq.com">白华伟</a>
// * @CreateDate 2021/2/27 18:07
// * @Description 功能描述
// * <pre>
// * 补充说明
// * </pre>
// **/
//public class MybatisDemo1 {
//    public static void main(String[] args) {
//        DataSource _dataSource = null;
//        TransactionFactory _transactionFactory = new JdbcTransactionFactory();
//
//        Environment _environment = new Environment("development", _transactionFactory, _dataSource);
//
//        Configuration _configuration = new Configuration(_environment);
//        _configuration.setLazyLoadingEnabled(true);
//        TypeAliasRegistry _typeAliasRegistry = _configuration.getTypeAliasRegistry();
////        _typeAliasRegistry.registerAlias(Post.class);
////        _configuration.addMapper(BoundBlogMapper.class);
//
//        SqlSessionFactory _factory = new SqlSessionFactoryBuilder().build(_configuration);
//        SqlSession _session = _factory.openSession();
//
//    }
//}
