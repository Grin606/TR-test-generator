package tel_ran.tests.generator.code_task.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TagNesting implements IHtmlTask{

	private static final int BREAK_TYPES_N = 4;
	private static final String PLACEHOLDER = "<###>";
	private static final int NUM_OF_DESCR = 11;

	private String data;
	private Element matching;

	@Override
	public void generateTask(Element taskRoot, int difficultyLevel) {
		generateClearDoc(taskRoot);
		matching = taskRoot;
		data = generateMistakes(taskRoot);
	}

	private void generateClearDoc(Element taskRoot) {
		Element parent = taskRoot.appendElement(HtmlUtils.getParentTagName());
		HtmlUtils.setAttributes(parent);
		Element child = parent.appendElement(HtmlUtils.getChildTagName());
		HtmlUtils.setAttributes(child);
		child.text(HtmlUtils.lorem.getTitle(5, 10));
	}

	private String generateMistakes(Element clearEl) {
		Element parent  = clearEl.child(0);
		String parentHtml = clearEl.html();
		Element child = parent.child(0);
		String childHtml = HtmlUtils.getMatchedString(HtmlUtils.getWhitespaceIndepended(child.outerHtml()),parentHtml);
		String newParent = parentHtml;
		String newChild = childHtml;
		int breakType = HtmlUtils.RND.nextInt(BREAK_TYPES_N);
		if(breakType == 0) {
			String leftParent = HtmlUtils.getElementBracket(parent, true);
			String leftChild = HtmlUtils.getElementBracket(child, true);
			newChild = childHtml.replaceFirst(leftChild, leftParent);
			newParent = parentHtml.replaceFirst(leftParent, leftChild);
		} else if (breakType == 1) {
			String rightParent = HtmlUtils.getElementBracket(parent, false);
			String rightChild = HtmlUtils.getElementBracket(child, false);
			newChild = childHtml.replaceFirst(rightChild, rightParent);
			newParent = parentHtml.replaceFirst(rightParent, rightChild);
		} else if (breakType == 2) {
			String leftChild = HtmlUtils.getElementBracket(child, true);
			String rightChild = HtmlUtils.getElementBracket(child, false);
			newChild = childHtml.replaceFirst(leftChild, PLACEHOLDER);
			newChild = newChild.replaceFirst(rightChild, leftChild);
			newChild = newChild.replaceFirst(PLACEHOLDER, rightChild);
		} else {
			String leftParent = HtmlUtils.getElementBracket(parent, true);
			String leftChild = HtmlUtils.getElementBracket(child, true);
			String rightParent = HtmlUtils.getElementBracket(parent, false);
			String rightChild = HtmlUtils.getElementBracket(child, false);
			newChild = childHtml.replaceFirst(leftChild, leftParent);
			newChild = newChild.replaceFirst(rightChild, rightParent);
			newParent = parentHtml.replaceFirst(leftParent, leftChild);
			newParent = newParent.replaceFirst(rightParent, rightChild);
		}
		newParent = newParent.replaceFirst(childHtml, newChild);
		return clearEl.html().replaceFirst(parentHtml, newParent);
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public	Element getMatching() {
		return matching;
	}

	@Override
	public boolean check(Element solution, Element matching) {
		Elements mathcEls = matching.children();
		Elements solEls = solution.children();
		for(Element mathEl: mathcEls) {
			int index = 0;
			for(Element solEl:solEls) {
				if(HtmlUtils.matchingElements(mathEl, solEl))
					break;
				index++;
			}
			if(index == solEls.size())
				return false;
			solEls.remove(index);
		}
		return true;
	}

	@Override
	public int getNumberOfDescription() {
		return NUM_OF_DESCR;
	}
}
