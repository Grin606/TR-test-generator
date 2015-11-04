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
		String falsePhrase = HtmlUtils.getAnyFalsePhrase();
		Element textEl = new Element(HtmlUtils.getParagrafTag(),"").text(falsePhrase);
		String lBracket = HtmlUtils.getElementBracket(textEl, true);
		String rBracket = HtmlUtils.getElementBracket(textEl, false);
		String taskText =  lBracket + falsePhrase + rBracket;
		
		taskRoot.appendElement(HtmlUtils.getLineTagName());
		taskRoot.appendChild(textEl);
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
