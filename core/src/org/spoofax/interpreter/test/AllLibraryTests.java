/*
 * Created on 02.mar.2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllLibraryTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.spoofax.interpreter.test");
        //$JUnit-BEGIN$
        suite.addTestSuite(BasicLibraryTest.class);
        suite.addTestSuite(LibraryTest.class);
        //$JUnit-END$
        return suite;
    }

}
