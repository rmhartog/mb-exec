/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ArrayCreation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedArrayCreation extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final ArrayCreation wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ArrayCreation", 3);
    
    WrappedArrayCreation(ArrayCreation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getType());
        case 1:
            return ECJFactory.wrap(wrappee.dimensions());
        case 2:
            return ECJFactory.wrapExpression(wrappee.getInitializer());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ArrayCreation getWrappee() {
        return wrappee;
    }

}
