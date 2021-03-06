// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a while-statement.
 */

class JForStatement extends JStatement {

    private JStatement body;
    private JVariableDeclarator variable;
    private JExpression condition;
    private JExpression increment;
    private String name;
    private boolean foreach = false;

    /**
     * Construct an AST node for a while-statement given its line number, the
     * test expression, and the body.
     * 
     * @param line
     *            line in which the while-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */
    public JForStatement(int line, JVariableDeclarator assignment, JExpression condition, JExpression increment, JStatement body) {
        super(line);
        this.variable = assignment;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }


    public JForStatement(int line, JVariableDeclarator variable, String name, JStatement body) {
        super(line);
        this.variable = variable;
        this.name = name;
        this.body = body;
        foreach = true;
    }
    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForStatement analyze(Context context) {
        // condition = condition.analyze(context);
        // condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        // condition = condition.analyze(context);
        // condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        // body = (JStatement) body.analyze(context);
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {

        // Need two labels
        // String test = output.createLabel();
        // String out = output.createLabel();

        // variable.codegen(output);
        // // Branch out of the loop on the test condition
        // // being false
        // output.addLabel(test);
        // condition.codegen(output, out, false);

        // // Codegen body
        // body.codegen(output);
        // increment.codegen(output);

        // // Unconditional jump back up to test
        // output.addBranchInstruction(GOTO, test);

        // // The label below and outside the loop
        // output.addLabel(out);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        if (!foreach) {
            p.printf("<JForStatement line=\"%d\">\n", line());
            p.indentRight();
            p.printf("<TestExpression>\n");
            p.indentRight();
            variable.writeToStdOut(p);
            condition.writeToStdOut(p);
            increment.writeToStdOut(p);
            p.indentLeft();
            p.printf("</TestExpression>\n");
            p.printf("<Body>\n");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.printf("</Body>\n");
            p.indentLeft();
            p.printf("</JForStatement>\n");
        } else {
            p.printf("<JForStatement line=\"%d\">\n", line());
            p.indentRight();
            variable.writeToStdOut(p);
            p.printf("<IterateVariable name=\"%s\">\n", name);
            p.indentLeft();
            p.printf("<Body>\n");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.printf("</Body>\n");
            p.indentLeft();
            p.printf("</JForStatement>\n");
        }
    }

}
