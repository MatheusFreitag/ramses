/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramses;

/**
 *
 * @authors Felipe Tadeu Trinhain Palermo
 *          Frederico de Oliveira Sedrez
 */

public class Ramses {
     //Declaração dos componentes da máquina: Memória e Rgistradores
    private static Memoria mem = new Memoria();
    private static PC pc = new PC();
    private static Registrador a = new Registrador();
    private static Registrador b = new Registrador();
    private static Registrador x = new Registrador();
    private static Registrador nzc = new Registrador();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
       //inicializa valores dos Registradores com valor 0 em 8 bits
       inicializaRegs();
        
        //inicializa interface gráfica
        Macros interf = new Macros();
        interf.run();
        
    }
    
    private static void inicializaRegs(){
        a.setReg("00000000");
        b.setReg("00000000");
        x.setReg("00000000");
        pc.setReg("00000000");
        nzc.setReg("00000000");
    }
    
    public static int getPC(){
        return Converter.stringParaInt(pc.getReg());
    }
    
    public static void setPC(int endereço){
        pc.setReg(Converter.paraStringBin8(endereço));
    }
    
    public static void incrementaPC(){
        pc.incrementa();
    }
    
     public static String getRegistradorNZC(){
        return nzc.getReg();
    }
    
    public static void setRegistradorNZC(String elem){
        nzc.setReg(elem);
    }
    
    public static int getRegistrador(int tipoReg){
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
    
    public static void setRegistrador(int dado, int tipoReg){
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
    
    public static void setPalavra(String endereco, int registrador){
        
        mem.setDado(endereco, getRegistrador(registrador));
    }
    
    public static void setPalavraIndireto(String endereco, int registrador){
        mem.setDadoInd(endereco, getRegistrador(registrador));
    }
    
    public static String getPalavra(int indice){
        return mem.getDado(indice);
    }
}
