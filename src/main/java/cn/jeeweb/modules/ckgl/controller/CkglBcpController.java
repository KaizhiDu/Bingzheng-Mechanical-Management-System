package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dscription: 仓库管理 - 未完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bcp/ywcbcp")
@RequiresPathPermission("ckgl:ywcbcp")
public class CkglBcpController extends BaseCRUDController<CkglBcp, String> {

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){

    }

    /**仓库管理 - 半成品Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**
     * Dscription: 查所有已完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @RequestMapping(value = "ajaxBcpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBcp> ajaxBcpList(Queryable queryable, CkglBcp ckglBcp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglBcp> pageJson = ckglBcpService.ajaxBcpList(queryable,ckglBcp);
        return pageJson;
    }

    /**
     * Dscription: 查所有未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    @RequestMapping(value = "ajaxBcpList2", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBcp> ajaxBcpList2(Queryable queryable, CkglBcp ckglBcp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglBcp> pageJson = ckglBcpService.ajaxBcpList2(queryable,ckglBcp);
        return pageJson;
    }

    /**
     * Dscription: 转到已完成页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:45
     */
    @RequestMapping(value = "ywcbcp", method={RequestMethod.GET, RequestMethod.POST})
    public String ywcbcp(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("ywcbcp");
    }

    /**
     * Dscription: 转到未完成页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:45
     */
    @RequestMapping(value = "wwcbcp", method={RequestMethod.GET, RequestMethod.POST})
    public String wwcbcp(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("wwcbcp");
    }
}
