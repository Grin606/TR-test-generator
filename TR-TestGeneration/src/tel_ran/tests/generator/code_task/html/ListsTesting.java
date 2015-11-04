package tel_ran.tests.generator.code_task.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListsTesting implements IHtmlTask {

	private static final String LIST_TYPE = "type";
	private static final String EXCESS_TAG = "excess";
	private static final int NUM_OF_DESCR = 11;
	private String data;
	private Element matching;
	
	@Override
	public void generateTask(Element taskRoot, int difficultyLevel) {
		Element list = HtmlUtils.getAnyList();
		taskRoot.appendChild(list);
		data = generateMistakes(list, difficultyLevel);
		matching = taskRoot;
		
	}

	private String generateMistakes(Element clearEl, int difficultyLevel) {
		int rndItem = HtmlUtils.RND.nextInt(clearEl.childNodeSize());
		String keyString = "";
		String closingTag = "";
		if(difficultyLevel > 1)
		for(Element item:clearEl.children()){
			int curIndex = item.elementSiblingIndex(); 
			if (curIndex != rndItem-1 && curIndex != rndItem) {
				keyString = item.ownText();
				closingTag = HtmlUtils.getElementBracket(item, false);
				item.text(" - " + item.ownText());
				Element subItem = HtmlUtils.getAnyElement(TaskData.TAG_INLINE,HtmlUtils.lorem.getKeyWord());
				HtmlUtils.setAttributes(subItem);
				item.prependChild(subItem);
				break;
			}
		}
		Element taskEl = clearEl.clone();
		Element noWrapEl = HtmlUtils.getAnyElement(TaskData.TAG_BLOCK);
		clearEl.parent().attr(LIST_TYPE, clearEl.tagName()).attr(EXCESS_TAG, noWrapEl.tagName());
		for(Element item:taskEl.children()) {
			if(item.elementSiblingIndex() == rndItem) {
				item.wrap(noWrapEl.toString());
				break;
			}
		}
		if(!keyString.isEmpty() && difficultyLevel > 2) {
			return taskEl.outerHtml().replaceAll(HtmlUtils.getWhitespaceIndepended(keyString+" "+closingTag), keyString);
		} else {
			return  taskEl.outerHtml();
		}
	}
	
	@Override
	public Element getMatching() {
		return matching;
	}

	@Override
	public boolean check(Element solution, Element matching) {
		String listType = matching.attr(LIST_TYPE);
		String unWrapTag = matching.attr(EXCESS_TAG);
		Elements els = solution.getElementsByTag(listType);
		if(els.size() != 1)
			return false;
		Element list = els.first();
		els = list.getElementsByTag(unWrapTag);
		for(Element el : els) {
			el.unwrap();
		}
		return HtmlUtils.matchingElements(matching.child(0), list);
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public int getNumberOfDescription() {
		return NUM_OF_DESCR;
	}

}
