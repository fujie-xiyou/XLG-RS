package org.xiyoulinux.recruitment.config.database;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MyBatis {

    @Bean(name = "sfb")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sfb.setMapperLocations(resolver.getResources("org/xiyoulinux/recruitment/mapping/*.xml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return sfb;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactoryBean sfb){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("org.xiyoulinux.recruitment.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sfb");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return  dataSourceTransactionManager;
    }
}
