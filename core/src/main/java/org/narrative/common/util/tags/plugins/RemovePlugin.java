/*
 * Copyright 1999,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.narrative.common.util.tags.plugins;

import org.apache.jasper.compiler.tagplugin.TagPlugin;
import org.apache.jasper.compiler.tagplugin.TagPluginContext;
import org.apache.jasper.tagplugins.jstl.Util;

public class RemovePlugin implements TagPlugin {

    public void doTag(TagPluginContext ctxt) {

        //scope flag
        boolean hasScope = ctxt.isAttributeSpecified("scope");

        //the value of the "var"
        String strVar = ctxt.getConstantAttribute("var");

        //remove attribute from certain scope.
        //default scope is "page".
        if (hasScope) {
            int iScope = Util.getScope(ctxt.getConstantAttribute("scope"));
            ctxt.generateJavaSource("_jspx_page_context.removeAttribute(\"" + strVar + "\"," + iScope + ");");
        } else {
            ctxt.generateJavaSource("_jspx_page_context.removeAttribute(\"" + strVar + "\");");
        }
    }

}
