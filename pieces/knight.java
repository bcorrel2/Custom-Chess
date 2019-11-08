package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.pieces.gamePiece;

public class knight extends gamePiece{
	// Representation of a Knight Piece
	
	public knight(int x, int y, boolean newSide) {
		// constructor for class knight
		super();
		name = "Knight";
		symbol = 'N';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}
	
	static public void spawnKnight(gameBoard board, int x, int y, boolean side) {
		// Spawn Knight on Board
		// @param x,y: coordinates to spawn Knight at
		// @param side: False for White, True for Black
		
		knight newKnight = new knight(x,y, side);
		board.setPiece(newKnight, x, y);
	}
	
	static public void moveKnight(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Knight
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int knight_x = piece.getLocation().getX();
		int knight_y = piece.getLocation().getY();
		
		boolean isKnight = piece.getName() == "Knight" && piece.getSymbol() == 'N';
		boolean inBounds = board.inBounds(x,y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = (x == knight_x + 2 && y == knight_y - 1) || (x == knight_x + 2 && y == knight_y + 1) || 
							(x == knight_x - 2 && y == knight_y - 1) || (x == knight_x - 2 && y == knight_y + 1) ||
							(x == knight_x + 1 && y == knight_y - 2) || (x == knight_x + 1 && y == knight_y + 2) ||
							(x == knight_x - 1 && y == knight_y - 2) || (x == knight_x - 1 && y == knight_y + 2);
		
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
		
		// The move has been deemed possible 
		if(isKnight && inBounds && validMove && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
			
		}
		
	}
}