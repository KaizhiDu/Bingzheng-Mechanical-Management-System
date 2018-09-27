package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglRgsb;
import cn.jeeweb.modules.scgl.mapper.ScglRgsbMapper;
import cn.jeeweb.modules.scgl.service.IScglRgsbService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 日工 - 日工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
@Transactional
@Service("IScglRgsbService")
public class ScglRgsbServiceImpl extends CommonServiceImpl<ScglRgsbMapper, ScglRgsb> implements IScglRgsbService {

    /**日工 - 设备 Mapper*/
    @Autowired
    private ScglRgsbMapper scglRgsbMapper;

    /**
     * Dscription: 日工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    @Override
    public PageJson<RgsbDTO> ajaxRcrwfpSbList(Queryable queryable, RgsbDTO rgsbDTO) {
        Pageable pageable = queryable.getPageable();
        Page<RgsbDTO> page = new Page<RgsbDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglRgsbMapper.ajaxRcrwfpSbList(page, rgsbDTO));
        PageJson<RgsbDTO> pagejson = new PageJson<RgsbDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
