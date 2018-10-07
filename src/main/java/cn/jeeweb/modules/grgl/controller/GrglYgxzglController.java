package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
@Controller
@RequestMapping("${admin.url.prefix}/grgl/ygxzgl")
@RequiresPathPermission("grgl:ygxzgl")
public class GrglYgxzglController extends BaseCRUDController<GrglYgxzgl, String> {

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**员工管理 - 员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);
        model.addAttribute("nd", nd);
        model.addAttribute("yf", yf);


    }

    /**
     * Dscription: 得到所有员工薪资管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/6 17:56
     */
    @RequestMapping(value = "ajaxYgxzglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<GrglYgxzgl> ajaxYgxzglList(String nd2, String yf2, Queryable queryable, GrglYgxzgl grglYgxzgl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (nd2!=null){
            grglYgxzgl.setNd(Integer.parseInt(nd2));
        }
        if (yf2!=null){
            grglYgxzgl.setYf(Integer.parseInt(yf2));
        }
        PageJson<GrglYgxzgl> pageJson = grglYgxzglService.ajaxYgxzglList(queryable,grglYgxzgl);
        return pageJson;
    }


}
