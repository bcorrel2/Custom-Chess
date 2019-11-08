package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.pieces.gamePiece;

public class queen extends gamePiece{
	// Representation of a Queen Piece
	
	public queen(int x, int y, boolean newSide) {
		// constructor for class queen
		super();
		name = "Queen";
		symbol = 'Q';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}

	static public void spawnQueen(gameBoard board, int x, int y, boolean side) {
		// Spawn Queen on Board
		// @param x,y: coordinates to spawn Queen at
		// @param side: False for White, True for Black
		
		queen newQueen = new queen(x,y, side);
		board.setPiece(newQueen, x, y);
	}
	
	static public void moveQueen(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a Queen
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int queen_x = piece.getLocation().getX();
		int queen_y = piece.getLocation().getY();
		
		boolean isQueen = piece.getName() == "Queen" && piece.getSymbol() == 'Q';
		boolean inBounds = board.inBounds(x,y);
		// Determines if any obstacles block path
		boolean unobstPath = false; 
				
		if((x == queen_x || y == queen_y) &&  !(x == queen_x && y == queen_y))
			unobstPath = rook.pathingRook(board, piece, x, y, queen_x, queen_y);
		
		else if(bishop.isDiagonal(x, y, queen_x, queen_y))
			unobstPath = bishop.pathingBishop(board, piece, x, y, queen_x, queen_y);
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = ((x == queen_x || y == queen_y) &&  !(x == queen_x && y == queen_y)) ||
				bishop.isDiagonal(x, y, queen_x, queen_y);;
		
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
		if(isQueen && inBounds && validMove && unobstPath && playerTurn) {
					
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
				
			else if(!spaceOccupied)
				piece.move(board, x, y);
					
		}
		
	}
}