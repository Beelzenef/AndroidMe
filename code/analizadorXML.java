package com.example.app1.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.example.app1.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Analizador de XML
 */

public class CheckXML {

    public static String analizar(String texto) throws XmlPullParserException, IOException {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Leyendo XML:\n ");
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_DOCUMENT)
                cadena.append("START DOCUMENT \n");
            if (eventType == XmlPullParser.START_TAG)
                cadena.append("START TAG: " + xpp.getName() + "\n");
            if (eventType == XmlPullParser.TEXT)
                cadena.append("CONTENT: " + xpp.getText() + "\n");
            if (eventType == XmlPullParser.END_TAG)
                cadena.append("END TAG: " + xpp.getName() + "\n");
            eventType = xpp.next();
        }

        cadena.append("END DOCUMENT");
        return cadena.toString();
    }

    public static String analizarXmlGet(Context c) throws XmlPullParserException, IOException {
        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder stringResultante = new StringBuilder();
        Double suma = 0.0;
        int contador = 0;

        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xrp.getName().equals("nombre")) {
                        esNombre = true;
                    }

                    if (xrp.getName().equals("nota")) {
                        esNota = true;

                        for (int i = 0; i < xrp.getAttributeCount(); i++) {
                            stringResultante.append(xrp.getAttributeName(i) + ": " + xrp.getAttributeValue(i) + "\n");
                            contador++;
                        }
                    }

                    if (xrp.getName().equals("observaciones")) {

                    }
                    break;
                case XmlPullParser.TEXT:

                    if (esNombre) {
                        stringResultante.append("Nombre: " + xrp.getText() + "\n");
                    }
                    else if (esNota) {
                        suma += Double.parseDouble(xrp.getText());
                        stringResultante.append("Nota: " + xrp.getText() + "\n");
                    }
                    else {
                        stringResultante.append("Observaciones: " + xrp.getText() + "\n");
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (xrp.getName().equals("nombre"))
                        esNombre = false;
                    if (xrp.getName().equals("nota"))
                        esNota = false;
                    if (xrp.getName().equals("alumno"))
                        stringResultante.append("\n");
                    break;
            }
            eventType = xrp.next();
        }

        stringResultante.append("Media de todas las notas : " + String.format("%.2f", suma / contador));
        xrp.close();
        return stringResultante.toString();
    }

    public static String analizarXmlNextText(Context c) throws XmlPullParserException, IOException {

        StringBuilder stringResultante = new StringBuilder();

        double suma = 0.0;
        double getNota = 0;
        int contador = 0;


        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xrp.getName().equals("nombre")) {
                        stringResultante.append("Nombre: " + xrp.nextText() + "\n");
                    }

                    if (xrp.getName().equals("nota")) {

                        for (int i = 0; i < xrp.getAttributeCount(); i++) {
                            stringResultante.append(xrp.getAttributeName(i) + ": " + xrp.getAttributeValue(i) + "\n");
                            contador++;
                        }

                        getNota = Double.parseDouble(xrp.nextText());
                        stringResultante.append("Nota: " + Double.toString(getNota) + "\n");
                        suma += getNota;
                    }
                    if (xrp.getName().equals("observaciones")) {
                        stringResultante.append("Observaciones: " + xrp.nextText() + "\n\n");
                    }
                    break;
            }
            eventType = xrp.next();
        }

        stringResultante.append("Media de todas las notas : " + String.format("%.2f", suma / contador));
        xrp.close();
        return stringResultante.toString();
    }

    public static String analizarRSS(File file) throws NullPointerException, XmlPullParserException, IOException {

        boolean dentroItem = false;

        StringBuilder builder = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));

        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("item"))
                        dentroItem = true;

                    if (dentroItem && xpp.getName().equals("title"))
                    {
                        builder.append("Post: " + xpp.next() + "\n");
                        dentroItem = false;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        return builder.toString();
    }

}