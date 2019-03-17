package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Area;
import cn.tedu.store.entity.City;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IAreaService;
import cn.tedu.store.service.ICityService;
import cn.tedu.store.service.IProvinceService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Autowired
	private IProvinceService provinceService;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAreaService areaService;
	
	//显示地址管理页面
	@RequestMapping("/list.do")
	public String showList(String action,Integer id,ModelMap map,HttpSession session) {
		// 判断此次显示列表页时，表单的操作类型
		String actionUrl;
		String actionTitle;
		if(!"edit".equals(action)) {
			action = "addNew";
			actionUrl = "add.do";
			actionTitle = "增加";
		}else {
			action = "edit";
			actionUrl = "edit.do";
			actionTitle = "修改";
			//如果此次操作是edit类型，需要读取对应的数据
			Address address = addressService.getAddressById(id);
			//获取修改的地址对应的市列表和区列表，并转发到JSP页面
			List<City> cities = cityService.getAllCityByProvince(address.getRecvProvince());
			List<Area> areas = areaService.getAllAreaByCityCode(address.getRecvCity());
			// 封装所修改的收货地址数据
			map.addAttribute("address", address);
			// 封装市列表
			map.put("cities", cities);
			// 封装区列表
			map.put("areas", areas);
		}
		// 获取省的列表
		List<Province> provinces = provinceService.getAllProvince();
		// 获取用户id
		Integer uid = getUidFromSession(session);
		List<Address> addresses = addressService.getAddressListByUid(uid);
		// 封装页面操作类型的名称
		map.put("actionTitle", actionTitle);
		// 封装当前页面表单的操作类型和提交路径
		map.put("action", action);
		map.put("actionUrl", actionUrl);
		// 封装省的列表，以准备转发
		map.put("provinces",provinces);
		// 封装指定用户的地址列表
		map.put("addresses", addresses);
		// 执行转发
		return "address";
	}
	
	@RequestMapping("/add.do")
	public String handleAddNew(Address address,HttpSession session) {
		// 此处省略N多数据有效性判断
		
		// 封装用户id
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		addressService.addNew(address);
		return "redirect:list.do";
	}
	
	@RequestMapping("/delete.do")
	public String handleDelete(Integer id,HttpSession session) {
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 执行删除
		addressService.deleteAddressById(id, uid);
		// 重定向到列表页
		return "redirect:list.do";
	}
	
	@RequestMapping("/set_default.do")
	public String handleSetDefault(
		Integer id, HttpSession session, 
		ModelMap modelMap) {
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 执行
		try {
			addressService.setDefault(id, uid);
			return "redirect:list.do";
		} catch (ServiceException e) {
			// 封装e.getMessage()
			modelMap.addAttribute("err-msg", e.getMessage());
			// 转发到专门的错误页面
			return "error";
		}
	}
	
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	public String handleEdit(Address address,HttpSession session) {
		// 获取username
		String username = session.getAttribute("uname").toString();
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 向Address对象中封装数据
		address.setUid(uid);
		try {
			// 执行修改
			addressService.changeAddressInfo(username,address);
			// 返回：重定向
			return "redirect:list.do";
		} catch (ServiceException e) {
			// 转发到专门提示错误的页面
			return "error";
		}
	}
	
}
