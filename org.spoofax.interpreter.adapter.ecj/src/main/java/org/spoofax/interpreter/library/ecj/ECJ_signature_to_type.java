/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

class ECJ_signature_to_type extends ECJPrimitive {

    ECJ_signature_to_type() {
        super("ECJ_signature_to_type", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

    	if(!Tools.isTermString(tvars[0]))
    		return false;

    	final ECJFactory factory = (ECJFactory)env.getFactory();
    	final Type t = signatureStringToType(
    			factory.getAST(),
    			Signature.createTypeSignature(Tools.asJavaString(tvars[0]), false));
    	env.setCurrent(ECJFactory.wrapType(t));
    	return true;

    }

	@SuppressWarnings("unchecked")
    private Type signatureStringToType(final AST ast, final String signatureName) {
		final String[] args = Signature.getTypeArguments(signatureName);
		final String qual = Signature.getQualifier(signatureName);
		final String base = Signature.getSignatureSimpleName(Signature.getTypeErasure(signatureName));
		final Name n = qual.length() == 0 ? ast.newName(base) : ast.newName(qual + "." + base);

		final Type bt = ast.newSimpleType(n);
		if(args.length == 0)
			return bt;
		else {
			ParameterizedType pt = ast.newParameterizedType(bt);
			for(String s : args) {
				pt.typeArguments().add(signatureStringToType(ast, s));
			}
			return pt;
		}
	}


}
