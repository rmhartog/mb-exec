/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;

import org.spoofax.DebugUtil;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Pair;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoRef;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class Match extends Strategy {

    // FIXME pre-process to avoid String.equals() for Op cases
    protected IStrategoAppl pattern;

    public Match(IStrategoAppl pattern) {
        this.pattern = pattern;
    }

    public boolean eval(IContext env) throws InterpreterException {
        
        if (DebugUtil.isDebugging()) {
            debug("Match.eval() - ", " !", env.current(), " ; ?", pattern);
        }

        IStrategoTerm current = env.current();

        Results r = match(env, current, pattern);

        if (r == null) {
            if (DebugUtil.isDebugging()) {
                return DebugUtil.traceReturn(false, env.current(), this);
            }
            return false;
        }
        else {
            boolean b = env.bindVars(r);

            if (DebugUtil.isDebugging()) {
                debug("Bindings: " + r); //todo: unclear
                return DebugUtil.traceReturn(b, env.current(), this);
            }
            return b;
        }
    }

    public Results matchAppl(IContext env, IStrategoAppl t,
            IStrategoAppl p) throws InterpreterException {

        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if (Tools.isOp(p, env)) {
            return matchApplOp(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return matchApplInt(env, t, p);
        }
        else if (Tools.isStr(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isAs(p, env)) {
            return matchCompoundAs(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }

        throw new InterpreterException("Unknown Appl case '" + p + "'");
    }

    protected Results matchApplInt(IContext env, IStrategoTerm t,
            IStrategoAppl p) throws InterpreterException {
        if (Tools.isTermInt(t))
            return match(env, Tools.intAt(t, 0), Tools.applAt(p, 0));
        return null;
    }

    protected Results matchApplStr(IStrategoTerm t, IStrategoTerm p) {

        throw new NotImplementedException();

        //if (t.equals(p))
        //    return emptyList();
        //return null;
    }

    protected Results matchApplOp(IContext env, IStrategoAppl t,
            IStrategoAppl p) throws InterpreterException {


        String c = Tools.javaStringAt(p, 0);
        if(c.equals("Cons")) {
            return null; //matchApplCons(env, t, p);
        } else if(c.equals("Nil")) {
            return null; //matchApplNil(env, t);
        } else if(c.equals("")) {
            return matchApplTuple(env, t, p);
        } 

        IStrategoTerm[] ctorArgs = Tools.listAt(p, 1).getAllSubterms();

        // Check if arity of the pattern matches that
        // of the term
        if (ctorArgs.length != t.getSubtermCount())
            return null;

        // Check if the constructor name in the pattern
        // matches that of the term
        if (!t.getConstructor().getName().equals(c))
            return null;

        // Recursively match all arguments to term
        Results r = emptyList();
        IStrategoTerm[] applArgs = t.getArguments();
        
        for (int i = 0; i < ctorArgs.length; i++) {
            Results m = match(env, applArgs[i],
                              (IStrategoAppl) ctorArgs[i]);
            if (m != null)
                r.addAll(m);
            else
                return null;
        }

        return r;
    }

//    private Results matchApplCons(IContext env, IStrategoAppl t, IStrategoAppl p) {
//        throw new NotImplementedException();
//    }
//
//    private Results matchApplNil(IContext env, IStrategoAppl t) {
//        throw new NotImplementedException();
//    }

    private Results matchApplTuple(IContext env, IStrategoAppl t, IStrategoAppl p) throws InterpreterException {
        
        String c = Tools.javaStringAt(p, 0);

        // Check that the pattern p is really against a tuple 
        if(!c.equals(""))
            return null;

        IStrategoTerm[] ctorArgs = Tools.listAt(p, 1).getAllSubterms();
        
        IStrategoTerm[] args = t.getArguments();
        
        // Check that arity of pattern equals arity of tuple
        if(ctorArgs.length != args.length)
            return null;
        
        // Match subterms of tuple against subpatterns of pattern 
        Results r = emptyList();
        for (int i = 0; i < ctorArgs.length; i++) {
            Results m = match(env, args[i],
                              (IStrategoAppl) ctorArgs[i]);
            if (m != null)
                r.addAll(m);
            else
                return null;
        }

        return r;
    }

    private Results emptyList() {
        return new Results();
    }


    protected Results matchInt(IContext env, IStrategoInt t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("term is Int");
        }

        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return matchIntInt(t, p);
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return null;
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchAnyAs(env, t, p);
        } else if(Tools.isStr(p, env)) {
            return null;
        }

        throw new InterpreterException("Unknown Int case '" + p + "'");
    }

    protected Results matchReal(IContext env, IStrategoReal t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("term is Real");
        }

        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return matchRealReal(t, p);
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return null;
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchAnyAs(env, t, p);
        } else if (Tools.isStr(p, env)) {
            return null;
        }

        throw new InterpreterException("Unknown Real case '" + p + "'");
    }

    private Results matchRealReal(IStrategoReal t, IStrategoAppl p) {

        Double realVal = new Double(Tools.javaStringAt(p, 0));

        if (realVal == t.getValue())
            return emptyList();

        return null;
    }

    private Results matchAnyWld(IStrategoAppl p) {
        return emptyList();
    }

    protected Results matchAnyAnno(IContext env, IStrategoTerm t,
            IStrategoAppl p) throws InterpreterException {
        if(DebugUtil.isDebugging()) {
            DebugUtil.debug("  pattern is Anno");
        }
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }

    protected Results matchIntInt(IStrategoInt t, IStrategoAppl p) {
        Integer intVal = new Integer(Tools.javaStringAt(p, 0));
        if (intVal == t.getValue())
            return emptyList();

        return null;
    }

    protected Results matchAnyAs(IContext env, IStrategoTerm t, IStrategoAppl p) throws InterpreterException {
        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        Results pre = newResult(new Binding(varName, t));
        Results post = match(env, t, Tools.applAt(p, 1));
        if(post == null)
            return null;
        pre.addAll(post);
        return pre;
    }

    @SuppressWarnings("serial")
    public static final class Results extends ArrayList<Binding> {
    }

    public static final class Binding extends Pair<String, IStrategoTerm> {
        public Binding(String first, IStrategoTerm second) {
            super(first, second);
        }
    }

    private Results newResult(Binding initial) {
        Results r = new Results();
        r.add(initial);
        return r;
    }

    private Results matchAnyExplode(IContext env, IStrategoTerm t,
            IStrategoAppl p) throws InterpreterException {

        if(DebugUtil.isDebugging()) {
            DebugUtil.debug("  pattern is Explode");
        }
        IStrategoAppl opPattern = Tools.applAt(p, 0);
        IStrategoAppl argsPattern = Tools.applAt(p, 1);

        IStrategoTerm op = getTermConstructor(env, t);
        IStrategoTerm args = getTermArguments(env, t);

        Results opResult = match(env, op, opPattern);
        Results argsResult = match(env, args, argsPattern);

        if (opResult == null || argsResult == null)
            return null;

        opResult.addAll(argsResult);

        return opResult;
    }

    private IStrategoTerm getTermArguments(IContext env, IStrategoTerm t) throws InterpreterException {

        switch(t.getTermType()) {
        case IStrategoTerm.INT:
        case IStrategoTerm.REAL:
            return env.getFactory().makeList();
        case IStrategoTerm.APPL:
            IStrategoAppl a = (IStrategoAppl)t;
            if (Tools.isNil(a, env) || Tools.isCons(a, env))
                return t;
            else
                return env.getFactory().makeList(a.getArguments());
        case IStrategoTerm.LIST: 
            return t;
        case IStrategoTerm.STRING:
            return env.getFactory().makeList();
        case IStrategoTerm.TUPLE:
            IStrategoTuple tup = (IStrategoTuple) t;
            return env.getFactory().makeList(tup.getAllSubterms()); 
        }
            
        throw new InterpreterException("Unknown term '" + t + "'");
    }

    private IStrategoTerm getTermConstructor(IContext env, IStrategoTerm t) throws InterpreterException {

        if (Tools.isTermInt(t) || Tools.isTermReal(t)) {
            return t;
        } else if (Tools.isTermString(t)) {
            return env.getFactory().makeString("\"" + ((IStrategoString)t).getValue() + "\"");
        } else if (Tools.isTermAppl(t)) {
            IStrategoAppl a = (IStrategoAppl)t;
            if (Tools.isCons(a, env) || Tools.isNil(a, env))
                return env.getFactory().makeAppl(env.getStrategoSignature().getNil());
            else
                return env.getFactory().makeString(((IStrategoAppl)t).getConstructor().getName());
        } else if (Tools.isTermList(t)) {
            return env.getFactory().makeList();
        } else if (Tools.isTermTuple(t)) {
            return env.getFactory().makeString("");
        }

        throw new InterpreterException("Unknown term '" + t + "'");
    }

    public Results match(IContext env, IStrategoTerm t, IStrategoAppl p)
    throws InterpreterException {

        switch (t.getTermType()) {
        case IStrategoTerm.APPL:
            return matchAppl(env, (IStrategoAppl) t, p);
        case IStrategoTerm.INT:
            return matchInt(env, (IStrategoInt) t, p);
        case IStrategoTerm.REAL:
            return matchReal(env, (IStrategoReal) t, p);
        case IStrategoTerm.STRING:
            return matchString(env, (IStrategoString) t, p);
        case IStrategoTerm.LIST:
            return matchList(env, (IStrategoList) t, p);
        case IStrategoTerm.TUPLE:
            return matchTuple(env, (IStrategoTuple) t, p);
        case IStrategoTerm.REF:
            return matchRef(env, (IStrategoRef)t, p);
        default:
            throw new InterpreterException("Unsupported term type : "
                                           + t.getClass().toString() + " [" + t.getTermType() + "]");
        }
    }

    private Results matchRef(IContext env, IStrategoRef ref, IStrategoAppl p) {
        throw new NotImplementedException();
    }

    private Results matchTuple(IContext env, IStrategoTuple t, IStrategoAppl p) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("term is Tuple");
        }
        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return matchTupleOp(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchCompoundAs(env, t, p);
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        } 
        else if (Tools.isStr(p, env)) {
            return null;
        }

        throw new InterpreterException("Unknown Tuple case '" + p + "'");
    }

    private Results matchTupleOp(IContext env, IStrategoTuple t, IStrategoAppl p) throws InterpreterException {
        
        String c = Tools.javaStringAt(p, 0);

        // Check that the pattern p is really against a tuple 
        if(!c.equals(""))
            return null;

        IStrategoTerm[] ctorArgs = Tools.listAt(p, 1).getAllSubterms();

        // Check that arity of pattern equals arity of tuple
        if(ctorArgs.length != t.size())
            return null;
        
        IStrategoTerm[] tupleArgs = t.getAllSubterms();
        
        // Match subterms of tuple against subpatterns of pattern 
        Results r = emptyList();
        for (int i = 0; i < ctorArgs.length; i++) {
            Results m = match(env, tupleArgs[i],
                              (IStrategoAppl) ctorArgs[i]);
            if (m != null)
                r.addAll(m);
            else
                return null;
        }

        return r;
    }

    protected Results matchList(IContext env, IStrategoList t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("term is List");
        }

        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return matchListOp(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchCompoundAs(env, t, p);
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isStr(p, env)) {
            return null;
        }

        throw new InterpreterException("Unknown List case '" + p + "'");
    }

    private Results matchCompoundAs(IContext env, IStrategoTerm t, IStrategoAppl p) throws InterpreterException {
        
        Results r = match(env, t, Tools.applAt(p, 1));

        if (r == null)
            return null;

        if (DebugUtil.isDebugging()) {
            debug("matching CompoundAs", p);
        }

        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        r.add(new Binding(varName, t));

        return r;
    }

    private Results matchListOp(IContext env, IStrategoList t, IStrategoAppl p) throws InterpreterException {
        
        String c = Tools.javaStringAt(p, 0);

        if(c.equals("Nil")) {
            if(t.size() == 0)
                return emptyList();
        } 
        else if(c.equals("Cons")) {
            
            if(t.size() < 1) {
                return null;
            }
                
            IStrategoTerm head = t.head();
            IStrategoList tail = t.tail();
            
            IStrategoList pattern = Tools.listAt(p, 1);

            Results r = match(env, head, (IStrategoAppl)pattern.get(0));
            if(r == null)
                return null;
            
            Results r2 = match(env, tail, (IStrategoAppl)pattern.get(1));
            if(r2 == null)
                return null;
            
            r.addAll(r2);
            return r;
        }
        
        return null;
    }

    private Results matchAnyVar(IStrategoTerm t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(p, 0);
        return newResult(new Binding(varName, t));
    }

    private Results matchString(IContext env, IStrategoString t, IStrategoAppl p) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("term is String");
        }
        
        if (Tools.isAnno(p, env)) {
            return matchAnyAnno(env, t, p);
        }
        else if(Tools.isStr(p, env)) {
            return matchStrStr(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchAnyVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return null;
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchAnyWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchAnyAs(env, t, p);
        } 

        throw new InterpreterException("Unknown String case '" + p + "'");
    }

    private Results matchStrStr(IContext env, IStrategoString t, IStrategoAppl p) {
        if(DebugUtil.isDebugging()) {
            DebugUtil.debug("  pattern is Str");
        }
        IStrategoString s = Tools.stringAt(p, 0);
        if(s.match(t)) {
            return emptyList();
        }
        return null;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Match(" + pattern.toString() + ")");
    }

    @Override
    protected String getTraceName() {
        return super.getTraceName() + "(" + pattern + ")";
    }
}
