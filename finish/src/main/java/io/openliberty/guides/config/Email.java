// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
// end::copyright[]

package io.openliberty.guides.config;

// tag::email[]
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
