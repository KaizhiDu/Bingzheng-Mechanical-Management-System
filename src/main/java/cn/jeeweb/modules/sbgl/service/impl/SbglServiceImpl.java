package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.mapper.SbglMapper;
import cn.jeeweb.modules.sbgl.service.ISbglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @Description:    设备管理
* @Author:         杜凯之
* @CreateDate:     2018/8/30 17:08
* @Version:        1.0
*/
@Transactional
@Service("ISbglService")
public class SbglServiceImpl  extends CommonServiceImpl<SbglMapper, Sbgl> implements ISbglService {

    /**设备基本管理Service*/
    @Autowired
    private SbglMapper sbglMapper;

    /**
     * @Description:    展示所有设备
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    @Override
    public PageJson<Sbgl> ajaxListSbgl(Queryable queryable, Sbgl sbgl, String addSb) {
        Pageable pageable = queryable.getPageable();
        Page<Sbgl> page = new Page<Sbgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(sbglMapper.ajaxListSbgl(page, sbgl, addSb));
        PageJson<Sbgl> pagejson = new PageJson<Sbgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
