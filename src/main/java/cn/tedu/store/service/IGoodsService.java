package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {

	/**
	 * 获取所有商品的数据
	 * @return 所有商品的数据的List集合
	 */
	List<Goods> getGoodsList();
	
}
