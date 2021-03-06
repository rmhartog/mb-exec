/*
 * Created on 25. des.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedAbstractTypeDeclaration extends WrappedBodyDeclaration {

    private static final long serialVersionUID = 1L;

    protected WrappedAbstractTypeDeclaration(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract AbstractTypeDeclaration getWrappee();
}
