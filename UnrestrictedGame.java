/** @author Aarushi Sharma April 25, 2020
*/
import java.util.*;
public class UnrestrictedGame extends GuessingGame{
	public UnrestrictedGame(String thisFile){
		super(thisFile);
	}
	/** Overriding fresh game to add new element in the binary tree*/
	public void freshGame(){
		BinaryTreeNode<String> helperNode = super.getCurrentNode();
		if (freshGame == false){
			super.print("What is the name of the university/college you were thinking of?");
			String newNodeData = super.receiveInput();
			super.print("Please give me a yes/no question that would have determined this university.");
			String newQuestion = super.receiveInput();
			super.print("Is the answer yes or no?");
			String newAnswer = super.receiveInput();
			if (newAnswer.equals("yes")){
				GameTreeNode<String> newNode = new GameTreeNode<String>(newNodeData);
				helperNode.setLeftChild(newNode);
				helperNode.setRightChild(new GameTreeNode<String>(helperNode.getData()));
			}
			else{
				GameTreeNode<String> newNode = new GameTreeNode<String>(newNodeData);
				helperNode.setRightChild(newNode);
				helperNode.setLeftChild(new GameTreeNode<String>(helperNode.getData()));
			}
			helperNode.setData(newQuestion);
			super.freshGame();
		}
		else{
			super.freshGame();
		}
	}
	public static void main(String[] args) {
		UnrestrictedGame unrestrictedGameObject = new UnrestrictedGame(args[0]);
		unrestrictedGameObject.guessAnswer();
	}
}