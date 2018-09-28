package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgsb;
import cn.jeeweb.modules.scgl.mapper.ScglBgsbMapper;
import cn.jeeweb.modules.scgl.service.IScglBgsbService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 包工 - 包工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
@Transactional
@Service("IScglBgsbService")
public class ScglBgsbServiceImpl extends CommonServiceImpl<ScglBgsbMapper, ScglBgsb> implements IScglBgsbService {

    /**日工 - 设备 Mapper*/
    @Autowired
    private ScglBgsbMapper scglBgsbMapper;

    /**
     * Dscription: 包工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    @Override
    public PageJson<BgsbDTO> ajaxBgrwfpSbList(Queryable queryable, BgsbDTO bgsbDTO) {
        Pageable pageable = queryable.getPageable();
        Page<BgsbDTO> page = new Page<BgsbDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglBgsbMapper.ajaxBgrwfpSbList(page, bgsbDTO));
        PageJson<BgsbDTO> pagejson = new PageJson<BgsbDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
