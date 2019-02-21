package com.taotaoke.search.Daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.taotaoke.search.Dao.SearchDao;
import com.taotaoke.search.pojo.Item;
import com.taotaoke.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Resource
	SolrServer server;

	public SearchResult search(SolrQuery solrQuery) throws Exception {
		// 创建返回值对象
		SearchResult searchResult = new SearchResult();

		// 根据条件查询 返回结果
		QueryResponse queryResponse = server.query(solrQuery);
		// 取查询结果
		SolrDocumentList documentList = queryResponse.getResults();
		// 取查询总数量
		searchResult.setCount(documentList.getNumFound());
		// 商品列表、
		List<Item> itemlist = new ArrayList<>();
		// 取高亮显示
		Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
		// 取商品列表
		for (SolrDocument it : documentList) {
			// 创建商品信息
			Item item = new Item();
			item.setId((String) it.get("id"));
			// 取高亮显示的结果
			List<String> list = map.get(it.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) it.get("item_title");
			}
			item.setTitle(title);

			item.setImage((String) it.get("item_image"));
			item.setPrice((long) it.get("item_price"));
			item.setSell_point((String) it.get("item_sell_point"));
			item.setCategory_name((String) it.get("item_category_name"));
			itemlist.add(item);
		}
		searchResult.setList(itemlist);

		return searchResult;
	}

}
