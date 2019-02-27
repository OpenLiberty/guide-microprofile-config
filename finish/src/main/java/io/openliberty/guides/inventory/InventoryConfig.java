// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
// tag::config-class[]
package io.openliberty.guides.inventory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.openliberty.guides.config.Email;

@RequestScoped
public class InventoryConfig {

  // tag::port-number[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_port_number")
  private int portNumber;

  // end::port-number[]
  // tag::build-in-converter[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_inventory_inMaintenance")
  private Provider<Boolean> inMaintenance;

  // end::build-in-converter[]
  // tag::custom-converter[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_email")
  private Provider<Email> email;

  // end::custom-converter[]
  // tag::getPortNumber[]
  public int getPortNumber() {
    return portNumber;
  }

  // end::getPortNumber[]
  // tag::isInMaintenance[]
  public boolean isInMaintenance() {
    return inMaintenance.get();
  }

  // end::isInMaintenance[]
  // tag::getEmail[]
  public Email getEmail() {
    return email.get();
  }
  // end::getEmail[]
}
// end::config-class[]
