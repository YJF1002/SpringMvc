package com.xupt.manager;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class LuceneManager {

	public IndexWriter getIndexWriter() throws Exception {
		
		Directory directory = FSDirectory.open(new File("E:\\temp\\index"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		return new IndexWriter(directory, config);
	}
	
	public IndexSearcher getIndexSearcher() throws Exception{
		
		Directory directory =  FSDirectory.open(new File("E:\\temp\\index"));
//		第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
		
//		第三步：创建一个indexsearcher对象，需要指定IndexReader对象
		return new IndexSearcher(indexReader);
	}
	public void printResult(IndexSearcher indexSearcher,Query query) throws Exception{
//		第五步：执行查询。
		TopDocs topDocs = indexSearcher.search(query, 10);
		
//		第六步：返回查询结果。遍历查询结果并输出。
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;
			Document document = indexSearcher.doc(doc);
			//文件名称
			String fileName = document.get("fileName");
			System.out.println(fileName);
			//文件内容
			String fileContent = document.get("fileContent");
			System.out.println(fileContent);
			//文件大小
			String fileSize = document.get("fileSize");
			System.out.println(fileSize);
			//文件路径
			String filePath = document.get("filePath");
			System.out.println(filePath);
			System.out.println("------------------");
		}
	}
	
	@Test
	public void testDelete() throws Exception{
		IndexWriter indexWriter = getIndexWriter();
		Query query = new TermQuery(new Term("fileName","apache"));
		
		indexWriter.deleteDocuments(query);
		indexWriter.close();
		
	}
	
	//修改
	
	@Test
	public void testupdate() throws Exception{
		IndexWriter indexWriter = getIndexWriter();
		Document doc = new Document();
		doc.add(new TextField("fileN","测试文件名",Store.YES));
		doc.add(new TextField("fileC","测试文件内容",Store.YES));
		indexWriter.updateDocument(new Term("fileName","lucene"),doc, new StandardAnalyzer());
		indexWriter.close();
			
	}
	
	//查询所有
	
	@Test
	public void testMacthAllDocsQuery() throws Exception{
		
		IndexSearcher indexSearcher = getIndexSearcher();
		
		Query query = new MatchAllDocsQuery();
		
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
		
	}
	//根据数值范围查询
	@Test
	public void testNumericRangeQuery() throws Exception{
		
		IndexSearcher indexSearcher = getIndexSearcher();
		
		Query query = NumericRangeQuery.newLongRange("fileSize",100L, 200L,true, true);
		
		printResult(indexSearcher, query);
		
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	
	//多条件查询
	@Test
	public void testBooleanQuery() throws Exception{
		
		IndexSearcher indexSearcher = getIndexSearcher();
		
		//创建布尔查询对象
		BooleanQuery query = new BooleanQuery();
		
		//创建查询条件
		Query query1 = new TermQuery(new Term("fileName","apache"));
		
		Query query2 = new TermQuery(new Term("fileName","lunece"));
		
		query.add(query1, Occur.MUST);
		query.add(query1, Occur.SHOULD);
		
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	
	@Test
	public void testQueryParser() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher();
		
		//参数1：默认查询的域
		//参数2：采用的分析器
		QueryParser queryParser = new QueryParser("fileName",new StandardAnalyzer());
		
		Query query = queryParser.parse("fileSize:{47 TO 200}");
		//Query query = queryParser.parse("+fileName:apache -fileName:luncene");
		
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
		
	}
}

