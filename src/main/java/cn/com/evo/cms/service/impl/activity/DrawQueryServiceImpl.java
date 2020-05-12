package cn.com.evo.cms.service.impl.activity;

import cn.com.evo.cms.domain.entity.activity.Draw;
import cn.com.evo.cms.domain.entity.activity.DrawChild;
import cn.com.evo.cms.domain.entity.activity.DrawQuery;
import cn.com.evo.cms.repository.activity.DrawQueryRepository;
import cn.com.evo.cms.service.activity.DrawChildService;
import cn.com.evo.cms.service.activity.DrawQueryService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
public class DrawQueryServiceImpl extends AbstractBaseService<DrawQuery, String> implements DrawQueryService {

    @Autowired
    private DrawQueryRepository drawQueryRepository;
    @Autowired
    private DrawChildService drawChildService;

    @Override
    protected BaseRepository<DrawQuery, String> getBaseRepository() {
        return drawQueryRepository;
    }

    @Override
    public Integer residueCount(String userId, Draw draw) {
        Integer maxCount = draw.getMaxCount();
        Integer residueCount = 0;
        switch (draw.getUnit()) {
            case 0:
                // 总量
                List<DrawQuery> temp1 = drawQueryRepository.findByUserId(userId);
                if (temp1.size() >= maxCount) {
                    return 0;
                } else {
                    return maxCount - temp1.size();
                }
            case 1:
                // 每日
                List<DrawQuery> temp2 = drawQueryRepository.findByUserIdAndCreateDateLike(userId,
                        DateUtil.getDate() + "%");
                if (temp2.size() >= maxCount) {
                    return 0;
                } else {
                    return maxCount - temp2.size();
                }
            case 2:
                // 每月
                List<DrawQuery> temp3 = drawQueryRepository.findByUserIdAndCreateDateIsBetween(userId,
                        DateUtil.thisMonth(), DateUtil.thisMonthEnd());
                if (temp3.size() >= maxCount) {
                    return 0;
                } else {
                    return maxCount - temp3.size();
                }
            case 3:
                // 每年
                List<DrawQuery> temp4 = drawQueryRepository.findByUserIdAndCreateDateIsBetween(userId,
                        DateUtil.thisYear(), DateUtil.thisYearEnd());
                if (temp4.size() >= maxCount) {
                    return 0;
                } else {
                    return maxCount - temp4.size();
                }
            default:
                throw new RuntimeException("抽奖池限制单位配置错误！！！");
        }
    }

    @Override
    public DrawQuery draw(String userId, String cardNo, String appId, Draw draw) {
        // 获取可用奖品
        List<DrawChild> drawChildren = drawChildService.findByDrawIdAndEnable(draw.getId(), 1);
        if (drawChildren.size() > 0) {
            int index = draw(drawChildren);
            DrawChild drawChild = drawChildren.get(index);

            DrawQuery entity = new DrawQuery();
            entity.setAppId(appId);
            entity.setUserId(userId);
            entity.setDrawId(draw.getId());
            entity.setDrawChildId(drawChild.getId());
            entity.setIsSend(0);
            entity.setCardNo(cardNo);
            if (drawChild.getIsEffective() == 0) {
                entity.setIsOwn(0);
            } else {
                entity.setIsOwn(1);
            }
            super.save(entity);

            return entity;
        } else {
            throw new RuntimeException("未配置可用奖项无法抽奖");
        }
    }

    @Override
    public List<DrawQuery> findByUserIdAndAppId(String userId, String appId) {
        return drawQueryRepository.findByUserIdAndAppId(userId, appId);
    }

    @Override
    public List<DrawQuery> findByIsOwnAndAppId(Integer isOwn, String appId) {
        return drawQueryRepository.findByIsOwnAndAppId(isOwn, appId);

    }

    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     *
     * @param drawChilds
     * @return random：奖品列表draws中的序列（draws中的第random个就是抽中的奖品）
     */
    public int draw(List<DrawChild> drawChilds) {
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (DrawChild drawChild : drawChilds) {
                sumWeight += drawChild.getProbability();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < drawChilds.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(drawChilds.get(i).getProbability())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(drawChilds.get(i - 1).getProbability())) / sumWeight;
                }

                // 随机数落在某个权重区间内
                if (randomNumber >= d1 && randomNumber <= d2) {
                    DrawChild drawChild = drawChilds.get(i);
                    if (drawChild.getNowCount() > 0) {
                        random = i;
                        drawChild.setNowCount(drawChild.getNowCount() - 1);
                        // 更新剩余数量
                        drawChildService.update(drawChild);
                        break;
                    } else {
                        random = draw(drawChilds);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }
/*
    public static void main(String[] args) {
        int i = 0;
        int[] result = new int[4];
        List<DrawChild> drawChilds = new ArrayList<DrawChild>();

        DrawChild p1 = new DrawChild();
        p1.setName("一等奖");
        p1.setProbability(1);
        p1.setCount(3);
        drawChilds.add(p1);

        DrawChild p2 = new DrawChild();
        p2.setName("二等奖");
        p2.setProbability(2);
        p2.setCount(10);
        drawChilds.add(p2);

        DrawChild p3 = new DrawChild();
        p3.setName("三等奖");
        p3.setProbability(5);
        p3.setCount(50);
        drawChilds.add(p3);

        DrawChild p4 = new DrawChild();
        p4.setName("未中奖");
        p4.setProbability(73);
        p4.setCount(99999999);
        drawChilds.add(p4);

        System.out.println("抽奖开始");
        // 打印10000个测试概率的准确性
        for (i = 0; i < 3000; i++) {
            int selected = draw(drawChilds);
            System.out.println("第" + i + "次抽中的奖品为：" + drawChilds.get(selected).getName());
            result[selected]++;
            System.out.println("--------------------------------");
        }
        System.out.println("抽奖结束");
        System.out.println("每种奖品抽到的数量为：");
        System.out.println("一等奖：" + result[0]);
        System.out.println("二等奖：" + result[1]);
        System.out.println("三等奖：" + result[2]);
        System.out.println("未中奖：" + result[3]);
    }*/
}
