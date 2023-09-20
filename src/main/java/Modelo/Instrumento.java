package Modelo;


public class Instrumento {
    private String Serie;
    private String Descripcion;
    private int Tolerancia;
    private int min;
    private int max;
    private TipoInstrumento tipDinstrumentos;
    private ListaCalibraciones calibraciones;

    public Instrumento(String Serie, String Descripcion, int min, int max,int Tolerancia, TipoInstrumento tipDinstrumentos) {
        this.Serie = Serie;
        this.Descripcion = Descripcion;
        this.Tolerancia = Tolerancia;
        this.min = min;
        this.max = max;
        this.tipDinstrumentos = tipDinstrumentos;
        calibraciones = new ListaCalibraciones();
    }

    public ListaCalibraciones getCalibraciones() {
        return calibraciones;
    }

    public void setCalibraciones(ListaCalibraciones calibraciones) {
        this.calibraciones = calibraciones;
    }

    public String getSerie() {
        return Serie;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getTolerancia() {
        return Tolerancia;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public TipoInstrumento getTipDinstrumentos() {
        return tipDinstrumentos;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setTolerancia(int Tolerancia) {
        this.Tolerancia = Tolerancia;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setTipDinstrumentos(TipoInstrumento tipDinstrumentos) {
        this.tipDinstrumentos = tipDinstrumentos;
    }
    
}
