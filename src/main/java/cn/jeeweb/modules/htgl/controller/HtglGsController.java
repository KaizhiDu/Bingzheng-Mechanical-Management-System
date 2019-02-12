package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.htgl.entity.HtglGs;
import cn.jeeweb.modules.htgl.entity.HtglHt;
import cn.jeeweb.modules.htgl.entity.HtglHtmx;
import cn.jeeweb.modules.htgl.service.IHtglGsService;
import cn.jeeweb.modules.htgl.service.IHtglHtService;
import cn.jeeweb.modules.htgl.service.IHtglHtmxService;
import cn.jeeweb.modules.jcsz.entity.JcszZzse;
import cn.jeeweb.modules.jcsz.service.IJcszMxmbService;
import cn.jeeweb.modules.jcsz.service.IJcszZzseService;
import cn.jeeweb.modules.zzgl.service.IZzglJhService;
import cn.jeeweb.modules.zzgl.service.IZzglZzglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Dscription: 合同管理 - 公司
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/9 13:40
 */
@Controller
@RequestMapping("${admin.url.prefix}/htgl/gs")
@RequiresPathPermission("htgl:gs")
public class HtglGsController extends BaseCRUDController<HtglGs, String> {

    /**基础设置 - 资金数额Service*/
    @Autowired
    private IJcszZzseService jcszZzseService;

    @Autowired
    private IHtglGsService htglGsService;

    @Autowired
    private IHtglHtService htglHtService;

    @Autowired
    private IHtglHtmxService htglHtmxService;

    /**
     * Dscription: 转到添加公司页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/10 10:43
     */
    @RequestMapping(value = "createGs", method={RequestMethod.GET, RequestMethod.POST})
    public String createGs(HttpServletRequest request, HttpServletResponse response, Model model){
        //把各项名字传入
        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper1);
        model.addAttribute("jcszZzseName", jcszZzseName);
        return display("createGs");
    }

    @RequestMapping(value = "saveGs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGs(String jf, String yf, HttpServletRequest request, HttpServletResponse response, Model model){
        String yfArray[] = yf.split(",");
        String yfmc = "";
        String yfxl = "";
        for (int i = 0; i <yfArray.length ; i++) {
            String yff = yfArray[i];
            String yffArray[] = yff.split("-");
            if (i==0){
                yfxl = yffArray[0];
                yfmc = yffArray[1];
            }
            else {
                yfxl = yfxl+","+yffArray[0];
                yfmc = yfmc+","+yffArray[1];
            }
        }
        HtglGs htglGs = new HtglGs();
        String uuid  = UUID.randomUUID().toString().replaceAll("-","");
        htglGs.setId(uuid);
        htglGs.setJf(jf);
        htglGs.setYf(yfmc);
        htglGs.setYfxl(yfxl);
        htglGs.setJe("0");
        htglGsService.insert(htglGs);

        //创建一个公司首先要创建一个合同（此合同名为总资金）
        HtglHt htglHt = new HtglHt();
        htglHt.setGsid(uuid);
        htglHt.setJe("0");
        htglHt.setHtmc("总资金");
        htglHt.setRq("2999-12-31 23:59:59");
        htglHt.setBz("适用于无合同的回款");
        htglHtService.insert(htglHt);
    }

    /**
     * Dscription: 转到更新页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/10 11:40
     */
     @RequestMapping(value = "xg", method={RequestMethod.GET, RequestMethod.POST})
     public String xg(String id, HttpServletRequest request, HttpServletResponse response, Model model){
         HtglGs htglGs = htglGsService.selectById(id);
         model.addAttribute("htglGs", htglGs);
         //把各项名字传入
         EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
         wrapper1.eq("TYPE","0");
         JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper1);
         model.addAttribute("jcszZzseName", jcszZzseName);
         return display("xg");
     }
    @RequestMapping(value = "saveUpdateGs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveUpdateGs(String id, String jf, String yf, HttpServletRequest request, HttpServletResponse response, Model model){
        String yfArray[] = yf.split(",");
        String yfmc = "";
        String yfxl = "";
        for (int i = 0; i <yfArray.length ; i++) {
            String yff = yfArray[i];
            String yffArray[] = yff.split("-");
            if (i==0){
                yfxl = yffArray[0];
                yfmc = yffArray[1];
            }
            else {
                yfmc = yfmc+","+yffArray[1];
                yfxl = yfxl+","+yffArray[0];

            }
        }
        HtglGs htglGs = htglGsService.selectById(id);
        htglGs.setJf(jf);
        htglGs.setYf(yfmc);
        htglGs.setYfxl(yfxl);
        htglGsService.updateById(htglGs);
    }

    /**
     * Dscription: 转到合同页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/10 12:00
     */
    @RequestMapping(value = "ht", method={RequestMethod.GET, RequestMethod.POST})
    public String ht(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        HtglGs htglGs = htglGsService.selectById(id);
        model.addAttribute("htglGs", htglGs);
        return display("ht");
    }

/**
 * Dscription: 转到添加合同页面
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/10 13:45
 */
    @RequestMapping(value = "createHt", method={RequestMethod.GET, RequestMethod.POST})
    public String createHt(String gsid, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("gsid", gsid);
        return display("createHt");
    }

    @RequestMapping(value = "saveHt", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHt(String fk, String htmc, String je, String bz, String gsid, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String rq = sdf0.format(date0);

        DecimalFormat df = new DecimalFormat("#,###.00");

        float jee = 0;
        if (je!=null&&!je.equals("")){
            jee = getNumber(je);
        }

        float fkk = 0;
        if (fk!=null&&!fk.equals("")){
            fkk = getNumber(fk);
        }

        je = df.format(jee);
        fk = df.format(fkk);

        HtglHt htglHt = new HtglHt();
        htglHt.setBz(bz);
        htglHt.setHtmc(htmc);
        htglHt.setZje(je);
        htglHt.setJe(je);
        htglHt.setFp(je);
        htglHt.setFk(fk);
        htglHt.setFkyk(fk);
        htglHt.setGsid(gsid);
        htglHt.setRq(rq);
        htglHtService.insert(htglHt);

        //还要加上总金额 和 工资总金额
        HtglGs htglGs = htglGsService.selectById(gsid);

        htglGs.setJe(df.format(getNumber(htglGs.getJe())+jee));
        htglGsService.updateById(htglGs);

        EntityWrapper<HtglHt> wrapper = new EntityWrapper<HtglHt>();
        wrapper.eq("RQ","2999-12-31 23:59:59");
        wrapper.eq("GSID", gsid);
        HtglHt htglHt1 = htglHtService.selectOne(wrapper);
        htglHt1.setJe(df.format(getNumber(htglHt1.getJe())+jee));
        htglHtService.updateById(htglHt1);

    }
    /**
     * @Description:    展示所有合同信息
     * @Author:         杜凯之
     * @CreateDate:     2018/8/18 16:58
     * @Version:        1.0
     */
    @RequestMapping(value = "ajaxHtList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglHt> ajaxHtList(String id, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model){
        HtglHt htglHt = new HtglHt();
        htglHt.setId(id);
        PageJson<HtglHt> pageJson = htglHtService.ajaxHtList(queryable,htglHt);
        return pageJson;
    }

    /**
     * Dscription: 删除一条合同信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/11 11:46
     */
    @RequestMapping(value = "deleteHt", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteHt(String ids, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
            String htid = idsArray[i];
            HtglHt htglHt = htglHtService.selectById(htid);
            String je = htglHt.getJe();
            //要减去两个地方的je
            String gsid = htglHt.getGsid();
            HtglGs htglGs = htglGsService.selectById(gsid);
           float jee = getNumber(htglGs.getJe()) - getNumber(je);
            htglGs.setJe(df.format(jee));
            htglGsService.updateById(htglGs);
            EntityWrapper<HtglHt> wrapper = new EntityWrapper<HtglHt>();
            wrapper.eq("RQ", "2999-12-31 23:59:59");
            wrapper.eq("GSID", gsid);
            HtglHt htglHt1 = htglHtService.selectOne(wrapper);
            if (htglHt1!=null){
                float jeee = getNumber(htglHt1.getJe()) - getNumber(je);
                htglHt1.setJe(df.format(jeee));
                htglHtService.updateById(htglHt1);
            }
            //最后删掉该信息
            htglHtService.deleteById(htid);
        }
    }

    /**
     * Dscription: 转到详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/11 18:51
     */
    @RequestMapping(value = "xq", method={RequestMethod.GET, RequestMethod.POST})
    public String xq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        HtglHt htglHt = htglHtService.selectById(id);
        model.addAttribute("htglHt", htglHt);
        return display("xq");
    }

    @RequestMapping(value = "ajaxHtmxList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglHtmx> ajaxHtmxList(String id, String type, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model){
        HtglHtmx htglHtmx = new HtglHtmx();
        htglHtmx.setHtid(id);
        htglHtmx.setLx(type);
        PageJson<HtglHtmx> pageJson = htglHtmxService.ajaxHtmxList(queryable,htglHtmx);
        return pageJson;
    }


    public float getNumber(String number) throws ParseException {
        float d1 = new DecimalFormat().parse(number).floatValue();
        return d1;
    }

    /**
     * Dscription: 转到开发票页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/12 14:43
     */
    @RequestMapping(value = "kfp", method={RequestMethod.GET, RequestMethod.POST})
    public String kfp(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        HtglHt htglHt = htglHtService.selectById(id);
        model.addAttribute("htglHt", htglHt);
        return display("kfp");
    }
    
    /**
     * Dscription: 模块功能
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/12 16:28
     */
    @RequestMapping(value = "createFp", method={RequestMethod.GET, RequestMethod.POST})
    public String createFp(String htid, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        model.addAttribute("htid", htid);
        return display("createFp");
    }

    @RequestMapping(value = "saveFp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveFp(String htid, String bz, String rq, String fp, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        DecimalFormat df = new DecimalFormat("#,###.00");
        float fpf = getNumber(fp);
        fp = df.format(fpf);
        //首先加入明细
        HtglHtmx htglHtmx = new HtglHtmx();
        htglHtmx.setJe(fp);
        htglHtmx.setRq2(rq);
        htglHtmx.setRq(currentDate);
        htglHtmx.setBz(bz);
        htglHtmx.setLx("1");
        htglHtmx.setHtid(htid);
        htglHtmxService.insert(htglHtmx);
        //然后是减去合同里面的发票
        HtglHt htglHt = htglHtService.selectById(htid);
        float oldfpf = getNumber(htglHt.getFp());
        oldfpf = oldfpf - fpf;
        String newfp = df.format(oldfpf);
        htglHt.setFp(newfp);
        htglHtService.updateById(htglHt);
    }

/**
 * Dscription: 删除发票信息
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/12 17:04
 */
    @RequestMapping(value = "deleteFp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteFp(String id, String htid, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        HtglHtmx htglHtmx = htglHtmxService.selectById(id);
        float je = getNumber(htglHtmx.getJe());
        //然后是减去合同里面的发票
        HtglHt htglHt = htglHtService.selectById(htid);
        float oldfpf = getNumber(htglHt.getFp());
        oldfpf = oldfpf + je;
        String newfp = df.format(oldfpf);
        htglHt.setFp(newfp);
        htglHtService.updateById(htglHt);
        //最后删除明细信息
        htglHtmxService.deleteById(id);
    }
}
