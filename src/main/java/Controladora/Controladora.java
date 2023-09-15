package Controladora;

import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Vista.CalibracionesJPanel;
import Vista.InstruJPanel;
import Vista.TipInstruJPanel;
import Vista.VenPri;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controladora implements ActionListener {
    private VenPri VenPricipal;
    public boolean BOOLvp=false;
    private TipInstruJPanel PanTIPOSInstru;
    public boolean BOOLti=false;
    private InstruJPanel PanInstru;
    public boolean BOOLi=false;
    private CalibracionesJPanel PanCali;
    public boolean BOOLcali=false;
    
    private ModelTabINSTRUMENTOS admiinstru;
    private ModelTabTipeInstrument admiTIPOSinstru;
    
    public Controladora(VenPri v,TipInstruJPanel ti,InstruJPanel i,CalibracionesJPanel c){
        this.VenPricipal=v;
        v.setVisible(true);
        
        this.PanTIPOSInstru=ti;
        this.PanInstru=i;
        this.PanCali=c;
        VenPricipal.getTABpri().addTab("Tipos de Instrumentos", PanTIPOSInstru);
        VenPricipal.getTABpri().addTab("Instrumentos", PanInstru);
        VenPricipal.getTABpri().addTab("Calibraciones", PanCali);
        
        admiTIPOSinstru=new ModelTabTipeInstrument();
        ti.getTablaListTipeInstrument().setModel(admiTIPOSinstru.getModelo());
        
        admiinstru=new ModelTabINSTRUMENTOS();
        i.getTablaDInstrumentos().setModel(admiinstru.getModelito());
        admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals()){
//        
//        }
    }

}
//////////  Lest seee
