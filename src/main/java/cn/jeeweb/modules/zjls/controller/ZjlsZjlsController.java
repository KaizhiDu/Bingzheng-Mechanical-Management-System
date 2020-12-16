package cn.jeeweb.modules.zjls.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jcsz.entity.JcszMxmb;
import cn.jeeweb.modules.jcsz.service.IJcszMxmbService;
import cn.jeeweb.modules.zjls.dto.ZjlsValueDTO;
import cn.jeeweb.modules.zjls.entity.ZjlsZj;
import cn.jeeweb.modules.zjls.entity.ZjlsZjls;
import cn.jeeweb.modules.zjls.service.ZjlsZjService;
import cn.jeeweb.modules.zjls.service.ZjlsZjlsService;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 资金流水
 *
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:35
 */
@Controller
@RequestMapping("${admin.url.prefix}/zjls/zjls")
@RequiresPathPermission("zjls:zjls")
public class ZjlsZjlsController extends BaseCRUDController<ZjlsZjls, String> {

    @Autowired
    private ZjlsZjService zjlsZjService;

    @Autowired
    private ZjlsZjlsService zjlsZjlsService;

    @Autowired
    private IJcszMxmbService iJcszMxmbService;


    /**
     * Dscription: 搜索项和前置内容
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response) {
        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int n = Integer.parseInt(dateArray[0]);
        int y = Integer.parseInt(dateArray[1]);
        int r = Integer.parseInt(dateArray[2]);
        model.addAttribute("yf", y);
        model.addAttribute("nd", n);
        model.addAttribute("r", r);

        //查看有没有资金信息
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        int count = zjlsZjService.selectCount(wrapper);
        if (count == 0) {
            ZjlsZj zjlsZj = new ZjlsZj();
            zjlsZj.setOne("0");
            zjlsZj.setTwo("0");
            zjlsZjService.insert(zjlsZj);
        }
        EntityWrapper<JcszMxmb> wrapper2 = new EntityWrapper<JcszMxmb>();
        wrapper2.eq("type", "4");
        List<JcszMxmb> zjlsGsList = iJcszMxmbService.selectList(wrapper2);
        wrapper2.orderBy("sort", false);
        model.addAttribute("gsList", zjlsGsList);

        EntityWrapper<JcszMxmb> wrapper3 = new EntityWrapper<JcszMxmb>();
        wrapper3.eq("type", "5");
        wrapper3.orderBy("sort", false);
        List<JcszMxmb> zjlsXmList = iJcszMxmbService.selectList(wrapper3);
        EntityWrapper<JcszMxmb> wrapper4 = new EntityWrapper<JcszMxmb>();
        wrapper4.eq("type", "6");
        wrapper4.orderBy("sort", false);
        List<JcszMxmb> zjlsXmzcList = iJcszMxmbService.selectList(wrapper4);
        for (JcszMxmb jcszMxmb : zjlsXmzcList) {
            zjlsXmList.add(jcszMxmb);
        }
        model.addAttribute("xmList", zjlsXmList);
    }

    /**
     * Dscription: 导出
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2020/1/18 11:33
     */
    @RequestMapping(value = "dc", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void dc(ZjlsZjls zjlsZjls, String yi, String er, String zongji, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<ZjlsZjls> dc = zjlsZjlsService.dc(zjlsZjls);
        String exportExcelName = zjlsZjls.getN()+"年";
        if (!zjlsZjls.getY().equals("")){
            exportExcelName = exportExcelName + zjlsZjls.getY()+"月";
            if (zjlsZjls.getR().equals("")) exportExcelName = exportExcelName + ",";
        }
        if (!zjlsZjls.getR().equals("")){
            exportExcelName = exportExcelName + zjlsZjls.getR()+"日,";
        }
        if (!zjlsZjls.getMx2().equals("")){
            exportExcelName = exportExcelName + zjlsZjls.getMx2()+",";
        }
        if (!zjlsZjls.getLx().equals("")){
            if (zjlsZjls.getLx().equals("0")) exportExcelName = exportExcelName + "收入,";
                if (zjlsZjls.getLx().equals("1")) exportExcelName = exportExcelName + "支出,";
        }
        exportExcelName = exportExcelName + "资金流水";
        try {
            exportDcExcel(dc, exportExcelName, yi, er, zongji);
        } catch (Exception e){

        }
    }

    public void exportDcExcel(List<ZjlsZjls> list, String exportExcelName, String yi, String er, String zongji) throws Exception{
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("资金流水");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 1900);
        sheet1.setColumnWidth(1, 1900);
        sheet1.setColumnWidth(2, 1900);
        sheet1.setColumnWidth(3, 1900);
        sheet1.setColumnWidth(4, 2200);
        sheet1.setColumnWidth(5, 2500);
        sheet1.setColumnWidth(6, 2500);
        sheet1.setColumnWidth(7, 5000);

        //设置边框
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //表头
        Row row0 = sheet1.createRow(0);
        row0.setHeightInPoints(60);
        Cell cell00 = row0.createCell(0);
        Cell cell01 = row0.createCell(1);
        Cell cell02 = row0.createCell(2);
        Cell cell03 = row0.createCell(3);
        Cell cell04 = row0.createCell(4);
        Cell cell05 = row0.createCell(5);
        Cell cell06 = row0.createCell(6);
        Cell cell07 = row0.createCell(7);

        cell00.setCellValue("类型");
        cell01.setCellValue("年");
        cell02.setCellValue("月");
        cell03.setCellValue("日");
        cell04.setCellValue("项目");
        cell05.setCellValue("流动资金");
        cell06.setCellValue("占用资金");
        cell07.setCellValue("详情");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        cell05.setCellStyle(style);
        cell06.setCellStyle(style);
        cell07.setCellStyle(style);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ZjlsZjls c = list.get(i);
                //创建一行
                Row row = sheet1.createRow(i + 1);
                row.setHeightInPoints(30);

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
                cell0.setCellValue(c.getLx());
                cell1.setCellValue(c.getN());
                cell2.setCellValue(c.getY());
                cell3.setCellValue(c.getR());
                cell4.setCellValue(c.getMx2());
                cell5.setCellValue(c.getOne());
                cell6.setCellValue(c.getTwo());
                cell7.setCellValue(c.getMx());
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
        Row zjrow1 = sheet1.createRow(list.size()+2);
        Cell zjcell1 = zjrow1.createCell(7);
        zjcell1.setCellValue("流动资金：");
        zjcell1.setCellStyle(style);
        Row zjrow2 = sheet1.createRow(list.size()+3);
        Cell zjcell2 = zjrow2.createCell(7);
        zjcell2.setCellValue(yi);
        zjcell2.setCellStyle(style);
        Row zjrow3 = sheet1.createRow(list.size()+4);
        Cell zjcell3 = zjrow3.createCell(7);
        zjcell3.setCellValue("占用资金：");
        zjcell3.setCellStyle(style);
        Row zjrow4 = sheet1.createRow(list.size()+5);
        Cell zjcell4 = zjrow4.createCell(7);
        zjcell4.setCellValue(er);
        zjcell4.setCellStyle(style);
        Row zjrow5 = sheet1.createRow(list.size()+6);
        Cell zjcell5 = zjrow5.createCell(7);
        zjcell5.setCellValue("总计：");
        zjcell5.setCellStyle(style);
        Row zjrow6 = sheet1.createRow(list.size()+7);
        Cell zjcell6 = zjrow6.createCell(7);
        zjcell6.setCellValue(zongji);
        zjcell6.setCellStyle(style);

        String io = "d:\\bingzhengjixie\\"+exportExcelName+".xlsx";
        //创建流
        FileOutputStream fileOut = new FileOutputStream(io);
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * Dscription: 静态改变各个资金源的数值
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/6 15:36
     */
    @RequestMapping(value = "changeValue", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ZjlsValueDTO changeValue(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        ZjlsValueDTO zjlsValueDTO = new ZjlsValueDTO();
        //查看有没有资金信息
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        int count = zjlsZjService.selectCount(wrapper);
        if (count == 0) {
            ZjlsZj zjlsZj = new ZjlsZj();
            zjlsZj.setTwo("0");
            zjlsZj.setOne("0");
            zjlsZjService.insert(zjlsZj);
        }
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        double one = getNumber(zjlsZj.getOne());
        double two = getNumber(zjlsZj.getTwo());
        double sum = one + two;
        zjlsValueDTO.setOne(df.format(one));
        zjlsValueDTO.setTwo(df.format(two));
        zjlsValueDTO.setSum(df.format(sum));
        return zjlsValueDTO;
    }

    public double getNumber(String number) throws ParseException {
        double d1 = new DecimalFormat().parse(number).doubleValue();
        return d1;
    }

    /**
     * Dscription: 转到资金收入页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 15:53
     */
    @RequestMapping(value = "sr", method = {RequestMethod.GET, RequestMethod.POST})
    public String sr(HttpServletRequest request, HttpServletResponse response, Model model) {
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);

        EntityWrapper<JcszMxmb> wrapper2 = new EntityWrapper<JcszMxmb>();
        wrapper2.eq("type", "4");
        List<JcszMxmb> zjlsGsList = iJcszMxmbService.selectList(wrapper2);
        wrapper2.orderBy("sort", false);
        model.addAttribute("gsList", zjlsGsList);

        EntityWrapper<JcszMxmb> wrapper3 = new EntityWrapper<JcszMxmb>();
        wrapper3.eq("type", "5");
        wrapper3.orderBy("sort", false);
        List<JcszMxmb> zjlsXmsrList = iJcszMxmbService.selectList(wrapper3);
        model.addAttribute("xmsrList", zjlsXmsrList);

        return display("sr");
    }

    /**
     * Dscription: 清空数据
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 16:20
     */
    @RequestMapping(value = "qksj", method = {RequestMethod.GET, RequestMethod.POST})
    public void qksj(HttpServletRequest request, HttpServletResponse response, Model model) {
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        zjlsZj.setOne("0");
        zjlsZj.setTwo("0");
        zjlsZjService.updateById(zjlsZj);
        EntityWrapper<ZjlsZjls> wrapper2 = new EntityWrapper<ZjlsZjls>();
        zjlsZjlsService.delete(wrapper2);
    }


    /**
     * Dscription: 保存输入信息
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 13:10
     */
    @RequestMapping(value = "saveSr", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveSr(String mx, String gs, String money, String lx, String jtsj, String zjly, String mx2, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        // mxmb添加次数
        if (gs!=null&&!gs.equals("")) {
            JcszMxmb jcszMxmb = iJcszMxmbService.selectById(gs);
            jcszMxmb.setSort(jcszMxmb.getSort() + 1);
            iJcszMxmbService.updateById(jcszMxmb);
            gs = jcszMxmb.getName();
        }

        if (mx2!=null&&!mx2.equals("")) {
            JcszMxmb jcszMxmb = iJcszMxmbService.selectById(mx2);
            jcszMxmb.setSort(jcszMxmb.getSort() + 1);
            iJcszMxmbService.updateById(jcszMxmb);
            mx2 = jcszMxmb.getName();
        }

        if (money == null) {
            money = "0";
        }

        DecimalFormat df = new DecimalFormat("#,###.00");

        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rq = sdf.format(date);

        String[] dateArray = jtsj.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        //float moneyf = Float.parseFloat(money);
        double moneyd = Double.parseDouble(money);
//        if (moneyf<1){
//            moneyf = moneyf + 1;
//        }
        money = df.format(moneyd);

        ZjlsZjls zjlsZjls = new ZjlsZjls();
        if (zjly.equals("1")) {
            zjlsZjls.setOne(money);
        } else {
            zjlsZjls.setTwo(money);
        }
        zjlsZjls.setN(n);
        zjlsZjls.setY(y);
        zjlsZjls.setR(r);
        zjlsZjls.setRq(rq);
        zjlsZjls.setLx(lx);
        zjlsZjls.setMx(mx);
        zjlsZjls.setMx2(mx2);
        zjlsZjls.setGs(gs);

        zjlsZjlsService.insert(zjlsZjls);

        //还要改变资金数额
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        if (zjly.equals("1")) {
            double oldOne = getNumber(zjlsZj.getOne());
            oldOne = oldOne + moneyd;
            zjlsZj.setOne(df.format(oldOne));
        } else {
            double oldTwo = getNumber(zjlsZj.getTwo());
            oldTwo = oldTwo + moneyd;
            zjlsZj.setTwo(df.format(oldTwo));
        }
        zjlsZjService.updateById(zjlsZj);
    }

    /**
     * Dscription: 展示所有资金流水信息
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 16:34
     */
    @RequestMapping(value = "ajaxZjlsList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ZjlsZjls> ajaxZzglList(String n, String y, String gs, String lx, String r, String mx2, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nn = Integer.parseInt(dateArray[0]);
        int yy = Integer.parseInt(dateArray[1]);
        int rr = Integer.parseInt(dateArray[2]);
        ZjlsZjls zjlsZjls = new ZjlsZjls();
        zjlsZjls.setLx(lx);
        zjlsZjls.setN(n);
        zjlsZjls.setY(y);
        zjlsZjls.setR(r);
        zjlsZjls.setMx2(mx2);
        zjlsZjls.setGs(gs);

        if (n == null) {
            zjlsZjls.setN(nn + "");
        }
        if (y == null) {
            zjlsZjls.setY(yy + "");
        }
        if (r == null) {
            zjlsZjls.setR(rr + "");
        }
        PageJson<ZjlsZjls> pageJson = zjlsZjlsService.ajaxZjlsList(queryable, zjlsZjls);
        return pageJson;
    }

    /**
     * Dscription: 转到支出页面
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 19:11
     */
    @RequestMapping(value = "zc", method = {RequestMethod.GET, RequestMethod.POST})
    public String zc(HttpServletRequest request, HttpServletResponse response, Model model) {
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);

        EntityWrapper<JcszMxmb> wrapper2 = new EntityWrapper<JcszMxmb>();
        wrapper2.eq("type", "4");
        List<JcszMxmb> zjlsGsList = iJcszMxmbService.selectList(wrapper2);
        wrapper2.orderBy("sort", false);
        model.addAttribute("gsList", zjlsGsList);

        EntityWrapper<JcszMxmb> wrapper3 = new EntityWrapper<JcszMxmb>();
        wrapper3.eq("type", "6");
        wrapper3.orderBy("sort", false);
        List<JcszMxmb> zjlsXmzcList = iJcszMxmbService.selectList(wrapper3);
        model.addAttribute("xmzcList", zjlsXmzcList);
        return display("zc");
    }

    /**
     * Dscription: 保存支出
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 19:19
     */
    @RequestMapping(value = "saveZc", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZc(String mx, String money, String lx, String jtsj, String zjly, String mx2, String gs, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        // mxmb添加次数
        if (gs!=null&&!gs.equals("")) {
            JcszMxmb jcszMxmb = iJcszMxmbService.selectById(gs);
            jcszMxmb.setSort(jcszMxmb.getSort() + 1);
            iJcszMxmbService.updateById(jcszMxmb);
            gs = jcszMxmb.getName();
        }

        if (mx2!=null&&!mx2.equals("")) {
            JcszMxmb jcszMxmb = iJcszMxmbService.selectById(mx2);
            jcszMxmb.setSort(jcszMxmb.getSort() + 1);
            iJcszMxmbService.updateById(jcszMxmb);
            mx2 = jcszMxmb.getName();
        }

        if (money == null) {
            money = "0";
        } else {
            money = "-" + money;
        }

        DecimalFormat df = new DecimalFormat("#,###.00");

        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rq = sdf.format(date);

        String[] dateArray = jtsj.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        double moneyd = Double.parseDouble(money);

        money = df.format(moneyd);

        ZjlsZjls zjlsZjls = new ZjlsZjls();
        if (zjly.equals("1")) {
            zjlsZjls.setOne(money);
        } else {
            zjlsZjls.setTwo(money);
        }
        zjlsZjls.setN(n);
        zjlsZjls.setY(y);
        zjlsZjls.setR(r);
        zjlsZjls.setRq(rq);
        zjlsZjls.setLx(lx);
        zjlsZjls.setMx(mx);
        zjlsZjls.setMx2(mx2);
        zjlsZjls.setGs(gs);

        zjlsZjlsService.insert(zjlsZjls);

        //还要改变资金数额
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        if (zjly.equals("1")) {
            double oldOne = getNumber(zjlsZj.getOne());
            oldOne = oldOne + moneyd;
            zjlsZj.setOne(df.format(oldOne));
        } else {
            double oldTwo = getNumber(zjlsZj.getTwo());
            oldTwo = oldTwo + moneyd;
            zjlsZj.setTwo(df.format(oldTwo));
        }
        zjlsZjService.updateById(zjlsZj);
    }

    /**
     * Dscription: 删除资金流水
     *
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 19:57
     */
    @RequestMapping(value = "deleteZjls", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteZjls(String ids, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String idsArray[] = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            String id = idsArray[i];
            ZjlsZjls zjlsZjls = zjlsZjlsService.selectById(id);
            double one = 0;
            double two = 0;
            if (zjlsZjls.getOne() != null) {
                one = getNumber(zjlsZjls.getOne());
            }
            if (zjlsZjls.getTwo() != null) {
                two = getNumber(zjlsZjls.getTwo());
            }
            EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
            ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
            double newOne = getNumber(zjlsZj.getOne()) - one;
            double newTwo = getNumber(zjlsZj.getTwo()) - two;
            zjlsZj.setOne(df.format(newOne));
            zjlsZj.setTwo(df.format(newTwo));
            zjlsZjService.updateById(zjlsZj);

            //最后删掉资金流水信息
            zjlsZjlsService.deleteById(id);
        }
    }
}
