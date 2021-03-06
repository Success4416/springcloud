package com.lenily.dream.manager.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import com.alibaba.fescar.spring.annotation.GlobalTransactionScanner;

import feign.RequestInterceptor;

@Configuration
public class FescarConfig {

	/**
	 * autowired datasource config
	 */
	@Autowired
	private DataSourceProperties dataSourceProperties;

	/**
	 * init durid datasource
	 *
	 * @Return: druidDataSource datasource instance
	 */
	@Bean
	@Primary
	public DruidDataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(dataSourceProperties.getUrl());
		druidDataSource.setUsername(dataSourceProperties.getUsername());
		druidDataSource.setPassword(dataSourceProperties.getPassword());
		druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		druidDataSource.setInitialSize(0);
		druidDataSource.setMaxActive(180);
		druidDataSource.setMaxWait(60000);
		druidDataSource.setMinIdle(0);
		druidDataSource.setValidationQuery("Select 1 from DUAL");
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(25200000);
		druidDataSource.setRemoveAbandoned(true);
		druidDataSource.setRemoveAbandonedTimeout(1800);
		druidDataSource.setLogAbandoned(true);
		return druidDataSource;
	}

	/**
	 * init datasource proxy
	 * 
	 * @Param: druidDataSource datasource bean instance
	 * @Return: DataSourceProxy datasource proxy
	 */
	@Bean
	public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
		return new DataSourceProxy(druidDataSource);
	}

	/**
	 * init mybatis sqlSessionFactory
	 * 
	 * @Param: dataSourceProxy datasource proxy
	 * @Return: DataSourceProxy datasource proxy
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceProxy);
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath*:com/lenily/dreamadmin/mapper/*.xml"));
		factoryBean.setTransactionFactory(new JdbcTransactionFactory());
		return factoryBean.getObject();
	}

	/**
	 * init global transaction scanner
	 *
	 * @Return: GlobalTransactionScanner
	 */
	@Bean
	public GlobalTransactionScanner globalTransactionScanner() {
		return new GlobalTransactionScanner("account-gts-fescar-admin", "my_test_tx_group");
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		FeignBasicRequestInterceptor requestInterceptor = new FeignBasicRequestInterceptor();
		return requestInterceptor;
	}

	@Bean
	public FescarXidFilter fescarXidFilter() {
		return new FescarXidFilter();
	}
}
