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
// tag::manager[]
package io.openliberty.guides.inventory;

import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import io.openliberty.guides.inventory.model.InventoryList;

@ApplicationScoped
public class InventoryManager {

  private InventoryList invList = new InventoryList();
  private InventoryUtils invUtils = new InventoryUtils();

  public Properties get(String hostname, int portNumber) {

    Properties properties = invUtils.getPropertiesWithGivenHostName(hostname,
        portNumber);

    if (properties != null) {
      invList.addToInventoryList(hostname, properties);
    }
    return properties;
  }

  public InventoryList list() {
    return invList;
  }

}
// end::manager[]
