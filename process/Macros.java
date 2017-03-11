package ramses;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matheusfreitag
 */
public class Macros extends javax.swing.JFrame {
    public File selFile;
    private ArrayList vetorInicial = new ArrayList();
    /**
     * Creates new form MacroInterface
     */
    private static Ramses ramses = new Ramses();
    public Macros() {
        initComponents();
    }
    
    public ArrayList retornaVetorInicial(){
        if(vetorInicial.size() == 0)
        {
            vetorInicial.add("Teste");
            return vetorInicial;
                   }
        else
            return vetorInicial;
    }

    public static String geradorDeVetor(File file) throws IOException{
        // array list arrayPalavras
        ArrayList arrayPalavras = new ArrayList();
        // é criada uma string onde vai ser guardado o conteudo do arquivo
        // em seguida o arquivo é lido
        String stringFromArquivo = "";
        FileReader filereader = new FileReader(file);
        BufferedReader bufferreader = new BufferedReader(filereader);
        // aqui é onde o conteudo do arquivo é guardado em uma string
        while(bufferreader.ready()){
          stringFromArquivo += bufferreader.readLine();
          stringFromArquivo+= " ";
        }

        return stringFromArquivo;
    }

    public static String identificadorEnderecamento(String s){
        switch(s){
            case "DIR":
                return "00";
            case "IND":
                return "01";
            case "IME":
                return "10";
            case "IDX":
                return "11";
            default:
                return "XX";
        }
    }

    public /*static*/ ArrayList construtorDePalavras(ArrayList list){
        int i               = 0;
        int index           = 0;
        String extraZero    = "";
        String codigo       = "";
        String registrador  = "";
        String modo         = "";
        String endereco     = "";
        String palavra      = "";

        ArrayList<String> stringDePalavras = new ArrayList<>();

        while(i < list.size()){
            switch(list.get(i).toString()){
                case "NOP":
                    codigo = "0000";
                    registrador = "00";
                     modo = "00";
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add(palavra);
                    i += 1;
                    break;

                case "STR":
                    codigo = "0001";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 4;
                    break;

                case "LDR":
                    codigo = "0010";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 4;
                    break;


                case "ADD":
                    codigo = "0011";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    index+=1;
                    i += 4;
                    break;

                case "OR":
                    codigo = "0100";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 4;
                    break;

                case "AND":
                    codigo = "0101";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    index+=1;
                    i += 4;
                    break;

                case "NOT":
                    codigo = "0110";
                    registrador = list.get(i+1).toString();
                    modo = "00";
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    i += 3;
                    break;

                case "SUB":
                    codigo = "0111";
                    registrador = list.get(i+1).toString() ;
                    modo = identificadorEnderecamento(list.get(i+3).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+2).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 4;
                    break;

                case "JMP":
                    codigo = "1000";
                    registrador = "00" ;
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+1).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add(palavra);
                    stringDePalavras.add(endereco);
                    i += 3;
                    break;

                case "JN":
                    codigo = "1001";
                    registrador = "00" ;
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+1).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    index+=1;
                    i += 3;
                    break;

                case "JZ":
                    codigo = "1010";
                    registrador = "00" ;
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+1).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 3;
                    break;

                case "JC":
                    codigo = "1011";
                    registrador = "00" ;
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+1).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 3;
                    break;

                case "JSR":
                    codigo = "1100";
                    registrador = "00" ;
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    endereco = Integer.toBinaryString(Integer.parseInt(list.get(i+1).toString()));
                    if (endereco.length() < 8){
                        for(int j = 0; j< (8 - endereco.length()); j++){
                            extraZero += "0";
                        }
                    }
                    endereco = extraZero + endereco;
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    stringDePalavras.add(endereco);
                    i += 3;
                    break;

                case "NEG":
                    codigo = "1101";
                    registrador = list.get(i+1).toString();
                    modo = identificadorEnderecamento(list.get(i+2).toString());
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add(palavra);
                    i += 3;
                    break;

                case "SHR":
                    codigo = "1110";
                    registrador = list.get(i+1).toString();
                    modo = "00";
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    i += 3;
                    break;

                case "HLT":
                    codigo = "1111";
                    registrador = "00";
                    modo = "00";
                    palavra = codigo + registrador + modo;
                    stringDePalavras.add( palavra);
                    i += 2;
                    break;
            }
            extraZero = "";
        }
        return stringDePalavras;
    }
    
    private void inicializaMemoria(ArrayList list){
        System.out.println(list.size());
        for (int i=0;i<list.size();i++)
        {
            ramses.setPalavra(Converter.paraStringBin8(i), Converter.stringParaInt((String) list.get(i)));
           System.out.println((String) list.get(i));
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRun = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        txtArquivo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMemoria = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        txtC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtN = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtZ = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtMensagens = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Sair");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo"));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        txtArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Memória"));

        txtMemoria.setColumns(20);
        txtMemoria.setRows(5);
        jScrollPane1.setViewportView(txtMemoria);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Registradores"));

        txtC.setColumns(10);

        jLabel5.setText("N");

        txtN.setColumns(10);
        txtN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNActionPerformed(evt);
            }
        });

        jLabel6.setText("Z");

        txtPC.setColumns(10);
        txtPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPCActionPerformed(evt);
            }
        });

        jLabel3.setText("PC");

        jLabel4.setText("C");

        txtZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(17, 17, 17)
                        .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensagens"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMensagens, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtMensagens, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnRun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1)
                    .addComponent(btnRun))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArquivoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser;
        chooser = new JFileChooser();
        File f = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(f);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showOpenDialog(btnAbrir);
        selFile = chooser.getSelectedFile();

        txtArquivo.setText(selFile.getAbsolutePath());
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        // TODO add your handling code here:
        try {
            String line = geradorDeVetor(selFile);

            ArrayList<String> vetorPrograma = new ArrayList<>(Arrays.asList(line.split("\\s+")));
            //Aqui ele imprime a palavra
            vetorInicial = construtorDePalavras(vetorPrograma);
            System.out.println(vetorInicial);
            inicializaMemoria(vetorInicial);
        } catch (IOException ex) {
            Logger.getLogger(Macros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnRunActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void txtPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPCActionPerformed

    private void txtNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNActionPerformed

    private void txtZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String Args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Macros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Macros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Macros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Macros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Macros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnRun;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField txtArquivo;
    private javax.swing.JTextField txtC;
    private javax.swing.JTextArea txtMemoria;
    private javax.swing.JTextField txtMensagens;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtPC;
    private javax.swing.JTextField txtZ;
    // End of variables declaration//GEN-END:variables
}
