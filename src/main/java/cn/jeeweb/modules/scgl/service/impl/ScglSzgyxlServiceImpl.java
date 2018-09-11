package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglSzgyxl;
import cn.jeeweb.modules.scgl.mapper.ScglSzgyxlMapper;
import cn.jeeweb.modules.scgl.service.IScglSzgyxlService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产管理-设置工艺小类
 * @Author:         杜凯之
 * @CreateDate:     2018/9/11 16:47
 * @Version:        1.0
 */
@Transactional
@Service("IScglSzgyxlService")
public class ScglSzgyxlServiceImpl extends CommonServiceImpl<ScglSzgyxlMapper, ScglSzgyxl> implements IScglSzgyxlService {

    /**生产管理-设置工艺小类*/
    @Autowired
    private ScglSzgyxlMapper scglSzgyxlMapper;

    /**
     * @Description:    显示设置工艺小类
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 18:45
     * @Version:        1.0
     */
    @Override
    public PageJson<ScglSzgyxl> szgyxlList(Queryable queryable, ScglSzgyxl scglSzgyxl) {
        Pageable pageable = queryable.getPageable();
        Page<ScglSzgyxl> page = new Page<ScglSzgyxl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglSzgyxlMapper.szgyxlList(page, scglSzgyxl));
        PageJson<ScglSzgyxl> pagejson = new PageJson<ScglSzgyxl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
