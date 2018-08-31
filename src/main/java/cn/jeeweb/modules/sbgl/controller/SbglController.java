package cn.jeeweb.modules.sbgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import cn.jeeweb.modules.sbgl.service.ISbglService;
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
 * @Description:    设备管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/30 17:08
 * @Version:        1.0
 */
@Controller
@RequestMapping("${admin.url.prefix}/sbgl/sbgl")
@RequiresPathPermission("sbgl:sbgl")
public class SbglController extends BaseCRUDController<Sbgl, String> {

    /**设备基本管理Service*/
    @Autowired
    private ISbglService sbglSbflgl;
    /**设备分类管理Service*/
    @Autowired
    private ISbglSbflglService sbglSbflglService;

    /**
* @Description:    展示所有设备
* @Author:         杜凯之
* @CreateDate:     2018/8/30 17:58
* @Version:        1.0
*/
    @RequestMapping(value = "ajaxListSbgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<Sbgl> ajaxListSbgl(Queryable queryable, Sbgl sbgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<Sbgl> pageJson = sbglSbflgl.ajaxListSbgl(queryable,sbgl);
        return pageJson;
    }

    /**
     * Dscription: 打开创建设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/1 0:09
     */
    @RequestMapping(value = "createSb", method={RequestMethod.GET, RequestMethod.POST})
   public String createSb(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<SbglSbflgl> wrapper = new EntityWrapper();
        List<SbglSbflgl> sbflglList = sbglSbflglService.selectList(wrapper);
        model.addAttribute("list",sbflglList);
        return display("create");
    }

    /**
     * @Description:    保存一个设备
     * @Author:         杜凯之
     * @CreateDate:     2018/8/31 18:14
     * @Version:        1.0
     */
    @RequestMapping(value = "saveSb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson saveSb(Sbgl sbgl, HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        //插入一条记录
        if (sbgl.getId()==null||sbgl.getId().equals("")){
            sbglSbflgl.insert(sbgl);
        }
        //更新这条记录
        else {
            sbglSbflgl.updateById(sbgl);
        }

        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }
}
