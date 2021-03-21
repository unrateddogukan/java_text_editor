package proje2;


// Program arayüzü calisiyorken klavyeden yapilan tusa basma eylemlerinin komut nesneleri olarak kaydedilmesini ve 
// metin ekraninin yedeginin alinmasini saglayan class.
public class TusaBasmaKomutu implements UndoableCommand{

    public MetinEditoru editor;
    private String backup;

    public TusaBasmaKomutu(MetinEditoru editor) {
        this.editor = editor;
    }

    @Override
    public boolean execute() {
        backup = editor.anaEkran.getText();
        return true;
    }

    @Override
    public boolean undo() {
        editor.anaEkran.setText(backup);
        return true;
    }
}
