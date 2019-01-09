package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;

import java.util.List;

/**
 * Dscription: 仓库管理 - 未完成工序
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 13:35
 */
public interface ICkglWwcgxService extends ICommonService<CkglWwcgx> {
    public List<CkglWwcgx> getData(String id);
}
