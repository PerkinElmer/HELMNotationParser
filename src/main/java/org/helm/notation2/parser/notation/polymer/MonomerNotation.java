/**
 * *****************************************************************************
 * Copyright C 2015, The Pistoia Alliance
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *****************************************************************************
 */
package org.helm.notation2.parser.notation.polymer;

import org.helm.notation2.parser.exceptionparser.HELM1ConverterException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * MonomerNotation
 *
 *
 * @author shecht
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "classType")
public abstract class MonomerNotation {
  protected String unit;

  protected String annotation;

  protected String count = String.valueOf(1);

  protected boolean isDefault = true;

  protected boolean isAnnotationHere = false;

  protected String type;

  public MonomerNotation() {

  }

  /**
   * Constructs with the given string
   *
   * @param str Monomer
   * @param type Type of polymer
   */
  public MonomerNotation(String str, String type) {
    this.unit = str;
    this.type = type;
    if (str.equals("?")) {
      setCount("0..n");
      isDefault = true;
    }
    if (str.equals("_")) {
      setCount("0");
      isDefault = true;
    }
    if (str.equals("*")) {
      setCount("0..n");
      isDefault = true;
    }

  }

  /**
   * method to get the ID of the Monomer
   *
   * @return ID
   */

  public String getUnit() {
    return unit;
  }

  /**
   * method to add annotation to this monomer
   *
   * @param str annotation
   */
  public void setAnnotation(String str) {
    if (str != null) {
      annotation = str;
      isAnnotationHere = true;
    } else {
      annotation = null;
      isAnnotationHere = false;
    }
  }

  /**
   * method to change the default count of one to the user-defined
   *
   * @param str user-defined count
   */
  public void setCount(String str) {
    isDefault = false;

    if (this.unit.equals("?")&&str.equals("0..n")) {
	    isDefault = true;
	}
    else if (this.unit.equals("_")&&str.equals("0")) {
	    isDefault = true;
	}
    else if (this.unit.equals("*")&&str.equals("0..n")) {
	    isDefault = true;
	}
    else if(str.equals("1")) {
        isDefault = true;
    }


    count = str;
  }

  /**
   * method to get the count of the monomer
   *
   * @return count
   */
  public String getCount() {
    return count;
  }

  /**
   * method to get the annotation for this monomer
   *
   * @return annotation
   */
  public String getAnnotation() {
    return annotation;
  }

  /**
   * method to check if a annotation is there or not
   *
   * @return true, if the annotation is there, false otherwise
   */
  @JsonIgnore
  public boolean isAnnotationTrue() {
    return isAnnotationHere;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    if (isAnnotationTrue()) {
      return "Monomer unit: " + unit + "\nCount: " + count + "\nAnnotation: " + annotation;
    } else {
      return "Monomer unit: " + unit + "\nCount: " + count;
    }
  }

  /**
   * method to generate a valid HELM2
   *
   * @return valid HELM2
   */
  public abstract String toHELM2();

  /**
   * method to get the Type of Polymer
   *
   * @return
   */
  public String getType() {
    return this.type;
  }

  /**
   * method to generate a valid HELM
   *
   * @return
   * @throws HELM1ConverterException
   */
  public abstract String toHELM() throws HELM1ConverterException;

}
