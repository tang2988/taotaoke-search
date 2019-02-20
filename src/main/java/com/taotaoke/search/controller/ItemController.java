package com.taotaoke.search.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotaoke.common.pojo.TaotaoResult;
import com.taotaoke.search.service.ItemService;

@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Resource
	ItemService itemservice;
	
	@RequestMapping("importall")
	@ResponseBody
	public TaotaoResult importall(){
		 TaotaoResult result = itemservice.importItemAll();
		return result;
	}

}
