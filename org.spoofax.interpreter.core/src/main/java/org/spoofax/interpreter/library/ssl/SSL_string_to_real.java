/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_string_to_real extends AbstractPrimitive {

    protected SSL_string_to_real() {
        super("SSL_string_to_real", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!Tools.isTermString(targs[0]))
            return false;

        Double d = new Double(Tools.javaString(targs[0]));
        env.setCurrent(env.getFactory().makeReal(d.doubleValue()));
        return true;
    }
}
