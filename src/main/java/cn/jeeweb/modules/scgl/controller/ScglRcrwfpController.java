package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjlJcsj;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import cn.jeeweb.modules.grgl.service.IGrglYgkqjlJcsjService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.sbgl.entity.*;
import cn.jeeweb.modules.sbgl.service.*;
import cn.jeeweb.modules.scgl.dto.*;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/rcrwfp")
@RequiresPathPermission("scgl:rcrwfp")
public class ScglRcrwfpController extends BaseCRUDController<ScglRcrwfp, String> {

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**员工管理 - 薪资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

    /**员工管理 - 员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    @Autowired
    /**生产管理-日工工时Service*/
    private IScglRggsService scglRggsService;

    /**设备分类管理Service*/
    @Autowired
    private ISbglSbflglService sbglSbflglService;

    /**设备管理Service*/
    @Autowired
    private ISbglService sbglService;

    /**生产管理-日工设备Service*/
    @Autowired
    private IScglRgsbService scglRgsbService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**生产管理-日工任务Service*/
    @Autowired
    private IScglRgrwService scglRgrwService;

    /**生产管理-零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**设备管理 - 日工设备占用Service*/
    @Autowired
    private ISbglRgsbzyService sbglRgsbzyService;

    /**设备管理 - 包工设备占用Service*/
    @Autowired
    private ISbglBgsbzyService sbglBgsbzyService;

    /**设备管理 - 设备占用Service*/
    @Autowired
    private ISbglSbzyService sbglSbzyService;

    /**考勤基础数据Service*/
    @Autowired
    private IGrglYgkqjlJcsjService grglYgkqjlJcsjService;

    /**包工任务分配Service*/
    @Autowired
    private IScglBgrwfpService scglBgrwfpService;

    /**包工-明细Service*/
    @Autowired
    IScglBgmxService scglBgmxService;


    /**
     * Dscription: 添加日子和搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 17:14
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sss = new SimpleDateFormat("yyyy-MM-dd");
        //得到今天的时间
        Calendar calendar0 = new GregorianCalendar();
        calendar0.setTime(new Date());
        calendar0.add(calendar0.DATE,0);
        String day = sss.format(calendar0.getTime());

        //得到明天的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,1);
        String day1 = sss.format(calendar.getTime());

        model.addAttribute("day1", day1);

        //得到后天的时间
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(new Date());
        calendar2.add(calendar.DATE,2);
        String day2 = sss.format(calendar2.getTime());

        //得到大后天的时间
        Calendar calendar3 = new GregorianCalendar();
        calendar3.setTime(new Date());
        calendar3.add(calendar.DATE,3);
        String day3 = sss.format(calendar3.getTime());

        String dates[] = {day1,day,day2,day3};

        model.addAttribute("dates", dates);

        for (int i=0;i<dates.length;i++){
            String thisDay = dates[i];
            String[] dateArray = thisDay.split("-");
            int nd = Integer.parseInt(dateArray[0]);
            int yf = Integer.parseInt(dateArray[1]);

            //查一下有没有当前年度和月份的信息，如果没有的话，插入
            EntityWrapper<GrglYgxzgl> wrapper0 = new EntityWrapper<GrglYgxzgl>();
            wrapper0.eq("ND", nd);
            wrapper0.eq("YF", yf);
            int count = grglYgxzglService.selectCount(wrapper0);
            if (count == 0){
                //得到所有员工信息
                EntityWrapper<Grgl> wrapper1 = new EntityWrapper<Grgl>();
                List<Grgl> grgls = grglService.selectList(wrapper1);
                //循环插入当前月的信息
                for (Grgl g : grgls) {
                    GrglYgxzgl grglYgxzgl = new GrglYgxzgl();
                    grglYgxzgl.setNd(nd);
                    grglYgxzgl.setYf(yf);
                    grglYgxzgl.setYgid(g.getId());
                    grglYgxzgl.setXm(g.getName());
                    EntityWrapper<Xzzwfp> wrapper2 = new EntityWrapper<Xzzwfp>();
                    wrapper2.eq("YGID", g.getId());
                    Xzzwfp xzzwfp = grglXzzwfpService.selectOne(wrapper2);
                    grglYgxzgl.setZw(xzzwfp.getZwid());
                    grglYgxzgl.setZwgz(xzzwfp.getZwgz());
                    grglYgxzgl.setDx(xzzwfp.getDx());
                    grglYgxzgl.setFb(xzzwfp.getFb());
                    grglYgxzgl.setJtf(xzzwfp.getJtf());
                    grglYgxzgl.setBt(xzzwfp.getBt());
                    grglYgxzgl.setBx(xzzwfp.getBx());
                    grglYgxzgl.setSx(xzzwfp.getSx());

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
                    float zybf = 0;
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
                    if (grglYgxzgl.getZybf()!=null&&!grglYgxzgl.getZybf().equals("")){
                        zybf = Float.parseFloat(grglYgxzgl.getZybf());
                    }

                    zcqgz = cqgz + cqgz2;
                    rggz = gs * sx + zybf;
                    hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje + jl - kk +zcqgz;

                    grglYgxzgl.setZcqgz(zcqgz+"");
                    grglYgxzgl.setRggz(rggz+"");
                    grglYgxzgl.setHj(hj+"");

                    grglYgxzglService.insert(grglYgxzgl);
                    //计算日工工资和总工资
                    //grglYgxzglService.countGz(grglYgxzgl);
                }
            }

            //判断表里面有没有该日期数据
            EntityWrapper<ScglRcrwfp> wrapper = new EntityWrapper<ScglRcrwfp>();
            wrapper.eq("RQ",thisDay);
            List<ScglRcrwfp> scglRcrwfps = scglRcrwfpService.selectList(wrapper);
            //如果表里面没有就插入数据
            if (scglRcrwfps.size()==0){
                List<ScglRcrwfp> list = new ArrayList<ScglRcrwfp>();
                List<YgsjDTO> ygsjList = scglRcrwfpService.getYgsj();
                for (YgsjDTO ygsjDTO: ygsjList) {
                    ScglRcrwfp s = new ScglRcrwfp();
                    s.setXb(ygsjDTO.getXb());
                    s.setRq(thisDay);
                    s.setXm(ygsjDTO.getXm());
                    s.setZw(ygsjDTO.getZw());
                    s.setYgid(ygsjDTO.getYgid());
                    //如果有未完成的包工信息，则设置bgzy为已分配包工
                    EntityWrapper<ScglBgrwfp> wrapper1 = new EntityWrapper<ScglBgrwfp>();
                    wrapper1.eq("YGID", ygsjDTO.getYgid());
                    wrapper1.eq("SFWC", "0");
                    int count1 = scglBgrwfpService.selectCount(wrapper1);
                    if (count1>0){
                        s.setBgzy("已分配包工");
                    }
                    else{
                        s.setBgzy("");
                    }
                    list.add(s);
                }
                scglRcrwfpService.insertBatch(list);
            }

            //判断sbzy表里面有没有该日期数据
            EntityWrapper<SbglSbzy> wrapper1 = new EntityWrapper<SbglSbzy>();
            wrapper1.eq("RQ",thisDay);
            int count2 = sbglSbzyService.selectCount(wrapper1);
            //如果sbzy表里面没有数据的话，就生成数据
            if (count2==0){
                EntityWrapper<Sbgl> wrapper2 = new EntityWrapper<Sbgl>();
                //wrapper2.eq("ZT","1");
                List<Sbgl> sbgls = sbglService.selectList(wrapper2);

                for (Sbgl s : sbgls) {
                    SbglSbzy sbglSbzy = new SbglSbzy();
                    sbglSbzy.setSbid(s.getId());
                    sbglSbzy.setSbbh(s.getSbbh());
                    sbglSbzy.setSbmc(s.getSbmc());
                    sbglSbzy.setSsdl(s.getSsdl());
                    sbglSbzy.setRq(thisDay);
                    sbglSbzy.setSfky("1");
                    sbglSbzy.setZt(s.getZt());
                    sbglSbzyService.insert(sbglSbzy);
                }
            }
        }

    }

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @RequestMapping(value = "ajaxRcrwfpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglRcrwfp> ajaxRcrwfpList(Queryable queryable, ScglRcrwfp scglRcrwfp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScglRcrwfp> pageJson = scglRcrwfpService.ajaxRcrwfpList(queryable,scglRcrwfp);
        return pageJson;
    }

    /**
     * Dscription: 日工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    @RequestMapping(value = "ajaxRcrwfpSbList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<RgsbDTO> ajaxRcrwfpSbList(Queryable queryable, RgsbDTO rgsbDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<RgsbDTO> pageJson = scglRgsbService.ajaxRcrwfpSbList(queryable,rgsbDTO);
        return pageJson;
    }

    /**
     * Dscription: 日工 - 添加的任务展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:30
     */
    @RequestMapping(value = "ajaxRcrwfpRwList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<RgrwDTO> ajaxRcrwfpRwList(Queryable queryable, RgrwDTO rgrwDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<RgrwDTO> pageJson = scglRgrwService.ajaxRcrwfpRwList(queryable,rgrwDTO);
        return pageJson;
    }


    /**
     * Dscription: 跳转到日常任务设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:07
     */
    @RequestMapping(value = "fpsb", method={RequestMethod.GET, RequestMethod.POST})
    public String fpsb(String id, String rq, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        model.addAttribute("rq", rq);
        return display("fpsb");
    }

    /**
     * Dscription: 跳转到分配工时页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:44
     */
    @RequestMapping(value = "fpgs", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgs(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        EntityWrapper<ScglRggs> wrapper = new EntityWrapper<ScglRggs>();
        wrapper.eq("RCRWFPID", scglRcrwfp.getId());
        ScglRggs scglRggs = scglRggsService.selectOne(wrapper);
        model.addAttribute("rggs", scglRggs);
        if (scglRggs==null){
            model.addAttribute("RggsId", "");
        }
        else{
            model.addAttribute("RggsId", scglRggs.getId());
        }
        return display("fpgs");
    }

    /**
     * Dscription: 保存工时
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 9:27
     */
    @RequestMapping(value = "saveGs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGs(String rq, ScglRggs scglRggs, HttpServletRequest request, HttpServletResponse response, Model model){

        //首先得到工时和加班，把他们两个相加放到rcrwfp表里
        float gss = 0;
        float jbb = 0;
        if (scglRggs.getJb()!=null&&!scglRggs.getJb().equals("")){
            jbb = Float.parseFloat(scglRggs.getJb());
        }
        if (scglRggs.getGs()!=null&&!scglRggs.getGs().equals("")){
            gss = Float.parseFloat(scglRggs.getGs());
        }
        float zgss = gss + jbb;
        ScglRcrwfp scglRcrwfp01 = scglRcrwfpService.selectById(scglRggs.getRcrwfpid());
        scglRcrwfp01.setGs(zgss+"");
        scglRcrwfpService.updateById(scglRcrwfp01);

        //拿到原始数据
        String gsid = scglRggs.getId();
        ScglRggs ysRggs1 = scglRggsService.selectById(gsid);
        //原始的中夜班费和新的中夜班费
        float yszybff = 0;
        float xzybff = 0;
        if (ysRggs1!=null){
            if (ysRggs1.getZybf()!=null&&!ysRggs1.getZybf().equals("")){
                yszybff = Float.parseFloat(ysRggs1.getZybf());
            }
        }

        if (scglRggs.getZybf()!=null&&!scglRggs.getZybf().equals("")){
            xzybff = Float.parseFloat(scglRggs.getZybf());
        }

        //得到年月
        String[] dateArray = rq.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);

        //得到员工ID
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(scglRggs.getRcrwfpid());
        String ygid = scglRcrwfp.getYgid();

        if (scglRggs.getGsmc()==null){
            scglRggs.setGsmc("");
        }
        //执行插入操作
        if (scglRggs.getId()==null||scglRggs.getId().equals("")){
            scglRggs.setId(null);
            scglRggsService.insert(scglRggs);
        }
        //执行更新操作
        else{
            scglRggsService.updateById(scglRggs);
        }

        //先拿到考勤基础数据
        EntityWrapper<GrglYgkqjlJcsj> wrapper0 = new EntityWrapper<GrglYgkqjlJcsj>();
        List<GrglYgkqjlJcsj> grglYgkqjlJcsjs = grglYgkqjlJcsjService.selectList(wrapper0);
        float cqxsgz = 0;
//        for (GrglYgkqjlJcsj g : grglYgkqjlJcsjs) {
//            if (g.getMc().equals("出勤工资(每小时)")){
//                cqxsgz = Float.parseFloat(g.getSz());
//            }
//        }

        //同步插入工资表里
        float zgs = 0;
        float cqgzf = 0;
        if (ysRggs1!=null){
            if (ysRggs1.getGs()!=null){
                if (!ysRggs1.getGs().equals("")){
                    zgs = zgs - Float.parseFloat(ysRggs1.getGs());
                }
            }
            if (ysRggs1.getJb()!=null){
                if (!ysRggs1.getJb().equals("")){
                    zgs = zgs - Float.parseFloat(ysRggs1.getJb());
                    cqgzf = cqgzf - Float.parseFloat(ysRggs1.getJb())*cqxsgz;
                }
            }
        }
        float gs = 0;
        float jb = 0;
        if (scglRggs.getGs()!=null){
            if (!scglRggs.getGs().equals("")){
                gs = Float.parseFloat(scglRggs.getGs());
            }
        }
        if (scglRggs.getJb()!=null){
            if (!scglRggs.getJb().equals("")){
                jb = Float.parseFloat(scglRggs.getJb());
                cqgzf = cqgzf + Float.parseFloat(scglRggs.getJb())*cqxsgz;
            }
        }

            zgs = zgs + gs + jb;
            EntityWrapper<GrglYgxzgl> wrapper = new EntityWrapper<GrglYgxzgl>();
            wrapper.eq("ND", nd);
            wrapper.eq("YF", yf);
            wrapper.eq("YGID", ygid);
            GrglYgxzgl grglYgxzgl = grglYgxzglService.selectOne(wrapper);
            if (grglYgxzgl.getGs()==null){
                grglYgxzgl.setGs(zgs+"");
            }
            else{
                zgs = zgs + Float.parseFloat(grglYgxzgl.getGs());

                grglYgxzgl.setGs(zgs+"");
            }
            if (grglYgxzgl.getCqgz2()==null){
                grglYgxzgl.setCqgz2(cqgzf+"");
            }
            else{
                cqgzf = cqgzf + Float.parseFloat(grglYgxzgl.getCqgz2());
                grglYgxzgl.setCqgz2(cqgzf+"");
            }

            //把新的zybf累加到YGXZGL里面
            float zybfzh = 0;
            if (grglYgxzgl.getZybf()!=null&&!grglYgxzgl.getZybf().equals("")){
                zybfzh = Float.parseFloat(grglYgxzgl.getZybf());
            }
            zybfzh = zybfzh - yszybff + xzybff;
            grglYgxzgl.setZybf(zybfzh+"");

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
        float gs2 = 0;
        float cbje = 0;
        float jl = 0;
        float kk = 0;
        float zybf = 0;
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
            gs2 = Float.parseFloat(grglYgxzgl.getGs());
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
        if (grglYgxzgl.getZybf()!=null&&!grglYgxzgl.getZybf().equals("")){
            zybf = Float.parseFloat(grglYgxzgl.getZybf());
        }

        zcqgz = cqgz + cqgz2;
        rggz = gs2 * sx + zybf;
        hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje + jl - kk + zcqgz;

        grglYgxzgl.setZcqgz(zcqgz+"");
        grglYgxzgl.setRggz(rggz+"");
        grglYgxzgl.setHj(hj+"");

        grglYgxzglService.updateById(grglYgxzgl);
        //计算日工工资和总工资
        //grglYgxzglService.countGz(grglYgxzgl);

    }

    /**
     * Dscription: 转到添加设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 14:38
     */
    @RequestMapping(value = "addSb", method={RequestMethod.GET, RequestMethod.POST})
    public String addSb(String rcrwfpid, String rq, HttpServletRequest request, HttpServletResponse response, Model model){
        //设备分类
        EntityWrapper<SbglSbflgl> wrapper = new EntityWrapper();
        wrapper.orderBy("fldm");
        List<SbglSbflgl> sbflglList = sbglSbflglService.selectList(wrapper);
        model.addAttribute("list",sbflglList);
        model.addAttribute("rcrwfpid", rcrwfpid);
        model.addAttribute("rq", rq);
        //日常任务分配
        return display("addSb");
    }

    /**
     * Dscription: 保存设备信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 9:55
     */
    @RequestMapping(value = "saveSb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveSb(String ids ,String rcrwfpid, String rq, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String sbid = sbglSbzyService.selectById(idsArray[i]).getSbid();
            EntityWrapper<ScglRgsb> wrapper = new EntityWrapper<ScglRgsb>();
            wrapper.orderBy("PX");
            int index = scglRgsbService.selectList(wrapper).size();
            String uuid  = UUID.randomUUID().toString().replaceAll("-","");
            if (index == 0){
                int px = 1;
                ScglRgsb s = new ScglRgsb();
                s.setId(uuid);
                s.setPx(px);
                s.setRcrwfpid(rcrwfpid);
                s.setSbid(sbid);
                scglRgsbService.insert(s);

            }
            else{
                int px = scglRgsbService.selectList(wrapper).get(index-1).getPx()+1;
                ScglRgsb s = new ScglRgsb();
                s.setId(uuid);
                s.setPx(px);
                s.setRcrwfpid(rcrwfpid);
                s.setSbid(sbid);
                scglRgsbService.insert(s);
            }

            //每次分配设备的时候，把sbzy表 里日期为rq的对应信息的 sfky设为 0
           EntityWrapper<SbglSbzy> wrapper01 = new EntityWrapper<SbglSbzy>();
            wrapper01.eq("RQ", rq);
            wrapper01.eq("SBID", sbid);
            SbglSbzy sbglSbzy = sbglSbzyService.selectOne(wrapper01);
            sbglSbzy.setSfky("0");
            sbglSbzyService.updateById(sbglSbzy);

            //把设备信息和今天的日期放到rgsbzy里面
            SbglRgsbzy sbglRgsbzy = new SbglRgsbzy();
            sbglRgsbzy.setRgsbid(uuid);
            sbglRgsbzy.setRq(rq);
            sbglRgsbzy.setSbid(sbid);
            sbglRgsbzyService.insert(sbglRgsbzy);
        }
    }

    /**
     * Dscription: 删除设备信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 9:55
     */
    @RequestMapping(value = "deleteSb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteSb(String rq, String ids , HttpServletRequest request, HttpServletResponse response, Model model){

        AjaxJson ajaxJson = new AjaxJson();
        String idsArray[] = ids.split(",");
        Boolean flag = true;
        for (int i=0;i<idsArray.length;i++){
            //通过设备ID,找下属的任务。如果有任务则flag设为false
            EntityWrapper<ScglRgrw> wrapper = new EntityWrapper<ScglRgrw>();
            wrapper.eq("FPSBID", idsArray[i]);
            int count = scglRgrwService.selectCount(wrapper);
            if (count>0){
                flag = false;
                break;
            }
        }
        //如果已经分配了任务则不允许删除
        if (flag==true){
            for (int i=0;i<idsArray.length;i++){
                //下面一步操作是为了通过删除，来判断设备的使用情况。
                    //但是删除之前要先判断库里有几个该设备
                    String sbid = scglRgsbService.selectById(idsArray[i]).getSbid();
                    EntityWrapper<SbglRgsbzy> wrapper0 = new EntityWrapper<SbglRgsbzy>();
                    wrapper0.eq("SBID" ,sbid);
                    wrapper0.eq("RQ", rq);
                    int count = sbglRgsbzyService.selectCount(wrapper0);
                    //如果等于1的话，再判断包工里面有没有该sb的数据
                    if (count==1){
                        EntityWrapper<SbglBgsbzy> wrapper2 = new EntityWrapper<SbglBgsbzy>();
                        wrapper2.eq("SBID", sbid);
                        int count2 = sbglBgsbzyService.selectCount(wrapper2);
                        //如果包工里面没有该设备数据，那么该设备的sfzy可以设为1
                        if (count2==0){
                            EntityWrapper<SbglSbzy> wrapper = new EntityWrapper<SbglSbzy>();
                            wrapper.eq("SBID" ,sbid);
                            wrapper.eq("RQ", rq);
                            SbglSbzy sbglSbzy = sbglSbzyService.selectOne(wrapper);
                            sbglSbzy.setSfky("1");
                            sbglSbzyService.updateById(sbglSbzy);
                        }
                    }


                //删除rgsbzy里面的数据
                EntityWrapper<SbglRgsbzy> wrapper = new EntityWrapper<SbglRgsbzy>();
                wrapper.eq("RGSBID", idsArray[i]);
                sbglRgsbzyService.delete(wrapper);
                //然后删除rgsb数据
                scglRgsbService.deleteById(idsArray[i]);

            }
            ajaxJson.setMsg("删除成功");
        }
        else{
            ajaxJson.setMsg("设备已分配任务，请先删除任务");
        }

        return ajaxJson;
    }

    /**
     * Dscription: 删除任务信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 15:20
     */
    @RequestMapping(value = "deleteRw", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteRw(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        String idsArray[] = ids.split(",");
        //0是删除成功，1是任务已经检验
        int a = 0;
        for (int i=0;i<idsArray.length;i++){
            //需要拿到每个任务的sjwcl，如果世界完成量为空，则提示 任务已经检验，不能删除
            ScglRgrw scglRgrw = scglRgrwService.selectById(idsArray[i]);
            if (scglRgrw.getSjwcl()!=null){
                a = 1;
                break;
            }
        }

        if (a==1){
            ajaxJson.setMsg("任务已经检验，不能删除");
        }
        //可以删除
        else{
            ajaxJson.setMsg("删除成功");
            for (int i=0;i<idsArray.length;i++){
                ScglRgrw scglRgrw = scglRgrwService.selectById(idsArray[i]);
                //零部件工艺编制下的计划生产数量应该 减去 应完成量
                String ljgybzid = scglRgrw.getLjgybzid();
                ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
                int ywcli = 0;
                if (scglRgrw.getYwcl()!=null&&!scglRgrw.getYwcl().equals("")){
                    ywcli = Integer.parseInt(scglRgrw.getYwcl());
                }
                int jhscsl = scglLjgybz.getJhscsl() - ywcli;
                scglLjgybz.setJhscsl(jhscsl);
                scglLjgybzService.updateById(scglLjgybz);
                //删除该任务
                scglRgrwService.deleteById(idsArray[i]);
            }
        }
        return ajaxJson;
    }

    /**
     * Dscription: 跳转到任务分配页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 12:30
     */
    @RequestMapping(value = "fprw", method={RequestMethod.GET, RequestMethod.POST})
    public String fprw(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        String sbid = scglRgsbService.selectById(id).getSbid();
        String sbmc = sbglService.selectById(sbid).getSbmc();
        model.addAttribute("fpsbid" ,id);
        model.addAttribute("sbmc" ,sbmc);
        return display("fprw");
    }

    /**
     * Dscription: 转到添加任务页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 10:13
     */
    @RequestMapping(value = "addRw", method={RequestMethod.GET, RequestMethod.POST})
    public String addRw(String fpsbid ,HttpServletRequest request, HttpServletResponse response, Model model){
         model.addAttribute("fpsbid", fpsbid);
        //得到所有计划信息
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("RQ", false);
        wrapper.eq("SFWC","0");
        List<ScjhglHtgl> jhList = scjhglHtglService.selectList(wrapper);
         model.addAttribute("jhglList", jhList);
        return display("addRw");
     }

     /**
      * Dscription: 保存任务信息
      * @author : Kevin Du
      * @version : 1.0
      * @date : 2018/9/27 13:30
      */
    @RequestMapping(value = "saveRw", method={RequestMethod.GET, RequestMethod.POST})
    public void saveRw(String ids ,String fpsbid ,HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String aaa[] = idsArray[i].split("@");
            String ljgybzid = aaa[0];
            String jhid = aaa[1];
            EntityWrapper<ScglRgrw> wrapper = new EntityWrapper<ScglRgrw>();
            wrapper.orderBy("PX");
            int index = scglRgrwService.selectList(wrapper).size();
            if (index == 0){
                int px = 1;
                ScglRgrw s = new ScglRgrw();
                s.setPx(px);
                s.setLjgybzid(ljgybzid);
                s.setFpsbid(fpsbid);
                s.setJhid(jhid);
                s.setSjwcl("0");
                scglRgrwService.insert(s);
            }
            else{
                int px = scglRgrwService.selectList(wrapper).get(index-1).getPx()+1;
                ScglRgrw s = new ScglRgrw();
                s.setPx(px);
                s.setLjgybzid(ljgybzid);
                s.setFpsbid(fpsbid);
                s.setJhid(jhid);
                s.setSjwcl("0");
                scglRgrwService.insert(s);
            }

        }
    }

    /**
     * Dscription: 转到分配工作量页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 16:04
     */
    @RequestMapping(value = "fpgzl", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgzl(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRgrw scglRgrw = scglRgrwService.selectById(id);
        String ljgybzid = scglRgrw.getLjgybzid();
        String xygzl = scglRgrw.getYwcl();
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int syslint = scglLjgybz.getSysl();
        int jhscslint = scglLjgybz.getJhscsl();
        String sysl = syslint-jhscslint+"";
        model.addAttribute("rgrwid", id);
        model.addAttribute("ljgybzid" ,ljgybzid);
        model.addAttribute("sysl", sysl);
        model.addAttribute("xygzl", xygzl);
        return display("fpgzl");
    }

    /**
     * Dscription: 保存工作量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 16:05
     */
    @RequestMapping(value = "saveGzl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGzl(String rgrwid, String gzl, String xygzl, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRgrw scglRgrw = new ScglRgrw();
        scglRgrw.setId(rgrwid);
        scglRgrw.setYwcl(gzl);
        scglRgrwService.updateById(scglRgrw);
        //并且要加到ljgybz下的jhscsl
       String ljgybzid = scglRgrwService.selectById(rgrwid).getLjgybzid();
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int gzli = 0;
        if (gzl!=null&&!gzl.equals("")){
            gzli = Integer.parseInt(gzl);
        }
        int xygzli = 0;
        if (xygzl!=null&&!xygzl.equals("")){
            xygzli = Integer.parseInt(xygzl);
        }
        scglLjgybz.setJhscsl(scglLjgybz.getJhscsl() + gzli - xygzli);
        scglLjgybzService.updateById(scglLjgybz);
    }

    /**
     * Dscription: 生成个人派工单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/2 11:13
     */
    @RequestMapping(value = "exportGrpgd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportGrpgd(String id, String rq, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
        //得到员工姓名
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        String xm = scglRcrwfp.getXm();
        //获取派工数据
        List<RgpgJcxxDTO> list = getRgpgxx(id, rq);
        createExcel(list, rq, xm);
    }

    /**
     * Dscription: 生成派工单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 11:03
     */
    @RequestMapping(value = "createPgd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void createPgd(String rq, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //获取派工数据
        List<RgpgJcxxDTO> list = getRgpgxx(null, rq);
        createExcel(list, rq, null);

    }

    public void createExcel(List<RgpgJcxxDTO> list, String rq, String xm) throws IOException {
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("日工派工单");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 2800);
        sheet1.setColumnWidth(1, 2500);
        sheet1.setColumnWidth(2, 3300);
        sheet1.setColumnWidth(3, 3300);
        sheet1.setColumnWidth(4, 3300);
        sheet1.setColumnWidth(5, 3300);
        sheet1.setColumnWidth(6, 3200);
        sheet1.setColumnWidth(7, 3200);
        sheet1.setColumnWidth(8, 2000);
        sheet1.setColumnWidth(9, 4500);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //表头
        Row row0 = sheet1.createRow(0);
        row0.setHeightInPoints(60);
        Cell cell00 = row0.createCell(0);
        Cell cell01 = row0.createCell(1);
        Cell cell02 = row0.createCell(2);
        Cell cell03 = row0.createCell(3);
        Cell cell04 = row0.createCell(4);
        Cell cell05 = row0.createCell(5);
        Cell cell06 = row0.createCell(6);
        Cell cell07 = row0.createCell(7);
        Cell cell08 = row0.createCell(8);
        Cell cell09 = row0.createCell(9);

        cell00.setCellValue("日期");
        cell01.setCellValue("员工名称");
        cell02.setCellValue("设备名称");
        cell03.setCellValue("计划编号");
        cell04.setCellValue("零件图号");
        cell05.setCellValue("零件名称");
        cell06.setCellValue("工艺大类名称");
        cell07.setCellValue("工艺小类名称");
        cell08.setCellValue("应完成量");
        cell09.setCellValue("备注");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);
        cell06.setCellStyle(style);
        cell07.setCellStyle(style);
        cell08.setCellStyle(style);
        cell09.setCellStyle(style);

        if (list!=null){
            for (int i=0;i<list.size();i++){
                RgpgJcxxDTO c = list.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(60);

                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);
                Cell cell4 = row.createCell(4);
                Cell cell5 = row.createCell(5);
                Cell cell6 = row.createCell(6);
                Cell cell7 = row.createCell(7);
                Cell cell8 = row.createCell(8);
                Cell cell9 = row.createCell(9);

                //给单元格设值
                cell0.setCellValue(rq);
                cell1.setCellValue(c.getXm());
                cell2.setCellValue(c.getSbmc());
                cell3.setCellValue(c.getJhbh());
                cell4.setCellValue(c.getLjth());
                cell5.setCellValue(c.getLjmc());
                cell6.setCellValue(c.getGydlmc());
                cell7.setCellValue(c.getGyxlmc());
                cell8.setCellValue(c.getYwcl());
                cell9.setCellValue(c.getBz());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
                cell6.setCellStyle(style);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);
                cell9.setCellStyle(style);

            }
        }

        String io = "";
        if (xm != null) {
            io = "d:\\bingzhengjixie\\生产\\"+rq+" "+xm+" 的 日工派工单.xlsx";
        } else {
            io = "d:\\bingzhengjixie\\生产\\"+rq+"日工派工单.xlsx";
        }

        //创建流
        FileOutputStream fileOut = new FileOutputStream(io);
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * @param border 边框宽度
     * @param region 合并单元格区域范围
     * @param sheet
     * @param wb
     */
    public static void setRegionBorder(int border, CellRangeAddress region, Sheet sheet,Workbook wb){
        RegionUtil.setBorderBottom(border,region, sheet, wb);
        RegionUtil.setBorderLeft(border,region, sheet, wb);
        RegionUtil.setBorderRight(border,region, sheet, wb);
        RegionUtil.setBorderTop(border,region, sheet, wb);
    }

    public List<RgpgJcxxDTO> getRgpgxx(String id, String rq){
        List<RgpgdDTO> rgpgdDTOList = scglRcrwfpService.getRgpgd(id, rq);
            //从数据库里面得到原始数据
            List<RgpgJcxxDTO> rgpgJcxx = scglRcrwfpService.getRgpgJcxx(id, rq);
            //如果没有任何数据的话，直接返回null
        
            return rgpgJcxx;
    }

    /**
     * Dscription: 任务详情
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/7 13:41
     */
    @RequestMapping(value = "rwxq", method={RequestMethod.GET, RequestMethod.POST})
    public String rwxq(String rq, String id, String xm, HttpServletRequest request, HttpServletResponse response, Model model){
        List<RgpgJcxxDTO> rgpgJcxx = scglRcrwfpService.getRgpgJcxx(id, rq);
        model.addAttribute("xm", xm);
        List<String> getData = new ArrayList<String>();
        for (RgpgJcxxDTO r : rgpgJcxx) {
            String nr = r.getSbmc()+" - "+r.getLjmc()+" - "+r.getLjth()+" - "+r.getGyxlmc()+" - "+r.getYwcl()+"件";
            getData.add(nr);
        }
        model.addAttribute("getData" , getData);
        return display("rwxq");
    }

    /**
     * Dscription: 分配包工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/17 16:32
     */
    @RequestMapping(value = "fpbgrw", method={RequestMethod.GET, RequestMethod.POST})
    public String fpbgrw(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("bgList");
    }

    /**
     * Dscription: 转到查看包工任务页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/4 15:29
     */
    @RequestMapping(value = "ckbgrw",method = {RequestMethod.GET,RequestMethod.POST})
    public String ckbgrw(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        Grgl grgl = grglService.selectById(id);
        model.addAttribute("grgl", grgl);
        return display("ckbgrw");
    }

    /**
     * Dscription: 修改承包金额
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/17 17:06
     */
    @RequestMapping(value = "xgcbje",method = {RequestMethod.GET,RequestMethod.POST})
    public String xgcbje(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(id);
        model.addAttribute("bgrwfp",scglBgrwfp);
        //查看如果有表里有数据，就把把数据代入
        EntityWrapper<ScglBgmx> wrapper = new EntityWrapper<ScglBgmx>();
        wrapper.eq("BGRWFPID", id);
        ScglBgmx scglBgmx = scglBgmxService.selectOne(wrapper);
        model.addAttribute("scglBgmx", scglBgmx);
        return display("xgcbje");
    }

    /**
     * Dscription: 保存包工明细
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/17 17:33
     */
    @RequestMapping(value = "saveBgmx",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveBgmx(String id, String cbje, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgmx scglBgmx = scglBgmxService.selectById(id);
        float cbjef = 0;
        float yjqs = 0;
        if (scglBgmx.getCbje()!=null&&!scglBgmx.getCbje().equals("")){
            cbjef = Float.parseFloat(scglBgmx.getCbje());
        }
        if (cbje!=null&&!cbje.equals("")){
            yjqs = Float.parseFloat(cbje);
        }
        cbjef = cbjef - yjqs;
        scglBgmx.setCbje(cbjef+"");
        scglBgmxService.updateById(scglBgmx);
    }

    /**
     * Dscription: 修改备注
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/3/5 7:18
     */
    @RequestMapping(value = "xgbz",method = {RequestMethod.GET,RequestMethod.POST})
    public String xgbz(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("scglRcrwfp" ,scglRcrwfp);
        return display("xgbz");
    }

    /**
     * Dscription: 保存修改备注
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/3/5 7:39
     */
    @RequestMapping(value = "saveXgbz",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveXgbz(String id, String bz, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        scglRcrwfp.setBz(bz);
        scglRcrwfpService.updateById(scglRcrwfp);
    }
}
