package solucion;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import problema.TreeNumbers;

public class TreeNumberComposite implements TreeNumberable {

	private String name;
	private final List<TreeNumberable> treeNumbersList;

	public TreeNumberComposite(String name) {
		super();
		this.name = name;
		this.treeNumbersList = new ArrayList<>();
	}

	public void add(TreeNumberable treeNumbers) {
		this.treeNumbersList.add(treeNumbers);
	}

	public void remove(TreeNumberable treeNumbers) {
		this.treeNumbersList.remove(treeNumbers);
	}

	@Override
	public int numberOfNodes() {
		return this.treeNumbersList.size();
	}

	@Override
	public int sum() {
		return this.treeNumbersList.stream().mapToInt(TreeNumberable::sum).sum();
	}

	@Override
	public int higher() {
		return this.treeNumbersList.stream().mapToInt(TreeNumberable::higher).max()
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public String toString() {
		return "TreeNumbers{" + ", name='" + name + '\'' + ", treeNumbersList=" + treeNumbersList + '}';
	}
}
