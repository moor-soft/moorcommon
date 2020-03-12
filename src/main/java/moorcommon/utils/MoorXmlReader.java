package moorcommon.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoorXmlReader {
    private Document doc;

    public MoorXmlReader(String input) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(new InputSource(new StringReader(input)));
        doc.getDocumentElement().normalize();
    }

    public Map<String, String> readTagValues(String parentTag, List<String> attributes, List<String> attributeValues, List<String> tags) {

        Map<String, String> resultDictionary = new HashMap<>();

        Node resultNode = readNode(parentTag, attributes, attributeValues);

        for (String tag : tags) {
            if(resultNode != null) {
                NodeList childList = resultNode.getChildNodes();
                if (childList != null) {
                    for (int j = 0; j < childList.getLength(); j++) {
                        Node childNone = childList.item(j);
                        if (childNone.getNodeName().equals(tag)) {
                            resultDictionary.put(tag, childNone.getTextContent());
                        }
                    }
                }
            }
        }

        return resultDictionary;
    }

    Node readNode(String tagName, List<String> attributes, List<String> attributeValues) {

        NodeList nList = doc.getElementsByTagName(tagName);

        for (int i = 0; i < nList.getLength(); i++) {
            boolean isFound = true;
            for (int j = 0; j < attributes.size(); j++) {


                String attributeValue = "";
                for (int k = 0; k < nList.item(i).getAttributes().getLength(); k++) {
                    Node node = nList.item(i).getAttributes().item(k);
                    if (node.getNodeName().equals(attributes.get(j))) {
                        attributeValue = node.getTextContent();
                        break;
                    }
                }

                if (!attributeValue.equals(attributeValues.get(j))) {
                    isFound = false;
                }
            }
            if (isFound) {
                return nList.item(i);
            }
        }
        return null;
    }

    List<String> readTagValues(String tagName) {
        List<String> resultDictionary = new ArrayList<>();
        NodeList nList = this.doc.getElementsByTagName(tagName);
        for(int i = 0; i < nList.getLength(); ++i) {
            resultDictionary.add(nList.item(i).getTextContent());
        }
        return resultDictionary;
    }

}
