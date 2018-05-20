package com.xupt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xupt.crm.mapper.BaseDictMapper;
import com.xupt.crm.pojo.BaseDict;

@Service

public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper bdm;
	
	@Override
	public List<BaseDict> selectBaseDictByCodeId(String coId) {
		
		return bdm.selectBaseDictByCodeId(coId);
	}

}
