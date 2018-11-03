package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglBcpWwc;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgxService;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGybzglService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
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

    /**仓库管理 - 半成品Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理 - 未完成工序Service*/
    @Autowired
    private ICkglWwcgxService ckglWwcgxService;

    /**生产计划管理 - 零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产管理 - 零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

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

    /**
     * Dscription: 转到零部件详情页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 17:37
     */
    @RequestMapping(value = "xq", method={RequestMethod.GET, RequestMethod.POST})
    public String xq(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglWwcgx> wrapper = new EntityWrapper<CkglWwcgx>();
        wrapper.eq("BCPID", id);
        List<CkglWwcgx> list = ckglWwcgxService.selectList(wrapper);
        model.addAttribute("gxList", list);
        return display("xq");
    }

    /**
     * Dscription: 加入生产
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 17:52
     */
    @RequestMapping(value = "jrsc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void jrsc(String bcpid, HttpServletRequest request, HttpServletResponse response, Model model){
        //首先删除wwcgx里面指定bcpid的数据
        EntityWrapper<CkglWwcgx> wrapper = new EntityWrapper<CkglWwcgx>();
        wrapper.eq("BCPID", bcpid);
        ckglWwcgxService.delete(wrapper);

        //再得到ljid 然后把ljgl里面的是否未完成入库设为0
        CkglBcp ckglBcp = ckglBcpService.selectById(bcpid);
        String ljid = ckglBcp.getLbjid();
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
        scjhglLjgl.setSfwwcrk("0");
        scjhglLjglService.updateById(scjhglLjgl);

        //然后得到ljid下的所有工艺 把所有ljgybz的scsfxs设为1
        List<ScglLjgybz> ljgybzList = scglLjgybzService.getLjgybzByLjid(ljid);
        for (ScglLjgybz s: ljgybzList) {
            s.setScsfxs("1");
            scglLjgybzService.updateById(s);
        }

        //最后删除半成品数据
        ckglBcpService.deleteById(bcpid);
    }
}
