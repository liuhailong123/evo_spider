/**
 * IngestAssetService_ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.nxsp.content.ws;

public class IngestAssetService_ServiceTestCase extends junit.framework.TestCase {
    public IngestAssetService_ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testIngestAssetServiceSOAPWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAPAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1IngestAssetServiceSOAPIngestAsset() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.ingestAsset(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

    public void test2IngestAssetServiceSOAPQueryState() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.queryState(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new javax.xml.rpc.holders.StringHolder(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

    public void test3IngestAssetServiceSOAPUnpublish() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.unpublish(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

    public void test4IngestAssetServiceSOAPDeleteAsset() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteAsset(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

    public void test5IngestAssetServiceSOAPPublish() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.publish(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

    public void test6IngestAssetServiceSOAPBindProduct() throws Exception {
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.bindProduct(new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new javax.xml.rpc.holders.StringHolder(new java.lang.String()), new java.lang.String(), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.StringHolder());
        // TBD - validate results
    }

}
