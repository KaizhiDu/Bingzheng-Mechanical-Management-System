package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.*;
import cn.jeeweb.modules.ckgl.service.*;
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
 * Dscription: 仓库管理 - 低值易耗品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 12:17
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/dzyhp")
@RequiresPathPermission("ckgl:dzyhp")
public class CkglDzyhpController extends BaseCRUDController<CkglDzyhp, String> {

    /**仓库管理 - 低值易耗品*/
    @Autowired
    private ICkglDzyhpService ckglDzyhpService;

    /**仓库管理 - 低值易耗品明细*/
    @Autowired
    private ICkglDzyhpMxService ckglDzyhpxMxService;

    /**仓库管理 - 大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "低值易耗品");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
    }

    /**
     * Dscription: 转到添加低值易耗品页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createDzyhp", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "低值易耗品");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        return display("createDzyhp");
    }

    /**
     * Dscription: 保存低值易耗品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveDzyhp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglDzyhp ckglDzyhp, HttpServletRequest request, HttpServletResponse response, Model model){
        String dlid = ckglDzyhp.getFldl();
        ckglDzyhp.setFldl(ckglDlService.selectById(dlid).getDlmc());
        ckglDzyhp.setKc("0");
        ckglDzyhpService.insert(ckglDzyhp);
    }

    /**
     * Dscription: 展示所有低值易耗品的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxDzyhpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglDzyhp> ajaxDzyhpList(Queryable queryable, CkglDzyhp ckglDzyhp, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglDzyhp.getFldl()!=null&&!ckglDzyhp.getFldl().equals("")){
            ckglDzyhp.setFldl(ckglDlService.selectById(ckglDzyhp.getFldl()).getDlmc());
        }
        PageJson<CkglDzyhp> pageJson = ckglDzyhpService.ajaxDzyhpList(queryable,ckglDzyhp);
        return pageJson;
    }

    /**
     * Dscription: 转到入库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "rk", method={RequestMethod.GET, RequestMethod.POST})
    public String rk(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(id);
        model.addAttribute("ckglDzyhp", ckglDzyhp);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveDzyhpkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String dzyhpid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglDzyhpMx ckglDzyhpMx = new CkglDzyhpMx();
        ckglDzyhpMx.setDzyhpid(dzyhpid);
        ckglDzyhpMx.setMx(mx);
        ckglDzyhpMx.setSj(date);
        ckglDzyhpxMxService.insert(ckglDzyhpMx);
        //更改标准件表
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(dzyhpid);
        float kc = 0;
        float zjl = 0;
        if (ckglDzyhp.getKc()!=null&&!ckglDzyhp.getKc().equals("")){
            kc = Float.parseFloat(ckglDzyhp.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglDzyhp.setKc(kc+"");
        ckglDzyhpService.updateById(ckglDzyhp);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(id);
        model.addAttribute("ckglDzyhp", ckglDzyhp);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveDzyhpkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String dzyhpid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+ly+" 于 "+currentDate+" 领取 "+cksl+" 件";
        //插入明细表
        CkglDzyhpMx ckglDzyhpMx = new CkglDzyhpMx();
        ckglDzyhpMx.setDzyhpid(dzyhpid);
        ckglDzyhpMx.setMx(mx);
        ckglDzyhpMx.setSj(date);
        ckglDzyhpxMxService.insert(ckglDzyhpMx);
        //更改标准件表
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(dzyhpid);
        float kc = 0;
        float zjl = 0;
        if (ckglDzyhp.getKc()!=null&&!ckglDzyhp.getKc().equals("")){
            kc = Float.parseFloat(ckglDzyhp.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            zjl = Float.parseFloat(cksl);
        }
        kc = kc - zjl;
        ckglDzyhp.setKc(kc+"");
        ckglDzyhpService.updateById(ckglDzyhp);
    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(id);
        model.addAttribute("ckglDzyhp", ckglDzyhp);
        EntityWrapper<CkglDzyhpMx> wrapper = new EntityWrapper<CkglDzyhpMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("DZYHPID", id);
        List<CkglDzyhpMx> ckgDzyhpMxList = ckglDzyhpxMxService.selectList(wrapper);
        model.addAttribute("DzyhpMxList", ckgDzyhpMxList);
        return display("ckxq");
    }
}
