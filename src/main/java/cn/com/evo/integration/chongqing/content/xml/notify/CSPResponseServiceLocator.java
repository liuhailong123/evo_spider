/**
 * CSPResponseServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.chongqing.content.xml.notify;

import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.common.ConstantFactory;

public class CSPResponseServiceLocator extends org.apache.axis.client.Service implements cn.com.evo.integration.chongqing.content.xml.notify.CSPResponseService {

    public CSPResponseServiceLocator() {
        ctms_address = ConstantFactory.map.get(CQConstantEnum.ctms_url.getKey());
        System.out.println(ctms_address);
    }


    public CSPResponseServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CSPResponseServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ctms
    private java.lang.String ctms_address = "http://127.0.0.1";

    public java.lang.String getctmsAddress() {
        return ctms_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ctmsWSDDServiceName = "ctms";

    public java.lang.String getctmsWSDDServiceName() {
        return ctmsWSDDServiceName;
    }

    public void setctmsWSDDServiceName(java.lang.String name) {
        ctmsWSDDServiceName = name;
    }

    public cn.com.evo.integration.chongqing.content.xml.notify.CSPResponse getctms() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ctms_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getctms(endpoint);
    }

    public cn.com.evo.integration.chongqing.content.xml.notify.CSPResponse getctms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.com.evo.integration.chongqing.content.xml.notify.CtmsSoapBindingStub _stub = new cn.com.evo.integration.chongqing.content.xml.notify.CtmsSoapBindingStub(portAddress, this);
            _stub.setPortName(getctmsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setctmsEndpointAddress(java.lang.String address) {
        ctms_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.com.evo.integration.chongqing.content.xml.notify.CSPResponse.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.com.evo.integration.chongqing.content.xml.notify.CtmsSoapBindingStub _stub = new cn.com.evo.integration.chongqing.content.xml.notify.CtmsSoapBindingStub(new java.net.URL(ctms_address), this);
                _stub.setPortName(getctmsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ctms".equals(inputPortName)) {
            return getctms();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("iptv", "CSPResponseService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("iptv", "ctms"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ctms".equals(portName)) {
            setctmsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
