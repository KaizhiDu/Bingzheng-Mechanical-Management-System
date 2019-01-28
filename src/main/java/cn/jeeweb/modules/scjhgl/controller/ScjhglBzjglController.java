package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglDl;
import cn.jeeweb.modules.ckgl.entity.CkglJhs;
import cn.jeeweb.modules.ckgl.service.ICkglBzjService;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import cn.jeeweb.modules.ckgl.service.ICkglJhsService;
import cn.jeeweb.modules.ckgl.service.ICkglXlService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBzjglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
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
 * Dscription: 生产计划管理 - 标准件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/21 16:27
 */
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/bzjgl")
@RequiresPathPermission("scjhgl:bzjgl")
public class ScjhglBzjglController extends BaseCRUDController<ScjhglBzjgl, String> {

    /**生产计划管理 - 标准件管理*/
    @Autowired
    private IScjhglBzjglService scjhglBzjglService;

    /**仓库管理 - 标准件*/
    @Autowired
    private ICkglBzjService ckglBzjService;

    /**仓库管理 - 大类*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**仓库管理 - 小类*/
    @Autowired
    private ICkglXlService ckglXlService;

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理 - 进货商Service*/
    @Autowired
    private ICkglJhsService ckglJhsService;

    /**
     * @Description:    搜索项
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 17:22
     * @Version:        1.0
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("rq", false);
        wrapper.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    /**
     * Dscription: 转到添加标准件页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/22 14:47
     */
    @RequestMapping(value = "createBzj", method={RequestMethod.GET, RequestMethod.POST})
    public String createBzj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "标准件");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        EntityWrapper<ScjhglHtgl> wrapper2 = new EntityWrapper<ScjhglHtgl>();
        wrapper2.orderBy("rq", false);
        wrapper2.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper2);
        model.addAttribute("htList", list);
        EntityWrapper<CkglJhs> wrapper1 = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper1);
        model.addAttribute("ckglJhs", ckglJhs);
        return display("createBzj");
    }

    /**
     * Dscription: 保存标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/22 14:47
     */
    @RequestMapping(value = "saveBzj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBzj(ScjhglBzjgl scjhglBzjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String jhid = scjhglBzjgl.getHtid();
        int jhsl = Integer.parseInt(scjhglHtglService.selectById(jhid).getSl());
        int dyl = Integer.parseInt(scjhglBzjgl.getDyl());
        int sl = jhsl * dyl;
        scjhglBzjgl.setSl(sl+"");
        scjhglBzjgl.setRq(currentDate);
        scjhglBzjglService.insert(scjhglBzjgl);
    }

    /**
     * Dscription: 显示所有标准件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/22 15:12
     */
    @RequestMapping(value = "ajaxBzjList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglBzjgl> ajaxBzjList(Queryable queryable, ScjhglBzjgl scjhglBzjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PageJson<ScjhglBzjgl> pageJson = scjhglBzjglService.ajaxBzjList(queryable, scjhglBzjgl);
        return pageJson;
    }

    /**
     * Dscription: 删除标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/23 12:17
     */
    @RequestMapping(value = "deleteBzj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteBzj(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
            String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
                String id = idsArray[i];
            scjhglBzjglService.deleteById(id);
        }
    }

    /**
     * Dscription: 修改标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/23 12:24
     */
    @RequestMapping(value = "modifyBzj", method={RequestMethod.GET, RequestMethod.POST})
    public String modifyBzj(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        //标准件管理信息
        ScjhglBzjgl scjhglBzjgl = scjhglBzjglService.selectById(id);
        model.addAttribute("scjhglBzjgl", scjhglBzjgl);
        EntityWrapper<CkglDl> wrapper = new EntityWrapper<CkglDl>();
        wrapper.eq("SSCK", "标准件");
        List<CkglDl> ckglDlList = ckglDlService.selectList(wrapper);
        model.addAttribute("DlList" ,ckglDlList);
        EntityWrapper<ScjhglHtgl> wrapper2 = new EntityWrapper<ScjhglHtgl>();
        wrapper2.orderBy("rq", false);
        wrapper2.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper2);
        model.addAttribute("htList", list);
        EntityWrapper<CkglJhs> wrapper1 = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper1);
        model.addAttribute("ckglJhs", ckglJhs);
        return display("modifyBzj");
}

    /**
     * Dscription: 更新标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/23 12:38
     */
    @RequestMapping(value = "updateBzj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void updateBzj(ScjhglBzjgl scjhglBzjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        scjhglBzjglService.updateById(scjhglBzjgl);
    }

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:12
     */
    @RequestMapping(value = "exportBzj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportBzj(String jhid, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String htmc = "";
        ScjhglBzjgl scjhglBzjgl1 = new ScjhglBzjgl();
        scjhglBzjgl1.setHtid(jhid);
        List<ScjhglBzjgl> getData = scjhglBzjglService.exportBzj(scjhglBzjgl1);
        htmc = getData.get(0).getHtid();
        for (ScjhglBzjgl s : getData) {
            if (!s.getHtid().equals(htmc)){
                htmc = "";
            }
        }
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("标准件详情");
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
        cell01.setCellValue("分类大类");
        cell02.setCellValue("分类小类");
        cell03.setCellValue("规格");
        cell04.setCellValue("单位");
        cell05.setCellValue("数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);

        if (getData!=null){
            for (int i=0;i<getData.size();i++){
                ScjhglBzjgl c = getData.get(i);
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
                cell1.setCellValue(c.getFldl());
                cell2.setCellValue(c.getFlxl());
                cell3.setCellValue(c.getGg());
                cell4.setCellValue(c.getDw());
                cell5.setCellValue(c.getSl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
            }
        }
        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\生产\\"+htmc+" 标准件详情.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();

    }

}
