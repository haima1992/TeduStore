package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	
	/**
	 * 新增收获地址
	 * @param address
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	
	/**
	 * 查询收获地址信息
	 * @param where where子句
	 * @param orderBy order by子句
	 * @param offset limit子句中的第一个参数，表示跳过多少条数据
	 * @param countPerPage limit子句中的第二个参数，表示将获取多少条数据
	 * @return 查询到的若干条收货地址的集合
	 */
	List<Address> select(
			@Param("where")String where,
			@Param("orderBy")String orderBy,
			@Param("offset")Integer offset,
			@Param("countPerPage")Integer countPerPage);
	
	/**
	 * 删除收货地址数据
	 * @param where where子句
	 * @return 受影响的行数
	 */
	Integer delete(@Param("where")String where);
	
	/**
	 * 修改收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer update(Address address);
	
}
