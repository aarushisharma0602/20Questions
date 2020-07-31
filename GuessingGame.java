/** @author Aarushi April 25
*/
import java.util.*;
import java.util.Arrays;
public class GuessingGame{
	private BinaryTreeNode<String> currentNode;
	private GameTree<String> guessingTree;
	public boolean freshGame;
	private GameTreeReader guessingTreeReader;
	/** Constructor 
	  *@param thisFile name (xml) */
	public GuessingGame(String thisFile){
		guessingTreeReader = new GameTreeReader(thisFile);
		guessingTree = guessingTreeReader.buildGameTree();
		currentNode = guessingTree.getRoot(); 
		freshGame = true;

	}
	/** Method to guess the answer 
	  * It takes care of printing on screen 
	  */
	public void guessAnswer(){
		print("Want to play 20 Questions? [yes/no]");
		if (receiveInput().equals("yes")){
			while(freshGame == true){
				print("Alright! When I ask a question, answer in 'yes' or 'no'");
				print("Choose an American University/College from this set and I will try to guess your choice");
				print("[" + printList(guessingTree.getRoot()) + "]");
				print("Are you ready to begin?");
				if(receiveInput().equals("yes")){
					while(currentNode.isLeaf() == false){
						print(currentNode.getData());
						String answer = receiveInput();
						if (answer.equals("yes")){
							currentNode = currentNode.getLeftChild();
						}
						else if (answer.equals("no")){
							currentNode = currentNode.getRightChild();
						}
						else{
							print("Come on! Type yes or no!");
						}
					}
				print("Were you thinking of " + currentNode.getData() + "?");
				String answer = receiveInput();
				if (answer.equals("yes")){
					print("Yayy, I guessed it!");
						freshGame = true; //added for ease of structuring UnrestrictedGame
					}
				else if (answer.equals("no")){
					print("Whoops, I guessed it wrong :(");
						freshGame = false; //added for ease of structuring UnrestrictedGame
					}
				else{
					print("Come on! Type yes or no!");
					}
				freshGame();
				}
			else{
				System.exit(0);
				}
			}
		}
		else{
			System.exit(0);
			}
	}
			
	/** Helper method to make taking input easier
	  * @return The string that the user entered
	  */
	public String receiveInput(){
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	/** Helper method to print easily 
	 *@param thisString
	 *action- prints output on screen
	 */
	public void print(String thisString){
		System.out.println(thisString);
	}
	/** Helps in printing the list of things on screen 
	  * @param thisRoot node of the binaryTree whose leafs need to be printed
	  * @return String that contains all the leafs of the binary tree
	  */
	public String printList(BinaryTreeNode<String> thisRoot){
		String stringList =  "";
		if (thisRoot == null){
			return stringList;
		}
		if (thisRoot.isLeaf() == true){
			stringList = stringList + " " + thisRoot.getData() + ",";
			//System.out.print(" " + thisRoot.getData());
		}
		else{
			stringList = stringList + printList(thisRoot.getLeftChild());
			stringList = stringList + printList(thisRoot.getRightChild());
		}
		return stringList;
		
	}
	/** Helper method to start a fresh game */
	public void freshGame(){
		print("Do you want to play again?");
				if(receiveInput().equals("yes")){
					currentNode = guessingTree.getRoot();
					freshGame = true;
				}
				else{
					freshGame = false;
				}
		}
	/** Getter method for currentNode instance variable
	  * @return BinaryTreeNode */
	public BinaryTreeNode<String> getCurrentNode(){
		return currentNode;
	}
	public static void main(String[] args) {
		GuessingGame guessGameObject = new GuessingGame(args[0]);
		guessGameObject.guessAnswer();
	}
}