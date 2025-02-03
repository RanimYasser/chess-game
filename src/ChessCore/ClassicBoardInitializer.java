package ChessCore;

import ChessCore.Pieces.*;

public final class ClassicBoardInitializer implements BoardInitializer {

    private static final BoardInitializer instance = new ClassicBoardInitializer();

    public ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        BoardBuilder boardBuilder = new BoardBuilder();
       
        return boardBuilder.build();
    }
}
