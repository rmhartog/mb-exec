/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSingleVariableDeclaration extends WrappedAppl {

    private final SingleVariableDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SingleVariableDeclaration", 1);
    
    WrappedSingleVariableDeclaration(SingleVariableDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.getModifiers());
        case 1:
            return WrappedECJFactory.wrapType(wrappee.getType());
        case 2:
            return WrappedECJFactory.wrapName(wrappee.getName());
        case 3:
            return WrappedECJFactory.wrapExpression(wrappee.getInitializer());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

    public IStrategoTerm[] getArguments() {
        throw new NotImplementedException();
    }

}
