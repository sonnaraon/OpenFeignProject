package com.nrson.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	
	private static final String MAPPER_LOCATION = "classpath:/mybatis/mapper/**/*.xml";
	private static final String CONFIG_LOCATION = "classpath:/mybatis/mybatis-config.xml";
	
	
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
//		transactionManager.setGlobalRollbackOnParticipationFailure(false);
//		return transactionManager;
//	}
	
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext, SqlLogInterceptor queryInterceptor) throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(DatabaseConfiguration.MAPPER_LOCATION));
//		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(DatabaseConfiguration.CONFIG_LOCATION));
//		sqlSessionFactoryBean.setPlugins(new Interceptor[] {queryInterceptor});
//		return sqlSessionFactoryBean.getObject();
//	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(DatabaseConfiguration.MAPPER_LOCATION));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(DatabaseConfiguration.CONFIG_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}	
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory); 
	}
	
	@Bean
	public MappingJackson2JsonView jsonViewer() {
		return new MappingJackson2JsonView();
	}
}
