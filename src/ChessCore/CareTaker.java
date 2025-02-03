package ChessCore;

import javax.swing.*;
import java.util.Stack;

public class CareTaker{
    private Stack<MementoClass> undoStack=new Stack<>();


    public CareTaker() {

    }

    public void saveSnapShot(MementoClass snapShot){
        undoStack.push(snapShot);
    }


    public MementoClass getLastSnapshot(){
        if(!undoStack.empty())
            return undoStack.pop();
        JOptionPane.showMessageDialog(null, "No more moves to undo", "Undo Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

}