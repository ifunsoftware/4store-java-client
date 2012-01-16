package uk.co.magus.fourstore.examples;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import uk.co.magus.fourstore.client.Store;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

import static com.hp.hpl.jena.rdf.model.ModelFactory.createDefaultModel;

public class InsertModel {
    
    public static void main(String[] args) throws IOException {

        Store store = new Store("http://aphreet.4s.ifunsoftware.com");

        String xmlModel = "<rdf:RDF\n" +
                "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "    xmlns:j.0=\"http://s4.ifunsoftware.com/2011/code-rdf/1.0#\" >\n" +
                "\n" +
                "    <rdf:Description rdf:about=\"http://s4.ifunsoftware.com/source/com.asemantics.rdfcoder.model.java.JavadocHandler/method:fieldJavadoc/param:entry\">\n" +
                "      <j.0:type rdf:resource=\"http://s4.ifunsoftware.com/source/com.asemantics.rdfcoder.parser.javadoc.FieldJavadoc\"/>\n" +
                "      <j.0:parent rdf:resource=\"http://s4.ifunsoftware.com/source/com.asemantics.rdfcoder.model.java.JavadocHandler/method:fieldJavadoc\"/>\n" +
                "    </rdf:Description>\n" +
                "\n" +
                "</rdf:RDF>";

        Model model = createDefaultModel();
        model.read(new StringReader(xmlModel), null);
        store.insertModel(model, "test");

        System.out.println("After insert");
        System.out.println(store.query("SELECT * FROM <test> WHERE {\n" +
                " ?s ?p ?o\n" +
                "} LIMIT 10"));

        store.deleteModel(model, "test");


        System.out.println("After delete");
        System.out.println(store.query("SELECT * FROM <test> WHERE {\n" +
                " ?s ?p ?o\n" +
                "} LIMIT 10"));

    }
}
