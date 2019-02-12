package cn.jeeweb.modules.zzgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jcsz.entity.JcszZzse;
import cn.jeeweb.modules.jcsz.service.IJcszZzseService;
import cn.jeeweb.modules.zzgl.dto.QyzjyDTO;
import cn.jeeweb.modules.zzgl.entity.ZzglJh;
import cn.jeeweb.modules.zzgl.entity.ZzglJhmx;
import cn.jeeweb.modules.zzgl.service.IZzglJhService;
import cn.jeeweb.modules.zzgl.service.IZzglJhmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Dscription: 资金管理 - 借还
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 14:47
 */
@Controller
@RequestMapping("${admin.url.prefix}/zzgl/jh")
@RequiresPathPermission("zzgl:jh")
public class ZzglJhController extends BaseCRUDController<ZzglJh, String> {

    /**基础数据 - 资金数额Service*/
    @Autowired
    private IJcszZzseService jcszZzseService;

    /**资金管理 - 借还Service*/
    @Autowired
    private IZzglJhService zzglJhService;

    /**资金管理 - 借还明细Service*/
    @Autowired
    private IZzglJhmxService zzglJhmxService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
//        //得到当前年月
//        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date0 = new Date();
//        String currentDate = sdf0.format(date0);
//        String[] dateArray = currentDate.split("-");
//        String n = dateArray[0];
//        String y = dateArray[1];
//        String r = dateArray[2];
//        model.addAttribute("y", y);
//        model.addAttribute("n", n);
//        model.addAttribute("r", r);
    }

    /**
     * Dscription: 展示借款数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 16:34
     */
    @RequestMapping(value = "ajaxJhList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ZzglJh> ajaxZzglList(String n, String y, String name, String r, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model){
        ZzglJh z = new ZzglJh();
        z.setN(n);
        z.setY(y);
        z.setR(r);
        z.setName(name);
        PageJson<ZzglJh> pageJson = zzglJhService.ajaxJhList(queryable,z);
        return pageJson;
    }

    /**
     * Dscription: 转到借款页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 15:30
     */
    @RequestMapping(value = "jk", method={RequestMethod.GET, RequestMethod.POST})
    public String jk(HttpServletRequest request, HttpServletResponse response, Model model){
                //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
//        String[] dateArray = currentDate.split("-");
//        String n = dateArray[0];
//        String y = dateArray[1];
//        String r = dateArray[2];
//        model.addAttribute("y", y);
//        model.addAttribute("n", n);
//        model.addAttribute("r", r);
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("jcszZzseName", jcszZzseName);

        List<QyzjyDTO> qyList = getyxList();
        model.addAttribute("qyList", qyList);

        return display("jk");
    }

    /**
     * Dscription: 保存借还信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 15:57
     */
    @RequestMapping(value = "saveJh", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveJh(String rq, String name, String money, String jkzh, String mx, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.00");

        float moneyf = getNumber(money);
        money = df.format(moneyf);

        String[] jkzhArray = jkzh.split("-");
        String zhcx = jkzhArray[0];
        String zhmc = jkzhArray[1];

        String[] dateArray = rq.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        String jhid  = UUID.randomUUID().toString().replaceAll("-","");
        //创建一个新的jh记录
        ZzglJh zzglJh = new ZzglJh();
        zzglJh.setId(jhid);
        zzglJh.setN(n);
        zzglJh.setY(y);
        zzglJh.setR(r);
        zzglJh.setRq(currentDate);
        zzglJh.setName(name);
        zzglJh.setMoney(money);
        zzglJh.setZjzh(zhcx);
        zzglJh.setZjmc(zhmc);
        zzglJh.setBz(mx);
        zzglJhService.insert(zzglJh);

        //然后添加一条借还明细
        ZzglJhmx z = new ZzglJhmx();
        z.setJhid(jhid);
        z.setN(n);
        z.setY(y);
        z.setR(r);
        z.setRq(currentDate);
        z.setMx(mx);
        String rqq = n+"-"+y+"-"+r;
        String xxmx = name+" 从账户 "+zhmc+" 借了 "+money;
        z.setXxmx(xxmx);
        zzglJhmxService.insert(z);

        //然后更改账面数额
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
            j.setSix("0");
            j.setSeven("0");
            j.setEight("0");
            j.setFive("0");
            j.setNine("0");
            j.setTen("0");
            j.setEleven("0");
            j.setTwelve("0");
            j.setThirteen("0");
            j.setFourteen("0");
            j.setFifteen("0");
            j.setSixteen("0");
            j.setSeventeen("0");
            j.setEighteen("0");
            j.setNineteen("0");
            j.setTwenty("0");

            if (zhcx.equals("1")){
                j.setOne("-"+money);
            }
            if (zhcx.equals("2")){
                j.setTwo("-"+money);
            }
            if (zhcx.equals("3")){
                j.setThree("-"+money);
            }
            if (zhcx.equals("4")){
                j.setFour("-"+money);
            }
            if (zhcx.equals("5")){
                j.setFive("-"+money);
            }
            if (zhcx.equals("6")){
                j.setSix("-"+money);
            }
            if (zhcx.equals("7")){
                j.setSeven("-"+money);
            }
            if (zhcx.equals("8")){
                j.setEight("-"+money);
            }
            if (zhcx.equals("9")){
                j.setNine("-"+money);
            }
            if (zhcx.equals("10")){
                j.setTen("-"+money);
            }
            if (zhcx.equals("11")){
                j.setEleven("-"+money);
            }
            if (zhcx.equals("12")){
                j.setTwelve("-"+money);
            }
            if (zhcx.equals("13")){
                j.setThirteen("-"+money);
            }
            if (zhcx.equals("14")){
                j.setFourteen("-"+money);
            }
            if (zhcx.equals("15")){
                j.setFifteen("-"+money);
            }
            if (zhcx.equals("16")){
                j.setSixteen("-"+money);
            }
            if (zhcx.equals("17")){
                j.setSeventeen("-"+money);
            }
            if (zhcx.equals("18")){
                j.setEighteen("-"+money);
            }
            if (zhcx.equals("19")){
                j.setNineteen("-"+money);
            }
            if (zhcx.equals("20")){
                j.setTwenty("-"+money);
            }
            jcszZzseService.insert(j);
        }
        //累加上
        else {
            float xs = 0;
            if (zhcx.equals("1")){
                xs = getNumber(jcszZzse.getOne()) - moneyf;
                jcszZzse.setOne(df.format(xs));
            }
            if (zhcx.equals("2")){
                xs = getNumber(jcszZzse.getTwo()) - moneyf;
                jcszZzse.setTwo(df.format(xs));
            }
            if (zhcx.equals("3")){
                xs = getNumber(jcszZzse.getThree()) - moneyf;
                jcszZzse.setThree(df.format(xs));
            }
            if (zhcx.equals("4")){
                xs = getNumber(jcszZzse.getFour()) - moneyf;
                jcszZzse.setFour(df.format(xs));
            }
            if (zhcx.equals("8")){
                xs = getNumber(jcszZzse.getEight()) - moneyf;
                jcszZzse.setEight(df.format(xs));
            }
            if (zhcx.equals("5")){
                xs = getNumber(jcszZzse.getFive()) - moneyf;
                jcszZzse.setFive(df.format(xs));
            }
            if (zhcx.equals("6")){
                xs = getNumber(jcszZzse.getSix()) - moneyf;
                jcszZzse.setSix(df.format(xs));
            }
            if (zhcx.equals("7")){
                xs = getNumber(jcszZzse.getSeven()) - moneyf;
                jcszZzse.setSeven(df.format(xs));
            }

            if (zhcx.equals("9")){
                xs = getNumber(jcszZzse.getNine()) - moneyf;
                jcszZzse.setNine(df.format(xs));
            }
            if (zhcx.equals("10")){
                xs = getNumber(jcszZzse.getTen()) - moneyf;
                jcszZzse.setTen(df.format(xs));
            }
            if (zhcx.equals("11")){
                xs = getNumber(jcszZzse.getEleven()) - moneyf;
                jcszZzse.setEleven(df.format(xs));
            }
            if (zhcx.equals("12")){
                xs = getNumber(jcszZzse.getTwelve()) - moneyf;
                jcszZzse.setTwelve(df.format(xs));
            }
            if (zhcx.equals("13")){
                xs = getNumber(jcszZzse.getThirteen()) - moneyf;
                jcszZzse.setThirteen(df.format(xs));
            }
            if (zhcx.equals("14")){
                xs = getNumber(jcszZzse.getFourteen()) - moneyf;
                jcszZzse.setFourteen(df.format(xs));
            }
            if (zhcx.equals("15")){
                xs = getNumber(jcszZzse.getFifteen()) - moneyf;
                jcszZzse.setFifteen(df.format(xs));
            }
            if (zhcx.equals("16")){
                xs = getNumber(jcszZzse.getSixteen()) - moneyf;
                jcszZzse.setSixteen(df.format(xs));
            }
            if (zhcx.equals("17")){
                xs = getNumber(jcszZzse.getSeventeen()) - moneyf;
                jcszZzse.setSeventeen(df.format(xs));
            }
            if (zhcx.equals("18")){
                xs = getNumber(jcszZzse.getEighteen()) - moneyf;
                jcszZzse.setEighteen(df.format(xs));
            }
            if (zhcx.equals("19")){
                xs = getNumber(jcszZzse.getNineteen()) - moneyf;
                jcszZzse.setNineteen(df.format(xs));
            }
            if (zhcx.equals("20")){
                xs = getNumber(jcszZzse.getTwenty()) - moneyf;
                jcszZzse.setTwenty(df.format(xs));
            }
            jcszZzseService.updateById(jcszZzse);
        }
    }

    /**
     * Dscription: 转到还款页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 17:46
     */
    @RequestMapping(value = "hk", method={RequestMethod.GET, RequestMethod.POST})
    public String hk(String id, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.00");

        ZzglJh zzglJh = zzglJhService.selectById(id);
        model.addAttribute("zzglJh", zzglJh);
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE","0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("jcszZzseName", jcszZzseName);
        //得到当前年月
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);

        float moneyf = getNumber(zzglJh.getMoney());
        String money = moneyf+"";
        model.addAttribute("money", money);

        List<QyzjyDTO> qyList = getyxList();
        model.addAttribute("qyList", qyList);

        return display("hk");
    }

    /**
     * Dscription: 保存还款信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 18:13
     */
    @RequestMapping(value = "saveHk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHk(String id, String rq, String money, String hkzh, String mx, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.00");

        if (money==null){
            money = "0";
        }
        float hk = getNumber(money);
        money = df.format(hk);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        String[] dateArray = currentDate.split("-");
        String n = dateArray[0];
        String y = dateArray[1];
        String r = dateArray[2];

        //排序时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentTime = sdf.format(date);

        String hkzhArray[] = hkzh.split("-");
        String hkcx = hkzhArray[0];
        String hkmc = hkzhArray[1];

        //首先把借还里面的数减出来
        ZzglJh zzglJh = zzglJhService.selectById(id);
        float yjk = 0;
        if (zzglJh.getMoney()!=null&&!zzglJh.getMoney().equals("")){
            yjk = getNumber(zzglJh.getMoney());
        }
        float xjk = yjk - hk;
        zzglJh.setMoney(df.format(xjk));
        zzglJhService.updateById(zzglJh);

        //然后需要记录明细
        String name = zzglJh.getName();
        String xxmx = name+" 还 "+money+" 进了账户 "+hkmc;
        ZzglJhmx z = new ZzglJhmx();
        z.setJhid(id);
        z.setMx(mx);
        z.setXxmx(xxmx);
        z.setRq(currentTime);
        z.setN(n);
        z.setY(y);
        z.setR(r);
        zzglJhmxService.insert(z);

        //最后要加上jcszzzsr
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "2");
        JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
        float xs = 0;
        if (hkcx.equals("2")){
            xs = getNumber(jcszZzse.getTwo()) + hk;
            jcszZzse.setTwo(df.format(xs));
        }
        if (hkcx.equals("1")){
            xs = getNumber(jcszZzse.getOne()) + hk;
            jcszZzse.setOne(df.format(xs));
        }
        if (hkcx.equals("3")){
            xs = getNumber(jcszZzse.getThree()) + hk;
            jcszZzse.setThree(df.format(xs));
        }
        if (hkcx.equals("4")){
            xs = getNumber(jcszZzse.getFour()) + hk;
            jcszZzse.setFour(df.format(xs));
        }
        if (hkcx.equals("5")){
            xs = getNumber(jcszZzse.getFive()) + hk;
            jcszZzse.setFive(df.format(xs));
        }
        if (hkcx.equals("6")){
            xs = getNumber(jcszZzse.getSix()) + hk;
            jcszZzse.setSix(df.format(xs));
        }
        if (hkcx.equals("8")){
            xs = getNumber(jcszZzse.getEight()) + hk;
            jcszZzse.setEight(df.format(xs));
        }
        if (hkcx.equals("7")){
            xs = getNumber(jcszZzse.getSeven()) + hk;
            jcszZzse.setSeven(df.format(xs));
        }

        if (hkcx.equals("9")){
            xs = getNumber(jcszZzse.getNine()) + hk;
            jcszZzse.setNine(df.format(xs));
        }
        if (hkcx.equals("10")){
            xs = getNumber(jcszZzse.getTen()) + hk;
            jcszZzse.setTen(df.format(xs));
        }
        if (hkcx.equals("11")){
            xs = getNumber(jcszZzse.getEleven()) + hk;
            jcszZzse.setEleven(df.format(xs));
        }
        if (hkcx.equals("12")){
            xs = getNumber(jcszZzse.getTwelve()) + hk;
            jcszZzse.setTwelve(df.format(xs));
        }
        if (hkcx.equals("13")){
            xs = getNumber(jcszZzse.getThirteen()) + hk;
            jcszZzse.setThirteen(df.format(xs));
        }
        if (hkcx.equals("14")){
            xs = getNumber(jcszZzse.getFourteen()) + hk;
            jcszZzse.setFourteen(df.format(xs));
        }
        if (hkcx.equals("15")){
            xs = getNumber(jcszZzse.getFifteen()) + hk;
            jcszZzse.setFifteen(df.format(xs));
        }
        if (hkcx.equals("16")){
            xs = getNumber(jcszZzse.getSixteen()) + hk;
            jcszZzse.setSixteen(df.format(xs));
        }
        if (hkcx.equals("17")){
            xs = getNumber(jcszZzse.getSeventeen()) + hk;
            jcszZzse.setSeventeen(df.format(xs));
        }
        if (hkcx.equals("18")){
            xs = getNumber(jcszZzse.getEighteen()) + hk;
            jcszZzse.setEighteen(df.format(xs));
        }
        if (hkcx.equals("19")){
            xs = getNumber(jcszZzse.getNineteen()) + hk;
            jcszZzse.setNineteen(df.format(xs));
        }
        if (hkcx.equals("20")){
            xs = getNumber(jcszZzse.getTwenty()) + hk;
            jcszZzse.setTwenty(df.format(xs));
        }
        jcszZzseService.updateById(jcszZzse);
    }

    /**
     * Dscription: 删除一条借还记录
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 19:04
     */
    @RequestMapping(value = "deleteJh", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteJh(String ids, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.00");

        String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
            String id = idsArray[i];
            //首先拿到钱和资金来源
            ZzglJh zzglJh = zzglJhService.selectById(id);
            String money = zzglJh.getMoney();
            String zjcx = zzglJh.getZjzh();
            float moneyf = 0;
            if (!money.equals("")&&money!=null){
                moneyf = getNumber(money);
            }

            EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
            wrapper2.eq("TYPE", "2");
            JcszZzse jcszZzse = jcszZzseService.selectOne(wrapper2);
            float xs = 0;
            if (zjcx.equals("1")){
                xs = getNumber(jcszZzse.getOne()) + moneyf;
                jcszZzse.setOne(df.format(xs));
            }
            if (zjcx.equals("2")){
                xs = getNumber(jcszZzse.getTwo()) + moneyf;
                jcszZzse.setTwo(df.format(xs));
            }
            if (zjcx.equals("3")){
                xs = getNumber(jcszZzse.getThree()) + moneyf;
                jcszZzse.setThree(df.format(xs));
            }
            if (zjcx.equals("4")){
                xs = getNumber(jcszZzse.getFour()) + moneyf;
                jcszZzse.setFour(df.format(xs));
            }
            if (zjcx.equals("8")){
                xs = getNumber(jcszZzse.getEight()) + moneyf;
                jcszZzse.setEight(df.format(xs));
            }
            if (zjcx.equals("5")){
                xs = getNumber(jcszZzse.getFive()) + moneyf;
                jcszZzse.setFive(df.format(xs));
            }
            if (zjcx.equals("6")){
                xs = getNumber(jcszZzse.getSix()) + moneyf;
                jcszZzse.setSix(df.format(xs));
            }
            if (zjcx.equals("7")){
                xs = getNumber(jcszZzse.getSeven()) + moneyf;
                jcszZzse.setSeven(df.format(xs));
            }

            if (zjcx.equals("9")){
                xs = getNumber(jcszZzse.getNine()) + moneyf;
                jcszZzse.setNine(df.format(xs));
            }
            if (zjcx.equals("10")){
                xs = getNumber(jcszZzse.getTen()) + moneyf;
                jcszZzse.setTen(df.format(xs));
            }
            if (zjcx.equals("11")){
                xs = getNumber(jcszZzse.getEleven()) + moneyf;
                jcszZzse.setEleven(df.format(xs));
            }
            if (zjcx.equals("12")){
                xs = getNumber(jcszZzse.getTwelve()) + moneyf;
                jcszZzse.setTwelve(df.format(xs));
            }
            if (zjcx.equals("13")){
                xs = getNumber(jcszZzse.getThirteen()) + moneyf;
                jcszZzse.setThirteen(df.format(xs));
            }
            if (zjcx.equals("14")){
                xs = getNumber(jcszZzse.getFourteen()) + moneyf;
                jcszZzse.setFourteen(df.format(xs));
            }
            if (zjcx.equals("15")){
                xs = getNumber(jcszZzse.getFifteen()) + moneyf;
                jcszZzse.setFifteen(df.format(xs));
            }
            if (zjcx.equals("16")){
                xs = getNumber(jcszZzse.getSixteen()) + moneyf;
                jcszZzse.setSixteen(df.format(xs));
            }
            if (zjcx.equals("17")){
                xs = getNumber(jcszZzse.getSeventeen()) + moneyf;
                jcszZzse.setSeventeen(df.format(xs));
            }
            if (zjcx.equals("18")){
                xs = getNumber(jcszZzse.getEighteen()) + moneyf;
                jcszZzse.setEighteen(df.format(xs));
            }
            if (zjcx.equals("19")){
                xs = getNumber(jcszZzse.getNineteen()) + moneyf;
                jcszZzse.setNineteen(df.format(xs));
            }
            if (zjcx.equals("20")){
                xs = getNumber(jcszZzse.getTwenty()) + moneyf;
                jcszZzse.setTwenty(df.format(xs));
            }
            jcszZzseService.updateById(jcszZzse);

            //然后再删除
            zzglJhService.deleteById(id);

        }
    }

    /**
     * Dscription: 转到借还明细页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 20:35
     */
    @RequestMapping(value = "ckmx", method={RequestMethod.GET, RequestMethod.POST})
    public String ckmx(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        String name = zzglJhService.selectById(id).getName();
        model.addAttribute("name", name);
        EntityWrapper<ZzglJhmx> wrapper = new EntityWrapper<ZzglJhmx>();
        wrapper.eq("JHID", id);
        wrapper.orderBy("RQ", false);
        List<ZzglJhmx> zzglJhmxes = zzglJhmxService.selectList(wrapper);
        model.addAttribute("zzglJhmxes", zzglJhmxes);
        return display("ckmx");
    }

    public float getNumber(String number) throws ParseException {
        float d1 = new DecimalFormat().parse(number).floatValue();
        return d1;
    }

    public List<QyzjyDTO> getyxList(){

        //是否启用
        EntityWrapper<JcszZzse> wrapper0 = new EntityWrapper<JcszZzse>();
        wrapper0.eq("TYPE","3");
        JcszZzse sfqy = jcszZzseService.selectOne(wrapper0);

        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE","0");
        JcszZzse name = jcszZzseService.selectOne(wrapper1);

        List<QyzjyDTO> qyList = new ArrayList<QyzjyDTO>();
        if (sfqy.getOne().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("1");
            qyzjyDTO.setYwzjy("one");
            qyzjyDTO.setName(name.getOne());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getTwo().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("2");
            qyzjyDTO.setYwzjy("two");
            qyzjyDTO.setName(name.getTwo());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getThree().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("3");
            qyzjyDTO.setYwzjy("three");
            qyzjyDTO.setName(name.getThree());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getFour().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("4");
            qyzjyDTO.setYwzjy("four");
            qyzjyDTO.setName(name.getFour());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getFive().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("5");
            qyzjyDTO.setYwzjy("five");
            qyzjyDTO.setName(name.getFive());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getSix().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("6");
            qyzjyDTO.setYwzjy("six");
            qyzjyDTO.setName(name.getSix());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getSeven().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("7");
            qyzjyDTO.setYwzjy("seven");
            qyzjyDTO.setName(name.getSeven());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getEight().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("8");
            qyzjyDTO.setYwzjy("eight");
            qyzjyDTO.setName(name.getEight());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getNine().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("9");
            qyzjyDTO.setYwzjy("nine");
            qyzjyDTO.setName(name.getNine());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getTen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("10");
            qyzjyDTO.setYwzjy("ten");
            qyzjyDTO.setName(name.getTen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getEleven().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("11");
            qyzjyDTO.setYwzjy("eleven");
            qyzjyDTO.setName(name.getEleven());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getTwelve().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("12");
            qyzjyDTO.setYwzjy("twelve");
            qyzjyDTO.setName(name.getTwelve());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getThirteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("13");
            qyzjyDTO.setYwzjy("thirteen");
            qyzjyDTO.setName(name.getThirteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getFourteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("14");
            qyzjyDTO.setYwzjy("fourteen");
            qyzjyDTO.setName(name.getFourteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getFifteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("15");
            qyzjyDTO.setYwzjy("fifteen");
            qyzjyDTO.setName(name.getFifteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getSixteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("16");
            qyzjyDTO.setYwzjy("sixteen");
            qyzjyDTO.setName(name.getSixteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getSeventeen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("17");
            qyzjyDTO.setYwzjy("seventeen");
            qyzjyDTO.setName(name.getSeventeen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getEighteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("18");
            qyzjyDTO.setYwzjy("eighteen");
            qyzjyDTO.setName(name.getEighteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getNineteen().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("19");
            qyzjyDTO.setYwzjy("nineteen");
            qyzjyDTO.setName(name.getNineteen());
            qyList.add(qyzjyDTO);
        }
        if (sfqy.getTwenty().equals("1")){
            QyzjyDTO qyzjyDTO = new QyzjyDTO();
            qyzjyDTO.setZjy("20");
            qyzjyDTO.setYwzjy("twenty");
            qyzjyDTO.setName(name.getTwenty());
            qyList.add(qyzjyDTO);
        }
        return qyList;
    }

}
