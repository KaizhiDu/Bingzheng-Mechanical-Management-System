package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.GydlbzDTO;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.mapper.ScglGydlbzMapper;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:    生产管理-工艺大类编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 18:03
 * @Version:        1.0
 */
@Transactional
@Service("IScglGydlbzService")
public class ScglGydlbzServiceImpl extends CommonServiceImpl<ScglGydlbzMapper, ScglGydlbz> implements IScglGydlbzService {

    /**生产计划管理-工艺大类编制Mapper*/
    @Autowired
    private ScglGydlbzMapper scglGydlbzMapper;

    /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
    @Override
    public PageJson<GydlbzDTO> ajaxGydlbzList(Queryable queryable, GydlbzDTO gydlbzDTO) {
        Pageable pageable = queryable.getPageable();
        Page<GydlbzDTO> page = new Page<GydlbzDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglGydlbzMapper.ajaxGydlbzList(page, gydlbzDTO));
        PageJson<GydlbzDTO> pagejson = new PageJson<GydlbzDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 根据根据零件id得到所有大类信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 13:13
     */
    @Override
    public List<SsxDTO> cxGydl(String ljid) {
        return scglGydlbzMapper.cxGydl(ljid);
    }

    /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
    @Override
    public List<ScglGydlbz> getGydlbzByjhid(String jhid) {
        return scglGydlbzMapper.getGydlbzByjhid(jhid);
    }
}
