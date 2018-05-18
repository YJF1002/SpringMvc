package com.xupt.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xupt.springmvc.pojo.Items;
import com.xupt.springmvc.pojo.QueryVo;
import com.xupt.springmvc.service.ItemService;

/*
 * 商品管理
 * 
 * @author 1x
 * */

@Controller
public class ItemController {

	@Autowired
	private ItemService itemservice;
	
	
	@RequestMapping("/itemlist.action")
		
	public ModelAndView itemList(){
		
		List<Items> list = itemservice.selectItemsList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("itemList", list);
		mav.setViewName("itemList");
		return mav;
	}
	
	@RequestMapping("/itemEdit.action")
	
	public ModelAndView itemEdit(Integer id,HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model){
		
	/*	String id = request.getParameter("id");*/
		Items itmes = itemservice.selectIltemsById(id);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("item",itmes);
		mav.setViewName("editItem");
		return mav;
	}
	
	//提交修改页面
	
	@RequestMapping("/updateitem.action")
	
	public ModelAndView updateitem(QueryVo vo){
		
		//修改
		itemservice.updateItems(vo.getItems());
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/success");
		return mav;
		
	}
	
}
