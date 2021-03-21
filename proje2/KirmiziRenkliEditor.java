package proje2;

import java.awt.*;

//RenkliEditor abstract class'indan extend edilerek metin editorunun
//menubar renginin kirmizi olmasini saglayan class.
public class KirmiziRenkliEditor extends RenkliEditor {

    @Override
    public Color renkSecim() {
        return Color.red;
    }
}
