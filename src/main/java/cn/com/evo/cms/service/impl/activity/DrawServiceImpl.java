package cn.com.evo.cms.service.impl.activity;

import cn.com.evo.cms.domain.entity.activity.Draw;
import cn.com.evo.cms.repository.activity.DrawRepository;
import cn.com.evo.cms.service.activity.DrawService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class DrawServiceImpl extends AbstractBaseService<Draw, String> implements DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Override
    protected BaseRepository<Draw, String> getBaseRepository() {
        return drawRepository;
    }

    @Override
    public void save(Draw entity) {
        if (entity.getEnable() == 1) {
            // 一个appId只允许存在唯一一个启用的抽奖活动
            List<Draw> temps = drawRepository.findByAppId(entity.getAppId());
            for (Draw temp : temps) {
                temp.setEnable(0);
                super.update(temp);
            }
        }
        super.save(entity);
    }

    @Override
    public void update(Draw entity) {
        if (entity.getEnable() == 1) {
            // 一个appId只允许存在唯一一个启用的抽奖活动
            List<Draw> temps = drawRepository.findByAppId(entity.getAppId());
            for (Draw temp : temps) {
                temp.setEnable(0);
                super.update(temp);
            }
        }
        super.update(entity);
    }

    @Override
    public List<Draw> findByEnableAndAppId(Integer enable, String appId) {
        return drawRepository.findByEnableAndAppId(enable, appId);
    }

    @Override
    public Draw getAvailableDraw(String appId) {
        try {
            List<Draw> temps = this.findByEnableAndAppId(1, appId);
            if (temps.size() == 0) {
                return null;
            } else {
                Draw draw = temps.get(0);
                if (DateUtil.compareTime(draw.getBeginTime()) &&
                        !DateUtil.compareTime(draw.getEndTime())) {
                    return draw;
                } else {
                    return null;
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException("获取可用的抽奖活动异常" + e.getMessage(), e);
        }
    }
}
