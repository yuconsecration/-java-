package com.dt.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dt.mybatis.bean.Employee;

public class MyBatisTest {
	/**
	 * 第一步：根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
	 * @throws IOException
	 */

	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";//引号内部书写
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//获取sqlSession实例，能直接执行已经映射的sql语句
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
		Employee employee = openSession.selectOne("com.dt.mybatis.EmployeeMapper.selectEmp", 1);
		System.out.println(employee);
		}finally{
		openSession.close();
		}
	}

}
