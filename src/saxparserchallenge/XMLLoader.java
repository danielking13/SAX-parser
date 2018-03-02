/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserchallenge;

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author danielking
 */
public class XMLLoader {   
    public static Node load(File xmlCourseFile) throws Exception {
        Node node = new Node();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            //defines an anonymous inner class
            DefaultHandler handler = new DefaultHandler() {
                Children child = null;
                boolean isRoot = true;
                Stack elements = new Stack();
                Stack nodeStack = new Stack();
                
                //qName is the tag name, attribute is the attribute such as id
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    //push the encountered element on the element stack
                    elements.push(qName);
                    System.out.println(qName);
                    
                    //skip over the root element/node
                    if(isRoot) {
                        isRoot = false;
                        nodeStack.push(child);
                        return;
                    } 
                    
                    child = new Children();
                    if(attributes != null && attributes.getLength() == 1) {
                        child.setAttribute(attributes.getValue(0));
                    }
                    
                    if(qName != null) {
                        child.setTagName(qName);
                    }
                    nodeStack.push(child);
                    
//                    if (qName.equalsIgnoreCase("student")) {
//                        student = new Student();
//                        String idString = attributes.getValue("id");
//                        if (idString != null) {
//                            int id = 0;
//                            try {
//                                id = Integer.parseInt(idString);
//                            } catch (NumberFormatException e) {
//                                throw new SAXException("student id in xml could not be converted to an int");
//                            }
//                            student.setId(id);
//                        }
//                    }
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
//                    System.out.println("Popping element: " + elements.peek().toString());
                    elements.pop();
                    Children childObject = (Children)nodeStack.pop();
                    if(childObject != null) {
                        node.addChildren(childObject);
                        child = null;
                    }
                }
                
                //working with the data inside the tag/ or node
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    
                    String data = new String(ch, start, length).trim();
                    if(data.length() == 0) {
                        return; //if the data is just spaces exit
                    }
                    if(child != null) {
                        child.setData(data);
                    }                   
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return node; 
    }
    
    
}
