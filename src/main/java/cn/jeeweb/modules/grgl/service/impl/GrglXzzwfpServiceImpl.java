package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.mapper.GrglXzzwfpMapper;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    员工职位薪资分配
 * @Author:         杜凯之
 * @CreateDate:     2018/8/18 15:15
 * @Version:        1.0
 */
@Transactional
@Service("IGrglXzzwfpService")
public class GrglXzzwfpServiceImpl extends CommonServiceImpl<GrglXzzwfpMapper, Xzzwfp> implements IGrglXzzwfpService {

    @Autowired
    GrglXzzwfpMapper grglXzzwfpMapper;

    /**
     * @Description:    查出所有员工的职位薪资分配
     * @Author:         杜凯之
     * @CreateDate:     2018/8/28 14:51
     * @Version:        1.0
     */
    @Override
    public PageJson<YgzxxDTO> queryAjax(Queryable queryable, Xzzwfp xzzwfp) {
        Pageable pageable = queryable.getPageable();
        Page<YgzxxDTO> page = new Page<YgzxxDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(grglXzzwfpMapper.xzzwfpList(page, xzzwfp));
        PageJson<YgzxxDTO> pagejson = new PageJson<YgzxxDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }






}
