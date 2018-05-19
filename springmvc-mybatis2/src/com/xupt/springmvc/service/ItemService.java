package com.xupt.springmvc.service;

import java.util.List;

import com.xupt.springmvc.pojo.Items;

public interface ItemService {
	
	public List<Items> selectItemsList();
	
	public Items selectIltemsById(Integer id);
	
	public void updateItems(Items items);
}
