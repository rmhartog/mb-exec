/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import aterm.ATerm;
import aterm.ATermList;

public interface IATermBuilder {
    
    public ATerm makeList(ATermList t);
    public ATerm makeList(ATerm... terms);
    public ATerm makeTerm(String s);
    public ATerm makeTerm(int i);
    public ATerm makeString(String s);

}
