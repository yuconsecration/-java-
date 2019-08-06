package com.dt.mybatis.dao;

import com.dt.mybatis.bean.Employee;

public interface EmployeeMapper {
	/**
	 * //定义一个函数用来查询sql并将其封装成Employee对象
	 * @param id
	 * @return
	 */
	public Employee getEmpById(Integer id);

}
