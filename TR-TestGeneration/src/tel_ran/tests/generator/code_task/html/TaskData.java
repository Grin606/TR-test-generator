package tel_ran.tests.generator.code_task.html;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

class TaskData {
	private static final String DATA_FILE = "HtmlData.xml";
	private static Document DATA_DOC;

	static final String DEFAULT_BASE_URI = "";
	static final String DEFAULT_DOC_TYPE = "html";
	static final String DEFAULT_TYPE_PUBLIC = "";
	static final String DEFAULT_SYSTEM_ID = "";

	static final String TAG_TITLE = "title";
	static final String TAG_META = "meta";
	static final String TAG_P = "p";
	static final String TAG_HR = "hr";
	static final String TAG_BR = "br";

	static final String TAG_MANDATORY = "tag-mandatory";
	static final String TAG_OPTIONAL = "tag-optional";
	static final String TAG_INLINE = "tag-inline";
	static final String TAG_BLOCK = "tag-block";
	static final String TAG_LIST_TYPE = "tag-listType";
	static final String TAG_CHILD = "child";
	
	static final String META_HTTP_EQUIV_KEY = "http-equiv";
	static final String META_HTTP_EQUIV_VAL = "Content-Type";
	static final String META_CONTENT_KEY = "content";
	static final String META_CONTENT_VAL = "text/html; charset=utf-8";
	
	static final String KW_TAG = "tag";
	static final String KW_TASK = "task";
	static final String KW_ID = "id";
	static final String KW_CLASS = "class";
	static final String KW_ATTRIBUTE = "attribute";
	static final String KW_NAME = "name";
	static final String KW_VALUE = "value";
	static final String KW_MIN_SIZE = "minsize";
	static final String KW_MAX_SIZE = "maxsize";

	static final String SECTION_TAG = KW_TAG;
	static final String SECTION_TASK = KW_TASK;
	
	static final String ATTR_MANDATORY = "attr-mandatory";
	static final String ATTR_OPTIONAL = "attr-optional";
	static final String TAG_DIV = "div";
	static final String INSERT_TEXT_MARK = "<!--INSERT TEXT-->";
	static final String TAG_TABLE = "tag-tableType";

	
	
	TaskData() {
		try {
			DATA_DOC = DataUtil.load(getClass().getResourceAsStream(DATA_FILE), "utf-8", "", Parser.xmlParser());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	List<String> getSectionList(String sectionId, String attrName,String attrValue) {
		List<String> sectionList = new LinkedList<String>();
		Element section = DATA_DOC.getElementById(sectionId);
		List<Element> elements = section.getElementsByAttributeValueContaining(attrName, attrValue);
		for(Element el:elements)
			sectionList.add(el.id());
		return sectionList;
	}
	
	List<String> getSectionList(String sectionId) {
		List<String> sectionList = new LinkedList<String>();
		List<Element> elements = DATA_DOC.getElementById(sectionId).children();
		for(Element el:elements)
			sectionList.add(el.id());
		return sectionList;
	}
	
	Map<String, List<String>> getAnyAttributes(String tagName, int index) {
		Map<String, List<String>> attrMap = new LinkedHashMap<String, List<String>>();
		List<Element> attrubutes = DATA_DOC.getElementById(tagName).getElementsByAttributeValueContaining(KW_CLASS,ATTR_MANDATORY);
		if(attrubutes != null && attrubutes.size() > 0) {
			for(Element el:attrubutes)
				attrMap.put(el.attr(KW_NAME), getValues(el));
		} else {
			attrubutes = DATA_DOC.getElementById(tagName).getElementsByAttributeValueContaining(KW_CLASS,ATTR_OPTIONAL);
			if(attrubutes != null && attrubutes.size() > 0) {
				index = index < 0 ? -index % attrubutes.size() : index % attrubutes.size();
				Element el = attrubutes.get(index);
				attrMap.put(el.attr(KW_NAME), getValues(el));
			}
		}
		return attrMap;
	}
	
	private List<String> getValues(Element el) {
		List<String> valuesList = new LinkedList<String>();
		for(Element val:el.getElementsByTag(KW_VALUE)) {
			valuesList.add(val.ownText());
		}
		return valuesList;
	}
	
	void setListStructure(Element list, int randomInt) {
		Element el = DATA_DOC.getElementById(list.tagName());
		Elements items  = el.getElementsByTag(TAG_CHILD);
		int count = getIntInRange(el);
		while(count-- > 0) {
			for(Element item:items) {
				String tag = item.attr(KW_NAME);
				list.appendElement(tag).text(String.valueOf(getIntInRange(DATA_DOC.getElementById(tag))));
			}
		}

	}

	private int getIntInRange(Element element) {
		int randomInt = (int)(Math.random()*100);
		int min = 1;
		int max = 1;
		String minVal = element.attr(KW_MIN_SIZE);
		String maxVal = element.attr(KW_MIN_SIZE);
		if(!minVal.isEmpty())
			min = Integer.parseInt(minVal);
		
		if(!maxVal.isEmpty())
			max = Integer.parseInt(element.attr(KW_MAX_SIZE));
		
		min = Math.min(min, max);
		return min + randomInt%(max-min+1);
		
	}

	public void setTableStructure(Element tableNode) {
		Element el = DATA_DOC.getElementById(tableNode.tagName());
		Elements items  = el.getElementsByTag(TAG_CHILD);
		int count = getIntInRange(el);
		if(items.size() > 0) {
			while(count-- > 0) {
				for(Element item:items) {
					if(item.attr(KW_CLASS).equals(TAG_MANDATORY)) {
						String tag = item.attr(KW_NAME);
						Element newNode = tableNode.appendElement(tag);
						setTableStructure(newNode);
					}
				}
			}
		} else {
			tableNode.text(String.valueOf(getIntInRange(el)));
		}
		
	}
}
