/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ArrayAccess;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedArrayAccess extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final ArrayAccess wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ArrayAccess", 2);
    
    WrappedArrayAccess(ArrayAccess wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getArray());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getIndex());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ArrayAccess getWrappee() {
        return wrappee;
    }

}
