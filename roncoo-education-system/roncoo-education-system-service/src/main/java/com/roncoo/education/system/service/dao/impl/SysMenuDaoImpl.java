package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.SysMenuDao;
import com.roncoo.education.system.service.dao.impl.mapper.SysMenuMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysMenuDaoImpl implements SysMenuDao {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    public int save(SysMenu record) {
        record.setId(IdWorker.getId());
        return this.sysMenuMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.sysMenuMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SysMenu record) {
        return this.sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByExampleSelective(SysMenu record, SysMenuExample example) {
        return this.sysMenuMapper.updateByExampleSelective(record, example);
    }

    public SysMenu getById(Long id) {
        return this.sysMenuMapper.selectByPrimaryKey(id);
    }

    public Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example) {
        int count = this.sysMenuMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysMenu>(count, totalPage, pageCurrent, pageSize, this.sysMenuMapper.selectByExample(example));
    }
}