package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description:    生产管理-工艺模板设置
* @Author:         杜凯之
* @CreateDate:     2018/9/5 14:21
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scgl/gymbsz")
@RequiresPathPermission("scgl:gymbsz")
public class ScglGymbszController extends BaseCRUDController<ScglGymbsz, String> {

    /**工艺模板设置Service*/
    @Autowired
    private IScglGymbszService scglGymbszService;

    /**
    * @Description:    转到添加工艺大类页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/5 17:13
    * @Version:        1.0
    */
    @RequestMapping(value = "createGydl", method={RequestMethod.GET, RequestMethod.POST})
    public String createGydl(HttpServletResponse response, HttpServletRequest request){
        return display("create");
    }

    /**
    * @Description:    保存工艺大类信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/5 17:26
    * @Version:        1.0
    */
    @RequestMapping(value = "saveGydl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGydl(ScglGymbsz scglGymbsz, HttpServletResponse response, HttpServletRequest request){
        scglGymbszService.insert(scglGymbsz);
    }

    /**
    * @Description:    跳到编辑工艺小类模板页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/5 17:48
    * @Version:        1.0
    */
    @RequestMapping(value = "Gyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String Gyxl(HttpServletResponse response, HttpServletRequest request){
        return display("gyxl");
    }
}
