package cn.jeeweb.modules.ckgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.entity.CkglCpCkjl;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglCpCkjlService;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
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

/**
 * Dscription: 仓库管理 - 成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 18:31
 */
@Controller
@RequestMapping("${admin.url.prefix}/ckgl/cp")
@RequiresPathPermission("ckgl:cp")
public class CkglCpController extends BaseCRUDController<CkglCp, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理 - 半成品Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理 - 成品Service*/
    @Autowired
    private ICkglCpService ckglCpService;

    /**仓库管理 - 成品出库记录Service*/
    @Autowired
    private ICkglCpCkjlService ckglCpCkjlService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //计划
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        //图号和名称
        EntityWrapper<CkglCp> wrapper1 = new EntityWrapper<CkglCp>();
        List<CkglCp> ckglCps = ckglCpService.selectList(wrapper1);
        model.addAttribute("cpList", ckglCps);
    }

    /**
     * Dscription: 转到成品出库页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/26 13:55
     */
    @RequestMapping(value = "ck", method={RequestMethod.GET, RequestMethod.POST})
    public String ck(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        CkglCp ckglCp = ckglCpService.selectById(id);
        model.addAttribute("cp",ckglCp);
        return display("ck");
    }

    /**
     * Dscription: 进行出库操作
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/26 14:13
     */
    @RequestMapping(value = "saveCk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveCk(String cpid, String cksl, HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        int cks = 0;
        if (cksl!=null&&!cksl.equals("")){
            cks = Integer.parseInt(cksl);
        }
       //得到成品信息
        CkglCp ckglCp = ckglCpService.selectById(cpid);
        //找一下出库记录表里有没有该记录
        String jhbh = ckglCp.getJhbh();
        String lbjth = ckglCp.getLbjth();
        EntityWrapper<CkglCpCkjl> wrapper = new EntityWrapper<CkglCpCkjl>();
        wrapper.eq("JHBH" ,jhbh);
        wrapper.eq("LBJTH" ,lbjth);
        wrapper.eq("RQ", currentTime);
        int count = ckglCpCkjlService.selectCount(wrapper);
        //没有对应信息，添加一条
        if (count==0){
        CkglCpCkjl ckglCpCkjl = new CkglCpCkjl();
        ckglCpCkjl.setJhid(ckglCp.getJhid());
        ckglCpCkjl.setJhbh(ckglCp.getJhbh());
        ckglCpCkjl.setLbjid(ckglCp.getLbjid());
        ckglCpCkjl.setLbjmc(ckglCp.getLbjmc());
        ckglCpCkjl.setLbjth(ckglCp.getLbjth());
        ckglCpCkjl.setRksl(cksl);
        ckglCpCkjl.setRq(currentTime);
            ckglCpCkjlService.insert(ckglCpCkjl);
        }
        //加上rksl
        else{
            CkglCpCkjl ckglCpCkjl = ckglCpCkjlService.selectOne(wrapper);
            int xsll =  Integer.parseInt(ckglCpCkjl.getRksl()) + cks;
            ckglCpCkjl.setRksl(xsll+"");
            ckglCpCkjlService.updateById(ckglCpCkjl);
        }

        //下面处理成品库信息
        int zsl = 0;
        if (ckglCp.getRksl()!=null&&!ckglCp.getRksl().equals("")){
            zsl = Integer.parseInt(ckglCp.getRksl());
        }

        //xsl 就是需要更新的数值
        int xsl = zsl - cks;
        //删除
        if (xsl==0){
            ckglCpService.deleteById(ckglCp.getId());

        }
        //更新
        else{
            ckglCp.setRksl(xsl+"");
            ckglCpService.updateById(ckglCp);
        }
    }

    /**
     * Dscription: 导出送货单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/26 15:16
     */
    @RequestMapping(value = "exportShd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportShd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        //得到数据
        EntityWrapper<CkglCpCkjl> wrapper = new EntityWrapper<CkglCpCkjl>();
        wrapper.eq("RQ", currentTime);
        wrapper.orderBy("JHBH");
        List<CkglCpCkjl> ckglCpCkjls = ckglCpCkjlService.selectList(wrapper);

        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("补货清单");
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
        row0.setHeightInPoints(35);
        Cell cell00 = row0.createCell(0);
        Cell cell01 = row0.createCell(1);
        Cell cell02 = row0.createCell(2);
        Cell cell03 = row0.createCell(3);
        Cell cell04 = row0.createCell(4);
        cell00.setCellValue("日期");
        cell01.setCellValue("计划名称");
        cell02.setCellValue("零部件名称");
        cell03.setCellValue("零部件图号");
        cell04.setCellValue("送货数量");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);

        if (ckglCpCkjls!=null){
            for (int i=0;i<ckglCpCkjls.size();i++){
                CkglCpCkjl c = ckglCpCkjls.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(35);

                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);
                Cell cell4 = row.createCell(4);

                //给单元格设值
                cell0.setCellValue(c.getRq());
                cell1.setCellValue(c.getJhbh());
                cell2.setCellValue(c.getLbjmc());
                cell3.setCellValue(c.getLbjth());
                cell4.setCellValue(c.getRksl());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
            }
        }

        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\"+currentTime+"送货清单.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();

    }

    /**
     * Dscription: 转到添加成品页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/31 13:45
     */
    @RequestMapping(value = "createCp", method={RequestMethod.GET, RequestMethod.POST})
    public String createCp(HttpServletRequest request, HttpServletResponse response, Model model){
        //计划
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        return display("createCp");
    }

    /**
     * Dscription: 保存成品信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/31 13:51
     */
    @RequestMapping(value = "saveCp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveCp(CkglCp ckglCp, HttpServletRequest request, HttpServletResponse response, Model model){
        //先判断半成品库里有没有该图号的零件存在
        EntityWrapper<CkglCp> wrapper = new EntityWrapper<CkglCp>();
        wrapper.eq("LBJTH", ckglCp.getLbjth());
        wrapper.eq("JHBH", ckglCp.getJhbh());
        int count = ckglCpService.selectCount(wrapper);
        if (count>0){
            CkglCp ckglCp1 = ckglCpService.selectOne(wrapper);
            int rksl = 0;
            if (ckglCp1.getRksl()!=null&&!ckglCp1.getRksl().equals("")){
                rksl = Integer.parseInt(ckglCp1.getRksl());
            }
            int newRksl = 0;
            if (ckglCp.getRksl()!=null&&!ckglCp.getRksl().equals("")){
                newRksl = Integer.parseInt(ckglCp.getRksl());
            }
            rksl = rksl + newRksl;
            ckglCp1.setRksl(rksl+"");
            ckglCpService.updateById(ckglCp1);
        }
        else{
            CkglCp ckgl = new CkglCp();
            ckgl.setJhid("");
            ckgl.setJhbh(ckglCp.getJhbh());
            ckgl.setLbjid("");
            ckgl.setLbjmc(ckglCp.getLbjmc());
            ckgl.setLbjth(ckglCp.getLbjth());
            ckgl.setRksl(ckglCp.getRksl());
            ckglCpService.insert(ckgl);

        }
    }

}
