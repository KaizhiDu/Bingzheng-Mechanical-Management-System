package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.service.ISbglSbflglService;
import cn.jeeweb.modules.sbgl.service.ISbglService;
import cn.jeeweb.modules.scgl.dto.*;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/rcrwfp")
@RequiresPathPermission("scgl:rcrwfp")
public class ScglRcrwfpController extends BaseCRUDController<ScglRcrwfp, String> {

    /**生产管理-日常任务分配Service*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    @Autowired
    /**生产管理-日工工时Service*/
    private IScglRggsService scglRggsService;

    /**设备分类管理Service*/
    @Autowired
    private ISbglSbflglService sbglSbflglService;

    /**设备管理Service*/
    @Autowired
    private ISbglService sbglService;

    /**生产管理-日工设备Service*/
    @Autowired
    private IScglRgsbService scglRgsbService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**生产管理-日工任务Service*/
    @Autowired
    private IScglRgrwService scglRgrwService;

    @Autowired
    /**生产管理-零件工艺编制Service*/
    private IScglLjgybzService scglLjgybzService;

    /**
     * Dscription: 添加日子和搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 17:14
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        model.addAttribute("currentTime",currentTime);

        //判断表里面有没有该数据
        EntityWrapper<ScglRcrwfp> wrapper = new EntityWrapper<ScglRcrwfp>();
        wrapper.eq("RQ",currentTime);
        List<ScglRcrwfp> scglRcrwfps = scglRcrwfpService.selectList(wrapper);
        //如果表里面没有就插入数据
        if (scglRcrwfps.size()==0){
            List<ScglRcrwfp> list = new ArrayList<ScglRcrwfp>();
            List<YgsjDTO> ygsjList = scglRcrwfpService.getYgsj();
            for (YgsjDTO ygsjDTO: ygsjList) {
                ScglRcrwfp s = new ScglRcrwfp();
                s.setXb(ygsjDTO.getXb());
                s.setRq(currentTime);
                s.setXm(ygsjDTO.getXm());
                s.setZw(ygsjDTO.getZw());
                s.setYgid(ygsjDTO.getYgid());
                list.add(s);
            }
            scglRcrwfpService.insertBatch(list);
        }
    }

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @RequestMapping(value = "ajaxRcrwfpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglRcrwfp> ajaxRcrwfpList(Queryable queryable, ScglRcrwfp scglRcrwfp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScglRcrwfp> pageJson = scglRcrwfpService.ajaxRcrwfpList(queryable,scglRcrwfp);
        return pageJson;
    }

    /**
     * Dscription: 日工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    @RequestMapping(value = "ajaxRcrwfpSbList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<RgsbDTO> ajaxRcrwfpSbList(Queryable queryable, RgsbDTO rgsbDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<RgsbDTO> pageJson = scglRgsbService.ajaxRcrwfpSbList(queryable,rgsbDTO);
        return pageJson;
    }

    /**
     * Dscription: 日工 - 添加的任务展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:30
     */
    @RequestMapping(value = "ajaxRcrwfpRwList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<RgrwDTO> ajaxRcrwfpRwList(Queryable queryable, RgrwDTO rgrwDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<RgrwDTO> pageJson = scglRgrwService.ajaxRcrwfpRwList(queryable,rgrwDTO);
        return pageJson;
    }


    /**
     * Dscription: 跳转到日常任务设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:07
     */
    @RequestMapping(value = "fpsb", method={RequestMethod.GET, RequestMethod.POST})
    public String fpsb(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        return display("fpsb");
    }

    /**
     * Dscription: 跳转到分配工时页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:44
     */
    @RequestMapping(value = "fpgs", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgs(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        EntityWrapper<ScglRggs> wrapper = new EntityWrapper<ScglRggs>();
        wrapper.eq("RCRWFPID", scglRcrwfp.getId());
        ScglRggs scglRggs = scglRggsService.selectOne(wrapper);
        model.addAttribute("rggs", scglRggs);
        if (scglRggs==null){
            model.addAttribute("RggsId", "");
        }
        else{
            model.addAttribute("RggsId", scglRggs.getId());
        }
        return display("fpgs");
    }

    /**
     * Dscription: 保存工时
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 9:27
     */
    @RequestMapping(value = "saveGs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGs(ScglRggs scglRggs, HttpServletRequest request, HttpServletResponse response, Model model){
        if (scglRggs.getGsmc()==null){
            scglRggs.setGsmc("");
        }
        //执行插入操作
        if (scglRggs.getId()==null||scglRggs.getId().equals("")){
            scglRggs.setId(null);
            scglRggsService.insert(scglRggs);
        }
        //执行更新操作
        else{
            scglRggsService.updateById(scglRggs);
        }
    }

    /**
     * Dscription: 转到添加设备页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 14:38
     */
    @RequestMapping(value = "addSb", method={RequestMethod.GET, RequestMethod.POST})
    public String addSb(String rcrwfpid ,HttpServletRequest request, HttpServletResponse response, Model model){
        //设备分类
        EntityWrapper<SbglSbflgl> wrapper = new EntityWrapper();
        wrapper.orderBy("fldm");
        List<SbglSbflgl> sbflglList = sbglSbflglService.selectList(wrapper);
        model.addAttribute("list",sbflglList);
        model.addAttribute("rcrwfpid", rcrwfpid);
        //日常任务分配
        return display("addSb");
    }

    /**
     * Dscription: 保存设备信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 9:55
     */
    @RequestMapping(value = "saveSb", method={RequestMethod.GET, RequestMethod.POST})
    public void saveSb(String ids ,String rcrwfpid, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            EntityWrapper<ScglRgsb> wrapper = new EntityWrapper<ScglRgsb>();
            wrapper.orderBy("PX");
            int index = scglRgsbService.selectList(wrapper).size();
            if (index == 0){
                int px = 1;
                ScglRgsb s = new ScglRgsb();
                s.setPx(px);
                s.setRcrwfpid(rcrwfpid);
                s.setSbid(idsArray[i]);
                scglRgsbService.insert(s);
            }
            else{
                int px = scglRgsbService.selectList(wrapper).get(index-1).getPx()+1;
                ScglRgsb s = new ScglRgsb();
                s.setPx(px);
                s.setRcrwfpid(rcrwfpid);
                s.setSbid(idsArray[i]);
                scglRgsbService.insert(s);
            }

        }
    }

    /**
     * Dscription: 删除设备信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 9:55
     */
    @RequestMapping(value = "deleteSb", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteSb(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglRgsbService.deleteById(idsArray[i]);
        }
    }

    /**
     * Dscription: 删除任务信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 15:20
     */
    @RequestMapping(value = "deleteRw", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteRw(String ids , HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            scglRgrwService.deleteById(idsArray[i]);
        }
    }

    /**
     * Dscription: 跳转到任务分配页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 12:30
     */
    @RequestMapping(value = "fprw", method={RequestMethod.GET, RequestMethod.POST})
    public String fprw(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        String sbid = scglRgsbService.selectById(id).getSbid();
        String sbmc = sbglService.selectById(sbid).getSbmc();
        model.addAttribute("fpsbid" ,id);
        model.addAttribute("sbmc" ,sbmc);
        return display("fprw");
    }

    /**
     * Dscription: 转到添加任务页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 10:13
     */
    @RequestMapping(value = "addRw", method={RequestMethod.GET, RequestMethod.POST})
    public String addRw(String fpsbid ,HttpServletRequest request, HttpServletResponse response, Model model){
         model.addAttribute("fpsbid", fpsbid);
         //得到所有计划信息
         List<SsxDTO> jhglList = scjhglHtglService.getJhList();
         model.addAttribute("jhglList", jhglList);
        return display("addRw");
     }

     /**
      * Dscription: 保存任务信息
      * @author : Kevin Du
      * @version : 1.0
      * @date : 2018/9/27 13:30
      */
    @RequestMapping(value = "saveRw", method={RequestMethod.GET, RequestMethod.POST})
    public void saveRw(String ids ,String fpsbid ,HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            EntityWrapper<ScglRgrw> wrapper = new EntityWrapper<ScglRgrw>();
            wrapper.orderBy("PX");
            int index = scglRgrwService.selectList(wrapper).size();
            if (index == 0){
                int px = 1;
                ScglRgrw s = new ScglRgrw();
                s.setPx(px);
                s.setLjgybzid(idsArray[i]);
                s.setFpsbid(fpsbid);
                scglRgrwService.insert(s);
            }
            else{
                int px = scglRgrwService.selectList(wrapper).get(index-1).getPx()+1;
                ScglRgrw s = new ScglRgrw();
                s.setPx(px);
                s.setLjgybzid(idsArray[i]);
                s.setFpsbid(fpsbid);
                scglRgrwService.insert(s);
            }

        }
    }

    /**
     * Dscription: 转到分配工作量页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 16:04
     */
    @RequestMapping(value = "fpgzl", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgzl(String id, HttpServletRequest request, HttpServletResponse response, Model model){

        ScglRgrw scglRgrw = scglRgrwService.selectById(id);
        String ljgybzid = scglRgrw.getLjgybzid();
        String xygzl = scglRgrw.getYwcl();
        int syslint = scglLjgybzService.selectById(ljgybzid).getSysl();
        String sysl = syslint+"";
        model.addAttribute("rgrwid", id);
        model.addAttribute("ljgybzid" ,ljgybzid);
        model.addAttribute("sysl", sysl);
        model.addAttribute("xygzl", xygzl);
        return display("fpgzl");
    }

    /**
     * Dscription: 保存工作量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 16:05
     */
    @RequestMapping(value = "saveGzl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGzl(String rgrwid, String gzl, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRgrw scglRgrw = new ScglRgrw();
        scglRgrw.setId(rgrwid);
        scglRgrw.setYwcl(gzl);
        scglRgrwService.updateById(scglRgrw);
    }

    /**
     * Dscription: 生成派工单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 11:03
     */
    @RequestMapping(value = "createPgd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void createPgd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //获取派工数据
        List<RgpgdDTO> list = getRgpgxx();

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("日工派工单");
        //设置单元格宽度
        sheet1.setColumnWidth(2, 3000);
        sheet1.setColumnWidth(3, 3000);
        sheet1.setColumnWidth(4, 3000);
        sheet1.setColumnWidth(5, 3000);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //开始搞
        for (int i=0;i<list.size();i++){

        }


        //创建一行
        Row row0 = sheet1.createRow(0);
        row0.setHeightInPoints(30);
        //创建单元格
        Cell cell00 = row0.createCell(0);
        Cell cell01 = row0.createCell(1);
        Cell cell02 = row0.createCell(2);
        Cell cell03 = row0.createCell(3);
        Cell cell04 = row0.createCell(4);
        Cell cell05 = row0.createCell(5);

        //给单元格设值
        cell00.setCellValue("姓名");
        cell01.setCellValue("杜凯之");
        cell02.setCellValue("职位");
        cell03.setCellValue("钳工");
        cell04.setCellValue("日期");
        cell05.setCellValue("2018-9-29");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);

        //第二行
        Row row1 = sheet1.createRow(1);
        row1.setHeightInPoints(30);
        //创建单元格
        Cell cell10 = row1.createCell(0);
        Cell cell11 = row1.createCell(1);
        Cell cell12 = row1.createCell(2);
        Cell cell13 = row1.createCell(3);
        //给单元格设值
        cell10.setCellValue("工时");
        cell11.setCellValue("夜班（12）");
        cell12.setCellValue("加班");
        cell13.setCellValue("2");
        cell10.setCellStyle(style);
        cell11.setCellStyle(style);
        cell12.setCellStyle(style);
        cell13.setCellStyle(style);

        //第三行
        Row row2 = sheet1.createRow(2);
        row2.setHeightInPoints(30);
        //创建单元格
        Cell cell20 = row2.createCell(0);
        Cell cell21 = row2.createCell(1);
        Cell cell22 = row2.createCell(2);
        //给单元格设值
        cell20.setCellValue("内容1");
        cell21.setCellValue("设备1");
        cell22.setCellValue("xx计划-xx零件-xx大类-xx小类-50件");
        cell20.setCellStyle(style);
        cell21.setCellStyle(style);

        //第四行
        Row row3 = sheet1.createRow(3);
        row3.setHeightInPoints(30);
        //创建单元格
        Cell cell30 = row3.createCell(0);
        Cell cell31 = row3.createCell(1);
        Cell cell32 = row3.createCell(2);
        //给单元格设值
        cell30.setCellValue("内容2");
        cell31.setCellValue("设备2");
        cell32.setCellValue("xx计划-xx零件-xx大类-xx小类-150件");
        cell30.setCellStyle(style);
        cell31.setCellStyle(style);

        //第五行
        Row row4 = sheet1.createRow(4);
        row4.setHeightInPoints(30);
        //创建单元格
        Cell cell40 = row4.createCell(0);
        Cell cell41 = row4.createCell(1);
        Cell cell42 = row4.createCell(2);
        //给单元格设值
        cell40.setCellValue("内容3");
        cell41.setCellValue("设备3");
        cell42.setCellValue("xx计划-xx零件-xx大类-xx小类-200件");
        cell40.setCellStyle(style);
        cell41.setCellStyle(style);

        //第六行
        Row row5 = sheet1.createRow(5);
        row5.setHeightInPoints(30);
        //创建单元格
        Cell cell50 = row5.createCell(0);
        Cell cell51 = row5.createCell(1);
        Cell cell52 = row5.createCell(2);
        //给单元格设值
        cell50.setCellValue("内容4");
        cell51.setCellValue("设备4");
        cell52.setCellValue("xx计划-xx零件-xx大类-xx小类-250件");
        cell50.setCellStyle(style);
        cell51.setCellStyle(style);

        //第七行
        Row row6 = sheet1.createRow(6);
        row6.setHeightInPoints(30);
        //创建单元格
        Cell cell60 = row6.createCell(0);
        Cell cell61 = row6.createCell(1);
        Cell cell62 = row6.createCell(2);
        //给单元格设值
        cell60.setCellValue("内容5");
        cell61.setCellValue("设备5");
        cell62.setCellValue("xx计划-xx零件-xx大类-xx小类-300件");
        cell60.setCellStyle(style);
        cell61.setCellStyle(style);

        //合并单元格
        sheet1.addMergedRegion(new CellRangeAddress(1,1,4,5));
        sheet1.addMergedRegion(new CellRangeAddress(2,2,2,5));
        sheet1.addMergedRegion(new CellRangeAddress(3,3,2,5));
        sheet1.addMergedRegion(new CellRangeAddress(4,4,2,5));
        sheet1.addMergedRegion(new CellRangeAddress(5,5,2,5));
        sheet1.addMergedRegion(new CellRangeAddress(6,6,2,5));
        //给合并的单元格加边框
        setRegionBorder(1,new CellRangeAddress(1,1,4,5),sheet1,wb);
        setRegionBorder(1,new CellRangeAddress(2,2,2,5),sheet1,wb);
        setRegionBorder(1,new CellRangeAddress(3,3,2,5),sheet1,wb);
        setRegionBorder(1,new CellRangeAddress(4,4,2,5),sheet1,wb);
        setRegionBorder(1,new CellRangeAddress(5,5,2,5),sheet1,wb);
        setRegionBorder(1,new CellRangeAddress(6,6,2,5),sheet1,wb);
        //给其他单元格加边框

        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\2018-9-29日工派工单.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * @param border 边框宽度
     * @param region 合并单元格区域范围
     * @param sheet
     * @param wb
     */
    public static void setRegionBorder(int border, CellRangeAddress region, Sheet sheet,Workbook wb){
        RegionUtil.setBorderBottom(border,region, sheet, wb);
        RegionUtil.setBorderLeft(border,region, sheet, wb);
        RegionUtil.setBorderRight(border,region, sheet, wb);
        RegionUtil.setBorderTop(border,region, sheet, wb);
    }

    public List<RgpgdDTO> getRgpgxx(){
        List<RgpgdDTO> rgpgdDTOList = scglRcrwfpService.getRgpgd();
        //从数据库里面得到原始数据
        List<RgpgJcxxDTO> rgpgJcxx = scglRcrwfpService.getRgpgJcxx();
        RgpgJcxxDTO r0 = rgpgJcxx.get(0);
        String rw = r0.getSbmc()+"||||"+r0.getJhbh()+"-"+r0.getLjmc()+"-"+r0.getGydlmc()+"-"+r0.getGyxlmc()+"-"+r0.getYwcl()+"件||||||||";
        for (int i=1;i<rgpgJcxx.size();i++){
            RgpgJcxxDTO r1 = rgpgJcxx.get(i);
            RgpgJcxxDTO r2 = rgpgJcxx.get(i-1);
            //id相同，r1继续插入
            if (r1.getId().equals(r2.getId())){
                rw = rw + r1.getSbmc()+"||||"+r1.getJhbh()+"-"+r1.getLjmc()+"-"+r1.getGydlmc()+"-"+r1.getGyxlmc()+"-"+r1.getYwcl()+"件||||||||";
            }
            //id不相同
            else{
                for (RgpgdDTO r : rgpgdDTOList) {
                    if (r.getId().equals(r2.getId())){
                        rw = rw.substring(0,rw.length()-8);
                        r.setNr(rw);
                    }
                }
                //重设rw
                rw = r1.getSbmc()+"||||"+r1.getJhbh()+"-"+r1.getLjmc()+"-"+r1.getGydlmc()+"-"+r1.getGyxlmc()+"-"+r1.getYwcl()+"件||||||||";
            }


        }

        return rgpgdDTOList;
    }

}
