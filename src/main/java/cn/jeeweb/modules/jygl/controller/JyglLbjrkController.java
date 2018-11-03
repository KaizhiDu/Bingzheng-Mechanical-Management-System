package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgxService;
import cn.jeeweb.modules.jygl.entity.JyglLbjrk;
import cn.jeeweb.modules.jygl.service.IJyglLbjrkService;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
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
 * Dscription: 检验管理 - 零部件入库
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 16:34
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/lbjrk")
@RequiresPathPermission("jygl:lbjrk")
public class JyglLbjrkController extends BaseCRUDController<JyglLbjrk, String> {

    /**检验管理 - 零部件入库管理*/
    @Autowired
    private IJyglLbjrkService jyglLbjrkService;

    /**生产管理-零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**生产计划管理-零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理-半成品管理Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理-未完成工序Service*/
    @Autowired
    private ICkglWwcgxService ckglWwcgxService;




    /**
     * @Description:    搜索项
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 17:22
     * @Version:        1.0
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    /**
     * @Description:    展示所有零部件信息（除了未完成入库的零部件）
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    @RequestMapping(value = "ajaxlbjglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<JyglLbjrk> ajaxlbjglList(Queryable queryable, JyglLbjrk jyglLbjrk, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<JyglLbjrk> pageJson = jyglLbjrkService.ajaxlbjglList(queryable,jyglLbjrk);
        return pageJson;
    }

    /**
     * Dscription: 入半成品库
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 12:02
     */
    @RequestMapping(value = "rbcpk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void rbcpk(String ljid, HttpServletRequest request, HttpServletResponse response, Model model){
        //首先要把零部件 是否未完成入库 设为1
        ScjhglLjgl ljgl = scjhglLjglService.selectById(ljid);
        ljgl.setSfwwcrk("1");
        scjhglLjglService.updateById(ljgl);
        String lbjid = ljgl.getId();
        String lbjmc = ljgl.getLjmc();
        String lbjth = ljgl.getLjth();
        String rksl = ljgl.getWrksl();

        //之后要把零部件工艺编制的 生产是否显示 设为0
        //在最后一步一起完成

        //下一步要插入bcp表
        String jhid = ljgl.getHtid();
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(jhid);
        String jhbh = scjhglHtgl.getHtbh();
        //要查询有没有同样的图号
        EntityWrapper<CkglBcp> wrapper = new EntityWrapper<CkglBcp>();
        wrapper.eq("LBJTH" ,lbjth);
        wrapper.eq("SFSWWCBCP", "1");
        int count = ckglBcpService.selectCount(wrapper);
        //插入新记录
        if (count == 0){
            CkglBcp ckglBcp = new CkglBcp();
            ckglBcp.setJhid(jhid);
            ckglBcp.setJhbh(jhbh);
            ckglBcp.setRksl(rksl);
            ckglBcp.setLbjid(lbjid);
            ckglBcp.setLbjmc(lbjmc);
            ckglBcp.setLbjth(lbjth);
            ckglBcp.setSfswwcbcp("1");
            ckglBcpService.insert(ckglBcp);
        }
        //更新入库数量
        else{
            CkglBcp ckgl2 = ckglBcpService.selectOne(wrapper);
            int newRksl = Integer.parseInt(ckgl2.getRksl())+Integer.parseInt(rksl);
            ckgl2.setRksl(newRksl+"");
            ckglBcpService.updateById(ckgl2);
        }

        //最后要把工序插入wwcgx表
        //拿到半成品ID
       String bcpid = ckglBcpService.selectOne(wrapper).getId();
        //拿到零部件下的所有工艺
        List<ScglLjgybz> ljgybzByLjidList = scglLjgybzService.getLjgybzByLjid(ljid);
        //循环插入wwcgx表里
        for (ScglLjgybz s:ljgybzByLjidList) {
            s.setScsfxs("0");
            scglLjgybzService.updateById(s);
            CkglWwcgx ckglWwcgx = new CkglWwcgx();
            ckglWwcgx.setBcpid(bcpid);
            ckglWwcgx.setGyxlmc(s.getGyxlmc());
            ckglWwcgx.setSl(s.getSl()+"");
            ckglWwcgx.setSysl(s.getSysl()+"");
            ckglWwcgx.setWrksl(s.getWrksl()+"");
            ckglWwcgxService.insert(ckglWwcgx);
        }
    }
}
