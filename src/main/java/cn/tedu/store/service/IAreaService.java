package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Area;

public interface IAreaService {
	
	List<Area> getAllAreaByCityCode(String cityCode);
	
	Area getAreaByCode(String areaCode);
}
