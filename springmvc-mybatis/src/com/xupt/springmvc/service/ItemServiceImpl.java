package com.xupt.springmvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xupt.springmvc.dao.ItemsMapper;
import com.xupt.springmvc.pojo.Items;

/*
 * 查询商品信息
 * @author 1x
 * 
 * */

@Service
public class ItemServiceImpl implements ItemService{

	
	@Autowired
	private ItemsMapper itemsMapper;
	
	//查询商品列表
	
	public List<Items> selectItemsList(){
		
		return itemsMapper.selectByExampleWithBLOBs(null);
	}
	
	public Items selectIltemsById(Integer id){
		return itemsMapper.selectByPrimaryKey(id);
	}
	
	public void updateItems(Items items){
		items.setCreatetime(new Date());
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}
	
}
