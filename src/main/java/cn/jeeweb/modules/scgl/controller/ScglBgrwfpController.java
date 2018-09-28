package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import cn.jeeweb.modules.sbgl.service.ISbglService;
import cn.jeeweb.modules.scgl.dto.*;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    /**生产管理-零件工艺编制Service*/
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
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        model.addAttribute("currentTime",currentTime);

        //判断表里面有没有该数据
        EntityWrapper<ScglBgrwfp> wrapper = new EntityWrapper<ScglBgrwfp>();
        wrapper.eq("RQ",currentTime);
        List<ScglBgrwfp> scglRcrwfps = scglBgrwfpService.selectList(wrapper);
        //如果表里面没有就插入数据
        if (scglRcrwfps.size()==0){
            List<ScglBgrwfp> list = new ArrayList<ScglBgrwfp>();
            List<YgsjDTO> ygsjList = scglBgrwfpService.getYgsj();
            for (YgsjDTO ygsjDTO: ygsjList) {
                ScglBgrwfp s = new ScglBgrwfp();
                s.setXb(ygsjDTO.getXb());
                s.setRq(currentTime);
                s.setXm(ygsjDTO.getXm());
                s.setZw(ygsjDTO.getZw());
                s.setYgid(ygsjDTO.getYgid());
                list.add(s);
            }
            scglBgrwfpService.insertBatch(list);
        }
    }

    /**
     * Dscription: 得到所有当天的员工的包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @RequestMapping(value = "ajaxBgrwfpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglBgrwfp> ajaxBgrwfpList(Queryable queryable, ScglBgrwfp scglBgrwfp, HttpServletRequest request, HttpServletResponse response, Model model){
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
    public void deleteSb(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglBgsbService.deleteById(idsArray[i]);
        }
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
    public void deleteRw(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglBgrwService.deleteById(idsArray[i]);
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

        ScglBgrw scglBgrw = scglBgrwService.selectById(id);
        String ljgybzid = scglBgrw.getLjgybzid();
        String xygzl = scglBgrw.getYwcl();
        int syslint = scglLjgybzService.selectById(ljgybzid).getSysl();
        String sysl = syslint+"";
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
    public void saveGzl(String bgrwid, String gzl, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglBgrw scglBgrw = new ScglBgrw();
        scglBgrw.setId(bgrwid);
        scglBgrw.setYwcl(gzl);
        scglBgrwService.updateById(scglBgrw);
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
    public void saveBgmx(ScglBgmx scglBgmx, HttpServletRequest request, HttpServletResponse response, Model model){
        //插入
        if (scglBgmx.getId().equals("")||scglBgmx.getId()==null){
            scglBgmx.setId(null);
            scglBgmxService.insert(scglBgmx);
        }
        //更新
        else {
            scglBgmxService.updateById(scglBgmx);
        }
    }
}
