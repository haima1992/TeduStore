package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.City;
import cn.tedu.store.mapper.CityMapper;
@Service("cityService")
public class CityServiceImpl implements ICityService{
	
	@Autowired
	private CityMapper cityMapper;
	
	public List<City> getAllCityByProvince(String provinceCode) {
		List<City> cities = cityMapper.getCityListByProvinceCode(provinceCode);
		return cities;
	}

	public City getCityByCode(String cityCode) {
		City city = cityMapper.getCityByCode(cityCode);
		return city;
	}

}
