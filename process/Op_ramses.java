package ramses;

/**
 *
 * @author moni e leti
 */
public class Op_ramses {
//duvida 1: o que é o registrador de índices (X)?
//duvida 2: pq no modo imediato os jumps são reconhecidos como NOPs? que    
    
    /*public Op_ramses(Memoria memoria){
        this.memoria = memoria;
    }*/
    
    private int nop(int indice){
        Ramses.incrementaPC();
    return indice + 1;
    }
    
    private int str(int indice, int tipoEnd, int tipoReg){
        int registrador = Ramses.getRegistrador(tipoReg);
        
        if (tipoEnd == 0){
            Ramses.setPalavra(Ramses.getPalavra(indice), registrador);
        } else if (tipoEnd == 1){
            Ramses.setPalavraIndireto(Ramses.getPalavra(indice), registrador);
        } else if (tipoEnd == 3){
            Ramses.setPalavra(Ramses.getPalavra(indice) + Ramses.getRegistrador(2), registrador);
        } else 
            return -3;
        
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int ldr(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        Ramses.setRegistrador(operando, tipoReg);
        
        atualizaEstadosNZ(operando);
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int add(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = Ramses.getRegistrador(tipoReg) + operando;
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = Ramses.getRegistradorNZC();
        String novaString = new String();
        
        novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
        Ramses.setRegistradorNZC(novaString);
                
        Ramses.incrementaPC();
        return indice + 1;
        
    }
    
    private int or(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = Ramses.getRegistrador(tipoReg) || operando;
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int and(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = Ramses.getRegistrador(tipoReg) && operando;
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int not(int indice, int tipoReg){
        int registrador = Ramses.getRegistrador(tipoReg);
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        Ramses.incrementaPC();
        return indice + 1;        
    }
    
    private int sub(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = Ramses.getRegistrador(tipoReg) - operando;
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = Ramses.getRegistradorNZC();
        String novaString = new String();
        
        if (registrador < 0){
            novaString = atualizaC.substring(0, 2)+'1'+atualizaC.substring(3, 8);
            Ramses.setRegistradorNZC(novaString);
        }else{
            novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
            Ramses.setRegistradorNZC(novaString);
        }
            
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int jmp(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        
        if (tipoEnd == 2){
            Ramses.incrementaPC();
            return indice + 1;
        } else {
            Ramses.setPC(endereco);
            return endereco;
        }
            
 
    }
    
    private int jn(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = Ramses.getRegistradorNZC();

        if (verificaNZC.charAt(0) == '1'){
            if (tipoEnd == 2){
                Ramses.incrementaPC();
                return indice + 1;
            } else {
                Ramses.setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(0) == '0'){
            Ramses.incrementaPC();
            return indice + 1;
        }
    }
    
    private int jz(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = Ramses.getRegistradorNZC();
               
        
        if (verificaNZC.charAt(1) == '1'){
            if (tipoEnd == 2){
                Ramses.incrementaPC();
                return indice + 1;
            } else {
                Ramses.setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(1) == '0'){
            Ramses.incrementaPC();
            return indice + 1;
        }
    }
    
    private int jc(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = Ramses.getRegistradorNZC();
               
        
        if (verificaNZC.charAt(2) == '1'){
            if (tipoEnd == 2){
                Ramses.incrementaPC();
                return indice + 1;
            } else {
                Ramses.setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(2) == '0'){
            Ramses.incrementaPC();
            return indice + 1;
        }
    
    }
    
    private int jsr(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        setEndereco(endereco, Ramses.getPC()); //seta o pc no endereço
        Ramses.setPC(endereco + 1);
        
        return endereco + 1;
    }
    
    private int neg(int indice, int tipoReg){
        int registrador = -1*Ramses.getRegistrador(tipoReg);
        Ramses.setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = Ramses.getRegistradorNZC();
        String novaString = new String();
        
        if (registrador < 0){
            novaString = atualizaC.substring(0, 2)+'1'+atualizaC.substring(3, 8);
            Ramses.setRegistradorNZC(novaString);
        }else{
            novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
            Ramses.setRegistradorNZC(novaString);
        }
            
        
        Ramses.incrementaPC();
        return indice + 1;    
    }
    
    private int shr(int indice, int tipoReg){
        int registrador = Ramses.getRegistrador(tipoReg);
        registrador = registrador >> 1;
        Ramses.setRegistrador(registrador, tipoReg);
        
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = Ramses.getRegistradorNZC();
        String novaString = new String();
        
        novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
        Ramses.setRegistradorNZC(novaString);
        
        Ramses.incrementaPC();
        return indice + 1;
    }
    
    private int hlt(int indice){
        
        Ramses.incrementaPC();
        return indice + 1; 
        
    }
    
    private int identificaReg(int indice){
        String copiaPalavra = Ramses.getPalavra(N);  //no lugar de Ramses estava a expressao this.memoria
        copiaPalavra = copiaPalavra.substring(4, 6);
        
        if(copiaPalavra.equalsIgnoreCase("00")){
            return 0; //registrador A (RA)
        } else if (copiaPalavra.equalsIgnoreCase("01")){
            return 1; //registrador B (RB)
        } else if (copiaPalavra.equalsIgnoreCase("10")){
            return 2; //registrador X (RX)
        } else 
            return -1; //registrador indefinido
    }
    
    private int identificaModo(int indice){
        String copiaPalavra = Ramses.getPalavra(N);  //no lugar de Ramses estava a expressao this.memoria
        copiaPalavra = copiaPalavra.substring(6, 9);
        
        if(copiaPalavra.equalsIgnoreCase("00")){
            return 0; //endereçamento direto
        } else if (copiaPalavra.equalsIgnoreCase("01")){
            return 1; //endereçamento indireto
        } else if (copiaPalavra.equalsIgnoreCase("10")){
            return 2; //endereçamento imediato
        } else if (copiaPalavra.equalsIgnoreCase("11")){
            return 3; //endereçamento indexado
        } else
            return -2;
    }
    
    private int recebeOperando (int indice, int modo){
        int operando = 0;
        
        if (modo == 0) { //direto
            int i = Converter.stringParaInt(Ramses.getPalavra(indice)); //no lugar de Ramses estava a expressao this.memoria
            operando = Converter.stringParaInt(Ramses.getPalavra(i));
        } else if (modo == 1){ //indireto
            int i = Converter.stringParaInt(Ramses.getPalavra(indice));
            operando = Converter.stringParaInt(Ramses.getPalavraInd(i));
        } else if (modo == 2){ //imediato
            operando = Converter.stringParaInt(Ramses.getPalavra(indice));
        } else if (modo == 3){ //indexado
            int i = Converter.stringParaInt(Ramses.getPalavra(indice)) + Ramses.getRegistrador(2);
            operando = Converter.stringParaInt(Ramses.getPalavra(i));            
        }
        
        return operando;          
    }
    
    private void atualizaEstadosNZ(int registrador){
        String atualizaNZC = Ramses.getRegistradorNZC();
        String novaString = new String();
        
        if (registrador == 0){
            novaString = "01"+atualizaNZC.substring(2, 8);
        }else if (registrador < 0){
            novaString = "10"+atualizaNZC.substring(2, 8);
        }else if (registrador > 0){
            novaString = "00"+atualizaNZC.substring(2, 8);
        }
        
        Ramses.setRegistradorNZC(novaString);        
    }
}
