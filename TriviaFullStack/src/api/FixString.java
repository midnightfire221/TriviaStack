package api;

/**
 * Replaces URL encoding to be readable 
 * Updated 12-13-23
 * 
 * @author Sengthida Lorvan
 */
public class FixString extends MultipleChoice {
	// Reference Arrays and a temporary array
	private static String[] tempArray1 = new String[numOfQuestions];
	private static String[] tempArray2 = new String[answerArray.length];
	private static String[] urlArray = { "%20", "%3F", "%27", "%E2%80%9C", "%E2%80%9D", "%22", "%E2%80%99", "%20%C3%89",
			"%24", "%26", "%E2%80%A6%", "%2C", "%C3%85%20", "%25", "%3A", "%2F", "%28", "%29", "%C3%BA" };
	private static final String[] replaceChar = { " ", "?", "", "�", "�", "", "�", "�", "$", "&", "�", ",", "�", "%", ":", "/", "(", ")", "�" };
	// Helper method that sets the key and then calls the helper method for replacing url
    private static void check(int loop1, int loop2, String key, String[] arrayInput1, String[]array, String[] arrayInput2){
        for (int i = 0; i < loop1; i++) {
            key = arrayInput1[i];
            for (int j = 0; j < loop2; j++) {
                if (array[j].contains(key)) {
                    replaceUrl(array, key, arrayInput2[i], loop2);
                }
            }
        }
    }

    // Helper method that replaces url
    private static String[] replaceUrl(String[] array, String key, String replace,int loopNum) {
        int loop = loopNum;
        for (int i = 0; i < loop; i++) {
            array[i] = array[i].replace(key, replace);
        }
        return array;
    }

    public static String[] fixQuestions() {
        store(questionArray, tempArray1, numOfQuestions);
        check(urlArray.length, numOfQuestions, key, urlArray,tempArray1, replaceChar);
        store(tempArray1, questionArray, numOfQuestions);
        num = TWO;
        assign(tempArray1, questionArray, num);
        for (int i = 0; i < questionArray.length; i++) {
        	System.out.println(questionArray[i]);
        }
        return questionArray;
    }

    public static String[] fixCorrectAnswers() {
        store(correctAnswer, tempArray1, numOfQuestions);
        check(urlArray.length, numOfQuestions, key, urlArray,tempArray1, replaceChar);
        store(tempArray1, correctAnswer, numOfQuestions);
        num = TWO;
        assign(tempArray1, correctAnswer, num);
        return correctAnswer;
    }

    public static String[] fixAnswers() {
        store(answerArray, tempArray2, answerArray.length);
        check(urlArray.length, answerArray.length, key, urlArray,tempArray2, replaceChar);
        store(tempArray2, answerArray, answerArray.length);
        num = TWO;
        assign(tempArray2, correctAnswer, num);
        for (int i = 0; i < answerArray.length; i++) {
        	System.out.println(answerArray[i]);
        }
        return answerArray;
    }
}
