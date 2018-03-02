/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserchallenge;

/**
 *
 * @author danielking
 */
public class Children {
    
    private String tagName = "";
    private String tagContent = "";
    private String attributes = "";
 
    public String getTagName() {
        return tagName;
    }
    public String getData() {
        return tagContent;
    }
    public String getAttribute() {
        return attributes;
    }
    public void setTagName(String qName) {
        this.tagName = qName;
    }
    public void setData(String data) {
        this.tagContent = data;
    }
    public void setAttribute(String attribute) {
        this.attributes = attribute;
    }
}
