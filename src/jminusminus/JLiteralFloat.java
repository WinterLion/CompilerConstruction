// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a float literal.
 */

class JLiteralFloat extends JExpression {

    /** String representation of the float. */
    private String text;

    /**
     * Construct an AST node for a float literal given its line number and string
     * representation.
     * 
     * @param line
     *            line in which the literal occurs in the source file.
     * @param text
     *            string representation of the literal.
     */
    public JLiteralFloat(int line, String text) {
        super(line);
        this.text = text;
    }

    /**
     * Analyzing a float literal is trivial.
     * 
     * @param context
     *            context in which names are resolved (ignored here).
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        type = Type.FLOAT;
        return this;
    }

    /**
     * Generating code for a literal means generating code to push it onto
     * the stack.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        Float i = Float.parseFloat(text);
        // switch (i) {
        // case 0:
        //     output.addNoArgInstruction(FCONST_0);
        //     break;
        // case 1:
        //     output.addNoArgInstruction(FCONST_1);
        //     break;
        // case 2:
        //     output.addNoArgInstruction(FCONST_2);
        //     break;
        // default:
        //     if (i >= 3 && i <= 127) {
        //         output.addOneArgInstruction(BIPUSH, i);
        //     } else if (i >= 128 && i <= 32767) {
        //         output.addOneArgInstruction(SIPUSH, i);
        //     } else {
        //         output.addLDCInstruction(i);
        //     }
        // }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralFloat line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}
