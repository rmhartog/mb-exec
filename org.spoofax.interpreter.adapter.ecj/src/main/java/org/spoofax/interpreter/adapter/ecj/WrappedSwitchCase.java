/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.SwitchCase;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSwitchCase extends WrappedStatement {

    private static final long serialVersionUID = 1L;

    // FIXME default
    
    private final SwitchCase wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("SwitchCase", 1);
    
    WrappedSwitchCase(SwitchCase wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapExpression(wrappee.getExpression());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SwitchCase getWrappee() {
        return wrappee;
    }
}
