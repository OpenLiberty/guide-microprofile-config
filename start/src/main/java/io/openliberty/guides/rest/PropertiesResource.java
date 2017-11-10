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
package io.openliberty.guides.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import io.openliberty.guides.microprofile.util.JsonMessages;

@RequestScoped
@Path("properties")
public class PropertiesResource {

  @Inject
  SystemConfig systemConfig;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject getProperties() {
    if (!systemConfig.isInMaintenance()) {
      JsonObjectBuilder builder = Json.createObjectBuilder();

      System.getProperties()
            .entrySet()
            .stream()
            .forEach(entry -> builder.add((String)entry.getKey(),
                                          (String)entry.getValue()));

      return builder.build();
    } else {
      return JsonMessages.serviceInMaintenance("PropertiesResource");
    }

  }


}
