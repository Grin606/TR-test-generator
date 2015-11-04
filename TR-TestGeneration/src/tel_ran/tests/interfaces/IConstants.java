package tel_ran.tests.interfaces;

import tel_ran.tests.box_generator.Abstract_Reasoning;
import tel_ran.tests.box_generator.Attention;
import tel_ran.tests.box_generator.Programming_Task;
import tel_ran.tests.box_generator.Quantative_Reasoning;

public interface IConstants {
	
	public static final String[] PROGRAM_LANGUAGES = {"Java","HTML"};
	
	public static final String[] CATEGORIES = {"Programming Tasks", "Abstract Reasoning", "Quantative Reasoning", "Attention"};
	
	public static final String[] CATEGORY_DIR_PATHS = {"Programming_Task", "Abstract_Reasoning", "Quantative_Reasoning", "Attention"};
	
	static final Class<?>[] CATEGORY_CLASSES = {Programming_Task.class,  Abstract_Reasoning.class, Quantative_Reasoning.class, Attention.class}; 
	
	
	public static final int PROGRAMMING_TASKS = 0;
	public static final int ABSTRACT_REASONING = 1;
	public static final int QUANTATIVE_REASOINING = 2;
	public static final int ATTENTION = 3;
	

}
