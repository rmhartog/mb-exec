/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.library.SSL;

import aterm.ATerm;

public class PrimT extends Strategy {

    protected String name;

    protected List<Strategy> svars;

    protected List<ATerm> tvars;

    public PrimT(String name, List<Strategy> svars, List<ATerm> tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext env) throws FatalError {

        if (Interpreter.isDebugging()) {
            debug("PrimT.eval() - ", env.current());
        }

        Primitive prim = SSL.lookup(name);

        if (prim == null)
            throw new FatalError("No such function : '" + name + "'");

        if (Interpreter.isDebugging()) {
            debug(" call  : ", prim.getName());
        }
        if (Interpreter.isDebugging()) {
            debug(" svars : ", svars);
        }

        List<ATerm> vals = new ArrayList<ATerm>(tvars.size());
        for (ATerm t : tvars) {
            vals.add(env.lookupVar(Tools.stringAt(t, 0)));
        }

        if (Interpreter.isDebugging()) {
            debug(" tvars : ", vals);
        }

        if (vals.size() != prim.getArity())
            throw new FatalError("Wrong aterm arity when calling '" + name + "', expected " + prim.getArity() + " got " + vals.size());

        if (svars.size() != prim.getSArity())
            throw new FatalError("Wrong strategy arity when calling '" + name + "', expected " + prim.getSArity() + " got " + svars.size());

        boolean r = prim.call(env, svars, vals);

        if (Interpreter.isDebugging()) {
            debug(" return: ", prim.getName(), " (", (r ? "ok" : "failed"), ")");
        }

        return r;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("PrimT(\"" + name + "\")");
    }

}