/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.runtime.CoreException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_create_project extends AbstractPrimitive {

    protected ECJ_create_project() {
        super("ECJ_create_project", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        ECJLibrary lib = ECJLibrary.registry(env);
        ProjectUtils pu = lib.getProjectUtils();
        if(!Tools.isTermString(tvars[0]))
            return false;
        IStrategoString s = (IStrategoString) tvars[0];
        try {
            env.setCurrent(ECJFactory.wrap(pu.createProject(s.stringValue(), false)));
        } catch(CoreException e) {
            return ECJLibrary.invokeExceptionHandler(env, e);
        }
        return true;
    }

}
