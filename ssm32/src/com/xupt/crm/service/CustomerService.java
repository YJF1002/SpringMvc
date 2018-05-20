package com.xupt.crm.service;

import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;
import com.xupt.crm.utils.Page;

public interface CustomerService {

	public Page<Customer> selectPageByQueryVo(QueryVo vo);
}
