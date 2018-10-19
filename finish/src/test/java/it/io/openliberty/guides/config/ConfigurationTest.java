// tag::comment[]
/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::comment[]
// tag::test[]
package it.io.openliberty.guides.config;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

  private String port;
  private String baseUrl;
  private Client client;

  private final String INVENTORY_HOSTS = "inventory/systems";
  private final String USER_DIR = System.getProperty("user.dir");
  private final String DEFAULT_CONFIG_FILE = USER_DIR
      + "/src/main/resources/META-INF/microprofile-config.properties";
  private final String CUSTOM_CONFIG_FILE = USER_DIR.split("target")[0]
      + "/resources/CustomConfigSource.json";
  private final String INV_MAINTENANCE_PROP = "io_openliberty_guides_inventory_inMaintenance";

  @Before
  public void setup() {
    port = System.getProperty("liberty.test.port");
    baseUrl = "http://localhost:" + port + "/";
    ConfigTestUtil.setDefaultJsonFile(CUSTOM_CONFIG_FILE);

    client = ClientBuilder.newClient();
    client.register(JsrJsonpProvider.class);
  }

  @After
  public void teardown() {
    ConfigTestUtil.setDefaultJsonFile(CUSTOM_CONFIG_FILE);
    client.close();
  }

  // tag::testSuite[]
  @Test
  public void testSuite() {
    this.testInitialServiceStatus();
    this.testPutServiceInMaintenance();
    this.testChangeEmail();
  }
  // end::testSuite[]

  // tag::testInitialServiceStatus()[]
  public void testInitialServiceStatus() {
    boolean status = Boolean.valueOf(ConfigTestUtil.readPropertyValueInFile(
        INV_MAINTENANCE_PROP, DEFAULT_CONFIG_FILE));
    if (!status) {
        ConfigTestUtil.getJsonObjectFromURL(client, baseUrl + INVENTORY_HOSTS, 1,
            null, Optional.of(200));
    } else {
      assertEquals("The Inventory Service should be in maintenance",
          "ERROR: Service is currently in maintenance. Contact: admin@guides.openliberty.io",
          ConfigTestUtil.getStringFromURL(client, baseUrl + INVENTORY_HOSTS));
    }
  }
  // end::testInitialServiceStatus()[]

  // tag::testPutServiceInMaintenance()[]
  public void testPutServiceInMaintenance() {
    ConfigTestUtil.getJsonObjectFromURL(client,
        baseUrl + INVENTORY_HOSTS, 1, null, Optional.of(200));

    ConfigTestUtil.switchInventoryMaintenance(CUSTOM_CONFIG_FILE, true);

    String error = ConfigTestUtil.getStringFromURL(client,
        baseUrl + INVENTORY_HOSTS);

    assertEquals("The inventory service should be down in the end",
        "ERROR: Service is currently in maintenance. Contact: admin@guides.openliberty.io",
        error);
  }
  // end::testPutServiceInMaintenance()[]

  // tag::testChangeEmail()[]
  public void testChangeEmail() {
    ConfigTestUtil.switchInventoryMaintenance(CUSTOM_CONFIG_FILE, true);

    String error = ConfigTestUtil.getStringFromURL(client,
        baseUrl + INVENTORY_HOSTS);

    assertEquals("The email should be admin@guides.openliberty.io in the beginning",
        "ERROR: Service is currently in maintenance. Contact: admin@guides.openliberty.io",
        error);

    ConfigTestUtil.changeEmail(CUSTOM_CONFIG_FILE, "service@guides.openliberty.io");

    error = ConfigTestUtil.getStringFromURL(client,
        baseUrl + INVENTORY_HOSTS);

    assertEquals("The email should be service@guides.openliberty.io in the beginning",
        "ERROR: Service is currently in maintenance. Contact: service@guides.openliberty.io",
        error);
  }
  // end::testChangeEmail()[]

}
// end::test[]
