package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgkqjlDTO;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjl;
import cn.jeeweb.modules.grgl.mapper.GrglYgkqjlMapper;
import cn.jeeweb.modules.grgl.service.IGrglYgkqjlService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 员工管理 - 员工考勤记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/2 14:32
 */
@Transactional
@Service("IGrglYgkqjlService")
public class GrglYgkqjlServiceImpl extends CommonServiceImpl<GrglYgkqjlMapper, GrglYgkqjl> implements IGrglYgkqjlService {

    /**员工管理Mapper*/
    @Autowired
    private GrglYgkqjlMapper grglYgkqjlMapper;


    /**
     * Dscription: 展示所有考勤信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:29
     */
    @Override
    public PageJson<YgkqjlDTO> ajaxGrglYgkqjlList(Queryable queryable, YgkqjlDTO ygkqjlDTO) {
        Pageable pageable = queryable.getPageable();
        Page<YgkqjlDTO> page = new Page<YgkqjlDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(grglYgkqjlMapper.ajaxGrglYgkqjlList(page, ygkqjlDTO));
        PageJson<YgkqjlDTO> pagejson = new PageJson<YgkqjlDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
