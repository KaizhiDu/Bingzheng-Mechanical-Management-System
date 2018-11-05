package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import cn.jeeweb.modules.sbgl.service.ISbglService;
import cn.jeeweb.modules.scgl.dto.*;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 生产管理-包工任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 12:19
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/bgrwfp")
@RequiresPathPermission("scgl:bgrwfp")
public class ScglBgrwfpController extends BaseCRUDController<ScglBgrwfp, String> {

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**员工管理 - 员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**员工管理 - 薪资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglBgrwfpService scglBgrwfpService;

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

    @Autowired
    /**生产管理-零部件工艺编制Service*/
    private IScglLjgybzService scglLjgybzService;

    @Autowired
    /**包工-设备Service*/
    private IScglBgsbService scglBgsbService;

    @Autowired
    /**包工-任务Service*/
    private IScglBgrwService scglBgrwService;

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

        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
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

                grglYgxzgl.setZcqgz(zcqgz+"");
                grglYgxzgl.setRggz(rggz+"");
                grglYgxzgl.setHj(hj+"");

                grglYgxzglService.insert(grglYgxzgl);
                //计算日工工资和总工资
                //grglYgxzglService.countGz(grglYgxzgl);
            }

        }


//        //得到当前时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String currentTime = sdf.format(date);
//        model.addAttribute("currentTime",currentTime);
//
//        //判断表里面有没有该数据
//        EntityWrapper<ScglBgrwfp> wrapper = new EntityWrapper<ScglBgrwfp>();
//        wrapper.eq("RQ",currentTime);
//        List<ScglBgrwfp> scglRcrwfps = scglBgrwfpService.selectList(wrapper);
//        //如果表里面没有就插入数据
//        if (scglRcrwfps.size()==0){
//            List<ScglBgrwfp> list = new ArrayList<ScglBgrwfp>();
//            List<YgsjDTO> ygsjList = scglBgrwfpService.getYgsj();
//            for (YgsjDTO ygsjDTO: ygsjList) {
//                ScglBgrwfp s = new ScglBgrwfp();
//                s.setXb(ygsjDTO.getXb());
//                s.setRq(currentTime);
//                s.setXm(ygsjDTO.getXm());
//                s.setZw(ygsjDTO.getZw());
//                s.setYgid(ygsjDTO.getYgid());
//                list.add(s);
//            }
//            scglBgrwfpService.insertBatch(list);
//        }
    }

    /**
     * Dscription: 得到所有当天的员工的包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @RequestMapping(value = "ajaxBgrwfpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglBgrwfp> ajaxBgrwfpList(String ygid, Queryable queryable, ScglBgrwfp scglBgrwfp, HttpServletRequest request, HttpServletResponse response, Model model){
        scglBgrwfp.setYgid(ygid);
        PageJson<ScglBgrwfp> pageJson = scglBgrwfpService.ajaxBgrwfpList(queryable,scglBgrwfp);
        return pageJson;
    }

    /**
     * Dscription: 跳转到日常任务设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/28 13:14
     */
    @RequestMapping(value = "fpsb", method={RequestMethod.GET, RequestMethod.POST})
    public String fpsb(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(id);
        model.addAttribute("bgrwfp",scglBgrwfp);
        return display("fpsb");
    }

    /**
     * Dscription: 转到添加设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 14:38
     */
    @RequestMapping(value = "addSb", method={RequestMethod.GET, RequestMethod.POST})
    public String addSb(String bgrwfpid ,HttpServletRequest request, HttpServletResponse response, Model model){
        //设备分类
        EntityWrapper<SbglSbflgl> wrapper = new EntityWrapper();
        wrapper.orderBy("fldm");
        List<SbglSbflgl> sbflglList = sbglSbflglService.selectList(wrapper);
        model.addAttribute("list",sbflglList);
        model.addAttribute("bgrwfpid", bgrwfpid);
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
    public void saveSb(String ids ,String bgrwfpid, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            EntityWrapper<ScglBgsb> wrapper = new EntityWrapper<ScglBgsb>();
            wrapper.orderBy("PX");
            int index = scglBgsbService.selectList(wrapper).size();
            if (index == 0){
                int px = 1;
                ScglBgsb s = new ScglBgsb();
                s.setPx(px);
                s.setBgrwfpid(bgrwfpid);
                s.setSbid(idsArray[i]);
                scglBgsbService.insert(s);
            }
            else{
                int px = scglBgsbService.selectList(wrapper).get(index-1).getPx()+1;
                ScglBgsb s = new ScglBgsb();
                s.setPx(px);
                s.setBgrwfpid(bgrwfpid);
                s.setSbid(idsArray[i]);
                scglBgsbService.insert(s);
            }

        }
    }

    /**
     * Dscription: 包工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    @RequestMapping(value = "ajaxBgrwfpSbList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgsbDTO> ajaxBgrwfpSbList(Queryable queryable, BgsbDTO bgsbDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgsbDTO> pageJson = scglBgsbService.ajaxBgrwfpSbList(queryable,bgsbDTO);
        return pageJson;
    }

    /**
     * Dscription: 删除设备信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 9:55
     */
    @RequestMapping(value = "deleteSb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteSb(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        String idsArray[] = ids.split(",");
        Boolean flag = true;
        for (int i=0;i<idsArray.length;i++){
            //通过设备ID,找下属的任务。如果有任务则flag设为false
            EntityWrapper<ScglBgrw> wrapper = new EntityWrapper<ScglBgrw>();
            wrapper.eq("FPSBID", idsArray[i]);
            int count = scglBgrwService.selectCount(wrapper);
            if (count>0){
                flag = false;
                break;
            }
        }
        //如果已经分配了任务则不允许删除
        if (flag==true){
            for (int i=0;i<idsArray.length;i++){
                scglBgsbService.deleteById(idsArray[i]);
            }
            ajaxJson.setMsg("删除成功");
        }
        else{
            ajaxJson.setMsg("设备已分配任务，请先删除任务");
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
        String sbid = scglBgsbService.selectById(id).getSbid();
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
        List<SsxDTO> jhglList = scjhglHtglService.getJhList();
        model.addAttribute("jhglList", jhglList);
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
            EntityWrapper<ScglBgrw> wrapper = new EntityWrapper<ScglBgrw>();
            wrapper.orderBy("PX");
            int index = scglBgrwService.selectList(wrapper).size();
            if (index == 0){
                int px = 1;
                ScglBgrw s = new ScglBgrw();
                s.setPx(px);
                s.setLjgybzid(idsArray[i]);
                s.setFpsbid(fpsbid);
                scglBgrwService.insert(s);
            }
            else{
                int px = scglBgrwService.selectList(wrapper).get(index-1).getPx()+1;
                ScglBgrw s = new ScglBgrw();
                s.setPx(px);
                s.setLjgybzid(idsArray[i]);
                s.setFpsbid(fpsbid);
                scglBgrwService.insert(s);
            }

        }
    }

    /**
     * Dscription: 日工 - 添加的任务展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:30
     */
    @RequestMapping(value = "ajaxBgrwfpRwList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgrwDTO> ajaxBgrwfpRwList(Queryable queryable, BgrwDTO bgrwDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgrwDTO> pageJson = scglBgrwService.ajaxBgrwfpRwList(queryable,bgrwDTO);
        return pageJson;
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
            ScglBgrw scglBgrw = scglBgrwService.selectById(idsArray[i]);
            if (scglBgrw.getSjwcl()!=null){
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
                ScglBgrw scglBgrw = scglBgrwService.selectById(idsArray[i]);
                //零部件工艺编制下的计划生产数量应该 减去 应完成量
                String ljgybzid = scglBgrw.getLjgybzid();
                ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
                int ywcli = 0;
                if (scglBgrw.getYwcl()!=null&&!scglBgrw.getYwcl().equals("")){
                    ywcli = Integer.parseInt(scglBgrw.getYwcl());
                }
                int jhscsl = scglLjgybz.getJhscsl() - ywcli;
                scglLjgybz.setJhscsl(jhscsl);
                scglLjgybzService.updateById(scglLjgybz);
                //删除该任务
                scglBgrwService.deleteById(idsArray[i]);
            }
        }
        return ajaxJson;
    }

    /**
     * Dscription: 转到分配工作量页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 16:04
     */
    @RequestMapping(value = "fpgzl", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgzl(String id, HttpServletRequest request, HttpServletResponse response, Model model){

        ScglBgrw scglBgrw = scglBgrwService.selectById(id);
        String ljgybzid = scglBgrw.getLjgybzid();
        String xygzl = scglBgrw.getYwcl();
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int syslint = scglLjgybz.getSysl();
        int jhscslint = scglLjgybz.getJhscsl();
        String sysl = syslint-jhscslint+"";
        model.addAttribute("bgrwid", id);
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
    public void saveGzl(String bgrwid, String gzl, String xygzl, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgrw scglBgrw = new ScglBgrw();
        scglBgrw.setId(bgrwid);
        scglBgrw.setYwcl(gzl);
        scglBgrwService.updateById(scglBgrw);
        //并且要加到ljgybz下的jhscsl
        String ljgybzid = scglBgrwService.selectById(bgrwid).getLjgybzid();
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int gzli = 0;
        if (gzl!=null&&!gzl.equals("")){
            gzli = Integer.parseInt(gzl);
        }
        int xygzli = 0;
        if (xygzl!=null&&!xygzl.equals("")){
            xygzli = Integer.parseInt(xygzl);
        }
        scglLjgybz.setJhscsl(scglLjgybz.getJhscsl() + gzli -xygzli);
        scglLjgybzService.updateById(scglLjgybz);
    }

    /**
     * Dscription: 包工明细
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/28 15:24
     */
    @RequestMapping(value = "bgmx", method={RequestMethod.GET, RequestMethod.POST})
    public String bgmx(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(id);
        model.addAttribute("bgrwfp",scglBgrwfp);
        //查看如果有表里有数据，就把把数据代入
        EntityWrapper<ScglBgmx> wrapper = new EntityWrapper<ScglBgmx>();
        wrapper.eq("BGRWFPID", id);
        ScglBgmx scglBgmx = scglBgmxService.selectOne(wrapper);
        model.addAttribute("scglBgmx", scglBgmx);
        return display("bgmx");
    }

    /**
     * Dscription: 保存包工明细
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/28 16:14
     */
    @RequestMapping(value = "saveBgmx",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveBgmx(String rq, ScglBgmx scglBgmx, HttpServletRequest request, HttpServletResponse response, Model model){
        //更新包工任务分配日期
        String bgrwfpid = scglBgmx.getBgrwfpid();
        ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(bgrwfpid);
        scglBgrwfp.setRq(rq);
        scglBgrwfpService.updateById(scglBgrwfp);
        //插入
        if (scglBgmx.getId().equals("")||scglBgmx.getId()==null){
            scglBgmx.setId(null);
            scglBgmx.setSfwc("0");
            scglBgmxService.insert(scglBgmx);
        }
        //更新
        else {
            scglBgmxService.updateById(scglBgmx);
        }

    }

    /**
     * Dscription: 生成派工单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 11:03
     */
    @RequestMapping(value = "createPgd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void createPgd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //获得派工数据
        List<BgpgdDTO> list = getBgpgxx();

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("包工派工单");
        //设置单元格宽度
        sheet1.setColumnWidth(1, 3700);
        sheet1.setColumnWidth(2, 3700);
        sheet1.setColumnWidth(3, 3700);
        sheet1.setColumnWidth(4, 3700);
        sheet1.setColumnWidth(5, 3700);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //开始搞,得到所有最终内容展示集合
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                //出现一个存放最终内容结果的集合
                List<BgnrDTO> bgnrDTOList = new ArrayList<BgnrDTO>();
                BgpgdDTO bgpgd = list.get(i);
                if (bgpgd.getNr() == null) {
                    //放10个空值进去
                    for (int t = 0; t < 10; t++) {
                        BgnrDTO bgnrDTO = new BgnrDTO();
                        bgnrDTO.setSbmc("");
                        bgnrDTO.setNr("");
                        bgnrDTOList.add(bgnrDTO);
                    }
                } else {
                    String[] rwArray = bgpgd.getNr().split("dafenge");
                    for (int j = 0; j < rwArray.length; j++) {
                        String sbmc = rwArray[j].split("xiaofenge")[0];
                        String nr = rwArray[j].split("xiaofenge")[1];
                        BgnrDTO bgnrDTO = new BgnrDTO();
                        bgnrDTO.setSbmc(sbmc);
                        bgnrDTO.setNr(nr);
                        bgnrDTOList.add(bgnrDTO);
                    }
                    //检查一下现在最终内容集合的size
                    int size = bgnrDTOList.size();
                    int dtj = 10 - size;
                    for (int t = 0; t < dtj; t++) {
                        BgnrDTO bgnrDTO = new BgnrDTO();
                        bgnrDTO.setSbmc("");
                        bgnrDTO.setNr("");
                        bgnrDTOList.add(bgnrDTO);
                    }
                }

                //创建一行
                Row row0 = sheet1.createRow(i*15);
                row0.setHeightInPoints(35);
                //创建单元格
                Cell cell00 = row0.createCell(0);
                Cell cell01 = row0.createCell(1);
                Cell cell02 = row0.createCell(2);
                Cell cell03 = row0.createCell(3);
                Cell cell04 = row0.createCell(4);
                Cell cell05 = row0.createCell(5);

                //给单元格设值
                cell00.setCellValue("姓名");
                cell01.setCellValue(bgpgd.getXm());
                cell02.setCellValue("职位");
                cell03.setCellValue(bgpgd.getZw());
                cell04.setCellValue("日期");
                cell05.setCellValue(bgpgd.getRq());
                cell00.setCellStyle(style);
                cell01.setCellStyle(style);
                cell02.setCellStyle(style);
                cell03.setCellStyle(style);
                cell04.setCellStyle(style);
                cell05.setCellStyle(style);

                //第二行
                Row row1 = sheet1.createRow(i*15+1);
                row1.setHeightInPoints(35);
                //创建单元格
                Cell cell10 = row1.createCell(0);
                Cell cell11 = row1.createCell(1);
                Cell cell12 = row1.createCell(2);
                Cell cell13 = row1.createCell(3);
                //给单元格设值
                cell10.setCellValue("");
                cell11.setCellValue("");
                cell12.setCellValue("注释");
                cell13.setCellValue(bgpgd.getZs());
                cell10.setCellStyle(style);
                cell11.setCellStyle(style);
                cell12.setCellStyle(style);
                cell13.setCellStyle(style);

                //循环rgnrDTOList，放入值
                for (int a=0;a<bgnrDTOList.size();a++){
                    BgnrDTO bgnr = bgnrDTOList.get(a);
                    Row row = sheet1.createRow(i*15+2+a);
                    row.setHeightInPoints(30);
                    //创建单元格
                    Cell cell0 = row.createCell(0);
                    Cell cell1 = row.createCell(1);
                    Cell cell2 = row.createCell(2);
                    //给单元格设值
                    cell0.setCellValue("");
                    cell1.setCellValue(bgnr.getSbmc());
                    cell2.setCellValue(bgnr.getNr());
                    cell0.setCellStyle(style);
                    cell1.setCellStyle(style);
                }


                //合并单元格
                sheet1.addMergedRegion(new CellRangeAddress(i*15+2,i*15+11,0,0));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+1,i*15+1,3,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+2,i*15+2,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+3,i*15+3,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+4,i*15+4,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+5,i*15+5,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+6,i*15+6,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+7,i*15+7,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+8,i*15+8,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+9,i*15+9,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+10,i*15+10,2,5));
                sheet1.addMergedRegion(new CellRangeAddress(i*15+11,i*15+11,2,5));

                //给合并的单元格加边框
                setRegionBorder(1,new CellRangeAddress(i*15+2,i*15+11,0,0),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+1,i*15+1,3,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+2,i*15+2,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+3,i*15+3,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+4,i*15+4,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+5,i*15+5,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+6,i*15+6,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+7,i*15+7,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+8,i*15+8,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+9,i*15+9,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+10,i*15+10,2,5),sheet1,wb);
                setRegionBorder(1,new CellRangeAddress(i*15+11,i*15+11,2,5),sheet1,wb);

                }
            }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\"+currentTime+"包工派工单.xlsx");
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
    public static void setRegionBorder(int border, CellRangeAddress region, Sheet sheet, Workbook wb){
        RegionUtil.setBorderBottom(border,region, sheet, wb);
        RegionUtil.setBorderLeft(border,region, sheet, wb);
        RegionUtil.setBorderRight(border,region, sheet, wb);
        RegionUtil.setBorderTop(border,region, sheet, wb);
    }

    /**
     * Dscription: 得到包工显示数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:24
     */
    public List<BgpgdDTO> getBgpgxx(){
        List<BgpgdDTO> bgpgdDTOList = scglBgrwfpService.getBgpgd();
        //从数据库里面得到原始数据
        List<BgpgJcxxDTO> bgpgJcxxList = scglBgrwfpService.getBgpgJcxx();
        //如果没有任何数据的话，直接返回null
        if (bgpgJcxxList.size()==0){
            return null;
        }
        else{

            //现在把所有单独一个的都加上一个""值
            List<BgpgJcxxDTO> bgpgJcxxList2 = new ArrayList<BgpgJcxxDTO>();
            for (int i=1;i<bgpgJcxxList.size();i++){
                BgpgJcxxDTO r1 = bgpgJcxxList.get(i);
                BgpgJcxxDTO r2 = bgpgJcxxList.get(i-1);
                if (!r1.getId().equals(r2.getId())){
                    //需要复制""
                        //入r2，然后入""
                        bgpgJcxxList2.add(r2);
                        //复制
                                BgpgJcxxDTO bb = new BgpgJcxxDTO();
                                bb.setId(r2.getId());
                                bb.setSbmc("");
                                bb.setJhbh("");
                                bb.setLjmc("");
                                bb.setGydlmc("");
                                bb.setGyxlmc("");
                                bb.setYwcl("");
                                bgpgJcxxList2.add(bb);

                        //最后一个的话，入r1，然后入""
                        if (i==bgpgJcxxList.size()-1){
                            bgpgJcxxList2.add(r1);
                            BgpgJcxxDTO bbb = new BgpgJcxxDTO();
                            bbb.setId(r1.getId());
                            bbb.setSbmc("");
                            bbb.setJhbh("");
                            bbb.setLjmc("");
                            bbb.setGydlmc("");
                            bbb.setGyxlmc("");
                            bbb.setYwcl("");
                            bgpgJcxxList2.add(bbb);
                        }


                }
                else{
                    bgpgJcxxList2.add(r2);
                    if (i==bgpgJcxxList.size()-1){
                        bgpgJcxxList2.add(r1);
                    }
                }
            }



            BgpgJcxxDTO r0 = bgpgJcxxList2.get(0);
            String rw = r0.getSbmc()+"xiaofenge"+r0.getJhbh()+"-"+r0.getLjmc()+"-"+r0.getGydlmc()+"-"+r0.getGyxlmc()+"-"+r0.getYwcl()+"件dafenge";
                for (int i=1;i<bgpgJcxxList2.size();i++){
                    BgpgJcxxDTO r1 = bgpgJcxxList2.get(i);
                    BgpgJcxxDTO r2 = bgpgJcxxList2.get(i-1);

                    //id相同，r1继续插入
                    if (r1.getId().equals(r2.getId())){
                        rw = rw + r1.getSbmc()+"xiaofenge"+r1.getJhbh()+"-"+r1.getLjmc()+"-"+r1.getGydlmc()+"-"+r1.getGyxlmc()+"-"+r1.getYwcl()+"件dafenge";
                    }
                    //id不相同
                    else{
                        for (BgpgdDTO r : bgpgdDTOList) {
                            if (r.getId().equals(r2.getId())){
                                rw = rw.substring(0,rw.length()-7);
                                r.setNr(rw);
                            }
                        }
                        //重设rw
                        rw = r1.getSbmc()+"xiaofenge"+r1.getJhbh()+"-"+r1.getLjmc()+"-"+r1.getGydlmc()+"-"+r1.getGyxlmc()+"-"+r1.getYwcl()+"件dafenge";
                    }

                    if (i==bgpgJcxxList2.size()-1){
                        //rw = rw + r1.getSbmc()+"xiaofenge"+r1.getJhbh()+"-"+r1.getLjmc()+"-"+r1.getGydlmc()+"-"+r1.getGyxlmc()+"-"+r1.getYwcl()+"件";
                        for (BgpgdDTO r : bgpgdDTOList) {
                            if (r.getId().equals(r2.getId())){
                                r.setNr(rw);
                            }
                        }
                    }

                }
        }

        for (BgpgdDTO b : bgpgdDTOList) {
            String nr = b.getNr().substring(b.getNr().length()-5,b.getNr().length());
            if (nr.equals("----件")){
                nr = b.getNr().substring(0,b.getNr().length()-14);
                b.setNr(nr);
            }
        }
        return bgpgdDTOList;
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
     * Dscription: 模块功能
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/4 15:43
     */
    @RequestMapping(value = "createBgrw",method = {RequestMethod.GET,RequestMethod.POST})
    public void createBgrw(String ygid, HttpServletRequest request, HttpServletResponse response, Model model){
        //得到当前时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String rq = sdf.format(date);
        Grgl grgl = grglService.selectById(ygid);
        String xm = grgl.getName();
        String xb = grgl.getGender();
        EntityWrapper<Xzzwfp> wrapper = new EntityWrapper<Xzzwfp>();
        wrapper.eq("YGID", ygid);
        Xzzwfp xzzwfp = grglXzzwfpService.selectOne(wrapper);
        String zw = xzzwfp.getZwid();
        ScglBgrwfp scglBgrwfp = new ScglBgrwfp();
        scglBgrwfp.setYgid(ygid);
        scglBgrwfp.setXm(xm);
        scglBgrwfp.setXb(xb);
        scglBgrwfp.setZw(zw);
//        scglBgrwfp.setRq(rq);
        scglBgrwfp.setSfwc("0");
        scglBgrwfpService.insert(scglBgrwfp);
    }

}
