package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.City;

public interface ICityService {
	
	List<City> getAllCityByProvince(String provinceCode);
	
	City getCityByCode(String cityCode);
}
