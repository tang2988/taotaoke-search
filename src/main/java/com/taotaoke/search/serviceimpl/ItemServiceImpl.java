package com.taotaoke.search.serviceimpl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotaoke.common.pojo.TaotaoResult;
import com.taotaoke.common.util.ExceptionUtil;
import com.taotaoke.search.mapper.itemMapper;
import com.taotaoke.search.pojo.Item;
import com.taotaoke.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	private itemMapper itemMapper;
	@Resource
	SolrServer solrServer;
	
	public TaotaoResult importItemAll() {
		try {
			//查询商品列表
			 List<Item> items = itemMapper.getItemList();
			 //把信息寫入索引庫
			 for(Item item:items){
				 //创建 solrinputdocuemnt
				 SolrInputDocument document = new SolrInputDocument();
				 document.setField("id", item.getId());
				 document.setField("item_title", item.getTitle());
				 document.setField("item_sell_point", item.getSell_point());
				 document.setField("item_price", item.getPrice());
				 document.setField("item_image", item.getImage());
				 solrServer.add(document);
			 }
			 //提交修改
			 solrServer.commit();	
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok();
	}

}
