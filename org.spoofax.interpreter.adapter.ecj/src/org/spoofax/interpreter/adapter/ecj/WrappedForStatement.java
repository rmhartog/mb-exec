/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ForStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedForStatement extends WrappedStatement {

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
            return ECJFactory.wrap(wrappee.initializers());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getExpression());
        case 2:
            return ECJFactory.wrap(wrappee.updaters());
        case 3:
            return ECJFactory.wrapStatement(wrappee.getBody());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ForStatement getWrappee() {
        return wrappee;
    }
}