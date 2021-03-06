/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IJavaProject;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIJavaProject extends WrappedIJavaElement {
    
    private static final long serialVersionUID = 1L;

    private final IJavaProject wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ECJJavaProject", 2);
    
    WrappedIJavaProject(IJavaProject wrappee) {
        super(CTOR, wrappee);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getElementName());
        case 1:
            return ECJFactory.wrap(wrappee.hashCode());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public IJavaProject getWrappee() {
        return wrappee;
    }

}
