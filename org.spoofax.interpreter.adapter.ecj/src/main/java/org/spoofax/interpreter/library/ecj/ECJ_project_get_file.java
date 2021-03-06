/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_project_get_file extends ECJPrimitive {

    public ECJ_project_get_file() {
        super("ECJ_project_get_file", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!ECJTools.isIProject(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        IProject project = ECJTools.asIProject(tvars[0]);
        
        IFile file = project.getFile(Tools.asJavaString(tvars[1]));
        
        env.setCurrent(ECJFactory.wrap(file));
        
        return true;
    }

}
