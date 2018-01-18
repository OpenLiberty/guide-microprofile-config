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

package io.openliberty.guides.config;

import org.eclipse.microprofile.config.spi.Converter;
import io.openliberty.guides.config.Email;

// tag::customConfig[]
public class CustomEmailConverter implements Converter<Email> {

  @Override
  public Email convert(String value) {
    return new Email(value);
  }
}
// end::customConfig[]
