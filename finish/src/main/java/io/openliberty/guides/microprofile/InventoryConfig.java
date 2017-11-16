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
package io.openliberty.guides.microprofile;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.Json;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import javax.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.openliberty.guides.config.Email;

@ApplicationScoped
@Path("config")
public class InventoryConfig {

  // tag::config[]
  @Inject
  private Config config;

  // tag::build-in-converter[]
  @Inject
  @ConfigProperty(
    name = "io.openliberty.guides.microprofile.inventory.inMaintenance")
  private Provider<Boolean> inMaintenance;
  //end::build-in-converter[]
  // end::config[]

  // tag::custom-converter[]
  @Inject
  @ConfigProperty(name = "io.openliberty.guides.microprofile.email")
  private Provider<Email> email;
  // end::custom-converter[]

  @Inject
  @ConfigProperty(name = "io.openliberty.guides.microprofile.port")
  private static int portNumber;

  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject getAllConfig() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    return builder.add("ConfigSources", sourceJsonBuilder())
                  .add("ConfigProperties", propertyJsonBuilder())
                  .build();
  }

  public JsonObject sourceJsonBuilder() {
    JsonObjectBuilder sourcesBuilder = Json.createObjectBuilder();
    for (ConfigSource source : config.getConfigSources()) {
      sourcesBuilder.add(source.getName(), source.getOrdinal());
    }
    return sourcesBuilder.build();
  }

  // tag::propertyJsonBuilder[]
  public JsonObject propertyJsonBuilder() {
    JsonObjectBuilder propertiesBuilder = Json.createObjectBuilder();
    for (String name : config.getPropertyNames()) {
      if (name.contains("io.openliberty.guides.microprofile")) {
        propertiesBuilder.add(name, config.getValue(name, String.class));
      }
    }
    // A use case of custom converter for Email class type
    Email devEmail = email.get();
    propertiesBuilder.add("Name", devEmail.getEmailName()).add("Domain", devEmail.getEmailDomain());
    return propertiesBuilder.build();
  }
  // end::propertyJsonBuilder[]

  // tag::getPortNumber[]
  public static int getPortNumber() {
    return portNumber;
  }
  // end::getPortNumber[]

  // tag::isInMaintenance[]
  public boolean isInMaintenance() {
    return inMaintenance.get();
  }
  // end::isInMaintenance[]

}
