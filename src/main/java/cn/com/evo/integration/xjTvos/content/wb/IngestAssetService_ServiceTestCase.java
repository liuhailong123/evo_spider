/**
 * IngestAssetService_ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class IngestAssetService_ServiceTestCase extends junit.framework.TestCase {
    public IngestAssetService_ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testIngestAssetServiceSOAPWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAPAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1IngestAssetServiceSOAPIngestAssetDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetResponse value = null;
        value = binding.ingestAssetDeal(new cn.com.evo.integration.xjTvos.content.wb.IngestAsset());
        // TBD - validate results
    }

    public void test2IngestAssetServiceSOAPQueryStateDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.QueryStateResponse value = null;
        value = binding.queryStateDeal(new cn.com.evo.integration.xjTvos.content.wb.QueryState());
        // TBD - validate results
    }

    public void test3IngestAssetServiceSOAPUnpublishDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.UnpublishResponse value = null;
        value = binding.unpublishDeal(new cn.com.evo.integration.xjTvos.content.wb.Unpublish());
        // TBD - validate results
    }

    public void test4IngestAssetServiceSOAPDeleteAssetDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.DeleteAssetResponse value = null;
        value = binding.deleteAssetDeal(new cn.com.evo.integration.xjTvos.content.wb.DeleteAsset());
        // TBD - validate results
    }

    public void test5IngestAssetServiceSOAPPublishDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.PublishResponse value = null;
        value = binding.publishDeal(new cn.com.evo.integration.xjTvos.content.wb.Publish());
        // TBD - validate results
    }

    public void test6IngestAssetServiceSOAPBindProductDeal() throws Exception {
        cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.xjTvos.content.wb.IngestAssetServiceSOAPStub)
                          new cn.com.evo.integration.xjTvos.content.wb.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
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
        cn.com.evo.integration.xjTvos.content.wb.BindProductResponse value = null;
        value = binding.bindProductDeal(new cn.com.evo.integration.xjTvos.content.wb.BindProduct());
        // TBD - validate results
    }

}
