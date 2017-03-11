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

public class Ramses {
     //Declaração dos componentes da máquina: Memória e Rgistradores
    private Memoria mem = new Memoria();
    private PC pc = new PC();
    private Registrador a = new Registrador();
    private Registrador b = new Registrador();
    private Registrador x = new Registrador();
    private Registrador nzc = new Registrador();
    /**
     * @param args the command line arguments
     */
    public Ramses() {
        
       
       //inicializa valores dos Registradores com valor 0 em 8 bits
       inicializaRegs();
    }
    
    
    private void inicializaRegs(){
        a.setReg("00000000");
        b.setReg("00000000");
        x.setReg("00000000");
        pc.setReg("00000000");
        nzc.setReg("00000000");
    }
    
    public int getPC(){
        return Converter.stringParaInt(pc.getReg());
    }
    
    public void setPC(int endereço){
        pc.setReg(Converter.paraStringBin8(endereço));
    }
    
    public void incrementaPC(){
        pc.incrementa();
    }
    
     public String getRegistradorNZC(){
        return nzc.getReg();
    }
    
    public void setRegistradorNZC(String elem){
        nzc.setReg(elem);
    }
    
    public int getRegistrador(int tipoReg){
        if (tipoReg == 0)
            return Converter.stringParaInt(a.getReg());
        else if (tipoReg == 1)
            return Converter.stringParaInt(b.getReg());
        else if (tipoReg == 2)
            return Converter.stringParaInt(x.getReg());
        else
        {
            System.out.println("Erro, registrador inválido!");
            System.exit(1);
            return 0;
        }
    }
    
    public void setRegistrador(int dado, int tipoReg){
        if (tipoReg == 0)
            a.setReg(Converter.paraStringBin8(dado));
        else if (tipoReg == 1)
            b.setReg(Converter.paraStringBin8(dado));
        else if (tipoReg == 2)
            x.setReg(Converter.paraStringBin8(dado));
        else
        {
            System.out.println("Erro, registrador inválido!");
            System.exit(1);
        }
    }
    
    public void setPalavra(String endereco, int dado){
        
        mem.setDado(endereco, dado);
    }
    
    public void setPalavraDireto(String endereco, int registrador){
        mem.setDado(endereco, getRegistrador(registrador));
    }
    
    public void setPalavraIndireto(String endereco, int registrador){
        mem.setDadoInd(endereco, getRegistrador(registrador));
    }
    
    public String getPalavra(int indice){
        return mem.getDado(indice);
    }
}
