/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedVariableDeclarationFragment extends WrappedAppl {

    private final VariableDeclarationFragment wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("VariableDeclarationFragment", 3); 
    
    WrappedVariableDeclarationFragment(VariableDeclarationFragment wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.getName());
        case 1:
            return WrappedECJFactory.wrap(wrappee.getExtraDimensions());
        case 2:
            return WrappedECJFactory.wrapExpression(wrappee.getInitializer());
        
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

    public IStrategoTerm[] getArguments() {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
