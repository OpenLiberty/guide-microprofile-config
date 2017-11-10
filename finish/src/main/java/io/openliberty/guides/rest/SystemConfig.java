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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;
import javax.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@RequestScoped
public class SystemConfig {

  @Inject
  Config config;

  // tag::config[]
  @Inject
  @ConfigProperty(name = "io.openliberty.guides.microprofile.system.inMaintenance")
  Provider<String> inMaintenance;
  // end::config[]

  public boolean isInMaintenance() {
    String propValue = inMaintenance.get();
    return Boolean.parseBoolean(propValue);
  }

}
