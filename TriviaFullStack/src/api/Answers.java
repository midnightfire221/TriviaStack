package api;

/**
 * This class separates the questions from the query 
 * Updated 11-25-23
 *
 * @author Sengthida Lorvan
 */

 public class Answers extends Questions {
	// Arrays to hold the respective answers
	public static String[] correctAnswer = new String[numOfQuestions];
	protected static String[] incorrectAnswer = new String[numOfQuestions];

	// Substring number for the respective answers
	private static final int OSUB = 20;
	private static final int XSUB = 23;

	public static String[] correctAnswers() {
		// Gets text after questions
		setToSub(correctAnswer, temp, index);

		// Gets rid of text before
		reduce(correctAnswer, numOfQuestions, OSUB);

		// Gets index of next quotation mark
		find(correctAnswer, index, key);

		// Copy to temp
		store(correctAnswer, temp, numOfQuestions);

		num = ONE;
		// Assigns correct answer by itself into array
		assign(index, correctAnswer, num);

		// Sets leftover substring into this array
		for (int i = 0; i < numOfQuestions; i++) {
			incorrectAnswer[i] = temp[i].substring(Integer.parseInt(index[i]));
		}
		//for (int i = 0; i < numOfQuestions; i++) {
		//	System.out.println(correctAnswer[i]);
		//}
		return correctAnswer;
	}

	public static String[] incorrectAnswers() {
		// Set array equal to temp
		setToSub(incorrectAnswer, temp, index);

		// Get rid of text in front
		reduce(incorrectAnswer, numOfQuestions, XSUB);

		// Find index of next bracket
		key = "]";
		find(incorrectAnswer, index, key);

		// Array with incorrect answers that still need to separate
		for (int i = 0; i < numOfQuestions; i++) {
			incorrectAnswer[i] = incorrectAnswer[i].substring(0, Integer.parseInt(index[i]));
		}

		// Get rid of commas and replace
		for (int i = 0; i < numOfQuestions; i++) {
			incorrectAnswer[i] = incorrectAnswer[i].replace(",", "");
			if (incorrectAnswer[i].contains("\" ")) {
				incorrectAnswer[i] = incorrectAnswer[i].replace("\" ", "\"");
			}
			//if (incorrectAnswer[i].contains("\\tN")) {
				//incorrectAnswer[i] = incorrectAnswer[i].replace("\\tN", "N");
			//}
		}
		//for (int i = 0; i < numOfQuestions; i++) {
	//		System.out.println(incorrectAnswer[i]);
	//	}
		
		return incorrectAnswer;
	}
}
