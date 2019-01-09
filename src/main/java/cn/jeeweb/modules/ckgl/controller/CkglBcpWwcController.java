package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglBcpWwc;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgydl;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgxService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgydlSerivce;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGybzglService;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**仓库管理 - 未完成工艺大类Service*/
    @Autowired
    private ICkglWwcgydlSerivce ckglWwcgydlSerivce;

    /**生产计划管理 - 零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产管理 - 零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**生产管理 - 零部件工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

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
        List<CkglWwcgx> data = ckglWwcgxService.getData(id);
        model.addAttribute("data", data);
        return display("xq");
    }

    /**
     * Dscription: 转到加入生产页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 17:52
     */
    @RequestMapping(value = "jrsc", method={RequestMethod.GET, RequestMethod.POST})
    public String jrsc(String bcpid, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("rq", false);
        wrapper.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        model.addAttribute("bcpid", bcpid);
        return display("jrsc");
    }

    /**
     * Dscription: 加入生产
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/8 17:44
     */
    @RequestMapping(value = "saveJrsc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveJrsc(String htid, String bcpid, HttpServletRequest request, HttpServletResponse response, Model model){

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String rq = sdf0.format(date0);

        //先得到半成品信息
        CkglBcp ckglBcp = ckglBcpService.selectById(bcpid);
        String ljid = ckglBcp.getLbjid();
        //再得到wwcgydl
        EntityWrapper<CkglWwcgydl> wrapper = new EntityWrapper<CkglWwcgydl>();
        wrapper.eq("LJID", ljid);
        List<CkglWwcgydl> ckglWwcgydls = ckglWwcgydlSerivce.selectList(wrapper);
        //然后需要获得wwcgx
        List<CkglWwcgx> data = ckglWwcgxService.getData(bcpid);

        //之后需要加入到jhid所在的计划，下属的零件，工艺大类编制，和零件工艺编制

        //首先要看该计划下有没有该零件图号
        String lbjth = ckglBcp.getLbjth();
        int sl = Integer.parseInt(ckglBcp.getRksl());
        boolean isExist = false;
       EntityWrapper<ScjhglLjgl> wrapper1 = new EntityWrapper<ScjhglLjgl>();
       wrapper1.eq("HTID", htid);
        List<ScjhglLjgl> scjhglLjgls = scjhglLjglService.selectList(wrapper1);
        for (ScjhglLjgl s : scjhglLjgls) {
            if (lbjth.equals(s.getLjth())){
                isExist = true;
                break;
            }
        }
        //如果存在的话，只需要添加零件和零件工艺编制
        if (isExist){
            EntityWrapper<ScjhglLjgl> wrapper2 = new EntityWrapper<ScjhglLjgl>();
            wrapper2.eq("LJTH", lbjth);
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectOne(wrapper2);
            int sli = 0;
            int wrksli = 0;
            int sysli = 0;
            if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
                sli = Integer.parseInt(scjhglLjgl.getSl());
            }
            if (scjhglLjgl.getWrksl()!=null&&!scjhglLjgl.getWrksl().equals("")){
                wrksli = Integer.parseInt(scjhglLjgl.getWrksl());
            }
            if (scjhglLjgl.getSysl()!=null&&!scjhglLjgl.getSysl().equals("")){
                sysli = Integer.parseInt(scjhglLjgl.getSysl());
            }
            sli = sli + sl;
            wrksli = wrksli + sl;

            sysli = sysli +sl;
            scjhglLjgl.setSl(sli+"");
            scjhglLjgl.setWrksl(wrksli+"");
            scjhglLjgl.setSysl(sysli+"");
            scjhglLjglService.updateById(scjhglLjgl);

            //然后需要根据gyxlmc和gydlid，增加零件工艺编制对应数量
            String lbjid = scjhglLjgl.getId();
            List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(lbjid);

            for (CkglWwcgx c : data) {
                String gyxlmc = c.getGyxlmc();
                String gydlmc = ckglWwcgydlSerivce.selectById( c.getGydlbzid()).getGydlmc();
                for (ScglLjgybz s : ljgybzByLjid) {
                    String gyxlmc2 = s.getGyxlmc();
                    String gxdlmc2 = scglGydlbzService.selectById(s.getGydlbzid()).getGydlmc();
                    //可以更新数值
                    if (gydlmc.equals(gxdlmc2)&&gyxlmc.equals(gyxlmc2)){
                        s.setSl(s.getSl()+c.getSl());
                        s.setWrksl(s.getWrksl()+c.getWrksl());
                        s.setSysl(s.getSysl()+c.getSysl());
                        scglLjgybzService.updateById(s);
                    }
                }
            }

        }
        else{
            //计划
            ScjhglLjgl scjhglLjgl = new ScjhglLjgl();
            scjhglLjgl.setId(ckglBcp.getLbjid());
            scjhglLjgl.setHtid(htid);
            scjhglLjgl.setLjth(ckglBcp.getLbjth());
            scjhglLjgl.setLjmc(ckglBcp.getLbjmc());
            scjhglLjgl.setSl(ckglBcp.getRksl());
            scjhglLjgl.setWrksl(ckglBcp.getRksl());
            scjhglLjgl.setSysl(ckglBcp.getRksl());
            scjhglLjgl.setSfwwcrk("0");
            scjhglLjgl.setSfsbj("0");
            scjhglLjgl.setRq(rq);
            scjhglLjglService.insert(scjhglLjgl);

            //工艺大类编制
            for (CkglWwcgydl c : ckglWwcgydls) {
                ScglGydlbz s = new ScglGydlbz();
                s.setId(c.getId());
                s.setLjid(c.getLjid());
                s.setGydlid(c.getGydlid());
                s.setGydlmc(c.getGydlmc());
                s.setPx(c.getPx());
                scglGydlbzService.insert(s);
            }

            //零件工艺编制
            for (CkglWwcgx c : data) {
                ScglLjgybz s = new ScglLjgybz();
                s.setId(c.getId());
                s.setGydlbzid(c.getGydlbzid());
                s.setGyxlid(c.getGyxlid());
                s.setGyxlmc(c.getGyxlmc());
                s.setMs(c.getMs());
                s.setPx(c.getPx());
                s.setSl(c.getSl());
                s.setWrksl(c.getWrksl());
                s.setSysl(c.getSysl());
                s.setJhscsl(c.getJhscsl());
                s.setScsfxs(c.getScsfxs());
                scglLjgybzService.insert(s);
            }
        }


        //最后要删除所有wwcgx wwcgydl bcp相关信息
        //先得到半成品信息
        ckglBcpService.deleteById(bcpid);
        ckglWwcgydlSerivce.delete(wrapper);
        for (CkglWwcgx c : data) {
            ckglWwcgxService.deleteById(c.getId());
        }
    }
}
