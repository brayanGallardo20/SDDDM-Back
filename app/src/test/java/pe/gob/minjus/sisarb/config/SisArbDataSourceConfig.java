package pe.gob.minjus.sisarb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import pe.gob.minjus.sisarb.exception.EntityValidationCustom;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "pe.gob.minjus.sisarb.**.mapper.**", sqlSessionTemplateRef = "SisArbSessionTemplate")
public class SisArbDataSourceConfig {

	private SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

		@Bean(name = "SisArbDataSource")
		@Primary
		@ConfigurationProperties(prefix = "sisarb.datasource")
		public DataSource sisArbDataSource() {
			return  DataSourceBuilder.create().build();
		}

		@Bean(name = "SisArbSessionFactory")
		@Primary
		public SqlSessionFactory sisArbSessionFactory(@Qualifier("SisArbDataSource") DataSource dataSource) throws  Exception {
			bean.setDataSource(dataSource);
			bean.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources("classpath*:/sisarb/pe/gob/minjus/sisarb/**/mapper/**.xml"));
			return bean.getObject();

		}

		@Bean(name = "SisArbTransactionManager")
		@Primary
		public DataSourceTransactionManager sisArbTransactionManager(@Qualifier("SisArbDataSource") DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean(name = "SisArbSessionTemplate")
		@Primary
		public SqlSessionTemplate sisArbSessionTemplate(@Qualifier("SisArbSessionFactory") SqlSessionFactory sqlSessionFactory) {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
}
