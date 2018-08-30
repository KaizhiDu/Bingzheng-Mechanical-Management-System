package cn.jeeweb.modules.sbgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:    设备管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/30 17:08
 * @Version:        1.0
 */
@Controller
@RequestMapping("${admin.url.prefix}/sbgl/sbgl")
@RequiresPathPermission("sbgl:sbgl")
public class SbglController extends BaseCRUDController<Sbgl, String> {

    /**
* @Description:    展示
* @Author:         杜凯之
* @CreateDate:     2018/8/30 17:58
* @Version:        1.0
*/
}
