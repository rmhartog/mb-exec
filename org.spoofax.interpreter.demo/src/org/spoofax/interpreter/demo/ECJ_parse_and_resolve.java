/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import java.util.List;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_parse_and_resolve extends Primitive {

    public ECJ_parse_and_resolve() {
        super("ECJ_parse_and_resolve", 0, 2);
    }
    @Override
    public boolean call(IContext env, List<Strategy> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
