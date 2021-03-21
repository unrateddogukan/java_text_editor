package proje2;

import java.util.Stack;

//UndoableCommand nesnelerinin oluşturulduğunda bir stack içerisine atılıp kaydedildiği ve daha sonra bu komut nesnelerine ulaşılabilen class.
public class CommandHistory {
    private final Stack<UndoableCommand> history = new Stack<>();

    public void push(UndoableCommand c) {
        history.push(c);
    }

    public UndoableCommand pop() {
        return history.pop();
    }

    public boolean isEmpty() { 
        return history.isEmpty(); 
    }
}
