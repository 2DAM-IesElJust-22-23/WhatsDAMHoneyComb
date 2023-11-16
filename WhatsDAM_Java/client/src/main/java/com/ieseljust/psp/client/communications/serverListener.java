package com.ieseljust.psp.client.communications;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import com.ieseljust.psp.client.ViewModel;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.json.JSONArray;

public class serverListener implements Runnable {

    /*
     * Aquesta classe s'encarrega de gestionar els broadcasts que fa el servidor
     * cap als clients subscrits a les seues publicacions.
     * Implementarà doncs un servei que escoltarà en un port aleatori el que
     * li envia el servidor de missatgeria i ho processarà de forma adeqüada.
     * 
     */
    ViewModel vm;

    public serverListener(ViewModel vm) {
        this.vm = vm;
    }

    int listenerPort = CurrentConfig.listenPort();

    private void procesarPeticioClient(Socket socketClient) throws IOException {

        InputStreamReader isr;
        OutputStream os = socketClient.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        PrintWriter pw = new PrintWriter(writer);       

        try {
            isr = new InputStreamReader(socketClient.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String linia = br.readLine();

            procesarMissatge(linia);
            /*while ((linia = br.readLine()) != null) {
                procesarMissatge(linia);
            }*/

        

            pw.write("{'status':'ok'}");
            pw.flush();
            pw.close();
            br.close();
            writer.close();
            os.close();
            isr.close();
            
            socketClient.close();
        } catch (IOException ex) {
            System.out.println("Error al procesar la petición");
        }
    }

    private void procesarMissatge(String msg) {
        System.out.println(msg);    
    
        JSONObject jObject = new JSONObject(msg);
        String tipo="";
        if(jObject.has("type"))
            tipo=jObject.getString("type");

        switch (tipo) {
            case "userlist":

                JSONArray userList = jObject.getJSONArray("content");
                ArrayList<String> userArrayList = new ArrayList<String>();
                for (int i = 0; i < userList.length(); i++) {
                    userArrayList.add(userList.getString(i));
                }
                userArrayList.addAll(vm.getLlistaUsuaris());
                vm.updateUserList(userArrayList);

                break;
            case "message":
                String user = jObject.getString("user");
                String content = jObject.getString("content");
                Message mensaje=new Message(user, content);
                vm.addMessage(mensaje);
                break;
            /*default:
                System.out.println("Tipo desconocido.");*/
        }
    }

    @Override
    public void run() {
        // 1. Crear un socket de tipus servidor que escolte pel port de recepció de
        // missatges
        ServerSocket listener = null;
        try {
            // Creem el socket en un  port determinat pel sistema
            // i el guardem a listenPort.
            listener = new ServerSocket(0);
            CurrentConfig.setlistenPort(listener.getLocalPort());

        } catch (IOException e) {
            System.out.println("El port " + listenerPort + " està ocupato és inaccessible.");
            return;
        }

        // TO-DO
        // 2. Iniciem un bucle infinit a l'espera de rebre connexions
        // Quan arribe una connexió la processrem de manera adeqüada
        // Les peticions que podem rebre seran de tipus:
        // {"type": "userlist", "content": [Llista d'usuaris]}, amb un JSONArray amb la llista d'usuaris.
        // {"type": "message", "user":"usuari", "content": "Contingut del missatge" }
        // És interessant implementar un mètode a banda per processat aquestes línies
        // però no cal que creem un fil a propòsit per atendre cada missatge, ja que
        // no som un servidor com a tal, i el que estem fent aci, és mantindre un 
        // canal de recepció només amb el servidor.
        
        while (true) {

            try {
                Socket socketClient = listener.accept();

                Thread filClient = new Thread(() ->{
                    try {
                        procesarPeticioClient(socketClient);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
                
                filClient.start();

            } 
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
