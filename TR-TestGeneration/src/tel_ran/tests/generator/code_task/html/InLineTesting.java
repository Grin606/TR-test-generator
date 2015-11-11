package tel_ran.tests.generator.code_task.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class InLineTesting implements IHtmlTask {
	private static final int NUM_OF_DESCR = 11;
	private String data;
	private Element matching;

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void generateTask(Element taskRoot, int difficultyLevel) {
		String[] falsePhrase = HtmlUtils.getAnyFalsePhrase();
		Element blockEl = HtmlUtils.getAnyElement("tag-uniBlock").text(falsePhrase[0]);
		Element errEl;
		String errStr;
		if(difficultyLevel > 1) {
			errEl = blockEl.appendElement(HtmlUtils.getAnyTagName("tag-markInline")).text(falsePhrase[1]);
			if(difficultyLevel > 2)
				HtmlUtils.setAttributes(errEl);
			errStr = falsePhrase[1];
		} else {
			errEl = blockEl.appendText(" " + falsePhrase[1]);
			errStr = falsePhrase[0] + " " + falsePhrase[1];
		}
		String lBracket = HtmlUtils.getElementBracket(errEl, true);
		String rBracket = HtmlUtils.getElementBracket(errEl, false);
		String taskText =  lBracket + errStr + rBracket;
		
		taskRoot.appendElement(HtmlUtils.getLineTagName());
		taskRoot.appendChild(blockEl);
		taskRoot.appendElement(HtmlUtils.getLineTagName());
		matching = taskRoot;
		data = taskRoot.html().replaceFirst(lBracket + ".+" + rBracket, taskText);
	}
	
	@Override
	public Element getMatching() {
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
