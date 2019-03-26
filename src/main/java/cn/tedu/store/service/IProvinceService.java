package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Province;

public interface IProvinceService {

	/**
	 * 获取所有省的列表
	 * @return 所有省的列表
	 */
	List<Province> getProvinceList();

	/**
	 * 根据省的代号，获取省的信息
	 * @param provinceCode 省的代号
	 * @return 省的信息
	 */
	Province getProvinceByCode(String provinceCode);
	
}
