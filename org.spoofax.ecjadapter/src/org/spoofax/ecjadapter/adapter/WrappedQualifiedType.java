/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.QualifiedType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedQualifiedType extends WrappedType {

    private final QualifiedType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("QualifiedType", 2);
    
    WrappedQualifiedType(QualifiedType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getName());
        case 1:
            return ECJFactory.wrapType(wrappee.getQualifier());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public QualifiedType getWrappee() {
        return wrappee;
    }
}
