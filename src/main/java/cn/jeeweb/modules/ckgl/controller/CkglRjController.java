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
 * Dscription: 仓库管理 - 刃具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:13
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/rj")
@RequiresPathPermission("ckgl:rj")
public class CkglRjController extends BaseCRUDController<CkglRj, String> {
    /**仓库管理 - 刃具*/
    @Autowired
    private ICkglRjService ckglRjService;

    /**仓库管理 - 大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**仓库管理 - 小类*/
    @Autowired
    private ICkglXlService ckglXlService;

    /**仓库管理 - 刃具明细*/
    @Autowired
    private ICkglRjMxService ckglRjMxService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "刃具");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);

    }

    /**
     * Dscription: 转到添加刃具页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createRj", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "刃具");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        return display("createRj");
    }

    /**
     * Dscription: 保存刃具
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveRj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglRj ckglRj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglRj.getFldl()!=null&&!ckglRj.getFldl().equals("")){
            ckglRj.setFldl(ckglDlService.selectById(ckglRj.getFldl()).getDlmc());
        }
        ckglRjService.insert(ckglRj);
    }

    /**
     * Dscription: 根据大类ID得到所有下属小类信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 17:16
     */
    @RequestMapping(value = "getFlxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<CkglXl> getFlxl(String fldl, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglXl> wrapper = new EntityWrapper<CkglXl>();
        wrapper.eq("DLID", fldl);
        List<CkglXl> ckglXlList = ckglXlService.selectList(wrapper);
        return ckglXlList;
    }

    /**
     * Dscription: 展示所有刃具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxRjList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglRj> ajaxRjList(Queryable queryable, CkglRj ckglRj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglRj.getFldl()!=null&&!ckglRj.getFldl().equals("")){
            ckglRj.setFldl(ckglDlService.selectById(ckglRj.getFldl()).getDlmc());
        }
        PageJson<CkglRj> pageJson = ckglRjService.ajaxRjList(queryable,ckglRj);
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
        CkglRj ckglRj = ckglRjService.selectById(id);
        model.addAttribute("ckglRj", ckglRj);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveRjkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String rjid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglRjMx ckglRjMx = new CkglRjMx();
        ckglRjMx.setRjid(rjid);
        ckglRjMx.setMx(mx);
        ckglRjMx.setSj(date);
        ckglRjMxService.insert(ckglRjMx);
        //更改标准件表
        CkglRj ckglRj = ckglRjService.selectById(rjid);
        float kc = 0;
        float zjl = 0;
        if (ckglRj.getKc()!=null&&!ckglRj.getKc().equals("")){
            kc = Float.parseFloat(ckglRj.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglRj.setKc(kc+"");
        ckglRjService.updateById(ckglRj);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglRj ckglRj = ckglRjService.selectById(id);
        model.addAttribute("ckglRj", ckglRj);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveRjkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String rjid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+ly+" 于 "+currentDate+" 入库 "+cksl+" 件";
        //插入明细表
        CkglRjMx ckglRjMx = new CkglRjMx();
        ckglRjMx.setRjid(rjid);
        ckglRjMx.setMx(mx);
        ckglRjMx.setSj(date);
        ckglRjMxService.insert(ckglRjMx);
        //更改标准件表
        CkglRj ckglRj = ckglRjService.selectById(rjid);
        float kc = 0;
        float zjl = 0;
        if (ckglRj.getKc()!=null&&!ckglRj.getKc().equals("")){
            kc = Float.parseFloat(ckglRj.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            zjl = Float.parseFloat(cksl);
        }
        kc = kc - zjl;
        ckglRj.setKc(kc+"");
        ckglRjService.updateById(ckglRj);
    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglRj ckglRj = ckglRjService.selectById(id);
        model.addAttribute("ckglRj", ckglRj);
        EntityWrapper<CkglRjMx> wrapper = new EntityWrapper<CkglRjMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("RJID", id);
        List<CkglRjMx> ckglRjMxList = ckglRjMxService.selectList(wrapper);
        model.addAttribute("RjMxList", ckglRjMxList);
        return display("ckxq");
    }
}
