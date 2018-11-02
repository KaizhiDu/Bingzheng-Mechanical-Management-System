package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbrwjk;
import cn.jeeweb.modules.sbgl.entity.SbglSbzy;
import cn.jeeweb.modules.sbgl.mapper.SbglSbrwjkMapper;
import cn.jeeweb.modules.sbgl.service.ISbglSbrwjkService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    设备管理-设备任务监控
 * @Author:         杜凯之
 * @CreateDate:     2018/9/1 13:49
 * @Version:        1.0
 */
@Transactional
@Service("ISbglSbrwjkService")
public class SbglSbrwjkServiceImpl extends CommonServiceImpl<SbglSbrwjkMapper, SbglSbrwjk> implements ISbglSbrwjkService {

    /**设备任务监控Mapper*/
    @Autowired
    private SbglSbrwjkMapper sbglSbrwjkMapper;

    /**
     * @Description:    展示所有设备监控情况
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    @Override
    public PageJson<SbglSbzy> ajaxListSbrwjk(Queryable queryable, SbglSbzy sbglSbzy) {
        Pageable pageable = queryable.getPageable();
        Page<SbglSbzy> page = new Page<SbglSbzy>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(sbglSbrwjkMapper.ajaxListSbrwjk(page, sbglSbzy));
        PageJson<SbglSbzy> pagejson = new PageJson<SbglSbzy>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
