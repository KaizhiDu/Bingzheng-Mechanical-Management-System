package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglBcpWwc;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Dscription: 仓库管理 - 已完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bcp/wwcbcp")
@RequiresPathPermission("ckgl:wwcbcp")
public class CkglBcpWwcController extends BaseCRUDController<CkglBcpWwc, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    @Autowired
    /**仓库管理 - 半成品*/
    private ICkglBcpService ckglBcpService;

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
        wrapper1.eq("SFSWWCBCP", "1");
        List<CkglBcp> ckglBcps = ckglBcpService.selectList(wrapper1);
        model.addAttribute("bcpList", ckglBcps);
    }
}
