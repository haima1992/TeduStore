package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;

public interface IAddressService {
	
	/**
	 * 新增收获地址
	 * @param address 收获地址
	 * @return 收获地址完整信息，将包括数据的id
	 */
	Address addNew(Address address);
	
	/**
	 * 根据用户的id获取某个用户创建的所有收货地址
	 * @param uid 用户的id
	 * @return 该用户的收获地址列表
	 */
	List<Address> getAddressListByUid(Integer uid);
	
	/**
	 * 根据数据id获取指定的收货地址信息
	 * @param id 数据id
	 * @return 收获地址信息
	 */
	Address getAddressById(Integer id);
	
	/**
	 * 根据数据id删除指定的收货地址信息
	 * @param id 数据id
	 * @param uid 用户id
	 * @return 如果删除成功则返回1，删除失败返回0
	 */
	Integer deleteAddressById(Integer id,Integer uid);

	/**
	 * 将指定的收货地址设置为默认收货地址
	 * @param id 需要设置为默认收货地址的数据id
	 * @param uid 该收货地址的持有者
	 * @return 受影响的行数
	 * @throws DataNotExistsException
	 */
	Integer setDefault(Integer id, Integer uid);
	
	/**
	 * 修改收货地址信息
	 * @param username 用户名，该收货地址的持有者，用于记录日志
	 * @param address 新的收货地址信息，必须包含id、uid及新数据，且不包含isDefault
	 * @return 受影响的行数
	 * @throws DataNotExistsException
	 */
	Integer changeAddressInfo(String username,Address address);
}
