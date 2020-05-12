package cn.com.evo.integration.wasu.common.auth;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author sgt
 * @since 2019-04-17 10:15
 */
public class ResponseBody {


    /**
     * output : {"prodSubscription":[{"busiExpriDate":"2099-12-31","busiValidDate":"2017-12 -06","createDate":"2017-12-06","prodSubscriptionId":"100179729616","productId":"800500544320","productName":"数字基本频道14.7元套餐（惠农）","status":"1","type":"3"},{"busiExpriDate":"2099-12-31","busiValidDate":"2018-11-15","createDate":"2018-11-15","prodSubscriptionId":"100187299910","productId":"800500597547","productName":"【云和】中国气象免费包","status":"1","type":"3"}]}
     * returnCode : 0
     * returnMessage : 订购查询成功。
     */

    private OutputBean output;
    private String returnCode;
    private String returnMessage;

    public OutputBean getOutput() {
        return output;
    }

    public void setOutput(OutputBean output) {
        this.output = output;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public static class OutputBean {
        private List<ProdSubscriptionBean> prodSubscription;

        public List<ProdSubscriptionBean> getProdSubscription() {
            return prodSubscription;
        }

        public void setProdSubscription(List<ProdSubscriptionBean> prodSubscription) {
            this.prodSubscription = prodSubscription;
        }

        public static class ProdSubscriptionBean {
            /**
             * busiExpriDate : 2099-12-31
             * busiValidDate : 2017-12 -06
             * createDate : 2017-12-06
             * prodSubscriptionId : 100179729616
             * productId : 800500544320
             * productName : 数字基本频道14.7元套餐（惠农）
             * status : 1
             * type : 3
             */

            private String busiExpriDate;
            private String busiValidDate;
            private String createDate;
            private String prodSubscriptionId;
            private String productId;
            private String productName;
            private String status;
            private String type;

            public String getBusiExpriDate() {
                return busiExpriDate;
            }

            public void setBusiExpriDate(String busiExpriDate) {
                this.busiExpriDate = busiExpriDate;
            }

            public String getBusiValidDate() {
                return busiValidDate;
            }

            public void setBusiValidDate(String busiValidDate) {
                this.busiValidDate = busiValidDate;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getProdSubscriptionId() {
                return prodSubscriptionId;
            }

            public void setProdSubscriptionId(String prodSubscriptionId) {
                this.prodSubscriptionId = prodSubscriptionId;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
