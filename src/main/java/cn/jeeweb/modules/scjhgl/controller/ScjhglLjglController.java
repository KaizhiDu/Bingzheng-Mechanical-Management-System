package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
    * @Description:    转到创建零件页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:32
    * @Version:        1.0
    */
    @RequestMapping(value = "createLj",method = {RequestMethod.GET,RequestMethod.POST})
    public String createLj(HttpServletRequest request, HttpServletResponse response){
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
        ScjhglLjgl s = new ScjhglLjgl();
        s.setHtid("01");
        s.setLjmc(scjhglLjgl.getLjmc());
        s.setSl(scjhglLjgl.getSl());
        scjhglLjglService.insert(s);
    }
}
