package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import cn.jeeweb.modules.jygl.dto.BgjyDTO;
import cn.jeeweb.modules.jygl.dto.BgjyxqDTO;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.jygl.entity.JyglBgjy;
import cn.jeeweb.modules.jygl.service.IJyglBgjyService;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjzc;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjzcService;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 检验管理 - 包工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:54
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/bgjy")
@RequiresPathPermission("jygl:bgjy")
public class JyglBgjyController extends BaseCRUDController<JyglBgjy, String> {

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    /**检验管理-包工检验Service*/
    @Autowired
    private IJyglBgjyService jyglBgjyService;

    /**生产管理-零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**生产管理-包工任务分配Service*/
    @Autowired
    private IScglBgrwfpService scglBgrwfpService;

    /**员工管理-员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**生产管理-包工明细Service*/
    @Autowired
    private IScglBgmxService scglBgmxService;

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**生产计划管理-零部件管理Service*/
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

    /**计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**分配设备管理Service*/
    @Autowired
    private IScglBgsbService scglBgsbService;

    /**包工任务Service*/
    @Autowired
    private IScglBgrwService scglBgrwService;

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
     * Dscription: 展示所有包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    @RequestMapping(value = "ajaxBgjyList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgjyDTO> ajaxBgjyList(Queryable queryable, BgjyDTO bgjyDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgjyDTO> pageJson = jyglBgjyService.ajaxBgjyList(queryable,bgjyDTO);
        return pageJson;
    }

    /**
     * Dscription: 转到检验页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:41
     */
    @RequestMapping(value = "jy", method={RequestMethod.GET, RequestMethod.POST})
    public String jy(String id, String bgrwfpid, HttpServletRequest request, HttpServletResponse response, Model model){
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(id);

        //包工任务分配ID
        model.addAttribute("bgrwfpid", bgrwfpid);

        //实际完成量
        String sjwcl = jyglBgjy.getSjwcl();
        model.addAttribute("sjwcl", sjwcl);
        //应完成量
        String ywcl = jyglBgjy.getYwcl();
        model.addAttribute("ywcl", ywcl);
        //零部件工艺编制ID
        String ljgybzid = jyglBgjy.getLjgybzid();
        model.addAttribute("ljgybzid", ljgybzid);
        //报废量
        model.addAttribute("bfl", jyglBgjy.getBfl());
        //日工任务ID
        model.addAttribute("bgrwid", id);
        return display("jy");
    }

    /**
     * Dscription: 展示所有包工明细信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:51
     */
    @RequestMapping(value = "ajaxBgjyxqList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<BgjyxqDTO> ajaxBgjyList(String id ,Queryable queryable, BgjyxqDTO bgjyxqDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<BgjyxqDTO> pageJson = jyglBgjyService.ajaxBgjyxqList(queryable,bgjyxqDTO,id);
        return pageJson;
    }

    /**
     * Dscription: 检验成功后，需要扣除相应的数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 12:13
     */
    @RequestMapping(value = "saveWcl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveWcl(String bgrwid, String bgrwfpid, String ljgybzid, String sjwcl, String bfl, HttpServletRequest request, HttpServletResponse response, Model model){

        JyglBgjy preJyglBgjy = jyglBgjyService.selectById(bgrwid);
        String gydlbzid = scglLjgybzService.selectById(ljgybzid).getGydlbzid();
        String ljid = scglGydlbzService.selectById(gydlbzid).getLjid();

        //首先要判断之前是否已经设置过了实际完成量
        //如果之前已经设置了实际完成量，则需要先恢复数据
        if (preJyglBgjy.getSjwcl()!=null&&!preJyglBgjy.getSjwcl().equals("")){
            //之前的实际完成量
            int preSjwcl = Integer.parseInt(preJyglBgjy.getSjwcl());
            ScglLjgybz preScglLjgybz = scglLjgybzService.selectById(ljgybzid);
            //把ljgybz的sysl加上preSjwcl
            int preSysl = preScglLjgybz.getSysl();
            int sysl = preSysl + preSjwcl;
            int preYwcl =  0;
            if (preJyglBgjy.getYwcl()!=null&&!preJyglBgjy.getYwcl().equals("")){
                preYwcl = Integer.parseInt(preJyglBgjy.getYwcl());
            }

            //同时也要改变jhscsl
            preScglLjgybz.setSysl(sysl);
            preScglLjgybz.setJhscsl(preYwcl);
            scglLjgybzService.updateById(preScglLjgybz);

            //下一步要拿到零部件下的所有工艺
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

            //判断是不是部件
            EntityWrapper<ScjhglBjzc> wrapper0 = new EntityWrapper<ScjhglBjzc>();
            wrapper0.eq("BJID", ljid);
            int count0 = scjhglBjzcService.selectCount(wrapper0);
            //如果是部件的话，要在半成品完成品库里面加上对应零部件的信息
            if (count0>0){
                List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper0);
                for (ScjhglBjzc s: scjhglBjzcs) {
                    //需要得到零部件图号，然后减去库存
                    ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(s.getLjid());
                    String ljth = scjhglLjgl.getLjth();
                    //通过ljid找到jhbh
                    String jhbh = scjhglHtglService.selectById(scjhglLjgl.getHtid()).getHtbh();
                    EntityWrapper<CkglBcp> wrapper11 = new EntityWrapper<CkglBcp>();
                    wrapper11.eq("LBJTH", ljth);
                    wrapper11.eq("JHBH", jhbh);
                    //看半成品完成品库里面有没有该零部件
                    int count2 = ckglService.selectCount(wrapper11);
                    //有就更新数量
                    if (count2>0){
                        CkglBcp ckglBcp = ckglService.selectOne(wrapper11);
                        int rksl = 0;
                        if (ckglBcp.getRksl()!=null&&!ckglBcp.getRksl().equals("")){
                            rksl = Integer.parseInt(ckglBcp.getRksl());
                        }
                        rksl = rksl + abc;
                        ckglBcp.setRksl(rksl+"");
                        ckglService.updateById(ckglBcp);
                    }
                    //没有就插入一条记录
                    else{
                        CkglBcp ckgl = new CkglBcp();
                        String jhid = scjhglLjgl.getHtid();
                        String jhbh1 = scjhglJhglService.selectById(jhid).getHtbh();
                        String lbjid = scjhglLjgl.getId();
                        String lbjmc = scjhglLjgl.getLjmc();
                        String lbjth = scjhglLjgl.getLjth();
                        String kczl = "08";
                        String sfswwcbcp = "0";
                        ckgl.setJhid(jhid);
                        ckgl.setJhbh(jhbh1);
                        ckgl.setLbjid(lbjid);
                        ckgl.setLbjmc(lbjmc);
                        ckgl.setLbjth(lbjth);
                        ckgl.setKczl(kczl);
                        ckgl.setSfswwcbcp(sfswwcbcp);
                        ckgl.setRksl(abc+"");
                        ckglService.insert(ckgl);
                    }
                }
            }


            for (ScglLjgybz s: ljgybzByLjidList) {
                int wrksl = s.getSl() - abc;
                s.setWrksl(wrksl);
                scglLjgybzService.updateById(s);
            }

            //改变零部件里面的wrksl
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
            int sl = 0;
            if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
                sl = Integer.parseInt(scjhglLjgl.getSl());
            }
            int wrkl = sl - abc;
            scjhglLjgl.setWrksl(wrkl+"");
            scjhglLjglService.updateById(scjhglLjgl);

            //把日工任务里面的sjwcl设为空
            preJyglBgjy.setSjwcl("");
            jyglBgjyService.updateById(preJyglBgjy);

            //得到零部件图号
            String lbjth = scjhglLjgl.getLjth();
            EntityWrapper<CkglBcp> wrapper = new EntityWrapper<CkglBcp>();
            wrapper.eq("LBJTH", lbjth);
            //先判断半成品库里有没有该零部件
            int count = ckglService.selectCount(wrapper);
            //没有该零部件
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
            //有该零部件
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
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(bgrwid);
        String ss = jyglBgjy.getSjwcl();
        int sss = 0;
        if (ss!=null&&!ss.equals("")){
            sss = Integer.parseInt(ss);
        }

        //第一步是零部件工艺编制下面的计划生产数量减去应完成量
        String ywcl = jyglBgjy.getYwcl();
        ScglLjgybz oldscglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int ywcli = 0;
        if (ywcl!=null&&!ywcl.equals("")){
            ywcli = Integer.parseInt(ywcl);
        }
        oldscglLjgybz.setJhscsl(oldscglLjgybz.getJhscsl()-ywcli);
        scglLjgybzService.updateById(oldscglLjgybz);

        //再然后零部件工艺编制下的剩余数量 - sjwcl 然后更新
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl()+sss-Integer.parseInt(sjwcl);
        scglLjgybz.setSysl(sysl);
        scglLjgybzService.updateById(scglLjgybz);

        //最后更新 日工任务下的 实际完成量
        jyglBgjy.setSjwcl(sjwcl);
        jyglBgjyService.updateById(jyglBgjy);

        //判断该零部件工艺所在的零部件下的所有工艺，是否有全完成了的。有就入仓库（判断sysl 和 wrksl）
        //首先要拿到零部件ID
        //下一步要拿到零部件下的所有工艺
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
        //是的话要在入库之前要减去下属零部件数量
        if (count0>0){
            List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper0);
            for (ScjhglBjzc s: scjhglBjzcs) {
                //需要得到零部件图号，然后减去库存
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

        //还要减去零部件数量
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

        //拿到之前的报废量
        int preBfli = 0;
        int bfli = 0;
        String preBfl = preJyglBgjy.getBfl();
        if (preBfl!=null&&!preBfl.equals("")){
            preBfli = Integer.parseInt(preBfl);
        }
        if (bfl!=null&&!bfl.equals("")){
            bfli = Integer.parseInt(bfl);
        }
        //工艺和零件减去这个realbfl
        int realbfl = bfli - preBfli;

        ScjhglLjgl scjhglLjgl0 = scjhglLjglService.selectById(ljid);
        scjhglLjgl0.getSl();
        int zsl = 0;
        int zsysl = 0;
        int zwrksl = 0;
        if (scjhglLjgl0.getSl()!=null&&!scjhglLjgl0.getSl().equals("")){
            zsl = Integer.parseInt(scjhglLjgl0.getSl());
        }
        if (scjhglLjgl0.getSysl()!=null&&!scjhglLjgl0.getSysl().equals("")){
            zsysl = Integer.parseInt(scjhglLjgl0.getSysl());
        }
        if (scjhglLjgl0.getWrksl()!=null&&!scjhglLjgl0.getWrksl().equals("")){
            zwrksl = Integer.parseInt(scjhglLjgl0.getWrksl());
        }
        zsl = zsl - realbfl;
        zsysl = zsysl - realbfl;
        zwrksl = zwrksl - realbfl;
        scjhglLjgl0.setSl(zsl+"");
        scjhglLjgl0.setSysl(zsysl+"");
        scjhglLjgl0.setWrksl(zwrksl+"");
        scjhglLjglService.updateById(scjhglLjgl0);
        List<ScglLjgybz> getLjgybzList = scglLjgybzService.getLjgybzByLjid(ljid);
        for (ScglLjgybz s : getLjgybzList) {
            int zsl2 = s.getSl();
            int zwrksl2 = s.getWrksl();
            int zsysl2 = s.getSysl();
            zsl2 = zsl2 - realbfl;
            zsysl2 = zsysl2 - realbfl;
            zwrksl2 = zwrksl2 - realbfl;
            s.setSl(zsl2);
            s.setSysl(zsysl2);
            s.setWrksl(zwrksl2);
            scglLjgybzService.updateById(s);
        }
        JyglBgjy finalBgjy = jyglBgjyService.selectById(bgrwid);
        finalBgjy.setBfl(bfl);
        jyglBgjyService.updateById(finalBgjy);

        //得到与该任务平齐的所有任务
        String fpsbid = finalBgjy.getFpsbid();
        ScglBgsb scglBgsb = scglBgsbService.selectById(fpsbid);
        //String bgrwfpid = scglBgsb.getBgrwfpid();
        List<ScglBgrw> bgrwByBgrwfpid = scglBgrwService.getBgrwByBgrwfpid(bgrwfpid);
        if (bgrwByBgrwfpid.size()>0){
            int bfll = 0;
            for (ScglBgrw s : bgrwByBgrwfpid) {
                if (s.getBfl()!=null&&!s.getBfl().equals("")){
                    bfll = bfll + Integer.parseInt(s.getBfl());
                }
            }
            //现在得到的bfll就是总的报废量
            //0为未满足检验完成条件；1未满足检验完成条件
            int flag = 1;
            for (ScglBgrw s : bgrwByBgrwfpid) {
                int ywcll = 0;
                if (s.getYwcl()!=null&&!s.getYwcl().equals("")){
                    ywcll = Integer.parseInt(s.getYwcl());
                }
                int sjwcll = 0;
                if (s.getSjwcl()!=null&&!s.getSjwcl().equals("")){
                    sjwcll = Integer.parseInt(s.getSjwcl());
                }
                if (sjwcll < (ywcll-bfll)){
                    flag = 0;
                    break;
                }
            }
            //flag是1的话
           if (flag == 1){
                //首先判断bgrwfp里面sfwc是否为1
               ScglBgrwfp scglBgrwfp = scglBgrwfpService.selectById(bgrwfpid);
               //为0的话就可以把钱算上
               if (scglBgrwfp.getSfwc().equals("0")){
                   //先更新包工任务分配里面的sfwc
                   scglBgrwfp.setSfwc("1");
                   scglBgrwfpService.updateById(scglBgrwfp);

                   EntityWrapper<ScglBgrwfp> wrapper111 = new EntityWrapper<ScglBgrwfp>();
                   wrapper111.eq("YGID", scglBgrwfp.getYgid());
                   wrapper111.eq("SFWC", "0");
                   int count111 = scglBgrwfpService.selectCount(wrapper111);
                   //如果count1为0，那么要更改所有rcrwfp里面的bgzy
                   String content = "";
                   if (count111 == 0){
                       content = "";
                   }
                   else{
                       content = "已分配包工";
                   }
                       EntityWrapper<ScglRcrwfp> wrapper222 = new EntityWrapper<ScglRcrwfp>();
                       wrapper222.eq("YGID", scglBgrwfp.getYgid());
                       List<ScglRcrwfp> scglRcrwfps = scglRcrwfpService.selectList(wrapper222);
                       for (ScglRcrwfp s : scglRcrwfps) {
                           s.setBgzy(content);
                           scglRcrwfpService.updateById(s);
                       }

                   EntityWrapper<ScglBgmx> wrapper = new EntityWrapper<ScglBgmx>();
                   wrapper.eq("BGRWFPID", bgrwfpid);
                   ScglBgmx scglBgmx = scglBgmxService.selectOne(wrapper);
                   float cbjee = 0;
                   if (scglBgmx.getCbje()!=null&&!scglBgmx.getCbje().equals("")){
                       cbjee = Float.parseFloat(scglBgmx.getCbje());
                   }
                   SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
                   Date date0 = new Date();
                   String currentDate = sdf0.format(date0);
                   String[] dateArray = currentDate.split("-");
                   int nd = Integer.parseInt(dateArray[0]);
                   int yf = Integer.parseInt(dateArray[1]);
                   EntityWrapper<GrglYgxzgl> wrapper1 = new EntityWrapper<GrglYgxzgl>();
                   wrapper1.eq("ND", nd);
                   wrapper1.eq("YF", yf);
                   wrapper1.eq("YGID",scglBgrwfp.getYgid());
                   GrglYgxzgl grglYgxzgl = grglYgxzglService.selectOne(wrapper1);
                   float oldCbjee = 0;
                   if (grglYgxzgl.getCbje()!=null&&!grglYgxzgl.getCbje().equals("")){
                           oldCbjee = Float.parseFloat(grglYgxzgl.getCbje());
                   }
                   oldCbjee = oldCbjee + cbjee;
                   grglYgxzgl.setCbje(oldCbjee+"");

                   //注意判断null和""的情况
                   //合计
                   float hj = 0;
                   //日工工资
                   float rggz = 0;
                   float cqgz = 0;
                   float cqgz2 = 0;
                   float zcqgz = 0;
                   float zwgz = 0;
                   float dx = 0;
                   float fb = 0;
                   float jtf = 0;
                   float bt = 0;
                   float bx = 0;
                   float cq = 0;
                   float sx = 0;
                   float gs = 0;
                   float cbje = 0;
                   float jl = 0;
                   float kk = 0;
                   float zybf = 0;
                   if (grglYgxzgl.getCqgz()!=null&&!grglYgxzgl.getCqgz().equals("")){
                       cqgz = Float.parseFloat(grglYgxzgl.getCqgz());
                   }
                   if (grglYgxzgl.getCqgz2()!=null&&!grglYgxzgl.getCqgz2().equals("")){
                       cqgz2 = Float.parseFloat(grglYgxzgl.getCqgz2());
                   }
                   if (grglYgxzgl.getZwgz()!=null&&!grglYgxzgl.getZwgz().equals("")){
                       zwgz = Float.parseFloat(grglYgxzgl.getZwgz());
                   }
                   if (grglYgxzgl.getDx()!=null&&!grglYgxzgl.getDx().equals("")){
                       dx = Float.parseFloat(grglYgxzgl.getDx());
                   }
                   if (grglYgxzgl.getFb()!=null&&!grglYgxzgl.getFb().equals("")){
                       fb = Float.parseFloat(grglYgxzgl.getFb());
                   }
                   if (grglYgxzgl.getJtf()!=null&&!grglYgxzgl.getJtf().equals("")){
                       jtf = Float.parseFloat(grglYgxzgl.getJtf());
                   }
                   if (grglYgxzgl.getBt()!=null&&!grglYgxzgl.getBt().equals("")){
                       bt = Float.parseFloat(grglYgxzgl.getBt());
                   }
                   if (grglYgxzgl.getBx()!=null&&!grglYgxzgl.getBx().equals("")){
                       bx = Float.parseFloat(grglYgxzgl.getBx());
                   }
                   if (grglYgxzgl.getCq()!=null&&!grglYgxzgl.getCq().equals("")){
                       cq = Float.parseFloat(grglYgxzgl.getCq());
                   }
                   if (grglYgxzgl.getSx()!=null&&!grglYgxzgl.getSx().equals("")){
                       sx = Float.parseFloat(grglYgxzgl.getSx());
                   }
                   if (grglYgxzgl.getGs()!=null&&!grglYgxzgl.getGs().equals("")){
                       gs = Float.parseFloat(grglYgxzgl.getGs());
                   }
                   if (grglYgxzgl.getCbje()!=null&&!grglYgxzgl.getCbje().equals("")){
                       cbje = Float.parseFloat(grglYgxzgl.getCbje());
                   }
                   if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
                       jl = Float.parseFloat(grglYgxzgl.getJl());
                   }
                   if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
                       kk = Float.parseFloat(grglYgxzgl.getKk());
                   }
                   if (grglYgxzgl.getZybf()!=null&&!grglYgxzgl.getZybf().equals("")){
                       zybf = Float.parseFloat(grglYgxzgl.getZybf());
                   }

                   zcqgz = cqgz + cqgz2;
                   rggz = gs * sx + zybf;
                   hj = zwgz + dx + fb + jtf + bt - bx + cq + rggz + cbje + jl - kk + zcqgz;

                   grglYgxzgl.setZcqgz(zcqgz+"");
                   grglYgxzgl.setRggz(rggz+"");
                   grglYgxzgl.setHj(hj+"");

                   //更新
                   grglYgxzglService.updateById(grglYgxzgl);


               }
           }

        }
    }

    /**
     * Dscription: 是否大于剩余数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/6 11:20
     */
    @RequestMapping(value = "sfdysysl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int sfdysysl(String bgrwid, String ljgybzid, String sjwcl, HttpServletRequest request, HttpServletResponse response, Model model){
        int flag = 0;
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl();
        int sjwcli = 0;
        if (sjwcl!=null&&!sjwcl.equals("")){
            sjwcli = Integer.parseInt(sjwcl);
        }
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(bgrwid);
        int oldSjwcl = 0;
        if (jyglBgjy.getSjwcl()!=null&&!jyglBgjy.getSjwcl().equals("")){
            oldSjwcl = Integer.parseInt(jyglBgjy.getSjwcl());
        }

        if (sjwcli>(sysl+oldSjwcl)){
            flag = 1;
        }
        return flag;
    }

    /**
     * Dscription: 导出包工检验单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/8 12:49
     */
    @RequestMapping(value = "exportBgjyd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportBgjyd(String xm, String rq, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<BgjyDTO> bgjyDTOS = jyglBgjyService.exportBgjyd(xm, rq);

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("包工检验单");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3700);
        sheet1.setColumnWidth(1, 2000);
        sheet1.setColumnWidth(2, 4700);
        sheet1.setColumnWidth(3, 3700);
        sheet1.setColumnWidth(4, 3700);
        sheet1.setColumnWidth(5, 3700);
        sheet1.setColumnWidth(6, 3700);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //表头
        Row row0 = sheet1.createRow(0);
        row0.setHeightInPoints(35);
        Cell cell00 = row0.createCell(0);
        Cell cell01 = row0.createCell(1);
        Cell cell02 = row0.createCell(2);
        Cell cell03 = row0.createCell(3);
        Cell cell04 = row0.createCell(4);
        Cell cell05 = row0.createCell(5);
        Cell cell06 = row0.createCell(6);
        Cell cell07 = row0.createCell(7);
        Cell cell08 = row0.createCell(8);
        Cell cell09 = row0.createCell(9);
        cell00.setCellValue("日期");
        cell01.setCellValue("姓名");
        cell02.setCellValue("计划编号");
        cell03.setCellValue("零部件名称");
        cell04.setCellValue("设备名称");
        cell05.setCellValue("工艺大类名称");
        cell06.setCellValue("工艺小类名称");
        cell07.setCellValue("应完成量");
        cell08.setCellValue("实完成量");
        cell09.setCellValue("报废量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);
        cell06.setCellStyle(style);
        cell07.setCellStyle(style);
        cell08.setCellStyle(style);
        cell09.setCellStyle(style);

        if (bgjyDTOS!=null){
            for (int i = 0; i < bgjyDTOS.size(); i++) {
                BgjyDTO c = bgjyDTOS.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(35);
                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);
                Cell cell4 = row.createCell(4);
                Cell cell5 = row.createCell(5);
                Cell cell6 = row.createCell(6);
                Cell cell7 = row.createCell(7);
                Cell cell8 = row.createCell(8);
                Cell cell9 = row.createCell(9);
                //给单元格设值
                cell0.setCellValue(c.getRq());
                cell1.setCellValue(c.getXm());
                cell2.setCellValue(c.getJhbh());
                cell3.setCellValue(c.getLjmc());
                cell4.setCellValue(c.getSbmc());
                cell5.setCellValue(c.getGydlmc());
                cell6.setCellValue(c.getGyxlmc());
                cell7.setCellValue(c.getYwcl());
                cell8.setCellValue("");
                cell9.setCellValue("");
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
                cell6.setCellStyle(style);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);
                cell9.setCellStyle(style);

            }
        }
        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\检验\\"+rq+"-"+xm+"包工检验单.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * Dscription: 转到注释页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 11:23
     */
    @RequestMapping(value = "zs", method={RequestMethod.GET, RequestMethod.POST})
    public String zs(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(id);
        model.addAttribute("jyglBgjy", jyglBgjy);
        return display("zs");
    }

    /**
     * Dscription: 保存注释
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 11:33
     */
    @RequestMapping(value = "saveZs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZs(String id, String zs, HttpServletRequest request, HttpServletResponse response, Model model){
        JyglBgjy jyglBgjy = jyglBgjyService.selectById(id);
        jyglBgjy.setZs(zs);
        jyglBgjyService.updateById(jyglBgjy);
    }

}
