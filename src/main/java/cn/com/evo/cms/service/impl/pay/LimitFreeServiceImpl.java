package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.pay.LimitFree;
import cn.com.evo.cms.repository.pay.LimitFreeRepository;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.web.api.vo.ShowContentApiVo;
import cn.com.evo.cms.web.voService.ContentVoService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@Transactional
public class LimitFreeServiceImpl extends AbstractBaseService<LimitFree, String> implements LimitFreeService {
    @Autowired
    private LimitFreeRepository limitFreeRepository;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ContentVoService contentVoService;

    @Override
    protected BaseRepository<LimitFree, String> getBaseRepository() {
        return limitFreeRepository;
    }

    @Override
    public void save(LimitFree entity) {
        if (entity.getType() == 1) {
            entity.setBizId(entity.getAppId());
            entity.setContentId(null);
            entity.setContentType(null);
        } else if (entity.getType() == 2) {
            entity.setBizId(entity.getColumnId());
            entity.setContentId(null);
            entity.setContentType(null);
        } else if (entity.getType() == 3) {
            entity.setBizId(entity.getContentId());
        }
        LimitFree temp = limitFreeRepository.getByBizIdAndAppId(entity.getBizId(), entity.getAppId());
        if (temp == null) {
            super.save(entity);
        } else {
            throw new RuntimeException("已存在的限免计划，请勿重复添加！！！");
        }
    }

    @Override
    public String getBizName(String id) {
        LimitFree entity = this.findById(id);
        //1-应用限免；2-栏目限免；3-内容限免
        if (entity.getType() == 1) {
            return columnService.findById(entity.getBizId()).getName();
        } else if (entity.getType() == 2) {
            return columnService.findById(entity.getBizId()).getName();
        } else if (entity.getType() == 3) {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(entity.getBizId());
            ShowContentApiVo vo = contentVoService.getShowContentApiVo(entity.getContentType(), catalogueRelation.getBId(), catalogueRelation, null);
            if (vo != null) {
                return vo.getName();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public LimitFree getByBizIdAndAppId(String bizId, String appId) {
        return limitFreeRepository.getByBizIdAndAppId(bizId, appId);
    }

    @Override
    public boolean isFree(String bizId, String appId) {
        LimitFree limitFree = this.getByBizIdAndAppId(bizId, appId);
        if (limitFree == null) {
            // 未配置限免
            return false;
        } else {
            try {
                if (DateUtil.compareTime(limitFree.getBeginFreeTime()) &&
                        !DateUtil.compareTime(limitFree.getEndFreeTime())) {
                    // 限时免费未失效
                    return true;
                } else {
                    // 限时免费已失效
                    return false;
                }
            } catch (ParseException e) {
                throw new RuntimeException("时间比较异常" + e.getMessage(), e);
            }
        }
    }
}
