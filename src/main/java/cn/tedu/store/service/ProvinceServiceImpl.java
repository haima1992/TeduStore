package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Province;
import cn.tedu.store.mapper.ProvinceMapper;
@Service("provinceService")
public class ProvinceServiceImpl implements IProvinceService{
	
	@Autowired
	private ProvinceMapper provinceMapper;
	
	public List<Province> getAllProvince() {
		List<Province> provinces = provinceMapper.getProvinceList();
		return provinces;
	}

	public Province getProvinceByCode(String provinceCode) {
		Province province = provinceMapper.getProvinceByCode(provinceCode);
		return province;
	}

}
