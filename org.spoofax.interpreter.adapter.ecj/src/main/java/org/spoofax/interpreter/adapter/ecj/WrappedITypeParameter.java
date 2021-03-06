/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedITypeParameter extends AbstractECJAppl {

    private static final long serialVersionUID = 1L;
    
    private final ITypeParameter wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ITypeParameter", 2);
    
    WrappedITypeParameter(ITypeParameter wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    public ITypeParameter getWrappee() {
        return wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
        	return ECJFactory.wrap(wrappee.getElementName());
        case 1:
        	try {
        		return ECJFactory.wrap(wrappee.getBounds());
        	} catch(JavaModelException e) {
        		e.printStackTrace();
        		return None.INSTANCE;
        	}
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
