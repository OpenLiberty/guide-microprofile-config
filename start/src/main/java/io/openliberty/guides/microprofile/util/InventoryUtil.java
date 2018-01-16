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
package io.openliberty.guides.microprofile.util;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;

public class InventoryUtil {
  private static final String PROTOCOL = "http";
  private static final String SYSTEM_PROPERTIES = "/system/properties";

  public static JsonObject getProperties(String hostname, int port) {
    Client client = ClientBuilder.newClient();
    URI propURI = InventoryUtil.buildUri(hostname, port);
    return client.target(propURI).request().get(JsonObject.class);
  }

  public static boolean responseOk(String hostname, int port) {
    try {
      URL target = new URL(InventoryUtil.buildUri(hostname, port).toString());
      HttpURLConnection http = (HttpURLConnection) target.openConnection();
      http.setConnectTimeout(50);
      int response = http.getResponseCode();
      return (response != 200) ? false : true;
    } catch (Exception e) {
      return false;
    }
  }

  private static URI buildUri(String hostname, int portNumber) {
    return UriBuilder.fromUri(SYSTEM_PROPERTIES).host(hostname).port(portNumber)
                     .scheme(PROTOCOL).build();
  }

}
