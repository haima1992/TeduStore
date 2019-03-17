package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Area;
import cn.tedu.store.mapper.AreaMapper;
@Service("areaService")
public class AreaServiceImpl implements IAreaService{
	
	@Autowired
	private AreaMapper areaMapper;
	
	public List<Area> getAllAreaByCityCode(String cityCode) {
		List<Area> areas = areaMapper.getAreaListByCityCode(cityCode);
		return areas;
	}

	public Area getAreaByCode(String areaCode) {
		Area area = areaMapper.getAreaByCode(areaCode);
		return area;
	}
	
}
