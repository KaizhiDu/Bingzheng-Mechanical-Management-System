package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.dto.CkglBhglDTO;
import cn.jeeweb.modules.ckgl.entity.*;
import cn.jeeweb.modules.ckgl.service.*;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 仓库管理 - 补货管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/16 9:37
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/bhgl")
@RequiresPathPermission("ckgl:bhgl")
public class CkglBhglController extends BaseCRUDController<CkglBhgl, String> {

    /**仓库管理 - 补货管理Service*/
    @Autowired
    private ICkglBhglService ckglBhglService;

    /**仓库管理 - 进货商*/
    @Autowired
    private ICkglJhsService ckglJhsService;
    @Autowired
    private ICkglDzyhpService ckglDzyhpService;
    @Autowired
    private ICkglDzyhpMxService ckglDzyhpMxService;
    @Autowired
    private ICkglBzjService ckglBzjService;
    @Autowired
    private ICkglBzjMxSevice ckglBzjMxService;
    @Autowired
    private ICkglYclService ckglYclService;
    @Autowired
    private ICkglYclMxService ckglYclMxService;
    @Autowired
    private ICkglRjService ckglRjService;
    @Autowired
    private ICkglRjMxService ckglRjMxService;
    @Autowired
    private ICkglBgypService ckglBgypService;
    @Autowired
    private ICkglBgypMxService ckglBgypMxService;

    /**
     * Dscription: 展示所有需要补货的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @RequestMapping(value = "ajaxBhglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<CkglBhglDTO> ajaxBhglList(Queryable queryable, CkglBhglDTO ckglBhglDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<CkglBhglDTO> pageJson = ckglBhglService.ajaxBhglList(queryable,ckglBhglDTO);
        return pageJson;
    }

    /**
     * Dscription: 显示入库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 7/7/2019 11:13 AM
     */
    @RequestMapping(value = "rk", method={RequestMethod.GET, RequestMethod.POST})
    public String rk(String fldl, String flxl, String gg, String id, String ck, HttpServletRequest request, HttpServletResponse response, Model model){
        boolean hasJhs = false;
        if (ck.equals("标准件") || ck.equals("原材料") || ck.equals("刃具")) {
            hasJhs = true;
        }
        EntityWrapper<CkglJhs> wrapper = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper);
        model.addAttribute("ckglJhs", ckglJhs);
        model.addAttribute("title", fldl+" - "+flxl+" - "+gg);
        model.addAttribute("id", id);
        model.addAttribute("ck", ck);
        model.addAttribute("hasJhs", hasJhs);
        return display("rk");
    }

    @RequestMapping(value = "saveRk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveRk(String cg, String rksl, String jhs, String id, String ck, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        switch (ck){
            case "标准件": bzjrk(jhs, id, cg, rksl);
                break;
            case "原材料": yclrk(jhs, id, cg, rksl);
                break;
            case "刃具": rjrk(jhs, id, cg, rksl);
                break;
            case "办公用品":bgyprk(id, cg, rksl);
                break;
            case "低值易耗品":dzyhprk(id, cg, rksl);
                break;
        }
    }

    public void bzjrk(String jhs, String bzjid, String cg, String rksl){
        CkglBzj ckglBzj0 = ckglBzjService.selectById(bzjid);

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String datee = sdf0.format(date0);
        String dateArray[] = datee.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglBzjMx ckglBzjMx = new CkglBzjMx();
        ckglBzjMx.setFldl(ckglBzj0.getFldl());
        ckglBzjMx.setFlxl(ckglBzj0.getFlxl());
        ckglBzjMx.setGg(ckglBzj0.getGg());
        ckglBzjMx.setBzjid(bzjid);
        ckglBzjMx.setMx(mx);
        ckglBzjMx.setSj(date);
        ckglBzjMx.setN(n);
        ckglBzjMx.setY(y);
        ckglBzjMx.setR(r);
        ckglBzjMx.setJhs(jhs);
        ckglBzjMx.setJx("0");
        ckglBzjMxService.insert(ckglBzjMx);
        //更改标准件表
        CkglBzj ckglBzj = ckglBzjService.selectById(bzjid);
        float kc = 0;
        float zjl = 0;
        if (ckglBzj.getKc()!=null&&!ckglBzj.getKc().equals("")){
            kc = Float.parseFloat(ckglBzj.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglBzj.setKc(kc+"");
        ckglBzjService.updateById(ckglBzj);
    }

    public void yclrk(String jhs, String yclid, String cg, String rksl) {
        CkglYcl ckglYcl0 = ckglYclService.selectById(yclid);

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String datee = sdf0.format(date0);
        String dateArray[] = datee.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglYclMx ckglYclMx = new CkglYclMx();
        ckglYclMx.setFldl(ckglYcl0.getFldl());
        ckglYclMx.setFlxl(ckglYcl0.getFlxl());
        ckglYclMx.setGg(ckglYcl0.getGg());
        ckglYclMx.setYclid(yclid);
        ckglYclMx.setMx(mx);
        ckglYclMx.setSj(date);
        ckglYclMx.setN(n);
        ckglYclMx.setY(y);
        ckglYclMx.setR(r);
        ckglYclMx.setJhs(jhs);
        ckglYclMx.setJx("0");
        ckglYclMxService.insert(ckglYclMx);
        //更改标准件表
        CkglYcl ckglYcl = ckglYclService.selectById(yclid);
        float kc = 0;
        float zjl = 0;
        if (ckglYcl.getKc()!=null&&!ckglYcl.getKc().equals("")){
            kc = Float.parseFloat(ckglYcl.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglYcl.setKc(kc+"");
        ckglYclService.updateById(ckglYcl);
    }

    public void rjrk(String jhs, String rjid, String cg, String rksl) {
        CkglRj ckglRj0 = ckglRjService.selectById(rjid);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String datee = sdf0.format(date0);
        String dateArray[] = datee.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglRjMx ckglRjMx = new CkglRjMx();
        ckglRjMx.setFldl(ckglRj0.getFldl());
        ckglRjMx.setFlxl(ckglRj0.getFlxl());
        ckglRjMx.setGg(ckglRj0.getGg());
        ckglRjMx.setRjid(rjid);
        ckglRjMx.setMx(mx);
        ckglRjMx.setSj(date);
        ckglRjMx.setN(n);
        ckglRjMx.setY(y);
        ckglRjMx.setR(r);
        ckglRjMx.setJhs(jhs);
        ckglRjMx.setJx("0");
        ckglRjMxService.insert(ckglRjMx);
        //更改标准件表
        CkglRj ckglRj = ckglRjService.selectById(rjid);
        float kc = 0;
        float zjl = 0;
        if (ckglRj.getKc()!=null&&!ckglRj.getKc().equals("")){
            kc = Float.parseFloat(ckglRj.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglRj.setKc(kc+"");
        ckglRjService.updateById(ckglRj);
    }

    public void bgyprk(String bgypid, String cg, String rksl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglBgypMx ckglBgypMx = new CkglBgypMx();
        ckglBgypMx.setBgypid(bgypid);
        ckglBgypMx.setMx(mx);
        ckglBgypMx.setSj(date);
        ckglBgypMxService.insert(ckglBgypMx);
        //更改标准件表
        CkglBgyp ckglBgyp = ckglBgypService.selectById(bgypid);
        float kc = 0;
        float zjl = 0;
        if (ckglBgyp.getKc()!=null&&!ckglBgyp.getKc().equals("")){
            kc = Float.parseFloat(ckglBgyp.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglBgyp.setKc(kc+"");
        ckglBgypService.updateById(ckglBgyp);
    }

    public void dzyhprk(String dzyhpid, String cg, String rksl){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String mx = "采购员 "+cg+" 于 "+currentDate+" 入库 "+rksl+" 件";
        //插入明细表
        CkglDzyhpMx ckglDzyhpMx = new CkglDzyhpMx();
        ckglDzyhpMx.setDzyhpid(dzyhpid);
        ckglDzyhpMx.setMx(mx);
        ckglDzyhpMx.setSj(date);
        ckglDzyhpMxService.insert(ckglDzyhpMx);
        //更改标准件表
        CkglDzyhp ckglDzyhp = ckglDzyhpService.selectById(dzyhpid);
        float kc = 0;
        float zjl = 0;
        if (ckglDzyhp.getKc()!=null&&!ckglDzyhp.getKc().equals("")){
            kc = Float.parseFloat(ckglDzyhp.getKc());
        }
        if (rksl!=null&&!rksl.equals("")){
            zjl = Float.parseFloat(rksl);
        }
        kc = kc + zjl;
        ckglDzyhp.setKc(kc+"");
        ckglDzyhpService.updateById(ckglDzyhp);
    }

        /**
         * Dscription: 导出补货单
         * @author : Kevin Du
         * @version : 1.0
         * @date : 2018/10/16 10:41
         */
    @RequestMapping(value = "exportBhd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportBhd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //得到数据
        List<CkglBhglDTO> getData = ckglBhglService.bhglList();

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("补货清单");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3700);
//        sheet1.setColumnWidth(2, 3700);
//        sheet1.setColumnWidth(3, 3700);
//        sheet1.setColumnWidth(4, 3700);
//        sheet1.setColumnWidth(5, 3700);
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
        cell00.setCellValue("仓库");
        cell01.setCellValue("大类");
        cell02.setCellValue("小类");
        cell03.setCellValue("规格/名称");
        cell04.setCellValue("单位");
        cell05.setCellValue("库存");
        cell06.setCellValue("预警库存");
        cell07.setCellValue("应补数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);
        cell06.setCellStyle(style);
        cell07.setCellStyle(style);
        //开始搞,得到所有最终内容展示集合
        if (getData != null) {
            for (int i = 0; i < getData.size(); i++) {
                CkglBhglDTO c = getData.get(i);
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

                //给单元格设值
                cell0.setCellValue(c.getCk());
                cell1.setCellValue(c.getFldl());
                cell2.setCellValue(c.getFlxl());
                cell3.setCellValue(c.getGg());
                cell4.setCellValue(c.getDw());
                cell5.setCellValue(c.getKc());
                cell6.setCellValue(c.getYjkc());
                cell7.setCellValue(c.getYbsl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
                cell6.setCellStyle(style);
                cell7.setCellStyle(style);

            }
        }

        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\仓库\\补货清单.xlsx");
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
    public static void setRegionBorder(int border, CellRangeAddress region, Sheet sheet, Workbook wb){
        RegionUtil.setBorderBottom(border,region, sheet, wb);
        RegionUtil.setBorderLeft(border,region, sheet, wb);
        RegionUtil.setBorderRight(border,region, sheet, wb);
        RegionUtil.setBorderTop(border,region, sheet, wb);
    }
}
