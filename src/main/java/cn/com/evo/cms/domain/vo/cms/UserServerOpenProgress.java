package cn.com.evo.cms.domain.vo.cms;/** * @Description: 会员导入或服务开通进度类 * @author: lu.xin * @create: 2019-07-09 11:36 AM **/public class UserServerOpenProgress {    private static Integer total = 0;    private static Integer success = 0;    private static Integer fail = 0;    public synchronized static void setTotal(Integer temp) {        total = temp;    }    public synchronized static void addSuccess() {        success = success + 1;    }    public synchronized static void addFail() {        fail = fail + 1;    }    public synchronized static void init() {        total = 0;        success = 0;        fail = 0;    }    public static Integer getTotal() {        return total;    }    public static Integer getSuccess() {        return success;    }    public static Integer getFail() {        return fail;    }}