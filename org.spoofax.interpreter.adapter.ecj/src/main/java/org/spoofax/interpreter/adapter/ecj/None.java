/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class None extends WrappedASTNode {

    private static final long serialVersionUID = 1L;
    
    private final static IStrategoTerm[] EMPTY = new IStrategoTerm[0];
    private final static IStrategoConstructor CTOR = new ASTCtor("None", 0); 
    
    final static None INSTANCE = new None();
    
    protected None() {
        super(CTOR);
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return null;
    }

/*    
    @Override
    public IStrategoTerm[] getArguments() {
        return EMPTY;
    }
*/
    
    @Override
    public IStrategoTerm[] getAllSubterms() {
        return EMPTY;
    }
}
