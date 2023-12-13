package api;

/**
 * Replaces URL encoding to be readable 
 * Updated 11-27-23
 * 
 * @author Sengthida Lorvan
 */
public class FixString extends MultipleChoice {
	// Reference Arrays and a temporary array
	private static String[] tempArray1 = new String[numOfQuestions];
	private static String[] tempArray2 = new String[answerArray.length];
	private static String[] urlArray = { "%20", "%3F", "%27", "%E2%80%9C", "%E2%80%9D", "%22", "%E2%80%99", "%20%C3%89",
			"%24", "%26", "%E2%80%A6%", "%2C", "%C3%85%20", "%25", "%3A", "%2F", "%28", "%29", "%C3%BA" };
	private static final String[] _replaceChar = { " ", "?", "", "“", "”", "", "’", "É", "$", "&", "…", ",", "Å", "%", ":", "/", "(", ")", "ú" };
}
