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

public class SSL_strcat extends AbstractPrimitive {

    protected SSL_strcat() {
        super("SSL_strcat", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!Tools.isTermString(targs[0]))
            return false;
        if(!Tools.isTermString(targs[1]))
            return false;

        String s = Tools.javaString(targs[0]);
        String t = Tools.javaString(targs[1]);
        
        env.setCurrent(env.getFactory().makeString(s + t));
        return true;
    }
}
