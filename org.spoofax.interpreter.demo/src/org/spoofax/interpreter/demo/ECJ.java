/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class ECJ extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "ECJ";

    ECJ() {
        init();
    }
    
    private void init() {
        add(new ECJ_parse_only());
        add(new ECJ_parse_and_resolve());
        add(new ECJ_open_project());
        add(new ECJ_create_project());
        add(new ECJ_add_source_folder());
        add(new ECJ_add_jar());
        add(new ECJ_binding_of_name());
        add(new ECJ_type_of_type());
        add(new ECJ_type_of_typeparameter());
        add(new ECJ_is_cast_compatible());
    }
}
