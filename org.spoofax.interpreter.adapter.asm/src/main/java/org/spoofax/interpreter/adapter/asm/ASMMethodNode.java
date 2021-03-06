/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.MethodNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMMethodNode extends AbstractASMNode {

	private static final long serialVersionUID = -3879090866079739712L;

	private final MethodNode wrappee;
	private final static IStrategoConstructor CTOR = new StrategoConstructor("MethodNode", 16);
	
	ASMMethodNode(final MethodNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapAccessFlags(wrappee.access);
		case 1:
			return ASMFactory.genericWrap(wrappee.annotationDefault);
		case 2:
			return ASMFactory.wrap(wrappee.attrs);
		case 3:
			return ASMFactory.wrap(wrappee.desc);
		case 4:
			return ASMFactory.wrap(wrappee.exceptions);
		case 5:
			return ASMFactory.wrap(wrappee.invisibleAnnotations);
		case 6:
			return ASMFactory.wrap(wrappee.localVariables);
		case 7:
			return ASMFactory.wrap(wrappee.maxLocals);
		case 8:
			return ASMFactory.wrap(wrappee.maxStack);
		case 9:
			return ASMFactory.wrap(wrappee.name);
		case 10:
			return ASMFactory.wrap(wrappee.signature);
		case 11:
			return ASMFactory.wrap(wrappee.tryCatchBlocks);
		case 12:
			return ASMFactory.wrap(wrappee.visibleAnnotations);
		case 13:
			return ASMFactory.wrap(wrappee.instructions);
		case 14:
			return ASMFactory.wrap(wrappee.invisibleParameterAnnotations);
		case 15:
			return ASMFactory.wrap(wrappee.visibleParameterAnnotations);
		}
		throw new ArrayIndexOutOfBoundsException();
	}
}
