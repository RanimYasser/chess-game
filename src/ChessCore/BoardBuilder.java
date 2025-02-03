/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

import ChessCore.Pieces.Bishop;
import ChessCore.Pieces.King;
import ChessCore.Pieces.Knight;
import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;
import ChessCore.Pieces.PieceFactory;
import ChessCore.Pieces.Queen;
import ChessCore.Pieces.Rook;

/**
 *
 * @author Win11
 */

public class BoardBuilder {

    private PieceFactory pieceFactory;
    private Piece[][] board;

    public BoardBuilder() {
        this.pieceFactory = new PieceFactory();
        this.board = new Piece[8][8];
    }

    public BoardBuilder setPieceInSquare(int row, int col, String pieceType, Player player) {
        board[row][col] = pieceFactory.createPiece(pieceType, player);
        return this;
    }

    public BoardBuilder setEmptySquare(int row, int col) {
        board[row][col] = null;
        return this;
    }

    public Piece[][] build() {
        
        this.setPieceInSquare(0, 0, "Rook", Player.WHITE);
        this.setPieceInSquare(0, 1, "Knight", Player.WHITE);
        this.setPieceInSquare(0, 2, "Bishop", Player.WHITE);
        this.setPieceInSquare(0, 3, "Queen", Player.WHITE);
        this.setPieceInSquare(0, 4, "King", Player.WHITE);
        this.setPieceInSquare(0, 5, "Bishop", Player.WHITE);
        this.setPieceInSquare(0, 6, "Knight", Player.WHITE);
        this.setPieceInSquare(0, 7, "Rook", Player.WHITE);
        this.setPieceInSquare(1, 0, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 1, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 2, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 3, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 4, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 5, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 6, "Pawn", Player.WHITE);
        this.setPieceInSquare(1, 7, "Pawn", Player.WHITE);
        this.setPieceInSquare(6, 0, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 1, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 2, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 3, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 4, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 5, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 6, "Pawn", Player.BLACK);
        this.setPieceInSquare(6, 7, "Pawn", Player.BLACK);
        this.setPieceInSquare(7, 0, "Rook", Player.BLACK);
        this.setPieceInSquare(7, 1, "Knight", Player.BLACK);
        this.setPieceInSquare(7, 2, "Bishop", Player.BLACK);
        this.setPieceInSquare(7, 3, "Queen", Player.BLACK);
        this.setPieceInSquare(7, 4, "King", Player.BLACK);
        this.setPieceInSquare(7, 5, "Bishop", Player.BLACK);
        this.setPieceInSquare(7, 6, "Knight", Player.BLACK);
        this.setPieceInSquare(7, 7, "Rook", Player.BLACK);
        
        return board;
    }
    
}
