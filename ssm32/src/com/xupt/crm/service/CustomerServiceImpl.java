package com.xupt.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xupt.crm.mapper.CustomerMapper;
import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;
import com.xupt.crm.utils.Page;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	private CustomerMapper cm;
	
	//通过五个条件查询呢分页对象
	public Page<Customer> selectPageByQueryVo(QueryVo vo){
		
		Page<Customer> page = new Page<Customer>();
		
		page.setSize(5);
		vo.setSize(5);
		//判断当前页
		if(null != vo){
			if(null != vo.getPage()){
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() -1)*vo.getSize());
			}
			if(null != vo.getCustName() && "".equals(vo.getCustName().trim())){
				vo.setCustName(vo.getCustName().trim());
			}if(null != vo.getCustSource() && "".equals(vo.getCustSource().trim())){
				vo.setCustSource(vo.getCustSource().trim());
			}
			if(null != vo.getCustIndustry() && "".equals(vo.getCustIndustry().trim())){
				vo.setCustIndustry(vo.getCustIndustry().trim());
			}
			if(null != vo.getCustLevel() && "".equals(vo.getCustLevel().trim())){
				vo.setCustLevel(vo.getCustLevel().trim());
			}
			
			page.setTotal(cm.customerCountByQueryVo(vo));
			page.setRows(cm.selectCustomerListByQueryVo(vo));
		}
		
		return page;
	}

	@Override
	public Customer selectCustomerById(Integer id) {
		
		return cm.selectCustomerById(id);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		
		cm.updateCustomerById(customer);
		
	}

	@Override
	public void deleteCustomerById(Integer id) {
		
		cm.deleteCustomerById(id);
		
	}
}
