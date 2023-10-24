package com.mycompany.chatwithtcp.server;

import com.mycompany.chatwithtcp.client.ClientForm;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class ServerForm extends javax.swing.JFrame {

   private ServerSocket server;
    private final ArrayList<Socket> clients = new ArrayList<>();


    public ServerForm() {
        initComponents();

    }

    private void startServer() {
        int portNum = Integer.parseInt(txtServerPort.getText());
        try {
            server = new ServerSocket(portNum);
            updateGUI(true);

            new Thread(() -> {
                while (true) {
                    try {
                        Socket client = server.accept();
                        clients.add(client);
                        readyForData(client);
                    } catch (IOException e) {
                    }
                }
            }).start();
        } catch (IOException e) {
        }
    }

    private void readyForData(Socket client) {
        new Thread(() -> {
            try {
                DataInputStream in = new DataInputStream(client.getInputStream());

                while (true) {
                    String msg = in.readUTF();
                    writeLog("Client Said: " + msg);
                }
            } catch (IOException e) {
                 clients.remove(client);
                writeLog("Client Disconnected");
            }
        }).start();
    }



    private void sendMessage(String message) {
        for (Socket client : clients) {
            if (client.isConnected()) {
                try {
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    out.writeUTF(message);
                } catch (IOException e) {
                 }
            }
        }
    }

    private void updateGUI(boolean isConnected) {
        btnStartServer.setEnabled(!isConnected);
        btnSend.setEnabled(isConnected);
        txtConnected.setText(isConnected ? "Connected" : "Disconnected");
        txtConnected.setForeground(isConnected ? Color.GREEN : Color.RED);
    }

    private void writeLog(String msg) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append(msg + System.lineSeparator());
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendToServer = new javax.swing.JLabel();
        txtSendMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        serberLabel = new javax.swing.JLabel();
        serverPortLable = new javax.swing.JLabel();
        txtServerPort = new javax.swing.JTextField();
        logLable = new javax.swing.JLabel();
        btnNewClient = new javax.swing.JButton();
        btnStartServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        txtConnected = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sendToServer.setText("Send Message to All Client : ");

        txtSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSendMessageActionPerformed(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        serberLabel.setBackground(new java.awt.Color(102, 102, 102));
        serberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serberLabel.setText("Server");

        serverPortLable.setText("Server Port : ");

        txtServerPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerPortActionPerformed(evt);
            }
        });

        logLable.setText("Log: ");

        btnNewClient.setText("New Client");
        btnNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClientActionPerformed(evt);
            }
        });

        btnStartServer.setText("Start Server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        txtConnected.setText("connected");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(serverPortLable)
                                    .addGap(26, 26, 26)
                                    .addComponent(txtServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(logLable)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnStartServer))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnNewClient)
                                        .addComponent(sendToServer))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSend))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtConnected, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serberLabel)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPortLable)
                    .addComponent(txtServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logLable)
                    .addComponent(btnStartServer))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNewClient)
                .addGap(12, 12, 12)
                .addComponent(sendToServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(txtConnected)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSendMessageActionPerformed

    }//GEN-LAST:event_txtSendMessageActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
       sendMessage(txtSendMessage.getText());
    }//GEN-LAST:event_btnSendActionPerformed

    private void txtServerPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServerPortActionPerformed

    private void btnNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClientActionPerformed
        // TODO add your handling code here:
        new ClientForm().setVisible(true);
    }//GEN-LAST:event_btnNewClientActionPerformed

    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
         startServer();
        //  connectToServerAsync();
    }//GEN-LAST:event_btnStartServerActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ServerForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewClient;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnStartServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logLable;
    private javax.swing.JLabel sendToServer;
    private javax.swing.JLabel serberLabel;
    private javax.swing.JLabel serverPortLable;
    private javax.swing.JLabel txtConnected;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtSendMessage;
    private javax.swing.JTextField txtServerPort;
    // End of variables declaration//GEN-END:variables
}
