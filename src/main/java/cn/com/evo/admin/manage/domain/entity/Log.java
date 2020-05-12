package cn.com.evo.admin.manage.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sys_log")
@NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
public class Log extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(length = 45)
    private String operator;

    @Column(length = 45)
    private String operation;

    @Column(length = 225)
    private String opParams;

    @Column(length = 225)
    private String opResult;

    @Temporal(TemporalType.TIMESTAMP)
    private Date opDate;

    @Column(length = 15)
    private String hostIp;

    @Column(length = 45)
    private String logType;

    public Log() {
    }

    public Log(String operator, String operation, String opParams, String opResult, Date opDate, String hostIp,
            String logType) {
        this.operator = operator;
        this.operation = operation;
        this.opParams = opParams;
        this.opResult = opResult;
        this.opDate = opDate;
        this.hostIp = hostIp;
        this.logType = logType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOpParams() {
        return opParams;
    }

    public void setOpParams(String opParams) {
        this.opParams = opParams;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

}