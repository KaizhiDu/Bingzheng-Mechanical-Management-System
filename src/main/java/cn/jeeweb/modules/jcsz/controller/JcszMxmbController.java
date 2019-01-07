package cn.jeeweb.modules.jcsz.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jcsz.entity.JcszMxmb;
import cn.jeeweb.modules.jcsz.service.IJcszMxmbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dscription: 基础设置 - 明细模板
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/4 14:37
 */
@Controller
@RequestMapping("${admin.url.prefix}/jcsz/mxmb")
@RequiresPathPermission("jcsz:mxmb")
public class JcszMxmbController extends BaseCRUDController<JcszMxmb, String> {

    /**基础设置资金数额Service*/
    @Autowired
    private IJcszMxmbService jcszMxmbService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * Dscription: 添加明细模板
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:03
     */
    @RequestMapping(value = "insertMxmb", method={RequestMethod.GET, RequestMethod.POST})
    public String insertMxmb(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("insertMxmb");
    }

    /**
     * Dscription: 保存添加明细模板
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:27
     */
    @RequestMapping(value = "addMxmb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addMxmb(JcszMxmb jcszMxmb, HttpServletRequest request, HttpServletResponse response, Model model){
        jcszMxmb.setSort(0);
        jcszMxmbService.insert(jcszMxmb);
    }

    /**
     * Dscription: 展示所有明细模板信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:31
     */
    @RequestMapping(value = "ajaxMxmbList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<JcszMxmb> ajaxMxmbList(Queryable queryable, String type, HttpServletRequest request, HttpServletResponse response, Model model){
        JcszMxmb jcszMxmb = new JcszMxmb();
        jcszMxmb.setType(type);
        PageJson<JcszMxmb> pageJson = jcszMxmbService.ajaxMxmbList(queryable,jcszMxmb);
        return pageJson;
    }
    
    /**
     * Dscription: 转到修改页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 22:06
     */
    @RequestMapping(value = "modifyMxmb", method={RequestMethod.GET, RequestMethod.POST})
    public String modifyMxmb(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        JcszMxmb jcszMxmb = jcszMxmbService.selectById(id);
        model.addAttribute("jcszMxmb", jcszMxmb);
        return display("modifyMxmb");
    }

    /**
     * Dscription: 更新明细模板
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 22:29
     */
    @RequestMapping(value = "updateMxmb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void updateMxmb(JcszMxmb jcszMxmb, HttpServletRequest request, HttpServletResponse response, Model model){
        jcszMxmbService.updateById(jcszMxmb);
    }
}
