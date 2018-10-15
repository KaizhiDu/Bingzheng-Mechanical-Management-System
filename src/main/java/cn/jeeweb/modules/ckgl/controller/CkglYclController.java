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
 * Dscription: 仓库管理 - 原材料
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:09
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/ycl")
@RequiresPathPermission("ckgl:ycl")
public class CkglYclController extends BaseCRUDController<CkglYcl, String> {
    /**仓库管理 - 原材料*/
    @Autowired
    private ICkglYclService ckglYclService;

    /**仓库管理 - 标准件*/
    @Autowired
    private ICkglBzjService ckglBzjService;

    /**仓库管理 - 大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**仓库管理 - 小类*/
    @Autowired
    private ICkglXlService ckglXlService;

    /**仓库管理 - 标准件明细*/
    @Autowired
    private ICkglBzjMxSevice ckglBzjxMxService;

    /**仓库管理 - 原材料明细*/
    @Autowired
    private ICkglYclMxService ckglYclxMxService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "原材料");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);

    }

    /**
     * Dscription: 转到添加原材料页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createYcl", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "原材料");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        return display("createYcl");
    }

    /**
     * Dscription: 保存原材料
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveYcl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglYcl ckglYcl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglYcl.getFldl()!=null&&!ckglYcl.getFldl().equals("")){
            ckglYcl.setFldl(ckglDlService.selectById(ckglYcl.getFldl()).getDlmc());
        }
        ckglYcl.setKc("0");
        ckglYclService.insert(ckglYcl);
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
     * Dscription: 展示所有原材料的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxYclList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglYcl> ajaxYclList(Queryable queryable, CkglYcl ckglYcl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglYcl.getFldl()!=null&&!ckglYcl.getFldl().equals("")){
            ckglYcl.setFldl(ckglDlService.selectById(ckglYcl.getFldl()).getDlmc());
        }
        PageJson<CkglYcl> pageJson = ckglYclService.ajaxYclList(queryable,ckglYcl);
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
        CkglYcl ckglYcl = ckglYclService.selectById(id);
        model.addAttribute("ckglYcl", ckglYcl);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveYclkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String yclid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglYclMx ckglYclMx = new CkglYclMx();
        ckglYclMx.setYclid(yclid);
        ckglYclMx.setMx(mx);
        ckglYclMx.setSj(date);
        ckglYclxMxService.insert(ckglYclMx);
        //更改标准件表
        CkglYcl ckglYcl = ckglYclService.selectById(yclid);
        float kc = 0;
        float zjl = 0;
        if (ckglYcl.getKc()!=null&&!ckglYcl.getKc().equals("")){
            kc = Float.parseFloat(ckglYcl.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglYcl.setKc(kc+"");
        ckglYclService.updateById(ckglYcl);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglYcl ckglYcl = ckglYclService.selectById(id);
        model.addAttribute("ckglYcl", ckglYcl);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveYclkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String yclid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+ly+" 于 "+currentDate+" 领取 "+cksl+" 件";
        //插入明细表
        CkglYclMx ckglYclMx = new CkglYclMx();
        ckglYclMx.setYclid(yclid);
        ckglYclMx.setMx(mx);
        ckglYclMx.setSj(date);
        ckglYclxMxService.insert(ckglYclMx);
        //更改标准件表
        CkglYcl ckglYcl = ckglYclService.selectById(yclid);
        float kc = 0;
        float zjl = 0;
        if (ckglYcl.getKc()!=null&&!ckglYcl.getKc().equals("")){
            kc = Float.parseFloat(ckglYcl.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            zjl = Float.parseFloat(cksl);
        }
        kc = kc - zjl;
        ckglYcl.setKc(kc+"");
        ckglYclService.updateById(ckglYcl);
    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglYcl ckglYcl = ckglYclService.selectById(id);
        model.addAttribute("ckglYcl", ckglYcl);
        EntityWrapper<CkglYclMx> wrapper = new EntityWrapper<CkglYclMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("YCLID", id);
        List<CkglYclMx> ckglYclMxList = ckglYclxMxService.selectList(wrapper);
        model.addAttribute("YclMxList", ckglYclMxList);
        return display("ckxq");
    }
}
