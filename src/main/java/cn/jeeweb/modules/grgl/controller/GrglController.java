package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.service.IGrglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description:    员工管理
* @Author:         杜凯之
* @CreateDate:     2018/8/18 15:15
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/grgl/grgl")
@RequiresPathPermission("grgl:grgl")
public class GrglController extends BaseCRUDController<Grgl, String> {

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**
    * @Description:    展示所有员工信息
    * @Author:         杜凯之
    * @CreateDate:     2018/8/18 16:58
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxListGrgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<Grgl> ajaxListGrgl(Queryable queryable, Grgl grgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<Grgl> pageJson = grglService.grglList(queryable,grgl);
        return pageJson;
    }

    /**
    * @Description:    跳转到添加员工页面
    * @Author:         杜凯之
    * @CreateDate:     2018/8/24 16:56
    * @Version:        1.0
    */
    @RequestMapping(value = "createWorker",method = {RequestMethod.GET,RequestMethod.POST})
    public String createWorker(Queryable queryable, Grgl grgl, HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }

    /**
     * @Description:    保存添加数据
     * @Author:         杜凯之
     * @CreateDate:     2018/8/24 16:56
     * @Version:        1.0
     */
    @RequestMapping(value = "saveWorker",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveWorker(Queryable queryable, Grgl grgl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (grgl.getId().equals("")){
            grglService.insert(grgl);
        }
        else{
            grglService.updateById(grgl);
        }
    }

    /**
    * @Description:    跳转到员工更新页面
    * @Author:         杜凯之
    * @CreateDate:     2018/8/25 9:53
    * @Version:        1.0
    */
    @RequestMapping(value = "updateWorker",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateWorker(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        Grgl grgl = grglService.selectById(id);
        model.addAttribute("grgl",grgl);
        return display("update");
    }

    /**
    * @Description:    删除一个员工
    * @Author:         杜凯之
    * @CreateDate:     2018/8/25 11:50
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteWorker",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void deleteWorker(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        grglService.deleteById(id);
    }
}
