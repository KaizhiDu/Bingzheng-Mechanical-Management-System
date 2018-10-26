package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.entity.CkglCpCkjl;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglCpCkjlService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 仓库管理 - 成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 18:31
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/cp")
@RequiresPathPermission("ckgl:cp")
public class CkglCpController extends BaseCRUDController<CkglCp, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理 - 半成品Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理 - 成品Service*/
    @Autowired
    private ICkglCpService ckglCpService;

    /**仓库管理 - 成品出库记录Service*/
    @Autowired
    private ICkglCpCkjlService ckglCpCkjlService;

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
        EntityWrapper<CkglCp> wrapper1 = new EntityWrapper<CkglCp>();
        List<CkglCp> ckglCps = ckglCpService.selectList(wrapper1);
        model.addAttribute("cpList", ckglCps);
    }

    /**
     * Dscription: 转到成品出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/26 13:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglCp ckglCp = ckglCpService.selectById(id);
        model.addAttribute("cp",ckglCp);
        return display("ck");
    }

    /**
     * Dscription: 进行出库操作
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/26 14:13
     */
    @RequestMapping(value = "saveCk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveCk(String cpid, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        int cks = 0;
        if (cksl!=null&&!cksl.equals("")){
            cks = Integer.parseInt(cksl);
        }
       //得到成品信息
        CkglCp ckglCp = ckglCpService.selectById(cpid);
        //找一下出库记录表里有没有该记录
        String jhbh = ckglCp.getJhbh();
        String lbjth = ckglCp.getLbjth();
        EntityWrapper<CkglCpCkjl> wrapper = new EntityWrapper<CkglCpCkjl>();
        wrapper.eq("JHBH" ,jhbh);
        wrapper.eq("LBJTH" ,lbjth);
        wrapper.eq("RQ", currentTime);
        int count = ckglCpCkjlService.selectCount(wrapper);
        //没有对应信息，添加一条
        if (count==0){
        CkglCpCkjl ckglCpCkjl = new CkglCpCkjl();
        ckglCpCkjl.setJhid(ckglCp.getJhid());
        ckglCpCkjl.setJhbh(ckglCp.getJhbh());
        ckglCpCkjl.setLbjid(ckglCp.getLbjid());
        ckglCpCkjl.setLbjmc(ckglCp.getLbjmc());
        ckglCpCkjl.setLbjth(ckglCp.getLbjth());
        ckglCpCkjl.setRksl(cksl);
        ckglCpCkjl.setRq(currentTime);
            ckglCpCkjlService.insert(ckglCpCkjl);
        }
        //加上rksl
        else{
            CkglCpCkjl ckglCpCkjl = ckglCpCkjlService.selectOne(wrapper);
            int xsll =  Integer.parseInt(ckglCpCkjl.getRksl()) + cks;
            ckglCpCkjl.setRksl(xsll+"");
            ckglCpCkjlService.updateById(ckglCpCkjl);
        }

        //下面处理成品库信息
        int zsl = 0;
        if (ckglCp.getRksl()!=null&&!ckglCp.getRksl().equals("")){
            zsl = Integer.parseInt(ckglCp.getRksl());
        }

        //xsl 就是需要更新的数值
        int xsl = zsl - cks;
        //删除
        if (xsl==0){
            ckglCpService.deleteById(ckglCp.getId());

        }
        //更新
        else{
            ckglCp.setRksl(xsl+"");
            ckglCpService.updateById(ckglCp);
        }


    }

}
