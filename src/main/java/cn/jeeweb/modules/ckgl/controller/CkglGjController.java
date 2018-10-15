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
 * Dscription: 仓库管理 - 工具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 19:03
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/gj")
@RequiresPathPermission("ckgl:gj")
public class CkglGjController extends BaseCRUDController<CkglGj, String> {

    /**仓库管理 - 工具*/
    @Autowired
    private ICkglGjService ckglGjService;

    /**仓库管理 - 大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**仓库管理 - 小类*/
    @Autowired
    private ICkglXlService ckglXlService;

    /**仓库管理 - 工具明细*/
    @Autowired
    private ICkglGjMxService ckglGjMxService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "工具");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);

    }

    /**
     * Dscription: 转到添加工具页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createGj", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "工具");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        return display("createGj");
    }

    /**
     * Dscription: 保存工具
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveGj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglGj ckglGj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglGj.getFldl()!=null&&!ckglGj.getFldl().equals("")){
            ckglGj.setFldl(ckglDlService.selectById(ckglGj.getFldl()).getDlmc());
        }
        ckglGj.setKc("0");
        ckglGj.setKc(ckglGj.getZsl());
        ckglGjService.insert(ckglGj);
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
     * Dscription: 展示所有工具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxGjList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglGj> ajaxGjList(Queryable queryable, CkglGj ckglGj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglGj.getFldl()!=null&&!ckglGj.getFldl().equals("")){
            ckglGj.setFldl(ckglDlService.selectById(ckglGj.getFldl()).getDlmc());
        }
        PageJson<CkglGj> pageJson = ckglGjService.ajaxGjList(queryable,ckglGj);
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
        CkglGj ckglGj = ckglGjService.selectById(id);
        model.addAttribute("ckglGj", ckglGj);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveGjkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String gjid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "员工 "+cg+" 于 "+currentDate+" 还了 "+rksl+" 件";
        //插入明细表
        CkglGjMx ckglGjMx = new CkglGjMx();
        ckglGjMx.setGjid(gjid);
        ckglGjMx.setMx(mx);
        ckglGjMx.setSj(date);
        ckglGjMxService.insert(ckglGjMx);
        //更改标准件表
        CkglGj ckglGj = ckglGjService.selectById(gjid);
        float kc = 0;
        float zjl = 0;
        if (ckglGj.getKc()!=null&&!ckglGj.getKc().equals("")){
            kc = Float.parseFloat(ckglGj.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglGj.setKc(kc+"");
        ckglGjService.updateById(ckglGj);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglGj ckglGj = ckglGjService.selectById(id);
        model.addAttribute("ckglGj", ckglGj);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveGjkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String gjid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "员工 "+ly+" 于 "+currentDate+" 借了 "+cksl+" 件";
        //插入明细表
        CkglGjMx ckglGjMx = new CkglGjMx();
        ckglGjMx.setGjid(gjid);
        ckglGjMx.setMx(mx);
        ckglGjMx.setSj(date);
        ckglGjMxService.insert(ckglGjMx);
        //更改标准件表
        CkglGj ckglGj = ckglGjService.selectById(gjid);
        float kc = 0;
        float zjl = 0;
        if (ckglGj.getKc()!=null&&!ckglGj.getKc().equals("")){
            kc = Float.parseFloat(ckglGj.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            zjl = Float.parseFloat(cksl);
        }
        kc = kc - zjl;
        ckglGj.setKc(kc+"");
        ckglGjService.updateById(ckglGj);
    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglGj ckglGj = ckglGjService.selectById(id);
        model.addAttribute("ckglGj", ckglGj);
        EntityWrapper<CkglGjMx> wrapper = new EntityWrapper<CkglGjMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("GJID", id);
        List<CkglGjMx> ckglGjMxList = ckglGjMxService.selectList(wrapper);
        model.addAttribute("GjMxList", ckglGjMxList);
        return display("ckxq");
    }

    /**
     * Dscription: 查看是否 归还数量+库存<=总数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/15 19:38
     */
    @RequestMapping(value = "saveCheckZsl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int saveCheckZsl(String gjid, String rksl, String zsl, String kc, HttpServletRequest request, HttpServletResponse response, Model model){
        // 1是小于等于 0是大于
        int flag = 0;
        float rkslf = 0;
        float zslf = 0;
        float kcf = 0;
        if (zsl!=null&&!zsl.equals("")){
            zslf = Float.parseFloat(zsl);
        }
        if (rksl!=null&&!rksl.equals("")){
            rkslf = Float.parseFloat(rksl);
        }
        if (kc!=null&&!kc.equals("")){
            kcf = Float.parseFloat(kc);
        }
        if ((rkslf+kcf)<=zslf){
            flag = 1;
        }

        return flag;
    }

}
