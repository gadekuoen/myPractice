package cn.wjqixige.mybatis.sqlsession;

import cn.wjqixige.mybatis.cfg.Configuration;
import cn.wjqixige.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import cn.wjqixige.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *  用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return  new DefaultSqlSessionFactory(cfg);
    }
}
