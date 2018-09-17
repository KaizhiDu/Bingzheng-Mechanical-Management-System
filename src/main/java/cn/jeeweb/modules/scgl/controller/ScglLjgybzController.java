package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.dto.GydlbzDTO;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.entity.ScglSzgyxl;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import cn.jeeweb.modules.scgl.service.IScglSzgyxlService;
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
import java.util.List;

/**
* @Description:    生产管理-零件工艺编制
* @Author:         杜凯之
* @CreateDate:     2018/9/14 15:58
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scgl/ljgybz")
@RequiresPathPermission("scgl:ljgybz")
public class ScglLjgybzController extends BaseCRUDController<ScglLjgybz, String> {

    /**计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**工艺大类编制*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**大类模板*/
    @Autowired
    private IScglGymbszService scglGymbszService;

    @Autowired
    /**工艺小类模板*/
    private IScglSzgyxlService scglSzgyxlService;

    /**
    * @Description:    搜索项
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 16:28
    * @Version:        1.0
    */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("HTBH");
        List<ScjhglHtgl> jhList = scjhglHtglService.selectList(wrapper);
        model.addAttribute("jhList",jhList);
    }

    /**
    * @Description:    展示所有计划信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 17:01
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxJhglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglHtgl> ajaxJhglList(Queryable queryable, ScjhglHtgl scjhglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglHtgl> pageJson = scjhglHtglService.ajaxJhglList(queryable,scjhglHtgl);
        return pageJson;
    }

    /**
    * @Description:    跳转到编制工艺页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 17:32
    * @Version:        1.0
    */
    @RequestMapping(value = "bzgydl", method={RequestMethod.GET, RequestMethod.POST})
    public String bzgydl(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl", scjhglHtgl);
        return display("bzgydl");
    }

    /**
    * @Description:    编制工艺大类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 18:20
    * @Version:        1.0ajaxGymbszList
    */
    @RequestMapping(value = "addGydl", method={RequestMethod.GET, RequestMethod.POST})
    public String addGydl(String jhid, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("jhid", jhid);
        return display("addGydl");
    }

    /**
     * Dscription: 编制工艺大类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/15 1:19
     */
     @RequestMapping(value = "saveGydlbz", method={RequestMethod.GET, RequestMethod.POST})
     @ResponseBody
    public AjaxJson saveGydlbz(String jhid, String ids, HttpServletRequest request, HttpServletResponse response, Model model){
         AjaxJson ajaxJson = new AjaxJson();
         String idsArray[] = ids.split(",");
         for (int i=0;i<idsArray.length;i++){
             EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
             wrapper.eq("JHID",jhid);
             int count = scglGydlbzService.selectCount(wrapper);
             int px = count+1;
             ScglGydlbz s = new ScglGydlbz();
             s.setPx(px);
             s.setJhid(jhid);
             s.setGydlid(idsArray[i]);
             //先查数据库里面该大类名称
             ScglGymbsz scglGymbsz = scglGymbszService.selectById(idsArray[i]);
             //然后查插入表里面该大类的数量
             EntityWrapper<ScglGydlbz> wrapper2 = new EntityWrapper<ScglGydlbz>();
             wrapper2.eq("GYDLID",idsArray[i]);
             wrapper2.eq("JHID",jhid);
             int count2 = scglGydlbzService.selectCount(wrapper2);
             //没有该工艺大类
             if (count2==0){
                 s.setGydlmc(scglGymbsz.getGydlmc());
             }
             else{
                 String gydlmc = scglGymbsz.getGydlmc()+(count2+1)+"";
                 s.setGydlmc(gydlmc);
             }
             scglGydlbzService.insert(s);
         }
         ajaxJson.setMsg("添加成功！！！");
         return ajaxJson;
     }

     /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
     @RequestMapping(value = "ajaxGydlbzList", method={RequestMethod.GET, RequestMethod.POST})
     @ResponseBody
     public PageJson<GydlbzDTO> ajaxGydlbzList(Queryable queryable, GydlbzDTO gydlbzDTO, HttpServletRequest request, HttpServletResponse response, Model model){
         PageJson<GydlbzDTO> pageJson = scglGydlbzService.ajaxGydlbzList(queryable,gydlbzDTO);
         return pageJson;
     }

     /**
     * @Description:    转到设置大类排序页面
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 14:16
     * @Version:        1.0
     */
     @RequestMapping(value = "szdlpx", method={RequestMethod.GET, RequestMethod.POST})
     public String szdlpx(String id ,String jhid, HttpServletRequest request, HttpServletResponse response, Model model){
         //修改用到的id
         model.addAttribute("id",id);
         //当前排序
         ScglGydlbz scglGydlbz = scglGydlbzService.selectById(id);
         model.addAttribute("dqpx", scglGydlbz.getPx());
         //计划ID
         model.addAttribute("jhid", jhid);
         //排序总数
         EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
        wrapper.eq("JHID", jhid);
        List<ScglGydlbz> scglGydlbzs = scglGydlbzService.selectList(wrapper);
        model.addAttribute("pxzs", scglGydlbzs.size());
         return display("szdlpx");
     }

     /**
     * @Description:    修改大类排序
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 14:07
     * @Version:        1.0
     */
    @RequestMapping(value = "savePx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void savePx(String id, String px, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglGydlbz scglGydlbz = scglGydlbzService.selectById(id);
        int px2 = Integer.parseInt(px);
        scglGydlbz.setPx(px2);
        scglGydlbzService.updateById(scglGydlbz);
    }

    /**
    * @Description:    转到零件工艺编制页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/17 15:26
    * @Version:        1.0
    */
    @RequestMapping(value = "bzgyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String bzgyxl(String id ,String jhid, HttpServletRequest request, HttpServletResponse response, Model model){
        //计划编号
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(jhid);
        model.addAttribute("jhxx",scjhglHtgl);
        //计划大类编制信息
        ScglGydlbz gydlbz = scglGydlbzService.selectById(id);
        model.addAttribute("gydlbz", gydlbz);
        return display("bzgyxl");
    }

    /**
    * @Description:    转到添加零件工艺编制页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/17 16:21
    * @Version:        1.0
    */
    @RequestMapping(value = "addGyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String addGyxl(String gydlbzid, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglGydlbz scglGydlbz = scglGydlbzService.selectById(gydlbzid);
        String dlid = scglGydlbz.getGydlid();
       List<ScglSzgyxl> gyxlList = scglSzgyxlService.getXlList(dlid);
        model.addAttribute("gyxlList", gyxlList);
        //工艺大类编制ID
        model.addAttribute("gydlbzid", gydlbzid);
        return display("addGyxl");
    }
}
