package parser;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;
import parser.rule.RuleAdaptor;
import java.io.ByteArrayInputStream;

public class Main {

  public static void main(String[] args) {
    try {

      String ruleCode = "a() -> b()\n";
//    RuleLexer lexer = new RuleLexer(new ANTLRInputStream(System.in));
      RuleLexer lexer = new RuleLexer(new ANTLRInputStream(new ByteArrayInputStream(ruleCode.getBytes("UTF-8"))));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      RuleParser parser = new RuleParser(tokens);
      // Parse the input expression
      Tree b = (Tree) parser.ruleset().getTree();
      System.out.println("Result = " + RuleAdaptor.getTerm(b));
    } catch (Exception e) {
      System.err.println("exception: " + e);
      return;
    }
  }
}
