package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Area;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IAreaService;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{
	
	@Autowired
	private IAreaService areaService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public ResponseResult<List<Area>> getAreaList(String cityCode){
		return new ResponseResult<List<Area>>(ResponseResult.STATE_OK,areaService.getAllAreaByCityCode(cityCode));
	}
	@RequestMapping("/info.do")
	@ResponseBody
	public ResponseResult<Area> getAreaByCode(String areaCode){
		return new ResponseResult<Area>(ResponseResult.STATE_OK,areaService.getAreaByCode(areaCode));
	}
}
