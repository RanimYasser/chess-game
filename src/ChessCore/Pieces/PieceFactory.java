/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore.Pieces;

import ChessCore.Player;

/**
 *
 * @author Win11
 */
public class PieceFactory {

    public PieceFactory() {
    }

    public Piece createPiece(String pieceName,Player player) {
        switch (pieceName) {
            case "Pawn":
                return new Pawn(player);
            case "Rook":
                return new Rook(player);
            case "Knight":
                return new Knight(player);
            case "Queen":
                return new Queen(player);
            case "King":
                return new King(player);

            case "Bishop":
                return new Bishop(player);
            default:
            throw new IllegalArgumentException("Invalid Piece type");    
        
        }
    }

}