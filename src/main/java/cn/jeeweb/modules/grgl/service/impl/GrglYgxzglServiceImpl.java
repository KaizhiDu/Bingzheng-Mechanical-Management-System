package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.mapper.GrglMapper;
import cn.jeeweb.modules.grgl.mapper.GrglYgxzglMapper;
import cn.jeeweb.modules.grgl.service.IGrglYgkqjlService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
@Transactional
@Service("IGrglYgxzglService")
public class GrglYgxzglServiceImpl extends CommonServiceImpl<GrglYgxzglMapper, GrglYgxzgl> implements IGrglYgxzglService {

//    /**员工管理Mapper*/
//    @Autowired
//    private GrglMapper grglMapper;
//
    /**员工管理 - 员工薪资管理Mapper*/
    @Autowired
    private GrglYgxzglMapper grglYgxzglMapper;
//    /**员工管理 - 员工薪资管理Service*/
//    @Autowired
//    private IGrglYgxzglService grglYgxzglService;


    /**
     * Dscription: 计算日工工资和工资总和
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/5 19:33
     */
    @Override
    public void countGz(GrglYgxzgl grglYgxzgl) {
        //注意判断null和""的情况
        //合计
        float hj = 0;
        //日工工资
        float rggz = 0;
        float cqgz = 0;
        float cqgz2 = 0;
        float zcqgz = 0;
        float zwgz = 0;
        float dx = 0;
        float fb = 0;
        float jtf = 0;
        float bt = 0;
        float bx = 0;
        float cq = 0;
        float sx = 0;
        float gs = 0;
        float cbje = 0;
        float jl = 0;
        float kk = 0;
        if (grglYgxzgl.getCqgz()!=null&&!grglYgxzgl.getCqgz().equals("")){
            cqgz = Float.parseFloat(grglYgxzgl.getCqgz());
        }
        if (grglYgxzgl.getCqgz2()!=null&&!grglYgxzgl.getCqgz2().equals("")){
            cqgz2 = Float.parseFloat(grglYgxzgl.getCqgz2());
        }
        if (grglYgxzgl.getZwgz()!=null&&!grglYgxzgl.getZwgz().equals("")){
            zwgz = Float.parseFloat(grglYgxzgl.getZwgz());
        }
        if (grglYgxzgl.getDx()!=null&&!grglYgxzgl.getDx().equals("")){
            dx = Float.parseFloat(grglYgxzgl.getDx());
        }
        if (grglYgxzgl.getFb()!=null&&!grglYgxzgl.getFb().equals("")){
            fb = Float.parseFloat(grglYgxzgl.getFb());
        }
        if (grglYgxzgl.getJtf()!=null&&!grglYgxzgl.getJtf().equals("")){
            jtf = Float.parseFloat(grglYgxzgl.getJtf());
        }
        if (grglYgxzgl.getBt()!=null&&!grglYgxzgl.getBt().equals("")){
            bt = Float.parseFloat(grglYgxzgl.getBt());
        }
        if (grglYgxzgl.getBx()!=null&&!grglYgxzgl.getBx().equals("")){
            bx = Float.parseFloat(grglYgxzgl.getBx());
        }
        if (grglYgxzgl.getCq()!=null&&!grglYgxzgl.getCq().equals("")){
            cq = Float.parseFloat(grglYgxzgl.getCq());
        }
        if (grglYgxzgl.getSx()!=null&&!grglYgxzgl.getSx().equals("")){
            sx = Float.parseFloat(grglYgxzgl.getSx());
        }
        if (grglYgxzgl.getGs()!=null&&!grglYgxzgl.getGs().equals("")){
            gs = Float.parseFloat(grglYgxzgl.getGs());
        }
        if (grglYgxzgl.getCbje()!=null&&!grglYgxzgl.getCbje().equals("")){
            cbje = Float.parseFloat(grglYgxzgl.getCbje());
        }
        if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
            jl = Float.parseFloat(grglYgxzgl.getJl());
        }
        if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
            kk = Float.parseFloat(grglYgxzgl.getKk());
        }

        zcqgz = cqgz + cqgz2;
        rggz = gs * sx;
        hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + jl - kk + zcqgz;

        grglYgxzgl.setZcqgz(zcqgz+"");
        grglYgxzgl.setRggz(rggz+"");
        grglYgxzgl.setHj(hj+"");

//        grglYgxzglService.updateById(grglYgxzgl);
    }

    /**
     * Dscription: 得到所有员工薪资管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/6 17:56
     */
    @Override
    public PageJson<GrglYgxzgl> ajaxYgxzglList(Queryable queryable, GrglYgxzgl grglYgxzgl) {
        Pageable pageable = queryable.getPageable();
        Page<GrglYgxzgl> page = new Page<GrglYgxzgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(grglYgxzglMapper.ajaxYgxzglList(page, grglYgxzgl));
        PageJson<GrglYgxzgl> pagejson = new PageJson<GrglYgxzgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
