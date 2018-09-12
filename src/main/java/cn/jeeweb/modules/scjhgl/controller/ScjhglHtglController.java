package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
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

/**
* @Description:    生产计划管理-合同管理
* @Author:         杜凯之
* @CreateDate:     2018/9/12 14:22
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/htgl")
@RequiresPathPermission("scjhgl:htgl")
public class ScjhglHtglController extends BaseCRUDController<ScjhglHtgl, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**
    * @Description:    展示所有合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:42
    * @Version:        1.0
    */
    @RequestMapping(value = "createHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String creatHt(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }

    /**
    * @Description:    添加合同
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:43saveHt
    * @Version:        1.0
    */
    @RequestMapping(value = "saveHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveHt(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglHtgl scjhglHtgl){
        if (scjhglHtgl.getId()==null){
            scjhglHtglService.insert(scjhglHtgl);
        }
        else{
            scjhglHtglService.updateById(scjhglHtgl);
        }
    }

    /**
    * @Description:    更新合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:04
    * @Version:        1.0
    */
    @RequestMapping(value = "updateHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateHt(String id ,HttpServletResponse response, HttpServletRequest request, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl", scjhglHtgl);
        return display("update");
    }
}
