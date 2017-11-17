//tag::comment[]
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
package io.openliberty.guides.microprofile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.openliberty.guides.microprofile.util.JsonMessages;
import io.openliberty.guides.config.Email;

@RequestScoped
@Path("hosts")
public class InventoryResource {

  @Inject
  InventoryManager manager;

  // tag::config-injection[]
  @Inject
  InventoryConfig inventoryConfig;
  // end::config-injection[]

  // tag::config-methods[]
  @GET
  @Path("{hostname}")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject getPropertiesForHost(
      @PathParam("hostname") String hostname) {
    if (!inventoryConfig.isInMaintenance()) {
      return manager.get(hostname);
    } else {
      return returnMessage();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject listContents() {
    if (!inventoryConfig.isInMaintenance()) {
      return manager.list();
    } else {
      return returnMessage();
    }
  }
  // end::config-methods[]

  // tag::returnMessage[]
  public JsonObject returnMessage() {
    // A use case of custom converter for Email class type
    Email devEmail = inventoryConfig.getEmail();
    JsonObject contact = Json.createObjectBuilder()
                             .add("Email", devEmail.toString())
                             .add("Name", devEmail.getEmailName())
                             .add("Domain", devEmail.getEmailDomain()).build();
    return Json.createObjectBuilder()
               .add("Message",
                    JsonMessages.serviceInMaintenance("InventoryResource"))
               .add("Contact", contact).build();
  }
  // end::returnMessage[]

}
