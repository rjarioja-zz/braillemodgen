package com.ui;

import javax.swing.ImageIcon;

/**
*
* @author Ryan Arioja, Theodore Merin
* Braille Module Generator v.1
* Copyright (C) 2014  Ryan Arioja, Theodore Merin, Kevin Zurita
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
* 
*/

public class Output extends javax.swing.JFrame {

	private static final long serialVersionUID = -3700998044051947509L;
	private static String finalString = "";
	ImageIcon img = new ImageIcon("icon.jpg");
	
    public Output() {
        initComponents();
    }

    private void initComponents() {
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        BrailleOutputText = new javax.swing.JTextArea();
        SummarizedText = new javax.swing.JTextArea();
        Close = new javax.swing.JButton();
        
        setIconImage(img.getImage());
        setTitle("Braille Module Generator v.1.0 - Output");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BrailleOutputText.setColumns(40);
        BrailleOutputText.setFont(new java.awt.Font("Braille Outline", 0, 20)); // NOI18N
        BrailleOutputText.setRows(5);
        jScrollPane1.setViewportView(BrailleOutputText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Braille Output", jPanel1);

        SummarizedText.setColumns(20);
        SummarizedText.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        SummarizedText.setRows(5);
        SummarizedText.setLineWrap(true);
        SummarizedText.setWrapStyleWord(true);
        jScrollPane2.setViewportView(SummarizedText);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Summarized Text", jPanel2);

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Close)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addGap(18, 18, 18)
                .addComponent(Close)
                .addContainerGap())
        );

        pack();
    }                        

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) { System.exit(0); }                                     
    public static void runner() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Output.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Output.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Output.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Output.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Output().setVisible(true);
                BrailleOutputText.setText(brailleTextProcess(finalString));
                SummarizedText.setText(finalString);
            }
        });
    }
    
    public void setText(String s) { finalString = s; }
    
    public static String brailleTextProcess(String text) {
    	StringBuilder builder = new StringBuilder();
    	String lines[] = text.split("\n");
    	String newline = "";
    	for (int i = 0; i < lines.length; i++) {
    		if (lines[i].contains("\n")) lines[i].replace('\n', ' ');
    		int index = 0;
    		builder.append(newline);
			newline = "\n";
    		String words[] = lines[i].split("\\s");
    		while (index < lines[i].length()) {
    			double limit = 40;
    			for (char c : lines[i].toCharArray()) if (Character.isUpperCase(c)) limit -= .5;
                int bufferlength = 0;
                for (int j = 0; j < words.length; j++) {
                	bufferlength += words[j].length() + 1;
                	index += words[j].length() + 1;
                	builder.append(words[j] + " ");
                	if (bufferlength > limit) {
                		bufferlength = 0;
                		builder.append(newline);
                	}
                }
                if (index > lines[i].length()) break;
            }
    		
    	}

        return builder.toString();
    }
                    
    private static javax.swing.JTextArea BrailleOutputText;
    private static javax.swing.JTextArea SummarizedText;
    private javax.swing.JButton Close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane3;                   
}
