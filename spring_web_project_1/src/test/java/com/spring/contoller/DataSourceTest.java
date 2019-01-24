package com.spring.contoller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class DataSourceTest {

	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSource;

	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory sql;

	@Test
	public void test() {
		try {
			SqlSession session = sql.openSession();
			Connection con = dataSource.getConnection();

			log.info(session);
			log.info(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
