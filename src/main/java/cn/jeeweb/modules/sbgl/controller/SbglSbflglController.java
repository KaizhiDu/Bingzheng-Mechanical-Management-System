package cn.jeeweb.modules.sbgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description:    设备管理-设备分类管理
* @Author:         杜凯之
* @CreateDate:     2018/8/31 16:47
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/sbgl/sbflgl")
@RequiresPathPermission("sbgl:sbflgl")
public class SbglSbflglController extends BaseCRUDController<SbglSbflgl, String> {

    /**设备分类管理Service*/
    @Autowired
    private ISbglSbflglService sbglSbflglService;
    /**
    * @Description:    展示所有设备分类管理
    * @Author:         杜凯之
    * @CreateDate:     2018/8/31 17:29
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxListSbflgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<SbglSbflgl> ajaxListSbflgl(Queryable queryable, SbglSbflgl sbglSbflgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<SbglSbflgl> pageJson = sbglSbflglService.sbglSbflglList(queryable,sbglSbflgl);
        return pageJson;
    }

    /**
    * @Description:    创建一个设备分类
    * @Author:         杜凯之
    * @CreateDate:     2018/8/31 17:54
    * @Version:        1.0
    */
    @RequestMapping(value = "createSbflgl", method={RequestMethod.GET, RequestMethod.POST})
    public String createSbflgl(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }
    /**
    * @Description:    保存一个设备分类
    * @Author:         杜凯之
    * @CreateDate:     2018/8/31 18:14
    * @Version:        1.0
    */
    @RequestMapping(value = "saveSbflgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson saveSbflgl(SbglSbflgl sbglSbflgl, HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        //插入一条记录
        if (sbglSbflgl.getId()==null||sbglSbflgl.getId().equals("")){
            sbglSbflglService.insert(sbglSbflgl);
        }
        else {
            sbglSbflglService.updateById(sbglSbflgl);
        }

        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }

    @RequestMapping(value = "updateSbflgl", method={RequestMethod.GET, RequestMethod.POST})
    public String updateSbflgl(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        SbglSbflgl sbglSbflgl = sbglSbflglService.selectById(id);
        model.addAttribute("sbglSbflgl",sbglSbflgl);
        return display("update");
    }
}
