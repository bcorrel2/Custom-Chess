package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;

//======================================== //
//			   Chess - CS242               //
//			  @author bcorrel2             //
//======================================== //

public class assassin extends gamePiece{
	// A Fairy Piece - The Assassin
	// Can move any direction 3 or less spaces, can pass through pieces
	
		public assassin(int x, int y, boolean newSide) {
			// constructor for class assassin
			super();
			name = "Assassin";
			symbol = 'A';
			coord newLocation = new coord(x,y);
			location = newLocation;	
			side = newSide; 
		}

		static public void spawnAssassin(gameBoard board, int x, int y, boolean side) {
			// Spawn Assassin on Board
			// @param x,y: coordinates to spawn Queen at
			// @param side: False for White, True for Black
			
			assassin newAssassin = new assassin(x,y, side);
			board.setPiece(newAssassin, x, y);
		}
		
		static public void moveAssassin(gameBoard board, gamePiece piece, int x, int y) {
			// Moves Piece with Movement Constraints of a Assassin
			// @param piece: piece to move
			// @param x,y: coordinates move piece to
			
			int assassin_x = piece.getLocation().getX();
			int assassin_y = piece.getLocation().getY();
			
			boolean isAssassin = piece.getName() == "Assassin" && piece.getSymbol() == 'A';
			boolean inBounds = board.inBounds(x,y);
			
			boolean playerTurn = board.getTurn() == piece.getSide();
			
			if(inBounds == false) // Out-Of-Bounds
				return;
			
			// Determines if selected coordinates constitute a valid move
			boolean validMove = ((x == assassin_x || y == assassin_y) &&  !(x == assassin_x && y == assassin_y)) ||
					bishop.isDiagonal(x, y, assassin_x, assassin_y) && (Math.abs(assassin_x - x) <= 3 && Math.abs(assassin_y - y) <= 3);;
			
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
			if(isAssassin && inBounds && validMove && playerTurn) {
						
				if(spaceOccupied && canCapture)
					piece.capture(board, x, y);
					
				else if(!spaceOccupied)
					piece.move(board, x, y);
						
			}
			
		}
}
