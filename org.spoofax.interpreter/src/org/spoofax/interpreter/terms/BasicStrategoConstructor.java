/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import org.spoofax.NotImplementedException;

public class BasicStrategoConstructor implements IStrategoConstructor {

    private final String name;
    private final int arity;
    private boolean quoted;

    BasicStrategoConstructor(String name, int arity, boolean quoted) {
        this.name = name;
        this.arity = arity;
        this.quoted = quoted;
    }
    
    public int getArity() {
        return arity;
    }

    public String getName() {
        return name;
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoTerm... kids) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoList kids) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }

    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.CTOR;
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }

    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.CTOR)
            return false;
        IStrategoConstructor o = (IStrategoConstructor)second;
        if(arity != o.getArity())
            return false;
        if(!name.equals(o.getName()))
            return false;
        return true;
    }

    public void prettyPrint(ITermPrinter pp) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm)obj);
    }
}