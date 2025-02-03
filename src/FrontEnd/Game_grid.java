package FrontEnd;

import ChessCore.*;
import static ChessCore.PawnPromotion.None;
import ChessCore.Pieces.Piece;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import ChessCore.Player;
import ChessCore.Pieces.King;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;

public class Game_grid extends JFrame {

    public ClassicChessGame chessGame;
    public ChessBoard chessBoard;
    public Board_grid boardPanel;
    public Piece[][] board;
    private MoveCommand moveCommand;
    private NormalMoveCommand normalMoveCommand;
    private PawnPromotionMoveCommand pawnPromotionMoveCommand;

    public Game_grid() {
        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMaximumSize(new Dimension(800, 800));
        chessGame = new ClassicChessGame();
        // set up board grid panel
        boardPanel = new Board_grid(8, 8, this, chessGame);
        add(boardPanel);

        chessBoard = chessGame.getBoard();
        board = chessBoard.board;

        // test the update and the flip functions
        boardPanel.setBoard(board, chessGame.getWhoseTurn());

        normalMoveCommand = new NormalMoveCommand(chessGame);
        pawnPromotionMoveCommand = new PawnPromotionMoveCommand(chessGame);

    }

    public static void main(String[] args) {
        Game_grid game = new Game_grid();

        game.setVisible(true);
    }

    public void move(int x1, int y1, int x2, int y2) {
        System.out.println(BoardFile.values()[y1]);
        System.out.println(BoardRank.values()[x1]);
        System.out.println(BoardFile.values()[y2]);
        System.out.println(BoardRank.values()[x2]);

        Square current = new Square(BoardFile.values()[y1], BoardRank.values()[x1]);

        Square target = new Square(BoardFile.values()[y2], BoardRank.values()[x2]);
        Move move = new Move(current, target);

        if (chessGame.isPawnPromoted(current, target) == true && chessGame.getGameStatus() == GameStatus.IN_PROGRESS) {
            int pieceOption;
            String[] options = {"None", "Knight", "Bishop", "Rook", "Queen"};
            pieceOption = JOptionPane.showOptionDialog(
                    null,
                    "Pawn Promoted",
                    "Choose Promotion",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            move.setPawnPromotionFromOption(pieceOption);
            pawnPromotionMoveCommand.setMove(move);
            moveCommand = pawnPromotionMoveCommand;
        } else {
            normalMoveCommand.setMove(move);
            moveCommand = normalMoveCommand;
        }

        if (moveCommand.execute()) {
            boardPanel.updateBoard(board, chessGame.getWhoseTurn());
            updateGui();
        }
        CheckGameStatus();
    }

    public void CheckGameStatus() {

        if (chessGame.getGameStatus() == GameStatus.WHITE_UNDER_CHECK || chessGame.getGameStatus() == GameStatus.BLACK_UNDER_CHECK) {
            Player player = chessGame.getGameStatus() == GameStatus.WHITE_UNDER_CHECK ? Player.WHITE : Player.BLACK;

            Square sq = getKingSquare(player, chessBoard);
            boardPanel.highlightKing(sq);

        }
        if (chessGame.getGameStatus() == GameStatus.BLACK_WON || chessGame.getGameStatus() == GameStatus.WHITE_WON || chessGame.getGameStatus() == GameStatus.INSUFFICIENT_MATERIAL || chessGame.getGameStatus() == GameStatus.STALEMATE) {

            JOptionPane.showMessageDialog(null, chessGame.getGameStatus(), "Game Ended", 1);
        }
    }

    public void updateGui() {
        repaint();
    }

    public Square getKingSquare(Player whoseTurn, ChessBoard board) {
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        for (BoardFile file : files) {
            for (BoardRank rank : ranks) {
                Square sq = new Square(file, rank);
                Piece p = board.getPieceAtSquare(sq);
                if (p instanceof King && p.getOwner() == whoseTurn) {
                    return sq;
                }
            }
        }
        return null;
    }
}
