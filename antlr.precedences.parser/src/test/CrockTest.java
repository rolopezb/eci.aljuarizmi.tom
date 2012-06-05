package test;

import java.io.FileInputStream;

import javax.swing.JOptionPane;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import prec.PrecedencesLexer;
import crock.CrockfordParser;
import crock.Operators;
import eci.antlr.tools.AntlrTree;

public class CrockTest {
	static final Logger logger = Logger.getLogger(CrockTest.class);

	public static void main(String[] args) {
		try {
			if (args.length <= 0) {
				System.out.println("usage: java CrockTest <filename>");
			} else {
				// Initialize parser
				PrecedencesLexer lexer = new PrecedencesLexer(new ANTLRInputStream(
						new FileInputStream(args[0])));
				
				CrockfordParser crkParse = new CrockfordParser(lexer, new Operators());
				crkParse.init();
				//Muestra el arbol en una de dos formas posibles
				Tree t =(Tree) crkParse.parse();
				if(args.length > 1 && args[1].equals("-dot")){
					DOTTreeGenerator gen = new DOTTreeGenerator();
					StringTemplate st = gen.toDOT(t);
					logger.info(st.toString());
				}
				else {
//					System.out.println(t.toStringTree());
//					logger.info(new TreeFormater(t,3).format());
					new AntlrTree(t, "Crockford Tree");
				}
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error!!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
