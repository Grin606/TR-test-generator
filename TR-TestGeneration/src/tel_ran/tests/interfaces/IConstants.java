package tel_ran.tests.interfaces;

import tel_ran.tests.box_generator.Abstract_Reasoning;
import tel_ran.tests.box_generator.Attention;
import tel_ran.tests.box_generator.Programming_Task;
import tel_ran.tests.box_generator.Programming_Task_Html;
import tel_ran.tests.box_generator.Programming_Task_Java;
import tel_ran.tests.box_generator.Programming_Task_Javascript;
import tel_ran.tests.box_generator.Quantative_Reasoning;

public interface IConstants {
	

	public static final String[] PROGRAM_LANGUAGES = {"Java","HTML", "Javascript"};
	public static final Class<?>[] PR_LANGUAGES_CLASSES = {
		Programming_Task_Java.class,
		Programming_Task_Html.class, 
		Programming_Task_Javascript.class
	};
	
	public static final String[] CATEGORIES = {"Programming Tasks", "Abstract Reasoning", "Quantative Reasoning", "Attention"};
	
	public static final String[] CATEGORY_DIR_PATHS = {"Programming_Task", "Abstract_Reasoning", "Quantative_Reasoning", "Attention"};
	
	static final Class<?>[] CATEGORY_CLASSES = {Programming_Task.class,  Abstract_Reasoning.class, Quantative_Reasoning.class, Attention.class}; 
	
	
	public static final int PROGRAMMING_TASKS = 0;
	public static final int ABSTRACT_REASONING = 1;
	public static final int QUANTATIVE_REASOINING = 2;
	public static final int ATTENTION = 3;
	
	public static final int JAVA = 0;
	public static final int HTML = 1;
	public static final int JAVASCRIPT = 2;
	

}
