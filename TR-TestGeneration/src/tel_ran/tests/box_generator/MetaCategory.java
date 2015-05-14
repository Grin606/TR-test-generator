package tel_ran.tests.box_generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tel_ran.tests.exceptions.TasksException;

/** This class is only for meta-category naming 
 * It keeps an array of string with short names (it receives it from the classes of meta-categories) 
 * and also an array of Class for class-reflection, that can be called from generation class (TestProcessor)
 * **/
public class MetaCategory {
	
	static final Class<?>[] classNames = {Abstract_Reasoning.class, Attention.class, Programming_Task.class, Quantative_Reasoning.class}; 
	static final String[] typeNames = {Abstract_Reasoning.category, Attention.category, Programming_Task.category, Quantative_Reasoning.category};
	
	
	public static List<String> getMetaCategory() {
		List<String> result = new ArrayList<String>();
		for (String type : typeNames)
			result.add(type);
		return result;
	}
	
	public static Class<?> getClass(String type) throws TasksException {
		
		int pose = Arrays.binarySearch(typeNames, type);
		if (pose < 0)
			throw new TasksException("Wrong Meta Type Name!");		
		return classNames[pose];		
	}
	
	public static Class<?> getClass(int type) throws TasksException {
	
		return classNames[type];		
	}
	

}
