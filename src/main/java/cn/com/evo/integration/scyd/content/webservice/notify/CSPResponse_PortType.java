/**
 * CSPResponse_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.scyd.content.webservice.notify;

public interface CSPResponse_PortType extends java.rmi.Remote {
    public cn.com.evo.integration.scyd.content.webservice.notify.CSPResult resultNotify(java.lang.String CSPID, java.lang.String LSPID, java.lang.String correlateID, int cmdResult, java.lang.String resultFileURL) throws java.rmi.RemoteException;
}
