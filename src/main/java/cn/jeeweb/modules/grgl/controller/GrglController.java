package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglXzzwfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    /** 员工薪资职位分配Service*/
    @Autowired
    private IGrglXzzwfpService grglXzzwfpService;

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
        //插入
        if (grgl.getId().equals("")){
            String uuid  = UUID.randomUUID().toString().replaceAll("-","");
            grgl.setId(uuid);
            grglService.insert(grgl);
            //同时也插入薪资职位表
            Xzzwfp xzzwfp = new Xzzwfp();
            xzzwfp.setYgid(uuid);
            grglXzzwfpService.insert(xzzwfp);
        }
        //更新
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
