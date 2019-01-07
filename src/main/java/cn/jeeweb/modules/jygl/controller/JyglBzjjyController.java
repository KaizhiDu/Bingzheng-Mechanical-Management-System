package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBzj;
import cn.jeeweb.modules.ckgl.entity.CkglBzjMx;
import cn.jeeweb.modules.ckgl.entity.CkglJhs;
import cn.jeeweb.modules.ckgl.service.ICkglBzjMxSevice;
import cn.jeeweb.modules.ckgl.service.ICkglBzjService;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import cn.jeeweb.modules.ckgl.service.ICkglJhsService;
import cn.jeeweb.modules.jygl.entity.JyglBzjjy;
import cn.jeeweb.modules.jygl.service.IJyglBzjjyService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
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
import java.util.UUID;

/**
 * Dscription: 标准件检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/24 13:08
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/bzjjy")
@RequiresPathPermission("jygl:bzjjy")
public class JyglBzjjyController extends BaseCRUDController<JyglBzjjy, String> {

    /**标准件检验Service*/
    @Autowired
    private IJyglBzjjyService jyglBzjjyService;

    /**分类大类Service*/
    @Autowired
    private ICkglDlService ckglDlService;

    /**标准件Service*/
    @Autowired
    private ICkglBzjService ckglBzjService;

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**标准件明细*/
    @Autowired
    private ICkglBzjMxSevice ckglBzjMxSevice;

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
     * Dscription: 转到检验入库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 13:48
     */
    @RequestMapping(value = "jyrk", method={RequestMethod.GET, RequestMethod.POST})
    public String jyrk(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        JyglBzjjy jyglBzjjy = jyglBzjjyService.selectById(id);
        model.addAttribute("jyglBzjjy", jyglBzjjy);
        EntityWrapper<CkglJhs> wrapper1 = new EntityWrapper<CkglJhs>();
        List<CkglJhs> ckglJhs = ckglJhsService.selectList(wrapper1);
        model.addAttribute("ckglJhs", ckglJhs);
        return display("jyrk");
    }

    /**
     * Dscription: 保存入库信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 15:12
     */
    @RequestMapping(value = "saveRkxx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveRkxx(String id, String rksl, String status, String jhs, HttpServletRequest request, HttpServletResponse response, Model model){
        //先更改标准件管理表里面的信息
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String sj = sdf0.format(date0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = sdf.format(date);
        String dateArray[] = currentDate.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        JyglBzjjy jyglBzjjy = jyglBzjjyService.selectById(id);

        if (status.equals("0")){
            jyglBzjjy.setN(n);
            jyglBzjjy.setY(y);
            jyglBzjjy.setR(r);
        }

        //得到分类大类名称
        String fldlid = jyglBzjjy.getFldl();
        String dlmc = ckglDlService.selectById(fldlid).getDlmc();
        String xlmc = jyglBzjjy.getFlxl();
        String gg = jyglBzjjy.getGg();
        String dw = jyglBzjjy.getDw();
        String xsl = jyglBzjjy.getSl();

        //入库之前首先判断之前有没有入库。 如果为空的话，就可以进行操作
        if (jyglBzjjy.getRksl()==null){
            //如果大类，小类，规格都相同的话，就进行更新操作，否则进行插入操作
            EntityWrapper<CkglBzj> wrapper = new EntityWrapper<CkglBzj>();
            wrapper.eq("FLDL", dlmc);
            wrapper.eq("FLXL", xlmc);
            wrapper.eq("GG", gg);
            CkglBzj ckglBzj = ckglBzjService.selectOne(wrapper);
            //插入
            if (ckglBzj==null){
                UUID uuid = UUID.randomUUID();
               String bzjid = uuid.toString().replace("-", "");
                CkglBzj ckglBzj1 = new CkglBzj();
                ckglBzj1.setId(bzjid);
                ckglBzj1.setFldl(dlmc);
                ckglBzj1.setFlxl(xlmc);
                ckglBzj1.setGg(gg);
                ckglBzj1.setDw(dw);
                ckglBzj1.setKc(xsl);
                ckglBzjService.insert(ckglBzj1);

                CkglBzjMx ckglBzjMx = new CkglBzjMx();
                ckglBzjMx.setN(n);
                ckglBzjMx.setY(y);
                ckglBzjMx.setR(r);
                ckglBzjMx.setBzjid(bzjid);
                ckglBzjMx.setFldl(dlmc);
                ckglBzjMx.setFlxl(xlmc);
                ckglBzjMx.setGg(gg);
                String mx = "采购员 "+rksl+" 于 "+sj+" 入库 "+xsl+" 件";
                ckglBzjMx.setMx(mx);
                ckglBzjMx.setSj(new Date());
                ckglBzjMx.setJhs(jhs);
                ckglBzjMx.setJx("0");
                ckglBzjMxSevice.insert(ckglBzjMx);

            }
            //更新
            else {
                float oldKc = Float.parseFloat(ckglBzj.getKc());
                float xKc = Float.parseFloat(xsl);
                float kc = oldKc + xKc;
                ckglBzj.setKc(kc+"");
                ckglBzjService.updateById(ckglBzj);

                CkglBzjMx ckglBzjMx = new CkglBzjMx();
                ckglBzjMx.setN(n);
                ckglBzjMx.setY(y);
                ckglBzjMx.setR(r);
                ckglBzjMx.setBzjid(ckglBzj.getId());
                ckglBzjMx.setFldl(dlmc);
                ckglBzjMx.setFlxl(xlmc);
                ckglBzjMx.setGg(gg);
                String mx = "采购员 "+rksl+" 于 "+sj+" 入库 "+xsl+" 件";
                ckglBzjMx.setMx(mx);
                ckglBzjMx.setSj(new Date());
                ckglBzjMx.setJhs(jhs);
                ckglBzjMx.setJx("0");
                ckglBzjMxSevice.insert(ckglBzjMx);
            }
        }
        jyglBzjjy.setRksl(rksl);
        jyglBzjjy.setJhs(jhs);
        jyglBzjjyService.updateById(jyglBzjjy);

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
        JyglBzjjy jyglBzjjy = new JyglBzjjy();
        jyglBzjjy.setHtid(jhid);
        List<JyglBzjjy> getData = jyglBzjjyService.exportBzj(jyglBzjjy);
        htmc = getData.get(0).getHtid();
        for (JyglBzjjy s : getData) {
            if (!s.getHtid().equals(htmc)){
                htmc = "";
            }
        }
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("标准件检验");
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
                JyglBzjjy c = getData.get(i);
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
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\检验\\"+htmc+" 标准件检验.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();

    }
}
