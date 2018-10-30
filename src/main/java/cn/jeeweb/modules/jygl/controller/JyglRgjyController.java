package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.jygl.entity.JyglRgjy;
import cn.jeeweb.modules.jygl.service.IJyglRgjyService;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scgl.service.IScglRcrwfpService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjzc;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjzcService;
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

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**生产计划管理-零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglJhglService;

    /**仓库管理Service*/
    @Autowired
    private ICkglBcpService ckglService;

    /**部件组成Service*/
    @Autowired
    private IScjhglBjzcService scjhglBjzcService;

    /**计划管理*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

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
        //首先要判断之前是否已经设置过了实际完成量
        JyglRgjy preJyglRgjy = jyglRgjyService.selectById(rgrwid);
        //如果之前已经设置了实际完成量，则需要先恢复数据
        if (preJyglRgjy.getSjwcl()!=null&&!preJyglRgjy.getSjwcl().equals("")){
            //之前的实际完成量
            int preSjwcl = Integer.parseInt(preJyglRgjy.getSjwcl());
            ScglLjgybz preScglLjgybz = scglLjgybzService.selectById(ljgybzid);
            //把ljgybz的sysl加上preSjwcl
            int preSysl = preScglLjgybz.getSysl();
            int sysl = preSysl + preSjwcl;
            int preYwcl =  0;
            if (preJyglRgjy.getYwcl()!=null&&!preJyglRgjy.getYwcl().equals("")){
                preYwcl = Integer.parseInt(preJyglRgjy.getYwcl());
            }

            //同时也要改变jhscsl
            preScglLjgybz.setSysl(sysl);
            preScglLjgybz.setJhscsl(preYwcl);
            scglLjgybzService.updateById(preScglLjgybz);

            //下一步要拿到零件下的所有工艺
            String gydlbzid = scglLjgybzService.selectById(ljgybzid).getGydlbzid();
            String ljid = scglGydlbzService.selectById(gydlbzid).getLjid();
            List<ScglLjgybz> ljgybzByLjidList = scglLjgybzService.getLjgybzByLjid(ljid);
            //判断要入库多少
            int abc = 1000000;
            //现在得出的abc是应该入库的数量
            for (ScglLjgybz s: ljgybzByLjidList) {
                int a = s.getSl() - s.getSysl();
                if (a<abc){
                    abc = a;
                }
            }

            for (ScglLjgybz s: ljgybzByLjidList) {
               int wrksl = s.getSl() - abc;
               s.setWrksl(wrksl);
                scglLjgybzService.updateById(s);
            }

            //改变零件里面的wrksl
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
            int sl = 0;
            if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
                sl = Integer.parseInt(scjhglLjgl.getSl());
            }
            int wrkl = sl - abc;
            scjhglLjgl.setWrksl(wrkl+"");
            scjhglLjglService.updateById(scjhglLjgl);

            //把日工任务里面的sjwcl设为空
            preJyglRgjy.setSjwcl("");
            jyglRgjyService.updateById(preJyglRgjy);

            //得到零件图号
            String lbjth = scjhglLjgl.getLjth();
            EntityWrapper<CkglBcp> wrapper = new EntityWrapper<CkglBcp>();
            wrapper.eq("LBJTH", lbjth);
            //先判断半成品库里有没有该零件
            int count = ckglService.selectCount(wrapper);
            //没有该零件
            if (count==0 ){
                //先判断abc是否为0
                //为0的话要删除半成品已完成库里面相关的数量
                if (abc==0){

                }
                //更新半成品已完成库里面相关数量
                else{
                    CkglBcp ckgl = new CkglBcp();
                    String jhid = scjhglLjgl.getHtid();
                    String jhbh = scjhglJhglService.selectById(jhid).getHtbh();
                    String lbjid = scjhglLjgl.getId();
                    String lbjmc = scjhglLjgl.getLjmc();
                    String kczl = "08";
                    String sfswwcbcp = "0";
                    ckgl.setJhid(jhid);
                    ckgl.setJhbh(jhbh);
                    ckgl.setLbjid(lbjid);
                    ckgl.setLbjmc(lbjmc);
                    ckgl.setLbjth(scjhglLjgl.getLjth());
                    ckgl.setKczl(kczl);
                    ckgl.setSfswwcbcp(sfswwcbcp);
                    ckgl.setRksl(abc+"");
                    ckglService.insert(ckgl);
                }
            }
            //有该零件
            else{
                //先判断abc是否为0
                //为0的话要删除半成品已完成库里面相关的数量
                if (abc==0){
                    ckglService.delete(wrapper);
                }
                //更新半成品已完成库里面相关数量
                else{
                    CkglBcp ckglBcp111 = ckglService.selectOne(wrapper);
                    ckglBcp111.setRksl(abc+"");
                    ckglService.updateById(ckglBcp111);
                }
            }

        }





        //逻辑是先找到日工任务下面的  实际完成量
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(rgrwid);
        String ss = jyglRgjy.getSjwcl();
        int sss = 0;
        if (ss!=null&&!ss.equals("")){
            sss = Integer.parseInt(ss);
        }

        //第一步是零件工艺编制下面的计划生产数量减去应完成量
        String ywcl = jyglRgjy.getYwcl();
        ScglLjgybz oldscglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int ywcli = 0;
        if (ywcl!=null&&!ywcl.equals("")){
            ywcli = Integer.parseInt(ywcl);
        }
        oldscglLjgybz.setJhscsl(oldscglLjgybz.getJhscsl()-ywcli);
        scglLjgybzService.updateById(oldscglLjgybz);

        //再然后零件工艺编制下的剩余数量 - sjwcl 然后更新
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl()+sss-Integer.parseInt(sjwcl);
        scglLjgybz.setSysl(sysl);
        scglLjgybzService.updateById(scglLjgybz);

        //最后更新 日工任务下的 实际完成量
        jyglRgjy.setSjwcl(sjwcl);
        jyglRgjyService.updateById(jyglRgjy);

        //判断该零件工艺所在的零件下的所有工艺，是否有全完成了的。有就入仓库（判断sysl 和 wrksl）
        //首先要拿到零件ID
        String gydlbzid = scglLjgybzService.selectById(ljgybzid).getGydlbzid();
        String ljid = scglGydlbzService.selectById(gydlbzid).getLjid();
        //下一步要拿到零件下的所有工艺
        List<ScglLjgybz> ljgybzByLjidList = scglLjgybzService.getLjgybzByLjid(ljid);
        //判断要入库多少
        int rksl = 1000000;
        for (ScglLjgybz s: ljgybzByLjidList) {
            int a = s.getWrksl() - s.getSysl();
            if (a<rksl){
                rksl = a;
            }
        }
        //判断是不是部件
        EntityWrapper<ScjhglBjzc> wrapper0 = new EntityWrapper<ScjhglBjzc>();
        wrapper0.eq("BJID", ljid);
        int count0 = scjhglBjzcService.selectCount(wrapper0);
        //是的话要在入库之前要减去下属零件数量
        if (count0>0){
            List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper0);
            for (ScjhglBjzc s: scjhglBjzcs) {
                //需要得到零件图号，然后减去库存
                ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(s.getLjid());
                String ljth = scjhglLjgl.getLjth();
                //通过ljid找到jhbh
                String jhbh = scjhglHtglService.selectById(scjhglLjgl.getHtid()).getHtbh();
                EntityWrapper<CkglBcp> wrapper11 = new EntityWrapper<CkglBcp>();
                wrapper11.eq("LBJTH", ljth);
                wrapper11.eq("JHBH", jhbh);
                CkglBcp ckglBcp = ckglService.selectOne(wrapper11);
                int newsl = Integer.parseInt(ckglBcp.getRksl()) - rksl;
                //如果新的数量等于0，则删除
                if (newsl==0){
                    ckglService.deleteById(ckglBcp.getId());
                }
                else{
                    ckglBcp.setRksl(newsl+"");
                    ckglService.updateById(ckglBcp);
                }

            }
        }


        //依次减去数量未入库数量
        for (ScglLjgybz s: ljgybzByLjidList) {
            int newWrksl = s.getWrksl() - rksl;
            s.setWrksl(newWrksl);
            scglLjgybzService.updateById(s);
        }

        //还要减去零件数量
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
        int wrksl = Integer.parseInt(scjhglLjgl.getWrksl()) - rksl;
        scjhglLjgl.setWrksl(wrksl+"");
        scjhglLjglService.updateById(scjhglLjgl);

        //最后入仓库
        //先判断是否要入库,如果rksl不为0就入仓库
        if (rksl!=0){
            CkglBcp ckgl = new CkglBcp();
            String jhid = scjhglLjgl.getHtid();
            String jhbh = scjhglJhglService.selectById(jhid).getHtbh();
            String lbjid = scjhglLjgl.getId();
            String lbjmc = scjhglLjgl.getLjmc();
            String lbjth = scjhglLjgl.getLjth();
            String kczl = "08";
            String sfswwcbcp = "0";
            ckgl.setJhid(jhid);
            ckgl.setJhbh(jhbh);
            ckgl.setLbjid(lbjid);
            ckgl.setLbjmc(lbjmc);
            ckgl.setLbjth(lbjth);
            ckgl.setKczl(kczl);
            ckgl.setSfswwcbcp(sfswwcbcp);
            ckgl.setRksl(rksl+"");
            //要查询有没有同样的图号
            EntityWrapper<CkglBcp> wrapper = new EntityWrapper<CkglBcp>();
            wrapper.eq("JHBH", jhbh);
            wrapper.eq("LBJTH" ,lbjth);
            wrapper.eq("SFSWWCBCP", "0");
            int count = ckglService.selectCount(wrapper);
            //插入新记录
            if (count == 0){
                ckglService.insert(ckgl);
            }
            //更新入库数量
            else{
                CkglBcp ckgl2 = ckglService.selectOne(wrapper);
                int newRksl = Integer.parseInt(ckgl2.getRksl())+rksl;
                ckgl2.setRksl(newRksl+"");
                ckglService.updateById(ckgl2);
            }
        }
    }

    /**
     * Dscription: 是否大于剩余数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/17 21:00
     */
    @RequestMapping(value = "sfdysysl", method={RequestMethod.GET, RequestMethod.POST})
    public int sfdysysl(String ljgybzid, String sjwcl, HttpServletRequest request, HttpServletResponse response, Model model){
        int flag = 0;
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl();
        int sjwcli = 0;
        if (sjwcl!=null&&!sjwcl.equals("")){
            sjwcli = Integer.parseInt(sjwcl);
        }
        if (sjwcli>sysl){
            flag = 1;
        }
        return flag;
    }
}
