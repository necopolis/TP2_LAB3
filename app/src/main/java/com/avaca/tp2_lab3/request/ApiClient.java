package com.avaca.tp2_lab3.request;


import android.content.Context;
import android.widget.Toast;

import com.avaca.tp2_lab3.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    public static void guardar(Context context, Usuario usuario) {
        File archivo = new File(context.getFilesDir(), "datos.obj");

        try {
            //Nodo
            FileOutputStream fo = new FileOutputStream(archivo, false);
            BufferedOutputStream buff = new BufferedOutputStream(fo);
            ObjectOutputStream output = new ObjectOutputStream(buff);
            output.writeObject(usuario);
            output.close();

        } catch (FileNotFoundException ex) {
            Toast.makeText(context.getApplicationContext(), "No se encuentra", Toast.LENGTH_LONG).show();
        } catch (IOException io) {
            Toast.makeText(context.getApplicationContext(), "No existe", Toast.LENGTH_LONG).show();
        }
    }

    public static Usuario leer(Context context) {
        File archivo = new File(context.getFilesDir(), "datos.obj");
        Usuario usuario = null;

        try {
            //Read from the stored file
            FileInputStream is = new FileInputStream(archivo);
            BufferedInputStream buff = new BufferedInputStream(is);
            ObjectInputStream input = new ObjectInputStream(buff);
            usuario = (Usuario) input.readObject();
            input.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context.getApplicationContext(), "No se encuentra el archivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(), "No existe", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context.getApplicationContext(), "No encuentra", Toast.LENGTH_LONG).show();
        }

        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass) {
        Usuario usuario = null;
        Usuario usu = leer(context);

        if (mail != null && pass != null && usu != null) {
            if (mail.equals(usu.getMail()) && pass.equals(usu.getPassword())) {
                usuario = usu;
            }
        }

        if (usuario == null) {
            Toast.makeText(context.getApplicationContext(), "Datos Invalidos", Toast.LENGTH_LONG).show();
        }

        return usuario;
    }
}