package org.iu9.testdb1.service.impl;

import java.io.File;
import java.util.List;

import org.iu9.frame.service.IBaseService;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.Page;
import org.iu9.testdb1.entity.TuserRole;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import org.iu9.testdb1.service.ITuserRoleService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.iu9.testdb1.service.impl.TuserRole
 */
@Service("tuserRoleService")
public class TuserRoleServiceImpl extends BaseTestdb1ServiceImpl implements ITuserRoleService {

   
    @Override
	public String  saveTuserRole(TuserRole entity) throws Exception{
	       return super.save(entity).toString();
	}

    @Override
	public String  saveorupdateTuserRole(TuserRole entity) throws Exception{
	       return super.saveorupdate(entity).toString();
	}
	
	@Override
    public Integer updateTuserRole(TuserRole entity) throws Exception{
	return super.update(entity);
    }
    @Override
	public TuserRole findTuserRoleById(Object id) throws Exception{
	 return super.findById(id,TuserRole.class);
	}

/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param baseService service 调用
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, IBaseService baseService, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,baseService,o);
		}
		
}
