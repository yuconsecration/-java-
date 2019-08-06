package com.dt.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dt.mybatis.bean.Employee;
import com.dt.mybatis.dao.EmployeeMapper;

public class MyBatisTest {
	/**
	 * 第一步：根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
	 * @throws IOException
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	//第一种方法：不推荐
//	@Test
//	public void test() throws IOException {
//		//String resource = "mybatis-config.xml";//引号内部书写工程中mybatis的配置文件的位置
//		//InputStream inputStream = Resources.getResourceAsStream(resource);
//		//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		//获取sqlSession实例，能直接执行已经映射的sql语句
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//		SqlSession openSession = sqlSessionFactory.openSession();
//		try{
//			//将返回的值封装到Employee类中的对象employee中
//		Employee employee = openSession.selectOne("com.dt.mybatis.EmployeeMapper.selectEmp", 1);//函数中第一个参数表示sql的唯一标识符，这里即为EmployeeMapper.xml中的唯一标识符，同时加上相应的命名空间用来防止存在同名的现象，第二个参数表示执行的参数对象
//		System.out.println(employee);
//		}finally{
//		openSession.close();
//		}
//	}
	//第二种方法：接口式编程（我们建议采用这种方法，它拥有许多的优点，例如：更安全的类型检查）
	@Test
	public void test01()throws IOException{
		//第一步：获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//第二步：获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
		//第三步：获取接口的实现类对象
		EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
		//第四步：查出数据库中id为1的值
		Employee employee = mapper.getEmpById(1);
//		//会为接口自动创建一个代理对象，代理对象去执行增删改查
//		System.out.print(mapper.getClass());
		//第五步：输出employee对象的值
		System.out.print(employee);
		}finally{
		//第六步：关闭openSession
			openSession.close();
		}
		
		
	}
}
