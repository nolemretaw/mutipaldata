package com.lanou.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @program: springboot01
 * @description:
 * @author: hutingrong
 * @create: 2019-07-14 18:07
 */
@Configuration
@MapperScan(basePackages = {ClusterDataSourceConfig.DAOPACKAGE,ClusterDataSourceConfig.MAPPACKAGE},sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {
    static final String DAOPACKAGE = "com.lanou.mapper.cluster";
    static final String MAPPACKAGE = "classpath:mapper/cluster";
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Primary
    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "spring.cluster.datasource")
    public DataSource clusterDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        System.err.println("cluster开始");
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
