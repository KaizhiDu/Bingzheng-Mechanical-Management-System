package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
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
import java.util.List;

/**
* @Description:    生产计划管理-零件管理
* @Author:         杜凯之
* @CreateDate:     2018/9/4 17:07
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/ljgl")
@RequiresPathPermission("scjhgl:ljgl")
public class ScjhglLjglController extends BaseCRUDController<ScjhglLjgl, String> {

    /**零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;
    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

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
    * @Description:    转到创建零件页面
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
    * @Description:    添加一条零件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:49
    * @Version:        1.0
    */
    @RequestMapping(value = "saveLj",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveLj(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglLjgl scjhglLjgl){
        String jhid = scjhglLjgl.getHtid();
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(jhid);
        int jhsl = Integer.parseInt(scjhglHtgl.getSl());
        int ljsl = Integer.parseInt(scjhglLjgl.getDyl());
        int zyl = jhsl*ljsl;
        scjhglLjgl.setSl(zyl+"");
        scjhglLjglService.insert(scjhglLjgl);
    }

    /**
    * @Description:    展示所有零件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:53
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxljglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglLjgl> ajaxljglList(String dlid, Queryable queryable, ScjhglLjgl scglSzgyxl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglLjgl> pageJson = scjhglLjglService.ajaxljglList(queryable,scglSzgyxl);
        return pageJson;
    }
}
