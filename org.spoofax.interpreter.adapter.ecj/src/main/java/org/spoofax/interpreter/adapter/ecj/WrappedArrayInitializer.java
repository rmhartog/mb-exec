/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedArrayInitializer extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final ArrayInitializer wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ArrayInitializer", 1);
    
    WrappedArrayInitializer(ArrayInitializer wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.expressions());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ArrayInitializer getWrappee() {
        return wrappee;
    }

}
