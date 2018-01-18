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
package io.openliberty.guides.config;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.Json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.openliberty.guides.config.Email;

@RequestScoped
@Path("manager")
public class ConfigResource {

  // tag::config[]
  @Inject
  private Config config;
  // end::config[]

  // tag::port-number[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_port_number")
  private int portNumber;
  // end::port-number[]

  // tag::build-in-converter[]
  // tag::inv-in-maintenance[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_inventory_inMaintenance")
  private Provider<Boolean> invInMaintenance;
  // end::inv-in-maintenance[]
  // end::build-in-converter[]

  // tag::sys-in-maintenance[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_system_inMaintenance")
  private Provider<Boolean> sysInMaintenance;
  // end::sys-in-maintenance[]

  // tag::custom-converter[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_email")
  private Provider<Email> email;
  // end::custom-converter[]

  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject getAllConfig() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    return builder.add("ConfigSources", sourceJsonBuilder())
                  .add("ConfigProperties", propertyJsonBuilder()).build();
  }

  public JsonObject sourceJsonBuilder() {
    JsonObjectBuilder sourcesBuilder = Json.createObjectBuilder();
    for (ConfigSource source : config.getConfigSources()) {
      sourcesBuilder.add(source.getName(), source.getOrdinal());
    }
    return sourcesBuilder.build();
  }

  public JsonObject propertyJsonBuilder() {
    JsonObjectBuilder propertiesBuilder = Json.createObjectBuilder();
    for (String name : config.getPropertyNames()) {
      if (name.contains("io_openliberty_guides")) {
        propertiesBuilder.add(name, config.getValue(name, String.class));
      }
    }
    return propertiesBuilder.build();
  }

  // tag::getPortNumber[]
  public int getPortNumber() {
    return portNumber;
  }
  // end::getPortNumber[]

  // tag::isInvInMaintenance[]
  public boolean isInvInMaintenance() {
    System.out.println("Inventory is " + invInMaintenance.get());
    return invInMaintenance.get();
  }
  // end::isInvInMaintenance[]

  // tag::isSysInMaintenance[]
  public boolean isSysInMaintenance() {
    System.out.println("System is " + sysInMaintenance.get());
    return sysInMaintenance.get();
  }
  // end::isSysInMaintenance[]


  // tag::getEmail[]
  public Email getEmail() {
    System.out.println("Email is " + email.get());
    return email.get();
  }
  // end::getEmail[]

}
