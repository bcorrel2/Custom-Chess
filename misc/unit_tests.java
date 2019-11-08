package gameFiles.misc;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

import gameFiles.board.gameBoard;
import gameFiles.pieces.pawn;
import gameFiles.pieces.knight;
import gameFiles.pieces.rook;
import gameFiles.pieces.bishop;
import gameFiles.pieces.queen;
import gameFiles.pieces.king;
import gameFiles.pieces.assassin;
import gameFiles.pieces.dragon;
import static org.junit.Assert.*;
import org.junit.Test;

public class unit_tests {
	
	@Test public void spawnPiecesTest() {
		// Test for Spawning Each Piece Variant
		
		gameBoard testing = new gameBoard();
		pawn.spawnPawn(testing,0,5,false);
		knight.spawnKnight(testing, 1,5,false);
		rook.spawnRook(testing, 2,5,false);
		bishop.spawnBishop(testing, 3,5,false);
		queen.spawnQueen(testing, 4,5,false);
		king.spawnKing(testing, 5,5,false);
		
		System.out.println("");
		System.out.println("spawnPiecesTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 5), "Pawn");
		assertEquals(testing.getPieceName(1, 5), "Knight");
		assertEquals(testing.getPieceName(2, 5), "Rook");
		assertEquals(testing.getPieceName(3, 5), "Bishop");
		assertEquals(testing.getPieceName(4, 5), "Queen");
		assertEquals(testing.getPieceName(5, 5), "King");
		
	}
	
	@Test public void copyBoardTest() {
		// Test for Spawning Each Piece Variant
		
		gameBoard testing = gameBoard.setBoard();
		gameBoard copy = new gameBoard(testing);
		
		
		System.out.println("");
		System.out.println("copyBoardTest()");
		testing.printGameState();
		
		assertEquals(copy.getPieceName(0, 0), "Rook");
		assertEquals(copy.getPieceName(1, 5), "Pawn");
		assertEquals(copy.getPieceName(0, 1), "Knight");
		assertEquals(copy.getPieceName(0, 2), "Bishop");
		assertEquals(copy.getPieceName(0, 3), "Queen");
		assertEquals(copy.getPieceName(0, 4), "King");
		
	}
	
	@Test public void setBoardTest() {
		// Test For Setting Board
		
		gameBoard testing = gameBoard.setBoard();
		
		System.out.println("");
		System.out.println("setBoardTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(1, 0), "Pawn");
		assertEquals(testing.getPieceName(1, 1), "Pawn");
		assertEquals(testing.getPieceName(1, 2), "Pawn");
		assertEquals(testing.getPieceName(1, 3), "Pawn");
		assertEquals(testing.getPieceName(1, 4), "Pawn");
		assertEquals(testing.getPieceName(1, 5), "Pawn");
		assertEquals(testing.getPieceName(1, 6), "Pawn");
		assertEquals(testing.getPieceName(1, 7), "Pawn");
		assertEquals(testing.getPieceName(6, 0), "Pawn");
		assertEquals(testing.getPieceName(6, 1), "Pawn");
		assertEquals(testing.getPieceName(6, 2), "Pawn");
		assertEquals(testing.getPieceName(6, 3), "Pawn");
		assertEquals(testing.getPieceName(6, 4), "Pawn");
		assertEquals(testing.getPieceName(6, 5), "Pawn");
		assertEquals(testing.getPieceName(6, 6), "Pawn");
		assertEquals(testing.getPieceName(6, 7), "Pawn");
		
		assertEquals(testing.getPieceName(0, 0), "Rook");
		assertEquals(testing.getPieceName(7, 0), "Rook");
		assertEquals(testing.getPieceName(7, 7), "Rook");
		assertEquals(testing.getPieceName(0, 7), "Rook");
		
		assertEquals(testing.getPieceName(0, 1), "Knight");
		assertEquals(testing.getPieceName(0, 6), "Knight");
		assertEquals(testing.getPieceName(7, 1), "Knight");
		assertEquals(testing.getPieceName(7, 6), "Knight");
		
		assertEquals(testing.getPieceName(0, 2), "Bishop");
		assertEquals(testing.getPieceName(0, 5), "Bishop");
		assertEquals(testing.getPieceName(7, 2), "Bishop");
		assertEquals(testing.getPieceName(7, 5), "Bishop");
		
		assertEquals(testing.getPieceName(0, 3), "Queen");
		assertEquals(testing.getPieceName(7, 3), "Queen");
		
		assertEquals(testing.getPieceName(0, 4), "King");
		assertEquals(testing.getPieceName(7, 4), "King");
		
	}
	
	@Test public void setFairyBoardTest() {
		// Test For Setting Board
		
		gameBoard testing = gameBoard.setFairyBoard();
		
		System.out.println("");
		System.out.println("setFairyBoardTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(1, 0), "Pawn");
		assertEquals(testing.getPieceName(1, 1), "Pawn");
		assertEquals(testing.getPieceName(1, 2), "Pawn");
		assertEquals(testing.getPieceName(1, 3), "Pawn");
		assertEquals(testing.getPieceName(1, 4), "Pawn");
		assertEquals(testing.getPieceName(1, 5), "Pawn");
		assertEquals(testing.getPieceName(1, 6), "Pawn");
		assertEquals(testing.getPieceName(1, 7), "Pawn");
		assertEquals(testing.getPieceName(6, 0), "Pawn");
		assertEquals(testing.getPieceName(6, 1), "Pawn");
		assertEquals(testing.getPieceName(6, 2), "Pawn");
		assertEquals(testing.getPieceName(6, 3), "Pawn");
		assertEquals(testing.getPieceName(6, 4), "Pawn");
		assertEquals(testing.getPieceName(6, 5), "Pawn");
		assertEquals(testing.getPieceName(6, 6), "Pawn");
		assertEquals(testing.getPieceName(6, 7), "Pawn");
		
		assertEquals(testing.getPieceName(0, 0), "Rook");
		assertEquals(testing.getPieceName(7, 0), "Rook");
		assertEquals(testing.getPieceName(7, 7), "Rook");
		assertEquals(testing.getPieceName(0, 7), "Rook");
		
		assertEquals(testing.getPieceName(0, 1), "Dragon");
		assertEquals(testing.getPieceName(0, 6), "Dragon");
		assertEquals(testing.getPieceName(7, 1), "Dragon");
		assertEquals(testing.getPieceName(7, 6), "Dragon");
		
		assertEquals(testing.getPieceName(0, 2), "Bishop");
		assertEquals(testing.getPieceName(0, 5), "Bishop");
		assertEquals(testing.getPieceName(7, 2), "Bishop");
		assertEquals(testing.getPieceName(7, 5), "Bishop");
		
		assertEquals(testing.getPieceName(0, 3), "Assassin");
		assertEquals(testing.getPieceName(7, 3), "Assassin");
		
		assertEquals(testing.getPieceName(0, 4), "King");
		assertEquals(testing.getPieceName(7, 4), "King");
		
	}
	
	@Test public void pawnFunctionTest() {
		// Test For Moving and Capturing with Pawns
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 1,0,true);
		pawn.movePawn(testing, testing.getPiece(1,0), 3, 0);
		pawn.spawnPawn(testing, 6,3,false);
		pawn.movePawn(testing, testing.getPiece(6,3), 4, 3);
		pawn.spawnPawn(testing, 1,1,true);
		pawn.movePawn(testing, testing.getPiece(1,1), 2, 1);
		pawn.spawnPawn(testing, 6,4,false);
		pawn.movePawn(testing, testing.getPiece(6,4), 5, 4);
		pawn.spawnPawn(testing, 1,2,true);
		pawn.movePawn(testing, testing.getPiece(1,2), 1, 2);
		pawn.spawnPawn(testing, 6,5,false);
		pawn.movePawn(testing, testing.getPiece(6,5), 6, 5);
		
		pawn.spawnPawn(testing, 1,6,true);
		pawn.movePawn(testing, testing.getPiece(1,6), 3, 6);
		pawn.spawnPawn(testing, 5,6,false);
		pawn.movePawn(testing, testing.getPiece(5,6), 5, 6);
		
		

		
		// Capture Test
		testing.turnChange();
		knight.spawnKnight(testing, 4,7, true);
		pawn.movePawn(testing, testing.getPiece(5,6), 4, 7);
		
		System.out.println("");
		System.out.println("movePawnTest()");
		testing.printGameState();
		
		//assertEquals(testing.getPieceName(3, 0), "Pawn");
		assertEquals(testing.getPieceName(2, 1), "Pawn");
		//assertEquals(testing.getPieceName(1, 2), "Pawn");
		//assertEquals(testing.getPieceName(4, 3), "Pawn");
		//assertEquals(testing.getPieceName(5, 4), "Pawn");
		//assertEquals(testing.getPieceName(6, 5), "Pawn");
		//assertEquals(testing.getPieceName(3, 6), "Pawn");
		//assertEquals(testing.getPieceName(4, 7), "Pawn");
	}
	
	@Test public void knightFunctionTest() {
		// Test For Moving and Capturing with Knights
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 1,2,false);
		pawn.spawnPawn(testing, 4,1,false);
		pawn.spawnPawn(testing, 5,2,false);
		
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 1, 2);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 2, 1);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 4, 1);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 5, 2);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 5, 4);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 4, 5);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 2, 5);
		testing.turnChange();
		knight.spawnKnight(testing, 3,3,true);
		knight.moveKnight(testing, testing.getPiece(3, 3), 1, 4);
		testing.turnChange();
	
		System.out.println("");
		System.out.println("knightFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(3, 3), "null");
		assertEquals(testing.getPieceName(1, 2), "Knight");
		assertEquals(testing.getPieceName(2, 1), "Knight");
		assertEquals(testing.getPieceName(4, 1), "Knight");
		assertEquals(testing.getPieceName(5, 2), "Knight");
		assertEquals(testing.getPieceName(5, 4), "Knight");
		assertEquals(testing.getPieceName(4, 5), "Knight");
		assertEquals(testing.getPieceName(2, 5), "Knight");
		assertEquals(testing.getPieceName(1, 4), "Knight");
		
		// Test for Out-Of-Bounds
		knight.spawnKnight(testing, 0,0,true);
		knight.moveKnight(testing, testing.getPiece(0, 0), -2, -1);
		assertEquals(testing.getPieceName(0, 0), "Knight");
	
	}
	
	@Test public void rookFunctionTest() {
		// Test For Moving and Capturing with Rooks
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 0,3,false);
		pawn.spawnPawn(testing, 3,0,false);
	
		testing.turnChange();
		rook.spawnRook(testing, 3,3,true);
		rook.moveRook(testing, testing.getPiece(3, 3), 0, 3);
		testing.turnChange();
		rook.spawnRook(testing, 3,3,true);
		rook.moveRook(testing, testing.getPiece(3, 3), 7, 3);
		testing.turnChange();
		rook.spawnRook(testing, 3,3,true);
		rook.moveRook(testing, testing.getPiece(3, 3), 3, 0);
		testing.turnChange();
		rook.spawnRook(testing, 3,3,true);
		rook.moveRook(testing, testing.getPiece(3, 3), 3, 7);
		testing.turnChange();
		rook.spawnRook(testing, 3,3,true);
		rook.moveRook(testing, testing.getPiece(3, 3), 0, 0);
		testing.turnChange();
	
		// Pathing Test
		rook.spawnRook(testing, 7,6,true);
		pawn.spawnPawn(testing, 4,6,true);
		rook.moveRook(testing, testing.getPiece(7, 6), 0, 6);
		
		System.out.println("");
		System.out.println("rookFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 3), "Rook");
		assertEquals(testing.getPieceName(7, 3), "Rook");
		assertEquals(testing.getPieceName(3, 0), "Rook");
		assertEquals(testing.getPieceName(3, 7), "Rook");
		assertEquals(testing.getPieceName(3, 3), "Rook");
		assertEquals(testing.getPieceName(7, 6), "Rook");
		
		// Test for Out-Of-Bounds
		rook.spawnRook(testing, 0,0,true);
		rook.moveRook(testing, testing.getPiece(0, 0), 8, 0);
		assertEquals(testing.getPieceName(0, 0), "Rook");
	}
	
	@Test public void bishopFunctionTest() {
		// Test For Moving and Capturing with Bishops
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 0,0,false);
		pawn.spawnPawn(testing, 7,7,false);
	
		testing.turnChange();
		bishop.spawnBishop(testing, 3,3,true);
		bishop.moveBishop(testing, testing.getPiece(3, 3), 0, 0);
		testing.turnChange();
		bishop.spawnBishop(testing, 3,3,true);
		bishop.moveBishop(testing, testing.getPiece(3, 3), 7, 7);
		testing.turnChange();
		bishop.spawnBishop(testing, 3,3,true);
		bishop.moveBishop(testing, testing.getPiece(3, 3), 0, 6);
		testing.turnChange();
		bishop.spawnBishop(testing, 3,3,true);
		bishop.moveBishop(testing, testing.getPiece(3, 3), 6, 0);
		testing.turnChange();
		bishop.spawnBishop(testing, 3,3,true);
		bishop.moveBishop(testing, testing.getPiece(3, 3), 1, 2);
		testing.turnChange();
	
		System.out.println("");
		System.out.println("bishopFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 0), "Bishop");
		assertEquals(testing.getPieceName(7, 7), "Bishop");
		assertEquals(testing.getPieceName(0, 6), "Bishop");
		assertEquals(testing.getPieceName(6, 0), "Bishop");
		assertEquals(testing.getPieceName(3, 3), "Bishop");
		
		// Test for Out-Of-Bounds
		bishop.spawnBishop(testing, 0,0,true);
		bishop.moveBishop(testing, testing.getPiece(0, 0), 8, 8);
		assertEquals(testing.getPieceName(0, 0), "Bishop");
		
	}
	
	@Test public void queenFunctionTest() {
		// Test For Moving and Capturing with Queens
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 0,3,false);
		pawn.spawnPawn(testing, 3,0,false);
		pawn.spawnPawn(testing, 0,0,false);
		pawn.spawnPawn(testing, 7,7,false);
		
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 0, 3);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 7, 3);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 3, 0);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 3, 7);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 0, 0);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 7, 7);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 0, 6);
		testing.turnChange();
		queen.spawnQueen(testing, 3,3,true);
		queen.moveQueen(testing, testing.getPiece(3, 3), 6, 0);
		testing.turnChange();
		
		System.out.println("");
		System.out.println("queenFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 3), "Queen");
		assertEquals(testing.getPieceName(7, 3), "Queen");
		assertEquals(testing.getPieceName(3, 0), "Queen");
		assertEquals(testing.getPieceName(3, 7), "Queen");
		assertEquals(testing.getPieceName(0, 0), "Queen");
		assertEquals(testing.getPieceName(7, 7), "Queen");
		assertEquals(testing.getPieceName(0, 6), "Queen");
		assertEquals(testing.getPieceName(6, 0), "Queen");
		
		// Test for Out-Of-Bounds
		queen.spawnQueen(testing, 0,7,true);
		queen.moveQueen(testing, testing.getPiece(0, 0), 8, -1);
		testing.turnChange();
		queen.spawnQueen(testing, 0,5,true);
		queen.moveQueen(testing, testing.getPiece(0, 5), 8, 5);
		assertEquals(testing.getPieceName(0, 7), "Queen");
		assertEquals(testing.getPieceName(0, 5), "Queen");
		
	}
	
	@Test public void kingFunctionTest() {
		// Test For Moving and Capturing with Kings
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 2,2,false);
		pawn.spawnPawn(testing, 2,3,false);
		pawn.spawnPawn(testing, 2,4,false);
		
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 2, 2);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 2, 3);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 2, 4);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 3, 2);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 3, 4);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 4, 2);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 4, 3);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 4, 4);
		testing.turnChange();
		king.spawnKing(testing, 3,3,true);
		king.moveKing(testing, testing.getPiece(3, 3), 5, 3);
		testing.turnChange();
		
		System.out.println("");
		System.out.println("kingFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(2, 2), "King");
		assertEquals(testing.getPieceName(2, 3), "King");
		assertEquals(testing.getPieceName(2, 4), "King");
		assertEquals(testing.getPieceName(3, 2), "King");
		assertEquals(testing.getPieceName(3, 4), "King");
		assertEquals(testing.getPieceName(4, 2), "King");
		assertEquals(testing.getPieceName(4, 3), "King");
		assertEquals(testing.getPieceName(4, 4), "King");
		assertEquals(testing.getPieceName(3, 3), "King");
		
		// Test for Out-Of-Bounds
		king.spawnKing(testing, 7,7,true);
		king.moveKing(testing, testing.getPiece(7, 7), 8, 8);
		assertEquals(testing.getPieceName(7, 7), "King");
	
	}
	
	@Test public void friendlyFireTest() {
		// Test to Ensure Player Can't Capture Their Own Pieces
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 1,0,true);
		pawn.spawnPawn(testing, 1,1,true);
		pawn.spawnPawn(testing, 1,2,true);
		pawn.spawnPawn(testing, 1,3,true);
		pawn.spawnPawn(testing, 1,4,true);
		pawn.spawnPawn(testing, 1,5,true);
		
		knight.spawnKnight(testing, 3,1,true);
		rook.spawnRook(testing, 2,1,true);
		bishop.spawnBishop(testing, 2,3,true);
		king.spawnKing(testing, 2,4,true);
		queen.spawnQueen(testing, 2,5,true);
		
		knight.moveKnight(testing, testing.getPiece(3, 1), 1, 0);
		rook.moveRook(testing, testing.getPiece(2, 1), 1, 1);
		bishop.moveBishop(testing, testing.getPiece(2, 3), 1, 2);
		king.moveKing(testing, testing.getPiece(2, 4), 1, 4);
		queen.moveQueen(testing, testing.getPiece(2, 5), 1, 5);
	
		System.out.println("");
		System.out.println("friendlyFireTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(1, 0), "Pawn");
		assertEquals(testing.getPieceName(1, 1), "Pawn");
		assertEquals(testing.getPieceName(1, 2), "Pawn");
		assertEquals(testing.getPieceName(1, 3), "Pawn");
		assertEquals(testing.getPieceName(1, 4), "Pawn");
		assertEquals(testing.getPieceName(1, 5), "Pawn");
		assertEquals(testing.getPieceName(3, 1), "Knight");
		assertEquals(testing.getPieceName(2, 1), "Rook");
		assertEquals(testing.getPieceName(2, 3), "Bishop");
		assertEquals(testing.getPieceName(2, 5), "Queen");
		assertEquals(testing.getPieceName(2, 4), "King");
		
	}
	
	@Test public void checkTest() {
		// Test to Ensure Pieces Can Check
		
		gameBoard testing = new gameBoard();
		
		
		king.spawnKing(testing, 3,3,true);
		king.spawnKing(testing, 3,4,true);
		king.spawnKing(testing, 3,5,true);
		king.spawnKing(testing, 3,6,true);
		king.spawnKing(testing, 3,7,true);
		
		king A = (king) testing.getPiece(3, 3);
		king B = (king) testing.getPiece(3, 4);
		king C = (king) testing.getPiece(3, 5);
		king D = (king) testing.getPiece(3, 6);
		king E = (king) testing.getPiece(3, 7);
		
		pawn.spawnPawn(testing, 2,2,false);
		rook.spawnRook(testing, 1, 4,false);
		bishop.spawnBishop(testing, 1, 3, false);
		queen.spawnQueen(testing, 1,6,false);
		queen.spawnQueen(testing, 1, 5, false);
		
		king.spawnKing(testing, 5,4,true);
		king.spawnKing(testing, 5,5,true);
		king.spawnKing(testing, 5,6,true);
		king.spawnKing(testing, 5,7,true);
		
		king G = (king) testing.getPiece(5, 4);
		king H = (king) testing.getPiece(5, 5);
		king I = (king) testing.getPiece(5, 6);
		king J = (king) testing.getPiece(5, 7);
		
		rook.spawnRook(testing, 7, 4,false);
		bishop.spawnBishop(testing, 7, 7, false);
		queen.spawnQueen(testing, 7,6,false);
		queen.spawnQueen(testing, 7, 5, false);
		
		System.out.println("");
		System.out.println("checkTest()");
		testing.printGameState();
		
		assertEquals(king.inCheck(testing, A, 3, 3), true);
		assertEquals(king.inCheck(testing, B, 3, 4), true);
		assertEquals(king.inCheck(testing, C, 3, 5), true);
		assertEquals(king.inCheck(testing, D, 3, 6), true);
		assertEquals(king.inCheck(testing, E, 3, 7), true);
		
		assertEquals(king.inCheck(testing, G, 5, 4), true);
		assertEquals(king.inCheck(testing, H, 5, 5), true);
		assertEquals(king.inCheck(testing, I, 5, 6), true);
		assertEquals(king.inCheck(testing, J, 5, 7), true);
		
	}
	
	@Test public void rareCheckTest() {
		// Test to Ensure Rarer Pieces Can Check
		
		gameBoard testing = new gameBoard();
		
		king.spawnKing(testing, 3,3,true);
		king.spawnKing(testing, 3,4,true);
		king.spawnKing(testing, 3,5,true);
		king.spawnKing(testing, 3,6,true);
		king.spawnKing(testing, 3,7,true);
		
		king A = (king) testing.getPiece(3, 3);
		king B = (king) testing.getPiece(3, 4);
		king C = (king) testing.getPiece(3, 5);
		king D = (king) testing.getPiece(3, 6);
		
		
		king.spawnKing(testing, 2,2,false);
		assassin.spawnAssassin(testing, 1, 4,false);
		assassin.spawnAssassin(testing, 1, 3, false);
		dragon.spawnDragon(testing, 2,7,false);
	
		king.spawnKing(testing, 5,4,true);
		king.spawnKing(testing, 5,5,true);
		king.spawnKing(testing, 5,6,true);
		king.spawnKing(testing, 5,7,true);
		
		king G = (king) testing.getPiece(5, 4);
		king H = (king) testing.getPiece(5, 5);
		king I = (king) testing.getPiece(5, 6);
		
		assassin.spawnAssassin(testing, 7, 4,false);
		assassin.spawnAssassin(testing, 7, 7, false);
		dragon.spawnDragon(testing, 6,7,false);
		
		System.out.println("");
		System.out.println("rareCheckTest()");
		testing.printGameState();
		
		assertEquals(king.inCheck(testing, A, 3, 3), true);
		assertEquals(king.inCheck(testing, B, 3, 4), true);
		assertEquals(king.inCheck(testing, C, 3, 5), true);
		assertEquals(king.inCheck(testing, D, 3, 6), true);
		
		
		assertEquals(king.inCheck(testing, G, 5, 4), true);
		assertEquals(king.inCheck(testing, H, 5, 5), true);
		assertEquals(king.inCheck(testing, I, 5, 6), true);
	
	}
	
	@Test public void notInCheckTest() {
		// Test to Ensure Out-Of-Range Pieces Don't Check
		
		gameBoard testing = new gameBoard();
		
		king.spawnKing(testing, 3,3,true);
		king.spawnKing(testing, 3,4,true);
		king.spawnKing(testing, 3,5,true);
		king.spawnKing(testing, 3,6,true);
		king.spawnKing(testing, 3,7,true);
		
		king A = (king) testing.getPiece(3, 3);
		king B = (king) testing.getPiece(3, 4);
		king C = (king) testing.getPiece(3, 5);
		
		king.spawnKing(testing, 1,2,false);
		assassin.spawnAssassin(testing, 7, 4,false);
		pawn.spawnPawn(testing, 0, 7, false);
	
	
		
		
		assassin.spawnAssassin(testing, 7, 4,false);
		assassin.spawnAssassin(testing, 7, 7, false);
		dragon.spawnDragon(testing, 6,7,false);
		
		System.out.println("");
		System.out.println("checkTest()");
		testing.printGameState();
		
		assertEquals(king.inCheck(testing, A, 3, 3), false);
		assertEquals(king.inCheck(testing, B, 3, 4), false);
		assertEquals(king.inCheck(testing, C, 3, 5), false);
	}
	
	@Test public void kingMovesToCheckTest() {
		// Test to Ensure Player Can't Capture Their Own Pieces
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 1,0,false);
		king.spawnKing(testing, 3,2,true);
		
		king.moveKing(testing, testing.getPiece(3, 2), 2, 1);
	
		System.out.println("");
		System.out.println("kingMovesToCheckTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(3, 2), "King");
		
	}

	@Test public void checkmateTest() {
		// Test to Ensure Player Can't Capture Their Own Pieces
		
		gameBoard testing = new gameBoard();
		
		// Anastasia's Mate
		
		queen.spawnQueen(testing, 0,3,true);
		rook.spawnRook(testing, 0,5,true);
		king.spawnKing(testing, 0,7,true);
		knight.spawnKnight(testing, 1,4,false);
		pawn.spawnPawn(testing, 1,6,true);
		pawn.spawnPawn(testing, 1,7,true);
		pawn.spawnPawn(testing, 3,2,true);
		rook.spawnRook(testing, 3,4,false);
		knight.spawnKnight(testing, 4,3,true);
		queen.spawnQueen(testing, 5,3,false);
		pawn.spawnPawn(testing, 6,5,false);
		pawn.spawnPawn(testing, 6,6,false);
		pawn.spawnPawn(testing, 6,7,false);
		king.spawnKing(testing, 7,6,false);
		//added for ease of testing
		rook.spawnRook(testing, 7,2, true);
	
		System.out.println("");
		System.out.println("checkmateTest()");
		testing.printGameState();
		
		assertEquals(king.inCheck(testing, testing.getPiece(7, 6), 7, 6), true);
		assertEquals(king.isCheckmate(testing, testing.getPiece(7, 6),false, 7, 6), true);
		
	}
	
	@Test public void foolsMateTest() {
		// Test to Ensure Player Can't Capture Their Own Pieces
		
		gameBoard testing = gameBoard.setBoard();
		
		pawn.movePawn(testing, testing.getPiece(6, 5), 5, 5);
		pawn.movePawn(testing, testing.getPiece(1, 4), 3, 4);
		pawn.movePawn(testing, testing.getPiece(6, 6), 4, 6);
		queen.moveQueen(testing, testing.getPiece(0, 3), 4, 7);
	
		System.out.println("");
		System.out.println("foolsMateTest()");
		testing.printGameState();
		
		assertEquals(king.inCheck(testing, testing.getPiece(7, 4), 7, 4), true);
		assertEquals(king.isCheckmate(testing, testing.getPiece(7, 4),false, 7, 4), true);
		
	}
	
	@Test public void assassinFunctionTest() {
		// Test For Moving and Capturing with Queens
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 0,3,false);
		pawn.spawnPawn(testing, 3,0,false);
		pawn.spawnPawn(testing, 0,0,false);
		pawn.spawnPawn(testing, 6,6,false);
		pawn.spawnPawn(testing, 2,3,false);
		
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 0, 3);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 6, 3);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 3, 0);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 3, 6);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 0, 0);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 6, 6);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 0, 6);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 6, 0);
		testing.turnChange();
		assassin.spawnAssassin(testing, 3,3,true);
		assassin.moveAssassin(testing, testing.getPiece(3, 3), 7, 0);
		testing.turnChange();
		
		System.out.println("");
		System.out.println("assassinFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 3), "Assassin");
		assertEquals(testing.getPieceName(6, 3), "Assassin");
		assertEquals(testing.getPieceName(3, 0), "Assassin");
		assertEquals(testing.getPieceName(3, 6), "Assassin");
		assertEquals(testing.getPieceName(0, 0), "Assassin");
		assertEquals(testing.getPieceName(6, 6), "Assassin");
		assertEquals(testing.getPieceName(0, 6), "Assassin");
		assertEquals(testing.getPieceName(6, 0), "Assassin");
		assertEquals(testing.getPieceName(3, 3), "Assassin");
		
		// Test for Out-Of-Bounds
		assassin.spawnAssassin(testing, 0,7,true);
		assassin.moveAssassin(testing, testing.getPiece(0, 0), -1, 0);
		testing.turnChange();
		assassin.spawnAssassin(testing, 0,5,true);
		assassin.moveAssassin(testing, testing.getPiece(0, 5), 0, 8);
		assertEquals(testing.getPieceName(0, 7), "Assassin");
		assertEquals(testing.getPieceName(0, 5), "Assassin");
		
	}
	
	@Test public void dragonFunctionTest() {
		// Test For Moving and Capturing with Knights
		
		gameBoard testing = new gameBoard();
		
		pawn.spawnPawn(testing, 0,3,false);
		pawn.spawnPawn(testing, 3,0,false);
		pawn.spawnPawn(testing, 6,3,false);
		pawn.spawnPawn(testing, 1,4,false);
		
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 1, 2);
		testing.turnChange();
		dragon.moveDragon(testing, testing.getPiece(1, 2), 0, 3);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 2, 1);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 4, 1);
		testing.turnChange();
		dragon.moveDragon(testing, testing.getPiece(4, 1), 3, 0);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 5, 2);
		testing.turnChange();
		dragon.moveDragon(testing, testing.getPiece(5, 2), 6, 3);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 5, 4);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 4, 5);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 2, 5);
		testing.turnChange();
		dragon.spawnDragon(testing, 3,3,true);
		dragon.moveDragon(testing, testing.getPiece(3, 3), 1, 4);
		testing.turnChange();
		
		System.out.println("");
		System.out.println("dragonFunctionTest()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(0, 3), "Dragon");
		assertEquals(testing.getPieceName(2, 1), "Dragon");
		assertEquals(testing.getPieceName(3, 0), "Dragon");
		assertEquals(testing.getPieceName(6, 3), "Dragon");
		assertEquals(testing.getPieceName(5, 4), "Dragon");
		assertEquals(testing.getPieceName(4, 5), "Dragon");
		assertEquals(testing.getPieceName(2, 5), "Dragon");
		assertEquals(testing.getPieceName(3, 3), "Dragon");
		assertEquals(testing.getPieceName(1, 4), "Pawn");
		
		// Test for Out-Of-Bounds
		dragon.spawnDragon(testing, 0,0,true);
		dragon.moveDragon(testing, testing.getPiece(0, 0), -2, -1);
		assertEquals(testing.getPieceName(0, 0), "Dragon");
	
	}
	
	@Test public void moveCommand() {
		// Test to Ensure Player Can't Capture Their Own Pieces
		
		gameBoard testing = gameBoard.setBoard();
		
		testing.turnChange();
		testing.moveCommand(1,0,3,0);
		testing.moveCommand(6,3,4,3);
		testing.moveCommand(1,7,3,7);		
		
		testing.moveCommand(7,1,5,2);
		testing.moveCommand(0,1,2,0);
		
		testing.turnChange();
		testing.moveCommand(0,0,0,1);
		testing.moveCommand(7,0,7,1);
				
		testing.turnChange();
		testing.moveCommand(7,2,6,3);		
		
		testing.turnChange();
		testing.moveCommand(7,3,7,2);
		
		testing.turnChange();
		testing.moveCommand(7,4,7,3);
		
		System.out.println("");
		System.out.println("moveCommand()");
		testing.printGameState();
		
		assertEquals(testing.getPieceName(3, 0), "Pawn");
		assertEquals(testing.getPieceName(4, 3), "Pawn");
		assertEquals(testing.getPieceName(3, 7), "Pawn");
		assertEquals(testing.getPieceName(5, 2), "Knight");
		assertEquals(testing.getPieceName(2, 0), "Knight");
		assertEquals(testing.getPieceName(0, 1), "Rook");
		assertEquals(testing.getPieceName(7, 1), "Rook");
		assertEquals(testing.getPieceName(6, 3), "Bishop");
		assertEquals(testing.getPieceName(7, 2), "Queen");
		assertEquals(testing.getPieceName(7, 3), "King");
	}
}