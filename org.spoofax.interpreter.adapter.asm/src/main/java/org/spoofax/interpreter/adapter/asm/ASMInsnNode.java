/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.InsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMInsnNode extends AbstractASMNode {

	private static final long serialVersionUID = -774936416991214235L;
	
	private final InsnNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("InsnNode", 1);

	ASMInsnNode(InsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}
