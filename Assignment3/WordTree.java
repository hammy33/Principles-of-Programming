import java.util.ArrayList;

public class WordTree {
	
	private TreeNode root;
	
	public WordTree() {
		root = new TreeNode(); 
	}
	
	public int numOfWords(){
		return numOfWordsRec(root);
	}
	
	private int numOfWordsRec(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int childrenWords = 0;
		for(int i = 0; i < 26; i++) {
			childrenWords += numOfWordsRec(node.children[i]);
		}
		if(node.isWord) {
			return 1 + childrenWords;
		}
		return childrenWords;
	}
	
	public void insertWord(String word) {
		insertWordRec(root, word);
	}
	
	private void insertWordRec(TreeNode node, String word) {
		char firstChar = word.charAt(0);
		if(word.length() == 1) {
			if(node.children[letterToInt(firstChar)] == null) {
				node.children[letterToInt(firstChar)] = new TreeNode();
				node.children[letterToInt(firstChar)].isWord = true;
			}
			else {
				node.children[letterToInt(firstChar)].isWord = true;
			}
		}
		else {
			String restOfWord = word.substring(1);
			if(node.children[letterToInt(firstChar)] == null) {
				node.children[letterToInt(firstChar)] = new TreeNode();
				insertWordRec(node.children[letterToInt(firstChar)], restOfWord);
			}
			else {
				insertWordRec(node.children[letterToInt(firstChar)], restOfWord);
			}
		}
	}
	
	public boolean contains(String word) {
		if(word.length() == 0) {
			return false;
		}
		TreeNode Node = root;
		String first = word.substring(0, word.length() - 1);
		for (int i = 0; i < first.length(); i++) {
			if(Node.children[letterToInt(word.charAt(i))] == null) {
				return false;
			}
			Node = Node.children[letterToInt(word.charAt(i))];
		}
		char end = word.charAt(word.length() - 1);
		if (Node.children[letterToInt(end)] != null && Node.children[letterToInt(end)].isWord) {
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<String> suggestCorrections(String word, int offBy){
		if(word.length() == 0) {
			return new ArrayList<String>();
		}
		else {
			ArrayList<String> fullList = getWords("");
			ArrayList<String> newList = new ArrayList<String>();
			for(int i = 0; i < fullList.size(); i++) {
				if (word.length() == fullList.get(i).length()) {
					int counter = 0;
					for (int j = 0; j < word.length(); j++) {
						if(word.charAt(j) != fullList.get(i).charAt(j)) {
							counter++;
						}
					}
					if (counter <= offBy) {
						newList.add(fullList.get(i));
					}
				}
			}
			return newList;
		}
	}

	public static boolean isLeaf(TreeNode Node) {
		for (int i = 0; i < 26; i++) {
			if (Node.children[i] != null) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<String> suggestAutoCompletes(String prefix) {
		if(prefix.length() == 0) {
			return getWords(prefix);
		}
		if(prefixCheck(prefix) == false) {
			return new ArrayList<String>();
		}
		else {
			return getWords(prefix);
		}
	}
	
	public boolean prefixCheck(String prefix) {
		TreeNode Node = root;
		for (int i = 0; i < prefix.length(); i++) {
			if(Node.children[letterToInt(prefix.charAt(i))] == null) {
				return false;
			}
			Node = Node.children[letterToInt(prefix.charAt(i))];
		}
		return true;
	}
	public TreeNode searchPrefix(String prefix) {
		TreeNode current = root;
		for(int i = 0; i < prefix.length(); i++) {
			char value = prefix.charAt(i);
			if(current.children[letterToInt(value)] != null) {
				current = current.children[letterToInt(value)];
			}
			else {
				return null;
			}
		}
		return current;
	}

	public ArrayList <String> getWords(String prefix) {
		TreeNode node = searchPrefix(prefix); 
		if(node != null) {
			ArrayList<String> wordList = new ArrayList<String>();
			getWordsHelper(node, prefix, wordList);
			return wordList;
		}
		return new ArrayList<String>();
	}
	private void getWordsHelper (TreeNode node, String word, ArrayList<String> wordList) {
		if(node.isWord) {
			wordList.add(word);
		}
		for (int i = 0; i < 26; i++) {
			TreeNode next = node.children[i];
			if (next != null) {
				getWordsHelper(next, word + intToLetter(i), wordList);
			}
		}
	}
	
	public ArrayList<String> addToEach(char c, ArrayList<String> words) {
		ArrayList<String> newWords = new ArrayList<String>(); 
		for (int i = 0; i < words.size(); i++) {
			String element = words.get(i);
			element = c + element;
			newWords.add(element);
		}
		return newWords;
	}

	public int letterToInt(char c) {
		return c - 97;
	}
	
	public char intToLetter(int i) {
		return (char)(i + 97);
	}
	
}


