package FrontEnd;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ClassicChessGame;
import ChessCore.NormalMoveCommand;
import ChessCore.PawnPromotion;
import ChessCore.PawnPromotionMoveCommand;
import FrontEnd.Game_grid;
import ChessCore.Pieces.Piece;
import ChessCore.Player;
import ChessCore.Square;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board_grid extends JPanel {

    private int rows;
    private int columns;
    private int firstRow;
    private int firstColumn;
    private int secondRow;
    private int secondColumn;
    private boolean firstClick = true;
    private Game_grid game;
    private boolean WhitePlaying = true;
    private ImageIcon img;
    private ClassicChessGame chessGame;
    private boolean flag = true;
    private Square currentSquare;
    private Square targetSquare;

    public Board_grid(int rows, int columns, Game_grid game, ClassicChessGame chessGame) {
        this.columns = columns;
        this.rows = rows;
        this.game = game;  // Save a reference to the Game_grid instance
        setLayout(new GridLayout(rows, columns));
        createBoard(rows, columns);
        setPreferredSize(new Dimension(800, 800));
        addMouseListener(new GridMouseListener());
        this.chessGame = chessGame;
    }

    private void createBoard(int rows, int columns) {
        int index = 0;
        int odd = 1;
        int even = 0;
        for (int i = 0; i < 64; i++) {
            JPanel label = new JPanel(new GridLayout(1, 1));
            label.add(new JLabel());
            if (i % 2 == odd) {
                label.setBackground(new Color(119, 148, 85));

            }

            if (i % 2 == even) {
                label.setBackground(new Color(235, 235, 208));

            }
            if ((i + 1) % 8 == 0) {
                int temp = odd;
                odd = even;
                even = temp;
            }
            this.add(label, index);
            index++;
        }
    }

    public void updateBoard(Piece[][] board, Player player) {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Component squareComponent = this.getComponent((i * columns) + j);
            JPanel squarePanel = (JPanel) squareComponent;

            int row = (player == Player.BLACK) ? i : (7 - i);
            int col = (player == Player.BLACK) ? (7- j) : j;

            img = loadImage(GetFileName(board[row][col]));
            squarePanel.setBorder(null);

            JLabel label = new JLabel(img);

            squarePanel.removeAll();
            squarePanel.add(label);
        }
    }

    revalidate();
    repaint();
}


    public void setBoard(Piece[][] board, Player player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                img = loadImage(GetFileName(board[7 - i][j]));
                JLabel label = new JLabel(img);

                ((JPanel) this.getComponent((i * columns) + j)).remove(0);
                ((JPanel) this.getComponent((i * columns) + j)).add(label);
            }
        }

        revalidate();
        repaint();
    }

    public boolean compare(ArrayList<Square> validMoves, Square square) {
        int squareFile = square.getFile().ordinal();
        int squareRank = square.getRank().ordinal();
        for (Square move : validMoves) {
            int moveFile = move.getFile().ordinal();
            int moveRank = move.getRank().ordinal();
            if (squareFile == moveFile && squareRank == moveRank) {
                return true;
            }
        }
        return false;
    }

    public void highlightValidMoves(ArrayList<Square> validMoves) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square s = new Square(BoardFile.values()[j], BoardRank.values()[i]);
                int row = (chessGame.getWhoseTurn() == Player.BLACK) ? i : (7 - i);
                int col = (chessGame.getWhoseTurn() == Player.BLACK) ? (7 - j) : j;

                Component squareComponent = this.getComponent((row * columns) + col);
                Color borderColor;
                Color backgroundColor;

                if (compare(validMoves, s)) {
                    borderColor = (game.chessBoard.getPieceAtSquare(s) == null) ? new Color(0, 235, 2) : new Color(255, 0, 0);
                } else {
                    borderColor = ((i + j) % 2 == 0) ? new Color(119, 148, 85) : new Color(235, 235, 208);
                }

                ((JPanel) squareComponent).setBorder(BorderFactory.createLineBorder(borderColor, 5));

                if (!compare(validMoves, s)) {
                    backgroundColor = ((i + j) % 2 == 0) ? new Color(119, 148, 85) : new Color(235, 235, 208);
                    squareComponent.setBackground(backgroundColor);
                }
            }
        }
        repaint();
    }

 

    public String GetFileName(Piece piece) {
        if (piece != null) {
            String color = piece.getOwner() == Player.WHITE ? "White" : "Black";
            String pieceName = piece.getName();
            String imgPath = color + pieceName + ".png";
            return imgPath;

        }
        return null;
    }

    public ImageIcon loadImage(String imgPath) {

        try {
            ImageIcon imgIcon = new ImageIcon(imgPath);
            imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            return imgIcon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void highlightKing(Square sq) {
        int j = chessGame.getWhoseTurn() == Player.BLACK ? (7 - sq.getFile().ordinal()) : sq.getFile().ordinal();
        int i = chessGame.getWhoseTurn() == Player.BLACK ? sq.getRank().ordinal() : 7 - sq.getRank().ordinal();
        Component squareComponent = this.getComponent((i * columns) + j);
        squareComponent.setBackground(new Color(0, 0, 255));
        repaint();
        revalidate();
    }

    private class GridMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int gridSize = getWidth() / columns;
            int clickedRow = e.getY() / gridSize;
            int clickedColumn = e.getX() / gridSize;

            if (firstClick) {
                FirstClick(clickedRow, clickedColumn);
            } else {
                SecondClick(clickedRow, clickedColumn);
            }
        }

        private void FirstClick(int clickedRow, int clickedColumn) {
            firstRow = chessGame.getWhoseTurn() == Player.BLACK ? clickedRow : (7 - clickedRow);
            firstColumn = chessGame.getWhoseTurn() == Player.BLACK ? (7 - clickedColumn) : clickedColumn;

            currentSquare = new Square(BoardFile.values()[firstColumn], BoardRank.values()[firstRow]);
            ArrayList<Square> allValidMoves = new ArrayList<>(game.chessGame.getAllValidMovesFromSquare(currentSquare));
            highlightValidMoves(allValidMoves);

            firstClick = false;
        }

        private void SecondClick(int clickedRow, int clickedColumn) {
            secondRow = chessGame.getWhoseTurn() == Player.BLACK ? clickedRow : (7 - clickedRow);
            secondColumn = chessGame.getWhoseTurn() == Player.BLACK ? (7 - clickedColumn) : clickedColumn;

            targetSquare = new Square(BoardFile.values()[secondColumn], BoardRank.values()[secondRow]);

            if (chessGame.hasPieceInSquareForPlayer(targetSquare, chessGame.getWhoseTurn())) {
                ClickHandler();
            } else {
                game.move(firstRow, firstColumn, secondRow, secondColumn);
                firstClick = true;
            }
        }

        private void ClickHandler() {
            firstRow = secondRow;
            firstColumn = secondColumn;
            currentSquare = new Square(BoardFile.values()[firstColumn], BoardRank.values()[firstRow]);

            Piece selectedPiece = game.chessBoard.getPieceAtSquare(currentSquare);
            ArrayList<Square> allValidMoves = new ArrayList<>(game.chessGame.getAllValidMovesFromSquare(currentSquare));
            highlightValidMoves(allValidMoves);
        }
    }
}
