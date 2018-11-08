package cn.jeeweb.modules.jygl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.jygl.entity.JyglRgjy;
import cn.jeeweb.modules.jygl.mapper.JyglRgjyMapper;
import cn.jeeweb.modules.jygl.service.IJyglRgjyService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 检验管理 - 日工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/30 15:36
 */
@Transactional
@Service("IJyglRgjyService")
public class JyglRgjyServiceImpl extends CommonServiceImpl<JyglRgjyMapper, JyglRgjy> implements IJyglRgjyService {

    /**检验管理 - 日工检验Mapper*/
    @Autowired
    private JyglRgjyMapper jyglRgjyMapper;

    /**
     * Dscription: 展示所有检验信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 17:13
     */
    @Override
    public PageJson<JyglRgjy> ajaxRgjyList(Queryable queryable, RgjyDTO rgjyDTO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        Pageable pageable = queryable.getPageable();
        Page<JyglRgjy> page = new Page<JyglRgjy>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(jyglRgjyMapper.ajaxRgjyList(page, rgjyDTO,currentTime));
        PageJson<JyglRgjy> pagejson = new PageJson<JyglRgjy>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出日工检验单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/8 10:49
     */
    @Override
    public List<RgjyDTO> exportJypgd(String xm, String rq) {
        return jyglRgjyMapper.exportJypgd(xm, rq);
    }
}
