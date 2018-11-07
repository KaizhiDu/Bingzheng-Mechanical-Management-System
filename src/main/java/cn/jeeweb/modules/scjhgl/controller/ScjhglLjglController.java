package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
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
* @Description:    生产计划管理-零部件管理
* @Author:         杜凯之
* @CreateDate:     2018/9/4 17:07
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/ljgl")
@RequiresPathPermission("scjhgl:ljgl")
public class ScjhglLjglController extends BaseCRUDController<ScjhglLjgl, String> {

    /**零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;
    /**
    * @Description:    搜索项
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 17:22
    * @Version:        1.0
    */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    /**
    * @Description:    转到创建零部件页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:32
    * @Version:        1.0
    */
    @RequestMapping(value = "createLj",method = {RequestMethod.GET,RequestMethod.POST})
    public String createLj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        return display("create");
    }

    /**
    * @Description:    添加一条零部件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:49
    * @Version:        1.0
    */
    @RequestMapping(value = "saveLj",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveLj(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglLjgl scjhglLjgl){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String jhid = scjhglLjgl.getHtid();
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(jhid);
        int jhsl = 0;
        if (scjhglHtgl.getSl()!=null&&!scjhglHtgl.getSl().equals("")){
            jhsl = Integer.parseInt(scjhglHtgl.getSl());
        }
        int ljsl = 0;
        if (scjhglLjgl.getDyl()!=null&&!scjhglLjgl.getDyl().equals("")){
            ljsl = Integer.parseInt(scjhglLjgl.getDyl());
        }
        int zyl = jhsl*ljsl;
        scjhglLjgl.setSl(zyl+"");
        scjhglLjgl.setSysl(zyl+"");
        scjhglLjgl.setWrksl(zyl+"");
        scjhglLjgl.setSfsbj("0");
        scjhglLjgl.setSfwwcrk("0");
        scjhglLjgl.setRq(currentDate);

        scjhglLjglService.insert(scjhglLjgl);
    }

    /**
    * @Description:    展示所有零部件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:53
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxljglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglLjgl> ajaxljglList(String pxfs, String dlid, Queryable queryable, ScjhglLjgl scglSzgyxl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (pxfs==null){
            pxfs = "1";
        }
        PageJson<ScjhglLjgl> pageJson = scjhglLjglService.ajaxljglList(pxfs, queryable,scglSzgyxl);
        return pageJson;
    }

    /**
     * Dscription: 删除零部件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 14:35
     */
    @RequestMapping(value = "deleteLj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteLj(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String ljid = idsArray[i];
            //先删除ljid下属的零部件工艺编制信息
            List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(ljid);
            if (ljgybzByLjid!=null){
                for (ScglLjgybz s: ljgybzByLjid) {
                    scglLjgybzService.deleteById(s.getId());
                }
            }
            //再删除ljid下属的工艺大类编制信息
            EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
            wrapper.eq("LJID",ljid);
            List<ScglGydlbz> scglGydlbzs = scglGydlbzService.selectList(wrapper);
            if (scglGydlbzs!=null){
                for (ScglGydlbz s: scglGydlbzs) {
                    scglGydlbzService.deleteById(s.getId());
                }
            }
            //最后删除零部件
            scjhglLjglService.deleteById(ljid);
        }
    }

    /**
     * Dscription: 转到修改数量页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/5 17:58
     */
    @RequestMapping(value = "modifySl", method={RequestMethod.GET, RequestMethod.POST})
    public String modifySl(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        model.addAttribute("scjhglLjgl" ,scjhglLjgl);
        return display("modifySl");
    }

    /**
     * Dscription: 保存数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/5 18:08
     */
    @RequestMapping(value = "saveSl", method={RequestMethod.GET, RequestMethod.POST})
    public void saveSl(String id, String lbjsl, String htid, String ljmc, String ljth, HttpServletRequest request, HttpServletResponse response, Model model){
        int lbjsli = 0;
        if (lbjsl!=null&&!lbjsl.equals("")){
            lbjsli = Integer.parseInt(lbjsl);
        }
        //先改变零件的信息
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        scjhglLjgl.setWrksl(lbjsl);
        scjhglLjgl.setSysl(lbjsl);
        scjhglLjgl.setSl(lbjsl);
        scjhglLjgl.setLjmc(ljmc);
        scjhglLjgl.setLjth(ljth);
        scjhglLjgl.setHtid(htid);
        scjhglLjglService.updateById(scjhglLjgl);

        //再更新下属零件工艺编制信息
        List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(id);
        if (ljgybzByLjid.size()>0){
            for (ScglLjgybz s : ljgybzByLjid) {
                s.setWrksl(lbjsli);
                s.setSysl(lbjsli);
                s.setSl(lbjsli);
                scglLjgybzService.updateById(s);
            }
        }

    }

}
