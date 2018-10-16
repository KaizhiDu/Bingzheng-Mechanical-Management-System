package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.mapper.ScglLjgybzMapper;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
@Transactional
@Service("IScglLjgybzService")
public class ScglLjgybzServiceImpl extends CommonServiceImpl<ScglLjgybzMapper, ScglLjgybz> implements IScglLjgybzService {

    /**生产管理-零件工艺编制Mapper*/
    @Autowired
    private ScglLjgybzMapper scglLjgybzMapper;

    /**
     * @Description:    根据工艺大类编制ID获取所有小类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 16:24
     * @Version:        1.0
     */
    @Override
    public PageJson<ScglLjgybz> ajaxGyxlbzList(Queryable queryable, ScglLjgybz scglLjgybz, String gydlbzid) {
        Pageable pageable = queryable.getPageable();
        Page<ScglLjgybz> page = new Page<ScglLjgybz>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglLjgybzMapper.ajaxGyxlbzList(page, scglLjgybz, gydlbzid));
        PageJson<ScglLjgybz> pagejson = new PageJson<ScglLjgybz>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 通过零件ID，得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/10 12:20
     */
    @Override
    public List<ScglLjgybz> getLjgybzByLjid(String ljid) {
        return scglLjgybzMapper.getLjgybzByLjid(ljid);
    }

    /**
     * Dscription: 通过计划ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 12:58
     */
    @Override
    public List<ScglLjgybz> getLjgybzByJhid(String jhid) {
        return scglLjgybzMapper.getLjgybzByJhid(jhid);
    }

    /**
     * Dscription: 通过计划ID和工艺大类编制ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 13:35
     */
    @Override
    public List<ScglLjgybz> getLjgybzByJhidGydlid(String jhid, String gydlid) {
        return scglLjgybzMapper.getLjgybzByJhidGydlid(jhid, gydlid);
    }
}
