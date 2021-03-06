package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import cn.jeeweb.modules.scgl.entity.ScglRcrwfp;
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

/**
 * @Description:    员工职位薪资分配
 * @Author:         杜凯之
 * @CreateDate:     2018/8/18 15:15
 * @Version:        1.0
 */
@Controller
@RequestMapping("${admin.url.prefix}/grgl/xzzwfp")
@RequiresPathPermission("grgl:xzzwfp")
public class GrglXzzwfpController extends BaseCRUDController<Xzzwfp, String> {

    /** 员工薪资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

    /** 员工薪水管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**日共任务分配*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**
    * @Description:    查出所有员工的职位薪资分配
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 14:51
    * @Version:        1.0
    */
    @RequestMapping(value = "queryAjax", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<YgzxxDTO> queryAjax(Queryable queryable, YgzxxDTO ygzxxDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<YgzxxDTO> pageJson = grglXzzwfpService.queryAjax(queryable,ygzxxDTO);
        return pageJson;
    }

    /**
    * @Description:    给员工分配薪资职位
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 15:56
    * @Version:        1.0
    */
    @RequestMapping(value = "setEmp", method={RequestMethod.GET, RequestMethod.POST})
    public String setEmp(HttpServletRequest request, HttpServletResponse response, Model model, String id){
        //根据id查到xzzwfp的信息
        Xzzwfp xzzwfp = grglXzzwfpService.selectById(id);
        model.addAttribute("xzzwfp",xzzwfp);
        return display("set");
    }

    /**
    * @Description:    保存职位薪资信息
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 18:06
    * @Version:        1.0
    */
    @RequestMapping(value = "saveXzzwfp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveXzzwfp(HttpServletRequest request, HttpServletResponse response, Model model, Xzzwfp xzzwfp){
        //拿到xzzwfpid 看看原来的表里有没有职位数据
        String zw = grglXzzwfpService.selectById( xzzwfp.getId()).getZwid();
        //如果xzzwfp的职位为空
        if (zw==null||zw.equals("")){
            //需要把该员工加入日工任务分配
            //首先要先判断日工任务分配里有没有数据

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

            for(int i=0;i<dates.length;i++){
                EntityWrapper<ScglRcrwfp> wrapper1 = new EntityWrapper<ScglRcrwfp>();
                wrapper1.eq("RQ", dates[i]);
                int count = scglRcrwfpService.selectCount(wrapper1);
                //有数据啥也不干, 没数据就要添一条数据
                if (count!=0){
                    ScglRcrwfp scglRcrwfp = new ScglRcrwfp();
                    scglRcrwfp.setRq(dates[i]);
                    scglRcrwfp.setYgid(xzzwfp.getYgid());
                    Grgl grgl = grglService.selectById(xzzwfp.getYgid());
                    scglRcrwfp.setXm(grgl.getName());
                    scglRcrwfp.setZw(xzzwfp.getZwid());
                    scglRcrwfp.setXb(grgl.getGender());
                    scglRcrwfpService.insert(scglRcrwfp);
                }
            }


        }


        //先更新本月的薪资表
        String ygid = xzzwfp.getYgid();
        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);
        EntityWrapper<GrglYgxzgl> wrapper = new EntityWrapper<GrglYgxzgl>();
        wrapper.eq("ND", nd);
        wrapper.eq("YF", yf);
        wrapper.eq("YGID", ygid);
        GrglYgxzgl grglYgxzgl = grglYgxzglService.selectOne(wrapper);
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
        hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje + jl - kk + zcqgz;

        grglYgxzgl.setZcqgz(zcqgz+"");
        grglYgxzgl.setRggz(rggz+"");
        grglYgxzgl.setHj(hj+"");
        grglYgxzglService.updateById(grglYgxzgl);
        //计算日工工资和总工资
        //grglYgxzglService.countGz(grglYgxzgl);


        //更新员工信息
        grglXzzwfpService.updateById(xzzwfp);


    }
}
