package examSources;

import helperClasses.Pair;

import java.util.ArrayList;
import java.util.List;

//A password is considered strong if below conditions are all met:
//
//It has at least 6 characters and at most 20 characters.
//It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
//It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
//A function strongPasswordChecker(s), takes a string s as input, and returns the MINIMUM change
// required to make s a strong password. If s is already strong, it returns 0.
//
//Insertion, deletion or replace of any one character are all considered as one change.

public class PasswordStengtheningChecker
{
	public static int strongPasswordChecker(String password) throws InterruptedException
	{
		PasswordProperties passwordProperties = new PasswordProperties(password);

		Integer actionsTaken = 0;
		while(determineStateAndOrMakeChange(passwordProperties))
		{
			Thread.sleep(1000);
			actionsTaken++;
			PasswordProperties.populateValuesOfPasswordProperties(passwordProperties);
			displayDebugInformationOnVariables(passwordProperties);
		}

		return actionsTaken;
	}

	public static class PasswordProperties
	{
		String password;
		Integer length;

		//assert and measure uppercase, lowercase, and digit
		Integer upperCase;
		Integer lowerCase;
		Integer digit;
		List<Integer> upperCaseIndecies = new ArrayList<>();
		List<Integer> lowerCaseIndecies = new ArrayList<>();
		List<Integer> digitIndecies = new ArrayList<>();
		List<Integer> specialCharacterIndecies = new ArrayList<>();
		List<Pair<Integer,Integer>> beginningAndLengthOfConsequtiveCharacters = new ArrayList<>();

		PasswordProperties(String password)
		{
			this.password = password;
			PasswordProperties.populateValuesOfPasswordProperties(this);
		}

		public static void populateValuesOfPasswordProperties(PasswordProperties passwordProperties)
		{
			String repeatingString = " ";
			Integer repetitionOccurence = 0;
			//Reset values to properly increment.
			passwordProperties.length = passwordProperties.password.length();
			passwordProperties.upperCase = 0;
			passwordProperties.lowerCase = 0;
			passwordProperties.digit = 0;
			passwordProperties.beginningAndLengthOfConsequtiveCharacters = new ArrayList<>();

			for (int index = 0; index < passwordProperties.length; index++)
			{
				char charAtPosition = passwordProperties.password.charAt(index);
				String stringAtPosition = String.valueOf(charAtPosition);
				//determine if it's upper, lower, or digit
				if(Character.isLowerCase(charAtPosition))
				{
					passwordProperties.lowerCase++;
					passwordProperties.lowerCaseIndecies.add(index);
				}
				else if(Character.isUpperCase(charAtPosition))
				{
					passwordProperties.upperCase++;
					passwordProperties.upperCaseIndecies.add(index);
				}
				else if(Character.isDigit(charAtPosition))
				{
					passwordProperties.digit++;
					passwordProperties.digitIndecies.add(index);
				}
				else
				{
					//it's a special character
					passwordProperties.specialCharacterIndecies.add(index);
				}
				//determine if it's repeating, or exiting a repetition
				if(repeatingString.equals(stringAtPosition))
				{
					//it's a hit, we're repeating
					repetitionOccurence++;
				}
				//Else it's not a hit; determine if we had a 3+ repeat thus far
				else
				{
					if(repetitionOccurence >= 3)
					{
						//add existing Pair to List before replacing and resetting
						Integer positionOfBeginning = index-repetitionOccurence;
						passwordProperties.beginningAndLengthOfConsequtiveCharacters.add(new Pair<>(positionOfBeginning, repetitionOccurence));
					}

					repeatingString = stringAtPosition;
					repetitionOccurence = 1;
				}
			}
		}
	}

	private static boolean determineStateAndOrMakeChange(PasswordProperties passwordProperties)
	{
		boolean noConsequtives = passwordProperties.beginningAndLengthOfConsequtiveCharacters.isEmpty();
		if(lengthValidation(passwordProperties.length) && (passwordProperties.upperCase > 0) && (passwordProperties.lowerCase > 0)
				&& (passwordProperties.digit > 0) && noConsequtives)
		{
			return false;	//No action is needed to be taken!
		}

		//No Upper:
		//Add one
		if(!(passwordProperties.upperCase > 0) )
		{
			subroutineAddCategory(passwordProperties, CharacterEnum.UPPERCASE);
		}
		else if(!(passwordProperties.lowerCase > 0) )
		{
			subroutineAddCategory(passwordProperties, CharacterEnum.lowecase);
		}
		else if(!(passwordProperties.digit > 0) )
		{
			subroutineAddCategory(passwordProperties, CharacterEnum.d191t);
		}
		else if(!noConsequtives)
		{
			//take care of that lol
		}
		else if(!lengthValidation(passwordProperties.length))
		{

		}
		else
		{
			try
			{
				throw new Exception("Am I a joke to you?");
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		//Problems: May go over 20char limit
		//	Solution A: If any repetitions, break one up by changing it into an Upper
		//	Solution B: change the largest of Lowercase or Digits

		//No Lower
		//Same as Upper, but rotated.
		//	No Digit
		//Same as Lower, but rotated.
		//
		//Repetition of character 3x (or more)
		//Most complex
		//
		//Length > 20
		//Remove greatest of upper/lower/digit. If all 1, remove special char
		//
		//Length < 6
		//Add something



		return true;
	}

	private static void subroutineAddCategory(PasswordProperties passwordProperties, CharacterEnum additive)
	{
		//Problems: May go over 20char limit
		char charToAdd = additive.getChar();
		//Solution A: If any repetitions, break one up by changing it into an Upper
		if(!(passwordProperties.beginningAndLengthOfConsequtiveCharacters.isEmpty()))
		{
			//find these repetitions and address them if possible
			//TODO
		}
		else if(!(passwordProperties.length < 20))
		{
			//Solution B: change the largest of the other two
			//determine where to replace with the character.
			subroutineReplaceForCategory(passwordProperties, additive);
		}
	}

	private static void subroutineReplaceForCategory(PasswordProperties passwordProperties, CharacterEnum additive)
	{
		char charToAdd = additive.getChar();
		//	Further Problem B: What if no LowerCase or Digits?
		//		Further Solution: Assume all are special characters then
		if(passwordProperties.password.charAt(passwordProperties.length-1) == charToAdd)
		{
			charToAdd++;
		}
		passwordProperties.password += charToAdd;
	}

	private static boolean lengthValidation(Integer length)
	{
		return (length >= 6 && length <= 20);
	}

	private static void displayDebugInformationOnVariables(PasswordProperties passwordProperties)
	{
		System.out.println("Counts for debugging: Upper/Lower/Digit/Length: " + passwordProperties.upperCase + "/" +
				passwordProperties.lowerCase + "/" + passwordProperties.digit + "/" + passwordProperties.length);
		printListOfPairs(passwordProperties.beginningAndLengthOfConsequtiveCharacters);
	}

	private static void printListOfPairs(List<Pair<Integer, Integer>> beginningAndLengthOfConsequtiveCharacters)
	{
		System.out.println("List of pairs: ");
		for (Pair pair : beginningAndLengthOfConsequtiveCharacters)
		{
			System.out.println(pair.getKey() + ", " + pair.getVal());
		}
		System.out.println("End List of pairs.");
	}

	protected enum CharacterEnum
	{
		UPPERCASE(1),
		lowecase(2),
		d191t(3);

		private char toAdd;

		CharacterEnum(int identifier)
		{
			if(identifier == 1)
			{
				this.toAdd = 'X';
				return;
			}

			if(identifier == 2)
			{
				this.toAdd = 'x';
			}

			if(identifier == 3)
			{
				this.toAdd = '8';
			}
		}

		public char getChar()
		{
			return this.toAdd;
		}

	}
}
