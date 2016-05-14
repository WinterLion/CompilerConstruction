
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a long literal.
 */

class JLiteralLong extends JExpression {

    /** String representation of the long. */
    private String text;

    /**
     * Construct an AST node for an long literal given its line number and string
     * representation.
     * 
     * @param line
     *            line in which the literal occurs in the source file.
     * @param text
     *            string representation of the literal.
     */

    public JLiteralLong(int line, String text) {
        super(line);
        this.text = text;
    }

    /**
     * Analyzing an long literal is trivial.
     * 
     * @param context
     *            context in which names are resolved (ignored here).
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        type = Type.LONG;
        return this;
    }

    /**
     * Generating code for an int literal means generating code to push it onto
     * the stack.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        Long i = Long.parseLong(text);
        if (i == 0L){
        	output.addNoArgInstruction(LCONST_0);
        } else if (i == 1L) {
        	output.addNoArgInstruction(LCONST_1);
        } else {
        	output.addLDCInstruction(i);
        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralLong line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}
