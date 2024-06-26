/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.filter.v1_1;

import static org.junit.Assert.assertEquals;

import org.geotools.api.filter.And;
import org.geotools.api.filter.BinaryLogicOperator;
import org.geotools.api.filter.Or;
import org.geotools.xsd.Binding;
import org.junit.Test;
import org.w3c.dom.Document;

public class BinaryLogicOpTypeBindingTest extends FilterTestSupport {
    @Test
    public void testBinaryLogicOpType() {
        assertEquals(BinaryLogicOperator.class, binding(OGC.BinaryLogicOpType).getType());
    }

    @Test
    public void testAndType() {
        assertEquals(And.class, binding(OGC.And).getType());
    }

    @Test
    public void testAndExecutionMode() {
        assertEquals(Binding.AFTER, binding(OGC.And).getExecutionMode());
    }

    @Test
    public void testAndParse() throws Exception {
        FilterMockData.and(document, document);

        And and = (And) parse();

        assertEquals(2, and.getChildren().size());
    }

    @Test
    public void testAndEncode() throws Exception {
        Document dom = encode(FilterMockData.and(), OGC.And);
        assertEquals(
                1,
                dom.getElementsByTagNameNS(OGC.NAMESPACE, OGC.PropertyIsEqualTo.getLocalPart())
                        .getLength());
        assertEquals(
                1,
                dom.getElementsByTagNameNS(OGC.NAMESPACE, OGC.PropertyIsNotEqualTo.getLocalPart())
                        .getLength());
    }

    @Test
    public void testOrType() {
        assertEquals(Or.class, binding(OGC.Or).getType());
    }

    @Test
    public void testOrExecutionMode() {
        assertEquals(Binding.AFTER, binding(OGC.Or).getExecutionMode());
    }

    @Test
    public void testOrParse() throws Exception {
        FilterMockData.or(document, document);

        Or or = (Or) parse();

        assertEquals(2, or.getChildren().size());
    }

    @Test
    public void testOrEncode() throws Exception {
        Document dom = encode(FilterMockData.or(), OGC.Or);
        assertEquals(
                1,
                dom.getElementsByTagNameNS(OGC.NAMESPACE, OGC.PropertyIsEqualTo.getLocalPart())
                        .getLength());
        assertEquals(
                1,
                dom.getElementsByTagNameNS(OGC.NAMESPACE, OGC.PropertyIsNotEqualTo.getLocalPart())
                        .getLength());
    }
}
