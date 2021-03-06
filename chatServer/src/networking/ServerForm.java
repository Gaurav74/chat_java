package networking;

import java.io.*;
import java.net.*;

public class ServerForm extends javax.swing.JFrame {

    ServerSocket server;
    Socket client;
    ObjectInputStream input;
    ObjectOutputStream output;
    String send = null;
    String receive = null;

    public ServerForm() {
        initComponents();
        userText.setEditable(false);

    }
    public void start() {

        try {
            server = new ServerSocket(1254);
            chatWindow.append("Server started...");
            chatWindow.append("\n" + "Waiting for client...");

                client = server.accept();
                chatWindow.append("\n" + "Client Connected...");

                output = new ObjectOutputStream(client.getOutputStream());

                input = new ObjectInputStream(client.getInputStream());
                chatWindow.append("\n" + "Start chat now...");
                userText.setEditable(true);

            do {
                receive = (String) input.readObject();
                if (receive != null) {
                    chatWindow.append("\n" + "Client: " + receive);
                }
            } while (!receive.equals("END"));
            }
         catch (Exception e) {
            chatWindow.append(e.getMessage());
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatWindowScrollPane = new javax.swing.JScrollPane();
        chatWindow = new javax.swing.JTextArea();
        userText = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Machine");

        chatWindow.setEditable(false);
        chatWindow.setColumns(20);
        chatWindow.setRows(5);
        chatWindowScrollPane.setViewportView(chatWindow);

        userText.setToolTipText("Type your message here ");
        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatWindowScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatWindowScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed
                    send = userText.getText();
        try {
            output.writeObject(send);
            output.flush();
            chatWindow.append("\n" + "Me: " + send);
            userText.setText("");
        } catch (IOException e) {
            chatWindow.append(e.getMessage());
        }
    }//GEN-LAST:event_userTextActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed
    public static void main(String args[]) {
        ServerForm s = new ServerForm();
        s.setVisible(true);
        s.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JTextArea chatWindow;
    private javax.swing.JScrollPane chatWindowScrollPane;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
