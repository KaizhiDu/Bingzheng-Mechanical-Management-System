package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBzj;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.service.ICkglBzjService;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.dto.BzjBzb;
import cn.jeeweb.modules.scjhgl.dto.CpBzb;
import cn.jeeweb.modules.scjhgl.entity.*;
import cn.jeeweb.modules.scjhgl.service.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @Description:    生产计划管理-合同管理
* @Author:         杜凯之
* @CreateDate:     2018/9/12 14:22
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/htgl")
@RequiresPathPermission("scjhgl:htgl")
public class ScjhglHtglController extends BaseCRUDController<ScjhglHtgl, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**复制计数Service*/
    @Autowired
    private IScjhglFzjsService scjhglFzjsService;

    /**部件组成Service*/
    @Autowired
    private IScjhglBjzcService scjhglBjzcService;

    /**标准件管理Service*/
    @Autowired
    private IScjhglBzjglService scjhglBzjglService;

    /**仓库管理 - 标准件*/
    @Autowired
    private ICkglBzjService ckglBzjService;

    /**仓库管理 - 成品*/
    @Autowired
    private ICkglCpService ckglCpService;

    /**仓库管理 - 分类大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**
    * @Description:    展示所有合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:42
    * @Version:        1.0
    */
    @RequestMapping(value = "createHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String creatHt(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }

    /**
    * @Description:    添加合同
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:43
    * @Version:        1.0
    */
    @RequestMapping(value = "saveHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveHt(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglHtgl scjhglHtgl){

        //先得到原始数量
        String jhid = scjhglHtgl.getId();
        String oldsl = scjhglHtglService.selectById(jhid).getSl();
        String xsl = scjhglHtgl.getSl();
        int oldsli = 0;
        int xsli = 0;
        if (oldsl!=null&&!oldsl.equals("")){
            oldsli = Integer.parseInt(oldsl);
        }
        if (xsl!=null&&!xsl.equals("")){
            xsli = Integer.parseInt(xsl);
        }
        //这里的slf就是新增的或者新减的数量
        int sli = xsli - oldsli;
        if (sli!=0){
            //要把下属零件的数量加上
            List<ScjhglLjgl> ljByjhid = scjhglLjglService.getLjByjhid(jhid);
            for (ScjhglLjgl s : ljByjhid) {
                int sl = 0;
                int sysl = 0;
                int wrksl = 0;
                if (!s.getSl().equals("")&&s.getSl()!=null){
                    sl = Integer.parseInt(s.getSl());
                }
                if (!s.getSysl().equals("")&&s.getSysl()!=null){
                    sysl = Integer.parseInt(s.getSysl());
                }
                if (!s.getWrksl().equals("")&&s.getWrksl()!=null){
                    wrksl = Integer.parseInt(s.getWrksl());
                }
                sl = sl + sli;
                sysl = sysl + sli;
                wrksl = wrksl+ sli;
                s.setSl(sl+"");
                s.setWrksl(wrksl+"");
                s.setSysl(sysl+"");
                scjhglLjglService.updateById(s);
            }

            //要把下属工艺的数量加上
            List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(jhid);
            for (ScglLjgybz s : ljgybzByJhid) {
                int sl = s.getSl() + sli;
                int wrksl = s.getWrksl() + sli;
                int sysl = s.getSysl() + sli;
                s.setSysl(sysl);
                s.setWrksl(wrksl);
                s.setSl(sl);
                scglLjgybzService.updateById(s);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);

        if (scjhglHtgl.getId().equals("")){
            scjhglHtgl.setId(null);
        }
        if (scjhglHtgl.getId()==null){
            scjhglHtgl.setSfwc("0");
            scjhglHtgl.setRq(currentDate);
            scjhglHtglService.insert(scjhglHtgl);
        }
        else{
            scjhglHtglService.updateById(scjhglHtgl);
        }
    }

    /**
     * Dscription: 更新
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/20 13:10
     */

    /**
    * @Description:    更新合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:04
    * @Version:        1.0
    */
    @RequestMapping(value = "updateHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateHt(String id ,HttpServletResponse response, HttpServletRequest request, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl", scjhglHtgl);
        return display("update");
    }

    /**
    * @Description:    删除
    * @Author:         杜凯之
    * @CreateDate:     2018/9/13 14:03
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteHt(String id , HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
            //计划ID
            String jhid = id;
            //首先要判断该计划ID下的所有工艺是否还有剩余
            Boolean flag = false;
            List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(jhid);
            if (ljgybzByJhid!=null){
                for (ScglLjgybz s: ljgybzByJhid) {
                    int sysl = s.getSysl();
                    if (sysl>0){
                        flag = true;
                        break;
                    }
                }
            }
            //如果flag为假，就可以删除
            if (!flag){
                //先删除零部件工艺编制信息
                if (ljgybzByJhid!=null){
                    for (ScglLjgybz s: ljgybzByJhid) {
                        scglLjgybzService.deleteById(s.getId());
                    }
                }

                //然后删除工艺大类信息
                List<ScglGydlbz> gydlbzByjhid = scglGydlbzService.getGydlbzByjhid(jhid);
                if (gydlbzByjhid!=null){
                    for (ScglGydlbz s: gydlbzByjhid) {
                        scglGydlbzService.deleteById(s.getId());
                    }
                }

                //再删除零部件信息
                EntityWrapper<ScjhglLjgl> wrapper = new EntityWrapper<ScjhglLjgl>();
                wrapper.eq("HTID",jhid);
                List<ScjhglLjgl> scjhglLjgls = scjhglLjglService.selectList(wrapper);
                if (scjhglLjgls!=null){
                    scjhglLjglService.delete(wrapper);
                }

                //最后删除计划信息
                scjhglHtglService.deleteById(jhid);
                ajaxJson.setMsg("删除成功");
            }
            else{
                ajaxJson.setMsg("该计划未完成无法删除");
            }
        return ajaxJson;
    }

    /**
     * Dscription: 转到复制计划页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:49
     */
    @RequestMapping(value = "copyHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String copyHt(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl",scjhglHtgl);
        return display("copy");
    }

    /**
     * Dscription: 创建复制内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:55
     */
    @RequestMapping(value = "saveCopy",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveCopy(ScjhglHtgl scjhglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){

        String xjhsl = scjhglHtgl.getSl();
        int xjhsli = 0;
        if (xjhsl!=null&&!xjhsl.equals("")){
            xjhsli = Integer.parseInt(xjhsl);
        }
        //得到原计划ID
        String yjhid = scjhglHtgl.getId();
        //先找到原计划的计划名称
        String jhbh = scjhglHtglService.selectById(yjhid).getHtbh();
        //找复制计数里面有没有该计划名称的记录
        EntityWrapper<ScjhglFzjs> wrapper = new EntityWrapper<ScjhglFzjs>();
        wrapper.eq("JHBH", jhbh);
        //这个count就是记录本次复制的计数器
        int count = 0;
        int c = scjhglFzjsService.selectCount(wrapper);
        //得到count并且加一
        if (c>0){
            ScjhglFzjs scjhglFzjs = scjhglFzjsService.selectOne(wrapper);
            count = scjhglFzjs.getCount()+1;
            scjhglFzjs.setCount(count);
            scjhglFzjsService.updateById(scjhglFzjs);
        }
        //否则新建一条记录，count设置为1
        else{
            count = 1;
            ScjhglFzjs scjhglFzjs = new ScjhglFzjs();
            scjhglFzjs.setCount(count);
            scjhglFzjs.setJhbh(jhbh);
            scjhglFzjsService.insert(scjhglFzjs);
        }
        //现在需要把得到的count加到所有需要添加的ID后
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        //先创建新的计划数据
        ScjhglHtgl newScjhglHtgl = new ScjhglHtgl();
        newScjhglHtgl.setId(scjhglHtgl.getId()+"-"+count);
        newScjhglHtgl.setHtbh(scjhglHtgl.getHtbh());
        newScjhglHtgl.setMs(scjhglHtgl.getMs());
        newScjhglHtgl.setSl(scjhglHtgl.getSl());
        newScjhglHtgl.setRq(currentDate);
        newScjhglHtgl.setSfwc("0");
        scjhglHtglService.insert(newScjhglHtgl);

        //然后复制零部件工艺编制的数据
        List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(yjhid);
        for (ScglLjgybz scglLjgybz : ljgybzByJhid) {
            ScglLjgybz s = new ScglLjgybz();
           String xid = scglLjgybz.getId()+"-"+count;
           String xGydlbzid = scglLjgybz.getGydlbzid()+"-"+count;
           s.setId(xid);
           s.setGydlbzid(xGydlbzid);
           s.setGyxlid(scglLjgybz.getGyxlid());
           s.setGyxlmc(scglLjgybz.getGyxlmc());
           s.setMs(scglLjgybz.getMs());
           s.setPx(scglLjgybz.getPx());
           s.setSl(scglLjgybz.getSl());
           s.setWrksl(scglLjgybz.getSl());
           s.setSysl(scglLjgybz.getSl());
           s.setJhscsl(0);
           s.setScsfxs("1");
           scglLjgybzService.insert(s);
        }

        //然后复制工艺大类编制的数据
        List<ScglGydlbz> gydlbzByjhid = scglGydlbzService.getGydlbzByjhid(yjhid);
        for (ScglGydlbz scglGydlbz : gydlbzByjhid) {
            ScglGydlbz s = new ScglGydlbz();
            String xid = scglGydlbz.getId()+"-"+count;
            String xLjid = scglGydlbz.getLjid()+"-"+count;
            s.setId(xid);
            s.setLjid(xLjid);
            s.setGydlid(scglGydlbz.getGydlid());
            s.setGydlmc(scglGydlbz.getGydlmc());
            s.setPx(scglGydlbz.getPx());
            scglGydlbzService.insert(s);
        }

        //再复制零部件的数据
        List<ScjhglLjgl> ljByjhid = scjhglLjglService.getLjByjhid(yjhid);
        for (ScjhglLjgl scjhglLjgl : ljByjhid) {
            //如果是部件的话，需要复制部件组成里面的信息
            if (scjhglLjgl.getSfsbj().equals("1")){
                String ybjid = scjhglLjgl.getId();
                EntityWrapper<ScjhglBjzc> wrapper1 = new EntityWrapper<ScjhglBjzc>();
                wrapper1.eq("BJID", ybjid);
                List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper1);
                for (ScjhglBjzc scjhglBjzc : scjhglBjzcs) {
                    ScjhglBjzc s = new ScjhglBjzc();
                    String xxid = scjhglBjzc.getId()+"-"+count;
                    String xxbjid = scjhglBjzc.getBjid()+"-"+count;
                    String xxljid = scjhglBjzc.getLjid()+"-"+count;
                    s.setId(xxid);
                    s.setBjid(xxbjid);
                    s.setLjid(xxljid);
                    scjhglBjzcService.insert(s);
                }
            }
            ScjhglLjgl scjhglLjgl1 = new ScjhglLjgl();
            String xid = scjhglLjgl.getId()+"-"+count;
            String xjhid = scjhglLjgl.getHtid()+"-"+count;
            scjhglLjgl1.setId(xid);
            scjhglLjgl1.setHtid(xjhid);
            scjhglLjgl1.setLjth(scjhglLjgl.getLjth());
            scjhglLjgl1.setLjmc(scjhglLjgl.getLjmc());
            scjhglLjgl1.setDyl(scjhglLjgl.getDyl());
            scjhglLjgl1.setSfsbj(scjhglLjgl.getSfsbj());
            scjhglLjgl1.setBjzc(scjhglLjgl.getBjzc());
            //这里的为完成量和数量需要用单用量x新计划的数量
            int xljdyl = 0;
            if (scjhglLjgl.getDyl()!=null&&!scjhglLjgl.getDyl().equals("")){
                xljdyl = Integer.parseInt(scjhglLjgl.getDyl());
            }
            int xsl = xjhsli * xljdyl;
            scjhglLjgl1.setSl(xsl+"");
            scjhglLjgl1.setWrksl(xsl+"");
            scjhglLjgl1.setSysl(scjhglLjgl.getSysl());
            scjhglLjgl1.setSfwwcrk("0");
            scjhglLjglService.insert(scjhglLjgl1);
            //现在要更新所有零部件工艺编制下下面的数量，未入库数量，和剩余数量
            List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(xid);
            for (ScglLjgybz scglLjgybz : ljgybzByLjid) {
                scglLjgybz.setWrksl(xsl);
                scglLjgybz.setSl(xsl);
                scglLjgybz.setSysl(xsl);
                scglLjgybzService.updateById(scglLjgybz);
            }

        }

        //最后复制标准件信息
        EntityWrapper<ScjhglBzjgl> wrapper1 = new EntityWrapper<ScjhglBzjgl>();
        wrapper1.eq("HTID", yjhid);
        List<ScjhglBzjgl> scjhglBzjgls = scjhglBzjglService.selectList(wrapper1);
        for (ScjhglBzjgl s : scjhglBzjgls) {
            String xid = s.getId()+"-"+count;
            String xhtid = s.getHtid()+"-"+count;
            ScjhglBzjgl ss = new ScjhglBzjgl();
            ss.setId(xid);
            ss.setHtid(xhtid);
            ss.setFldl(s.getFldl());
            ss.setFlxl(s.getFlxl());
            ss.setDw(s.getDw());
            ss.setGg(s.getGg());
            ss.setDyl(s.getDyl());
            int dyl = Integer.parseInt(s.getDyl());
            int jhsl = Integer.parseInt(scjhglHtglService.selectById(yjhid+"-"+count).getSl());
            int sl = dyl * jhsl;
            ss.setSl(sl+"");
            ss.setRksl(s.getRksl());
            scjhglBzjglService.insert(ss);
        }

    }

    @RequestMapping(value = "yckbz",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void yckbz(String id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String htbh = scjhglHtglService.selectById(id).getHtbh();

        //先通过计划ID得到，该计划下所有标准件信息
        EntityWrapper<ScjhglBzjgl> wrapper01 = new EntityWrapper<ScjhglBzjgl>();
        wrapper01.eq("HTID", id);
        List<ScjhglBzjgl> scjhglBzjgls = scjhglBzjglService.selectList(wrapper01);

        //得到标准件仓库里面，所有标准件信息
        EntityWrapper<CkglBzj> wrapper001 = new EntityWrapper<CkglBzj>();
        List<CkglBzj> ckglBzjs = ckglBzjService.selectList(wrapper001);

        //再通过计划ID得到，该计划下所有零部件信息
        EntityWrapper<ScjhglLjgl> wrapper02 = new EntityWrapper<ScjhglLjgl>();
        wrapper02.eq("HTID", id);
        List<ScjhglLjgl> scjhglLjgls = scjhglLjglService.selectList(wrapper02);

        //得到成品仓库里面，所有成品信息
        EntityWrapper<CkglCp> wrapper002 = new EntityWrapper<CkglCp>();
        List<CkglCp> ckglCps = ckglCpService.selectList(wrapper002);

        //标准件比照信息
        List<BzjBzb> bzjBzbList = new ArrayList<BzjBzb>();

        //成品比照信息
        List<CpBzb> cpBzbList = new ArrayList<CpBzb>();

        for (ScjhglBzjgl s : scjhglBzjgls) {
            String dlmc = ckglDlService.selectById(s.getFldl()).getDlmc();
            s.setFldl(dlmc);
            for (CkglBzj c : ckglBzjs) {
               if (s.getFldl().equals(c.getFldl())){
                   if (s.getFlxl().equals(c.getFlxl())){
                       if (s.getGg().equals(c.getGg())){
                           //放入bzjbzb
                           BzjBzb b = new BzjBzb();
                           b.setFldl(s.getFldl());
                           b.setFlxl(s.getFlxl());
                           b.setGg(s.getGg());
                           b.setJhxql(s.getSl());
                           b.setKc(c.getKc());
                           float sl = 0;
                           float kc = 0;
                           if (s.getSl()!=null&&!s.getSl().equals("")){
                               sl = Float.parseFloat(s.getSl());
                           }
                           if (c.getKc()!=null&&!c.getKc().equals("")){
                               kc = Float.parseFloat(c.getKc());
                           }
                           float yjybsl = sl - kc;
                           if (yjybsl<0){
                               yjybsl = 0;
                           }
                           b.setYjybsl(yjybsl+"");
                           bzjBzbList.add(b);
                       }
                   }
               }
            }
        }

        for (ScjhglLjgl s : scjhglLjgls) {
            for (CkglCp c : ckglCps) {
                if (s.getLjth().equals(c.getLbjth())){
                    //插入cpbzb
                    CpBzb b = new CpBzb();
                    b.setTh(s.getLjth());
                    b.setMc(s.getLjmc());
                    b.setJhxql(s.getSl());
                    b.setKc(c.getRksl());
                    float sl = 0;
                    float kc = 0;
                    if (s.getSl()!=null&&!s.getSl().equals("")){
                        sl = Float.parseFloat(s.getSl());
                    }
                    if (c.getRksl()!=null&&!c.getRksl().equals("")){
                        kc = Float.parseFloat(c.getRksl());
                    }
                    float yjybsl = sl - kc;
                    if (yjybsl<0){
                        yjybsl = 0;
                    }
                    b.setYjybsl(yjybsl+"");
                    cpBzbList.add(b);
                }
            }
        }

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();

        //新建工作表
        Sheet sheet1 = wb.createSheet("零部件比照表");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3700);
        sheet1.setColumnWidth(1, 3700);
        sheet1.setColumnWidth(2, 3700);
        sheet1.setColumnWidth(3, 3700);
        sheet1.setColumnWidth(4, 3700);
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
        cell00.setCellValue("图号");
        cell01.setCellValue("名称");
        cell02.setCellValue("计划需求量");
        cell03.setCellValue("库存");
        cell04.setCellValue("预警补充数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);

        if (cpBzbList!=null){
            for (int i=0;i<cpBzbList.size();i++){
                CpBzb c = cpBzbList.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(20);

                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);
                Cell cell4 = row.createCell(4);

                //给单元格设值
                cell0.setCellValue(c.getTh());
                cell1.setCellValue(c.getMc());
                cell2.setCellValue(c.getJhxql());
                cell3.setCellValue(c.getKc());
                cell4.setCellValue(c.getYjybsl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
            }
        }


        //新建工作表
        Sheet sheet2 = wb.createSheet("标准件比照表");
        //设置单元格宽度
        sheet2.setColumnWidth(0, 3700);
        sheet2.setColumnWidth(1, 3700);
        sheet2.setColumnWidth(2, 3700);
        sheet2.setColumnWidth(3, 3700);
        sheet2.setColumnWidth(4, 3700);
        sheet2.setColumnWidth(5, 3700);
        //设置边框
        CellStyle style2 = wb.createCellStyle();
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //表头
        Row row02 = sheet2.createRow(0);
        row02.setHeightInPoints(20);
        Cell cell002 = row02.createCell(0);
        Cell cell012 = row02.createCell(1);
        Cell cell022 = row02.createCell(2);
        Cell cell032 = row02.createCell(3);
        Cell cell042 = row02.createCell(4);
        Cell cell052 = row02.createCell(5);
        cell002.setCellValue("分类大类");
        cell012.setCellValue("分类小类");
        cell022.setCellValue("规格");
        cell032.setCellValue("计划需求量");
        cell042.setCellValue("库存");
        cell052.setCellValue("预警补充数量");
        cell002.setCellStyle(style);
        cell012.setCellStyle(style);
        cell022.setCellStyle(style);
        cell032.setCellStyle(style);
        cell042.setCellStyle(style);
        cell052.setCellStyle(style);

        if (bzjBzbList!=null){
            for (int i=0;i<bzjBzbList.size();i++){
                BzjBzb c = bzjBzbList.get(i);
                //创建一行
                Row row = sheet2.createRow(i+1);
                row.setHeightInPoints(20);

                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);
                Cell cell4 = row.createCell(4);
                Cell cell5 = row.createCell(5);

                //给单元格设值
                cell0.setCellValue(c.getFldl());
                cell1.setCellValue(c.getFlxl());
                cell2.setCellValue(c.getGg());
                cell3.setCellValue(c.getJhxql());
                cell4.setCellValue(c.getKc());
                cell5.setCellValue(c.getYjybsl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
            }
        }

//创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\生产\\"+htbh+" 仓库对照表.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();

    }
}
