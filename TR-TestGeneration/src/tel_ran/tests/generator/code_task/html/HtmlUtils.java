package tel_ran.tests.generator.code_task.html;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.thedeanda.lorem.LoremIpsum;

class HtmlUtils {
	private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
	private static final String WELL_KNOWN = "Well-known fact:";
	private static final String STATEMENT_0 = "is not valid for HTML.";
	private static final String STATEMENT_1 = "The function:";
	private static final String STATEMENT_2 = "foo() { ";
	private static final String STATEMENT_3 = "}";
	private static final String STATEMENT_4= "returns ";

	static final TaskData taskData = new TaskData();
	static final LoremIpsum lorem = LoremIpsum.getInstance();
	static Random RND = new Random(System.currentTimeMillis());


	static void setAttributes(Element el) {
		Map<String,List<String>> attrMap = taskData.getAnyAttributes(el.tagName(),RND.nextInt());
		for(String attr:attrMap.keySet()) {
			List<String> values = attrMap.get(attr);
			if(values.isEmpty())
				el.attr(attr, lorem.getKeyWord());
			else
				el.attr(attr, attrMap.get(attr).get(RND.nextInt(values.size())));
		}
	}

	static String getMatchedString(String regExp, String origin) {
		Matcher matchGroup = Pattern.compile(regExp).matcher(origin);
		if(matchGroup.find())
			return matchGroup.group();
		return "";
	}
	
	static String getWhitespaceIndepended(String pattern) {
		pattern = pattern.replaceAll("\\{", " \\\\{ ").replaceAll("\\}", " \\\\} ");
		pattern = pattern.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		return pattern.replaceAll("\\s+", "\\\\s+?");
	}
	
	
	static Element getAnyElement(String className, String elText) {
		return new Element(getAnyTag(className), "").text(elText);
	}
	
	static Element getAnyElement(String className) {
		return new Element(getAnyTag(className), "");
	}
	
	static String getAnyTagName() {
		List<String> tags = taskData.getSectionList(TaskData.SECTION_TAG);
		return tags.get(RND.nextInt(tags.size()));
	}
	
	static String getAnyTagName(String className) {
		List<String> tags = taskData.getSectionList(TaskData.SECTION_TAG,TaskData.KW_CLASS,className);
		return tags.get(RND.nextInt(tags.size()));
	}
	
	static Tag getAnyTag(String className) {
		return Tag.valueOf(getAnyTagName(className));
	}
	
	static List<String> getTaskList(int nItems) {
		List<String> tasks = taskData.getSectionList(TaskData.SECTION_TASK);
		int taskSize = tasks.size();
		while(tasks.size() < nItems) {
			tasks.add(tasks.get(tasks.size()-taskSize));
		}
		while(tasks.size() > nItems) {
			tasks.remove(RND.nextInt(tasks.size()));
		}
		return tasks;
	}
	
	static String getListTypeName() {
		return getAnyTagName(TaskData.TAG_LIST_TYPE);
	}

	static String getParentTagName() {
		return getAnyTagName(TaskData.TAG_BLOCK);
	}

	static String getChildTagName() {
		return getAnyTagName(TaskData.TAG_INLINE);
	}
	
	static String getLineTagName() {
		return TaskData.TAG_HR;
	}

	static Tag getParagrafTag() {
		return Tag.valueOf(TaskData.TAG_P);
	}

	static String getHtmlParagrafTag() {
		return lorem.getHtmlParagraphs(1, 1);
	}

	static String getElementBracket(Element el, boolean left) {
		String strPat = el.tagName() + "[^<>]*?>{1}";
		if(left)
			strPat = "<" + strPat;
		else
			strPat = "</" + strPat;
		return getMatchedString(strPat,el.outerHtml());
	}
	
	static boolean matchingElements(Element element0,Element element1) {
		if(!matchingAttributes(element0, element1))
			return false;
		if(!matchingWhitespaceIgnore(element0.ownText(),element1.ownText()))
			return false;
		Elements elements0 = element0.children();	
		Elements elements1 = element1.children();
		if(elements0.size() != elements1.size())
			return false;
		for(Element el0:elements0) {
			int index = 0;
			for(Element el1:elements1) {
				if(matchingElements(el0,el1)) {
					break;
				}
				index++;
			}
			if(index == elements1.size())
				return false;
			elements1.remove(index);
		}
		return 0 == elements1.size();
	}
	
	static boolean matchingAttributes(Element element0,Element element1) {
		Attributes attrList0 = element0.attributes();
		Attributes attrList1 = element1.attributes();
		if(attrList0.size() != attrList1.size())
			return false;
		for(Attribute attr0:attrList0) {
			if(attrList1.hasKey(attr0.getKey())) {
				if(matchingWhitespaceIgnore(attr0.getValue(),attrList1.get(attr0.getKey()))) {
					attrList1.remove(attr0.getKey());					
				}
			}
		}
		return 0 == attrList1.size();
	}
	
	static boolean matchingWhitespaceIgnore(String str0,String str1) {
		return (str0.isEmpty() && str1.isEmpty()) || str0.matches(getWhitespaceIndepended(str1));
	}
	
	static Document prepareHtmlDoc() {
		Document doc = Document.createShell(TaskData.DEFAULT_BASE_URI);
		doc.prependChild(new DocumentType(TaskData.DEFAULT_DOC_TYPE, TaskData.DEFAULT_TYPE_PUBLIC, TaskData.DEFAULT_SYSTEM_ID, TaskData.DEFAULT_BASE_URI));
		doc.head().appendElement(TaskData.TAG_TITLE).text(lorem.getTitle(1, 3));
		doc.head().appendElement(TaskData.TAG_META).attr(TaskData.META_HTTP_EQUIV_KEY, TaskData.META_HTTP_EQUIV_VAL)
										.attr(TaskData.META_CONTENT_KEY, TaskData.META_CONTENT_VAL);
		return doc;
	}


	static Element getAnyList() {
		Element list = getAnyElement(TaskData.TAG_LIST_TYPE);
		setAttributes(list);
		taskData.setListStructure(list, RND.nextInt());
		for(Element item : list.children()) {
			setAttributes(item);
			int nWords = Integer.parseInt(item.ownText());
			item.text(lorem.getTitle(nWords));
		}
		return list;
	}

	public static Element getAnyTable() {
		Element table = getAnyElement(TaskData.TAG_TABLE);
		setAttributes(table);
		taskData.setTableStructure(table);
		fillChildren(table);
		return table;
	}
	
	
	private static void fillChildren(Element tableNode) {
		for(Element item : tableNode.children()) {
			setAttributes(item);
			String strNum = item.ownText();
			if(!strNum.isEmpty()) {
				int nWords = Integer.parseInt(strNum);
				item.text(lorem.getTitle(nWords));
			}
			fillChildren(item);
		}
	}

	static String[] getAnyFalsePhrase() {
		String[] phraseParts = new String[2];
		switch(RND.nextInt(3)){
		case 0 :
			phraseParts[0] = WELL_KNOWN; 
			phraseParts[1] = mathStatement(); 
			return phraseParts;
		case 1 :
			phraseParts[0] = STATEMENT_1; 
			phraseParts[1] = logicStatement(); 
			return phraseParts;
		default :
			List<String> tags = taskData.getSectionList(TaskData.SECTION_TAG);
			phraseParts[0] = WELL_KNOWN; 
			phraseParts[1] = TaskData.KW_TAG + " <" + tags.get(RND.nextInt(tags.size())) + "> " + STATEMENT_0; 
			return phraseParts;
		}
	}

	private static String mathStatement() {
		int a = RND.nextInt(50)+1;
		int b = RND.nextInt(50)+1;
		int c;
		String leftPart;
		if(RND.nextBoolean()) {
			c = a + b;
			leftPart = String.valueOf(a) + " + " + String.valueOf(b);
		} else {
			c = Math.max(a, b) - Math.min(a, b);
			leftPart = String.valueOf(Math.max(a, b)) + " - " + String.valueOf(Math.min(a, b));
		}
		if(RND.nextBoolean())
			return leftPart + " < " + String.valueOf(c);
		else
			return leftPart + " <= " + String.valueOf(c);
	}

	private static String logicStatement() {
		boolean a = RND.nextBoolean();
		boolean b = RND.nextBoolean();
		boolean c = !(a && b);
		
		char letter = LETTERS.charAt(RND.nextInt(LETTERS.length()));
		String isNot = "";
		if(RND.nextBoolean()) {
			c = (a && b);
			isNot = "!";
		}
		String res = STATEMENT_2 + " " + letter + " = " + String.valueOf(a) + ";"+
				" " + letter + " &= " + String.valueOf(b) + ";" + 
				" return " + isNot + letter + ";" +
				" " + STATEMENT_3 + " " +
				STATEMENT_4 + String.valueOf(c).toUpperCase() + ".";
			return res;
	}
	
}
