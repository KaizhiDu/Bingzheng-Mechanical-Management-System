package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
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
             int count = scglGydlbzService.selectCount(wrapper);
             String px = count+1+"";
             ScglGydlbz s = new ScglGydlbz();
             s.setPx(px);
             s.setJhid(jhid);
             s.setGydlid(idsArray[i]);
             scglGydlbzService.insert(s);
         }
         ajaxJson.setMsg("添加成功！！！");
         return ajaxJson;
     }
}
