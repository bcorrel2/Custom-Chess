package gameFiles.board;

import gameFiles.board.gameSpace;
import gameFiles.pieces.gamePiece;
import gameFiles.pieces.pawn;
import gameFiles.pieces.knight;
import gameFiles.pieces.rook;
import gameFiles.pieces.assassin;
import gameFiles.pieces.bishop;
import gameFiles.pieces.dragon;
import gameFiles.pieces.queen;
import gameFiles.pieces.king;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class gameBoard { 
	// Representation of a Chess Board and Related Functions 

	gameSpace[][] board;
	
	// Undo Game States
	public gameBoard undoBoard;
	public gamePiece lastPieceMoved;
	
	// References to the Kings
	public king blackKing;
	public king whiteKing;
	
	private boolean turn; // Tracks whose turn it is
	private boolean check; // Determines if king is in check
	private boolean checkmate; // Determines if game has ended
	private boolean winner; // Determines winner
	
	public gameBoard() { 
		// constructor for class gameBoard
		
		board = new gameSpace[8][8];
		
		// assign each cell of array with new gameSpace
		for(int i=0; i<8; i++) { 
			for(int j=0; j<8; j++) {
				board[i][j] = new gameSpace();
			}
		}
		
		turn = false; // White goes first
		check = false;
		checkmate = false;
		
	}
	
	public gameBoard(gameBoard oldBoard) { 
		// copy constructor for class gameBoard
		// @param oldBoard: board to be copied
		
		turn = oldBoard.turn;
		check = oldBoard.check;
		checkmate = oldBoard.checkmate;
		
		lastPieceMoved = oldBoard.lastPieceMoved;
		
		board = new gameSpace[8][8];
		
		// assign each cell of array with new gameSpace
		for(int i=0; i<8; i++) { 
			for(int j=0; j<8; j++) {
				board[i][j] = new gameSpace(oldBoard.board[i][j]);
			}
		}
		
		blackKing = oldBoard.blackKing;
		whiteKing = oldBoard.whiteKing;

	}
	
	public String getPieceName(int x, int y) {
		// Get-Function for gameBoard.gameSpace.piece.name
		// @param x,y: coordinates of piece
		// @return String containing piece name 
		
		if(!inBounds(x,y)) // Out-Of-Bounds
			return "";
		
		if(board[x][y].piece == null)
			return "null";
		
		else return board[x][y].piece.getName();
	}
	
	public gamePiece getPiece(int x, int y) {
		// Get-Function for gameBoard.gameSpace.piece
		// @param x,y: coordinates of piece
		// @return gamePiece reference to piece
		
		return board[x][y].piece;
	}
	
	public void setPiece(gamePiece piece, int x, int y) {
		// Set-Function for gameBoard.gameSpace.piece
		// @param x,y: coordinates of piece
		// @return gamePiece reference to piece
		
		board[x][y].piece = piece;
		board[x][y].occupied = true;
		
	}
	
	public gameSpace getSpace(int x, int y) {
		// Get-Function for gameBoard.gameSpace
		// @param x,y: coordinates of space
		// @return gameSpace reference to space
		
		return board[x][y];
	}
	
	public boolean getTurn() {
		// Get-Function for gameBoard.turn
		return turn;
	}
	
	public void turnChange() {
		// Changes the turn
		turn = !turn;
		
	}
	
	public boolean getCheck() {
		// Get-Function for gameBoard.check
		return check;
	}
	
	public void setCheck(boolean inCheck ) {
		// Set-Function for gameBoard.check
		check = inCheck;
	}
	
	public boolean getCheckmate() {
		// Get-Function for gameBoard.checkmate
		return checkmate;
	}
	
	public void isinCheckmate(boolean checkmatedPlayer) {
		// Called when Checkmate is reached
		checkmate = true;
		winner = !checkmatedPlayer;
	}
	
	public boolean getWinner() {
		// Get-Function for gameBoard.winner
		return winner;
	}
	
	public void setKing(king newKing) {
		// Set-Function for blackKing and whiteKing
		
		if(newKing.getSide())
			blackKing = newKing;
		else whiteKing = newKing;
			
	}
	
	public void undo(gameBoard board) {
		// Undos last piece movement
		// @param board: board being acted upon
		
		if(lastPieceMoved != null) {
			lastPieceMoved.setLocation(lastPieceMoved.old_location);
			lastPieceMoved.move(board, lastPieceMoved.getLocation().getX(), lastPieceMoved.getLocation().getY());
			board.turnChange(); // account for the extra turn change
		}
			
		else System.out.println("null");
	}
	
	public void printGameState() {
		// Debugging Function for Viewing Game State in the Console
		// Visits Every gameSpace and Prints Symbol of Piece Residing Within
		
		for(int i=0; i<8; i++) {
			System.out.println("");
			for(int j=0; j<8; j++) {
				if(board[i][j].piece == null)
					System.out.print('0');
				else System.out.print(board[i][j].piece.getSymbol());
				
			}
		}
		
		System.out.println("");
		
	}
	
	public static gameBoard setBoard() {
		// Sets Board in Standard Chess Arrangement
		
		gameBoard newBoard = new gameBoard();
		
		pawn.spawnPawn(newBoard, 6,0,false);
		pawn.spawnPawn(newBoard, 6,1,false);
		pawn.spawnPawn(newBoard, 6,2,false);
		pawn.spawnPawn(newBoard, 6,3,false);
		pawn.spawnPawn(newBoard, 6,4,false);
		pawn.spawnPawn(newBoard, 6,5,false);
		pawn.spawnPawn(newBoard, 6,6,false);
		pawn.spawnPawn(newBoard, 6,7,false);
		
		rook.spawnRook(newBoard, 7,0,false);
		knight.spawnKnight(newBoard, 7,1,false);
		bishop.spawnBishop(newBoard, 7,2,false);
		queen.spawnQueen(newBoard, 7,3,false);
		king.spawnKing(newBoard, 7,4,false);
		bishop.spawnBishop(newBoard, 7,5,false);
		knight.spawnKnight(newBoard, 7,6,false);
		rook.spawnRook(newBoard, 7,7,false);
		
		pawn.spawnPawn(newBoard, 1,0,true);
		pawn.spawnPawn(newBoard, 1,1,true);
		pawn.spawnPawn(newBoard, 1,2,true);
		pawn.spawnPawn(newBoard, 1,3,true);
		pawn.spawnPawn(newBoard, 1,4,true);
		pawn.spawnPawn(newBoard, 1,5,true);
		pawn.spawnPawn(newBoard, 1,6,true);
		pawn.spawnPawn(newBoard, 1,7,true);
		
		rook.spawnRook(newBoard, 0,0,true);
		knight.spawnKnight(newBoard, 0,1,true);
		bishop.spawnBishop(newBoard, 0,2,true);
		queen.spawnQueen(newBoard, 0,3,true);
		king.spawnKing(newBoard, 0,4,true);
		bishop.spawnBishop(newBoard, 0,5,true);
		knight.spawnKnight(newBoard, 0,6,true);
		rook.spawnRook(newBoard, 0,7,true);
		
		return newBoard;
	}
	
	public static gameBoard setFairyBoard() {
		// Sets Board in Fairy Chess Arrangement
		
		gameBoard newBoard = new gameBoard();
		
		pawn.spawnPawn(newBoard, 6,0,false);
		pawn.spawnPawn(newBoard, 6,1,false);
		pawn.spawnPawn(newBoard, 6,2,false);
		pawn.spawnPawn(newBoard, 6,3,false);
		pawn.spawnPawn(newBoard, 6,4,false);
		pawn.spawnPawn(newBoard, 6,5,false);
		pawn.spawnPawn(newBoard, 6,6,false);
		pawn.spawnPawn(newBoard, 6,7,false);
		
		rook.spawnRook(newBoard, 7,0,false);
		dragon.spawnDragon(newBoard, 7,1,false);
		bishop.spawnBishop(newBoard, 7,2,false);
		assassin.spawnAssassin(newBoard, 7,3,false);
		king.spawnKing(newBoard, 7,4,false);
		bishop.spawnBishop(newBoard, 7,5,false);
		dragon.spawnDragon(newBoard, 7,6,false);
		rook.spawnRook(newBoard, 7,7,false);
		
		pawn.spawnPawn(newBoard, 1,0,true);
		pawn.spawnPawn(newBoard, 1,1,true);
		pawn.spawnPawn(newBoard, 1,2,true);
		pawn.spawnPawn(newBoard, 1,3,true);
		pawn.spawnPawn(newBoard, 1,4,true);
		pawn.spawnPawn(newBoard, 1,5,true);
		pawn.spawnPawn(newBoard, 1,6,true);
		pawn.spawnPawn(newBoard, 1,7,true);
		
		rook.spawnRook(newBoard, 0,0,true);
		dragon.spawnDragon(newBoard, 0,1,true);
		bishop.spawnBishop(newBoard, 0,2,true);
		assassin.spawnAssassin(newBoard, 0,3,true);
		king.spawnKing(newBoard, 0,4,true);
		bishop.spawnBishop(newBoard, 0,5,true);
		dragon.spawnDragon(newBoard, 0,6,true);
		rook.spawnRook(newBoard, 0,7,true);
		
		return newBoard;
	}
	
	public boolean inBounds(int x, int y){
		// Checks if coordinates are in-bounds
		// @param x,y: coordinates to be checked
		
		return x > -1 && x < 8 && y > -1 && y < 8;
		
	}
	
	public void moveCommand(int x1, int y1, int x2, int y2) {
		// Function for moving pieces of unknown type
		// @param x1,y1: location of piece to be moved
		// @param x2,y2: target destination
		
		gamePiece piece = getPiece(x1,y1);
		
		if(piece == null)
			return;
		
		// Set for Undo Function
		lastPieceMoved = piece;
		
		check = false;
		
		String pieceName = piece.getName();
		
		if(pieceName == "Pawn")
			pawn.movePawn(this, piece, x2, y2);
		
		else if(pieceName == "Knight")
			knight.moveKnight(this, piece, x2, y2);
		
		else if(pieceName == "Rook")
			rook.moveRook(this, piece, x2, y2);
		
		else if(pieceName == "Bishop")
			bishop.moveBishop(this, piece, x2, y2);
		
		else if(pieceName == "Queen")
			queen.moveQueen(this, piece, x2, y2);
		
		else if(pieceName == "King")
			king.moveKing(this, piece, x2, y2);
		
		else if(pieceName == "Assassin")
			assassin.moveAssassin(this, piece, x2, y2);
		
		else if(pieceName == "Dragon")
			dragon.moveDragon(this, piece, x2, y2);
		
		// Check for Check and Checkmate
		
		check = king.inCheck(this, (gamePiece) blackKing, blackKing.getLocation().getX(), blackKing.getLocation().getY()) || king.inCheck(this, (gamePiece) whiteKing, whiteKing.getLocation().getX(), whiteKing.getLocation().getY());
	
		if(check && king.isCheckmate(this, blackKing, true, blackKing.getLocation().getX(), blackKing.getLocation().getY()))
				isinCheckmate(true);
		
		if(check && king.isCheckmate(this, whiteKing, false, whiteKing.getLocation().getX(), whiteKing.getLocation().getY()))
				isinCheckmate(false);
		
	}
	
	
}
