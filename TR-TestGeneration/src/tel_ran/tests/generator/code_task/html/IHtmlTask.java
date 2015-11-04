package tel_ran.tests.generator.code_task.html;
import org.jsoup.nodes.Element;

interface IHtmlTask {
	String getData();
	void generateTask(Element taskElement, int difficultyLevel);
	Element getMatching();
	boolean check(Element solution, Element matching);
	int getNumberOfDescription();

}
