/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoAppl implements IStrategoAppl {

    private final IStrategoTerm[] kids;
    private final IStrategoConstructor ctor;
    
    BasicStrategoAppl(IStrategoConstructor ctor, IStrategoTerm[] kids) {
        this.ctor = ctor;
        this.kids = kids;
    }
    
    public IStrategoTerm[] getArguments() {
        return kids;
    }

    public IStrategoConstructor getConstructor() {
        return ctor;
    }

    public IStrategoTerm[] getAllSubterms() {
        IStrategoTerm[] cloned = new IStrategoTerm[kids.length];
        for(int i = 0; i < cloned.length; i++)
            cloned[i] = kids[i];
        return cloned;
    }

    public IStrategoTerm getSubterm(int index) {
        return kids[index];
    }

    public int getSubtermCount() {
        return kids.length;
    }

    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }

    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.APPL)
            return false;
        IStrategoAppl o = (IStrategoAppl)second;
        if(!ctor.match(o.getConstructor()))
            return false;
        for(int i = 0, sz = kids.length; i < sz; i++) {
            if(!kids[i].match(second.getSubterm(i))) {
                return false;
            }
        }
        return true;
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(ctor.getName());
        if(kids.length > 0) {
            pp.println("(");
            pp.indent(ctor.getName().length());
            kids[0].prettyPrint(pp);
            for(int i = 1; i < kids.length; i++) {
                pp.print(", ");
                kids[i].prettyPrint(pp);
            }
            pp.println(")");
            pp.outdent(ctor.getName().length());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm) obj);
    }
}