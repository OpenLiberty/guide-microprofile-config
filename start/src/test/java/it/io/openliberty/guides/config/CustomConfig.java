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

package it.io.openliberty.guides.config;

public class CustomConfig {
  private int config_ordinal;
  private boolean io_openliberty_guides_inventory_inMaintenance;
  private boolean io_openliberty_guides_system_inMaintenance;
  private String io_openliberty_guides_email;
  private String io_openliberty_guides_testConfigOverwrite;

  public CustomConfig(int ordinal, boolean inventory, boolean system, String email, String testConfig) {
    this.setConfig_ordinal(ordinal);
    this.setIo_openliberty_guides_inventory_inMaintenance(inventory);
    this.setIo_openliberty_guides_system_inMaintenance(system);
    this.setIo_openliberty_guides_email(email);
    this.setIo_openliberty_guides_testConfigOverwrite(testConfig);
  }

  public int getConfig_ordinal() {
    return config_ordinal;
  }

  public void setConfig_ordinal(int config_ordinal) {
    this.config_ordinal = config_ordinal;
  }

  public boolean isIo_openliberty_guides_inventory_inMaintenance() {
    return io_openliberty_guides_inventory_inMaintenance;
  }

  public void setIo_openliberty_guides_inventory_inMaintenance(
      boolean io_openliberty_guides_inventory_inMaintenance) {
    this.io_openliberty_guides_inventory_inMaintenance = io_openliberty_guides_inventory_inMaintenance;
  }

  public boolean isIo_openliberty_guides_system_inMaintenance() {
    return io_openliberty_guides_system_inMaintenance;
  }

  public void setIo_openliberty_guides_system_inMaintenance(
      boolean io_openliberty_guides_system_inMaintenance) {
    this.io_openliberty_guides_system_inMaintenance = io_openliberty_guides_system_inMaintenance;
  }

  public String getIo_openliberty_guides_email() {
    return io_openliberty_guides_email;
  }

  public void setIo_openliberty_guides_email(
      String io_openliberty_guides_email) {
    this.io_openliberty_guides_email = io_openliberty_guides_email;
  }

  public String getIo_openliberty_guides_testConfigOverwrite() {
    return io_openliberty_guides_testConfigOverwrite;
  }

  public void setIo_openliberty_guides_testConfigOverwrite(
      String io_openliberty_guides_testConfigOverwrite) {
    this.io_openliberty_guides_testConfigOverwrite = io_openliberty_guides_testConfigOverwrite;
  }

}
