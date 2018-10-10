package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.Ckgl;
import cn.jeeweb.modules.ckgl.service.ICkglService;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import cn.jeeweb.modules.jygl.dto.BgjyDTO;
import cn.jeeweb.modules.jygl.dto.BgjyxqDTO;
import cn.jeeweb.modules.jygl.entity.JyglBgjy;
import cn.jeeweb.modules.jygl.mapper.JyglBgjyMapper;
import cn.jeeweb.modules.jygl.service.IJyglBgjyService;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgmx;
import cn.jeeweb.modules.scgl.entity.ScglBgrwfp;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 检验管理 - 包工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:54
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/bgjy")
@RequiresPathPermission("jygl:bgjy")
public class JyglBgjyController extends BaseCRUDController<JyglBgjy, String> {

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    /**检验管理-包工检验Service*/
    @Autowired
    private IJyglBgjyService jyglBgjyService;

    /**生产管理-零件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**生产管理-包工任务分配Service*/
    @Autowired
    private IScglBgrwfpService scglBgrwfpService;

    /**员工管理-员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**生产管理-包工明细Service*/
    @Autowired
    private IScglBgmxService scglBgmxService;

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**生产计划管理-零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglJhglService;

    /**仓库管理Service*/
    @Autowired
    private ICkglService ckglService;

    /**
     * Dscription: 搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 17:28
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        List<YgsjDTO> ygsjList = scglRcrwfpService.getYgsj();
        model.addAttribute("ygsjList", ygsjList);
    }

    /**
     * Dscription: 展示所有包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    @RequestMapping(value = "ajaxBgjyList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgjyDTO> ajaxBgjyList(Queryable queryable, BgjyDTO bgjyDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgjyDTO> pageJson = jyglBgjyService.ajaxBgjyList(queryable,bgjyDTO);
        return pageJson;
    }

    /**
     * Dscription: 转到检验页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:41
     */
    @RequestMapping(value = "jy", method={RequestMethod.GET, RequestMethod.POST})
    public String jy(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("id", id);
        return display("jy");
    }

    /**
     * Dscription: 展示所有包工明细信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:51
     */
    @RequestMapping(value = "ajaxBgjyxqList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgjyxqDTO> ajaxBgjyList(String id ,Queryable queryable, BgjyxqDTO bgjyxqDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgjyxqDTO> pageJson = jyglBgjyService.ajaxBgjyxqList(queryable,bgjyxqDTO,id);
        return pageJson;
    }

    /**
     * Dscription: 检验成功后，需要扣除相应的数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 12:13
     */
    @RequestMapping(value = "sfhg", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void sfhg(String id, String bgrwfpid, HttpServletRequest request, HttpServletResponse response, Model model){
        BgjyxqDTO bgjyxqDTO = new BgjyxqDTO();
        
        //得到员工信息
        ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(bgrwfpid);
        String ygid = scglBgrwfp.getYgid();

        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);

        //减去剩余数量
        List<BgjyxqDTO> bgjyxqList = jyglBgjyService.bgjyxqList(bgjyxqDTO, bgrwfpid);
        for (BgjyxqDTO b : bgjyxqList) {
            String ljgybzid = b.getLjgybzid();
            String ywcl = b.getYwcl();
            ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
            int sysl = scglLjgybz.getSysl();
            sysl = sysl - Integer.parseInt(ywcl);
            scglLjgybz.setSysl(sysl);
            scglLjgybzService.updateById(scglLjgybz);

            //判断该零件工艺所在的零件下的所有工艺，是否有全完成了的。有就入仓库（判断sysl 和 wrksl）
            //首先要拿到零件ID
            String gydlbzid = scglLjgybzService.selectById(ljgybzid).getGydlbzid();
            String ljid = scglGydlbzService.selectById(gydlbzid).getLjid();
            //下一步要拿到零件下的所有工艺
            List<ScglLjgybz> ljgybzByLjidList = scglLjgybzService.getLjgybzByLjid(ljid);
            //判断要入库多少
            int rksl = 1000000;
            for (ScglLjgybz s: ljgybzByLjidList) {
                int a = s.getWrksl() - s.getSysl();
                if (a<rksl){
                    rksl = a;
                }
            }

            //依次减去数量未入库数量
            for (ScglLjgybz s: ljgybzByLjidList) {
                int newWrksl = s.getWrksl() - rksl;
                s.setWrksl(newWrksl);
                scglLjgybzService.updateById(s);
            }

            //还要减去零件数量
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
            int wrksl = Integer.parseInt(scjhglLjgl.getWrksl()) - rksl;
            scjhglLjgl.setWrksl(wrksl+"");
            scjhglLjglService.updateById(scjhglLjgl);

            //最后入仓库
            //先判断是否要入库,如果rksl不为0就入仓库
            if (rksl!=0){
                Ckgl ckgl = new Ckgl();
                String jhid = scjhglLjgl.getHtid();
                String jhbh = scjhglJhglService.selectById(jhid).getHtbh();
                String lbjid = scjhglLjgl.getId();
                String lbjmc = scjhglLjgl.getLjmc();
                String lbjth = scjhglLjgl.getLjth();
                String kczl = "08";
                String sfswwcbcp = "0";
                ckgl.setJhid(jhid);
                ckgl.setJhbh(jhbh);
                ckgl.setLbjid(lbjid);
                ckgl.setLbjmc(lbjmc);
                ckgl.setLbjth(lbjth);
                ckgl.setKczl(kczl);
                ckgl.setSfswwcbcp(sfswwcbcp);
                ckgl.setRksl(rksl+"");
                //要查询有没有同样的图号
                EntityWrapper<Ckgl> wrapper = new EntityWrapper<Ckgl>();
                wrapper.eq("LBJTH" ,lbjth);
                int count = ckglService.selectCount(wrapper);
                //插入新记录
                if (count == 0){
                    ckglService.insert(ckgl);
                }
                //更新入库数量
                else{
                    Ckgl ckgl2 = ckglService.selectOne(wrapper);
                    int newRksl = Integer.parseInt(ckgl2.getRksl())+rksl;
                    ckgl2.setRksl(newRksl+"");
                    ckglService.updateById(ckgl2);
                }
            }

        }
        
        //设包工展示信息的是否完成为1
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(id);
        jyglBgjy.setSfwc("1");
        jyglBgjyService.updateById(jyglBgjy);

        //并且计算工资，累加工资表里面的承包金额
        //得到应插入的承包金额
        EntityWrapper<ScglBgmx> wrapper0 = new EntityWrapper<ScglBgmx>();
        wrapper0.eq("BGRWFPID", bgrwfpid);
        ScglBgmx scglBgmx = scglBgmxService.selectOne(wrapper0);
        String cbje = scglBgmx.getCbje();

        //得到当前月，该员工的信息
        EntityWrapper<GrglYgxzgl> wrapper = new EntityWrapper<GrglYgxzgl>();
        wrapper.eq("ND", nd);
        wrapper.eq("YF", yf);
        wrapper.eq("YGID", ygid);
        GrglYgxzgl grglYgxzgl = grglYgxzglService.selectOne(wrapper);
        //得到原始承包金额
        String yscbje = grglYgxzgl.getCbje();
        float zzcbje = 0;
        if (yscbje!=null){
            if (!yscbje.equals("")){
                zzcbje = zzcbje + Float.parseFloat(yscbje);
            }
        }
        if (cbje!=null){
            if (!cbje.equals("")){
                zzcbje = zzcbje + Float.parseFloat(cbje);
            }
        }
        grglYgxzgl.setCbje(zzcbje+"");

        //注意判断null和""的情况
        //合计
        float hj = 0;
        //日工工资
        float rggz = 0;

        float zwgz = 0;
        float dx = 0;
        float fb = 0;
        float jtf = 0;
        float bt = 0;
        float bx = 0;
        float cq = 0;
        float sx = 0;
        float gs = 0;
        float cbje2 = 0;
        float jl = 0;
        float kk = 0;
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
            cbje2 = Float.parseFloat(grglYgxzgl.getCbje());
        }
        if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
            jl = Float.parseFloat(grglYgxzgl.getJl());
        }
        if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
            kk = Float.parseFloat(grglYgxzgl.getKk());
        }

        rggz = gs * sx;
        hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje2 + jl - kk;

        grglYgxzgl.setRggz(rggz+"");
        grglYgxzgl.setHj(hj+"");

        grglYgxzglService.updateById(grglYgxzgl);
        //计算日工工资和总工资
        //grglYgxzglService.countGz(grglYgxzgl);

    }

}
