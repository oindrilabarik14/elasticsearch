/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.painless.ir;

import org.elasticsearch.painless.ClassWriter;
import org.elasticsearch.painless.Globals;
import org.elasticsearch.painless.Locals.Variable;
import org.elasticsearch.painless.MethodWriter;
import org.objectweb.asm.Opcodes;

public class VariableNode extends ExpressionNode {

    /* ---- begin node data ---- */

    private Variable variable;

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    /* ---- end node data ---- */

    @Override
    protected void write(ClassWriter classWriter, MethodWriter methodWriter, Globals globals) {
        methodWriter.visitVarInsn(MethodWriter.getType(variable.clazz).getOpcode(Opcodes.ILOAD), variable.getSlot());
    }

    @Override
    protected int accessElementCount() {
        return 0;
    }

    @Override
    protected void setup(ClassWriter classWriter, MethodWriter methodWriter, Globals globals) {
        // do nothing
    }

    @Override
    protected void load(ClassWriter classWriter, MethodWriter methodWriter, Globals globals) {
        methodWriter.visitVarInsn(MethodWriter.getType(variable.clazz).getOpcode(Opcodes.ILOAD), variable.getSlot());
    }

    @Override
    protected void store(ClassWriter classWriter, MethodWriter methodWriter, Globals globals) {
        methodWriter.visitVarInsn(MethodWriter.getType(variable.clazz).getOpcode(Opcodes.ISTORE), variable.getSlot());
    }
}
