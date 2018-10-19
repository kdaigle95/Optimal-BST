/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs317.optimal.bst;

/**
 *
 * @author Kyle
 */
public class CS317OptimalBSTMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OptimalBST bst = new OptimalBST();
        bst.createTree();
        //bst.printRoots();
        //bst.printValues();
        bst.writeFile();
    }
    
}
