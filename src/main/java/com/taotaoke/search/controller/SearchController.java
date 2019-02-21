package com.taotaoke.search.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotaoke.common.pojo.TaotaoResult;
import com.taotaoke.common.util.ExceptionUtil;
import com.taotaoke.search.pojo.SearchResult;
import com.taotaoke.search.service.SearchService;

@Controller
public class SearchController {

	@Resource
	SearchService searchService;
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("p") String queryString, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "50") Integer rows) {
		// 查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		SearchResult result = null;
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			result = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(result);
	}

}
