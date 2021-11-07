package com.ashenafee.csemachine;

import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class UserInterface {

    private JFrame frame;
    private JPanel panel;
    private JPanel contentPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField doiEntry;
    private JOptionPane cseCitationWindow;
    private JButton citationButton;
    private JPanel buttonPanel;

    public UserInterface() {
        // Create frame/panel objects
        this.frame = new JFrame("CSE Citation Machine");
        this.panel = new JPanel();
        this.contentPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.titlePanel = new JPanel();
        this.titleLabel = new JLabel("CSE Citation Machine", SwingConstants.CENTER);
        this.instructionLabel = new JLabel("Please enter your article DOI below.", SwingConstants.CENTER);
        this.doiEntry = new JTextField(1);
        this.citationButton = new JButton("Get Citation");
        this.cseCitationWindow = new JOptionPane();

        // Set frame properties
        configureUi();

        // Add widgets to panels
        addToUi();

        // Add panels to frame
        this.frame.getContentPane().add(panel);
        
        // Set frame close behavior
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void configureUi() {
        // Configure panels
        this.panel.setPreferredSize(new java.awt.Dimension(400, 200));
        this.panel.setLayout(new java.awt.GridLayout(0, 1));
        this.buttonPanel.setLayout(new java.awt.GridLayout(2, 0));
        this.contentPanel.setPreferredSize(new java.awt.Dimension(400, 50));
        this.contentPanel.setLayout(new java.awt.GridLayout(0, 2));
        this.titlePanel.setPreferredSize(new java.awt.Dimension(400, 100));
        this.titlePanel.setLayout(new java.awt.GridLayout(0, 1));
        this.frame.setResizable(false);
        this.frame.setTitle("CSE Citation Machine");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Configure elements
        this.titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        this.doiEntry.setToolTipText("Enter the DOI of the article you wish to cite");
        this.cseCitationWindow.setSize(200, 200);
            // // https://doi.org/10.1016/j.fitote.2016.05.016
        this.citationButton.setToolTipText("Click to get the article citation");
        this.citationButton.addActionListener(e -> {
            Article article = new Article(this.doiEntry.getText());
            JTextArea cseCitation = new JTextArea(article.citeAsCSE());
            cseCitation.setEditable(false);
            cseCitation.setSize(200, 200);
            cseCitation.setLineWrap(true);
            this.cseCitationWindow.showMessageDialog(null, cseCitation, "CSE Citation", JOptionPane.PLAIN_MESSAGE);
            this.cseCitationWindow.setVisible(true);
            this.doiEntry.setText("");
        });
    }

    public void addToUi() {
        // Add elements to panels
        this.titlePanel.add(titleLabel);
        this.titlePanel.add(instructionLabel);
        this.contentPanel.add(doiEntry);
        this.contentPanel.add(citationButton);
        this.panel.add(titlePanel);
        this.panel.add(contentPanel);
    }

    public void buildUi() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.buildUi();
    }
    
}
