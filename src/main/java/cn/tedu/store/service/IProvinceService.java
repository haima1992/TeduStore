package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Province;

public interface IProvinceService {
	
	List<Province> getAllProvince();
	
	Province getProvinceByCode(String provinceCode);
}
