/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedModifier extends WrappedASTNode implements IWrappedExtendedModifier {

    private static final long serialVersionUID = 1L;

    private final Modifier wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("Modifier", 1);
    
    WrappedModifier(Modifier wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getKeyword());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Modifier getWrappee() {
        return wrappee;
    }
    
    public IExtendedModifier getModifierWrappee() {
    	return wrappee;
    }
}
