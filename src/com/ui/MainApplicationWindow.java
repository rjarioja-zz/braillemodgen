package com.ui;
import java.io.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.extraction.Keyphrase;
import com.extraction.ReferenceReader;
import com.extraction.Scorer;

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

public class MainApplicationWindow extends javax.swing.JFrame {
	private static final long serialVersionUID = 3750182651672097343L;                
    private javax.swing.JButton BrowseCourseOutline;
    private javax.swing.JButton CancelButton;
    private javax.swing.JPanel CourseOutlineGroup;
    private javax.swing.JTextField CourseOutlinePathText;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JButton ReferenceMaterialBrowse;
    private javax.swing.JPanel ReferenceMaterialGroup;
    private javax.swing.JTextField ReferenceMaterialPathText;
    private javax.swing.JButton SeeOutputButton;
    private javax.swing.JPanel StatusGroup;
    private javax.swing.JTextArea StatusTextBox;
    private javax.swing.JScrollPane jScrollPane1;                   
    
	JFileChooser referenceMaterial_browseFiles = new JFileChooser();
    JFileChooser courseOutline_browseFiles = new JFileChooser();
    
    Timer timer = new Timer();
    
    ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
    
    File courseOutline_file;
    File referenceMaterial_file;
    
    boolean CourseOutlineValid = false;
    boolean ReferenceMaterialValid = false;
    
    String StatusText = null;
    String FinalOutput = null;

    public MainApplicationWindow() { initComponents(); }                          

    private void initComponents() {

        ReferenceMaterialGroup = new javax.swing.JPanel();
        ReferenceMaterialPathText = new javax.swing.JTextField();
        ReferenceMaterialBrowse = new javax.swing.JButton();
        CourseOutlineGroup = new javax.swing.JPanel();
        CourseOutlinePathText = new javax.swing.JTextField();
        BrowseCourseOutline = new javax.swing.JButton();
        StatusGroup = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StatusTextBox = new javax.swing.JTextArea();
        GenerateButton = new javax.swing.JButton();
        SeeOutputButton = new javax.swing.JButton();
        
        SeeOutputButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		SeeOutputActionPerformed(arg0);
        	}
        });
        
        SeeOutputButton.setEnabled(false);
        CancelButton = new javax.swing.JButton();

        setIconImage(img.getImage());
        setTitle("Braille Module Generator v.1.0");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ReferenceMaterialGroup.setBorder(javax.swing.BorderFactory.createTitledBorder("Reference Material"));

        ReferenceMaterialPathText.setEditable(false);
        ReferenceMaterialPathText.setBackground(new java.awt.Color(255, 255, 255));

        ReferenceMaterialBrowse.setText("Browse...");
        ReferenceMaterialBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReferenceMaterialBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReferenceMaterialGroupLayout = new javax.swing.GroupLayout(ReferenceMaterialGroup);
        ReferenceMaterialGroup.setLayout(ReferenceMaterialGroupLayout);
        ReferenceMaterialGroupLayout.setHorizontalGroup(
            ReferenceMaterialGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReferenceMaterialGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReferenceMaterialGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReferenceMaterialPathText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReferenceMaterialGroupLayout.createSequentialGroup()
                        .addGap(0, 189, Short.MAX_VALUE)
                        .addComponent(ReferenceMaterialBrowse)))
                .addContainerGap())
        );
        ReferenceMaterialGroupLayout.setVerticalGroup(
            ReferenceMaterialGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReferenceMaterialGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReferenceMaterialPathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReferenceMaterialBrowse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CourseOutlineGroup.setBorder(javax.swing.BorderFactory.createTitledBorder("Course Outline"));
        CourseOutlineGroup.setPreferredSize(new java.awt.Dimension(300, 108));

        CourseOutlinePathText.setEditable(false);
        CourseOutlinePathText.setBackground(new java.awt.Color(255, 255, 255));

        BrowseCourseOutline.setText("Browse...");
        BrowseCourseOutline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseCourseOutlineActionPerformed(evt);
            }
        });
        
        CancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});

        javax.swing.GroupLayout CourseOutlineGroupLayout = new javax.swing.GroupLayout(CourseOutlineGroup);
        CourseOutlineGroup.setLayout(CourseOutlineGroupLayout);
        CourseOutlineGroupLayout.setHorizontalGroup(
            CourseOutlineGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseOutlineGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CourseOutlineGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CourseOutlinePathText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CourseOutlineGroupLayout.createSequentialGroup()
                        .addGap(0, 189, Short.MAX_VALUE)
                        .addComponent(BrowseCourseOutline)))
                .addContainerGap())
        );
        CourseOutlineGroupLayout.setVerticalGroup(
            CourseOutlineGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseOutlineGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CourseOutlinePathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BrowseCourseOutline)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        StatusGroup.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        StatusTextBox.setEditable(false);
        StatusTextBox.setColumns(50);
        StatusTextBox.setRows(5);
        StatusTextBox.setLineWrap(true);
        jScrollPane1.setViewportView(StatusTextBox);

        javax.swing.GroupLayout StatusGroupLayout = new javax.swing.GroupLayout(StatusGroup);
        StatusGroup.setLayout(StatusGroupLayout);
        StatusGroupLayout.setHorizontalGroup(
            StatusGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatusGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        StatusGroupLayout.setVerticalGroup(
            StatusGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatusGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        GenerateButton.setText("Generate...");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerfomred(evt);
            }
        });

        SeeOutputButton.setText("See Output...");
        CancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReferenceMaterialGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CourseOutlineGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(StatusGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(GenerateButton)
                        .addGap(18, 18, 18)
                        .addComponent(SeeOutputButton)
                        .addGap(18, 18, 18)
                        .addComponent(CancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(StatusGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(CourseOutlineGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ReferenceMaterialGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(SeeOutputButton)
                    .addComponent(GenerateButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void BrowseCourseOutlineActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Normal Text Files (*.txt)", "txt", "text");
            courseOutline_browseFiles.setFileFilter(filter);
            if (evt.getSource() == BrowseCourseOutline) {
                int returnVal = courseOutline_browseFiles.showOpenDialog(MainApplicationWindow.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    courseOutline_file = courseOutline_browseFiles.getSelectedFile();
                    CourseOutlineValid = true;
                }
            }
        CourseOutlinePathText.setText(courseOutline_file.getPath());
    }                                                   

    private void ReferenceMaterialBrowseActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Normal Text Files (*.txt)", "txt", "text");
            referenceMaterial_browseFiles.setFileFilter(filter);
            if (evt.getSource() == ReferenceMaterialBrowse) {
                int returnVal = referenceMaterial_browseFiles.showOpenDialog(MainApplicationWindow.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    referenceMaterial_file = referenceMaterial_browseFiles.getSelectedFile();
                    ReferenceMaterialValid = true;
                }
            }
        ReferenceMaterialPathText.setText(referenceMaterial_file.getPath());
    }                                                       

    public void GenerateButtonActionPerfomred(ActionEvent evt) {
        Console.redirectOutput(StatusTextBox);
    	timer.schedule(new TimerTask() {
    		public void run() {
	        	if (CourseOutlineValid && ReferenceMaterialValid) {
	        		try {
	        			Keyphrase.start(courseOutline_file.getPath());
	        		} catch (IOException e1) {	e1.printStackTrace(); }
	        		
	        		try {
	        			try {
	    					ReferenceReader.parseFile(referenceMaterial_file.getPath());
	    				} catch (IOException e) { e.printStackTrace(); }
	        		} catch (ClassNotFoundException e) { e.printStackTrace(); }
	        		Scorer.start();
	        	}
        		FinalOutput = Scorer.output;
        		SeeOutputButton.setEnabled(true);
        		timer.cancel();
    		};
    	}, 500);
    }
    
    public void SeeOutputActionPerformed(java.awt.event.ActionEvent evt) {
    	Output out = new Output();
    	out.setText(FinalOutput);
    	Output.runner();
    }

    public static void main(String args[]) {
    	try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainApplicationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApplicationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApplicationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApplicationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApplicationWindow().setVisible(true);
            }
        });
    }
}

class Console implements Runnable {
    JTextArea displayPane;
    BufferedReader reader;

    private Console(JTextArea displayPane, PipedOutputStream pos) {
        this.displayPane = displayPane;

        try {
            PipedInputStream pis = new PipedInputStream( pos );
            reader = new BufferedReader( new InputStreamReader(pis) );
        } catch(IOException e) {}
    }

    public void run() {
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
            	// displayPane.replaceSelection( line + "\n" );
                displayPane.append( line + "\n" );
                displayPane.setCaretPosition(displayPane.getDocument().getLength());
            }
            System.err.println("Error: Whales not found");
        } catch (IOException ioe) {
        	System.out.print("");
        }
    }

    public static void redirectOutput(JTextArea displayPane) {
        Console.redirectOut(displayPane);
        Console.redirectErr(displayPane);
    }

    public static void redirectOut(JTextArea displayPane) {
        PipedOutputStream pos = new PipedOutputStream();
        System.setOut( new PrintStream(pos, true) );

        Console console = new Console(displayPane, pos);
        new Thread(console).start();
    }

    public static void redirectErr(JTextArea displayPane) {
        PipedOutputStream pos = new PipedOutputStream();
        System.setErr( new PrintStream(pos, true) );

        Console console = new Console(displayPane, pos);
        new Thread(console).start();
    }
}