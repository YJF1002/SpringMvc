package com.xupt.crm.mapper;

import java.util.List;

import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;

public interface CustomerMapper {
	
	//总条数
	public Integer customerCountByQueryVo(QueryVo vo);
	
	
	//结果集
	public List<Customer> selectCustomerListByQueryVo(QueryVo vo);
	
	//通过ID查询用户
	public Customer selectCustomerById(Integer id);
	
	//保存用户
	
	public void updateCustomerById(Customer customer);
	//删除用户利用用户ID
	
	public void deleteCustomerById(Integer id);
}
