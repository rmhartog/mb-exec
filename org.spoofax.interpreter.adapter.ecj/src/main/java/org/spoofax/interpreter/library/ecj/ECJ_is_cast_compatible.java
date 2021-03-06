/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.adapter.ecj.WrappedITypeBinding;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_is_cast_compatible extends AbstractPrimitive {

    protected ECJ_is_cast_compatible() {
        super("ECJ_is_cast_compatible", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!(tvars[0] instanceof WrappedITypeBinding))
            return true;
        if(!(tvars[1] instanceof WrappedITypeBinding))
            return true;
            
        WrappedITypeBinding wb0 = (WrappedITypeBinding) tvars[0];
        ITypeBinding b0 = wb0.getWrappee();

        WrappedITypeBinding wb1 = (WrappedITypeBinding) tvars[0];
        ITypeBinding b1 = wb1.getWrappee();
        
        return b0.isCastCompatible(b1);
    }

}
