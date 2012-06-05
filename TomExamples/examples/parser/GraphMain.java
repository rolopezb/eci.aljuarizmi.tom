package parser;

import java.io.ByteArrayInputStream;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;
import parser.term.TermAdaptor;

public class GraphMain {

  public static void main(String[] args) {
    try {
      String s = "<a:m|>\n"; // no se puede usar 'b' porque es reservado!!
      GraphLexer lexer = new GraphLexer(new ANTLRInputStream(new ByteArrayInputStream(s.getBytes("UTF-8"))));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      GraphParser parser = new GraphParser(tokens);
      // Parse the input expression
      Tree b = (Tree) parser.node().getTree();
      System.out.println("Result = " + TermAdaptor.getTerm(b));
    } catch (Exception e) {
      System.err.println("exception: " + e);
      e.printStackTrace();
      return;
    }
  }
}

