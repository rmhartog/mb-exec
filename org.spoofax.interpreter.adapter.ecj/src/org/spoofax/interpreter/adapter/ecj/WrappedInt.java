/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoInt;

public class WrappedInt extends BasicStrategoInt {

    WrappedInt(int value) {
        super(value);
    }
}
