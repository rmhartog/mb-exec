/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.util.Collection;
import java.util.List;


public interface IStrategoTermBuilder {
    
    // FIXME remote quotation param: use IStrategoString instead
    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted);

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids);
    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms);

    public IStrategoInt makeInt(int i);
    public IStrategoReal makeReal(double d);
    public IStrategoTuple makeTuple(IStrategoTerm... terms);
    public IStrategoString makeString(String s);
    public IStrategoList makeList(IStrategoTerm... terms);
    public IStrategoList makeList(List<IStrategoTerm> terms);

    public boolean hasConstructor(String s, int i);
    
}


