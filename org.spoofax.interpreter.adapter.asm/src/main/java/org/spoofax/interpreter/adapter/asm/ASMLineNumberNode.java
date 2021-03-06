/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LineNumberNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMLineNumberNode extends AbstractASMNode {

	private static final long serialVersionUID = -670253282390858542L;
	
	private final LineNumberNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("LineNumberNode", 1);
	
	ASMLineNumberNode(LineNumberNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.line);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}
