/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedArrayType extends WrappedAppl {

    private final ArrayType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ArrayType", 3);
    
    WrappedArrayType(ArrayType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrapType(wrappee.getComponentType());
        case 1:
            return WrappedECJFactory.wrap(wrappee.getDimensions());
        case 2:
            return WrappedECJFactory.wrapType(wrappee.getElementType());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

}