/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.DoStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedDoStatement extends WrappedStatement {
    
    private static final long serialVersionUID = 1L;

    private final DoStatement wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("DoStatement", 2);
    
    WrappedDoStatement(DoStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getExpression());
        case 1:
            return ECJFactory.wrapStatement(wrappee.getBody());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public DoStatement getWrappee() {
        return wrappee;
    }
}
