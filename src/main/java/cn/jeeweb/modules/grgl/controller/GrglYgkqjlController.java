package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.dto.YgkqjlDTO;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.mapper.GrglYgxzglMapper;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglYgkqjlService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
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
import java.util.Date;
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
        //查看表里有没有今天的员工考勤信息
        EntityWrapper<GrglYgkqjl> wrapper = new EntityWrapper<GrglYgkqjl>();
        wrapper.eq("RQ", currentTime);
        int count = grglYgkqjlService.selectCount(wrapper);
        //如果为空，就要把所有员工信息加入表内
        if (count == 0){
            EntityWrapper<Grgl> wrapper1 = new EntityWrapper<Grgl>();
            List<Grgl> grgls = grglService.selectList(wrapper1);
            for (int i=0;i<grgls.size();i++){
                Grgl grgl = grgls.get(i);
                GrglYgkqjl grglYgkqjl = new GrglYgkqjl();
                grglYgkqjl.setYgid(grgl.getId());
                grglYgkqjl.setRq(currentTime);
                grglYgkqjlService.insert(grglYgkqjl);
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


        //需要根据上午和加班的出勤情况，决定餐补（上午8块，加班7块）
        float cb = 0;
        if (ysYgkqjl.getSw()!=null){
            if (ysYgkqjl.getSw().equals("1")){
                cb = cb - 8;
            }
        }
        if (ysYgkqjl.getJb()!=null){
            if (ysYgkqjl.getJb().equals("1")){
                cb = cb - 7;
            }
        }
        if (grglYgkqjl.getSw().equals("1")){
            cb = cb + 8;
        }
        if (grglYgkqjl.getJb().equals("1")){
            cb = cb + 7;
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
