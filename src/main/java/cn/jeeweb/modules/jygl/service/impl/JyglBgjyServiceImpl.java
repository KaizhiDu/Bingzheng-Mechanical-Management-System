package cn.jeeweb.modules.jygl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.dto.BgjyDTO;
import cn.jeeweb.modules.jygl.dto.BgjyxqDTO;
import cn.jeeweb.modules.jygl.entity.JyglBgjy;
import cn.jeeweb.modules.jygl.entity.JyglRgjy;
import cn.jeeweb.modules.jygl.mapper.JyglBgjyMapper;
import cn.jeeweb.modules.jygl.service.IJyglBgjyService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 检验管理 - 包工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:54
 */
@Transactional
@Service("IJyglBgjyService")
public class JyglBgjyServiceImpl extends CommonServiceImpl<JyglBgjyMapper, JyglBgjy> implements IJyglBgjyService {

    /**检验管理 - 包工检验Mapper*/
    @Autowired
    private JyglBgjyMapper jyglBgjyMapper;

    /**
     * Dscription: 展示所有包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    @Override
    public PageJson<BgjyDTO> ajaxBgjyList(Queryable queryable, BgjyDTO bgjyDTO) {
        Pageable pageable = queryable.getPageable();
        Page<BgjyDTO> page = new Page<BgjyDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(jyglBgjyMapper.ajaxBgjyList(page, bgjyDTO));
        PageJson<BgjyDTO> pagejson = new PageJson<BgjyDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出检验单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/8 12:53
     */
    @Override
    public List<BgjyDTO> exportBgjyd(String xm, String rq) {
        return jyglBgjyMapper.exportBgjyd(xm, rq);
    }

    /**
     * Dscription: 展示所有包工详情信息（展示）
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    @Override
    public PageJson<BgjyxqDTO> ajaxBgjyxqList(Queryable queryable, BgjyxqDTO bgjyxqDTO, String id) {
        Pageable pageable = queryable.getPageable();
        Page<BgjyxqDTO> page = new Page<BgjyxqDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(jyglBgjyMapper.ajaxBgjyxqList(page, bgjyxqDTO, id));
        PageJson<BgjyxqDTO> pagejson = new PageJson<BgjyxqDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 展示所有包工详情信息（数据处理）
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    @Override
    public List<BgjyxqDTO> bgjyxqList(BgjyxqDTO bgjyxqDTO, String id) {
        return jyglBgjyMapper.bgjyxqList(bgjyxqDTO, id);
    }
}
