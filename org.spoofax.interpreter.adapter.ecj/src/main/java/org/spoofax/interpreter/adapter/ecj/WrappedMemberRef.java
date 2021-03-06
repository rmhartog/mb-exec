/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.MemberRef;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMemberRef extends WrappedASTNode {

    private static final long serialVersionUID = 1L;

    private final MemberRef wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("MemberRef", 2);
    
    WrappedMemberRef(MemberRef wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getName());
        case 1:
            return ECJFactory.wrapName(wrappee.getQualifier());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public MemberRef getWrappee() {
        return wrappee;
    }
}
