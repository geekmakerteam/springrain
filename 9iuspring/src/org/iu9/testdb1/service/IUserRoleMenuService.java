package org.iu9.testdb1.service;

import java.util.List;

import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.entity.Role;
import org.iu9.testdb1.entity.User;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.iu9.testdb1.service.UserRole
 */
public interface IUserRoleMenuService extends IBaseTestdb1Service {
/**
 * 根据用户ID 获取角色
 * @param UserId
 * @return
 * @throws Exception
 */
	List<Role> findRoleByUserId(String userId) throws Exception;
	/**
	 * 根据角色 获取能够访问的菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuByRoleId(String roleId) throws Exception;
	/**
	 * 获取角色下的所有人员
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<User> findUserByRoleId(String roleId) throws Exception;
	/**
	 * 根据userId 查询能够看到的菜单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuByUserId(String userId) throws Exception;
	/**
	 * 根据roleId,查询角色的信息,并包括权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	Role findRoleAndMenu(String roleId)throws Exception;
	
	/**
	 * 根据UserId 查询用户信息,并包括用户的url权限
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	User findUserAndMenu(String userId) throws Exception;
	/**
	 * 根据账号密码 验证是否能够登陆
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	String findLoginUserId(String account,String password) throws Exception;
	
/**
 * 查询数据库所有的角色和对应的菜单
 * @return
 * @throws Exception
 */
	List<Role> findAllRoleAndMenu()throws Exception;
	
}
