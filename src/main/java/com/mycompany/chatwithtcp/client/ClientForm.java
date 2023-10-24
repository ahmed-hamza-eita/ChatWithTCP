package com.mycompany.chatwithtcp.client;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author hamza
 */
public class ClientForm extends javax.swing.JFrame {

    Socket client;
    byte[] buffer = new byte[1024];

    public ClientForm() {
        initComponents();
        setIPAddress();
    }

    private void setIPAddress() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String ip = localhost.getHostAddress();
            txtIp.setText(ip);
        } catch (UnknownHostException e) {
        }
    }

    private void updateGUI(boolean isConnected) {
        btnConnectServer.setEnabled(!isConnected);
        btnSend.setEnabled(isConnected);
        txtConnected.setText(isConnected ? "Connected" : "Disconnected");
        txtConnected.setForeground(isConnected ? Color.GREEN : Color.RED);
    }

    private void connectToServer() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    String ipAddress = txtIp.getText();
                    int portNum = Integer.parseInt(txtPort.getText());

                    client = new Socket(ipAddress, portNum);
                    updateGUI(true);
                    writeLog("Connected To Server ..........");
                    waitingForData(client);
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                    updateGUI(false);
                }
                return null;
            }
        };

        worker.execute();
    }
 private void waitingForData(Socket client) {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());

            while (true) {
                String msg = in.readUTF();
                writeLog("Server Said: " + msg);
            }
        } catch (IOException e) {
            updateGUI(false);
        }
    }

    private void sendMessage() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String msg = txtSendMessage.getText();
            out.writeUTF(msg);
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logLable = new javax.swing.JLabel();
        sendToServer = new javax.swing.JLabel();
        txtSendMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        clientlable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        serverPortLable = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        clientPortlable = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnConnectServer = new javax.swing.JButton();
        txtConnected = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logLable.setText("Log: ");

        sendToServer.setText("Send Message to Server : ");

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

        clientlable.setBackground(new java.awt.Color(255, 204, 0));
        clientlable.setForeground(new java.awt.Color(51, 0, 255));
        clientlable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientlable.setText("Client");

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        serverPortLable.setText("IP");

        txtIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIpActionPerformed(evt);
            }
        });

        clientPortlable.setText("Port");

        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });

        btnConnectServer.setText("Connect to Server");
        btnConnectServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectServerActionPerformed(evt);
            }
        });

        txtConnected.setText("connected");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clientlable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSend)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(logLable)
                                            .addComponent(sendToServer)
                                            .addComponent(txtSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnConnectServer)
                                        .addGap(55, 55, 55))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clientPortlable)
                                    .addComponent(serverPortLable))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtConnected)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clientlable)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPortLable)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientPortlable)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnectServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logLable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendToServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtConnected)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSendMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSendMessageActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        sendMessage();
    }//GEN-LAST:event_btnSendActionPerformed

    private void txtIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIpActionPerformed

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPortActionPerformed

    private void btnConnectServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectServerActionPerformed
        // TODO add your handling code here:
        connectToServer();
    }//GEN-LAST:event_btnConnectServerActionPerformed

    private void writeLog(String msg) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append("Server Said: " + msg + System.lineSeparator());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnectServer;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel clientPortlable;
    private javax.swing.JLabel clientlable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logLable;
    private javax.swing.JLabel sendToServer;
    private javax.swing.JLabel serverPortLable;
    private javax.swing.JLabel txtConnected;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtSendMessage;
    // End of variables declaration//GEN-END:variables
}
