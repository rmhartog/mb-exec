/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.test;

public class LanguageTest extends InterpreterTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp("tests/data/lang/");
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(LanguageTest.class);
    }
}
