package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjglService;
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
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/bjgl")
@RequiresPathPermission("scjhgl:bjgl")
public class ScjhglBjglController extends BaseCRUDController<ScjhglBjgl, String> {

    /**零件管理Service*/
    @Autowired
    private IScjhglBjglService scjhglBjglService;

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
     * Dscription: 展示所有部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 17:04
     */
    @RequestMapping(value = "ajaxBjglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglBjgl> ajaxBjglList(Queryable queryable, ScjhglBjgl scjhglBjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglBjgl> pageJson = scjhglBjglService.ajaxBjglList(queryable,scjhglBjgl);
        return pageJson;
    }
}
