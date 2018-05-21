package com.xupt.crm.service;

import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;
import com.xupt.crm.utils.Page;

public interface CustomerService {

	//实现分页功能
	public Page<Customer> selectPageByQueryVo(QueryVo vo);
	
	//根据id查询用户
	public Customer selectCustomerById(Integer id);
	
	//根据id修改用户
	
	public void updateCustomerById(Customer customer);
	
	//根据id删除用户
	
	public void deleteCustomerById(Integer id);
}
