/*

   Derby - Class org.apache.derby.impl.sql.compile.XMLConstantNode

   Copyright 2005 The Apache Software Foundation or its licensors, as applicable.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package    org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.io.StoredFormatIds;
import org.apache.derby.iapi.services.sanity.SanityManager;
import org.apache.derby.iapi.types.TypeId;

import org.apache.derby.impl.sql.compile.ExpressionClassBuilder;

import org.apache.derby.iapi.util.ReuseFactory;

public final class XMLConstantNode extends ConstantNode
{
    /**
     * Initializer for an XMLConstantNode.
     *
     * @param arg1 The TypeId for the type of the node
     *
     * @exception StandardException
     */
    public void init(
                    Object arg1)
        throws StandardException
    {
        super.init(
                    arg1,
                    Boolean.TRUE,
                    ReuseFactory.getInteger(0));
    }

    /**
     * Return the value from this XMLConstantNode as a string.
     *
     * @return The value of this XMLConstantNode as a string.
     *
     * @exception StandardException Thrown on error
     */
    public String getString() throws StandardException
    {
        return value.getString();
    }

    /**
     * Return an Object representing the bind time value of this
     * expression tree.  If the expression tree does not evaluate to
     * a constant at bind time then we return null.
     *
     * @return An Object representing the bind time value of this
     *  expression tree (null if not a bind time constant).
     *
     * @exception StandardException        Thrown on error
     */
    Object getConstantValueAsObject() throws StandardException 
    {
        return value.getObject();
    }

    /**
     * This generates the proper constant.  For an XML value,
     * this constant value is simply the XML string (which is
     * just null because null values are the only types of
     * XML constants we can have).
     *
     * @param acb The ExpressionClassBuilder for the class being built
     * @param mb The method the code to place the code
     *
     * @return The compiled Expression, 
     *
     * @exception StandardException        Thrown on error
     */
    void generateConstant(ExpressionClassBuilder acb, MethodBuilder mb)
        throws StandardException
    {
        // The generated java is the expression:
        // "#getString()"
        mb.push(getString());
    }
}
