package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;
import gameFiles.pieces.gamePiece;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class bishop extends gamePiece{
	// Representation of a Bishop Piece
	
	public bishop(int x, int y, boolean newSide) {
		// constructor for class bishop
		super();
		name = "Bishop";
		symbol = 'B';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}
	
	static public void spawnBishop(gameBoard board, int x, int y, boolean side) {
		// Spawn Bishop on Board
		// @param x,y: coordinates to spawn Bishop at
		// @param side: False for White, True for Black
		
		bishop newBishop = new bishop(x,y, side);
		board.setPiece(newBishop, x, y);
	}
	
	static public void moveBishop(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Bishop
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int bishop_x = piece.getLocation().getX();
		int bishop_y = piece.getLocation().getY();
		
		boolean isBishop = piece.getName() == "Bishop" && piece.getSymbol() == 'B';
		boolean inBounds = board.inBounds(x,y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(!inBounds) // Out-Of-Bounds
			return;
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = isDiagonal(x, y, bishop_x, bishop_y);
		
		// Determines is any obstacles obstruct path
		boolean unobstPath = pathingBishop(board, piece, x, y, bishop_x, bishop_y);
		
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
		if(isBishop && inBounds && validMove && unobstPath && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
			
		}
		
	}
	
	static boolean isDiagonal(int target_x, int target_y, int curr_x, int curr_y) {
		// Function for determining if destination is in a diagonal location
		// Utilizes 4 Recursive Helper Functions with similar inputs
		// @param target_x, target_y: space being moved to
		// @param curr_x, curr_y: piece's current location
		
		return isNW(target_x, target_y, curr_x, curr_y) ||
			   isNE(target_x, target_y, curr_x, curr_y) ||
			   isSW(target_x, target_y, curr_x, curr_y) ||
			   isSE(target_x, target_y, curr_x, curr_y);
	}
	
	static boolean isNW(int target_x, int target_y, int curr_x, int curr_y){
		// isDiagonal() helper function for NW movements
		// @param target_x, target_y: space being moved to
		// @param curr_x, curr_y: current space being checked
		
		if(curr_x == target_x && curr_y == target_y)
			return true;
		
		if(curr_x < 0 || curr_x > 7)
			return false;
		
		return isNW(target_x, target_y, curr_x - 1, curr_y - 1);
		
	}
	
	static boolean isNE(int target_x, int target_y, int curr_x, int curr_y){
		// isDiagonal() helper function for NE movements
		// @param target_x, target_y: space being moved to
		// @param curr_x, curr_y: current space being checked
		
		if(curr_x == target_x && curr_y == target_y)
			return true;
		
		if(curr_x < 0 || curr_x > 7)
			return false;
		
		return isNE(target_x, target_y, curr_x - 1, curr_y + 1);
	}
	
	static boolean isSW(int target_x, int target_y, int curr_x, int curr_y){
		// isDiagonal() helper function for SW movements
		// @param target_x, target_y: space being moved to
		// @param curr_x, curr_y: current space being checked
		
		if(curr_x == target_x && curr_y == target_y)
			return true;
		
		if(curr_x < 0 || curr_x > 7)
			return false;
		
		return isSW(target_x, target_y, curr_x + 1, curr_y - 1);
	}
	
	static boolean isSE(int target_x, int target_y, int curr_x, int curr_y){
		// isDiagonal() helper function for SE movements
		// @param target_x, target_y: space being moved to
		// @param curr_x, curr_y: current space being checked
		
		if(curr_x == target_x && curr_y == target_y)
			return true;
		
		if(curr_x < 0 || curr_x > 7)
			return false;
		
		return isSE(target_x, target_y, curr_x + 1, curr_y + 1);
	}
	
	static boolean pathingBishop(gameBoard board, gamePiece piece, int target_x, int target_y, int curr_x, int curr_y) {
		// Recursive Function to check for obstructing pieces
		// @param piece: piece being moved
		// @param target_x, target_y: the space being moved to
		// @param curr_x, curr_y: the current space being checked
		
		if(curr_x == target_x && curr_y == target_y) // Base Case
			return true;
		
		if(curr_x > 7 || curr_x < 0 || curr_y > 7 || curr_y < 0) // Out-Of-Bounds
			return false;
		
		// Determines Starting Position so as not to count piece against itself
		boolean isStartingPos = piece.getLocation().getX() == curr_x && piece.getLocation().getY() == curr_y;		
		
		if(!isStartingPos && board.getSpace(curr_x, curr_y).getOccupied() == true)
			return false; // Obstructing Piece Identified
		
		else if(target_x > curr_x && target_y > curr_y) // Recurse for SE Movements
			return pathingBishop(board, piece, target_x, target_y, curr_x + 1, curr_y + 1);
		
		else if(target_x > curr_x && target_y < curr_y) // Recurse for SW Movements
			return pathingBishop(board, piece, target_x, target_y, curr_x + 1, curr_y - 1);
		
		else if(target_x < curr_x && target_y > curr_y) // Recurse for NE Movements
			return pathingBishop(board, piece, target_x, target_y, curr_x - 1, curr_y + 1);
		
		else if(target_x < curr_x && target_y < curr_y) // Recurse for NW Movements
			return pathingBishop(board, piece, target_x, target_y, curr_x - 1, curr_y - 1);
		
		else return false;
	}
}