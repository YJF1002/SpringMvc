package com.xupt.crm.mapper;

import java.util.List;

import com.xupt.crm.pojo.BaseDict;
import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;

public interface CustomerMapper {
	
	//总条数
	public Integer customerCountByQueryVo(QueryVo vo);
	
	
	//结果集
	public List<Customer> selectCustomerListByQueryVo(QueryVo vo);
}
