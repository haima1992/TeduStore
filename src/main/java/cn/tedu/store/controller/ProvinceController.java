package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Province;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IProvinceService;

@Controller
@RequestMapping("/province")
public class ProvinceController extends BaseController{
	
	@Autowired
	private IProvinceService provinceService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvinceList(){
		return new ResponseResult<List<Province>>(ResponseResult.STATE_OK,provinceService.getAllProvince());
	}
	@RequestMapping("/info.do")
	@ResponseBody
	public ResponseResult<Province> getProvinceByCode(String provinceCode){
		return new ResponseResult<Province>(ResponseResult.STATE_OK,provinceService.getProvinceByCode(provinceCode));
	}
}
