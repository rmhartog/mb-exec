/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.List;

import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_sqrt extends AbstractPrimitive {

    protected SSL_sqrt() {
        super("SSL_sqrt", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermReal(targs[0])))
            return false;

        IStrategoReal a = (IStrategoReal) targs[0];
        env.setCurrent(env.getFactory().makeReal(Math.sqrt(a.getValue())));
        return true;
    }
}
