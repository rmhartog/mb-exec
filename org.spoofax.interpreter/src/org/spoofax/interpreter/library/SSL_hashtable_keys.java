/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_hashtable_keys extends Primitive {

    protected SSL_hashtable_keys() {
        super("SSL_hashtable_keys", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermInt(targs[0])))
            return false;

        SSL or = (SSL) env.getOperatorRegistry(SSL.REGISTRY_NAME);
        Hashtable ath = or.getHashtable(Tools.javaInt(targs[0]));
        if(ath == null)
            return false;
        
        env.setCurrent(env.getFactory().makeList(ath.keySet()));
        return true;
    }
}
