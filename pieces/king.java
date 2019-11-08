package gameFiles.pieces;

import gameFiles.misc.coord;
import gameFiles.board.gameBoard;
import gameFiles.pieces.gamePiece;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class king extends gamePiece{
	// Representation of a King piece
	
	public king(int x, int y, boolean newSide) {
		// constructor for class king
		super();
		name = "King";
		symbol = 'K';
		coord newLocation = new coord(x,y);
		location = newLocation;	
		side = newSide; 
	}
	
	static public void spawnKing(gameBoard board, int x, int y, boolean side) {
		// Spawn King on Board
		// @param x,y: coordinates to spawn King at
		// @param side: False for White, True for Black
		
		king newKing = new king(x,y, side);
		board.setPiece(newKing, x, y);
		
		board.setKing(newKing);
	}
	
	static public void moveKing(gameBoard board, gamePiece piece, int x, int y) {
		// Moves Piece with Movement Constraints of a King
		// @param piece: piece to move
		// @param x,y: coordinates move piece to
		
		int king_x = piece.getLocation().getX();
		int king_y = piece.getLocation().getY();
		
		boolean isKing = piece.getName() == "King" && piece.getSymbol() == 'K';
		boolean inBounds = x > -1 && x < 8 && y > -1 && y < 8;
		
		boolean playerTurn = board.getTurn() == piece.getSide();
		
		if(inBounds == false) // Out-Of-Bounds
			return;
		
		// Determines if the Space Would Put the King in Check
		boolean inCheck = inCheck(board, piece, x, y);
		
		// Determines if selected coordinates constitute a valid move
		boolean validMove = Math.abs(x-king_x) == 1 || Math.abs(y-king_y) == 1;
		
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
		if(isKing && inBounds && validMove && !inCheck && playerTurn) {
			
			if(spaceOccupied && canCapture)
				piece.capture(board, x, y);
		
			else if(!spaceOccupied)
				piece.move(board, x, y);
			
		}
		
	}
	
	static public boolean inCheck(gameBoard board, gamePiece piece, int x, int y) {
		// Determines if Space is in Check 
		// @param x,y: coordinates of space
		
		return diagonalCheck(board, piece, piece.getSide(), x,y) || inlineCheck(board, piece,piece.getSide(), x, y) || lCheck(board, piece.getSide(), x, y);
				
	}
	
	static boolean diagonalCheck(gameBoard board, gamePiece piece, boolean side, int x, int y) {
		// Helper function for inCheck()
		// Checks if there's a threat diagonally
		// @param x,y: coordinates of space
		// @param side: Owner of piece testing for a Check
		
		// Pull possible threat pieces (i.e. pieces that are diagonal to King)
		
		gamePiece nw = nwCheck(board, piece,side, x-1, y-1);
		gamePiece ne = neCheck(board, piece,side, x-1, y+1);
		gamePiece sw = swCheck(board, piece,side, x+1, y-1);
		gamePiece se = seCheck(board, piece,side, x+1, y+1);
		
		// Assess threat of each piece to King
		
		if(nw != null && nw.getSide() != side) {
		
			if(nw.getName() == "Pawn" || nw.getName() == "King") {
				if(Math.abs(x-nw.getLocation().getX()) == 1 && Math.abs(y-nw.getLocation().getY()) == 1)
					return true;
			}
			
			if(nw.getName() == "Assassin") {
				if(Math.abs(x-nw.getLocation().getX()) <= 3 && Math.abs(y-nw.getLocation().getY()) <= 3)
					return true;
			}
		
			if(nw != null && nw.getName() == "Queen" || nw.getName() == "Bishop") {
			return true;
			}	
		
		}
			
		if(ne != null && ne.getSide() != side) {
		
			if(ne.getName() == "Pawn" || ne.getName() == "King") {
				if(Math.abs(x-ne.getLocation().getX()) == 1 && Math.abs(y-ne.getLocation().getY()) == 1)
					return true;
			}
			
			if(ne.getName() == "Assassin") {
				if(Math.abs(x-ne.getLocation().getX()) <= 3 && Math.abs(y-ne.getLocation().getY()) <= 3)
					return true;
			}
			
			if(ne.getName() == "Queen" || ne.getName() == "Bishop") {
				return true;
			}
		
		}
		
		if(sw != null && sw.getSide() != side) {
		
			if(sw.getName() == "Pawn" || sw.getName() == "King") {
				if(Math.abs(x-sw.getLocation().getX()) == 1 && Math.abs(y-sw.getLocation().getY()) == 1)
					return true;
			}
			
			if(sw.getName() == "Assassin") {
				if(Math.abs(x-sw.getLocation().getX()) <= 3 && Math.abs(y-sw.getLocation().getY()) <= 3)
					return true;
			}
		
			if(sw.getName() == "Queen" || sw.getName() == "Bishop") {
				return true;
			}
		
		}
		
		if(se != null && se.getSide() != side) {
		
			if(se.getName() == "Pawn" || se.getName() == "King") {
				if(Math.abs(x-se.getLocation().getX()) == 1 && Math.abs(y-se.getLocation().getY()) == 1)
					return true;
			}
		
			if(se.getName() == "Assassin") {
				if(Math.abs(x-se.getLocation().getX()) <= 3 && Math.abs(y-se.getLocation().getY()) <= 3)
					return true;
			}
			
			if(se.getName() == "Queen" || se.getName() == "Bishop") {
				return true;
			}
		
		}
		
		return false;
	}
	
	static gamePiece nwCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for NW movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
		
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
				
		return nwCheck(board, piece,side, x - 1, y - 1);
	}
	
	static gamePiece neCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for NE movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
				
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
				
		return neCheck(board, piece,side, x - 1, y + 1);
	}
	
	static gamePiece swCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for SW movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
				
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
					
		return swCheck(board, piece, side, x + 1, y - 1);
	}
	
	static gamePiece seCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for SE movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
				
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
					
			return seCheck(board, piece, side, x + 1, y + 1);
	}
	
	static boolean inlineCheck(gameBoard board, gamePiece piece, boolean side, int x, int y) {
		// Helper function for inCheck()
		// Checks if there's a threat in a line			
		// @param x,y: coordinates of space
		// @param side: Owner of piece testing for a Check
		
		// Pull possible threat pieces (i.e. pieces that are in-line to King)
		gamePiece up = upCheck(board, piece, side, x-1,y);
		gamePiece down = downCheck(board, piece, side, x+1,y);
		gamePiece left = leftCheck(board, piece, side, x,y-1);
		gamePiece right = rightCheck(board, piece, side, x,y+1);
		
		// Assess threat of each piece to King
		
		if(up != null) {
		
			if(up != null && up.getName() == "King") {
				if(Math.abs(x-up.getLocation().getX()) == 1 && Math.abs(y-up.getLocation().getY()) == 1)
					return true;
			}
			
			if(up.getName() == "Assassin") {
				if(Math.abs(x-up.getLocation().getX()) <= 3 && Math.abs(y-up.getLocation().getY()) <= 3)
					return true;
			}
		
			if(up != null && up.getName() == "Queen" || up.getName() == "Rook") {
				return true;
			}
		}
		
		if(down != null) {
			if(down != null && down.getName() == "King") {
				if(Math.abs(x-down.getLocation().getX()) == 1 && Math.abs(y-down.getLocation().getY()) == 1)
					return true;
				else return false;
			}
			
			if(down.getName() == "Assassin") {
				if(Math.abs(x-down.getLocation().getX()) <= 3 && Math.abs(y-down.getLocation().getY()) <= 3)
					return true;
			}
		
			if(down != null && down.getName() == "Queen" || down.getName() == "Rook") {
				return true;
			}
		}
		
		if(left != null) {
		
			if(left != null && left.getName() == "King") {
				if(Math.abs(x-left.getLocation().getX()) == 1 && Math.abs(y-left.getLocation().getY()) == 1)
					return true;
			}
		
			if(left.getName() == "Assassin") {
				if(Math.abs(x-left.getLocation().getX()) <= 3 && Math.abs(y-left.getLocation().getY()) <= 3)
					return true;
			}
			
			if(left != null && left.getName() == "Queen" || left.getName() == "Rook") {
				return true;
			}
		
		}
		
		if(right != null) {
		
			if(right != null && right.getName() == "King") {
				if(Math.abs(x-right.getLocation().getX()) == 1 && Math.abs(y-right.getLocation().getY()) == 1)
					return true;
			}
		
			if(right.getName() == "Assassin") {
				if(Math.abs(x-right.getLocation().getX()) <= 3 && Math.abs(y-right.getLocation().getY()) <= 3)
					return true;
			}
			
			if(right != null && right.getName() == "Queen" || right.getName() == "Rook") {
				return true;
			}
			
		}
		
		return false;
	}
	
	static gamePiece upCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for upwards movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
		
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
		
		return upCheck(board, piece,side, x - 1, y);
	}
	
	static gamePiece downCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for upwards movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
			
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
				
		return downCheck(board, piece,side, x + 1, y);
	}
	
	static gamePiece leftCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for upwards movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
			
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
						
		return leftCheck(board, piece, side, x, y - 1);
	}
	
	static gamePiece rightCheck(gameBoard board, gamePiece piece, boolean side, int x, int y){
		// inCheck() helper function for rightwards movements
		// @param x, y: current space being checked
		// @param side: Owner of piece testing for a Check
		// @param piece: piece testing for a Check
			
		if(!board.inBounds(x, y))
			return null;
		
		if(board.getSpace(x, y).getOccupied() == true && board.getPiece(x, y) != piece) { // Base Case
			if(board.getPiece(x, y).getSide() == side)
				return null;
			return board.getPiece(x, y);
		}
						
		return rightCheck(board, piece, side, x, y + 1);
	}
	
	static boolean lCheck(gameBoard board, boolean side, int x, int y) {
		// Helper function for inCheck()
		// Checks if there's a knight threatening the space
		// @param x,y: coordinates of space
		// @param side: Owner of piece testing for a Check
		
		if(board.inBounds(x+2, y-1))
			if(board.getSpace(x+2, y-1).getOccupied() == true && board.getPiece(x+2, y-1).getName() == "Knight" && board.getPiece(x+2, y-1).getSide() != side)
				return true;
		
		if(board.inBounds(x+2, y+1))
			if(board.getSpace(x+2, y+1).getOccupied() == true && board.getPiece(x+2, y+1).getName() == "Knight" && board.getPiece(x+2, y+1).getSide() != side)
				return true;
		
		if(board.inBounds(x-2, y-1))
			if(board.getSpace(x-2, y-1).getOccupied() == true && board.getPiece(x-2, y-1).getName() == "Knight" && board.getPiece(x-2, y-1).getSide() != side)
				return true;
		
		if(board.inBounds(x-2, y+1))
			if(board.getSpace(x-2, y+1).getOccupied() == true && board.getPiece(x-2, y+1).getName() == "Knight" && board.getPiece(x-2, y+1).getSide() != side)
				return true;
		
		if(board.inBounds(x+1, y-2))
			if(board.getSpace(x+1, y-2).getOccupied() && board.getPiece(x+1, y-2).getName() == "Knight" && board.getPiece(x+1, y-2).getSide() != side)
				return true;
		
		if(board.inBounds(x+1, y+2))
			if(board.getSpace(x+1, y+2).getOccupied() && board.getPiece(x+1, y+2).getName() == "Knight" && board.getPiece(x+1, y+2).getSide() != side)
				return true;
		
		if(board.inBounds(x-1, y-2))
			if(board.getSpace(x-1, y-2).getOccupied() && board.getPiece(x-1, y-2).getName() == "Knight" && board.getPiece(x-1, y-2).getSide() != side)
				return true;
	
		if(board.inBounds(x-1, y+2))
			if(board.getSpace(x-1, y+2).getOccupied() && board.getPiece(x-1, y+2).getName() == "Knight" && board.getPiece(x-1, y+2).getSide() != side)
				return true;
	
		return false;
	}
	
	static public boolean isCheckmate(gameBoard board, gamePiece piece, boolean side, int x, int y) {
		// Determines if Checkmate has been reached 
		// @param x,y: coordinates of space
		// @param side: Owner of piece testing for a Check
		
		// these meaningless variables exist to break the logic into manageable chunks
		// each one is equivalent to multiple logical statements
		// if all are true then the logic will evaluate to true
		boolean a = false,b = false,c = false,d = false,e = false,f = false,g = false,h = false;
		
		// Check every possible coordinate to determine that they are either inaccessible or checked
		
		if(board.inBounds(x+1, y)) {
			if(inCheck(board,piece,x+1,y) || (board.getSpace(x+1, y).getOccupied() == true && board.getPiece(x+1,y).getSide() != side)) 
				a = true;
		}
		else a = true;
		
		if(board.inBounds(x+1, y+1)) {
			if(inCheck(board,piece,x+1,y+1) || (board.getSpace(x+1, y+1).getOccupied() == true && board.getPiece(x+1,y+1).getSide() == side)) 
				b = true;
		}
		else b = true;
		
		if(board.inBounds(x, y+1)) {
			if(inCheck(board,piece,x,y+1)  || (board.getSpace(x, y+1).getOccupied() == true && board.getPiece(x,y+1).getSide() == side))
				c = true;
		}
		else c = true;
		
		if(board.inBounds(x-1, y)) {
			if(inCheck(board,piece,x-1,y) || (board.getSpace(x-1, y).getOccupied() == true && board.getPiece(x-1,y).getSide() == side))
				d = true;
		}
		else d = true;
		
		if(board.inBounds(x-1, y-1)) {
			if(inCheck(board,piece,x-1,y-1)  || (board.getSpace(x-1, y-1).getOccupied() == true && board.getPiece(x-1,y-1).getSide() == side)) 
				e = true;
		}
		else e = true;
		
		if(board.inBounds(x, y-1)) {
			if(inCheck(board,piece,x,y-1) || (board.getSpace(x, y-1).getOccupied() == true && board.getPiece(x,y-1).getSide() == side)) 
				f = true;
		}
		else f = true;
		
		if(board.inBounds(x+1, y-1)) {
			if(inCheck(board,piece,x+1,y-1)  || (board.getSpace(x+1, y-1).getOccupied() == true && board.getPiece(x+1,y-1).getSide() == side)) 
				g = true;
		}
		else g = true;
		
		if(board.inBounds(x-1, y+1)) {
			if(inCheck(board,piece,x-1,y+1) || (board.getSpace(x-1, y+1).getOccupied() == true && board.getPiece(x-1,y+1).getSide() == side)) 
				h = true;
		}
		else h = true;
		    
		if(a && b && c && d && e && f && g && h)
			return true;
		
		return false;
		
	}
}