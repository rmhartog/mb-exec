package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.*;

import java.io.File;
import java.io.IOException;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_mkstemp extends AbstractPrimitive {
    
    public SSL_mkstemp() {
        super("SSL_mkstemp", 0, 1);
    }

    @Override
    /**
     * Create a new temp file.
     * 
     * Stratego types: String -> String * Int.
     */
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if (tvars[0].getTermType() != IStrategoTerm.STRING)
            return false;
        
        // HACK: We ignore the template directory, and just use it as a filename prefix
        String prefix = new File(javaString(tvars[0])).getName();
        if (prefix.endsWith("XXXXXX"))
            prefix = prefix.substring(0, prefix.length() - 6);
        
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        IOAgent agent = op.getIOAgent();
        ITermFactory factory = env.getFactory();
         
        try {
            String fn = agent.createTempFile(prefix);
            int fd = agent.openRandomAccessFile(fn, "w");
                
            env.setCurrent(factory.makeTuple(factory.makeString(fn), factory.makeInt(fd)));
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
