package com.xupt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xupt.crm.pojo.BaseDict;
import com.xupt.crm.pojo.Customer;
import com.xupt.crm.pojo.QueryVo;
import com.xupt.crm.service.BaseDictService;
import com.xupt.crm.service.CustomerService;
import com.xupt.crm.utils.Page;

@Controller
//@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private BaseDictService bds;
	@Autowired
	private CustomerService cs;
	
	@RequestMapping(value = "/customer/list")
	public String list(QueryVo vo,Model model){
		
		List<BaseDict> fromType = bds.selectBaseDictByCodeId("002");
		List<BaseDict> industryType = bds.selectBaseDictByCodeId("001");
		List<BaseDict> levelType = bds.selectBaseDictByCodeId("006");
		
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		
		Page<Customer> page = cs.selectPageByQueryVo(vo);
		model.addAttribute("page",page );
		model.addAttribute("CustName",vo.getCustName());
		model.addAttribute("CustSource",vo.getCustSource());
		model.addAttribute("CustIndustry",vo.getCustIndustry() );
		model.addAttribute("CustLevel",vo.getCustLevel() );
		return "customer";	
	}
	
	
	@RequestMapping(value = "customer/edit.action")
	
	public @ResponseBody 
	Customer edit(Integer id){
		
		return cs.selectCustomerById(id);
	}
	
	
	@RequestMapping(value = "customer/update.action")
	public @ResponseBody
	String update(Customer customer){
	
		cs.updateCustomerById(customer);
		return "OK";
	}
	
	@RequestMapping("customer/delete.action")
	public @ResponseBody
	String delete(Integer id){
		
		cs.deleteCustomerById(id);
		
		return "OK";
	}
	

}
