/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pdvdesktop;

import javax.swing.JFrame;
import view.ViewPrincipal;
/**
 *
 * @author Marlene Juliana
 */
public class PDVDesktop {

    public static void main(String[] args) {
        ViewPrincipal view = new ViewPrincipal();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);       
    }
}
