package examSources;


import java.io.*;
import java.net.*;
import java.util.*;

public class DoubleReplacementValidator
{

	/** Class that implements the double-substitution encryption and decryption */
	public static class DoubleSubstitution
	{

		private Set<String> validWords;

		/** Constructor */
		public DoubleSubstitution(Set<String> validWords) {
			this.validWords = validWords;
		}

		/**
		 * Given the encryption key and the ciphertext, return every possible plaintext message as a set
		 * of words from the supplied collection of valid words.
		 */
		public Set<String> decrypt(Map<String, String> key, String ciphertext)
		{
			Set<String> result = new HashSet<>();

			Map<String, List<String>> decryptionMap = new HashMap<>();
			for(Map.Entry<String,String> entry : key.entrySet())
			{
				List<String> valResult = decryptionMap.get(entry.getValue());
				if(valResult != null)
				{
					valResult.add(entry.getKey());
					decryptionMap.put(entry.getValue(), valResult);
				}
				else
				{
					valResult = new ArrayList<>();
					valResult.add(entry.getKey());
					decryptionMap.put(entry.getValue(), valResult);
				}
			}


			List<List<String>> resultPossibilities = new ArrayList<>();
			for(int i = 0; i < ciphertext.length()-1; i+=2)
			{
				String encryptedString = ciphertext.substring(i, i+2);
				List<String> possibleCharacters = decryptionMap.get(encryptedString);
				resultPossibilities.add(possibleCharacters);
			}

			String currentString = "";
			decryptionHelper(result, resultPossibilities, 0, 0, currentString);
			for(String s : result)
			{
				System.out.println(s);
			}

			return result;
		}

		/*
		[[a] [b] [c]]
		[[d] [e] [f] [z]]
		[[g] [h]]
		*/
		private void decryptionHelper(Set<String> resultSet, List<List<String>> mapOfPossibilities, Integer column, Integer row, String currentString)
		{
			//Add or find the string to results. Then -- that char from currentString, and iterate through the row
			currentString += mapOfPossibilities.get(row).get(column);
			if(row >= (mapOfPossibilities.size()-1))   //If we're at the last row
			{
				//Add then iterate
				if(!resultSet.contains(currentString))
				{
					resultSet.add(currentString);
				}
			}
			else
			{
				//recurse with the greater row
				decryptionHelper(resultSet, mapOfPossibilities, column, row+1, currentString);
			}

			//remove latest char
			currentString.substring(0, currentString.length()-1);

			for(int i = 1; i < mapOfPossibilities.get(row).size(); i++)
			{
				column++;
				currentString += mapOfPossibilities.get(row).get(column);
				decryptionHelper(resultSet, mapOfPossibilities, 0, row+1, currentString);
			}
		}

		/**
		 * Given the encryption key and the plaintext, returns the ciphertext encrypted via
		 * double-substitution.
		 */
		public String encrypt(Map<String, String> key, String plaintext)
		{
			// TODO: encrypt the plain text!
			String encryptedText = "";
			for(int i = 0; i < plaintext.length(); i++)
			{
				char c = plaintext.charAt(i);
				String encryptedChar = key.get(Character.toString(c));
				encryptedText += encryptedChar;
			}
			return encryptedText;
		}
	}
}

