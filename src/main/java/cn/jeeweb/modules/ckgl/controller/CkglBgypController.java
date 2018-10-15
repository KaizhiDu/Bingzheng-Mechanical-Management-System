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
 * Dscription: 仓库管理 - 办公用品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 10:28
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bgyp")
@RequiresPathPermission("ckgl:bgyp")
public class CkglBgypController extends BaseCRUDController<CkglBgyp, String> {
    /**仓库管理 - 办公用品*/
    @Autowired
    private ICkglBgypService ckglBgypService;

    /**仓库管理 - 办公用品明细*/
    @Autowired
    private ICkglBgypMxService ckglBgypxMxService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * Dscription: 转到添加办公用品页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createBgyp", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("createBgyp");
    }

    /**
     * Dscription: 保存办公用品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveBgyp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglBgyp ckglBgyp, HttpServletRequest request, HttpServletResponse response, Model model){
        ckglBgyp.setKc("0");
        ckglBgypService.insert(ckglBgyp);
    }

    /**
     * Dscription: 展示所有办公用品的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxBgypList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBgyp> ajaxBgypList(Queryable queryable, CkglBgyp ckglBgyp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglBgyp> pageJson = ckglBgypService.ajaxBgypList(queryable,ckglBgyp);
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
        CkglBgyp ckglBgyp = ckglBgypService.selectById(id);
        model.addAttribute("ckglBgyp", ckglBgyp);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveBgypkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String bgypid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglBgypMx ckglBgypMx = new CkglBgypMx();
        ckglBgypMx.setBgypid(bgypid);
        ckglBgypMx.setMx(mx);
        ckglBgypMx.setSj(date);
        ckglBgypxMxService.insert(ckglBgypMx);
        //更改标准件表
        CkglBgyp ckglBgyp = ckglBgypService.selectById(bgypid);
        float kc = 0;
        float zjl = 0;
        if (ckglBgyp.getKc()!=null&&!ckglBgyp.getKc().equals("")){
            kc = Float.parseFloat(ckglBgyp.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglBgyp.setKc(kc+"");
        ckglBgypService.updateById(ckglBgyp);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglBgyp ckglBgyp = ckglBgypService.selectById(id);
        model.addAttribute("ckglBgyp", ckglBgyp);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveBgypkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String bgypid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+ly+" 于 "+currentDate+" 领取 "+cksl+" 件";
        //插入明细表
        CkglBgypMx ckglBgypMx = new CkglBgypMx();
        ckglBgypMx.setBgypid(bgypid);
        ckglBgypMx.setMx(mx);
        ckglBgypMx.setSj(date);
        ckglBgypxMxService.insert(ckglBgypMx);
        //更改标准件表
        CkglBgyp ckglBgyp = ckglBgypService.selectById(bgypid);
        float kc = 0;
        float zjl = 0;
        if (ckglBgyp.getKc()!=null&&!ckglBgyp.getKc().equals("")){
            kc = Float.parseFloat(ckglBgyp.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            zjl = Float.parseFloat(cksl);
        }
        kc = kc - zjl;
        ckglBgyp.setKc(kc+"");
        ckglBgypService.updateById(ckglBgyp);
    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglBgyp ckglBgyp = ckglBgypService.selectById(id);
        model.addAttribute("ckglBgyp", ckglBgyp);
        EntityWrapper<CkglBgypMx> wrapper = new EntityWrapper<CkglBgypMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("BGYPID", id);
        List<CkglBgypMx> ckglBgypMxList = ckglBgypxMxService.selectList(wrapper);
        model.addAttribute("BgypMxList", ckglBgypMxList);
        return display("ckxq");
    }
}
