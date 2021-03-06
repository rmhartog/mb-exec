/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Assignment;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedAssignment extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final Assignment wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("Assignment", 3);
    
    WrappedAssignment(Assignment wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
        	return ECJFactory.wrap(wrappee.getOperator());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getLeftHandSide());
        case 2:
            return ECJFactory.wrapExpression(wrappee.getRightHandSide());
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Assignment getWrappee() {
        return wrappee;
    }

}
