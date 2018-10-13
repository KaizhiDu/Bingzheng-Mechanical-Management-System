package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglDl;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dscription: 仓库管理 - 仓库分类设置
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 9:48
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/ckflsz")
@RequiresPathPermission("ckgl:ckflsz")
public class CkglCkflszController extends BaseCRUDController<CkglDl, String> {

    /**仓库管理-大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**
     * Dscription: 转到添加大类页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 10:23
     */
    @RequestMapping(value = "createDl", method={RequestMethod.GET, RequestMethod.POST})
    public String createDl(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("createDl");
    }

    /**
     * Dscription: 保存大类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 10:36
     */
    @RequestMapping(value = "saveDl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveDl(CkglDl ckglDl, HttpServletRequest request, HttpServletResponse response, Model model){
        ckglDlService.insert(ckglDl);
    }
}
