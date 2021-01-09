package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.util.Locale;

public class XMLWorker {
    static public void generateTripFile(String fileName, Trips trips) throws TransformerException, ParserConfigurationException {
        Path tripFile = Path.of(fileName);
        var document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        fillFile(document, trips);
        createFile(tripFile, document);
        System.out.println("File saved!");
    }

    static private Document fillFile(Document doc, Trips trips){
        Element root = doc.createElement("routes");
        root.setAttribute("xmlns:xsi",                      "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:noNamespaceSchemaLocation",  "http://sumo.dlr.de/xsd/routes_file.xsd");
        doc.appendChild(root);

        trips.trips.forEach(e -> addTrip(e, doc, root));
        return doc;
    }

    private static void addTrip(Trips.Trip trip, Document document, Element root){
        Element record = document.createElement("trip");
        Locale.setDefault(new Locale("en"));
        record.setAttribute("id",       String.valueOf(trip.id));
        record.setAttribute("depart",   String.format("%.2f", trip.depart));
        record.setAttribute("from",     String.valueOf(trip.startId));
        record.setAttribute("to",       String.valueOf(trip.endId));

        root.appendChild(record);
    }

    private static void createFile(Path file, Document doc) throws TransformerException {
        var transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file.toFile());

        transformer.transform(source, result);
    }
}
