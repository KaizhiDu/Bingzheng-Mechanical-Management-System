package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import cn.jeeweb.modules.scgl.entity.ScglSzgyxl;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import cn.jeeweb.modules.scgl.service.IScglGymbxlszService;
import cn.jeeweb.modules.scgl.service.IScglSzgyxlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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

    /**工艺模板大类设置Service*/
    @Autowired
    private IScglGymbszService scglGymbszService;
    /**工艺模板小类设置Service*/
    @Autowired
    private IScglGymbxlszService scglGymbxlszService;
    /**设置工艺小类Service*/
    @Autowired
    private IScglSzgyxlService scglSzgyxlService;

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

    /**
     * @Description:    转到添加工艺小类页面
     * @Author:         杜凯之
     * @CreateDate:     2018/9/5 17:13
     * @Version:        1.0
     */
    @RequestMapping(value = "createGyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String createGyxl(HttpServletResponse response, HttpServletRequest request){
        return display("createGyxl");
    }

    /**
     * @Description:    保存工艺大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/5 17:26
     * @Version:        1.0
     */
    @RequestMapping(value = "saveGyxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGyxl(ScglGymbxlsz scglGymbxlsz, HttpServletResponse response, HttpServletRequest request){
        scglGymbxlszService.insert(scglGymbxlsz);
    }

    /**
    * @Description:    工艺模板大类信息展示
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 10:01
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxGymbszList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglGymbsz> ajaxListGrgl(Queryable queryable, ScglGymbsz scglGymbsz, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScglGymbsz> pageJson = scglGymbszService.gymbszList(queryable,scglGymbsz);
        return pageJson;
    }

    /**
     * @Description:    工艺模板小类信息展示
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 10:01
     * @Version:        1.0
     */
    @RequestMapping(value = "ajaxGymbxlszList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglGymbxlsz> ajaxGymbxlszList(String dlid,Queryable queryable, ScglGymbxlsz scglGymbxlsz, HttpServletRequest request, HttpServletResponse response, Model model){
        scglGymbxlsz.setDlid(dlid);
        PageJson<ScglGymbxlsz> pageJson = scglGymbxlszService.gymbxlszList(queryable,scglGymbxlsz,dlid);
        return pageJson;
    }

    /**
    * @Description:    删除工艺模板小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 11:40
    * @Version:        1.0
    */

    public AjaxJson deleteGyxlsz(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArray = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglGymbxlszService.deleteById(idsArray[i]);
        }
        ajaxJson.setMsg("删除成功！！！");
        return ajaxJson;
    }

    /**
    * @Description:    跳转到设置工艺小类页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 16:18
    * @Version:        1.0
    */
    @RequestMapping(value = "szgyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String szgyxl(String id ,HttpServletResponse response, HttpServletRequest request, Model model){
        model.addAttribute("dlid", id);
        return display("szgyxl");
    }

    /**
    * @Description:    添加工艺小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 16:27
    * @Version:        1.0
    */
    @RequestMapping(value = "addGyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String addGyxl(String dlid, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("dlid",dlid);
        return display("addGyxl");
    }

    /**
    * @Description:    添加工艺小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 16:46
    * @Version:        1.0
    */
    @RequestMapping(value = "saveAddGyxl", method={RequestMethod.GET, RequestMethod.POST})
    public AjaxJson saveAddGyxl(String dlid ,String ids ,HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArray = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            ScglSzgyxl scglSzgyxl = new ScglSzgyxl();
            scglSzgyxl.setGydlid(dlid);
            scglSzgyxl.setGyxlid(idsArray[i]);
            scglSzgyxlService.insert(scglSzgyxl);
        }
        ajaxJson.setMsg("添加成功！！！");
        return ajaxJson;
    }

    /**
    * @Description:    显示设置工艺小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 18:45
    * @Version:        1.0
    */
    @RequestMapping(value = "szgyxlList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglSzgyxl> szgyxlList(String dlid,Queryable queryable, ScglSzgyxl scglSzgyxl, HttpServletRequest request, HttpServletResponse response, Model model){

        scglSzgyxl.setGydlid(dlid);
        PageJson<ScglSzgyxl> pageJson = scglSzgyxlService.szgyxlList(queryable,scglSzgyxl);
        return pageJson;
    }

    /**
    * @Description:    删除工艺小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/11 19:18
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteGyxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteGyxl(String ids, HttpServletResponse response, HttpServletRequest request, Model model){
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArray = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglSzgyxlService.deleteById(idsArray[i]);
        }
        ajaxJson.setMsg("删除成功！！！");
        return ajaxJson;
    }

}
