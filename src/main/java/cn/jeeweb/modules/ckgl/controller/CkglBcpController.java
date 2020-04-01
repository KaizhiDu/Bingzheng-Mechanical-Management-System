package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglBcpCkMx;
import cn.jeeweb.modules.ckgl.entity.CkglBcpMx;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.service.ICkglBcpCkMxService;
import cn.jeeweb.modules.ckgl.service.ICkglBcpMxService;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
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
import java.util.Date;
import java.util.List;

/**
 * Dscription: 仓库管理 - 未完成半成品
 *
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bcp/ywcbcp")
@RequiresPathPermission("ckgl:ywcbcp")
public class CkglBcpController extends BaseCRUDController<CkglBcp, String> {

    /**
     * 合同管理Service
     */
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**
     * 仓库管理 - 半成品Service
     */
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**
     * 仓库管理 - 成品Service
     */
    @Autowired
    private ICkglCpService ckglCpService;

    /**
     * 仓库管理 - 半成品明细Service
     */
    @Autowired
    private ICkglBcpMxService ckglBcpMxService;

    @Autowired
    private ICkglBcpCkMxService ckglBcpCkMxService;

    /**
     * Dscription: 搜索项和前置内容
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response) {
        //计划
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("rq", false);
        wrapper.eq("SFWC", "0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        //图号和名称
        EntityWrapper<CkglBcp> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("SFSWWCBCP", "0");
        List<CkglBcp> ckglBcps = ckglBcpService.selectList(wrapper1);
        model.addAttribute("bcpList", ckglBcps);
    }

    /**
     * Dscription: 查所有已完成的半成品
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @RequestMapping(value = "ajaxBcpList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBcp> ajaxBcpList(Queryable queryable, CkglBcp ckglBcp, HttpServletRequest request, HttpServletResponse response, Model model) {
        PageJson<CkglBcp> pageJson = ckglBcpService.ajaxBcpList(queryable, ckglBcp);
        return pageJson;
    }

    /**
     * Dscription: 查所有未完成的半成品
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @RequestMapping(value = "ajaxBcpList2", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBcp> ajaxBcpList2(Queryable queryable, CkglBcp ckglBcp, HttpServletRequest request, HttpServletResponse response, Model model) {
        PageJson<CkglBcp> pageJson = ckglBcpService.ajaxBcpList2(queryable, ckglBcp);
        return pageJson;
    }

    /**
     * Dscription: 转到已完成页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:45
     */
    @RequestMapping(value = "ywcbcp", method = {RequestMethod.GET, RequestMethod.POST})
    public String ywcbcp(HttpServletRequest request, HttpServletResponse response, Model model) {
        return display("ywcbcp");
    }

    /**
     * Dscription: 转到未完成页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:45
     */
    @RequestMapping(value = "wwcbcp", method = {RequestMethod.GET, RequestMethod.POST})
    public String wwcbcp(HttpServletRequest request, HttpServletResponse response, Model model) {
        return display("wwcbcp");
    }

    /**
     * Dscription: 半成品入成品库
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/12 13:30
     */
    @RequestMapping(value = "rcpk", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void rcpk(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        //先得到半成品信息
        CkglBcp ckglBcp = ckglBcpService.selectById(id);
        //复制一份到成品里面
        CkglCp ckglCp = new CkglCp();
        ckglCp.setJhbh(ckglBcp.getJhbh());
        ckglCp.setJhid(ckglBcp.getJhid());
        ckglCp.setLbjid(ckglBcp.getLbjid());
        ckglCp.setLbjmc(ckglBcp.getLbjmc());
        ckglCp.setLbjth(ckglBcp.getLbjth());
        ckglCp.setRksl(ckglBcp.getRksl());
        //根据图号判断，成品库里面有没有该信息
        EntityWrapper<CkglCp> wrapper = new EntityWrapper<CkglCp>();
        wrapper.eq("LBJTH", ckglBcp.getLbjth());
        //wrapper.eq("JHBH", ckglBcp.getJhbh());
        int count = ckglCpService.selectCount(wrapper);
        //插入
        if (count == 0) {
            ckglCpService.insert(ckglCp);
        }
        //更新
        else {
            CkglCp ckglCp1 = ckglCpService.selectOne(wrapper);
            int rksl = Integer.parseInt(ckglBcp.getRksl()) + Integer.parseInt(ckglCp1.getRksl());
            ckglCp1.setRksl(rksl + "");
            ckglCpService.updateById(ckglCp1);
        }

        //记录明细
        CkglBcpMx ckglBcpMx = new CkglBcpMx();
        String mx = "仓库管理员 于   " + currentDate + "   把   " + ckglBcp.getSyrksl() + "件   " + ckglBcp.getLbjmc() + "   转入成品库";
        ckglBcpMx.setMx(mx);
        ckglBcpMx.setRq(currentDate);
        ckglBcpMxService.insert(ckglBcpMx);

        //最后更新半成品里面的信息
        //ckglBcpService.deleteById(id);
        ckglBcp.setSyrksl("0");
        ckglBcpService.updateById(ckglBcp);

    }

    /**
     * Dscription: 转到手动入库页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/31 9:19
     */
    @RequestMapping(value = "createBcp", method = {RequestMethod.GET, RequestMethod.POST})
    public String createBcp(HttpServletRequest request, HttpServletResponse response, Model model) {
        //计划
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        return display("createBcp");
    }

    /**
     * Dscription: 保存半成品信息
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/31 11:46
     */
    @RequestMapping(value = "saveBcp", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBcp(CkglBcp ckglBcp, HttpServletRequest request, HttpServletResponse response, Model model) {
        //先判断半成品库里有没有该图号的零部件存在
        EntityWrapper<CkglBcp> wrapper = new EntityWrapper<CkglBcp>();
        wrapper.eq("LBJTH", ckglBcp.getLbjth());
        wrapper.eq("JHBH", ckglBcp.getJhbh());
        int count = ckglBcpService.selectCount(wrapper);
        if (count > 0) {
            CkglBcp ckglBcp1 = ckglBcpService.selectOne(wrapper);
            int rksl = 0;
            if (ckglBcp1.getRksl() != null && !ckglBcp1.getRksl().equals("")) {
                rksl = Integer.parseInt(ckglBcp1.getRksl());
            }
            int newRksl = 0;
            if (ckglBcp.getRksl() != null && !ckglBcp.getRksl().equals("")) {
                newRksl = Integer.parseInt(ckglBcp.getRksl());
            }
            rksl = rksl + newRksl;
            ckglBcp1.setRksl(rksl + "");
            ckglBcpService.updateById(ckglBcp1);
        } else {
            CkglBcp ckgl = new CkglBcp();
            ckgl.setJhid("");
            ckgl.setJhbh(ckglBcp.getJhbh());
            ckgl.setLbjid("");
            ckgl.setLbjmc(ckglBcp.getLbjmc());
            ckgl.setLbjth(ckglBcp.getLbjth());
            ckgl.setKczl("08");
            ckgl.setSfswwcbcp("0");
            ckgl.setRksl(ckglBcp.getRksl());
            ckglBcpService.insert(ckgl);

        }
    }

    /**
     * Dscription: 转到查看入成品库详情页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/31 13:10
     */
    @RequestMapping(value = "checkRcpkxq", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkRcpkxq(HttpServletRequest request, HttpServletResponse response, Model model) {
        EntityWrapper<CkglBcpMx> wrapper = new EntityWrapper<CkglBcpMx>();
        wrapper.orderBy("RQ", false);
        List<CkglBcpMx> ckglBcpMxes = ckglBcpMxService.selectList(wrapper);
        model.addAttribute("bcpMx", ckglBcpMxes);
        return display("checkRcpkxq");
    }

    @RequestMapping(value = "checkCklyxq", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkCklyxq(HttpServletRequest request, HttpServletResponse response, Model model) {
        EntityWrapper<CkglBcpCkMx> wrapper = new EntityWrapper<CkglBcpCkMx>();
        wrapper.orderBy("RQ", false);
        List<CkglBcpCkMx> ckglBcpCkMxes = ckglBcpCkMxService.selectList(wrapper);
        model.addAttribute("bcpMx", ckglBcpCkMxes);
        return display("checkCklyxq");
    }

    /**
     * Dscription: 转到出库页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/3 20:14
     */
    @RequestMapping(value = "ck", method = {RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        CkglBcp ckglBcp = ckglBcpService.selectById(id);
        model.addAttribute("ckglBcp", ckglBcp);
        return display("ck");
    }

    /**
     * Dscription: 保存出库信息
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/3 20:29
     */
    @RequestMapping(value = "saveCk", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveCk(String id, String cksl, String lyr, HttpServletRequest request, HttpServletResponse response, Model model) {
        int cksli = 0;
        if (cksl != null && !cksl.equals("")) {
            cksli = Integer.parseInt(cksl);
        }
        CkglBcp ckglBcp = ckglBcpService.selectById(id);
        int kc = Integer.parseInt(ckglBcp.getSyrksl());
        kc = kc - cksli;
        //如果kc为0，就删除；否则更新
       // if (kc == 0) {
            //ckglBcpService.deleteById(id);
       // } else {
            ckglBcp.setSyrksl(kc + "");
            ckglBcpService.updateById(ckglBcp);
       // }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);

        //记录明细
        CkglBcpCkMx ckglBcpCkMx = new CkglBcpCkMx();
        String mx = lyr + " 于   " + currentDate + "   把   " + cksl + "件   " + ckglBcp.getLbjmc() + "   领出半成品库";
        ckglBcpCkMx.setMx(mx);
        ckglBcpCkMx.setRq(currentDate);
        ckglBcpCkMxService.insert(ckglBcpCkMx);
    }
}