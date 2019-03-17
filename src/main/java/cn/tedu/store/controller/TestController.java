package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.service.IProvinceService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@Autowired
	private IProvinceService provinceService;
	@Autowired public IGoodsService goodsService;	
	@Autowired public IGoodsCategoryService goodsCategoryService;
	
	@RequestMapping("/address.do")
	public String getProvinceList(ModelMap map) {
		map.put("provinces", provinceService.getAllProvince());
		return "test";
	}
	
	@RequestMapping("/goods.do")
	public String showGoodsList(ModelMap modelMap) {
		// 查询数据
		List<Goods> goodsList = goodsService.getGoodsList();

		// 封装数据，准备转发
		modelMap.addAttribute("goodsList", goodsList);

		// 执行转发
		return "test_goods";
	}

	@RequestMapping("/goods_category.do")
	public String showGoodsList(
		Integer parentId, ModelMap modelMap) {
		// 设置默认parentId
		if (parentId == null) {
			parentId = 0;
		}
		
		// 查询数据
		@SuppressWarnings("deprecation")
		List<GoodsCategory> goodsCategoryList = 
			goodsCategoryService.getGoodsCategoryList(parentId);

		// 封装数据，准备转发
		modelMap.addAttribute(
			"goodsCategoryList", goodsCategoryList);

		// 执行转发
		return "test_goods_category";
	}
	
}
