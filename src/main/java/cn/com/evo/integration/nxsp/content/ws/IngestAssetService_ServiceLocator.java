/**
 * IngestAssetService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.nxsp.content.ws;

import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;

public class IngestAssetService_ServiceLocator extends org.apache.axis.client.Service implements cn.com.evo.integration.nxsp.content.ws.IngestAssetService_Service {

    public IngestAssetService_ServiceLocator() {
        IngestAssetServiceSOAP_address = ConstantFactory.map.get(NxConstantEnum.ctms_url.getKey());
        System.out.println("注入地址：" + IngestAssetServiceSOAP_address);
    }


    public IngestAssetService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IngestAssetService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IngestAssetServiceSOAP
    private java.lang.String IngestAssetServiceSOAP_address = "null";

    public java.lang.String getIngestAssetServiceSOAPAddress() {
        return IngestAssetServiceSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IngestAssetServiceSOAPWSDDServiceName = "IngestAssetServiceSOAP";

    public java.lang.String getIngestAssetServiceSOAPWSDDServiceName() {
        return IngestAssetServiceSOAPWSDDServiceName;
    }

    public void setIngestAssetServiceSOAPWSDDServiceName(java.lang.String name) {
        IngestAssetServiceSOAPWSDDServiceName = name;
    }

    public cn.com.evo.integration.nxsp.content.ws.IngestAssetService_PortType getIngestAssetServiceSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IngestAssetServiceSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIngestAssetServiceSOAP(endpoint);
    }

    public cn.com.evo.integration.nxsp.content.ws.IngestAssetService_PortType getIngestAssetServiceSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub _stub = new cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub(portAddress, this);
            _stub.setPortName(getIngestAssetServiceSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIngestAssetServiceSOAPEndpointAddress(java.lang.String address) {
        IngestAssetServiceSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.com.evo.integration.nxsp.content.ws.IngestAssetService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub _stub = new cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub(new java.net.URL(IngestAssetServiceSOAP_address), this);
                _stub.setPortName(getIngestAssetServiceSOAPWSDDServiceName());
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
        if ("IngestAssetServiceSOAP".equals(inputPortName)) {
            return getIngestAssetServiceSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "IngestAssetService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "IngestAssetServiceSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IngestAssetServiceSOAP".equals(portName)) {
            setIngestAssetServiceSOAPEndpointAddress(address);
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
