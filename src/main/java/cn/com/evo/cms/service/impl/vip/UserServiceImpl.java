package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.PayTypeEnum;
import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.PayConfig;
import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.vo.cms.UserServerOpenProgress;
import cn.com.evo.cms.repository.vip.UserRepository;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.PayConfigParamsService;
import cn.com.evo.cms.service.pay.PayConfigService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.service.vip.UserService;
import cn.com.evo.integration.xjnt.sdk.PayUtilXinJiang;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayReq;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayRsp;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Transactional
public class UserServiceImpl extends AbstractBaseService<User, String> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServerService userServerService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayConfigService payConfigService;
    @Autowired
    private PayConfigParamsService payConfigParamsService;

    @Override
    protected BaseRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public void deleteById(String id) {
        // 删除相关用户账户信息
        userAccountService.deleteByUserId(id);

        // 删除用户信息
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void dataImport(MultipartFile[] files, Integer type) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        for (MultipartFile mFile : files) {
            try {
                List<Map<String, String>> temps = Lists.newArrayList();
                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    // 读取excel数据放入内存
                    if (type == 1) {
                        // rowNum==0为表头
                        for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                            Row row = sheet.getRow(rowNum);
                            // 智能卡号
                            String cardNo = getValue(row.getCell(0));
                            // 应用id
                            String appId = getValue(row.getCell(1));
                            // 产品编码
                            String productCode = getValue(row.getCell(2));

                            System.out.println("rowNum:" + rowNum + "_cardNo:" + cardNo + "_appId:" + appId + "_productCode:" + productCode);

                            Map<String, String> map = Maps.newHashMap();
                            map.put("rowNum", rowNum + "");
                            map.put("cardNo", cardNo);
                            map.put("appId", appId);
                            map.put("productCode", productCode);
                            temps.add(map);
                        }
                    } else if (type == 2) {
                        // rowNum==0为表头
                        for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                            Row row = sheet.getRow(rowNum);
                            // 智能卡号
                            String cardNo = getValue(row.getCell(0));
                            // 应用id
                            String appId = getValue(row.getCell(1));
                            // 产品编码
                            String productCode = getValue(row.getCell(2));
                            // 开始时间
                            String beginTime = getValue(row.getCell(3));
                            // 结束时间
                            String endTime = getValue(row.getCell(4));

                            System.out.println("rowNum:" + rowNum + "_cardNo:" + cardNo + "_appId:" + appId + "_productCode:" + productCode);

                            Map<String, String> map = Maps.newHashMap();
                            map.put("rowNum", rowNum + "");
                            map.put("cardNo", cardNo);
                            map.put("appId", appId);
                            map.put("productCode", productCode);
                            map.put("beginTime", beginTime);
                            map.put("endTime", endTime);
                            temps.add(map);
                        }
                    } else {
                        throw new RuntimeException("模板类型错误!!!暂不支持!!!");
                    }

                    // 处理数据
                    UserServerOpenProgress.init();
                    UserServerOpenProgress.setTotal(temps.size());
                    // 服务开通
                    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-server-open-%d").build();

                    ExecutorService fixedThreadPool = new ThreadPoolExecutor(10, 20, 200L,
                            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);
                    for (Map<String, String> temp : temps) {
                        fixedThreadPool.execute(() -> {
                            excelWriteDb(temp, type);
                        });
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
    }

    @Override
    public void serverOpen(MultipartFile[] files) {
        for (MultipartFile mFile : files) {
            try {
                List<Map<String, String>> temps = Lists.newArrayList();

                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    // rowNum==0为表头
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        // 智能卡号
                        String cardNo = getValue(row.getCell(0));
                        // 机顶盒号
                        String stbNo = getValue(row.getCell(1));
                        // 应用id
                        String appId = getValue(row.getCell(2));
                        // 产品编码
                        String thirdProductCode = getValue(row.getCell(3));

                        Map<String, String> map = Maps.newHashMap();
                        map.put("rowNum", rowNum + "");
                        map.put("cardNo", cardNo);
                        map.put("stbNo", stbNo);
                        map.put("appId", appId);
                        map.put("thirdProductCode", thirdProductCode);
                        temps.add(map);

                    }
                }

                // 处理数据
                UserServerOpenProgress.init();
                UserServerOpenProgress.setTotal(temps.size());

                // 创建线程池
                ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-server-open-%d").build();
                ExecutorService fixedThreadPool = new ThreadPoolExecutor(10, 20, 200L,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);
                // 服务开通
                for (Map<String, String> temp : temps) {
                    fixedThreadPool.execute(() -> {
                        serverOpen(temp);
                    });
                }

                // 关闭线程池
                fixedThreadPool.shutdown();
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
    }

    /**
     * 读取cell的值
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    private String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            // 去除科学记数法
            return df.format(cell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 参数转换
     *
     * @param configParams
     * @return
     */
    private Map<String, Object> xjntTransform(List<PayConfigParams> configParams) {
        Map<String, Object> params = new HashMap<>();
        for (PayConfigParams configParam : configParams) {
            if ("authCode".equals(configParam.getNameEn())) {
                params.put("authCode", configParam.getValue());
            }
            if ("stbIp".equals(configParam.getNameEn())) {
                params.put("stbIp", configParam.getValue());
            }
            if ("bookId".equals(configParam.getNameEn())) {
                params.put("bookId", configParam.getValue());
            }
            if ("backUrl".equals(configParam.getNameEn())) {
                params.put("backUrl", configParam.getValue());
            }
        }
        return params;
    }

    /**
     * 写数据库
     *
     * @param temp
     * @param type
     */
    private void excelWriteDb(Map<String, String> temp, Integer type) {
        // 处理数据
        String rowNum = temp.get("rowNum");
        String cardNo = temp.get("cardNo");
        String appId = temp.get("appId");
        String productCode = temp.get("productCode");
        try {
            if (type == 1) {
                Product product = productService.getByCode(productCode);

                if (product == null) {
                    throw new RuntimeException("产品编码不存在");
                }

                // 获取用户信息
                UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);

                // 开通服务
                userServerService.openServer(userAccount.getUserId(), appId, product.getId());

            } else if (type == 2) {
                String beginTime = temp.get("beginTime");
                String endTime = temp.get("endTime");
                Product product = productService.getByCode(productCode);

                if (product == null) {
                    throw new RuntimeException("产品编码不存在");
                }

                // 获取用户信息
                UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);

                // 开通服务
                userServerService.openServer(userAccount.getUserId(), appId, product.getId(), beginTime, endTime);
            } else {
                throw new RuntimeException("模板类型错误!!!暂不支持!!!");
            }
            System.out.println("开通成功：rowNum:" + rowNum + "|cardNo:" + cardNo);
            UserServerOpenProgress.addSuccess();
        } catch (Exception e) {
            System.out.println("开通失败：rowNum:" + rowNum + "|cardNo:" + cardNo + "|msg:" + e.getMessage());
            UserServerOpenProgress.addFail();
        }
    }


    /**
     * 服务开通
     *
     * @param temp
     */
    private void serverOpen(Map<String, String> temp) {
        try {
            Province province = provinceService.getByEnable(1);
            if (province == null) {
                throw new RuntimeException("没有可用的省网配置!!!");
            }

            // 支付方式boss
            Integer payType = 3;

            String rowNum = temp.get("rowNum");
            String cardNo = temp.get("cardNo");
            String stbNo = temp.get("stbNo");
            String appId = temp.get("appId");
            String thirdProductCode = temp.get("thirdProductCode");

            // 获取用户信息
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, stbNo);
            // 获取产品100006
            Product product = productService.getByCode(thirdProductCode);
            // 获取支付方式配置
            PayConfig payConfig = payConfigService.getByPayTypeAndProvinceCodeAndEnable(3, ProvinceCodeEnum.XinJiang.getCode(), 1);
            // 获取支付详细配置
            List<PayConfigParams> configParams = payConfigParamsService.findByConfigId(payConfig.getId());

            // 省网code枚举
            ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());
            switch (provinceCodeEnum) {
                case XinJiang:
                    try {
                        //参数转换
                        Map<String, Object> params = xjntTransform(configParams);
                        XinJiangPayReq req = new XinJiangPayReq(params.get("authCode") + "", cardNo, stbNo, params.get("stbIp") + "", Integer.valueOf(params.get("bookId") + ""), product.getThirdPartyCode(), product.getContentCode(),
                                params.get("backUrl") + "", "");
                        XinJiangPayRsp rsp = PayUtilXinJiang.bossPay(req);
                        if ("0".equals(rsp.getRetCode()) || "SUCCESS".equals(rsp.getRetCode())) {
                            //创建订单记录
                            Order order = orderService.createOrder(rsp.getOrderNo(), payConfig.getId(), userAccount.getUserId(),
                                    product.getId(), product.getOriginalPrice(),
                                    product.getCurrentPrice(), payType, appId, userAccount.getId());

                            // 如果 支付方式为 BOSS／SP 则直接开通服务 其他支付方式在 支付结果回调 时开通
                            if (PayTypeEnum.BOSS.getType().equals(payType) || PayTypeEnum.SP.getType().equals(payType)) {
                                //开通服务
                                userServerService.openServer(userAccount.getUserId(), appId, product.getId());
                            }
                            System.out.println("开通成功：rowNum:" + rowNum + "|cardNo:" + cardNo + "|stbId:" + stbNo);
                            UserServerOpenProgress.addSuccess();
                        } else {
                            System.out.println("开通失败：rowNum:" + rowNum + "|cardNo:" + cardNo + "|stbId:" + stbNo + "|msg:" + rsp.getRetMsg());
                            // 开通失败 原因很多有可能是账本费用不足
                            UserServerOpenProgress.addFail();
                        }
                    } catch (Exception e) {
                        // 底层报错导致开通失败
                        System.out.println("开通失败：rowNum:" + rowNum + "|cardNo:" + cardNo + "|stbId:" + stbNo + "|msg:" + e.getMessage());
                        UserServerOpenProgress.addFail();
                    }
                    break;
                default:
                    throw new RuntimeException("暂不支持该省网执行本操作!!!");
            }
        } catch (Exception e) {
            throw new RuntimeException("读取excel数据，开通服务异常" + e.getMessage(), e);
        }
    }
}
