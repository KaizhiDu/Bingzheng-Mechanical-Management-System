package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:    员工职位薪资分配
 * @Author:         杜凯之
 * @CreateDate:     2018/8/18 15:15
 * @Version:        1.0
 */
@Controller
@RequestMapping("${admin.url.prefix}/grgl/xzzwfp")
@RequiresPathPermission("grgl:xzzwfp")
public class GrglXzzwfpController extends BaseCRUDController<Xzzwfp, String> {

    /** 员工薪资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

    /**
    * @Description:    查出所有员工的职位薪资分配
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 14:51
    * @Version:        1.0
    */
    @RequestMapping(value = "queryAjax", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<YgzxxDTO> queryAjax(Queryable queryable, Xzzwfp xzzwfp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<YgzxxDTO> pageJson = grglXzzwfpService.queryAjax(queryable,xzzwfp);
        return pageJson;
    }

    /**
    * @Description:    给员工分配薪资职位
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 15:56
    * @Version:        1.0
    */
    @RequestMapping(value = "setEmp", method={RequestMethod.GET, RequestMethod.POST})
    public String setEmp(HttpServletRequest request, HttpServletResponse response, Model model, String id){
        //根据id查到xzzwfp的信息
        Xzzwfp xzzwfp = grglXzzwfpService.selectById(id);
        model.addAttribute("xzzwfp",xzzwfp);
        return display("set");
    }

    /**
    * @Description:    保存职位薪资信息
    * @Author:         杜凯之
    * @CreateDate:     2018/8/28 18:06
    * @Version:        1.0
    */
    @RequestMapping(value = "saveXzzwfp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveXzzwfp(HttpServletRequest request, HttpServletResponse response, Model model, Xzzwfp xzzwfp){
        grglXzzwfpService.updateById(xzzwfp);
    }
}
