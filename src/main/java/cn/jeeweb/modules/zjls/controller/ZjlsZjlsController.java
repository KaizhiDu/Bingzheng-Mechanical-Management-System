package cn.jeeweb.modules.zjls.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.zjls.dto.ZjlsValueDTO;
import cn.jeeweb.modules.zjls.entity.ZjlsZj;
import cn.jeeweb.modules.zjls.entity.ZjlsZjls;
import cn.jeeweb.modules.zjls.service.ZjlsZjService;
import cn.jeeweb.modules.zjls.service.ZjlsZjlsService;
import cn.jeeweb.modules.zzgl.entity.ZzglZzgl;
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
import java.util.Date;

/**
 * Dscription: 资金流水
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

        //查看有没有资金信息
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        int count = zjlsZjService.selectCount(wrapper);
        if(count==0){
            ZjlsZj zjlsZj = new ZjlsZj();
            zjlsZj.setOne("0");
            zjlsZj.setTwo("0");
            zjlsZjService.insert(zjlsZj);
        }
        //ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);

    }

    /**
     * Dscription: 静态改变各个资金源的数值
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/6 15:36
     */
    @RequestMapping(value = "changeValue", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ZjlsValueDTO changeValue(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        ZjlsValueDTO zjlsValueDTO = new ZjlsValueDTO();
        //查看有没有资金信息
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        int count = zjlsZjService.selectCount(wrapper);
        if(count==0){
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
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 15:53
     */
    @RequestMapping(value = "sr", method={RequestMethod.GET, RequestMethod.POST})
    public String sr(HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("day", currentDate);
        return display("sr");
    }

    /**
     * Dscription: 清空数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 16:20
     */
    @RequestMapping(value = "qksj", method={RequestMethod.GET, RequestMethod.POST})
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
         * @author : Kevin Du
         * @version : 1.0
         * @date : 2019/1/5 13:10
         */
    @RequestMapping(value = "saveSr", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveSr(String mx, String money, String lx, String jtsj, String zjly, String mx2, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        if (money==null){
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
        if (zjly.equals("1")){
            zjlsZjls.setOne(money);
        }
        else{
            zjlsZjls.setTwo(money);
        }
        zjlsZjls.setN(n);
        zjlsZjls.setY(y);
        zjlsZjls.setR(r);
        zjlsZjls.setRq(rq);
        zjlsZjls.setLx(lx);
        zjlsZjls.setMx(mx);
        zjlsZjls.setMx2(mx2);

        zjlsZjlsService.insert(zjlsZjls);

        //还要改变资金数额
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        if (zjly.equals("1")){
            double oldOne = getNumber(zjlsZj.getOne());
            oldOne = oldOne + moneyd;
            zjlsZj.setOne(df.format(oldOne));
        }else{
            double oldTwo = getNumber(zjlsZj.getTwo());
            oldTwo = oldTwo + moneyd;
            zjlsZj.setTwo(df.format(oldTwo));
        }
        zjlsZjService.updateById(zjlsZj);
    }

    /**
     * Dscription: 展示所有资金流水信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 16:34
     */
    @RequestMapping(value = "ajaxZjlsList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ZjlsZjls> ajaxZzglList(String n, String y, String lx, String r, String mx2, Queryable queryable, HttpServletRequest request, HttpServletResponse response, Model model){
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

        if (n==null){
            zjlsZjls.setN(nn+"");
        }
        if (y==null){
            zjlsZjls.setY(yy+"");
        }
        if (r==null){
            zjlsZjls.setR(rr+"");
            }
        PageJson<ZjlsZjls> pageJson = zjlsZjlsService.ajaxZjlsList(queryable,zjlsZjls);
        return pageJson;
        }

        /**
         * Dscription: 转到支出页面
         * @author : Kevin Du
         * @version : 1.0
         * @date : 2019/2/18 19:11
         */
        @RequestMapping(value = "zc", method={RequestMethod.GET, RequestMethod.POST})
        public String zc(HttpServletRequest request, HttpServletResponse response, Model model){
            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
            Date date0 = new Date();
            String currentDate = sdf0.format(date0);
            model.addAttribute("day", currentDate);
            return display("zc");
        }

        /**
         * Dscription: 保存支出
         * @author : Kevin Du
         * @version : 1.0
         * @date : 2019/2/18 19:19
         */
    @RequestMapping(value = "saveZc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZc(String mx, String money, String lx, String jtsj, String zjly, String mx2, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        if (money==null){
            money = "0";
        }else{
            money = "-"+money;
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
        if (zjly.equals("1")){
            zjlsZjls.setOne(money);
        }
        else{
            zjlsZjls.setTwo(money);
        }
        zjlsZjls.setN(n);
        zjlsZjls.setY(y);
        zjlsZjls.setR(r);
        zjlsZjls.setRq(rq);
        zjlsZjls.setLx(lx);
        zjlsZjls.setMx(mx);
        zjlsZjls.setMx2(mx2);

        zjlsZjlsService.insert(zjlsZjls);

        //还要改变资金数额
        EntityWrapper<ZjlsZj> wrapper = new EntityWrapper<ZjlsZj>();
        ZjlsZj zjlsZj = zjlsZjService.selectOne(wrapper);
        if (zjly.equals("1")){
            double oldOne = getNumber(zjlsZj.getOne());
            oldOne = oldOne + moneyd;
            zjlsZj.setOne(df.format(oldOne));
        }else{
            double oldTwo = getNumber(zjlsZj.getTwo());
            oldTwo = oldTwo + moneyd;
            zjlsZj.setTwo(df.format(oldTwo));
        }
        zjlsZjService.updateById(zjlsZj);
    }

    /**
     * Dscription: 删除资金流水
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/2/18 19:57
     */
    @RequestMapping(value = "deleteZjls", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteZjls(String ids, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String idsArray[] = ids.split(",");
        for (int i = 0; i <idsArray.length ; i++) {
            String id = idsArray[i];
            ZjlsZjls zjlsZjls = zjlsZjlsService.selectById(id);
            double one = 0;
            double two = 0;
            if (zjlsZjls.getOne()!=null){
                one = getNumber(zjlsZjls.getOne());
            }
            if (zjlsZjls.getTwo()!=null){
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
