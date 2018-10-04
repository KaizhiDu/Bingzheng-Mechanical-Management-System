package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.mapper.GrglMapper;
import cn.jeeweb.modules.grgl.service.IGrglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description:    员工管理
* @Author:         杜凯之
* @CreateDate:     2018/8/18 15:23
* @Version:        1.0
*/
@Transactional
@Service("IGrglService")
public class GrglServiceImpl extends CommonServiceImpl<GrglMapper, Grgl> implements IGrglService {

    /**员工管理Mapper*/
    @Autowired
    private GrglMapper grglMapper;

    /**
     * @Description:    展示所有员工信息
     * @Author:         杜凯之
     * @CreateDate:     2018/8/18 16:58
     * @Version:        1.0
     */
    @Override
    public PageJson<Grgl> grglList(Queryable queryable, Grgl grgl) {
        Pageable pageable = queryable.getPageable();
        Page<Grgl> page = new Page<Grgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(grglMapper.grglList(page, grgl));
        PageJson<Grgl> pagejson = new PageJson<Grgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 只得到所有的工人信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/4 14:57
     */
    @Override
    public PageJson<YgzxxDTO> ajaxListGrgl(Queryable queryable, YgzxxDTO ygzxxDTO) {
        Pageable pageable = queryable.getPageable();
        Page<YgzxxDTO> page = new Page<YgzxxDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(grglMapper.ajaxListGrgl(page, ygzxxDTO));
        PageJson<YgzxxDTO> pagejson = new PageJson<YgzxxDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
