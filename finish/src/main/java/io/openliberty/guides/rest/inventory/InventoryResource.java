// tag::copyright[]
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
 // end::copyright[]
package io.openliberty.guides.rest.inventory;

// CDI
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

// JSON-P
import javax.json.JsonObject;

// JAX-RS
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.openliberty.guides.rest.util.JsonMessages;
import io.openliberty.guides.rest.inventory.InventoryConfig;

@RequestScoped
@Path("hosts")
public class InventoryResource {

    @Inject
    InventoryManager manager;

    // tag::config-injection[]
    @Inject
    InventoryConfig inventoryConfig;
    // end::config-injection[]

    @GET
    @Path("{hostname}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getPropertiesForHost(@PathParam("hostname") String hostname) {

      if (!inventoryConfig.isInvInMaintenance()) {
        return manager.get(hostname, inventoryConfig.getPortNumber());
      } else {
          // A use case of custom converter for Email class type
        return JsonMessages.returnMessage("InventoryResource", inventoryConfig.getEmail());
      }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject listContents() {
      if (!inventoryConfig.isInvInMaintenance()) {
        return manager.list();
      } else {
          // A use case of custom converter for Email class type
        return JsonMessages.returnMessage("InventoryResource", inventoryConfig.getEmail());
      }

    }



}
