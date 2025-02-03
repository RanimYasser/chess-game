/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

/**
 *
 * @author Win11
 */
public class PawnPromotionMoveCommand implements MoveCommand{
     private  ChessGame chessGame;
    private  Move move=null;

    
   public PawnPromotionMoveCommand(ChessGame chessGame) {
        this.chessGame = chessGame;
        
    }


     @Override
    public void setMove(Move move) {
        this.move = move;
    }
   
    /**
     *
     * @return
     */
   

    @Override
    public void undo() {
    
    }

    @Override
    public boolean execute() 
    {
    return chessGame.makeMove(move);
    }
    

}
