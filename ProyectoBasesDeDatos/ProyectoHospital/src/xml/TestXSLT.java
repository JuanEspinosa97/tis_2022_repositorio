package xml;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TestXSLT {
    public static void main(String[] args) {
    	String sourcePath = "./xml/xslt_ejemplo.xml";
    	String xsltPath = "./xml/xslt_ejemplo1.xslt";
    	String resultDir = "./xml/xslt_ejemplo1.html";

    	TransformerFactory tFactory = TransformerFactory.newInstance();
    	try {
    	    Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
    	    transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
}
