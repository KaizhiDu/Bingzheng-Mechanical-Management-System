package cn.jeeweb.modules.zzgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zzgl.dto.ZzglDTO;
import cn.jeeweb.modules.zzgl.entity.ZzglZzgl;
import cn.jeeweb.modules.zzgl.mapper.ZzglZzglMapper;
import cn.jeeweb.modules.zzgl.service.IZzglZzglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 资金管理 - 资金管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/5 9:33
 */
@Transactional
@Service("IZzglZzglService")
public class ZzglZzglServiceImpl extends CommonServiceImpl<ZzglZzglMapper, ZzglZzgl> implements IZzglZzglService {

   /**资金管理 - 资金管理Mapper*/
   @Autowired
   private ZzglZzglMapper zzglZzglMapper;

    /**
     * Dscription: 展示所有资金管理信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 14:47
     */
    @Override
    public PageJson<ZzglZzgl> ajaxZzglList(Queryable queryable, ZzglDTO zzglDTO) {
        if (zzglDTO.getY()!=null){
            if (!zzglDTO.getY().equals("10")&&!zzglDTO.getY().equals("11")&&!zzglDTO.getY().equals("12")){
                zzglDTO.setY("0"+zzglDTO.getY());
            }
        }
        if (zzglDTO.getR()!=null){
            if (zzglDTO.getR().equals("1")||zzglDTO.getR().equals("2")||zzglDTO.getR().equals("3")||zzglDTO.getR().equals("4")||zzglDTO.getR().equals("5")||zzglDTO.getR().equals("6")||zzglDTO.getR().equals("7")||zzglDTO.getR().equals("8")||zzglDTO.getR().equals("9")){
                zzglDTO.setR("0"+zzglDTO.getR());
            }
        }
        Pageable pageable = queryable.getPageable();
        Page<ZzglZzgl> page = new Page<ZzglZzgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(zzglZzglMapper.ajaxZzglList(page, zzglDTO.getN(), zzglDTO.getY(), zzglDTO.getPx(), zzglDTO.getLx(), zzglDTO.getR()));
        PageJson<ZzglZzgl> pagejson = new PageJson<ZzglZzgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
