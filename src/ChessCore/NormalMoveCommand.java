/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

/**
 *
 * @author Win11
 */
public class NormalMoveCommand implements MoveCommand{
 private  ChessGame chessGame;
    private Move move;
   

    public NormalMoveCommand(ChessGame chessGame) {
        this.chessGame = chessGame;
     
    }



 @Override
    public void setMove(Move move) {
        this.move = move;
    }
    

  

  
     @Override
    
    public boolean execute() {
      return chessGame.makeMove(move);
    } 
    @Override
    public void undo() {
     
    }

   
    
}
