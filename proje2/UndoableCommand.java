package proje2;

//Geri alinabilen komutlarin implement ettigi, Command interface’inden extend edilen ve undo metodunu iceren interface.
public interface UndoableCommand extends Command{
    boolean undo();
}
