package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMJavaInt extends AbstractASMNode {

	private static final long serialVersionUID = -2982339822386629718L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("JavaInt", 1);
	private final int wrappee;
	
	ASMJavaInt(int wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		if(index == 0)
			return ASMFactory.wrap(Integer.toString(wrappee));
		throw new ArrayIndexOutOfBoundsException(index);
	}

}
