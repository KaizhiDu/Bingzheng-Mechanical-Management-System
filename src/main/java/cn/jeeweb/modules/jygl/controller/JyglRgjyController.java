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
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    /**生产管理-零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

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
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("rq", false);
        wrapper.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
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
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        //零部件工艺编制ID
        String ljgybzid = jyglRgjy.getLjgybzid();
        model.addAttribute("ljgybzid", ljgybzid);
        //报废量
        model.addAttribute("bfl", jyglRgjy.getBfl());
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
    public void saveWcl(String rgrwid, String ljgybzid, String sjwcl, String bfl, HttpServletRequest request, HttpServletResponse response, Model model){


        JyglRgjy preJyglRgjy = jyglRgjyService.selectById(rgrwid);
        String gydlbzid = scglLjgybzService.selectById(ljgybzid).getGydlbzid();
        String ljid = scglGydlbzService.selectById(gydlbzid).getLjid();


        //首先要判断之前是否已经设置过了实际完成量
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
            preJyglRgjy.setSjwcl("");
            jyglRgjyService.updateById(preJyglRgjy);

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
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(rgrwid);
        String ss = jyglRgjy.getSjwcl();
        int sss = 0;
        if (ss!=null&&!ss.equals("")){
            sss = Integer.parseInt(ss);
        }

        //第一步是零部件工艺编制下面的计划生产数量减去应完成量
        String ywcl = jyglRgjy.getYwcl();
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
        jyglRgjy.setSjwcl(sjwcl);
        jyglRgjyService.updateById(jyglRgjy);

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
        String preBfl = preJyglRgjy.getBfl();
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
            int zsysl2 = s.getSysl();
            int zwrksl2 = s.getWrksl();
            zsl2 = zsl2 - realbfl;
            zsysl2 = zsysl2 - realbfl;
            zwrksl2 = zwrksl2 - realbfl;
            s.setSl(zsl2);
            s.setSysl(zsysl2);
            s.setWrksl(zwrksl2);
            scglLjgybzService.updateById(s);
        }
        JyglRgjy finalJyglRgjy = jyglRgjyService.selectById(rgrwid);
        finalJyglRgjy.setBfl(bfl);
        jyglRgjyService.updateById(finalJyglRgjy);
    }

    /**
     * Dscription: 是否大于剩余数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/17 21:00
     */
    @RequestMapping(value = "sfdysysl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int sfdysysl(String rgrwid, String ljgybzid, String sjwcl, HttpServletRequest request, HttpServletResponse response, Model model){
        int flag = 0;
        ScglLjgybz scglLjgybz = scglLjgybzService.selectById(ljgybzid);
        int sysl = scglLjgybz.getSysl();
        int sjwcli = 0;
        if (sjwcl!=null&&!sjwcl.equals("")){
            sjwcli = Integer.parseInt(sjwcl);
        }
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(rgrwid);
        int oldSjwcl = 0;
        if (jyglRgjy.getSjwcl()!=null&&!jyglRgjy.getSjwcl().equals("")){
            oldSjwcl = Integer.parseInt(jyglRgjy.getSjwcl());
        }

        if (sjwcli>(sysl+oldSjwcl)){
            flag = 1;
        }
        return flag;
    }
/**
 * Dscription: 导出日工检验单
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/8 10:44
 */
    @RequestMapping(value = "exportRgjyd", method={RequestMethod.GET, RequestMethod.POST})
    public void exportRgjyd(String xm, String rq, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //获取检验信息
        List<RgjyDTO> rgjyDTOS = jyglRgjyService.exportJypgd(xm, rq);

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("日工检验单");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3000);
        sheet1.setColumnWidth(1, 2300);
        sheet1.setColumnWidth(2, 4500);
        sheet1.setColumnWidth(3, 4500);
        sheet1.setColumnWidth(4, 3500);
        sheet1.setColumnWidth(5, 4000);
        sheet1.setColumnWidth(6, 3500);
        sheet1.setColumnWidth(7, 2000);
        sheet1.setColumnWidth(8, 2000);
        sheet1.setColumnWidth(9, 2000);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //表头
        Row row0 = sheet1.createRow(0);
        row0.setHeightInPoints(20);
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
        // cell02.setCellValue("计划编号");
        cell02.setCellValue("零部件图号");
        cell03.setCellValue("零部件名称");
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

        if (rgjyDTOS!=null){
            for (int i = 0; i < rgjyDTOS.size(); i++) {
                RgjyDTO c = rgjyDTOS.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(20);
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
                cell2.setCellValue(c.getLjth());
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
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\检验\\"+rq+"日工检验单.xlsx");
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
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(id);
        model.addAttribute("jyglRgjy", jyglRgjy);
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
        JyglRgjy jyglRgjy = jyglRgjyService.selectById(id);
        jyglRgjy.setZs(zs);
        jyglRgjyService.updateById(jyglRgjy);
    }
}
