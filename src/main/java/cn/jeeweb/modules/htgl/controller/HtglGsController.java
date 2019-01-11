package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.htgl.entity.HtglGs;
import cn.jeeweb.modules.htgl.entity.HtglHt;
import cn.jeeweb.modules.htgl.service.IHtglGsService;
import cn.jeeweb.modules.htgl.service.IHtglHtService;
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
        htglGs.setJe(0);
        htglGsService.insert(htglGs);

        //创建一个公司首先要创建一个合同（此合同名为总资金）
        HtglHt htglHt = new HtglHt();
        htglHt.setGsid(uuid);
        htglHt.setJe(0);
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
    public void saveHt(String htmc, String je, String bz, String gsid, HttpServletRequest request, HttpServletResponse response, Model model){

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String rq = sdf0.format(date0);

        float jee = 0;
        if (je!=null&&!je.equals("")){
            jee = Float.parseFloat(je);
        }
        HtglHt htglHt = new HtglHt();
        htglHt.setBz(bz);
        htglHt.setHtmc(htmc);
        htglHt.setJe(jee);
        htglHt.setGsid(gsid);
        htglHt.setRq(rq);
        htglHtService.insert(htglHt);

        //还要加上总金额 和 工资总金额
        HtglGs htglGs = htglGsService.selectById(gsid);
        htglGs.setJe(htglGs.getJe()+jee);
        htglGsService.updateById(htglGs);

        EntityWrapper<HtglHt> wrapper = new EntityWrapper<HtglHt>();
        wrapper.eq("RQ","2999-12-31 23:59:59");
        HtglHt htglHt1 = htglHtService.selectOne(wrapper);
        htglHt1.setJe(htglHt1.getJe()+jee);
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
    public void deleteHt(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
            String htid = idsArray[i];
            HtglHt htglHt = htglHtService.selectById(htid);
            float je = htglHt.getJe();
            //要减去两个地方的je
            String gsid = htglHt.getGsid();
            HtglGs htglGs = htglGsService.selectById(gsid);
            htglGs.setJe(htglGs.getJe() - je);
            htglGsService.updateById(htglGs);
            EntityWrapper<HtglHt> wrapper = new EntityWrapper<HtglHt>();
            wrapper.eq("RQ", "2999-12-31 23:59:59");
            wrapper.eq("GSID", gsid);
            HtglHt htglHt1 = htglHtService.selectOne(wrapper);
            htglHt1.setJe(htglHt1.getJe() - je);
            htglHtService.updateById(htglHt1);
            //最后删掉该信息
            htglHtService.deleteById(htid);
        }
    }


}
