/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMethodDeclaration extends WrappedAppl {

    private final MethodDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("MethodDeclaration", 6); 
    
    WrappedMethodDeclaration(MethodDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.modifiers());
        case 1:
            return WrappedECJFactory.wrap(wrappee.typeParameters());
        case 2:
            return WrappedECJFactory.wrap(wrappee.getName());
        case 3:
            return WrappedECJFactory.wrap(wrappee.parameters());
        case 4:
            return WrappedECJFactory.wrap(wrappee.thrownExceptions());
        case 5:
            return WrappedECJFactory.wrap(wrappee.getBody());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}