package org.spoofax.compiler;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.aterm.WrappedATermFactory;
import org.spoofax.interpreter.library.jsglr.JSGLRLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.InvalidParseTableException;

public class Compiler {
	private Interpreter compiler;
	private String sharePath;
	
    public Compiler() throws IOException, InterpreterException, InvalidParseTableException {
    	this(sharePath(), new WrappedATermFactory());
    }
    
	Compiler(String sharePath, WrappedATermFactory factory) throws IOException, InterpreterException, InvalidParseTableException {
		this.sharePath = sharePath;
		init(factory);
    }
    
    private void init(WrappedATermFactory factory) throws IOException, InterpreterException, InvalidParseTableException
    {
		compiler = new Interpreter(factory);
		compiler.addOperatorRegistry("JSGLR", new JSGLRLibrary(factory));
		compiler.load(sharePath + "/libstratego-lib.ctree");
		compiler.load(sharePath + "/libstratego-sglr.ctree");
		compiler.load(sharePath + "/libstrc.ctree");
		compiler.load(sharePath + "/jstrc.ctree");
	}
	
	IStrategoTerm compile(String file, String[] path, boolean lib) throws InterpreterException
	{
		Collection<IStrategoTerm> terms = new LinkedList<IStrategoTerm>();
		for (String p : path) {
			terms.add(compiler.getFactory().makeString(p));
		}
		IStrategoTerm tp = compiler.getFactory().makeList(terms);
		IStrategoTerm[] tuple = {
		  compiler.getFactory().makeString(file),
		  tp,
		  compiler.getFactory().makeInt(lib?1:0)
		};
		compiler.setCurrent(
		  compiler.getFactory().makeTuple(tuple)
		);
		if (!compiler.invoke("main_0_0"))
			return null;
		return compiler.current();
	}

	public static String sharePath() {
		String path = System.getProperty("share.dir");
		System.out.println("share.dir = " + path);
		return path == null ? System.getProperty("user.dir") + "/.nix-profile" : path;
	}
}
