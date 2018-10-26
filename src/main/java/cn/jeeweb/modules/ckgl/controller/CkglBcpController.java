package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
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
 * Dscription: 仓库管理 - 未完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bcp/ywcbcp")
@RequiresPathPermission("ckgl:ywcbcp")
public class CkglBcpController extends BaseCRUDController<CkglBcp, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理 - 半成品Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理 - 成品Service*/
    @Autowired
    private ICkglCpService ckglCpService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //计划
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        //图号和名称
        EntityWrapper<CkglBcp> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("SFSWWCBCP", "0");
        List<CkglBcp> ckglBcps = ckglBcpService.selectList(wrapper1);
        model.addAttribute("bcpList", ckglBcps);
    }

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

    /**
     * Dscription: 半成品入成品库
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/12 13:30
     */
    @RequestMapping(value = "rcpk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void rcpk(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        //先得到半成品信息
        CkglBcp ckglBcp = ckglBcpService.selectById(id);
        //复制一份到成品里面
        CkglCp ckglCp = new CkglCp();
        ckglCp.setJhbh(ckglBcp.getJhbh());
        ckglCp.setJhid(ckglBcp.getJhid());
        ckglCp.setLbjid(ckglBcp.getLbjid());
        ckglCp.setLbjmc(ckglBcp.getLbjmc());
        ckglCp.setLbjth(ckglBcp.getLbjth());
        ckglCp.setRksl(ckglBcp.getRksl());
        //根据图号判断，成品库里面有没有该信息
        EntityWrapper<CkglCp> wrapper = new EntityWrapper<CkglCp>();
        wrapper.eq("LBJTH", ckglBcp.getLbjth());
        wrapper.eq("JHBH", ckglBcp.getJhbh());
        int count = ckglCpService.selectCount(wrapper);
        //插入
        if (count == 0){
            ckglCpService.insert(ckglCp);
        }
        //更新
        else{
            CkglCp ckglCp1 = ckglCpService.selectOne(wrapper);
            int rksl = Integer.parseInt(ckglBcp.getRksl()) + Integer.parseInt(ckglCp1.getRksl());
            ckglCp1.setRksl(rksl+"");
            ckglCpService.updateById(ckglCp1);
        }
        //最后删除半成品里面的信息
        ckglBcpService.deleteById(id);
    }
}
