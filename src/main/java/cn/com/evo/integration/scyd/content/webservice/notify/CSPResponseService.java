/**
 * CSPResponseService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.scyd.content.webservice.notify;

public interface CSPResponseService extends javax.xml.rpc.Service {
    public java.lang.String getCSPResponseAddress();

    public cn.com.evo.integration.scyd.content.webservice.notify.CSPResponse_PortType getCSPResponse() throws javax.xml.rpc.ServiceException;

    public cn.com.evo.integration.scyd.content.webservice.notify.CSPResponse_PortType getCSPResponse(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
