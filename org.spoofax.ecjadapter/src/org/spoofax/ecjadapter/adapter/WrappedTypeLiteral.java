/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.TypeLiteral;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeLiteral extends WrappedStatement {

    private final TypeLiteral wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeLiteral", 1);
    
    WrappedTypeLiteral(TypeLiteral wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapType(wrappee.getType());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TypeLiteral getWrappee() {
        return wrappee;
    }

}
