package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.pieces.gamePiece;
import gameFiles.board.gameBoard;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class dragon extends gamePiece {
	// A Fairy Piece - The Dragon
	// movement of a Knight, but can only capture like a Pawn
	
	public dragon(int x, int y, boolean newSide) {
		// constructor for class dragon
		super();
		name = "Dragon";
		symbol = 'D';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}
	
	static public void spawnDragon(gameBoard board, int x, int y, boolean side) {
		// Spawn Knight on Board
		// @param x,y: coordinates to spawn Knight at
		// @param side: False for White, True for Black
		
		dragon newDragon = new dragon(x,y, side);
		board.setPiece(newDragon, x, y);
	}
	
	static public void moveDragon(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Dragon
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int dragon_x = piece.getLocation().getX();
		int dragon_y = piece.getLocation().getY();
		
		boolean isDragon = piece.getName() == "Dragon" && piece.getSymbol() == 'D';
		boolean inBounds = board.inBounds(x,y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = (x == dragon_x + 2 && y == dragon_y - 1) || (x == dragon_x + 2 && y == dragon_y + 1) || 
							(x == dragon_x - 2 && y == dragon_y - 1) || (x == dragon_x - 2 && y == dragon_y + 1) ||
							(x == dragon_x + 1 && y == dragon_y - 2) || (x == dragon_x + 1 && y == dragon_y + 2) ||
							(x == dragon_x - 1 && y == dragon_y - 2) || (x == dragon_x - 1 && y == dragon_y + 2);
		
		// Determines if piece at coordinates is hostile
		gamePiece occupyingPiece = board.getPiece(x, y);
		boolean spaceOccupied = occupyingPiece != null;
		
		if(spaceOccupied)
			validMove = false;
		
		boolean canCapture = spaceOccupied;
		
		if(piece.getSide() == false) { // white piece
			
			if(spaceOccupied && occupyingPiece.getSide() == false)
				canCapture = false;
		}
		
		else if(piece.getSide() == true) { // black piece
		
			if(spaceOccupied && occupyingPiece.getSide() == true)
				canCapture = false;
		}
		
		// Accounts for Special Dragon Capture Movement
		if(canCapture && Math.abs(dragon_y - y) == 1 && Math.abs(dragon_x - x) == 1)
			validMove = true;
		
		// The Desired Move is Possible
		if(isDragon && inBounds && validMove && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
		}
		
	}
}
