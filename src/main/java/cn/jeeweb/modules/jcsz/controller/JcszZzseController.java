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

    }

    /**
     * Dscription: 保存资金数额信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 10:34
     */
    @RequestMapping(value = "saveZzse", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveZzse(String name1, String name2, String name3, String name4, String name5, String name6, String name7, String name8, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, HttpServletRequest request, HttpServletResponse response, Model model){
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
            jcszZzseValue.setType("1");
            jcszZzseService.updateById(jcszZzseValue);
        }
    }

}
