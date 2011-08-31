package prec;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.Tree;

public class MyTree implements Tree {
	
	List <Tree> children = new ArrayList <Tree> ();

	@Override
	public void addChild(Tree child) {
		children.add(child);
		
	}

	@Override
	public Object deleteChild(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tree dupNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void freshenParentAndChildIndexes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Tree getAncestor(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAncestors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCharPositionInLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tree getChild(int index) {
		// TODO Auto-generated method stub
		return children.get(index);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return children.size();
	}

	@Override
	public int getChildIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tree getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTokenStartIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTokenStopIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasAncestor(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNil() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void replaceChildren(int arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChild(int arg0, Tree arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChildIndex(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParent(Tree arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTokenStartIndex(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTokenStopIndex(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toStringTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
