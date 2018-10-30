package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjzc;
import cn.jeeweb.modules.scjhgl.entity.ScjhglFzjs;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjzcService;
import cn.jeeweb.modules.scjhgl.service.IScjhglFzjsService;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
* @Description:    生产计划管理-合同管理
* @Author:         杜凯之
* @CreateDate:     2018/9/12 14:22
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/htgl")
@RequiresPathPermission("scjhgl:htgl")
public class ScjhglHtglController extends BaseCRUDController<ScjhglHtgl, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**零件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**复制计数Service*/
    @Autowired
    private IScjhglFzjsService scjhglFzjsService;

    /**部件组成Service*/
    @Autowired
    private IScjhglBjzcService scjhglBjzcService;

    /**
    * @Description:    展示所有合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:42
    * @Version:        1.0
    */
    @RequestMapping(value = "createHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String creatHt(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }

    /**
    * @Description:    添加合同
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:43saveHt
    * @Version:        1.0
    */
    @RequestMapping(value = "saveHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveHt(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglHtgl scjhglHtgl){
        if (scjhglHtgl.getId().equals("")){
            scjhglHtgl.setId(null);
        }
        if (scjhglHtgl.getId()==null){
            scjhglHtglService.insert(scjhglHtgl);
        }
        else{
            scjhglHtglService.updateById(scjhglHtgl);
        }
    }

    /**
    * @Description:    更新合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:04
    * @Version:        1.0
    */
    @RequestMapping(value = "updateHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateHt(String id ,HttpServletResponse response, HttpServletRequest request, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl", scjhglHtgl);
        return display("update");
    }

    /**
    * @Description:    删除
    * @Author:         杜凯之
    * @CreateDate:     2018/9/13 14:03
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteHt(String id , HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
            //计划ID
            String jhid = id;
            //首先要判断该计划ID下的所有工艺是否还有剩余
            Boolean flag = false;
            List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(jhid);
            if (ljgybzByJhid!=null){
                for (ScglLjgybz s: ljgybzByJhid) {
                    int sysl = s.getSysl();
                    if (sysl>0){
                        flag = true;
                        break;
                    }
                }
            }
            //如果flag为假，就可以删除
            if (!flag){
                //先删除零件工艺编制信息
                if (ljgybzByJhid!=null){
                    for (ScglLjgybz s: ljgybzByJhid) {
                        scglLjgybzService.deleteById(s.getId());
                    }
                }

                //然后删除工艺大类信息
                List<ScglGydlbz> gydlbzByjhid = scglGydlbzService.getGydlbzByjhid(jhid);
                if (gydlbzByjhid!=null){
                    for (ScglGydlbz s: gydlbzByjhid) {
                        scglGydlbzService.deleteById(s.getId());
                    }
                }

                //再删除零件信息
                EntityWrapper<ScjhglLjgl> wrapper = new EntityWrapper<ScjhglLjgl>();
                wrapper.eq("HTID",jhid);
                List<ScjhglLjgl> scjhglLjgls = scjhglLjglService.selectList(wrapper);
                if (scjhglLjgls!=null){
                    scjhglLjglService.delete(wrapper);
                }

                //最后删除计划信息
                scjhglHtglService.deleteById(jhid);
                ajaxJson.setMsg("删除成功");
            }
            else{
                ajaxJson.setMsg("该计划未完成无法删除");
            }
        return ajaxJson;
    }

    /**
     * Dscription: 转到复制计划页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:49
     */
    @RequestMapping(value = "copyHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String copyHt(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl",scjhglHtgl);
        return display("copy");
    }

    /**
     * Dscription: 创建复制内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:55
     */
    @RequestMapping(value = "saveCopy",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveCopy(ScjhglHtgl scjhglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){

        String xjhsl = scjhglHtgl.getSl();
        int xjhsli = 0;
        if (xjhsl!=null&&!xjhsl.equals("")){
            xjhsli = Integer.parseInt(xjhsl);
        }
        //得到原计划ID
        String yjhid = scjhglHtgl.getId();
        //先找到原计划的计划名称
        String jhbh = scjhglHtglService.selectById(yjhid).getHtbh();
        //找复制计数里面有没有该计划名称的记录
        EntityWrapper<ScjhglFzjs> wrapper = new EntityWrapper<ScjhglFzjs>();
        wrapper.eq("JHBH", jhbh);
        //这个count就是记录本次复制的计数器
        int count = 0;
        int c = scjhglFzjsService.selectCount(wrapper);
        //得到count并且加一
        if (c>0){
            ScjhglFzjs scjhglFzjs = scjhglFzjsService.selectOne(wrapper);
            count = scjhglFzjs.getCount()+1;
            scjhglFzjs.setCount(count);
            scjhglFzjsService.updateById(scjhglFzjs);
        }
        //否则新建一条记录，count设置为1
        else{
            count = 1;
            ScjhglFzjs scjhglFzjs = new ScjhglFzjs();
            scjhglFzjs.setCount(count);
            scjhglFzjs.setJhbh(jhbh);
            scjhglFzjsService.insert(scjhglFzjs);
        }
        //现在需要把得到的count加到所有需要添加的ID后

        //先创建新的计划数据
        ScjhglHtgl newScjhglHtgl = new ScjhglHtgl();
        newScjhglHtgl.setId(scjhglHtgl.getId()+"-"+count);
        newScjhglHtgl.setHtbh(scjhglHtgl.getHtbh());
        newScjhglHtgl.setMs(scjhglHtgl.getMs());
        newScjhglHtgl.setSl(scjhglHtgl.getSl());
        scjhglHtglService.insert(newScjhglHtgl);

        //然后复制零件工艺编制的数据
        List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(yjhid);
        for (ScglLjgybz scglLjgybz : ljgybzByJhid) {
            ScglLjgybz s = new ScglLjgybz();
           String xid = scglLjgybz.getId()+"-"+count;
           String xGydlbzid = scglLjgybz.getGydlbzid()+"-"+count;
           s.setId(xid);
           s.setGydlbzid(xGydlbzid);
           s.setGyxlid(scglLjgybz.getGyxlid());
           s.setGyxlmc(scglLjgybz.getGyxlmc());
           s.setMs(scglLjgybz.getMs());
           s.setPx(scglLjgybz.getPx());
           s.setSl(scglLjgybz.getSl());
           s.setWrksl(scglLjgybz.getSl());
           s.setSysl(scglLjgybz.getSl());
           s.setJhscsl(0);
           s.setScsfxs("1");
           scglLjgybzService.insert(s);
        }

        //然后复制工艺大类编制的数据
        List<ScglGydlbz> gydlbzByjhid = scglGydlbzService.getGydlbzByjhid(yjhid);
        for (ScglGydlbz scglGydlbz : gydlbzByjhid) {
            ScglGydlbz s = new ScglGydlbz();
            String xid = scglGydlbz.getId()+"-"+count;
            String xLjid = scglGydlbz.getLjid()+"-"+count;
            s.setId(xid);
            s.setLjid(xLjid);
            s.setGydlid(scglGydlbz.getGydlid());
            s.setGydlmc(scglGydlbz.getGydlmc());
            s.setPx(scglGydlbz.getPx());
            scglGydlbzService.insert(s);
        }

        //再复制零件的数据
        List<ScjhglLjgl> ljByjhid = scjhglLjglService.getLjByjhid(yjhid);
        for (ScjhglLjgl scjhglLjgl : ljByjhid) {
            //如果是部件的话，需要复制部件组成里面的信息
            if (scjhglLjgl.getSfsbj().equals("1")){
                String ybjid = scjhglLjgl.getId();
                EntityWrapper<ScjhglBjzc> wrapper1 = new EntityWrapper<ScjhglBjzc>();
                wrapper1.eq("BJID", ybjid);
                List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper1);
                for (ScjhglBjzc scjhglBjzc : scjhglBjzcs) {
                    ScjhglBjzc s = new ScjhglBjzc();
                    String xxid = scjhglBjzc.getId()+"-"+count;
                    String xxbjid = scjhglBjzc.getBjid()+"-"+count;
                    String xxljid = scjhglBjzc.getLjid()+"-"+count;
                    s.setId(xxid);
                    s.setBjid(xxbjid);
                    s.setLjid(xxljid);
                    scjhglBjzcService.insert(s);
                }
            }
            ScjhglLjgl scjhglLjgl1 = new ScjhglLjgl();
            String xid = scjhglLjgl.getId()+"-"+count;
            String xjhid = scjhglLjgl.getHtid()+"-"+count;
            scjhglLjgl1.setId(xid);
            scjhglLjgl1.setHtid(xjhid);
            scjhglLjgl1.setLjth(scjhglLjgl.getLjth());
            scjhglLjgl1.setLjmc(scjhglLjgl.getLjmc());
            scjhglLjgl1.setDyl(scjhglLjgl.getDyl());
            scjhglLjgl1.setSfsbj(scjhglLjgl.getSfsbj());
            scjhglLjgl1.setBjzc(scjhglLjgl.getBjzc());
            //这里的为完成量和数量需要用单用量x新计划的数量
            int xljdyl = 0;
            if (scjhglLjgl.getDyl()!=null&&!scjhglLjgl.getDyl().equals("")){
                xljdyl = Integer.parseInt(scjhglLjgl.getDyl());
            }
            int xsl = xjhsli * xljdyl;
            scjhglLjgl1.setSl(xsl+"");
            scjhglLjgl1.setWrksl(xsl+"");
            scjhglLjgl1.setSysl(scjhglLjgl.getSysl());
            scjhglLjgl1.setSfwwcrk("0");
            scjhglLjglService.insert(scjhglLjgl1);
            //现在要更新所有零件工艺编制下下面的数量，未入库数量，和剩余数量
            List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(xid);
            for (ScglLjgybz scglLjgybz : ljgybzByLjid) {
                scglLjgybz.setWrksl(xsl);
                scglLjgybz.setSl(xsl);
                scglLjgybz.setSysl(xsl);
                scglLjgybzService.updateById(scglLjgybz);
            }

        }



    }
}
