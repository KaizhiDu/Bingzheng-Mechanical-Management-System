package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.jygl.entity.JyglRgjy;
import cn.jeeweb.modules.jygl.service.IJyglRgjyService;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scgl.service.IScglRcrwfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Dscription: 检验管理 - 日工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/30 15:36
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/rgjy")
@RequiresPathPermission("jygl:rgjy")
public class JyglRgjyController extends BaseCRUDController<JyglRgjy, String> {

    /**检验管理 - 日常检验Service*/
    @Autowired
    private IJyglRgjyService jyglRgjyService;

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    /**生产管理-零件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**
     * Dscription: 搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 17:28
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        List<YgsjDTO> ygsjList = scglRcrwfpService.getYgsj();
        model.addAttribute("ygsjList", ygsjList);
    }

    /**
     * Dscription: 展示所有检验信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 17:13
     */
    @RequestMapping(value = "ajaxRgjyList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<JyglRgjy> ajaxRgjyList(Queryable queryable, RgjyDTO rgjyDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<JyglRgjy> pageJson = jyglRgjyService.ajaxRgjyList(queryable,rgjyDTO);
        return pageJson;
    }

    /**
     * Dscription: 转到检验页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 18:05
     */
    @RequestMapping(value = "jy", method={RequestMethod.GET, RequestMethod.POST})
    public String jy(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(id);
        //实际完成量
        String sjwcl = jyglRgjy.getSjwcl();
        model.addAttribute("sjwcl", sjwcl);
        //应完成量
        String ywcl = jyglRgjy.getYwcl();
        model.addAttribute("ywcl", ywcl);
        //零件工艺编制ID
        String ljgybzid = jyglRgjy.getLjgybzid();
        model.addAttribute("ljgybzid", ljgybzid);
        //日工任务ID
        model.addAttribute("rgrwid", id);
        return display("jy");
    }

    /**
     * Dscription: 保存完成量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 18:24
     */
    @RequestMapping(value = "saveWcl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveWcl(String rgrwid, String ljgybzid, String sjwcl, HttpServletRequest request, HttpServletResponse response, Model model){
        //逻辑是先找到日工任务下面的  实际完成量
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(rgrwid);
        String ss = jyglRgjy.getSjwcl();
        int sss = 0;
        if (ss!=null){
            sss = Integer.parseInt(ss);
        }
        //然后加到零件工艺编制下面的  剩余数量
        //再然后零件工艺编制下的剩余数量 - sjwcl 然后更新
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl()+sss-Integer.parseInt(sjwcl);
        scglLjgybz.setSysl(sysl);
        scglLjgybzService.updateById(scglLjgybz);
        //最后更新 日工任务下的 实际完成量
        jyglRgjy.setSjwcl(sjwcl);
        jyglRgjyService.updateById(jyglRgjy);
    }
}
