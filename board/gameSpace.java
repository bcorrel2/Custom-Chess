package gameFiles.board;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.pieces.gamePiece;

public class gameSpace {
	// Representation of a Space on a Chess Board and Related Functions
	
	boolean occupied; // Whether or not piece exists on space
	
	gamePiece piece; // The piece that exists on space
	
	public gameSpace() { 
		// constructor for class gameSpace()
		occupied = false;
		piece = null;
	}
	
	public gameSpace(gameSpace oldSpace) { 
		//  copy constructor for class gameSpace()
		occupied = oldSpace.occupied;
		piece = oldSpace.piece;
	}
	
	public boolean getOccupied() {
		// Get-Function for Occupied
		return occupied;
	}
	
	public void clearSpace() {
		// Clears Space of any Piece
		// @param x,y: coordinates to clear
		
		occupied = false;
		piece = null;
	}
	
}