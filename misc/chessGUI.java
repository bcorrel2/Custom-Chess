package gameFiles.misc;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import gameFiles.board.gameBoard;
import gameFiles.pieces.gamePiece;
import gameFiles.pieces.pawn;

//======================================== //
//			   Chess - CS242               //
//			  @author bcorrel2             //
//======================================== //

public class chessGUI implements ActionListener {
	
	gameBoard board; // holds reference to gameBoard being used
	
	static JButton[][] boardSpaces = new JButton[8][8]; // holds references to each space's button on board
	
	Font chessFont; // holds special chess font for GUI
	
	//Menu Items
	JMenuItem newGame;
	JMenuItem fairyGame;
	JMenuItem undo;
	JMenuItem changeNames;
	JMenuItem displayScores;
	JMenuItem nextGame;
	JMenuItem forfeitWhite;
	JMenuItem forfeitBlack;
	
	//Message Box Display
	JLabel message;
	
	public chessGUI(gameBoard board, JFrame frame) {
		// initialize GUI
		
		// Board Panel
		JPanel boardBox = new JPanel(new GridLayout(8, 8));
		boardBox.setSize(600,600);
		boardBox.setMinimumSize(new Dimension(200,200));
		boardBox.setMaximumSize(new Dimension(1000,1000));
		boardBox.setPreferredSize(new Dimension(600,600));
		
		// Message Panel
		JPanel messageBox = new JPanel();
		message = new JLabel(controller.getPlayer(1) + "'s Turn");
		message.setFont(new Font("SansSerif Bold", Font.BOLD, 20));
		message.setForeground(Color.GRAY);
		messageBox.add(message);
		messageBox.setBackground(Color.WHITE);
		messageBox.setBorder(new LineBorder(Color.BLACK));
		messageBox.setMinimumSize(new Dimension(200,40));
		messageBox.setMaximumSize(new Dimension(1000,40));
		messageBox.setPreferredSize(new Dimension(600,40));
		
		// Combined Panel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
		gamePanel.setSize(600,640);
		gamePanel.add(boardBox);
		gamePanel.add(messageBox);
		
		frame.setContentPane(gamePanel);
		frame.setSize(600, 700);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		chessFont = initChessFont(); // initializes the special chess font
		
		initMenu(frame); // initializes menu bar
		initSpaces(boardBox); // initializes buttons for spaces
		initBoard(board); // displays piece configuration
		
		frame.setVisible(true);
	}
	
	public void initSpaceListener(ActionListener a){
		// Initialize Action Listener for Game Spaces
		
		for(int i = 0; i < 8; i++) {
			 for(int j = 0; j < 8; j++){
				 boardSpaces[i][j].addActionListener(a);
			 }
		}
		
	}
	
	public void initNewGame(ActionListener a){
		// Initialize Action Listener for New Game 
		
		newGame.addActionListener(a);
	
	}
	
	public void initUndo(ActionListener a){
		// Initialize Action Listener for Undo
		
		undo.addActionListener(a);
	
	}
	
	public void initChangeNames(ActionListener a){
		// Initialize Action Listener for Change Player Names
		
		changeNames.addActionListener(a);
	
	}
	
	public void initNewFairyGame(ActionListener a){
		// Initialize Action Listener for New Fairy Game
		
		fairyGame.addActionListener(a);
	
	}
	
	public void initDisplayScores(ActionListener a){
		// Initialize Action Listener for Display Scores
		
		displayScores.addActionListener(a);
	
	}
	
	public void initNextGame(ActionListener a){
		// Initialize Action Listener for Next Game
		
		nextGame.addActionListener(a);
	
	}
	
	public void initBlackForfeit(ActionListener a){
		// Initialize Action Listener for Forfeiter - Black
		
		forfeitBlack.addActionListener(a);
	
	}
	
	public void initWhiteForfeit(ActionListener a){
		// Initialize Action Listener for Forfeiter - White
		
		forfeitWhite.addActionListener(a);
	
	}
	
	void initSpaces(JPanel panel) {
		// Creates 8x8 grid and populates it with 64 buttons representing spaces
		
		for(int i = 0; i < 8; i++) {
			 for(int j = 0; j < 8; j++){
			
			 JButton button = new JButton("");
			 button.setOpaque(true);
			 button.setBorderPainted(true);
			 button.setBorder(new LineBorder(Color.BLACK));
			 
			 if(chessFont != null)
				 button.setFont(chessFont);
			 
			 else button.setFont(new Font("Symbol-Bold", Font.BOLD, 14));
			 
			 boardSpaces[i][j] = button;
			 
			 // Paints board spaces the appropriate color
			 
			 Color beige = new Color(245, 245, 220);
			 Color maroon = new Color(128, 0, 0);
			 
			 if(i % 2 == 0) {
			 
			 	if(j % 2 > 0) {
			 		button.setBackground(maroon); // maroon
			 	}
			 
			 	else {
			 		button.setBackground(beige); // beige
			 	}
			 }
			 
			 else {
				 
				 if(j % 2 == 0) {
					 	button.setBackground(maroon); // maroon
				 	}
				 
				 	else {
				 		button.setBackground(beige); // beige
				 	}
			 }
			 
		     panel.add(button);
		     
			 }
		}
	}
	

	Font initChessFont() {
		// Initialize Special Chess Font
		// https://docs.oracle.com/javase/tutorial/2d/text/fonts.html
		
		try {
				File file = new File("resources/chess.ttf");
				
				//Returned font is of pt size 1
				Font font = Font.createFont(Font.TRUETYPE_FONT, file);

				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));	
				
		     	//Derive and return a 12 pt version:
		     	//Need to use float otherwise
		     	//it would be interpreted as style
			
				return font.deriveFont(30f);
				
			} 
		
		catch (IOException|FontFormatException e) {
			System.out.println("-Font Error-");
		 	return null;
		}

	}
	
	void initBoard(gameBoard board) {
		// Function for refreshing an entire board
		// @param board: the gameBoard being utilized for the game;
		
		for(int i = 0; i < 8; i++) {
			 for(int j = 0; j < 8; j++) {
				
				 updateSpace(board, i,j);
				 
			 }
		 }
	}
	
	void updateBoard(gameBoard board, int x1, int y1, int x2, int y2) {
		// Function for Updating Board after a Move
		// @param board: Game Board being operated on
		// @param x1,y1: initial coordinates - firstClick
		// @param x2,y2: target coordinates - secondClick
		
		updateSpace(board, x1, y1);
		updateSpace(board, x2, y2);
		
	}
	
	void updateSpace(gameBoard board, int x, int y) {
		// Function for Updating a Space with new Contents
		// @param board: Game Board being operated on
		// @param x,y: coordinates to refresh
		
		gamePiece piece = board.getPiece(x, y);
		 
		 if(piece != null) {
			 
			 String displayString = Character.toString(piece.getSymbol()).toLowerCase();
			 
			 if(displayString.equals("a")) // Assassin Special Case
				 boardSpaces[x][y].setText("Q");
			 
			 else if(displayString.equals("d")) // Dragon Special Case
				 boardSpaces[x][y].setText("N");
			 
			 else boardSpaces[x][y].setText(displayString);
			
			 if(piece.getSide())
				 boardSpaces[x][y].setForeground(Color.BLACK);
			 
			 
			 else boardSpaces[x][y].setForeground(Color.GRAY);
			
		 }
		 
		 else {
			 boardSpaces[x][y].setText("");
			 boardSpaces[x][y].setForeground(Color.RED);
		 }
			
	}
	
	private void initMenu(JFrame frame) { 
		// Creates Menu for Applet
		// Modified from CS242 Course Website
		
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu game = new JMenu("Game");
        
        newGame = new JMenuItem("New Game");
        fairyGame = new JMenuItem("Fairy Game");
        undo = new JMenuItem("Undo");
        changeNames = new JMenuItem("Change Player Names");
        displayScores = new JMenuItem("Display Scores");
       
        nextGame = new JMenuItem("Next Game");
        forfeitWhite = new JMenuItem("White Player Forfeits");
        forfeitBlack = new JMenuItem("Black Player Forfeits");
   
        menu.add(newGame);
        menu.add(fairyGame);
        menu.add(undo);
        menu.add(changeNames);
        game.add(displayScores);
        game.add(nextGame);
        game.add(forfeitWhite);
        game.add(forfeitBlack);
        menubar.add(menu);
        menubar.add(game);
        frame.setJMenuBar(menubar);
        
    }

	public static JButton getButton(int x, int y) {
		// Get-Function for boardSpaces[][]
		// @param x,y: coordinates of button
		
		return boardSpaces[x][y];
	}
	
	public void displayScores(int one, int two) {
		// Function for setting Message Panel to Display Scores
		// @param one,two:  Scores for players 1 and 2 respectively 
		
		message.setText(controller.getPlayer(1) + ": " + String.valueOf(one) + " " + controller.getPlayer(2) + ": " + String.valueOf(two));
	}
	
	public void illegalMessage() {
		// Function for Displaying Message after Illegal Move
		
		message.setText("Illegal Move");
		message.setForeground(Color.RED);
	}
	
	public void resetMessageBox(boolean turn, boolean check, boolean checkmate, boolean winner) {
		// Function for updating Message Panel throughout the game
		// @param turn: Player whose turn it is - false for White, true for Black
		// @param check: whether either king is in check
		// @param checkmate: whether any king is in checkmate
		// @param winner: winner (if applicable). null if game is in progress
		
		if(checkmate) {
			if(!winner) {
				message.setText(controller.getPlayer(1) + " Wins!");
				message.setForeground(Color.GRAY);
			}
			
			else {
				message.setText(controller.getPlayer(2) + " Wins!");
				message.setForeground(Color.BLACK);
			}
		}
		
		else if(check) {
			
			if(!turn) {
				message.setText("Check!");
				message.setForeground(Color.GRAY);
			}
			
			else {
				message.setText("Check!");
				message.setForeground(Color.BLACK);
			}
		}
		
		else if(!turn) {
			message.setText(controller.getPlayer(1) + "'s Turn");
			message.setForeground(Color.GRAY);
		}
		
		else {
			message.setText(controller.getPlayer(2) + "'s Turn");
			message.setForeground(Color.BLACK);
		}
	}
	
	static public coord getButtonCoord(JButton button) {
		// Function for finding the exact coordinates of a button
		// @param button: button whose coordinates are being searched for
		
		for(int i = 0; i < 8; i++) {
			 for(int j = 0; j < 8; j++) {
				 
				 if(boardSpaces[i][j] == button)
					 return new coord(i,j);
				 
			 }
		}
		
		return null;
		
	}

	static void paintBordersBlack() {
		// Function for painting the borders of board spaces black
		
		for(int i = 0; i < 8; i++) {
			 for(int j = 0; j < 8; j++) {
				 boardSpaces[i][j].setBorder(new LineBorder(Color.BLACK));
			 }
		}
		
	}
	
	@Override // here to make the compiler happy..
	public void actionPerformed(ActionEvent e) {
		//do nothing...
	}
	
}