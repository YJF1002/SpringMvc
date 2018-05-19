package com.xupt.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xupt.springmvc.exception.MessageException;
import com.xupt.springmvc.pojo.Items;
import com.xupt.springmvc.pojo.QueryVo;
import com.xupt.springmvc.service.ItemService;

/*
 * 商品管理
 * 
 * @author 1x
 * */

@Controller
@RequestMapping(value="item")
public class ItemController {

	@Autowired
	private ItemService itemservice;
	/**
	 * 1.ModelAndView  无敌的 带着数据 返回视图路径    不建议使用
	 * 2.String   返回视图路径  model带数据   官方推荐此种方式  解耦 数据 视图 分离 MVC  建议使用
	 * 3.void     ajax  请求  合适  json格式数据 response   异步请求使用
	 * @throws MessageException 
	 * */
	
	@RequestMapping("/itemlist.action")
		
	public String itemList(Model mav ,HttpServletRequest request,
			HttpServletResponse response) throws MessageException{
		
		List<Items> list = itemservice.selectItemsList();
		if(null == null){
			throw new MessageException("商品信息为空"); 
		}
		mav.addAttribute("itemList", list);
		//request.getRequestDispatcher("itemList").forward(request, response);
		return "itemList";
	}
	
	
	
	@RequestMapping(value="/itemEdit.action")
	
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
	
	@RequestMapping(value="/updateitem.action")
	
	public String updateitem(QueryVo vo,MultipartFile pictureFile) throws Exception{
		
		//保存图片
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		
		String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		
		pictureFile.transferTo(new File("G:\\upload\\" + name + "." + ext));
		//修改
		vo.getItems().setPic(name + "." + ext);
		itemservice.updateItems(vo.getItems());
	//	ModelAndView mav = new ModelAndView();
		
//		mav.setViewName("/success");
		return "redirect:/itemlist.action";
//		return "forward:/itemlist.action";
		
	}
	//删除多个
	@RequestMapping(value="/deletes.action")
	public ModelAndView deletes(QueryVo vo){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/success");
		return mav;
	}
	
	@RequestMapping(value="/updates.action")
	public ModelAndView updates(QueryVo vo){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/success");
		return mav;
	}
	//json数据交互
	@RequestMapping(value="/json.action")
	@ResponseBody
	public Items json(@RequestBody Items items){
		
		System.out.println(items);
		return items;
	}
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "Login";
	}
	
	@RequestMapping("login")
	public String login(String username,String password,HttpSession session){
		
		System.out.println(username);
		System.out.println(password);
		
		session.setAttribute("username", username);
		
		return "redirect:/item/itemList.action";
		
	}
	
	
}
