/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ChessCore;

/**
 *
 * @author Win11
 */
public interface MoveCommand {
    boolean execute() ;
    void undo();
    void setMove(Move move);
}
