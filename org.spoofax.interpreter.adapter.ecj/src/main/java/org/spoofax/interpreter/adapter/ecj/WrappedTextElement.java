/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.TextElement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTextElement extends WrappedASTNode {

    private static final long serialVersionUID = 1L;

    private final TextElement wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("TextElement", 1);
    
    WrappedTextElement(TextElement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getText());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TextElement getWrappee() {
        return wrappee;
    }
}
