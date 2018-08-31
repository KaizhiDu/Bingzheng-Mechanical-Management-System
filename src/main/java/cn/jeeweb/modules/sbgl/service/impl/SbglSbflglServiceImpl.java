package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.mapper.SbglSbflglMapper;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    设备管理-设备分类管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/31 16:47
 * @Version:        1.0
 */
@Transactional
@Service("ISbglSbflglService")
public class SbglSbflglServiceImpl extends CommonServiceImpl<SbglSbflglMapper, SbglSbflgl> implements ISbglSbflglService {

    /**设备分类管理Mapper*/
    @Autowired
    private SbglSbflglMapper sbglSbflglMapper;
    /**
     * @Description:    展示所有设备分类管理
     * @Author:         杜凯之
     * @CreateDate:     2018/8/31 17:29
     * @Version:        1.0
     */
    @Override
    public PageJson<SbglSbflgl> sbglSbflglList(Queryable queryable, SbglSbflgl sbglSbflgl) {
        Pageable pageable = queryable.getPageable();
        Page<SbglSbflgl> page = new Page<SbglSbflgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(sbglSbflglMapper.sbglSbflglList(page, sbglSbflgl));
        PageJson<SbglSbflgl> pagejson = new PageJson<SbglSbflgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
