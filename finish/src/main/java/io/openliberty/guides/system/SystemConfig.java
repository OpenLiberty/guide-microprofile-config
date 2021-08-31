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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.openliberty.guides.config.Email;

@RequestScoped
public class SystemConfig {

  // tag::build-in-converter[]
  // tag::inject-inMaintenance[]
  // tag::inject[]
  @Inject
  // end::inject[]
  // tag::configPropety[]
  @ConfigProperty(name = "io_openliberty_guides_inventory_inMaintenance")
  // end::configPropety[]
  // end::inject-inMaintenance[]
  private Provider<Boolean> inMaintenance;



  // tag::custom-converter[]
  @Inject
  @ConfigProperty(name = "io_openliberty_guides_email")
  private Provider<Email> email;
  // end::custom-converter[]

  // tag::isInMaintenance[]
  public boolean isInMaintenance() {
    // tag::inMaintenanceGet[]
    return inMaintenance.get();
    // end::inMaintenanceGet[]
  }
  // end::isInMaintenance[]

  // tag::getEmail[]
  public Email getEmail() {
    return email.get();
  }
  // end::getEmail[]

}
