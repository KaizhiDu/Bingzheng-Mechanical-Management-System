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
 * Dscription: 仓库管理 - 标准件
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 14:09
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bzj")
@RequiresPathPermission("ckgl:bzj")
public class CkglBzjController extends BaseCRUDController<CkglBzj, String> {

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

    /**仓库管理 - 进货商*/
    @Autowired
    private ICkglJhsService ckglJhsService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "标准件");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);

    }

    /**
     * Dscription: 转到添加标准件页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 14:30
     */
    @RequestMapping(value = "createBzj", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "标准件");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        return display("createBzj");
    }

    /**
     * Dscription: 保存标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @RequestMapping(value = "saveBzj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(CkglBzj ckglBzj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglBzj.getId()!=null&&!ckglBzj.getId().equals("")){
            ckglBzjService.updateById(ckglBzj);
        }
        else{
            if (ckglBzj.getFldl()!=null&&!ckglBzj.getFldl().equals("")){
                ckglBzj.setFldl(ckglDlService.selectById(ckglBzj.getFldl()).getDlmc());
            }
            ckglBzj.setKc("0");
            ckglBzjService.insert(ckglBzj);
        }

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
     * Dscription: 展示所有标准件的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxBzjList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBzj> ajaxBzjList(Queryable queryable, CkglBzj ckglBzj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (ckglBzj.getFldl()!=null&&!ckglBzj.getFldl().equals("")){
            ckglBzj.setFldl(ckglDlService.selectById(ckglBzj.getFldl()).getDlmc());
        }
        PageJson<CkglBzj> pageJson = ckglBzjService.ajaxBzjList(queryable,ckglBzj);
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
        CkglBzj ckglBzj = ckglBzjService.selectById(id);
        model.addAttribute("ckglBzj", ckglBzj);
        EntityWrapper<CkglJhs> wrapper = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper);
        model.addAttribute("ckglJhs", ckglJhs);
        return display("rk");
    }

    /**
     * Dscription: 保存库存，并且加入明细(入库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 13:12
     */
    @RequestMapping(value = "saveBzjkc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkc(String jhs, String bzjid, String cg, String rksl, HttpServletRequest request, HttpServletResponse response, Model model){

        CkglBzj ckglBzj0 = ckglBzjService.selectById(bzjid);

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String datee = sdf0.format(date0);
        String dateArray[] = datee.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglBzjMx ckglBzjMx = new CkglBzjMx();
        ckglBzjMx.setFldl(ckglBzj0.getFldl());
        ckglBzjMx.setFlxl(ckglBzj0.getFlxl());
        ckglBzjMx.setGg(ckglBzj0.getGg());
        ckglBzjMx.setBzjid(bzjid);
        ckglBzjMx.setMx(mx);
        ckglBzjMx.setSj(date);
        ckglBzjMx.setN(n);
        ckglBzjMx.setY(y);
        ckglBzjMx.setR(r);
        ckglBzjMx.setJhs(jhs);
        ckglBzjMx.setJx("0");
        ckglBzjxMxService.insert(ckglBzjMx);
        //更改标准件表
        CkglBzj ckglBzj = ckglBzjService.selectById(bzjid);
        float kc = 0;
        float zjl = 0;
        if (ckglBzj.getKc()!=null&&!ckglBzj.getKc().equals("")){
            kc = Float.parseFloat(ckglBzj.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglBzj.setKc(kc+"");
        ckglBzjService.updateById(ckglBzj);

    }

    /**
     * Dscription: 转到出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 12:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglBzj ckglBzj = ckglBzjService.selectById(id);
        model.addAttribute("ckglBzj", ckglBzj);
        return display("ck");
    }

    /**
     * Dscription: 保存库存，并且加入明细(出库)
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:04
     */
    @RequestMapping(value = "saveBzjkcck", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzjkcck(String bzjid, String ly, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){

        CkglBzj ckglBzj0 = ckglBzjService.selectById(bzjid);

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String datee = sdf0.format(date0);
        String dateArray[] = datee.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "员工 "+ly+" 于 "+currentDate+" 领取 "+cksl+" 件";
        //插入明细表
        CkglBzjMx ckglBzjMx = new CkglBzjMx();
        ckglBzjMx.setFldl(ckglBzj0.getFldl());
        ckglBzjMx.setFlxl(ckglBzj0.getFlxl());
        ckglBzjMx.setGg(ckglBzj0.getGg());
        ckglBzjMx.setBzjid(bzjid);
        ckglBzjMx.setMx(mx);
        ckglBzjMx.setSj(date);
        ckglBzjMx.setN(n);
        ckglBzjMx.setY(y);
        ckglBzjMx.setR(r);
        ckglBzjMx.setJx("1");
        ckglBzjxMxService.insert(ckglBzjMx);
        //更改标准件表
        CkglBzj ckglBzj = ckglBzjService.selectById(bzjid);
        float kc = 0;
        float jsl = 0;
        if (ckglBzj.getKc()!=null&&!ckglBzj.getKc().equals("")){
            kc = Float.parseFloat(ckglBzj.getKc());
        }
        if (cksl!=null&&!cksl.equals("")){
            jsl = Float.parseFloat(cksl);
        }
        kc = kc - jsl;
        ckglBzj.setKc(kc+"");
        ckglBzjService.updateById(ckglBzj);

    }

    /**
     * Dscription: 转到查看详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/14 14:18
     */
    @RequestMapping(value = "ckxq", method={RequestMethod.GET, RequestMethod.POST})
    public String ckxq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglBzj ckglBzj = ckglBzjService.selectById(id);
        model.addAttribute("ckglBzj", ckglBzj);
        EntityWrapper<CkglBzjMx> wrapper = new EntityWrapper<CkglBzjMx>();
        wrapper.orderBy("SJ",false);
        wrapper.eq("BZJID", id);
        List<CkglBzjMx> ckglBzjMxList = ckglBzjxMxService.selectList(wrapper);
        model.addAttribute("BzjMxList", ckglBzjMxList);
        return display("ckxq");
    }

    /**
     * Dscription: 转到查看整体进销情况页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 12:38
     */
    @RequestMapping(value = "jxxq", method={RequestMethod.GET, RequestMethod.POST})
    public String jxxq(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglJhs> wrapper = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper);
        model.addAttribute("ckglJhs", ckglJhs);
        return display("jxxq");
    }

    /**
     * Dscription: 展示所有进销信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 15:15
     */
    @RequestMapping(value = "ajaxXqList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBzjMx> ajaxXqList(Queryable queryable, CkglBzjMx ckglBzjMx, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglBzjMx> pageJson = ckglBzjxMxService.ajaxXqList(queryable,ckglBzjMx);
        return pageJson;
    }

    /**
     * Dscription: 转到修改页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 16:03
     */
    @RequestMapping(value = "xg", method={RequestMethod.GET, RequestMethod.POST})
    public String xg(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglBzj ckglBzj = ckglBzjService.selectById(id);
        model.addAttribute("ckglBzj", ckglBzj);
        return display("xg");
    }

}
