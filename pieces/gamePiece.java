package gameFiles.pieces;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;

public class gamePiece{
	// Representation of a gamePiece and Related Functions
	
	String name = "gamePiece"; 
	char symbol; // Symbol to be displayed for debugging
	coord location; // Piece's current location
	boolean side; // 0 = white | 1 = black 
	boolean turnMove; // Whether piece has moved on current turn
	
	// For Undo Function
	public coord old_location;
	
	public gamePiece() {
		// constructor for class gamePiece
		// empty because this is an abstract class
	}

	public String getName() {
		// Get-Function for name
		return name;
	}
	
	public char getSymbol() {
		// Get-Function for symbol
		return symbol;
	}
	
	public coord getLocation(){
		// Get-Function for location
		return location;
	}
	
	public void setLocation(int x, int y) {
		// Set-Function for location
		// @param x,y: new coordinates
		location = new coord(x,y);
	}
	
	public void setLocation(coord c) {
		// Set-Function for location
		// @param x,y: new coordinates
		location = c;
	}
	
	public boolean getSide(){
		// Get-Function for side
		return side;
	}
	
	public void move(gameBoard board, int x, int y) {
		// Moves Piece to any Destination
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int piece_x = location.getX();
		int piece_y = location.getY();
		
		board.getSpace(piece_x, piece_y).clearSpace();
		board.setPiece(this, x, y);
		setLocation(x,y);
		
		board.turnChange();
		old_location = new coord(piece_x, piece_y);
	}
	
	void capture(gameBoard board, int x, int y) {
		// Moves Piece to any Destination and Captures Existing Piece
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int piece_x = location.getX();
		int piece_y = location.getY();
		
		board.getSpace(piece_x, piece_y).clearSpace();
		board.setPiece(this, x, y);
		setLocation(x,y);
		
		board.turnChange();
		old_location = new coord(piece_x, piece_y);
		
		//TODO: Increment Score
		
	}
	
}