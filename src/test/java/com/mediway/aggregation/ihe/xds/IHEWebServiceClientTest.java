/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mediway.aggregation.ihe.xds;

import cn.hutool.core.collection.CollUtil;
import com.mediway.aggregation.ihe.xds.client.IHEWebServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AssigningAuthority;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AvailabilityStatus;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.FindDocumentsQuery;
import org.openehealth.ipf.commons.ihe.xds.core.responses.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:contextbak.xml")
@Slf4j
public class IHEWebServiceClientTest {

    @Autowired
    private IHEWebServiceClient iheClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testSmoke() throws Exception {
        assertNotNull(iheClient.getCamelContext());
    }

//    @Disabled
//    public void testIti47() throws Exception {
//        var in = getClass().getResourceAsStream("/example-messages/PDQv3.xml");
//        var request = IOUtils.toString(in, Charset.defaultCharset());
//        var result = iheClient.iti47PatientDemographicsQuery(request, "localhost", 8080, "pix-xref-mgr/ws/iti47Service?secure=true", false);
//        System.out.println(result);
//    }
    @Test
    public void testITI18() throws Exception {
        // set up a query
        FindDocumentsQuery query = new FindDocumentsQuery();
        query.setPatientId(new Identifiable("st3498702", new AssigningAuthority("1.3.6.1.4.1.21367.2005.3.7")));
        log.info(query.getPatientId().getId());
        // setup status
        List<AvailabilityStatus> statuses = CollUtil.newArrayList();
        statuses.add(AvailabilityStatus.APPROVED);
        query.setStatus(statuses);
        // send the query to endpoints
        QueryResponse queryResponse = iheClient.iti18StoredQuery(query,"localhost",8367,"api/services/xds-iti18");
        log.warn(queryResponse.toString());
    }

}
