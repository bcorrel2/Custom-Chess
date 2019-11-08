package gameFiles.misc;

import gameFiles.misc.chessGUI;
import gameFiles.pieces.gamePiece;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import gameFiles.board.gameBoard;

//======================================== //
//		       Chess - CS242               //
//            @author bcorrel2             //
//======================================== //

public class controller {
	
	private gameBoard board;
	private JFrame frame;
	private chessGUI gui;
	
	private static String player1 = "White";
	private static String player2 = "Black";
	
	// Track Wins Across Games
	private int whiteScore;
	private int blackScore;
	
	// Determine Starting and Ending Space
	public coord firstClick;
	public coord secondClick;
	
	// Saves Previous Gamestate
	private gameBoard undoBoard;
	
	public controller() {
		
	    board = gameBoard.setBoard();
	    
	    frame = new JFrame("Chess");
	    gui = new chessGUI(board, frame);
	
	    // Initialize Values
	    firstClick = null; secondClick = null;
	    whiteScore = 0; blackScore = 0;
	    
	    gui.initSpaceListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e) {
	    	  	
	    	  // Assign All Action Listeners
	    	  
	    	  Object source = e.getSource();
	    	  JButton button = (JButton) source;
	    	  
	    	  if(firstClick == null && (button.getText().equals("") || (button.getForeground() == Color.BLACK && board.getTurn() == false ) || (button.getForeground() == Color.GRAY && board.getTurn() == true))) {
	    		  firstClick = null; secondClick = null;
	    	  }
	    	  
	          else if(firstClick == null) {
	    		  firstClick = chessGUI.getButtonCoord(button);
	    		  button.setBorder(new LineBorder(Color.YELLOW));
	    	  }
	    	  
	    	  else {
	    		  
	    		  boolean curr_turn = board.getTurn();
	    		  
	    		  secondClick = chessGUI.getButtonCoord(button);
	    		  undoBoard = new gameBoard(board);
	    		  board.moveCommand(firstClick.getX(), firstClick.getY(), secondClick.getX(), secondClick.getY());
	    		  gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
	    		  chessGUI.paintBordersBlack();
	    		  gui.updateBoard(board, firstClick.getX(), firstClick.getY(), secondClick.getX(), secondClick.getY());
	    		  firstClick = null; secondClick = null; 
	    		  
	    		  if(board.getCheck() && board.getCheckmate()) {
	    			  if(board.getWinner())
	    				  blackScore++;
	    			  else whiteScore++;
	    	  	  }
	    		  
	    		  if(curr_turn == board.getTurn())
	    			  gui.illegalMessage();
	    	  }	
	    	  
	    	  //board.printGameState();
	      }
	    });
	    
	    gui.initNewGame(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	board = gameBoard.setBoard();
		    	firstClick = null; secondClick = null;
		    	gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		    	resetScores();
		    	chessGUI.paintBordersBlack();
		    	gui.initBoard(board); 
		  }
		} );
	    
	    gui.initNewFairyGame(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	board = gameBoard.setFairyBoard();
		    	firstClick = null; secondClick = null;
		    	gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		    	resetScores();
		    	chessGUI.paintBordersBlack();
		    	gui.initBoard(board); 
		  }
		} );
	    
	    gui.initUndo(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	
		    	  if(undoBoard != null) {
		    		  
		    		  gamePiece undoPiece = board.lastPieceMoved;	  
		    		  board = new gameBoard(undoBoard);
		    		  board.lastPieceMoved = undoPiece;
		    		  
		    		  gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		    		  firstClick = null; secondClick = null;
		    		   
		    		  undoBoard = null; 
		    		  board.undo(board);
		    		  
		    		  gui.initBoard(board);
		    	  }
		  }
		} );
	    
	    gui.initChangeNames(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  String p1 = (String) JOptionPane.showInputDialog(frame, "Player 1:\n", "Customized Dialog", JOptionPane.PLAIN_MESSAGE);      
		    	  String p2 = (String) JOptionPane.showInputDialog(frame, "Player 2:\n", "Customized Dialog", JOptionPane.PLAIN_MESSAGE);
		          
		          player1 = new String(p1);
		          player2 = new String(p2);
		    	  
		          gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		  }
		} );
	    
	    gui.initDisplayScores(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	gui.displayScores(whiteScore, blackScore);
		  }
		} );
	    
	    gui.initNextGame(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	board = gameBoard.setBoard();
		    	firstClick = null; secondClick = null;
		    	gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		    	chessGUI.paintBordersBlack();
		    	gui.initBoard(board); 
		  }
		} );
	    
	    gui.initBlackForfeit(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	 
		    	 if(!board.getCheckmate()) { 
		    		 whiteScore++;
		    		 board = gameBoard.setBoard();
		    		 firstClick = null; secondClick = null;
		    		 gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
		    		 chessGUI.paintBordersBlack();
		    	 	gui.initBoard(board); 
		    	 }
		  }
		} );
	    
	    gui.initWhiteForfeit(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  if(!board.getCheckmate()) { 
			    		 blackScore++;
			    		 board = gameBoard.setBoard();
			    		 firstClick = null; secondClick = null;
			    		 gui.resetMessageBox(board.getTurn(), board.getCheck(), board.getCheckmate(), board.getWinner());
			    		 chessGUI.paintBordersBlack();
			    	 	gui.initBoard(board); 
			    	 }
		  }
		} );
	      
	}
	
	public static String getPlayer(int no) {
		// Function for Getting Player Name
        // @param no: 1 for Player 1, 2 for Player 2  
		
		if(no == 1) return player1;
		else if(no == 2) return player2;
		else return "";
	}
	
	private void resetScores() {
		// Function for Reseting Player Scores 
		
		blackScore = 0; whiteScore = 0;
	}
	
	public static void main(String[] args) {
		// Main Function - Executes GUI
		
			// Sets Board with Default Configuration
			gameBoard board = gameBoard.setBoard();
			
			new controller();
		
		}
	
}