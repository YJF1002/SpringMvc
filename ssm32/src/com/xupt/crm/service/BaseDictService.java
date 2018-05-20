package com.xupt.crm.service;

import java.util.List;

import com.xupt.crm.pojo.BaseDict;

public interface BaseDictService {

	List<BaseDict> selectBaseDictByCodeId(String coId);
}
