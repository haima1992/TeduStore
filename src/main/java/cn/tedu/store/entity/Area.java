package cn.tedu.store.entity;

import java.io.Serializable;

public class Area implements Serializable{
	
	private static final long serialVersionUID = -427635224246514715L;
	
	private Integer id;
	private String cityCode;
	private String code;
	private String name;
	
	public Area() {
		super();
	}
	public Area(String cityCode, String code, String name) {
		super();
		this.cityCode = cityCode;
		this.code = code;
		this.name = name;
	}
	public Area(Integer id, String cityCode, String code, String name) {
		super();
		this.id = id;
		this.cityCode = cityCode;
		this.code = code;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Area [id=" + id + ", cityCode=" + cityCode + ", code=" + code + ", name=" + name + "]";
	}
	
}
