package cn.jeeweb.modules.sbgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbrwjk;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import cn.jeeweb.modules.sbgl.service.ISbglSbrwjkService;
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
* @Description:    设备管理-设备任务监控
* @Author:         杜凯之
* @CreateDate:     2018/9/1 13:49
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/sbgl/sbrwjk")
@RequiresPathPermission("sbgl:sbrwjk")
public class SbglSbrwjkController extends BaseCRUDController<SbglSbrwjk, String> {

    /**设备任务监控Service*/
    @Autowired
    private ISbglSbrwjkService sbglSbrwjkService;
    /**设备基本管理Service*/
    @Autowired
    private ISbglService sbglSbflgl;
    /**设备分类管理Service*/
    @Autowired
    private ISbglSbflglService sbglSbflglService;

    /**
     * @Description:    搜索项
     * @Author:         杜凯之
     * @CreateDate:     2018/9/1 10:17
     * @Version:        1.0
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //设备分类
        EntityWrapper<SbglSbflgl> wrapper = new EntityWrapper();
        wrapper.orderBy("fldm");
        List<SbglSbflgl> sbflglList = sbglSbflglService.selectList(wrapper);
        model.addAttribute("list",sbflglList);
    }

    /**
     * @Description:    展示所有设备监控情况
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    @RequestMapping(value = "ajaxListSbrwjk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<SbglSbrwjk> ajaxListSbrwjk(Queryable queryable, SbglSbrwjk sbglSbrwjk, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<SbglSbrwjk> pageJson = sbglSbrwjkService.ajaxListSbrwjk(queryable,sbglSbrwjk);
        return pageJson;
    }
}
