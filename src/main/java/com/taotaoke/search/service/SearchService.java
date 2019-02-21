package com.taotaoke.search.service;

import com.taotaoke.search.pojo.SearchResult;

public interface SearchService {
	public SearchResult search(String query,int page,int rows) throws Exception;
}
