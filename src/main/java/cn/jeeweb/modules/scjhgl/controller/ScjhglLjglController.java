package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
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
* @Description:    生产计划管理-零部件管理
* @Author:         杜凯之
* @CreateDate:     2018/9/4 17:07
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/ljgl")
@RequiresPathPermission("scjhgl:ljgl")
public class ScjhglLjglController extends BaseCRUDController<ScjhglLjgl, String> {

    /**零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;
    /**
    * @Description:    搜索项
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 17:22
    * @Version:        1.0
    */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    /**
    * @Description:    转到创建零部件页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:32
    * @Version:        1.0
    */
    @RequestMapping(value = "createLj",method = {RequestMethod.GET,RequestMethod.POST})
    public String createLj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        return display("create");
    }

    /**
    * @Description:    添加一条零部件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/4 17:49
    * @Version:        1.0
    */
    @RequestMapping(value = "saveLj",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveLj(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglLjgl scjhglLjgl){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String jhid = scjhglLjgl.getHtid();
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(jhid);
        int jhsl = 0;
        if (scjhglHtgl.getSl()!=null&&!scjhglHtgl.getSl().equals("")){
            jhsl = Integer.parseInt(scjhglHtgl.getSl());
        }
        int ljsl = 0;
        if (scjhglLjgl.getDyl()!=null&&!scjhglLjgl.getDyl().equals("")){
            ljsl = Integer.parseInt(scjhglLjgl.getDyl());
        }
        int zyl = jhsl*ljsl;
        scjhglLjgl.setSl(zyl+"");
        scjhglLjgl.setSysl(zyl+"");
        scjhglLjgl.setWrksl(zyl+"");
        scjhglLjgl.setSfsbj("0");
        scjhglLjgl.setSfwwcrk("0");
        scjhglLjgl.setRq(currentDate);

        scjhglLjglService.insert(scjhglLjgl);
    }

    /**
    * @Description:    展示所有零部件信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:53
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxljglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglLjgl> ajaxljglList(String pxfs, String dlid, Queryable queryable, ScjhglLjgl scglSzgyxl, HttpServletRequest request, HttpServletResponse response, Model model){
        if (pxfs==null){
            pxfs = "1";
        }
        PageJson<ScjhglLjgl> pageJson = scjhglLjglService.ajaxljglList(pxfs, queryable,scglSzgyxl);
        return pageJson;
    }

    /**
     * Dscription: 删除零部件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 14:35
     */
    @RequestMapping(value = "deleteLj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteLj(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String ljid = idsArray[i];
            //先删除ljid下属的零部件工艺编制信息
            List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(ljid);
            if (ljgybzByLjid!=null){
                for (ScglLjgybz s: ljgybzByLjid) {
                    scglLjgybzService.deleteById(s.getId());
                }
            }
            //再删除ljid下属的工艺大类编制信息
            EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
            wrapper.eq("LJID",ljid);
            List<ScglGydlbz> scglGydlbzs = scglGydlbzService.selectList(wrapper);
            if (scglGydlbzs!=null){
                for (ScglGydlbz s: scglGydlbzs) {
                    scglGydlbzService.deleteById(s.getId());
                }
            }
            //最后删除零部件
            scjhglLjglService.deleteById(ljid);
        }
    }

    /**
     * Dscription: 转到修改数量页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/5 17:58
     */
    @RequestMapping(value = "modifySl", method={RequestMethod.GET, RequestMethod.POST})
    public String modifySl(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        model.addAttribute("scjhglLjgl" ,scjhglLjgl);
        return display("modifySl");
    }

    /**
     * Dscription: 保存数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/5 18:08
     */
    @RequestMapping(value = "saveSl", method={RequestMethod.GET, RequestMethod.POST})
    public void saveSl(String id, String lbjsl, String htid, String ljmc, String ljth, HttpServletRequest request, HttpServletResponse response, Model model){
        int lbjsli = 0;
        if (lbjsl!=null&&!lbjsl.equals("")){
            lbjsli = Integer.parseInt(lbjsl);
        }
        //先改变零件的信息
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        scjhglLjgl.setWrksl(lbjsl);
        scjhglLjgl.setSysl(lbjsl);
        scjhglLjgl.setSl(lbjsl);
        scjhglLjgl.setLjmc(ljmc);
        scjhglLjgl.setLjth(ljth);
        scjhglLjgl.setHtid(htid);
        scjhglLjglService.updateById(scjhglLjgl);

        //再更新下属零件工艺编制信息
        List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(id);
        if (ljgybzByLjid.size()>0){
            for (ScglLjgybz s : ljgybzByLjid) {
                s.setWrksl(lbjsli);
                s.setSysl(lbjsli);
                s.setSl(lbjsli);
                scglLjgybzService.updateById(s);
            }
        }

    }

    /**
     * Dscription: 导出零件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 22:28
     */
    @RequestMapping(value = "exportLj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportLj(String jhid, String pxfs, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String htmc = "";
        ScjhglLjgl scjhglLjgl1 = new ScjhglLjgl();
        scjhglLjgl1.setHtid(jhid);
        List<ScjhglLjgl> getData = scjhglLjglService.exportLj(pxfs, scjhglLjgl1);
        htmc = getData.get(0).getHtid();
        for (ScjhglLjgl s : getData) {
            if (!s.getHtid().equals(htmc)){
                htmc = "";
            }
        }
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("零部件详情");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3700);
        sheet1.setColumnWidth(1, 3700);
        sheet1.setColumnWidth(2, 3700);
        sheet1.setColumnWidth(3, 3700);
        sheet1.setColumnWidth(4, 3700);
        sheet1.setColumnWidth(5, 3700);
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
        cell00.setCellValue("计划名称");
        cell01.setCellValue("零部件名称");
        cell02.setCellValue("零部件图号");
        cell03.setCellValue("单用量");
        cell04.setCellValue("数量");
        cell05.setCellValue("未入库数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);

        if (getData!=null){
            for (int i=0;i<getData.size();i++){
                ScjhglLjgl c = getData.get(i);
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

                //给单元格设值
                cell0.setCellValue(c.getHtid());
                cell1.setCellValue(c.getLjmc());
                cell2.setCellValue(c.getLjth());
                cell3.setCellValue(c.getDyl());
                cell4.setCellValue(c.getSl());
                cell5.setCellValue(c.getWrksl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
            }
        }
        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\生产\\"+htmc+" 零部件详情.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();

    }


}
