package Vista;//GEN-LINE:variables

import Controladora.Controladora;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VenPri extends javax.swing.JFrame {

    public VenPri() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTabbedPane getTABpri() {
        return TABpri;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        TABpri = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TABpri, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TABpri, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TipInstruJPanel pti=new TipInstruJPanel();
                CalibracionesJPanel pcali=new CalibracionesJPanel();
                InstruJPanel pinstru=new InstruJPanel();
                VenPri v=new VenPri(); 
                Controladora c=new Controladora(v,pti,pinstru,pcali);
            }
        });
    }

    // Variables declaration - do not modify                     
    public javax.swing.JTabbedPane TABpri;
    // End of variables declaration                   
}
