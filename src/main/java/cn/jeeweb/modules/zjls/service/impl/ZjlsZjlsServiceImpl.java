package cn.jeeweb.modules.zjls.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zjls.entity.ZjlsZjls;
import cn.jeeweb.modules.zjls.mapper.ZjlsZjlsMapper;
import cn.jeeweb.modules.zjls.service.ZjlsZjlsService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 资金流水
 *
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:36
 */
@Transactional
@Service("ZjlsZjlsService")
public class ZjlsZjlsServiceImpl extends CommonServiceImpl<ZjlsZjlsMapper, ZjlsZjls> implements ZjlsZjlsService {

    @Autowired
    private ZjlsZjlsMapper zjlsZjlsMapper;

    @Override
    public PageJson<ZjlsZjls> ajaxZjlsList(Queryable queryable, ZjlsZjls zjlsZjls) {
        if (!zjlsZjls.getY().equals("")) {
            if (!zjlsZjls.getY().equals("10") && !zjlsZjls.getY().equals("11") && !zjlsZjls.getY().equals("12")) {
                zjlsZjls.setY("0" + zjlsZjls.getY());
            }
        }
        if (!zjlsZjls.getR().equals("")) {
            if (zjlsZjls.getR().equals("1") || zjlsZjls.getR().equals("2") || zjlsZjls.getR().equals("3") || zjlsZjls.getR().equals("4") || zjlsZjls.getR().equals("5") || zjlsZjls.getR().equals("6") || zjlsZjls.getR().equals("7") || zjlsZjls.getR().equals("8") || zjlsZjls.getR().equals("9")) {
                zjlsZjls.setR("0" + zjlsZjls.getR());
            }
        }
        Pageable pageable = queryable.getPageable();
        Page<ZjlsZjls> page = new Page<ZjlsZjls>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(zjlsZjlsMapper.ajaxZjlsList(page, zjlsZjls.getN(), zjlsZjls.getY(), zjlsZjls.getGs(), zjlsZjls.getLx(), zjlsZjls.getR(), zjlsZjls.getMx2()));
        PageJson<ZjlsZjls> pagejson = new PageJson<ZjlsZjls>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    @Override
    public List<ZjlsZjls> dc(ZjlsZjls zjlsZjls) {
        if (!zjlsZjls.getY().equals("")) {
            if (!zjlsZjls.getY().equals("10") && !zjlsZjls.getY().equals("11") && !zjlsZjls.getY().equals("12")) {
                zjlsZjls.setY("0" + zjlsZjls.getY());
            }
        }
        if (!zjlsZjls.getR().equals("")) {
            if (zjlsZjls.getR().equals("1") || zjlsZjls.getR().equals("2") || zjlsZjls.getR().equals("3") || zjlsZjls.getR().equals("4") || zjlsZjls.getR().equals("5") || zjlsZjls.getR().equals("6") || zjlsZjls.getR().equals("7") || zjlsZjls.getR().equals("8") || zjlsZjls.getR().equals("9")) {
                zjlsZjls.setR("0" + zjlsZjls.getR());
            }
        }
        return zjlsZjlsMapper.dc(zjlsZjls);
    }
}
