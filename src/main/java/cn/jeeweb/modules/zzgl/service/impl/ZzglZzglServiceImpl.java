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

import java.util.List;

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
        page.setRecords(zzglZzglMapper.ajaxZzglList(page, zzglDTO.getN(), zzglDTO.getY(), zzglDTO.getPx(), zzglDTO.getLx(), zzglDTO.getR(), zzglDTO.getMx()));
        PageJson<ZzglZzgl> pagejson = new PageJson<ZzglZzgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出资金管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 22:17
     */
    @Override
    public List<ZzglZzgl> exportZzgl(String n1, String y1, String r1, String n2, String y2, String r2) {
        if (y1!=null){
            if (y1.equals("1")&&y1.equals("2")&&y1.equals("3")&&y1.equals("4")&&y1.equals("5")&&y1.equals("6")&&y1.equals("7")&&y1.equals("8")&&y1.equals("9")){
                y1 = "0"+y1;
            }
        }
        if (r1!=null){
            if (r1.equals("1")||r1.equals("2")||r1.equals("3")||r1.equals("4")||r1.equals("5")||r1.equals("6")||r1.equals("7")||r1.equals("8")||r1.equals("9")){
                r1 = "0"+r1;
            }
        }

        if (y2!=null){
            if (y2.equals("1")&&y2.equals("2")&&y2.equals("3")&&y2.equals("4")&&y2.equals("5")&&y2.equals("6")&&y2.equals("7")&&y2.equals("8")&&y2.equals("9")){
                y2 = "0"+y2;
            }
        }
        if (r2!=null){
            if (r2.equals("1")||r2.equals("2")||r2.equals("3")||r2.equals("4")||r2.equals("5")||r2.equals("6")||r2.equals("7")||r2.equals("8")||r2.equals("9")){
                r2 = "0"+r2;
            }
        }

        return zzglZzglMapper.exportZzgl(n1, y1, r1, n2, y2, r2);
    }

}
