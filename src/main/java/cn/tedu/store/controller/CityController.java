package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.City;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICityService;

@Controller
@RequestMapping("/city")
public class CityController extends BaseController{
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public ResponseResult<List<City>> getCityList(String provinceCode){
		return new ResponseResult<List<City>>(ResponseResult.STATE_OK,cityService.getAllCityByProvince(provinceCode));
	}
	@RequestMapping("/info.do")
	@ResponseBody
	public ResponseResult<City> getCityByCode(String cityCode){
		return new ResponseResult<City>(ResponseResult.STATE_OK,cityService.getCityByCode(cityCode));
	}
}
