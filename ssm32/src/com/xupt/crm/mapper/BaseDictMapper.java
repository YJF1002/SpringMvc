package com.xupt.crm.mapper;

import java.util.List;

import com.xupt.crm.pojo.BaseDict;

public interface BaseDictMapper {
	
	List<BaseDict> selectBaseDictByCodeId(String coId);

}
