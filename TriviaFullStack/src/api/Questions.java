package api;

/**
 * This class separates the questions from the query Updated 11-25-23
 *
 * @author Sengthida Lorvan
 */

 public class Questions extends API {
	// Arrays to store parsed parts
	public static String[] questionArray = new String[numOfQuestions];
	protected static String[] index = new String[numOfQuestions];
	protected static String[] temp = new String[numOfQuestions];

	// Final int for shifting through substring query
	protected static final int FOURLETTERS = 33;
	protected static final int MEDIUM = 35;

	public static String[] question() {
		try {
			Object getQuestionJSONArray;
			// Get questions
			for (int i = 0; i < numOfQuestions; i++) {
				getQuestionJSONArray = obj.getJSONArray("results").opt(i);
				questionArray[i] = getQuestionJSONArray.toString();
			}

			// Removes text in front of question
			for (int i = 0; i < numOfQuestions; i++) {
				if (difficulty.equals("easy") || difficulty.equals("hard")) {
					questionArray[i] = questionArray[i].substring(FOURLETTERS);
				} else if (difficulty.equals("medium")) {
					questionArray[i] = questionArray[i].substring(MEDIUM);
				}
			}

			key = "\"";
			// Gets index of next quotation mark
			find(questionArray, index, key);

			// Copy array to temp array
			store(questionArray, temp, numOfQuestions);

			num = ONE;
			// Assign separated questions into array
			assign(index, questionArray, num);

		} catch (Exception _ex) {
			System.out.println("Error: " + _ex);
		}
		return questionArray;
	}
}