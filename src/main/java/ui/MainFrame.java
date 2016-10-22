package ui;

import factory.AppProperties;
import factory.ObjectCreator;
import models.SearchResponse;
import services.SearchService;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JRadioButton yahooRB;
    private JRadioButton googleRB;
    private JRadioButton bingRB;
    private JButton searchButton;
    private AppProperties appProperties;
    private JTextPane textPane;

    public MainFrame() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setBounds(350, 350, 550, 400);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        contentPane.setLayout(gbl_contentPane);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 5;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 0;
        contentPane.add(textField, gbc_textField);
        textField.setColumns(10);

        yahooRB = new JRadioButton("Yahoo", true);
        GridBagConstraints GBCyahooRB = new GridBagConstraints();
        GBCyahooRB.fill = GridBagConstraints.HORIZONTAL;
        GBCyahooRB.insets = new Insets(0, 0, 5, 5);
        GBCyahooRB.gridx = 2;
        GBCyahooRB.gridy = 1;
        contentPane.add(yahooRB, GBCyahooRB);

        googleRB = new JRadioButton("Google");
        GridBagConstraints GBCgoogleRB = new GridBagConstraints();
        GBCgoogleRB.insets = new Insets(0, 0, 5, 5);
        GBCgoogleRB.gridx = 4;
        GBCgoogleRB.gridy = 1;
        contentPane.add(googleRB, GBCgoogleRB);

        bingRB = new JRadioButton("Bing");
        GridBagConstraints GBCbingRB = new GridBagConstraints();
        GBCbingRB.insets = new Insets(0, 0, 5, 5);
        GBCbingRB.gridx = 6;
        GBCbingRB.gridy = 1;
        contentPane.add(bingRB, GBCbingRB);

        searchButton = new JButton("Search");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 3;
        contentPane.add(searchButton, gbc_btnNewButton);

        textPane = new JTextPane();
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.gridwidth = 5;
        gbc_textPane.insets = new Insets(0, 0, 5, 5);
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 2;
        gbc_textPane.gridy = 5;
        contentPane.add(textPane, gbc_textPane);
    }

    public void showRadioButton() {
        appProperties = new AppProperties();
        yahooRB.setMnemonic(KeyEvent.VK_C);
        googleRB.setMnemonic(KeyEvent.VK_M);
        bingRB.setMnemonic(KeyEvent.VK_P);

        yahooRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appProperties.alter(AppProperties.SearchEngine.Yahoo);
            }
        });

        googleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appProperties.alter(AppProperties.SearchEngine.Google);
            }
        });

        bingRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appProperties.alter(AppProperties.SearchEngine.Bing);
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(yahooRB);
        group.add(googleRB);
        group.add(bingRB);
    }

    public void buttonHandle() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean internetStatus = Utils.isInternetConnectionExist("www.google.com");

                if (!internetStatus) {
                    JOptionPane.showMessageDialog(null, "No internet connection!");
                    return;
                }

                if (textField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please insert search string!");
                    return;
                }
                SearchService searchService = ObjectCreator.getSearchEngineService();
                SearchResponse searchResponse = searchService.search(textField.getText());
                textPane.setText("Title = " + searchResponse.getTitle() + "\n" + "Result = " + searchResponse.getUrl() + "\n" + "Search engine = " + searchResponse.getProvider());
            }
        });
    }
}
