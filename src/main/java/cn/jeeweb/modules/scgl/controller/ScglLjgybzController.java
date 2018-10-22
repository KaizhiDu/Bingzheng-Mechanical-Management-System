package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.dto.GydlbzDTO;
import cn.jeeweb.modules.scgl.dto.GyxlbzDTO;
import cn.jeeweb.modules.scgl.entity.*;
import cn.jeeweb.modules.scgl.service.*;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
* @Description:    生产管理-零件工艺编制
* @Author:         杜凯之
* @CreateDate:     2018/9/14 15:58
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scgl/ljgybz")
@RequiresPathPermission("scgl:ljgybz")
public class ScglLjgybzController extends BaseCRUDController<ScglLjgybz, String> {

    /**计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**工艺大类编制*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**大类模板*/
    @Autowired
    private IScglGymbszService scglGymbszService;

    /**小类模板*/
    @Autowired
    private IScglGymbxlszService scglGymbxlszService;

    @Autowired
    /**工艺小类*/
    private IScglSzgyxlService scglSzgyxlService;

    @Autowired
    /**零件管理*/
    private IScjhglLjglService scjhglLjglService;

    @Autowired
    /**零件工艺编制*/
    private IScglLjgybzService scglLjgybzService;

    /**
    * @Description:    搜索项
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 16:28
    * @Version:        1.0
    */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("HTBH");
        List<ScjhglHtgl> jhList = scjhglHtglService.selectList(wrapper);
        model.addAttribute("jhList",jhList);
    }

    /**
    * @Description:    展示所有计划信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 17:01
    * @Version:        1.0
    */
    @RequestMapping(value = "ajaxJhglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglHtgl> ajaxJhglList(Queryable queryable, ScjhglHtgl scjhglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglHtgl> pageJson = scjhglHtglService.ajaxJhglList(queryable,scjhglHtgl);
        return pageJson;
    }

    /**
     * Dscription: 展示所有零件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/17 23:17
     */
    @RequestMapping(value = "ajaxlbjglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglLjgl> ajaxlbjglList(Queryable queryable, ScjhglLjgl scjhglLjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglLjgl> pageJson = scjhglLjglService.ajaxlbjglList(queryable,scjhglLjgl);
        return pageJson;
    }

    /**
    * @Description:    跳转到编制工艺页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 17:32
    * @Version:        1.0
    */
    @RequestMapping(value = "bzgydl", method={RequestMethod.GET, RequestMethod.POST})
    public String bzgydl(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        //这个id为零件id

        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        String mc = "";
        if (scjhglLjgl.getSfsbj().equals("0")){
            mc = "零件";
        }
        if (scjhglLjgl.getSfsbj().equals("1")){
            mc = "部件";
        }
        model.addAttribute("mc", mc);
        model.addAttribute("scjhglLjgl", scjhglLjgl);
        return display("bzgydl");
    }

    /**
    * @Description:    编制工艺大类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/14 18:20
    * @Version:        1.0
    */
    @RequestMapping(value = "addGydl", method={RequestMethod.GET, RequestMethod.POST})
    public String addGydl(String ljid, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("ljid", ljid);
        return display("addGydl");
    }

    /**
     * Dscription: 编制工艺大类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/15 1:19
     */
     @RequestMapping(value = "saveGydlbz", method={RequestMethod.GET, RequestMethod.POST})
     @ResponseBody
    public AjaxJson saveGydlbz(String ljid, String ids, HttpServletRequest request, HttpServletResponse response, Model model){
         AjaxJson ajaxJson = new AjaxJson();
         String idsArray[] = ids.split(",");
         for (int i=0;i<idsArray.length;i++){
             EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
             wrapper.eq("LJID",ljid);
             int count = scglGydlbzService.selectCount(wrapper);
             int px = count+1;
             ScglGydlbz s = new ScglGydlbz();
             s.setPx(px);
             s.setLjid(ljid);
             s.setGydlid(idsArray[i]);
             //先查数据库里面该大类名称
             ScglGymbsz scglGymbsz = scglGymbszService.selectById(idsArray[i]);
             //然后查插入表里面该大类的数量
             EntityWrapper<ScglGydlbz> wrapper2 = new EntityWrapper<ScglGydlbz>();
             wrapper2.eq("GYDLID",idsArray[i]);
             wrapper2.eq("LJID",ljid);
             int count2 = scglGydlbzService.selectCount(wrapper2);
             //没有该工艺大类
             if (count2==0){
                 s.setGydlmc(scglGymbsz.getGydlmc());
             }
             else{
                 String gydlmc = scglGymbsz.getGydlmc()+(count2+1)+"";
                 s.setGydlmc(gydlmc);
             }
             scglGydlbzService.insert(s);
         }
         ajaxJson.setMsg("添加成功！！！");
         return ajaxJson;
     }

     /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
     @RequestMapping(value = "ajaxGydlbzList", method={RequestMethod.GET, RequestMethod.POST})
     @ResponseBody
     public PageJson<GydlbzDTO> ajaxGydlbzList(Queryable queryable, GydlbzDTO gydlbzDTO, HttpServletRequest request, HttpServletResponse response, Model model){
         PageJson<GydlbzDTO> pageJson = scglGydlbzService.ajaxGydlbzList(queryable,gydlbzDTO);
         return pageJson;
     }

     /**
     * @Description:    根据工艺大类编制ID获取所有小类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 16:24
     * @Version:        1.0
     */
     @RequestMapping(value = "ajaxGyxlbzList", method={RequestMethod.GET, RequestMethod.POST})
     @ResponseBody
     public PageJson<ScglLjgybz> ajaxGyxlbzList(String gydlbzid,Queryable queryable, ScglLjgybz scglLjgybz, HttpServletRequest request, HttpServletResponse response, Model model){
         PageJson<ScglLjgybz> pageJson = scglLjgybzService.ajaxGyxlbzList(queryable,scglLjgybz,gydlbzid);
         return pageJson;
     }

     /**
     * @Description:    转到设置大类排序页面
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 14:16
     * @Version:        1.0
     */
     @RequestMapping(value = "szdlpx", method={RequestMethod.GET, RequestMethod.POST})
     public String szdlpx(String ljid, HttpServletRequest request, HttpServletResponse response, Model model){
         //得到所有改零件的大类信息
         EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
         wrapper.eq("LJID", ljid);
         wrapper.orderBy("PX");
         List<ScglGydlbz> gydlbzsList = scglGydlbzService.selectList(wrapper);
         model.addAttribute("gydlbzsList", gydlbzsList);
         return display("szdlpx");
     }

     /**
     * @Description:    转到设置小类排序页面
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 17:03
     * @Version:        1.0
     */
     @RequestMapping(value = "szxlpx", method={RequestMethod.GET, RequestMethod.POST})
     public String szxlpx(String gydlbzid, HttpServletRequest request, HttpServletResponse response, Model model){
         //得到所有改零件的大类信息
         EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
         wrapper.eq("GYDLBZID", gydlbzid);
         wrapper.orderBy("PX");
         List<ScglLjgybz> gyxlbzsList = scglLjgybzService.selectList(wrapper);
         model.addAttribute("gyxlbzsList", gyxlbzsList);
         return display("szxlpx");
     }


     /**
     * @Description:    修改大类排序
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 14:07
     * @Version:        1.0
     */
    @RequestMapping(value = "savePx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void savePx(String id, String px, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglGydlbz scglGydlbz = scglGydlbzService.selectById(id);
        int px2 = Integer.parseInt(px);
        scglGydlbz.setPx(px2);
        scglGydlbzService.updateById(scglGydlbz);
    }

    /**
    * @Description:    转到零件工艺编制页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/17 15:26
    * @Version:        1.0
    */
    @RequestMapping(value = "bzgyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String bzgyxl(String id ,String ljid, HttpServletRequest request, HttpServletResponse response, Model model){
        //零件名称
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
        model.addAttribute("jhxx",scjhglLjgl);
        //计划大类编制信息
        ScglGydlbz gydlbz = scglGydlbzService.selectById(id);
        model.addAttribute("gydlbz", gydlbz);
        return display("bzgyxl");
    }

    /**
    * @Description:    转到添加零件工艺编制页面
    * @Author:         杜凯之
    * @CreateDate:     2018/9/17 16:21
    * @Version:        1.0
    */
    @RequestMapping(value = "addGyxl", method={RequestMethod.GET, RequestMethod.POST})
    public String addGyxl(String gydlbzid, HttpServletRequest request, HttpServletResponse response, Model model){
        ScglGydlbz scglGydlbz = scglGydlbzService.selectById(gydlbzid);
        String dlid = scglGydlbz.getGydlid();
       List<ScglSzgyxl> gyxlList = scglSzgyxlService.getXlList(dlid);
        model.addAttribute("gyxlList", gyxlList);
        //工艺大类编制ID
        model.addAttribute("gydlbzid", gydlbzid);
        return display("addGyxl");
    }

    /**
    * @Description:    保存大类排序
    * @Author:         杜凯之
    * @CreateDate:     2018/9/18 14:38
    * @Version:        1.0
    */
    @RequestMapping(value = "saveDlpx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveDlpx(String list, HttpServletRequest request, HttpServletResponse response, Model model){
        String dlpx[] = list.split(",");
        for (int i=0;i<dlpx.length;i++){
            String id = dlpx[i].split("-")[0];
            int px = Integer.parseInt(dlpx[i].split("-")[1]);
            ScglGydlbz scglGydlbz = scglGydlbzService.selectById(id);
            scglGydlbz.setPx(px);
            scglGydlbzService.updateById(scglGydlbz);
        }
    }

    /**
    * @Description:    保存小类排序
    * @Author:         杜凯之
    * @CreateDate:     2018/9/18 17:07
    * @Version:        1.0
    */
    @RequestMapping(value = "saveXlpx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveXlpx(String list, HttpServletRequest request, HttpServletResponse response, Model model){
        String xlpx[] = list.split(",");
        for (int i=0;i<xlpx.length;i++){
            String id = xlpx[i].split("-")[0];
            int px = Integer.parseInt(xlpx[i].split("-")[1]);
            ScglLjgybz scglLjgybz = scglLjgybzService.selectById(id);
            scglLjgybz.setPx(px);
            scglLjgybzService.updateById(scglLjgybz);
        }
    }

    /**
    * @Description:    保存零件工艺小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/18 15:25
    * @Version:        1.0
    */
    @RequestMapping(value = "saveGyxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGyxl(String gyxlid, String gydlbzid, String ms, HttpServletResponse response, HttpServletRequest request, Model model){
        //要保存的信息
        ScglLjgybz s = new ScglLjgybz();
        s.setGydlbzid(gydlbzid);
        s.setGyxlid(gyxlid);
        s.setMs(ms);
        //现在处理工艺小类名称
        ScglGymbxlsz scglGymbxlsz = scglGymbxlszService.selectById(gyxlid);
        //然后根据gydlbzid和gyxlid，计算现在有几个一样的数据
        EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
        wrapper.eq("GYDLBZID", gydlbzid);
        wrapper.eq("GYXLID", gyxlid);
        int count = scglLjgybzService.selectCount(wrapper);
        if (count==0){
            s.setGyxlmc(scglGymbxlsz.getGyxlmc());
        }
        else{
            s.setGyxlmc(scglGymbxlsz.getGyxlmc()+""+(count+1));
        }
        //现在处理排序
        EntityWrapper<ScglLjgybz> wrapper2 = new EntityWrapper<ScglLjgybz>();
        wrapper2.eq("GYDLBZID", gydlbzid);
        int count2 = scglLjgybzService.selectCount(wrapper2);
        s.setPx(count2+1);

        //添加工序的数量和剩余数量
        ScglGydlbz scglGydlbz = scglGydlbzService.selectById(gydlbzid);
        String ljid = scglGydlbz.getLjid();
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
        int sl = Integer.parseInt(scjhglLjgl.getSl());
        s.setSl(sl);
        s.setSysl(sl);
        s.setWrksl(sl);
        s.setScsfxs("1");
        s.setJhscsl(0);

        //可以插入了
        scglLjgybzService.insert(s);
    }

    /**
    * @Description:    删除编制大类，并且删除对应小类
    * @Author:         杜凯之
    * @CreateDate:     2018/9/18 17:29
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteGydl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteGydl(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String id = idsArray[i];
            //首先删除小类下面所有相关联的数据
            EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
            wrapper.eq("GYDLBZID", id);
            scglLjgybzService.delete(wrapper);
            //然后该删除大类
            scglGydlbzService.deleteById(id);
        }
    }
}
