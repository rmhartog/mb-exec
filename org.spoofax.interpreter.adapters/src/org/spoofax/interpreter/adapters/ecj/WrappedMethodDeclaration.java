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
import org.spoofax.NotImplementedException;
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
            return new WrappedInt(wrappee.getModifiers());
        case 1:
            return new WrappedList(wrappee.typeParameters());
        case 2:
            return new WrappedSimpleName(wrappee.getName());
        case 3:
            return new WrappedList(wrappee.parameters());
        case 4:
            return new WrappedList(wrappee.thrownExceptions());
        case 5:
            return new WrappedBlock(wrappee.getBody());
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
