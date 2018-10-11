package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.mapper.CkglBcpMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.grgl.entity.Grgl;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 未完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Transactional
@Service("ICkglBcpYwcService")
public class CkglBcpServiceImpl extends CommonServiceImpl<CkglBcpMapper, CkglBcp> implements ICkglBcpService {

    /**仓库管理 - 半成品Mapper*/
    @Autowired
    private CkglBcpMapper ckglBcpMapper;

    /**
     * Dscription: 查所有已完成或者未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @Override
    public PageJson<CkglBcp> ajaxBcpList(Queryable queryable, CkglBcp ckglBcp) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBcp> page = new Page<CkglBcp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglBcpMapper.ajaxBcpList(page, ckglBcp));
        PageJson<CkglBcp> pagejson = new PageJson<CkglBcp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 查所有未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @Override
    public PageJson<CkglBcp> ajaxBcpList2(Queryable queryable, CkglBcp ckglBcp) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBcp> page = new Page<CkglBcp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglBcpMapper.ajaxBcpList2(page, ckglBcp));
        PageJson<CkglBcp> pagejson = new PageJson<CkglBcp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
