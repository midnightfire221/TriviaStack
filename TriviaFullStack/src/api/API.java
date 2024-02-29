package api;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class performs the API call. 
 * Updated 11-25-23
 *
 * @author Sengthida Lorvan
 */

public class API {

	// Storage variables
	public static String difficulty = "easy"; // easy, medium, hard
	public static int category = 9; 
	public static int numOfQuestions = 5; // 1-50
	public static JSONObject obj;

	// Url Base
	public static final String BASEURL = "https://opentdb.com/api.php?";

	// Url parts
	public static final String ACTION1 = "amount=";
	public static final String ACTION2 = "&category=";
	public static final String ACTION3 = "&difficulty=";
	public static final String ACTION4 = "&type=multiple";
	public static final String ACTION5 = "&encode=url3986";

	// Variables for helper methods
	public static String key;
	public static int num;
	public static final int ONE = 1;
	public static final int TWO = 2;

	// Helper method for finding given string indexes
	protected static String[] find(String[] arrayInput, String[] arrayOutput, String key) {
		for (int i = 0; i < numOfQuestions; i++) {
			arrayOutput[i] = String.valueOf(arrayInput[i].indexOf(key));
		}
		return arrayOutput;
	}

	// Helper method for temporary storage
	protected static String[] store(String[] arrayInput, String[] arrayOutput, int loop) {
		for (int i = 0; i < loop; i++) {
			arrayOutput[i] = arrayInput[i];
		}
		return arrayOutput;
	}

	// Helper method for assigning wanted info into array
	protected static void assign(String[] arrayInput, String[] arrayOutput, int num) {
		switch (num) {
		case 1:
			for (int i = 0; i < numOfQuestions; i++) {
				arrayOutput[i] = arrayOutput[i].substring(0, Integer.parseInt(arrayInput[i]));
			}
			break;
		case 2:
			for (int i = 0; i < numOfQuestions; i++) {
				arrayOutput[i] = arrayInput[i];
			}
			break;
		}
	}

	// Helper method for assigning substring to array
	protected static void setToSub(String[] arrayOutput, String[] arrayInput1, String[] arrayInput2) {
		for (int i = 0; i < numOfQuestions; i++) {
			arrayOutput[i] = arrayInput1[i].substring(Integer.parseInt(arrayInput2[i]));
		}
	}

	protected static void reduce(String[] array, int loop, int index) {
		for (int i = 0; i < loop; i++) {
			array[i] = array[i].substring(index);
		}
	}

	public static void makeApiCall() {
		// Create url
		String urlString = BASEURL + ACTION1 + numOfQuestions + ACTION2 + category + ACTION3 + difficulty
				+ ACTION4 + ACTION5;
		URL url;
		try {
			url = new URL(urlString);
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");
			int status = connect.getResponseCode();
			if (status != 200) {
				System.out.println("Error: Could not get questions: " + status);
			} else {
				BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = input.readLine()) != null) {
					content.append(inputLine);
				}
				input.close();
				connect.disconnect();

				// Initialize JSON object
				obj = new JSONObject(content.toString());
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void run(){
        Questions.question();
        Answers.correctAnswers();
        Answers.incorrectAnswers();
        FixString.fixQuestions();
        FixString.fixCorrectAnswers();
        MultipleChoice.options();
        FixString.fixAnswers();
    }
	
	public static void main(String[] args) {
		makeApiCall();
		run();
	}

}