/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserchallenge;

import java.util.ArrayList;

/**
 *
 * @author danielking
 */
public class Node {
    private ArrayList<Children> children;
    
    public Node() {
        children = new ArrayList<>();
    }
    
    public ArrayList<Children> getChildren() {
        return children;
    }
    
    public void addChildren(Children child) {
        children.add(child);
    }
}
