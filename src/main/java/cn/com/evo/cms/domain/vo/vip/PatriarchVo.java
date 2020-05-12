package cn.com.evo.cms.domain.vo.vip;

import com.frameworks.core.vo.BaseVo;


public class PatriarchVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private String userAccountNo;

    private String patriarchPassWord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public String getPatriarchPassWord() {
        return patriarchPassWord;
    }

    public void setPatriarchPassWord(String patriarchPassWord) {
        this.patriarchPassWord = patriarchPassWord;
    }
}
