package com.xupt.sollrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * SolrJ管理
 * 
 * 添加
 * 删除
 * 修改
 * 查询
 * @author YJF
 * 
 * */
public class SolrManager {
	
	@Test
	public void testAdd() throws Exception{
		
		String baseURL = "http://localhost:8080/solr/";
		//单机版
		SolrServer solrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id","haha");
		doc.setField("name","杜秦芝");
		//添加
		solrServer.add(doc);
		solrServer.commit();

	}
	
	//删除
	@Test
	public void testDelete() throws Exception{
		
		String baseURL = "http://localhost:8080/solr/collection1";
		
		//单机版
		SolrServer solrServer = new HttpSolrServer(baseURL);
		//删除全部
		solrServer.deleteByQuery("*:*",1000);
		
		
	}

}
