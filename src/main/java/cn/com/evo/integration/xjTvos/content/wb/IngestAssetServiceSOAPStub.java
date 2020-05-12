/**
 * IngestAssetServiceSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class IngestAssetServiceSOAPStub extends org.apache.axis.client.Stub implements cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IngestAssetDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:IngestAsset"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">IngestAsset"), cn.com.evo.integration.xjTvos.content.wb.IngestAsset.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">IngestAssetResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "IngestAssetResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("QueryStateDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:QueryState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">QueryState"), cn.com.evo.integration.xjTvos.content.wb.QueryState.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">QueryStateResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "QueryStateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("UnpublishDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:Unpublish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">Unpublish"), cn.com.evo.integration.xjTvos.content.wb.Unpublish.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">UnpublishResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "UnpublishResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteAssetDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:DeleteAsset"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">DeleteAsset"), cn.com.evo.integration.xjTvos.content.wb.DeleteAsset.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">DeleteAssetResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "DeleteAssetResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PublishDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:Publish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">Publish"), cn.com.evo.integration.xjTvos.content.wb.Publish.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">PublishResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.PublishResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "PublishResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BindProductDeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:BindProduct"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">BindProduct"), cn.com.evo.integration.xjTvos.content.wb.BindProduct.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">BindProductResponse"));
        oper.setReturnClass(cn.com.evo.integration.xjTvos.content.wb.BindProductResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "BindProductResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public IngestAssetServiceSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public IngestAssetServiceSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public IngestAssetServiceSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">BindProduct");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.BindProduct.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">BindProductResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.BindProductResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">DeleteAsset");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.DeleteAsset.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">DeleteAssetResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">IngestAsset");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.IngestAsset.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">IngestAssetResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">Publish");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.Publish.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">PublishResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.PublishResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">QueryState");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.QueryState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">QueryStateResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">Unpublish");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.Unpublish.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">UnpublishResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse ingestAssetDeal(cn.com.evo.integration.xjTvos.content.wb.IngestAsset parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:IngestAssetDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse queryStateDeal(cn.com.evo.integration.xjTvos.content.wb.QueryState parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:QueryStateDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse unpublishDeal(cn.com.evo.integration.xjTvos.content.wb.Unpublish parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:UnpublishDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse deleteAssetDeal(cn.com.evo.integration.xjTvos.content.wb.DeleteAsset parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:DeleteAssetDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.com.evo.integration.xjTvos.content.wb.PublishResponse publishDeal(cn.com.evo.integration.xjTvos.content.wb.Publish parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:PublishDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.PublishResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.PublishResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.PublishResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.com.evo.integration.xjTvos.content.wb.BindProductResponse bindProductDeal(cn.com.evo.integration.xjTvos.content.wb.BindProduct parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ing:BindProductDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.evo.integration.xjTvos.content.wb.BindProductResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.evo.integration.xjTvos.content.wb.BindProductResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.evo.integration.xjTvos.content.wb.BindProductResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
