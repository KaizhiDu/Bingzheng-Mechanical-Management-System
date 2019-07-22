package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.dto.CkglBhglDTO;
import cn.jeeweb.modules.ckgl.entity.CkglBhgl;
import cn.jeeweb.modules.ckgl.mapper.CkglBhglMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBhglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 仓库管理 - 补货管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/16 9:37
 */
@Transactional
@Service("ICkglBhglService")
public class CkglBhglServiceImpl extends CommonServiceImpl<CkglBhglMapper, CkglBhgl> implements ICkglBhglService {

    /**原材料Mapper*/
    @Autowired
    private CkglBhglMapper ckglBhglMapper;

    /**
     * Dscription: 展示所有需要补货的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglBhglDTO> ajaxBhglList(Queryable queryable, CkglBhglDTO ckglBhglDTO) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBhglDTO> page = new Page<CkglBhglDTO>(pageable.getPageNumber(), pageable.getPageSize());
//        List<CkglBhglDTO> list = ckglBhglMapper.ajaxBhglList(page, ckglBhglDTO);
//        for (CkglBhglDTO c : list) {
//            if (Float.parseFloat(c.getYbsl())<0) {
//                list.remove(c);
//            }
//        }
        page.setRecords(ckglBhglMapper.ajaxBhglList(page, ckglBhglDTO));
        PageJson<CkglBhglDTO> pagejson = new PageJson<CkglBhglDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 10:46
     */
    @Override
    public List<CkglBhglDTO> bhglList() {
        return ckglBhglMapper.bhglList();
    }
}
