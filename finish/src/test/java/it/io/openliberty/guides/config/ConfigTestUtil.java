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
package it.io.openliberty.guides.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.json.bind.*;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import it.io.openliberty.guides.config.CustomConfig;

/*
 * ===========================================================================
 *  HELPER METHODS
 * ===========================================================================
 *
 */
public class ConfigTestUtil {
  private final static String EMAIL = "admin@guides.openliberty.io";
  private final static String TEST_CONFIG = "CustomSource";


  public static void setDefaultJsonFile(String source) {
    CustomConfig config = new CustomConfig(50, false, false, EMAIL, TEST_CONFIG);
    createJsonOverwrite(source, config);
  }

  /**
   * Change config_ordinal value for the config source.
   */
  public static void changeConfigSourcePriority(String source, int newValue) {
    CustomConfig config = new CustomConfig(newValue, false, false, EMAIL, TEST_CONFIG);
    createJsonOverwrite(source, config);
  }

  /**
   * Change the inventory.inMaintenance value for the config source.
   */
  public static void switchInventoryMaintenance(String source, boolean newValue) {
    CustomConfig config = new CustomConfig(150, newValue, false, EMAIL, TEST_CONFIG);
    createJsonOverwrite(source, config);
  }


  public static void createJsonOverwrite(String source, CustomConfig config){
    // Create Jsonb and serialize
    Jsonb jsonb = JsonbBuilder.create();
    String result = jsonb.toJson(config);
    overwriteFile(source, result);
  }

  /**
   * Read the property values from a local file.
   */
  public static String readPropertyValueInFile(String propName,
      String fileName) {
    String propValue = "";
    String line = "";
    try {
      File f = new File(fileName);
      if (f.exists()) {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        while ((line = reader.readLine()) != null) {
          if (line.contains(propName)) {
            propValue = line.split("=")[1];
          }
        }
        reader.close();
        return propValue;
      } else {
        System.out.println("File " + fileName + " does not exist...");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return propValue;
  }

  /**
   * Overwrite a local file.
   */
  public static void overwriteFile(String fileName, String newContent){
    try {
      File f = new File(fileName);
      if (f.exists()) {
        FileWriter fWriter = new FileWriter(f, false); // true to append
                                                       // false to overwrite.
        fWriter.write(newContent);
        fWriter.close();
      } else {
      System.out.println("File " + fileName + " does not exist");
    }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the Json Object from the URL provided.
   */
  public static JsonObject getJsonObjectFromURL(Client client, String url, int level, String key) {
    Response response = client.target(url).request().get();

    JsonObject obj = response.readEntity(JsonObject.class);
    JsonObject result = null;
    if (level == 1) {
      result = obj;
    } else if (level == 2) {
      JsonObject properties = obj.getJsonObject(key);
      result = properties;
    }
    response.close();
    return result;
  }

  public static String getStringFromURL(Client client, String url) {
    Response response = client.target(url).request().get();
    String result = response.readEntity(String.class);
    response.close();
    return result;
  }



}
