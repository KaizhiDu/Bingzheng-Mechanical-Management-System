package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.dto.YgkqjlDTO;
import cn.jeeweb.modules.grgl.entity.*;
import cn.jeeweb.modules.grgl.mapper.GrglYgxzglMapper;
import cn.jeeweb.modules.grgl.service.*;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.service.IScglRcrwfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Dscription: 员工管理 - 员工考勤记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/2 14:32
 */
@Controller
@RequestMapping("${admin.url.prefix}/grgl/ygkqjl")
@RequiresPathPermission("grgl:ygkqjl")
public class GrglYgkqjlController extends BaseCRUDController<GrglYgkqjl, String> {

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    /** 员工管理 - 员工考勤监控Service*/
    @Autowired
    private IGrglYgkqjlService grglYgkqjlService;

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /** 员工薪水管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**新资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

    /**员工考勤记录 - 基础数据*/
    @Autowired
    private IGrglYgkqjlJcsjService grglYgkqjlJcsjService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<Grgl> wrapper0 = new EntityWrapper<Grgl>();
        List<Grgl> ygsjList = grglService.selectList(wrapper0);
        model.addAttribute("ygsjList", ygsjList);
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        model.addAttribute("currentTime", currentTime);

        String[] dateArray = currentTime.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);
        //查一下有没有当前年度和月份的信息，如果没有的话，插入
        EntityWrapper<GrglYgxzgl> wrapper01 = new EntityWrapper<GrglYgxzgl>();
        wrapper01.eq("ND", nd);
        wrapper01.eq("YF", yf);
        int count0 = grglYgxzglService.selectCount(wrapper01);
        if (count0 == 0){
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
                hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje + jl - kk + zcqgz;

                grglYgxzgl.setRggz(rggz+"");
                grglYgxzgl.setHj(hj+"");
                grglYgxzgl.setZcqgz(zcqgz+"");

                grglYgxzglService.insert(grglYgxzgl);
                //计算日工工资和总工资
                //grglYgxzglService.countGz(grglYgxzgl);
            }

        }

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

        //查看表里有没有这几天的信息的员工考勤信息
        for(int i=0;i<dates.length;i++){
            EntityWrapper<GrglYgkqjl> wrapper = new EntityWrapper<GrglYgkqjl>();
            wrapper.eq("RQ", dates[i]);
            int count = grglYgkqjlService.selectCount(wrapper);
            //如果为空，就要把所有员工信息加入表内
            if (count == 0){
                EntityWrapper<Grgl> wrapper1 = new EntityWrapper<Grgl>();
                List<Grgl> grgls = grglService.selectList(wrapper1);
                for (int j=0;j<grgls.size();j++){
                    Grgl grgl = grgls.get(j);
                    GrglYgkqjl grglYgkqjl = new GrglYgkqjl();
                    grglYgkqjl.setYgid(grgl.getId());
                    grglYgkqjl.setRq(dates[i]);
                    grglYgkqjlService.insert(grglYgkqjl);
                }
            }
        }

    }

    /**
     * Dscription: 展示所有考勤信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:29
     */
    @RequestMapping(value = "ajaxGrglYgkqjlList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<YgkqjlDTO> ajaxGrglYgkqjlList(String currentTime, Queryable queryable, YgkqjlDTO ygkqjlDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ygkqjlDTO.getRq()==null||ygkqjlDTO.getRq().equals("")){
            ygkqjlDTO.setRq(currentTime);
        }
        PageJson<YgkqjlDTO> pageJson = grglYgkqjlService.ajaxGrglYgkqjlList(queryable,ygkqjlDTO);
        return pageJson;
    }


    /**
     * Dscription: 转到记录考勤页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 14:58
     */
    @RequestMapping(value = "jlkq", method={RequestMethod.GET, RequestMethod.POST})
    public String jlkq(String rq, String id, HttpServletRequest request, HttpServletResponse response, Model model){
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        //得到考勤数据
        GrglYgkqjl ygkqjl = grglYgkqjlService.selectById(id);
        model.addAttribute("ygkqjl", ygkqjl);
        //得到员工数据
        Grgl grgl = grglService.selectById(ygkqjl.getYgid());
        model.addAttribute("grgl" ,grgl);
        model.addAttribute("rq" ,rq);
        return display("jlkq");
    }

    /**
     * Dscription: 保存考勤信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 15:40
     */
    @RequestMapping(value = "saveKq", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saceKq(String rq, String id, String ygid, String checked, String qqyy, HttpServletRequest request, HttpServletResponse response, Model model){
        //先拿到原始数据
        GrglYgkqjl ysYgkqjl = grglYgkqjlService.selectById(id);
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        //分析得到上午，下午信息
        String sw = "0";
        String xw = "0";
        String jb = "0";
        //如果选中了上午或者下午的话
        if (!checked.equals("")){
            String kqjl[] = checked.split(",");
            for (int i=1;i<kqjl.length;i++){
                if (kqjl[i].equals("sw")){
                    sw = "1";
                }
                if (kqjl[i].equals("xw")){
                    xw = "1";
                }
                if (kqjl[i].equals("jb")){
                    jb = "1";
                }
            }
        }
        else{

        }
        GrglYgkqjl grglYgkqjl = new GrglYgkqjl();
        grglYgkqjl.setRq(currentTime);
        grglYgkqjl.setSw(sw);
        grglYgkqjl.setXw(xw);
        grglYgkqjl.setJb(jb);
        grglYgkqjl.setYgid(ygid);
        grglYgkqjl.setQqyy(qqyy);
        //如果id为"",则插入
        if (id.equals("")){
            grglYgkqjlService.insert(grglYgkqjl);
        }
        //否则更新
        else{
            grglYgkqjl.setId(id);
            grglYgkqjlService.updateById(grglYgkqjl);
        }


        //先拿到考勤基础数据
        EntityWrapper<GrglYgkqjlJcsj> wrapper0 = new EntityWrapper<GrglYgkqjlJcsj>();
        List<GrglYgkqjlJcsj> grglYgkqjlJcsjs = grglYgkqjlJcsjService.selectList(wrapper0);
        float swcf = 0;
        float jbcf = 0;
        float cqxsgz = 0;
        for (GrglYgkqjlJcsj g : grglYgkqjlJcsjs) {
            if (g.getMc().equals("上午餐费")){
                swcf = Float.parseFloat(g.getSz());
            }
            if (g.getMc().equals("加班餐费")){
                jbcf = Float.parseFloat(g.getSz());
            }
            if (g.getMc().equals("出勤工资(每小时)")){
                cqxsgz = Float.parseFloat(g.getSz());
            }
        }

        //需要根据上午和加班的出勤情况，决定餐补; 上午和下午决定出勤工资
        float cb = 0;
        float cqgzf = 0;
        if (ysYgkqjl.getSw()!=null){
            if (ysYgkqjl.getSw().equals("1")){
                cb = cb - swcf;
                cqgzf = cqgzf - 4*cqxsgz;
            }
        }
        if (ysYgkqjl.getXw()!=null){
            if (ysYgkqjl.getXw().equals("1")){
                cqgzf = cqgzf - 4*cqxsgz;
            }
        }
        if (ysYgkqjl.getJb()!=null){
            if (ysYgkqjl.getJb().equals("1")){
                cb = cb - jbcf;
            }
        }
        if (grglYgkqjl.getSw().equals("1")){
            cb = cb + swcf;
            cqgzf = cqgzf + 4*cqxsgz;
        }
        if (grglYgkqjl.getXw().equals("1")){
            cqgzf = cqgzf + 4*cqxsgz;
        }
        if (grglYgkqjl.getJb().equals("1")){
            cb = cb + jbcf;
        }
        //得到年月
        String[] dateArray = rq.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);
        EntityWrapper<GrglYgxzgl> wrapper = new EntityWrapper<GrglYgxzgl>();
        wrapper.eq("ND", nd);
        wrapper.eq("YF", yf);
        wrapper.eq("YGID", ygid);
        GrglYgxzgl grglYgxzgl = grglYgxzglService.selectOne(wrapper);

        if (grglYgxzgl.getCq()==null){
            String cq = cb+"";
            grglYgxzgl.setCq(cq);
        }
        else{
            cb = cb + Float.parseFloat(grglYgxzgl.getCq());
            String cq = cb+"";
            grglYgxzgl.setCq(cq);
        }

         if (grglYgxzgl.getCqgz()==null){
             grglYgxzgl.setCqgz(cqgzf+"");
         }
         else{
             cqgzf = cqgzf + Float.parseFloat(grglYgxzgl.getCqgz());
             grglYgxzgl.setCqgz(cqgzf+"");
         }

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
        float cqgz = 0;
        float cqgz2 = 0;
        float zcqgz = 0;
        float sx = 0;
        float gs = 0;
        float cbje2 = 0;
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
            cbje2 = Float.parseFloat(grglYgxzgl.getCbje());
        }
        if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
            jl = Float.parseFloat(grglYgxzgl.getJl());
        }
        if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
            kk = Float.parseFloat(grglYgxzgl.getKk());
        }

        zcqgz = cqgz + cqgz2;
        rggz = gs * sx;
        hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje2 + jl - kk + zcqgz;

        grglYgxzgl.setZcqgz(zcqgz+"");
        grglYgxzgl.setRggz(rggz+"");
        grglYgxzgl.setHj(hj+"");

        grglYgxzglService.updateById(grglYgxzgl);
        //计算日工工资和总工资
        //grglYgxzglService.countGz(grglYgxzgl);
    }

    /**
     * Dscription: 设置考勤基础数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/4 13:32
     */
    @RequestMapping(value = "Szkqjcsj", method={RequestMethod.GET, RequestMethod.POST})
    public String Szkqjcsj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<GrglYgkqjlJcsj> wrapper = new EntityWrapper<GrglYgkqjlJcsj>();
        List<GrglYgkqjlJcsj> list = grglYgkqjlJcsjService.selectList(wrapper);
        model.addAttribute("list", list);
        return display("Szkqjcsj");
    }

    /**
     * Dscription: 保存基础数据信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/4 14:06
     */
    @RequestMapping(value = "saveJcsj", method={RequestMethod.GET, RequestMethod.POST})
    public void saveJcsj(GrglYgkqjlJcsj grglYgkqjlJcsj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (grglYgkqjlJcsj.getSz()==null||grglYgkqjlJcsj.getSz().equals("")){
            grglYgkqjlJcsj.setSz("0");
        }
        grglYgkqjlJcsjService.updateById(grglYgkqjlJcsj);
    }

}
