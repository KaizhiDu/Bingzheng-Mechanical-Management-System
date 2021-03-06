package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglDl;
import cn.jeeweb.modules.ckgl.entity.CkglJhs;
import cn.jeeweb.modules.ckgl.entity.CkglXl;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import cn.jeeweb.modules.ckgl.service.ICkglJhsService;
import cn.jeeweb.modules.ckgl.service.ICkglXlService;
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

    /**仓库管理-小类*/
    @Autowired
    private ICkglXlService ckglXlService;

    /**仓库管理 - 进货商*/
    @Autowired
    private ICkglJhsService ckglJhsService;

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

    /**
     * Dscription: 转到设置小类页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 11:47
     */
    @RequestMapping(value = "szxl", method={RequestMethod.GET, RequestMethod.POST})
    public String szxl(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("dlmc", ckglDlService.selectById(id).getDlmc());
        model.addAttribute("dlid", id);
        return display("szxl");
    }

    /**
     * Dscription: 转到添加小类页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:00
     */
    @RequestMapping(value = "createXl", method={RequestMethod.GET, RequestMethod.POST})
    public String createXl(String dlmc, String dlid, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("dlmc", dlmc);
        model.addAttribute("dlid", dlid);
        return display("createXl");
    }

    /**
     * Dscription: 保存小类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:03
     */
    @RequestMapping(value = "saveXl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveXl(CkglXl ckglXl, HttpServletRequest request, HttpServletResponse response, Model model){
        ckglXlService.insert(ckglXl);
    }

    /**
     * Dscription: 根据大类id查到下属工艺小类的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:55
     */
    @RequestMapping(value = "ajaxCkxlList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglXl> ajaxCkxlList(Queryable queryable, CkglXl ckglXl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglXl> pageJson = ckglXlService.ajaxCkxlList(queryable,ckglXl);
        return pageJson;
    }

    /**
     * Dscription: 删除仓库小类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 13:20
     */
    @RequestMapping(value = "deleteXl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteXl(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String xlid = idsArray[i];
            ckglXlService.deleteById(xlid);
        }
    }

    /**
     * Dscription: 转到设置进货商页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 12:51
     */
    @RequestMapping(value = "szJhs", method={RequestMethod.GET, RequestMethod.POST})
    public String szJhs(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("szJhs");
    }

    /**
     * Dscription: 进货商数据显示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 13:12
     */
    @RequestMapping(value = "ajaxJhsList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglJhs> ajaxJhsList(Queryable queryable, CkglJhs ckglJhs, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglJhs> pageJson = ckglJhsService.ajaxJhsList(queryable,ckglJhs);
        return pageJson;
    }

    /**
     * Dscription: 转到创建进货商页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 13:29
     */
    @RequestMapping(value = "createJhs", method={RequestMethod.GET, RequestMethod.POST})
    public String createJhs(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("createJhs");
    }

    /**
     * Dscription: 保存进货商
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 13:32
     */
    @RequestMapping(value = "saveJhs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveJhs(String jhs, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglJhs ckglJhs = new CkglJhs();
        ckglJhs.setJhs(jhs);
        ckglJhsService.insert(ckglJhs);
    }

    /**
     * Dscription: 删除进货商
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/12 13:37
     */
    @RequestMapping(value = "deleteJhs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteJhs(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            ckglJhsService.deleteById(idsArray[i]);
        }
    }


}
