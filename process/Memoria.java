/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramses;

import java.util.ArrayList;

/**
 *
 * @authors Felipe Tadeu Trinhain Palermo
 *          Frederico de Oliveira Sedrez
 */
public class Memoria {

    private final int tamanhoMemoria = 256;
    private final int posIniInstrucoes = 0;
    private final int posIniDados = 206;
    private ArrayList<String> memoria;
    
    
    public Memoria() {
        memoria = new ArrayList(tamanhoMemoria);
        inicializaMemoria();

    }

    private void inicializaMemoria() {
        
        for(int i=0; i<tamanhoMemoria; i++){
            memoria.add(i, "00000000");
            
        }
    }

    public int getTamMemoria() {
        return memoria.size();
    }

    
    public boolean isDado(int N){
        if (N >= posIniDados && N < tamanhoMemoria ){
            return true;
        }
        else{
            return false;
        }
    }
    
    public  boolean isInstrucao(int N){
        if (N < posIniDados && N >= posIniInstrucoes ){
            return true;
        }
        else{
            return false;
        }
    }

    public int getPosIniInst() {
        return posIniInstrucoes;
    }

    public int getPosIniDados() {
        return posIniDados;
    }


    public void setDado(String posString, int operando) {
        int pos;
        String op;
        pos = Converter.stringParaInt(posString);
        op = Converter.paraStringBin8(operando);
        memoria.set(pos, op);
    }

    public void setDadoInd(String posString, int operando) {
        int pos, pos2;
        String op;
        pos = Converter.stringParaInt(posString);
        pos2 = Converter.stringParaInt(memoria.get(pos));
        op = Converter.paraStringBin8(operando);
        setDado(op, pos2);

    }

    public String getDado(int pos) {
        return memoria.get(pos);
    }

    public String getDadoInd(int pos) {
        int pos2;
        pos2 = Converter.stringParaInt(getDado(pos));
        return getDado(pos2);
    }
}
