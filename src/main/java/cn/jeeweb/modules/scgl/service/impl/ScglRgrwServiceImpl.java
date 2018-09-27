package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglRgrw;
import cn.jeeweb.modules.scgl.mapper.ScglRgrwMapper;
import cn.jeeweb.modules.scgl.mapper.ScglRgsbMapper;
import cn.jeeweb.modules.scgl.service.IScglRgrwService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 日工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
@Transactional
@Service("IScglRgrwService")
public class ScglRgrwServiceImpl extends CommonServiceImpl<ScglRgrwMapper, ScglRgrw> implements IScglRgrwService {

    /**日工 - 任务 Mapper*/
    @Autowired
    private ScglRgrwMapper scglRgrwMapper;

    /**
     * Dscription: 展示所以日工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:33
     */
    @Override
    public PageJson<RgrwDTO> ajaxRcrwfpRwList(Queryable queryable, RgrwDTO rgrwDTO) {
        Pageable pageable = queryable.getPageable();
        Page<RgrwDTO> page = new Page<RgrwDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglRgrwMapper.ajaxRcrwfpRwList(page, rgrwDTO));
        PageJson<RgrwDTO> pagejson = new PageJson<RgrwDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
