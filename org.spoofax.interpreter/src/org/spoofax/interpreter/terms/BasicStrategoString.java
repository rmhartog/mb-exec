/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public class BasicStrategoString implements IStrategoString {

    protected final String value;
    
    protected BasicStrategoString(String value) {
        this.value = value;
    }
    
    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }
    
    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.STRING;
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }
    
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.STRING)
            return false;
        
        IStrategoString snd = (IStrategoString) second;
        return value.equals(snd.getValue());
    }

    public String getValue() {
        return value;
    }
    
    public void prettyPrint(ITermPrinter pp) {
        pp.print("\"" +  value + "\"");
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm)obj);
    }
    
    @Override
    public String toString() {
        return value; 
    }
}
