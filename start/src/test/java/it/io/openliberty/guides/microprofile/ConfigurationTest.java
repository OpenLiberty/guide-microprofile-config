// tag::comment[]
/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::comment[]
package it.io.openliberty.guides.microprofile;

import static org.junit.Assert.*;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.io.openliberty.guides.microprofile.TestUtil;

// tag::class[]
public class ConfigurationTest {

  private String port;
  private String baseUrl;
  private Client client;

  private final String INVENTORY_HOSTS = "inventory/hosts";
  private final String INVENTORY_CONFIG_ALL = "inventory/config/all";
  private final String USER_DIR = System.getProperty("user.dir");
  private final String DEFAULT_CONFIG_FILE = USER_DIR
      + "/src/main/resources/META-INF/microprofile-config.properties";
  private final String CUSTOM_CONFIG_FILE = USER_DIR.split("src")[0]
      + "/CustomConfigSource.json";
  private final String INV_MAINTENANCE_PROP = "io.openliberty.guides.microprofile.inventory.inMaintenance";
  private final String TEST_OVERWRITE_PROP = "io.openliberty.guides.microprofile.inventory.testConfigOverwrite";

  @Before
  public void setup() {
    port = System.getProperty("liberty.test.port");
    baseUrl = "http://localhost:" + port + "/";

    client = ClientBuilder.newClient();
    client.register(JsrJsonpProvider.class);
  }

  @After
  public void teardown() {
    client.close();
  }

  // tag::testSuite[]
  
  // end::testSuite[]

  // tag::testInitialServiceStatus()[]

  // end::testInitialServiceStatus()[]

  // tag::testOverrideConfigProperty()[]

  // end::testOverrideConfigProperty()[]

  // tag::testPutServiceInMaintenance()[]

  // end::testPutServiceInMaintenance()[]


  /**
   * Get the Json Object from the URL provided.
   */
  private JsonObject getJsonObjectFromURL(String url, int level) {
    Response response = client.target(url).request().get();
    assertEquals("Incorrect response code from " + url, 200,
        response.getStatus());
    JsonObject obj = response.readEntity(JsonObject.class);
    JsonObject result = null;
    if (level == 1) {
      result = obj;
    } else if (level == 2) {
      JsonObject properties = obj.getJsonObject("ConfigProperties");
      result = properties;
    }
    response.close();
    return result;
  }

}
// end::class[]
