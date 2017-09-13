package org.openecomp.aai.rest;

import com.att.eelf.configuration.EELFLogger;
import com.att.eelf.configuration.EELFManager;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.openecomp.aai.AAISetup;
import org.openecomp.aai.dbmap.AAIGraph;
import org.openecomp.aai.introspection.ModelInjestor;
import org.openecomp.aai.introspection.Version;

import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class BulkProcessConsumerTest extends BulkAddConsumerTest {

    private static final EELFLogger logger = EELFManager.getInstance().getLogger(BulkProcessConsumerTest.class.getName());

    @Test
    public void testBulkAddCreatedWhenOneTransactionInPayloadContainsNotAllowedVerb() throws IOException {

        String uri = "/aai/v11/bulkadd";

        when(uriInfo.getPath()).thenReturn(uri);
        when(uriInfo.getPath(false)).thenReturn(uri);

        String payload = getBulkPayload("pserver-transactions-invalid");
        Response response = bulkConsumer.bulkAdd(
                payload,
                Version.getLatest().toString(),
                httpHeaders,
                uriInfo,
                null
        );

        System.out.println("Code: " + response.getStatus() + "\tResponse: " + response.getEntity());
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Override
    public BulkConsumer getConsumer(){
        return new BulkProcessConsumer();
    }
}