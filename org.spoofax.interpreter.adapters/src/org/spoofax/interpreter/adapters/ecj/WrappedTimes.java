/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTimes extends WrappedAppl {

    private final InfixExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Times", 2);

    public WrappedTimes(InfixExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrapExpression(wrappee.getLeftOperand());
        case 1:
            return WrappedECJFactory.wrapExpression(wrappee.getRightOperand());
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}