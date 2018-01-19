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

/*
 * ===========================================================================
 *  HELPER METHODS
 * ===========================================================================
 *
 */
public class ConfigurationTestUtil {
  private final static String INV_MAINTENANCE = "io_openliberty_guides_inventory_inMaintenance";
  private final static String SYS_MAINTENANCE = "io_openliberty_guides_system_inMaintenance";
  private final static String CONFIG_ORDINAL = "config_ordinal";

  public static void setDefaultJsonFile(String source) {
    changeLineInJsonFile(INV_MAINTENANCE, "true", "false", source);
    changeLineInJsonFile(SYS_MAINTENANCE, "true", "false", source);
    changeLineInJsonFile(CONFIG_ORDINAL, "700", "500", source);
  }

  /**
   * Change config_ordinal value for the config source.
   */
  public static void changeConfigSourcePriority(String source, String oldValue,
      String newValue) {
    changeLineInJsonFile(CONFIG_ORDINAL, oldValue, newValue, source);
  }

  /**
   * Change the inventory.inMaintenance value for the config source.
   */
  public static void switchInventoryMaintenance(String source, String oldValue,
      String newValue) {
    changeLineInJsonFile(INV_MAINTENANCE, oldValue, newValue, source);
  }

  /**
   * Change one value in Json file.
   */
  public static void changeLineInJsonFile(String property, String oldValue,
      String newValue, String source) {
    changeLineInFile(property + "\":\\s*" + oldValue,
                     property + "\": " + newValue, source);
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
   * Rewrite one line in a local file.
   */
  public static void changeLineInFile(String oldLine, String newLine,
      String fileName) {
    try {
      File f = new File(fileName);
      if (f.exists()) {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = "", oldContent = "", newContent = "";
        while ((line = reader.readLine()) != null) {
          oldContent += line + "\r\n";
        }
        reader.close();
        newContent = oldContent.replaceAll(oldLine, newLine);
        FileWriter writer = new FileWriter(fileName);
        writer.write(newContent);
        writer.close();
      } else {
        System.out.println("File " + fileName + " does not exist");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
