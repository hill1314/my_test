/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.modules.sys.utils;

import java.util.List;

import org.apache.shiro.subject.Subject;

import cn.com.git.udmp.common.security.Principal;
import cn.com.git.udmp.common.service.BaseService;
import cn.com.git.udmp.common.utils.CacheUtils;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.web.IUDMPContext;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.dao.AreaDao;
import cn.com.git.udmp.modules.sys.dao.MenuDao;
import cn.com.git.udmp.modules.sys.dao.OfficeDao;
import cn.com.git.udmp.modules.sys.dao.RoleDao;
import cn.com.git.udmp.modules.sys.dao.UserDao;
import cn.com.git.udmp.modules.sys.entity.Area;
import cn.com.git.udmp.modules.sys.entity.Menu;
import cn.com.git.udmp.modules.sys.entity.Office;
import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.web.context.IUDMPSysContext;

/**
 * 用户工具类
 * @author Spring Cao
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	private static IUDMPContext udmpContext = SpringContextHolder.getBean(Global.UDMP_CONTEXT_NAME);
	private static IUDMPSysContext udmpSysContext = SpringContextHolder.getBean(Global.UDMP_CONTEXT_NAME);
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 
	 * @title 根据登录名获取用户
	 * @description
	 * 
	 * @param loginName
	 * @return
	 */
	public static User getByLoginName(String loginName){
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 
	 * @title 清除当前用户缓存
	 * @description
	 */
	public static void clearCache(){
	    //清除认证缓存
	    removeCache(CACHE_AUTH_INFO);
	    //清除角色缓存
		removeCache(CACHE_ROLE_LIST);
		//清除菜单缓存
		removeCache(CACHE_MENU_LIST);
		//清除区域缓存
		removeCache(CACHE_AREA_LIST);
		//清除机构列表
		removeCache(CACHE_OFFICE_LIST);
		//清除所有机构列表
		removeCache(CACHE_OFFICE_ALL_LIST);
		//清除当前用户所有缓存
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 
	 * @title 清除指定用户缓存
	 * @description
	 * 
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}
	
	/**
	 * 
	 * @title 获取当前用户
	 * @description
	 * 
	 * @return
	 */
	public static User getUser(){
		Principal principal = udmpContext.getPrincipal();
		if (principal != null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 
	 * @title 获取当前用户角色列表
	 * @description
	 * 
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 
	 * @title 获取当前用户授权菜单
	 * @description
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			setMenuDragData(menuList);
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	/**
	 * 
	 * @title 解析菜单拖拽参数，V1.0版本暂时解决方案
	 * @description
	 * 
	 * @param menuList
	 */
	private static void setMenuDragData(List<Menu> menuList){
		if(menuList == null) return;
		for(Menu menu : menuList){
			if(menu.getRemarks() != null && !menu.getRemarks().isEmpty()) {
				if(menu.getRemarks().indexOf("&lt;type:") != -1 && menu.getRemarks().indexOf("/&gt") != -1){
					menu.setType(menu.getRemarks().substring(menu.getRemarks().indexOf("&lt;type:")+"&lt;type:".length(), menu.getRemarks().indexOf("/&gt")));
				}
				if(menu.getRemarks().indexOf("&lt;imgURL:") != -1 && menu.getRemarks().indexOf("/&gt",menu.getRemarks().indexOf("&lt;imgURL:")) != -1){
					menu.setImgURL(menu.getRemarks().substring(menu.getRemarks().indexOf("&lt;imgURL:")+"&lt;imgURL:".length(), menu.getRemarks().indexOf("/&gt",menu.getRemarks().indexOf("&lt;imgURL:"))));
				}
				if(menu.getRemarks().indexOf("&lt;modelURL:") != -1 && menu.getRemarks().indexOf("/&gt",menu.getRemarks().indexOf("&lt;modelURL:")) != -1){
					menu.setModelURL(menu.getRemarks().substring(menu.getRemarks().indexOf("&lt;modelURL:")+"&lt;modelURL:".length(), menu.getRemarks().indexOf("/&gt",menu.getRemarks().indexOf("&lt;modelURL:"))));
				}
			}
		}
	}
	
	/**
	 * 
	 * @title 获取当前用户授权的区域
	 * @description
	 * 
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}
	
	/**
	 * 
	 * @title 获取当前用户有权限访问的部门
	 * @description
	 * 
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 
	 * @title 获取当前用户有权限访问的部门
	 * @description
	 * 
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
	/**
	 * 
	 * @title 获取授权主要对象
	 * @description
	 * 
	 * @return
	 */
	public static Subject getSubject(){
		return ShiroUtils.getSubject();
	}
	
	
	/**
	 * 
	 * @title 根据缓存Key获取缓存对象
	 * @description
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	/**
	 * 
	 * @title 根据key获取缓存
	 * @description 若缓存不存在则返回传入的默认对象
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
	    Object obj = udmpSysContext.getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 
	 * @title 设置缓存
	 * @description
	 * 
	 * @param key
	 * @param value
	 */
	public static void putCache(String key, Object value) {
	    udmpSysContext.getSession().setAttribute(key, value);
	}

	/**
	 * 
	 * @title 根据Key移除缓存
	 * @description
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
	    udmpSysContext.getSession().removeAttribute(key);
	}
}
