import java.util.*;
import java.io.*;
public class QuestionTree {
	Scanner console = new Scanner(System.in);
	QuestionNode overallroot;
	String prompt;
	String answer1;
	String newQuestion;
	String answer2;
	
	
	
	public QuestionTree() {
		overallroot = new QuestionNode("computer");
	}
	
	public void read(Scanner input) {
		if(input.hasNextLine()) {
			String hols = "";
			//overallroot.data = input.nextLine();
			read(overallroot,input,hols);
			System.out.println("end");
		}
		input.close();
		
	}
	
	private void read(QuestionNode root, Scanner input, String hols) {
		if(input.hasNextLine()) {
			hols = input.nextLine();
			if(hols.contains("A")) {
				root.left = new QuestionNode(input.nextLine());
			}
			else {
				if(root.data == "computer") {
					root.data = input.nextLine();
					read(root,input,hols);
				}
				else {
				root.left = new QuestionNode(input.nextLine());
				read(root.left, input, hols);
				}
			}
			if(input.hasNextLine()) {
				hols = input.nextLine();
				root.right = new QuestionNode(input.nextLine());
			}	
		}
	}
	
	
	public void write(PrintStream output) throws FileNotFoundException {
		System.out.print("Do you want to save this tree? (y/n) ");
		String hold = console.next();
		if(hold.contains("y")) {
			System.out.print("What would you like to name this file? ");
			output = new PrintStream(new File(console.next()));
			output.println("Q");
			output.println(overallroot.data);
			writeRecursionLeft(overallroot.left, output);
			writeRecursionRight(overallroot.right,output);
		}
	}
	
	private void writeRecursionLeft(QuestionNode root, PrintStream output) {
		if(root.left == null && root.right == null) {
			output.println("A");
			output.println(root.data);
		}
		else {
			output.println("Q");
			output.println(root.data);
			writeRecursionLeft(root.left, output);
			if(root.right != null && (root.right.right == null && root.right.left == null )) {
				output.println("A");
				output.println(root.right.data);
			}
			else {
				output.println("Q");
				output.println(root.right.data);
			}
		}
	}
	private void writeRecursionRight(QuestionNode root, PrintStream output) {
		if(root.left == null && root.right == null) {
			output.println("A");
			output.println(root.data);
		}
		else {
			output.println("Q");
			output.println(root.data);
			writeRecursionRight(root.left, output);
			if(root.right != null && (root.right.right == null || root.right.left == null )) {
				output.println("A");
				output.println(root.right.data);
			}
			else {
				output.println("Q");
				output.println(root.right.data);
				writeRecursionRight(root.right.left, output);
			}
		}
	}
	
	
	
	public void askQuestions() {
		Boolean h = true;
		recursion(overallroot, h);
	}
	
	private void recursion(QuestionNode root, Boolean h) {
		Boolean hold = true;
		
		if(root.left == null && root.right == null) {
			h = yesTo("Would your object happen to be a " + root.data);
			if(h) {
				System.out.println("Great, I got it right!");
				hold = h;
				return;
			}
			else {
				hold = h;
			}
		}
		
		else {
			h = yesTo(root.data);
			if(!h && root.right != null) {
				recursion(root.right,h);
			}
			else if(h && root.left != null) {
				recursion(root.left,h);
			}
		}
		if(root.left == null || root.right == null || !hold) {
			
			if((!h && root.right == null) || (h && root.left == null)) {
				System.out.print("What is the name of your object? ");
				answer1 = console.nextLine();
				System.out.print("Please give me a yes/no question that distinguishes between your object and mine --> ");
				newQuestion = console.nextLine();
				System.out.print("And what is the answer for your object? ");
				answer2 = console.nextLine();
				
				if(!hold) {
					String holder2 = root.data;
					root.data = newQuestion;
					root.right = new QuestionNode(holder2);
					root.left = new QuestionNode(answer1);
				}
				
				else if(!h) {
					root.right = new QuestionNode(newQuestion);
					if(answer2.contains("y")) {
						root.right.left = new QuestionNode(answer1);
					}
					else {
						root.right.right = new QuestionNode(answer1);
					}
				}
				else {
					root.left = new QuestionNode(newQuestion);
					if(answer2.contains("y")) {
						root.left.left = new QuestionNode(answer1);
					}
					else {
						root.left.right = new QuestionNode(answer1);
					}
				}
	 		 }
		}
			
			
	}
	
	
	public boolean yesTo(String prompt) {
		System.out.print(prompt + " (y/n)? ");
		 String response = console.nextLine().trim().toLowerCase();
		 while (!response.equals("y") && !response.equals("n")) {
		 System.out.println("Please answer y or n.");
		 System.out.print(prompt + " (y/n)? ");
		 response = console.nextLine().trim().toLowerCase();
		 }
		 return response.equals("y");
	}
}
