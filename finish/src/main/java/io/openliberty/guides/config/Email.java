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
// tag::email[]
package io.openliberty.guides.config;

public class Email {
  private String name;
  private String domain;

  public Email(String value) {
    String[] components = value.split("@");
    if (components.length == 2) {
      name = components[0];
      domain = components[1];
    }
  }

  public String getEmailName() {
    return name;
  }

  public String getEmailDomain() {
    return domain;
  }

  public String toString() {
    return name + "@" + domain;
  }
}
// end::email[]
