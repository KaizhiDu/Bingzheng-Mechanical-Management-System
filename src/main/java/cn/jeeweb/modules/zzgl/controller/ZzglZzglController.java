package cn.jeeweb.modules.zzgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jcsz.entity.JcszMxmb;
import cn.jeeweb.modules.jcsz.entity.JcszZzse;
import cn.jeeweb.modules.jcsz.service.IJcszMxmbService;
import cn.jeeweb.modules.jcsz.service.IJcszZzseService;
import cn.jeeweb.modules.zzgl.dto.ValueDTO;
import cn.jeeweb.modules.zzgl.dto.ZzglDTO;
import cn.jeeweb.modules.zzgl.entity.ZzglJh;
import cn.jeeweb.modules.zzgl.entity.ZzglZzgl;
import cn.jeeweb.modules.zzgl.service.IZzglJhService;
import cn.jeeweb.modules.zzgl.service.IZzglZzglService;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 资金管理 - 资金管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/5 9:33
 */
@Controller
@RequestMapping("${admin.url.prefix}/zzgl/zzgl")
@RequiresPathPermission("zzgl:zzgl")
public class ZzglZzglController extends BaseCRUDController<ZzglZzgl, String> {

    /**基础设置 - 资金数额Service*/
    @Autowired
    private IJcszZzseService jcszZzseService;

    /**基础设置 - 明细模板Service*/
    @Autowired
    private IJcszMxmbService jcszMxmbService;

    /**资金管理 - 资金管理Service*/
    @Autowired
    private IZzglZzglService zzglZzglService;

    /**资金管理 - 借款Service*/
    @Autowired
    private IZzglJhService zzglJhService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
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
        //把各项名字传入
        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper1);
        model.addAttribute("jcszZzseName", jcszZzseName);
        //把各项基本资金传入
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","1");
        JcszZzse zz1 = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("zz1", zz1);
        //把各项流动资金传入
        EntityWrapper<JcszZzse> wrapper3 = new EntityWrapper<JcszZzse>();
        wrapper3.eq("TYPE","2");
        JcszZzse zz2 = jcszZzseService.selectOne(wrapper3);
        if (zz2==null){
            JcszZzse zz3 = new JcszZzse();
            zz3.setType("2");
            zz3.setTwo("0");
            zz3.setThree("0");
            zz3.setFour("0");
            zz3.setFive("0");
            zz3.setSeven("0");
            zz3.setOne("0");
            zz3.setSix("0");
            zz3.setEight("0");
            model.addAttribute("zz2", zz3);
        }
        else{
            model.addAttribute("zz2", zz2);
        }

    }

    /**
     * Dscription: 展示所有资金管理信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 14:47
     */
    @RequestMapping(value = "ajaxZzglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ZzglZzgl> ajaxZzglList(String n, String y, String lx, String px, String r, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model){
        if (px==null){
            px = "0";
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int nn = Integer.parseInt(dateArray[0]);
        int yy = Integer.parseInt(dateArray[1]);
        int rr = Integer.parseInt(dateArray[2]);
        ZzglDTO zzglDTO = new ZzglDTO();
        zzglDTO.setLx(lx);
        zzglDTO.setN(n);
        zzglDTO.setY(y);
        zzglDTO.setPx(px);
        zzglDTO.setR(r);
        if (n==null){
            zzglDTO.setN(nn+"");
        }
        if (y==null){
            zzglDTO.setY(yy+"");
        }
        if (r==null){
            zzglDTO.setR(rr+"");
        }
        PageJson<ZzglZzgl> pageJson = zzglZzglService.ajaxZzglList(queryable,zzglDTO);
        return pageJson;
    }

    /**
     * Dscription: 转到收入界面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 11:11
     */
    @RequestMapping(value = "sr", method={RequestMethod.GET, RequestMethod.POST})
    public String sr(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<JcszMxmb> wrapper1 = new EntityWrapper<JcszMxmb>();
        wrapper1.eq("type", "0");
        wrapper1.orderBy("sort",false);
        List<JcszMxmb> jcszMxmbs = jcszMxmbService.selectList(wrapper1);
        model.addAttribute("jcszMxmbs", jcszMxmbs);
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("jcszZzseName", jcszZzseName);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        return display("sr");
    }

    /**
     * Dscription: 转到支出界面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 11:11
     */
    @RequestMapping(value = "zc", method={RequestMethod.GET, RequestMethod.POST})
    public String zc(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<JcszMxmb> wrapper1 = new EntityWrapper<JcszMxmb>();
        wrapper1.eq("type", "1");
        wrapper1.orderBy("sort",false);
        List<JcszMxmb> jcszMxmbs = jcszMxmbService.selectList(wrapper1);
        model.addAttribute("jcszMxmbs", jcszMxmbs);
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("jcszZzseName", jcszZzseName);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        return display("zc");
    }

    /**
     * Dscription: 转到支出界面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 11:11
     */
    @RequestMapping(value = "dd", method={RequestMethod.GET, RequestMethod.POST})
    public String dd(HttpServletRequest request, HttpServletResponse response, Model model){
//        EntityWrapper<JcszMxmb> wrapper1 = new EntityWrapper<JcszMxmb>();
//        wrapper1.eq("type", "1");
//        wrapper1.orderBy("sort",false);
//        List<JcszMxmb> jcszMxmbs = jcszMxmbService.selectList(wrapper1);
//        model.addAttribute("jcszMxmbs", jcszMxmbs);
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("jcszZzseName", jcszZzseName);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        return display("dd");
    }

    /**
     * Dscription: 保存输入信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 13:10
     */
    @RequestMapping(value = "saveSr", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveSr(String mx1, String mx2, String jz, String money, String lx,String jtsj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (money==null){
            money = "0";
        }
        float moneyf = Float.parseFloat(money);
        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rq = sdf.format(date);

        String[] dateArray = jtsj.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        String jzArray[] = jz.split("-");
        String zjcx = jzArray[0];
        String zjmc = jzArray[1];
        String xxmx = mx1 + " 转入 " + money + " 到 "+zjmc;

        //得到所有数据,然后放到实体类中
        ZzglZzgl z = new ZzglZzgl();
        if (zjcx.equals("1")){
            z.setOne(money);
        }
        if (zjcx.equals("2")){
            z.setTwo(money);
        }
        if (zjcx.equals("3")){
            z.setThree(money);
        }
        if (zjcx.equals("4")){
            z.setFour(money);
        }
        if (zjcx.equals("5")){
            z.setFive(money);
        }
        if (zjcx.equals("6")){
            z.setSix(money);
        }
        if (zjcx.equals("7")){
            z.setSeven(money);
        }
        if (zjcx.equals("8")){
            z.setEight(money);
        }
        z.setLx(lx);
        z.setXxmx(xxmx);
        z.setRq(rq);
        z.setR(r);
        z.setN(n);
        z.setY(y);
        z.setMx(mx1);
        z.setMxbc(mx2);
        //然后插入一条
        zzglZzglService.insert(z);

        //然后要设置对应的收入明细sort+1
        EntityWrapper<JcszMxmb> wrapper1 = new EntityWrapper<JcszMxmb>();
        wrapper1.eq("TYPE", "0");
        wrapper1.eq("NAME", mx1);
        JcszMxmb jcszMxmb = jcszMxmbService.selectOne(wrapper1);
        int newSort = jcszMxmb.getSort()+1;
        jcszMxmb.setSort(newSort);
        jcszMxmbService.updateById(jcszMxmb);

        //最后要加到jcszZzse里面
        //先判断有没有type为2的数据存在
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "2");
        JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
        if (jcszZzse==null){
            JcszZzse j = new JcszZzse();
            j.setType("2");
            j.setOne("0");
            j.setTwo("0");
            j.setThree("0");
            j.setFour("0");
            j.setFive("0");
            j.setSix("0");
            j.setSeven("0");
            j.setEight("0");
            if (zjcx.equals("1")){
                j.setOne(money);
            }
            if (zjcx.equals("2")){
                j.setTwo(money);
            }
            if (zjcx.equals("3")){
                j.setThree(money);
            }
            if (zjcx.equals("4")){
                j.setFour(money);
            }
            if (zjcx.equals("5")){
                j.setFive(money);
            }
            if (zjcx.equals("6")){
                j.setSix(money);
            }
            if (zjcx.equals("7")){
                j.setSeven(money);
            }
            if (zjcx.equals("8")){
                j.setEight(money);
            }
            jcszZzseService.insert(j);
        }
        //累加上
        else {
            float xs = 0;
            if (zjcx.equals("1")){
                xs = Float.parseFloat(jcszZzse.getOne()) + moneyf;
                jcszZzse.setOne(xs+"");
            }
            if (zjcx.equals("2")){
                xs = Float.parseFloat(jcszZzse.getTwo()) + moneyf;
                jcszZzse.setTwo(xs+"");
            }
            if (zjcx.equals("3")){
                xs = Float.parseFloat(jcszZzse.getThree()) + moneyf;
                jcszZzse.setThree(xs+"");
            }
            if (zjcx.equals("4")){
                xs = Float.parseFloat(jcszZzse.getFour()) + moneyf;
                jcszZzse.setFour(xs+"");
            }
            if (zjcx.equals("5")){
                xs = Float.parseFloat(jcszZzse.getFive()) + moneyf;
                jcszZzse.setFive(xs+"");
            }
            if (zjcx.equals("6")){
                xs = Float.parseFloat(jcszZzse.getSix()) + moneyf;
                jcszZzse.setSix(xs+"");
            }
            if (zjcx.equals("7")){
                xs = Float.parseFloat(jcszZzse.getSeven()) + moneyf;
                jcszZzse.setSeven(xs+"");
            }
            if (zjcx.equals("8")){
                xs = Float.parseFloat(jcszZzse.getEight()) + moneyf;
                jcszZzse.setEight(xs+"");
            }
            jcszZzseService.updateById(jcszZzse);
        }
    }

    /**
     * Dscription: 保存支出信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 9:27
     */
    @RequestMapping(value = "saveZc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZc(String mx1, String mx2, String cz, String money, String lx,String jtsj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (money==null){
            money = "0";
        }
        String m = money;
        //支出是减去的钱
        float moneyf = 0 - Float.parseFloat(money);
        money = moneyf+"";
        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rq = sdf.format(date);
        String[] dateArray = jtsj.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        String jzArray[] = cz.split("-");
        String zjcx = jzArray[0];
        String zjmc = jzArray[1];
        String xxmx = "从 "+ zjmc + " 转出 " + m + " 用于 "+mx1;

        //得到所有数据,然后放到实体类中
        ZzglZzgl z = new ZzglZzgl();
        if (zjcx.equals("1")){
            z.setOne(money);
        }
        if (zjcx.equals("2")){
            z.setTwo(money);
        }
        if (zjcx.equals("3")){
            z.setThree(money);
        }
        if (zjcx.equals("4")){
            z.setFour(money);
        }
        if (zjcx.equals("5")){
            z.setFive(money);
        }
        if (zjcx.equals("6")){
            z.setSix(money);
        }
        if (zjcx.equals("7")){
            z.setSeven(money);
        }
        if (zjcx.equals("8")){
            z.setEight(money);
        }
        z.setLx(lx);
        z.setXxmx(xxmx);
        z.setRq(rq);
        z.setR(r);
        z.setN(n);
        z.setY(y);
        z.setMx(mx1);
        z.setMxbc(mx2);

        //然后插入一条
        zzglZzglService.insert(z);

        //然后要设置对应的收入明细sort+1
        EntityWrapper<JcszMxmb> wrapper1 = new EntityWrapper<JcszMxmb>();
        wrapper1.eq("TYPE", "1");
        wrapper1.eq("NAME", mx1);
        JcszMxmb jcszMxmb = jcszMxmbService.selectOne(wrapper1);
        int newSort = jcszMxmb.getSort()+1;
        jcszMxmb.setSort(newSort);
        jcszMxmbService.updateById(jcszMxmb);

        //最后要加到jcszZzse里面
        //先判断有没有type为2的数据存在
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "2");
        JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
        if (jcszZzse==null){
            JcszZzse j = new JcszZzse();
            j.setType("2");
            j.setOne("0");
            j.setTwo("0");
            j.setThree("0");
            j.setFive("0");
            j.setFour("0");
            j.setSix("0");
            j.setSeven("0");
            j.setEight("0");
            if (zjcx.equals("1")){
                j.setOne(money);
            }
            if (zjcx.equals("2")){
                j.setTwo(money);
            }
            if (zjcx.equals("3")){
                j.setThree(money);
            }
            if (zjcx.equals("4")){
                j.setFour(money);
            }
            if (zjcx.equals("5")){
                j.setFive(money);
            }
            if (zjcx.equals("6")){
                j.setSix(money);
            }
            if (zjcx.equals("7")){
                j.setSeven(money);
            }
            if (zjcx.equals("8")){
                j.setEight(money);
            }
            jcszZzseService.insert(j);
        }
        //累加上
        else {
            float xs = 0;
            if (zjcx.equals("2")){
                xs = Float.parseFloat(jcszZzse.getTwo()) + moneyf;
                jcszZzse.setTwo(xs+"");
            }
            if (zjcx.equals("3")){
                xs = Float.parseFloat(jcszZzse.getThree()) + moneyf;
                jcszZzse.setThree(xs+"");
            }
            if (zjcx.equals("1")){
                xs = Float.parseFloat(jcszZzse.getOne()) + moneyf;
                jcszZzse.setOne(xs+"");
            }
            if (zjcx.equals("4")){
                xs = Float.parseFloat(jcszZzse.getFour()) + moneyf;
                jcszZzse.setFour(xs+"");
            }
            if (zjcx.equals("5")){
                xs = Float.parseFloat(jcszZzse.getFive()) + moneyf;
                jcszZzse.setFive(xs+"");
            }
            if (zjcx.equals("6")){
                xs = Float.parseFloat(jcszZzse.getSix()) + moneyf;
                jcszZzse.setSix(xs+"");
            }
            if (zjcx.equals("7")){
                xs = Float.parseFloat(jcszZzse.getSeven()) + moneyf;
                jcszZzse.setSeven(xs+"");
            }
            if (zjcx.equals("8")){
                xs = Float.parseFloat(jcszZzse.getEight()) + moneyf;
                jcszZzse.setEight(xs+"");
            }
            jcszZzseService.updateById(jcszZzse);
        }
    }

    /**
     * Dscription: 保存调动信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 11:25
     */
    @RequestMapping(value = "saveDd", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveDd(String mx2, String jz, String cz, String money, String lx, String jtsj, HttpServletRequest request, HttpServletResponse response, Model model){
        if (money==null){
            money = "0";
        }
        float jmoney = Float.parseFloat(money);
        float cmoney = 0 - Float.parseFloat(money);
        String czMoney = cmoney+"";

        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rq = sdf.format(date);
        String[] dateArray = jtsj.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        String jzArray[] = jz.split("-");
        String jzcx = jzArray[0];
        String jzmc = jzArray[1];
        String czArray[] = cz.split("-");
        String czcx = czArray[0];
        String czmc = czArray[1];
        String mx = "从 "+czmc+" 到 "+jzmc;
        String xxmx = "从 "+ czmc + " 调动 " + money + " 到 "+jzmc;

        //得到所有数据,然后放到实体类中
        ZzglZzgl z = new ZzglZzgl();
        if (!jzcx.equals(czcx)){
            if (jzcx.equals("1")){
                z.setOne(money);
            }
            if (jzcx.equals("2")){
                z.setTwo(money);
            }
            if (jzcx.equals("3")){
                z.setThree(money);
            }
            if (jzcx.equals("4")){
                z.setFour(money);
            }
            if (jzcx.equals("5")){
                z.setFive(money);
            }
            if (jzcx.equals("6")){
                z.setSix(money);
            }
            if (jzcx.equals("7")){
                z.setSeven(money);
            }
            if (jzcx.equals("8")){
                z.setEight(money);
            }
            if (czcx.equals("1")){
                z.setOne(czMoney);
            }
            if (czcx.equals("2")){
                z.setTwo(czMoney);
            }
            if (czcx.equals("3")){
                z.setThree(czMoney);
            }
            if (czcx.equals("4")){
                z.setFour(czMoney);
            }
            if (czcx.equals("5")){
                z.setFive(czMoney);
            }
            if (czcx.equals("6")){
                z.setSix(czMoney);
            }
            if (czcx.equals("7")){
                z.setSeven(czMoney);
            }
            if (czcx.equals("8")){
                z.setEight(czMoney);
            }
        }
        z.setLx(lx);
        z.setXxmx(xxmx);
        z.setRq(rq);
        z.setR(r);
        z.setN(n);
        z.setY(y);
        z.setMx(mx);
        z.setMxbc(mx2);

        //然后插入一条
        zzglZzglService.insert(z);

        //最后要加到jcszZzse里面
        //先判断有没有type为2的数据存在
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "2");
        JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
        if (jcszZzse==null){
            JcszZzse j = new JcszZzse();
            j.setType("2");
            j.setOne("0");
            j.setTwo("0");
            j.setThree("0");
            j.setFive("0");
            j.setFour("0");
            j.setSix("0");
            j.setSeven("0");
            j.setEight("0");
            if (jzcx.equals("1")){
                j.setOne(money);
            }
            if (jzcx.equals("2")){
                j.setTwo(money);
            }
            if (jzcx.equals("3")){
                j.setThree(money);
            }
            if (jzcx.equals("4")){
                j.setFour(money);
            }
            if (jzcx.equals("5")){
                j.setFive(money);
            }
            if (jzcx.equals("6")){
                j.setSix(money);
            }
            if (jzcx.equals("8")){
                j.setEight(money);
            }
            if (jzcx.equals("7")){
                j.setSeven(money);
            }
            if (czcx.equals("1")){
                j.setOne(czMoney);
            }
            if (czcx.equals("2")){
                j.setTwo(czMoney);
            }
            if (czcx.equals("3")){
                j.setThree(czMoney);
            }
            if (czcx.equals("4")){
                j.setFour(czMoney);
            }
            if (czcx.equals("5")){
                j.setFive(czMoney);
            }
            if (czcx.equals("6")){
                j.setSix(czMoney);
            }
            if (czcx.equals("8")){
                j.setEight(czMoney);
            }
            if (czcx.equals("7")){
                j.setSeven(czMoney);
            }

            jcszZzseService.insert(j);
        }
        //累加上
        else {
            float xs = 0;
            if (jzcx.equals("2")){
                xs = Float.parseFloat(jcszZzse.getTwo()) + jmoney;
                jcszZzse.setTwo(xs+"");
            }
            if (jzcx.equals("1")){
                xs = Float.parseFloat(jcszZzse.getOne()) + jmoney;
                jcszZzse.setOne(xs+"");
            }
            if (jzcx.equals("3")){
                xs = Float.parseFloat(jcszZzse.getThree()) + jmoney;
                jcszZzse.setThree(xs+"");
            }
            if (jzcx.equals("4")){
                xs = Float.parseFloat(jcszZzse.getFour()) + jmoney;
                jcszZzse.setFour(xs+"");
            }
            if (jzcx.equals("5")){
                xs = Float.parseFloat(jcszZzse.getFive()) + jmoney;
                jcszZzse.setFive(xs+"");
            }
            if (jzcx.equals("6")){
                xs = Float.parseFloat(jcszZzse.getSix()) + jmoney;
                jcszZzse.setSix(xs+"");
            }
            if (jzcx.equals("8")){
                xs = Float.parseFloat(jcszZzse.getEight()) + jmoney;
                jcszZzse.setEight(xs+"");
            }
            if (jzcx.equals("7")){
                xs = Float.parseFloat(jcszZzse.getSeven()) + jmoney;
                jcszZzse.setSeven(xs+"");
            }

            float cxs = 0;
            if (czcx.equals("2")){
                cxs = Float.parseFloat(jcszZzse.getTwo()) + cmoney;
                jcszZzse.setTwo(cxs+"");
            }
            if (czcx.equals("1")){
                cxs = Float.parseFloat(jcszZzse.getOne()) + cmoney;
                jcszZzse.setOne(cxs+"");
            }
            if (czcx.equals("3")){
                cxs = Float.parseFloat(jcszZzse.getThree()) + cmoney;
                jcszZzse.setThree(cxs+"");
            }
            if (czcx.equals("4")){
                cxs = Float.parseFloat(jcszZzse.getFour()) + cmoney;
                jcszZzse.setFour(cxs+"");
            }
            if (czcx.equals("5")){
                cxs = Float.parseFloat(jcszZzse.getFive()) + cmoney;
                jcszZzse.setFive(cxs+"");
            }
            if (czcx.equals("6")){
                cxs = Float.parseFloat(jcszZzse.getSix()) + cmoney;
                jcszZzse.setSix(cxs+"");
            }
            if (czcx.equals("8")){
                cxs = Float.parseFloat(jcszZzse.getEight()) + cmoney;
                jcszZzse.setEight(cxs+"");
            }
            if (czcx.equals("7")){
                cxs = Float.parseFloat(jcszZzse.getSeven()) + cmoney;
                jcszZzse.setSeven(cxs+"");
            }

            jcszZzseService.updateById(jcszZzse);
        }
    }

    /**
     * Dscription: 删除一条资金管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 12:01
     */
    @RequestMapping(value = "deleteZzgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteZzgl(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
            String id = idsArray[i];
            ZzglZzgl zzglZzgl = zzglZzglService.selectById(id);
            float one = 0;
            float two = 0;
            float three = 0;
            float four = 0;
            float five = 0;
            float six = 0;
            float seven = 0;
            float eight = 0;
            if (zzglZzgl.getOne()!=null&&!zzglZzgl.getOne().equals("")){
                one = Float.parseFloat(zzglZzgl.getOne());
            }
            if (zzglZzgl.getTwo()!=null&&!zzglZzgl.getTwo().equals("")){
                two = Float.parseFloat(zzglZzgl.getTwo());
            }
            if (zzglZzgl.getThree()!=null&&!zzglZzgl.getThree().equals("")){
                three = Float.parseFloat(zzglZzgl.getThree());
            }
            if (zzglZzgl.getFour()!=null&&!zzglZzgl.getFour().equals("")){
                four = Float.parseFloat(zzglZzgl.getFour());
            }
            if (zzglZzgl.getFive()!=null&&!zzglZzgl.getFive().equals("")){
                five = Float.parseFloat(zzglZzgl.getFive());
            }
            if (zzglZzgl.getSix()!=null&&!zzglZzgl.getSix().equals("")){
                six = Float.parseFloat(zzglZzgl.getSix());
            }
            if (zzglZzgl.getSeven()!=null&&!zzglZzgl.getSeven().equals("")){
                seven = Float.parseFloat(zzglZzgl.getSeven());
            }
            if (zzglZzgl.getEight()!=null&&!zzglZzgl.getEight().equals("")){
                eight = Float.parseFloat(zzglZzgl.getEight());
            }

            //得到jcszzzse信息
            EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
            wrapper2.eq("TYPE", "2");
            JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
            float oldOne = Float.parseFloat(jcszZzse.getOne());
            float oldTwo = Float.parseFloat(jcszZzse.getTwo());
            float oldThree = Float.parseFloat(jcszZzse.getThree());
            float oldFour = Float.parseFloat(jcszZzse.getFour());
            float oldFive = Float.parseFloat(jcszZzse.getFive());
            float oldSix = Float.parseFloat(jcszZzse.getSix());
            float oldSeven = Float.parseFloat(jcszZzse.getSeven());
            float oldEight = Float.parseFloat(jcszZzse.getEight());
            oldOne = oldOne - one;
            oldTwo = oldTwo - two;
            oldThree = oldThree - three;
            oldFour = oldFour - four;
            oldFive = oldFive - five;
            oldSix = oldSix - six;
            oldSeven = oldSeven - seven;
            oldEight = oldEight - eight;
            jcszZzse.setOne(oldOne+"");
            jcszZzse.setTwo(oldTwo+"");
            jcszZzse.setThree(oldThree+"");
            jcszZzse.setFour(oldFour+"");
            jcszZzse.setFive(oldFive+"");
            jcszZzse.setSix(oldSix+"");
            jcszZzse.setSeven(oldSeven+"");
            jcszZzse.setEight(oldEight+"");
            jcszZzseService.updateById(jcszZzse);

            //然后删除资金管理数据
            zzglZzglService.deleteById(id);
        }
    }




    /**
     * Dscription: 静态改变各个资金源的数值
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/6 15:36
     */
    @RequestMapping(value = "changeValue", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ValueDTO changeValue(HttpServletRequest request, HttpServletResponse response, Model model){
        ValueDTO valueDTO = new ValueDTO();
        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE", "1");
        JcszZzse j1 = jcszZzseService.selectOne(wrapper1);
        if (j1==null){
            j1 = new JcszZzse();
            j1.setEight("0");
            j1.setSeven("0");
            j1.setSix("0");
            j1.setFive("0");
            j1.setFour("0");
            j1.setThree("0");
            j1.setTwo("0");
            j1.setOne("0");
        }
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "2");
        JcszZzse j2 = jcszZzseService.selectOne(wrapper2);
        if (j2==null){
            j2 = new JcszZzse();
            j2.setEight("0");
            j2.setSix("0");
            j2.setFive("0");
            j2.setSeven("0");
            j2.setFour("0");
            j2.setThree("0");
            j2.setTwo("0");
            j2.setOne("0");
        }
        float one = Float.parseFloat(j1.getOne())+Float.parseFloat(j2.getOne());
        float two = Float.parseFloat(j1.getTwo())+Float.parseFloat(j2.getTwo());
        float three = Float.parseFloat(j1.getThree())+Float.parseFloat(j2.getThree());
        float four = Float.parseFloat(j1.getFour())+Float.parseFloat(j2.getFour());
        float five = Float.parseFloat(j1.getFive())+Float.parseFloat(j2.getFive());
        float six = Float.parseFloat(j1.getSix())+Float.parseFloat(j2.getSix());
        float seven = Float.parseFloat(j1.getSeven())+Float.parseFloat(j2.getSeven());
        float eight = Float.parseFloat(j1.getEight())+Float.parseFloat(j2.getEight());
        float sum = one + two + three + four + five + six + seven + eight;

        //sum还需要加上借款
        EntityWrapper<ZzglJh> wrapper = new EntityWrapper<ZzglJh>();
        List<ZzglJh> zzglJhs = zzglJhService.selectList(wrapper);
        for (ZzglJh z : zzglJhs) {
            sum = sum + Float.parseFloat(z.getMoney());
        }

        valueDTO.setEight(eight+"");
        valueDTO.setFive(five+"");
        valueDTO.setFour(four+"");
        valueDTO.setOne(one+"");
        valueDTO.setTwo(two+"");
        valueDTO.setThree(three+"");
        valueDTO.setSix(six+"");
        valueDTO.setSeven(seven+"");
        valueDTO.setSum(sum+"");
        return valueDTO;
    }

    /**
     * Dscription: 转到修改时间页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/6 15:36
     */
    @RequestMapping(value = "xgsj", method={RequestMethod.GET, RequestMethod.POST})
    public String xgsj(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ZzglZzgl zzglZzgl = zzglZzglService.selectById(id);
        String rq = zzglZzgl.getN()+"-"+zzglZzgl.getY()+"-"+zzglZzgl.getR();
        model.addAttribute("day", rq);
        model.addAttribute("zzglZzgl", zzglZzgl);
        return display("xgsj");
    }

    /**
     * Dscription: 修改时间
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 10:23
     */
    @RequestMapping(value = "saveXgsj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveXgsj(String id, String rq, HttpServletRequest request, HttpServletResponse response, Model model){
        String[] dateArray = rq.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String rqq = sdf.format(date);
        ZzglZzgl zzglZzgl = zzglZzglService.selectById(id);
        zzglZzgl.setRq(rqq);
        zzglZzgl.setN(n);
        zzglZzgl.setY(y);
        zzglZzgl.setR(r);
        zzglZzglService.updateById(zzglZzgl);
    }

  /**
   * Dscription: 转到借还页面
   * @author : Kevin Du
   * @version : 1.0
   * @date : 2019/1/7 14:25
   */
    @RequestMapping(value = "jh", method={RequestMethod.GET, RequestMethod.POST})
    public String jh(HttpServletRequest request, HttpServletResponse response, Model model){
                //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String[] dateArray = currentDate.split("-");
        int n = Integer.parseInt(dateArray[0]);
        int y = Integer.parseInt(dateArray[1]);
        int r = Integer.parseInt(dateArray[2]);
        model.addAttribute("y", y);
        model.addAttribute("n", n);
        model.addAttribute("r", r);
        model.addAttribute("day", currentDate);
        return display("jh");
    }

/**
 * Dscription: 导出资金管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 22:09
 */
    @RequestMapping(value = "exprortZzgl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exprortZzgl(String n, String y, String r, String lx, String px, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //首先根据搜索条件得到数据
        List<ZzglZzgl> zzglZzgls = zzglZzglService.exportZzgl(n, y, r, lx, px);
        //新建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //新建工作表
        Sheet sheet1 = wb.createSheet("资金流动单");
        //设置单元格宽度
        sheet1.setColumnWidth(0, 3000);
        sheet1.setColumnWidth(1, 2500);
        sheet1.setColumnWidth(2, 9000);
        sheet1.setColumnWidth(3, 7500);
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
        cell00.setCellValue("日期");
        cell01.setCellValue("类型");
        cell02.setCellValue("详情");
        cell03.setCellValue("明细");
        cell00.setCellStyle(style);
        cell01.setCellStyle(style);
        cell02.setCellStyle(style);
        cell03.setCellStyle(style);

        if (zzglZzgls!=null){
            for (int i=0;i<zzglZzgls.size();i++){
                ZzglZzgl c = zzglZzgls.get(i);
                //创建一行
                Row row = sheet1.createRow(i+1);
                row.setHeightInPoints(35);

                //创建单元格
                Cell cell0 = row.createCell(0);
                Cell cell1 = row.createCell(1);
                Cell cell2 = row.createCell(2);
                Cell cell3 = row.createCell(3);

                //给单元格设值
                cell0.setCellValue(c.getN()+"-"+c.getY()+"-"+c.getR());
                cell1.setCellValue(c.getLx());
                cell2.setCellValue(c.getXxmx());
                cell3.setCellValue(c.getMx());
                cell0.setCellStyle(style);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
            }
        }

        //创建流
        FileOutputStream fileOut = new FileOutputStream("d:\\bingzhengjixie\\资金流动单.xlsx");
        //输出流
        wb.write(fileOut);
        fileOut.close();
    }


}
