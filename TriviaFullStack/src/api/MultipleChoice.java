package api;

/**
 * This class creates the answer array. Updated 11-25-23
 * 
 * @author Sengthida Lorvan
 */

 public class MultipleChoice extends Answers {

	public static String[] answerArray = new String[numOfQuestions * 4];
	private static String[] tempArray = new String[numOfQuestions];
	private static String[] storeIndex = new String[numOfQuestions];

	private static void assignAnswer(int i, int j, String[] arrayInput1, String[] arrayInput2, String[] arrayOutput1,
			String[] arrayOutput2) {
		do {
			arrayOutput1[j] = arrayInput1[i].substring(0, Integer.parseInt(arrayInput2[i]));
			arrayOutput2[i] = arrayInput1[i].substring(Integer.parseInt(arrayInput2[i]));
			j += 4;
			i++;
		} while (j < arrayOutput1.length);
	}

	public static String[] options() {
		store(incorrectAnswer, temp, numOfQuestions);
		num = ONE;
		reduce(temp, temp.length, num);
		key = "\"";
		// Removing the quotations from the incorrect anwsers
		find(temp, index, key);
		int i = 0;
		int j = 0;
		assignAnswer(i, j, temp, index, answerArray, tempArray);
		num = TWO;
		reduce(tempArray, tempArray.length, num);
		find(tempArray, storeIndex, key);
		j = 1;
		assignAnswer(i, j, tempArray, storeIndex, answerArray, tempArray);
		reduce(tempArray, tempArray.length, num);
		find(tempArray, storeIndex, key);
		j = 2;
		assignAnswer(i, j, tempArray, storeIndex, answerArray, tempArray);
		j = 3;
		do {
			answerArray[j] = correctAnswer[i];
			j += 4;
			i++;
		} while (j < answerArray.length);

		return answerArray;
	}
}
