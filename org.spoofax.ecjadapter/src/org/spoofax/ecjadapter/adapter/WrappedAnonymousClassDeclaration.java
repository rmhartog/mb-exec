/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedAnonymousClassDeclaration extends WrappedASTNode {

    private final AnonymousClassDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("AnonymousClassDeclaration", 1);
    
    WrappedAnonymousClassDeclaration(AnonymousClassDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.bodyDeclarations());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public AnonymousClassDeclaration getWrappee() {
        return wrappee;
    }

}
