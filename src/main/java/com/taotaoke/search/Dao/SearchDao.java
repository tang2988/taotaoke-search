package com.taotaoke.search.Dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Repository;

import com.taotaoke.search.pojo.SearchResult;


public interface SearchDao {

	public SearchResult search(SolrQuery query) throws Exception;

}
