/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoReal extends BasicStrategoTerm implements IStrategoReal {

    private final double value;
    
    protected BasicStrategoReal(double value, IStrategoList annotations) {
        super(annotations);
        this.value = value;
    }
    
    protected BasicStrategoReal(double value) {
        this(value, null);
    }
    
    public double realValue() {
        return value;
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
        return IStrategoTerm.REAL;
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.REAL)
            return false;
        if (value != ((IStrategoReal)second).realValue())
            return false;
        return getAnnotations().match(second.getAnnotations());
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print("" + value);
    }
    
    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public int hashCode() {
        return (int)(449 * value) ^ 7841;
    }
}
