/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPackageDeclaration extends WrappedASTNode {

    private static final long serialVersionUID = 1L;

    private static final IStrategoConstructor CTOR = new ECJConstructor("PackageDeclaration", 3); 
    private final PackageDeclaration wrappee;
    
    protected WrappedPackageDeclaration(PackageDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getJavadoc());
        case 1:
            return ECJFactory.wrap(wrappee.annotations());
        case 2:
            return ECJFactory.wrapName(wrappee.getName());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public PackageDeclaration getWrappee() {
        return wrappee;
    }
}
