/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ForStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedForStatement extends WrappedAppl {

    private final ForStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ForStatement", 4);
    
    WrappedForStatement(ForStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.initializers());
        case 1:
            return WrappedECJFactory.wrapExpression(wrappee.getExpression());
        case 2:
            return WrappedECJFactory.wrap(wrappee.updaters());
        case 3:
            return WrappedECJFactory.wrapStatement(wrappee.getBody());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}