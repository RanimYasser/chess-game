package ChessCore;
//memento class
public class MementoClass {
    private final ChessBoard board;
    private  final GameStatus gameStatus;
    private final Player whoseTurn;
    private Move lastMove;
    private final boolean canWhiteCastleKingSide ;
    private final boolean canWhiteCastleQueenSide ;
    private final  boolean canBlackCastleKingSide ;
    private final boolean canBlackCastleQueenSide ;

    public MementoClass(ChessBoard board, GameStatus gameStatus, Player whoseTurn,Move lastMove, boolean canWhiteCastleKingSide, boolean canWhiteCastleQueenSide, boolean canBlackCastleKingSide, boolean canBlackCastleQueenSide) {
        this.board = new ChessBoard(board);
        this.gameStatus = gameStatus;
        this.whoseTurn = whoseTurn;
        this.canWhiteCastleKingSide = canWhiteCastleKingSide;
        this.canWhiteCastleQueenSide = canWhiteCastleQueenSide;
        this.canBlackCastleKingSide = canBlackCastleKingSide;
        this.canBlackCastleQueenSide = canBlackCastleQueenSide;
        this.lastMove=lastMove;


    }


    public ChessBoard getBoard() {
        return board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWhoseTurn() {
        return whoseTurn;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public boolean isCanWhiteCastleKingSide() {
        return canWhiteCastleKingSide;
    }

    public boolean isCanWhiteCastleQueenSide() {
        return canWhiteCastleQueenSide;
    }

    public boolean isCanBlackCastleKingSide() {
        return canBlackCastleKingSide;
    }



    public boolean isCanBlackCastleQueenSide() {
        return canBlackCastleQueenSide;
    }
}
