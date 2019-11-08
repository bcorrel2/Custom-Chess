package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.pieces.gamePiece;

public class rook extends gamePiece{
	// Representation of a Rook Piece 
	
	public rook(int x, int y, boolean newSide) {
		//constructor for class rook
		super();
		name = "Rook";
		symbol = 'R';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}
	
	static public void spawnRook(gameBoard board, int x, int y, boolean side) {
		// Spawn Rook on Board
		// @param x,y: coordinates to spawn Rook at
		// @param side: False for White, True for Black
		
		rook newRook = new rook(x,y, side);
		board.setPiece(newRook, x, y);
	}
	
	static public void moveRook(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Rook
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int rook_x = piece.getLocation().getX();
		int rook_y = piece.getLocation().getY();
		
		boolean isRook = piece.getName() == "Rook" && piece.getSymbol() == 'R';
		boolean inBounds = board.inBounds(x,y);
		
		// Determines if any obstacles block path
		boolean unobstPath = pathingRook(board, piece, x, y, rook_x, rook_y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = (x == rook_x || y == rook_y) &&  !(x == rook_x && y == rook_y);
		
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
		if(isRook && inBounds && validMove && unobstPath && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
			
		}
		
	}
	
	static boolean pathingRook(gameBoard board, gamePiece piece, int target_x, int target_y, int curr_x, int curr_y) {
		// Recursive Function to check for obstructing pieces
		// @param piece: piece being moved
		// @param target_x, target_y: the space being moved to
		// @param curr_x, curr_y: the current space being checked
		
		if(curr_x == target_x && curr_y == target_y) // Base Case
			return true;
		
		if(!board.inBounds(curr_x, curr_y)) // Out-Of-Bounds
			return false;
		
		// Determines Starting Position so as not to count piece against itself
		boolean isStartingPos = piece.getLocation().getX() == curr_x && piece.getLocation().getY() == curr_y;		
		
		if(!isStartingPos && board.getSpace(curr_x, curr_y).getOccupied() == true)
			return false; // Obstructing Piece Identified
		
		else if(target_x > curr_x) // Recurse for Downward Movements
			return pathingRook(board, piece, target_x, target_y, curr_x + 1, curr_y);
		
		else if(target_x < curr_x) // Recurse for Upwards Movements
			return pathingRook(board, piece, target_x, target_y, curr_x - 1, curr_y);
		
		else if(target_y > curr_y) // Recurse for Movements Right
			return pathingRook(board, piece, target_x, target_y, curr_x, curr_y + 1);
		
		else if(target_y < curr_y) // Recurse for Movements Left
			return pathingRook(board, piece, target_x, target_y, curr_x, curr_y - 1);
		
		else return false;
	}
}