// classe que faz a ger�ncia das m�sicas tocadas durante o programa
import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class Sons {
    private InputStream som;
    private Sequencer tocar;
    
    // escolhe qual m�sica ser� tocada pelo m�todo iniciar
    public void escolheMusica(String musica) {
        som = this.getClass().getResourceAsStream("/"+musica+".mid");
    }
    
    // inicia a m�sica selecionada pelo m�todo escolheM�sica
    public void iniciar() {
        try {
            this.tocar = MidiSystem.getSequencer();
            this.tocar.open();
            this.tocar.setSequence(this.som);
            this.tocar.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // para a m�sica que estiver tocando
    public void parar() {
        this.tocar.stop();
        this.tocar.close();
    }
}