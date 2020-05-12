/**
 * CSPRequestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.scyd.content.webservice.lsp;

public interface CSPRequestService extends javax.xml.rpc.Service {
    public java.lang.String getctmsAddress();

    public cn.com.evo.integration.scyd.content.webservice.lsp.CSPRequest getctms() throws javax.xml.rpc.ServiceException;

    public cn.com.evo.integration.scyd.content.webservice.lsp.CSPRequest getctms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
