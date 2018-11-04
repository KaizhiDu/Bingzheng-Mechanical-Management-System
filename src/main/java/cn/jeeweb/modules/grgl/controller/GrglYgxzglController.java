package cn.jeeweb.modules.grgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.service.IGrglService;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
@Controller
@RequestMapping("${admin.url.prefix}/grgl/ygxzgl")
@RequiresPathPermission("grgl:ygxzgl")
public class GrglYgxzglController extends BaseCRUDController<GrglYgxzgl, String> {

    /** 员工管理Service*/
    @Autowired
    private IGrglService grglService;

    /**员工管理 - 员工薪资管理Service*/
    @Autowired
    private IGrglYgxzglService grglYgxzglService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nd = Integer.parseInt(dateArray[0]);
        int yf = Integer.parseInt(dateArray[1]);
        model.addAttribute("nd", nd);
        model.addAttribute("yf", yf);


    }

    /**
     * Dscription: 得到所有员工薪资管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/6 17:56
     */
    @RequestMapping(value = "ajaxYgxzglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<GrglYgxzgl> ajaxYgxzglList(String nd2, String yf2, Queryable queryable, GrglYgxzgl grglYgxzgl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (nd2!=null){
            grglYgxzgl.setNd(Integer.parseInt(nd2));
        }
        if (yf2!=null){
            grglYgxzgl.setYf(Integer.parseInt(yf2));
        }
        PageJson<GrglYgxzgl> pageJson = grglYgxzglService.ajaxYgxzglList(queryable,grglYgxzgl);
        return pageJson;
    }

    /**
     * Dscription: 转到设置奖罚页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 8:28
     */
    @RequestMapping(value = "szjf", method={RequestMethod.GET, RequestMethod.POST})
    public String szjf(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        GrglYgxzgl grglYgxzgl = grglYgxzglService.selectById(id);
        model.addAttribute("grglYgxzgl", grglYgxzgl);
        return display("szjf");
    }

    /**
     * Dscription: 保存员工薪资管理
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 8:46
     */
    @RequestMapping(value = "saveYgxzgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveYgxzgl(String id, String jl, String kk, String bz, HttpServletRequest request, HttpServletResponse response, Model model){
        GrglYgxzgl grglYgxzgl = grglYgxzglService.selectById(id);

        float hj = Float.parseFloat(grglYgxzgl.getHj());
        if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
           hj = hj - Float.parseFloat(grglYgxzgl.getJl());
        }
        if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
            hj = hj + Float.parseFloat(grglYgxzgl.getKk());
        }
        grglYgxzgl.setId(id);
        grglYgxzgl.setJl(jl);
        grglYgxzgl.setKk(kk);
        grglYgxzgl.setBz(bz);
        if (grglYgxzgl.getJl()!=null&&!grglYgxzgl.getJl().equals("")){
            hj = hj + Float.parseFloat(grglYgxzgl.getJl());
        }
        if (grglYgxzgl.getKk()!=null&&!grglYgxzgl.getKk().equals("")){
            hj = hj - Float.parseFloat(grglYgxzgl.getKk());
        }
        grglYgxzgl.setHj(hj+"");
        grglYgxzglService.updateById(grglYgxzgl);
    }

    /**
     * Dscription: 导出工资单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 9:34
     */
    @RequestMapping(value = "exportGzd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportGzd(String nd, String yf, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //获取指定年度和月份的工资信息
        EntityWrapper<GrglYgxzgl> wrapper = new EntityWrapper<GrglYgxzgl>();
        wrapper.eq("ND", nd);
        wrapper.eq("YF", yf);
        List<GrglYgxzgl> grglYgxzglList = grglYgxzglService.selectList(wrapper);

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("工资单");
        //设置单元格宽度
        sheet1.setColumnWidth(1, 1800);
        sheet1.setColumnWidth(2, 2200);
        sheet1.setColumnWidth(3, 1800);
        sheet1.setColumnWidth(4, 1800);
        sheet1.setColumnWidth(6, 1800);
        sheet1.setColumnWidth(7, 2000);
        sheet1.setColumnWidth(8, 2200);
        sheet1.setColumnWidth(9, 2200);
        sheet1.setColumnWidth(10, 2000);
        sheet1.setColumnWidth(11, 2200);
        sheet1.setColumnWidth(12, 2000);
        sheet1.setColumnWidth(13, 2000);
        sheet1.setColumnWidth(14, 3000);
        sheet1.setColumnWidth(15, 2000);
        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        if (grglYgxzglList!=null){
            for (int i=0;i<grglYgxzglList.size();i++){
                GrglYgxzgl g = grglYgxzglList.get(i);
                //创建一行
                Row row0 = sheet1.createRow(i*3);
                //row0.setHeightInPoints(35);
                //创建单元格
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
                Cell cell010 = row0.createCell(10);
                Cell cell011 = row0.createCell(11);
                Cell cell012 = row0.createCell(12);
                Cell cell013 = row0.createCell(13);
                Cell cell014 = row0.createCell(14);
                Cell cell015 = row0.createCell(15);
                //给单元格设值
                cell00.setCellValue("年月");
                cell01.setCellValue("姓名");
                cell02.setCellValue("职位工资");
                cell03.setCellValue("底薪");
                cell04.setCellValue("房补");
                cell05.setCellValue("交通费");
                cell06.setCellValue("补贴");
                cell07.setCellValue("保险");
                cell08.setCellValue("日工工资");
                cell09.setCellValue("承包金额");
                cell010.setCellValue("餐补");
                cell011.setCellValue("出勤工资");
                cell012.setCellValue("奖励");
                cell013.setCellValue("扣款");
                cell014.setCellValue("合计");
                cell015.setCellValue("签字");
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
                cell010.setCellStyle(style);
                cell011.setCellStyle(style);
                cell012.setCellStyle(style);
                cell013.setCellStyle(style);
                cell014.setCellStyle(style);
                cell015.setCellStyle(style);

                //创建二行
                Row row1 = sheet1.createRow(i*3+1);
                //row0.setHeightInPoints(35);
                //创建单元格
                Cell cell10 = row1.createCell(0);
                Cell cell11 = row1.createCell(1);
                Cell cell12 = row1.createCell(2);
                Cell cell13 = row1.createCell(3);
                Cell cell14 = row1.createCell(4);
                Cell cell15 = row1.createCell(5);
                Cell cell16 = row1.createCell(6);
                Cell cell17 = row1.createCell(7);
                Cell cell18 = row1.createCell(8);
                Cell cell19 = row1.createCell(9);
                Cell cell110 = row1.createCell(10);
                Cell cell111 = row1.createCell(11);
                Cell cell112 = row1.createCell(12);
                Cell cell113 = row1.createCell(13);
                Cell cell114 = row1.createCell(14);
                Cell cell115 = row1.createCell(15);

                //给单元格设值
                cell10.setCellValue(g.getNd()+"-"+g.getYf());
                cell11.setCellValue(g.getXm());
                cell12.setCellValue(g.getZwgz());
                cell13.setCellValue(g.getDx());
                cell14.setCellValue(g.getFb());
                cell15.setCellValue(g.getJtf());
                cell16.setCellValue(g.getBt());
                cell17.setCellValue(g.getBx());
                cell18.setCellValue(g.getRggz());
                cell19.setCellValue(g.getCbje());
                cell110.setCellValue(g.getCq());
                cell111.setCellValue(g.getZcqgz());
                cell112.setCellValue(g.getJl());
                cell113.setCellValue(g.getKk());
                cell114.setCellValue(g.getHj());
                cell115.setCellValue("");
                cell10.setCellStyle(style);
                cell11.setCellStyle(style);
                cell12.setCellStyle(style);
                cell13.setCellStyle(style);
                cell14.setCellStyle(style);
                cell15.setCellStyle(style);
                cell16.setCellStyle(style);
                cell17.setCellStyle(style);
                cell18.setCellStyle(style);
                cell19.setCellStyle(style);
                cell110.setCellStyle(style);
                cell111.setCellStyle(style);
                cell112.setCellStyle(style);
                cell113.setCellStyle(style);
                cell114.setCellStyle(style);
                cell115.setCellStyle(style);

            }
        }

        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\"+nd+"-"+yf+"工资单.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }
}
