package cn.jeeweb.modules.zzgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zzgl.entity.ZzglJh;
import cn.jeeweb.modules.zzgl.mapper.ZzglJhMapper;
import cn.jeeweb.modules.zzgl.service.IZzglJhService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 资金管理 - 借还
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 14:47
 */
@Transactional
@Service("IZzglJhService")
public class ZzglJhServiceImpl extends CommonServiceImpl<ZzglJhMapper, ZzglJh> implements IZzglJhService {

    @Autowired
    private ZzglJhMapper zzglJhMapper;

    @Override
    public PageJson<ZzglJh> ajaxJhList(Queryable queryable, ZzglJh zzglJh) {
        if (zzglJh.getY()!=null&&!zzglJh.getY().equals("")){
            if (!zzglJh.getY().equals("10")&&!zzglJh.getY().equals("11")&&!zzglJh.getY().equals("12")){
                zzglJh.setY("0"+zzglJh.getY());
            }
        }
        if (zzglJh.getR()!=null&&!zzglJh.getR().equals("")){
            if (zzglJh.getR().equals("1")||zzglJh.getR().equals("2")||zzglJh.getR().equals("3")||zzglJh.getR().equals("4")||zzglJh.getR().equals("5")||zzglJh.getR().equals("6")||zzglJh.getR().equals("7")||zzglJh.getR().equals("8")||zzglJh.getR().equals("9")){
                zzglJh.setR("0"+zzglJh.getR());
            }
        }
        Pageable pageable = queryable.getPageable();
        Page<ZzglJh> page = new Page<ZzglJh>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(zzglJhMapper.ajaxJhList(page, zzglJh.getN(), zzglJh.getY(), zzglJh.getName(), zzglJh.getR()));
        PageJson<ZzglJh> pagejson = new PageJson<ZzglJh>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
