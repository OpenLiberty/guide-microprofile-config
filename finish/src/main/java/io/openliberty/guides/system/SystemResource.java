// tag::copyright[]
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
// end::copyright[]
package io.openliberty.guides.system;

// CDI
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

// JAX-RS
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("properties")
public class SystemResource {

  // tag::config-injection[]
  @Inject
  SystemConfig systemConfig;
  // end::config-injection[]

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperties() {
    // tag::config-injection[]
    if (!systemConfig.isInMaintenance()) {
    // end::config-injection[]
      return Response.ok(System.getProperties()).build();
    // tag::config-injection[]
    } 
    // end::config-injection[]
    // tag::email[]
    else {
      return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                     .entity("ERROR: Service is currently in maintenance. Contact: "
                         + systemConfig.getEmail().toString())
                     .build();
    }
    // end::email[]
  }

}
