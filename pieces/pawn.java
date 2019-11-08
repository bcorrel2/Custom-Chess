package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.pieces.gamePiece;
import gameFiles.board.gameBoard;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class pawn extends gamePiece{
	// Representation of a Pawn Piece and Related Functions
	
	boolean pawnMoved; // tracks if Pawn has moved for movement purposes
	
	public pawn(int x, int y, boolean newSide) {
		// constructor for class pawn
		super();
		name = "Pawn";
		symbol = 'P';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide;
		pawnMoved = false; 
		turnMove = false;
	}
	
	public boolean getPawnMoved(){
		// Get-Function for pawnMoved
		return pawnMoved;
	}
	
	public void setPawnMoved(){
		// Set-Function for pawnMoved
		pawnMoved = true;
	}
	
	static public void spawnPawn(gameBoard board, int x, int y, boolean side) {
		// Spawn Pawn on Board
		// @param x,y: coordinates to spawn Pawn at
		// @param side: False for White, True for Black
		
		pawn newPawn = new pawn(x,y, side);
		board.setPiece(newPawn, x, y);
	}
	
	static public void movePawn(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Pawn
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int pawn_x = piece.getLocation().getX();
		int pawn_y = piece.getLocation().getY();
		
		boolean isPawn = piece.getName() == "Pawn" && piece.getSymbol() == 'P';
		boolean inBounds = board.inBounds(x,y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
	
		// Determines if selected coordinates constitute a valid move
		pawn thisPawn = (pawn) piece;
		boolean validMove = (!(thisPawn.getPawnMoved()) && Math.abs(pawn_x - x) < 3 && pawn_y == y) ||
							Math.abs(pawn_x - x) < 2 && pawn_y == y;
		
		// Determines if selected coordinates are in front of pawn
		boolean inFront = false;
		
		if(piece.getSide() == false) { // white piece
			
			if(x < pawn_x)
				inFront = true;			
		}
		
		else if(piece.getSide() == true) { // black piece
			
			if(x > pawn_x)
				inFront = true;
		}
		
		// Determines if piece at coordinates is hostile
		gamePiece occupyingPiece = board.getPiece(x, y);
		boolean spaceOccupied = occupyingPiece != null;
		boolean canCapture = spaceOccupied;
		
		if(piece.getSide() == false) { // white piece
			
			if(spaceOccupied && occupyingPiece.getSide() == false)
				canCapture = false;
		}
		
		else if(piece.getSide() == true) { // black piece
		
			if(spaceOccupied && occupyingPiece.getSide() == true)
				canCapture = false;
		}
		
		// Accounts for Special Pawn Capture Movement
		if(canCapture && Math.abs(pawn_y - y) == 1)
			validMove = true;
		
		// The Desired Move is Possible
		if(isPawn && inBounds && validMove && inFront && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
			
			thisPawn.setPawnMoved();
		}
		
	}
	
}