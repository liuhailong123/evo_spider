package cn.com.evo.cms.web.dataDispose.excelImport;

import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.entity.vip.UserServer;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.utils.WriteLogUtil;
import com.frameworks.core.web.controller.BaseController;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.frameworks.utils.DateUtil.compareTime;
import static org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted;


/**
 * sp1.0 用户服务开通 excel数据导入
 */
@Controller
@RequestMapping("/api/excelImport/userServerOpen")
public class UserServerOpen extends BaseController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServerService userServerService;

    /**
     * 步骤一
     *
     * @param path
     * @return
     */
    @RequestMapping(value = "/stepOne", method = {RequestMethod.GET})
    @ResponseBody
    public String stepOne(@RequestParam("path") String path) {
        String result = "";
        String cardNo = "";
        try {
            File file = new File(path);
            if (file.exists()) {//文件存在
                Workbook workbook = WorkbookFactory.create(file);
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                    System.out.println("进入sheet" + i);
                    try {
                        Sheet sheet = workbook.getSheetAt(i);
                        for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {//获取每行
                            try {
                                System.out.println("进入第" + (rowNum + 1) + "行");
                                //行 数据解析
                                Row row = sheet.getRow(rowNum);
                                cardNo = getValue(row.getCell(0));// 卡号
                                String prodCode = getValue(row.getCell(2));// 产品编码
                                String endTime = excelTime(row.getCell(5));
                                //判断卡号是否存在,不存在则创建 用户，用户账号
                                UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, "");
                                //创建订单
                                createOrder(userAccount, prodCode);
                                //开通服务
                                openServer(userAccount, endTime);
                                result = "导入成功！！！";
                            } catch (Exception e) {
                                e.printStackTrace();
                                //问题写日志
                                WriteLogUtil.writrLog(file.getParentFile().getAbsolutePath() + "/userServerOpen.txt",
                                        "sheet:" + i + "==行号：" + (rowNum + 1) + "==卡号" + cardNo + "\n");
                            }
                        }
                    } catch (Exception e) {
                        result = "excel处理失败，原因：" + e.getMessage();
                    }
                }
            } else {
                result = "文件不存在/路径不正确";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void openServer(UserAccount userAccount, String endTime) throws Exception {
        // 判断是否开通
        UserServer userServer = userServerService.getByUserIdAndAppIdAndServerCode(userAccount.getUserId(), "1", "10001");
        if (userServer == null) {
            // 如果未开通，新增
            userServer = new UserServer(userAccount.getUserId(), "1", "10001", endTime);
            userServerService.save(userServer);
        } else {
            // 如果开通
            if (compareTime(userServer.getMaturityTime())) {
                // 当前服务已过期,使用传入的结束时间
                userServer.setMaturityTime(endTime);
            } else {
                //当前服务未过期,使用传入剩余时间+当前结束时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //原到期时间-新开始时间
                Long startTime = dateFormat.parse(userServer.getMaturityTime()).getTime();
                //当前时间
                Long thisTime = System.currentTimeMillis();
                //原结束时间
                Long oldEndTime = dateFormat.parse(endTime).getTime();
                Long newTime = oldEndTime - thisTime;
                Long newEndTime = startTime + newTime;
                String newEndTimeStr = dateFormat.format(newEndTime);

                userServer.setMaturityTime(newEndTimeStr);
            }
            userServerService.update(userServer);
        }
    }

    private void createOrder(UserAccount userAccount, String prodCode) throws Exception {
        //根据产品code 获取产品
        Product product = productService.getByThirdPartyCode(prodCode);
        Order order = new Order();
        order.setManualHandlingInfo("sp-1.0-order-import-qiangyana");//订单人工处理信息
        order.setOrderNo("Import" + createOrderNo());//订单号
        order.setOrderType(1);//订单状态 已支付
        order.setOverDate(new Date());
        order.setPayAbleMoney(product.getOriginalPrice());//原价-应付金额
        order.setPayAmountMoney(product.getCurrentPrice());//优惠价-实付金额
        order.setProduct(product);//产品
        User user = new User();
        user.setId(userAccount.getUserId());
        order.setUser(user);//用户
        Column app = new Column();
        app.setId("1");
        order.setApp(app);//应用
        order.setUserAccount(userAccount);//用户账号
        orderService.save(order);
    }

    /**
     * 生成订单号
     *
     * @return
     */
    private String createOrderNo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(System.currentTimeMillis());
        //订单号增加6位随机数，防止重复
        String r = getRandom();
        String orderNo = r + dateString;
        return orderNo;
    }

    /**
     * 获取随机数(6位)
     *
     * @return
     */
    public static String getRandom() {
        Random r = new Random();
        long num = Math.abs(r.nextLong() % 1000000L);
        String s = String.valueOf(num);
        for (int i = 0; i < 6 - s.length(); i++) {
            s = "0" + s;
        }

        return s;
    }

    /**
     * 读取cell的值
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    public String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            return df.format(cell.getNumericCellValue());// 去除科学记数法
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * @param cell import org.apache.poi.ss.usermodel.Cell;
     *             关于如何将Excel表格中的时间字符串的数字格式  转换成 格式化的时间字符串
     * @return
     * @author SHUN
     */
    public String excelTime(Cell cell) {
        String guarantee_time = null;
        if (isCellDateFormatted(cell)) {
            //用于转化为日期格式
            Date d = cell.getDateCellValue();
//	             System.err.println(d.toString());
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            DateFormat formater = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            guarantee_time = formater.format(d);
        }
        return guarantee_time;
    }
}
