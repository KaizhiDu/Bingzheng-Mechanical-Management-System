package cn.jeeweb.modules.jcsz.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.jcsz.entity.JcszZzse;
import cn.jeeweb.modules.jcsz.service.IJcszZzseService;
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
 * Dscription: 基础数据 - 资金数额
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/3 15:38
 */
@Controller
@RequestMapping("${admin.url.prefix}/jcsz/zzse")
@RequiresPathPermission("jcsz:zzse")
public class JcszZzseController extends BaseCRUDController<JcszZzse, String> {

    /**基础设置资金数额Service*/
    @Autowired
    private IJcszZzseService jcszZzseService;

    /**
     * Dscription: 搜索项和前置内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:15
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
//        //得到当前年月
//        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
//        Date date0 = new Date();
//        String currentDate = sdf0.format(date0);
//        String[] dateArray = currentDate.split("-");
//        int nd = Integer.parseInt(dateArray[0]);
//        int yf = Integer.parseInt(dateArray[1]);
//        model.addAttribute("nd", nd);
//        model.addAttribute("yf", yf);
        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE", "0");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper1);
        model.addAttribute("name", jcszZzseName);

        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "1");
        JcszZzse jcszZzseValue = jcszZzseService.selectOne(wrapper2);
        model.addAttribute("value", jcszZzseValue);
        //是否启用
        EntityWrapper<JcszZzse> wrapper0 = new EntityWrapper<JcszZzse>();
        wrapper0.eq("TYPE","3");
        JcszZzse sfqy = jcszZzseService.selectOne(wrapper0);
        model.addAttribute("sfqy", sfqy);

        EntityWrapper<JcszZzse> wrapper3 = new EntityWrapper<JcszZzse>();
        wrapper3.eq("TYPE", "4");
        JcszZzse jcszZzseSort = jcszZzseService.selectOne(wrapper3);
        model.addAttribute("sort", jcszZzseSort);

    }

    /**
     * Dscription: 保存资金数额信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 10:34
     */
    @RequestMapping(value = "saveZzse", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZzse(String name1, String name2, String name3, String name4, String name5, String name6, String name7, String name8, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String name9, String name10, String name11, String name12, String value9, String value10, String value11, String value12, String value13, String value14, String value15, String value16, String value17, String value18, String value19, String value20, String name13, String name14, String name15, String name16, String name17, String name18, String name19, String name20,  String sf1, String sf2, String sf3, String sf4, String sf5, String sf6, String sf7, String sf8, String sf9, String sf10, String sf11, String sf12, String sf13, String sf14, String sf15, String sf16, String sf17, String sf18, String sf19, String sf20, String px1, String px2, String px3, String px4, String px5, String px6, String px7, String px8, String px9, String px10, String px11, String px12, String px13, String px14, String px15, String px16, String px17, String px18, String px19, String px20, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        EntityWrapper<JcszZzse> wrapper = new EntityWrapper<JcszZzse>();
        wrapper.eq("TYPE", "4");
        JcszZzse sort = jcszZzseService.selectOne(wrapper);
        sort.setOne(px1);
        sort.setTwo(px2);
        sort.setThree(px3);
        sort.setFour(px4);
        sort.setFive(px5);
        sort.setSix(px6);
        sort.setSeven(px7);
        sort.setEight(px8);
        sort.setNine(px9);
        sort.setTen(px10);
        sort.setEleven(px11);
        sort.setTwelve(px12);
        sort.setThirteen(px13);
        sort.setFourteen(px14);
        sort.setFifteen(px15);
        sort.setSixteen(px16);
        sort.setSeventeen(px17);
        sort.setEighteen(px18);
        sort.setNineteen(px19);
        sort.setTwenty(px20);
        jcszZzseService.updateById(sort);


        EntityWrapper<JcszZzse> wrapper0 = new EntityWrapper<JcszZzse>();
        wrapper0.eq("TYPE", "3");
        JcszZzse sfqy = jcszZzseService.selectOne(wrapper0);
        sfqy.setOne(sf1);
        sfqy.setTwo(sf2);
        sfqy.setThree(sf3);
        sfqy.setFour(sf4);
        sfqy.setFive(sf5);
        sfqy.setSix(sf6);
        sfqy.setSeven(sf7);
        sfqy.setEight(sf8);
        sfqy.setNine(sf9);
        sfqy.setTen(sf10);
        sfqy.setEleven(sf11);
        sfqy.setTwelve(sf12);
        sfqy.setThirteen(sf13);
        sfqy.setFourteen(sf14);
        sfqy.setFifteen(sf15);
        sfqy.setSixteen(sf16);
        sfqy.setSeventeen(sf17);
        sfqy.setEighteen(sf18);
        sfqy.setNineteen(sf19);
        sfqy.setTwenty(sf20);
        jcszZzseService.updateById(sfqy);

        DecimalFormat df = new DecimalFormat("#,###.00");
        if (!value1.equals("")&&value1!=null){
            value1 = df.format(getNumber(value1));
        }
        if (!value2.equals("")&&value2!=null){
            value2 = df.format(getNumber(value2));
        }
        if (!value3.equals("")&&value3!=null){
            value3 = df.format(getNumber(value3));
        }
        if (!value4.equals("")&&value4!=null){
            value4 = df.format(getNumber(value4));
        }
        if (!value5.equals("")&&value5!=null){
            value5 = df.format(getNumber(value5));
        }
        if (!value6.equals("")&&value6!=null){
            value6 = df.format(getNumber(value6));
        }
        if (!value7.equals("")&&value7!=null){
            value7 = df.format(getNumber(value7));
        }
        if (!value8.equals("")&&value8!=null){
            value8 = df.format(getNumber(value8));
        }
        if (!value9.equals("")&&value9!=null){
            value9 = df.format(getNumber(value9));
        }
        if (!value10.equals("")&&value10!=null){
            value10 = df.format(getNumber(value10));
        }
        if (!value11.equals("")&&value11!=null){
            value11 = df.format(getNumber(value11));
        }
        if (!value12.equals("")&&value12!=null){
            value12 = df.format(getNumber(value12));
        }
        if (!value13.equals("")&&value13!=null){
            value13 = df.format(getNumber(value13));
        }
        if (!value14.equals("")&&value14!=null){
            value14 = df.format(getNumber(value14));
        }
        if (!value15.equals("")&&value15!=null){
            value15 = df.format(getNumber(value15));
        }
        if (!value16.equals("")&&value16!=null){
            value16 = df.format(getNumber(value16));
        }
        if (!value17.equals("")&&value17!=null){
            value17 = df.format(getNumber(value17));
        }
        if (!value18.equals("")&&value18!=null){
            value18 = df.format(getNumber(value18));
        }
        if (!value19.equals("")&&value19!=null){
            value19 = df.format(getNumber(value19));
        }
        if (!value20.equals("")&&value20!=null){
            value20 = df.format(getNumber(value20));
        }


        EntityWrapper<JcszZzse> wrapper1 = new EntityWrapper<JcszZzse>();
        wrapper1.eq("TYPE", "0");
        EntityWrapper<JcszZzse> wrapper2 = new EntityWrapper<JcszZzse>();
        wrapper2.eq("TYPE", "1");
        JcszZzse jcszZzseName = jcszZzseService.selectOne(wrapper1);
        if (jcszZzseName==null){
            //添加
            JcszZzse j = new JcszZzse();
            j.setOne(name1);
            j.setTwo(name2);
            j.setThree(name3);
            j.setFour(name4);
            j.setFive(name5);
            j.setSix(name6);
            j.setSeven(name7);
            j.setEight(name8);
            j.setNine(name9);
            j.setTen(name10);
            j.setEleven(name11);
            j.setTwelve(name12);
            j.setThirteen(name13);
            j.setFourteen(name14);
            j.setFifteen(name15);
            j.setSixteen(name16);
            j.setSeventeen(name17);
            j.setEighteen(name18);
            j.setNineteen(name19);
            j.setTwenty(name20);
            j.setType("0");
            jcszZzseService.insert(j);
        }
        else {
            //更新
            jcszZzseName.setOne(name1);
            jcszZzseName.setTwo(name2);
            jcszZzseName.setThree(name3);
            jcszZzseName.setFour(name4);
            jcszZzseName.setFive(name5);
            jcszZzseName.setSix(name6);
            jcszZzseName.setSeven(name7);
            jcszZzseName.setEight(name8);
            jcszZzseName.setNine(name9);
            jcszZzseName.setTen(name10);
            jcszZzseName.setEleven(name11);
            jcszZzseName.setTwelve(name12);
            jcszZzseName.setThirteen(name13);
            jcszZzseName.setFourteen(name14);
            jcszZzseName.setFifteen(name15);
            jcszZzseName.setSixteen(name16);
            jcszZzseName.setSeventeen(name17);
            jcszZzseName.setEighteen(name18);
            jcszZzseName.setNineteen(name19);
            jcszZzseName.setTwenty(name20);
            jcszZzseName.setType("0");
            jcszZzseService.updateById(jcszZzseName);
        }

        JcszZzse jcszZzseValue = jcszZzseService.selectOne(wrapper2);
        if (jcszZzseValue==null){
            //添加
            JcszZzse j = new JcszZzse();
            j.setOne(value1);
            j.setTwo(value2);
            j.setThree(value3);
            j.setFour(value4);
            j.setFive(value5);
            j.setSix(value6);
            j.setSeven(value7);
            j.setEight(value8);
            j.setNine(value9);
            j.setTen(value10);
            j.setEleven(value11);
            j.setTwelve(value12);
            j.setThirteen(value13);
            j.setFourteen(value14);
            j.setFifteen(value15);
            j.setSixteen(value16);
            j.setSeventeen(value17);
            j.setEighteen(value18);
            j.setNineteen(value19);
            j.setTwenty(value20);
            j.setType("1");
            jcszZzseService.insert(j);
        }
        else {
            //更新
            jcszZzseValue.setOne(value1);
            jcszZzseValue.setTwo(value2);
            jcszZzseValue.setThree(value3);
            jcszZzseValue.setFour(value4);
            jcszZzseValue.setFive(value5);
            jcszZzseValue.setSix(value6);
            jcszZzseValue.setSeven(value7);
            jcszZzseValue.setEight(value8);
            jcszZzseValue.setNine(value9);
            jcszZzseValue.setTen(value10);
            jcszZzseValue.setEleven(value11);
            jcszZzseValue.setTwelve(value12);
            jcszZzseValue.setThirteen(value13);
            jcszZzseValue.setFourteen(value14);
            jcszZzseValue.setFifteen(value15);
            jcszZzseValue.setSixteen(value16);
            jcszZzseValue.setSeventeen(value17);
            jcszZzseValue.setEighteen(value18);
            jcszZzseValue.setNineteen(value19);
            jcszZzseValue.setTwenty(value20);
            jcszZzseValue.setType("1");
            jcszZzseService.updateById(jcszZzseValue);
        }
    }

    public float getNumber(String number) throws ParseException {
        float d1 = new DecimalFormat().parse(number).floatValue();
        return d1;
    }

    /**
     * Dscription: 得到是否启动数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/28 22:41
     */
    @RequestMapping(value = "getSfqy", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JcszZzse getSfqy(Model model, HttpServletRequest request, HttpServletResponse response){
        //是否启用
        EntityWrapper<JcszZzse> wrapper0 = new EntityWrapper<JcszZzse>();
        wrapper0.eq("TYPE","3");
        JcszZzse sfqy = jcszZzseService.selectOne(wrapper0);
       return sfqy;
    }

}
