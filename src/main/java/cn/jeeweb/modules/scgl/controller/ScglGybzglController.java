package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.dto.GybzglDTO;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGybzgl;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGybzglService;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Dscription: 生产管理-工艺编制概览
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 9:46
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/gybzgl")
@RequiresPathPermission("scgl:gybzgl")
public class ScglGybzglController extends BaseCRUDController<ScglGybzgl, String> {

    /**生产管理-工艺编制概览Service*/
    @Autowired
    private IScglGybzglService scglGybzglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**生产计划管理-零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**生产管理-工艺小类编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**
     * Dscription: 搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:46
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到所有计划信息
        List<SsxDTO> jhglList = scjhglHtglService.getJhList();
        model.addAttribute("jhglList", jhglList);
    }

    /**
     * Dscription: 展示所有工艺编制概览
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:09
     */
    @RequestMapping(value = "ajaxGybzglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<GybzglDTO> ajaxGybzglList(Queryable queryable, GybzglDTO gybzglDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<GybzglDTO> pageJson = scglGybzglService.ajaxGybzglList(queryable,gybzglDTO);
        return pageJson;
    }

    /**
     * Dscription: 根据计划ID查所有下属零部件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:02
     */
    @RequestMapping(value = "cxLj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SsxDTO> cxLj(String jhid, HttpServletResponse response, HttpServletRequest request, Model model){
        List<SsxDTO> ljList = scjhglLjglService.cxLj(jhid);
        return ljList;
    }

    /**
     * Dscription: 根据零部件ID查到所有下属工艺大类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:19
     */
    @RequestMapping(value = "cxGydl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SsxDTO> cxGydl(String ljid, HttpServletResponse response, HttpServletRequest request, Model model){
        List<SsxDTO> gydlList = scglGydlbzService.cxGydl(ljid);
        return gydlList;
    }

    /**
     * Dscription: 根据编制大类ID查到所有下属的编制小类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:19
     */
    @RequestMapping(value = "cxGyxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ScglLjgybz> cxGyxl(String gydlbzid, HttpServletResponse response, HttpServletRequest request, Model model){
        EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
        wrapper.eq("gydlbzid",gydlbzid);
        List<ScglLjgybz> gyxlList = scglLjgybzService.selectList(wrapper);
        return gyxlList;
    }

    /**
     * Dscription: 导出工艺
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 23:22
     */
    @RequestMapping(value = "exportGy", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportGy(GybzglDTO gybzglDTO, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        List<GybzglDTO> getData = scglGybzglService.exprotGy(gybzglDTO);
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("工艺详情");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3500);
        sheet1.setColumnWidth(1, 3500);
        sheet1.setColumnWidth(2, 3500);
        sheet1.setColumnWidth(3, 3500);
        sheet1.setColumnWidth(4, 3500);
        sheet1.setColumnWidth(5, 3500);
        sheet1.setColumnWidth(6, 3500);
        sheet1.setColumnWidth(7, 3500);
        sheet1.setColumnWidth(8, 3500);

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
        cell00.setCellValue("计划名称");
        cell01.setCellValue("零部件名称");
        cell02.setCellValue("零部件图号");
        cell03.setCellValue("工艺大类");
        cell04.setCellValue("工艺小类");
        cell05.setCellValue("数量");
        cell06.setCellValue("已分配数量");
        cell07.setCellValue("可分配数量");
        cell08.setCellValue("剩余数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);
        cell06.setCellStyle(style);
        cell07.setCellStyle(style);
        cell08.setCellStyle(style);


        if (getData!=null){
            for (int i=0;i<getData.size();i++){
                GybzglDTO c = getData.get(i);
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

                cell00.setCellValue("计划名称");
                cell01.setCellValue("零部件名称");
                cell02.setCellValue("零部件图号");
                cell03.setCellValue("工艺大类");
                cell04.setCellValue("工艺小类");
                cell05.setCellValue("数量");
                cell06.setCellValue("已分配数量");
                cell07.setCellValue("可分配数量");
                cell08.setCellValue("剩余数量");

                //给单元格设值
                cell0.setCellValue(c.getJhbh());
                cell1.setCellValue(c.getLjmc());
                cell2.setCellValue(c.getLjth());
                cell3.setCellValue(c.getGydlmc());
                cell4.setCellValue(c.getGyxlmc());
                cell5.setCellValue(c.getSl());
                cell6.setCellValue(c.getYfpsl());
                cell7.setCellValue(c.getKfpsl());
                cell8.setCellValue(c.getSysl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
                cell6.setCellStyle(style);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);

            }
        }
        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\ 工艺详情.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }
}
