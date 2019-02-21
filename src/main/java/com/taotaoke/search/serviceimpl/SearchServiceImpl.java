package com.taotaoke.search.serviceimpl;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import com.taotaoke.search.Dao.SearchDao;
import com.taotaoke.search.pojo.SearchResult;
import com.taotaoke.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Resource
	SearchDao dao;

	public SearchResult search(String query, int page, int rows) throws Exception {
		// 创建查询对象
		SolrQuery solrquery = new SolrQuery();
		// 设置查询条件
		solrquery.setQuery(query);
		// 设置分页
		solrquery.setStart((page - 1) * rows);
		solrquery.setRows(rows);
		// 设置默认搜索域
		solrquery.set("df", "item_keywords");
		// 设置高亮显示
		solrquery.setHighlight(true);
		solrquery.addHighlightField("item_title");
		solrquery.setHighlightSimplePre("<em style=\"color:red\">");
		solrquery.setHighlightSimplePost("</em>");
		// 执行查询
		SearchResult result = dao.search(solrquery);
		// 计算查询结果总页数
		Long count = result.getCount();
		long pageCount = count / rows;
		if (count % rows > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setPageCount(page);
		return result;

	}
}
