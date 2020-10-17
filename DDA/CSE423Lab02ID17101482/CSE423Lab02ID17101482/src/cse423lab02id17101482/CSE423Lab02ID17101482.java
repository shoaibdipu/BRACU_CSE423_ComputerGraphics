/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse423lab02id17101482;

/**
 *
 * @author Dipu
 */

import java.util.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;
import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {
    
    /**
     * Interface to the GLU library.
     */
    
    public GLU glu;
    
    //public int x1;
    //public int y1;
    //public int x2;
    //public int y2;
    
    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-100, -50, 50, 100); // xmin, ymin, xmax, ymax
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }
    
    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
        
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        
        //DDA(gl, x1, y1, x2, y2);
        
        DDA(gl, -97, 0, 0, 157);
        DDA(gl, -87, 0, 0, 147);
        DDA(gl, -77, 0, 0, 137);
        DDA(gl, -30, 0, 0, 180);
        DDA(gl, -20, 0, 0, 170);
        
    }
    
    public void DDA(GL2 gl, double x1, double y1, double x2, double y2){
        
        gl.glPointSize(5.0f);
        gl.glColor3d(1, 1, 1);
        
        gl.glLineWidth(3.0f);
        gl.glColor3d(1, 1, 1);
        
        gl.glBegin(GL2.GL_LINES);
        
        double dy = y2 - y1;
        double dx = x2 - x1;
        
        double m = dy / dx;
        
        if((m>-1 && m<0) || (m>0 && m<1)){ // x will control the loop
            
            if(x1<x2){ // incremental case 
                while(x1<x2){ 
                    gl.glVertex2d(x1, y1); 
                    x1++;
                    y1 = y1 + m;
                }
            }else if(x1>x2){ // decremental case
                while(x1>x2){ 
                    gl.glVertex2d(x1, y1);
                    x1--;
                    y1 = y1 - m; 
                }
            }
            
        }else if(m<-1 || m>1){ // y will control the loop
            
            if(y1<y2){ // incremental case
                while(y1<y2){ 
                    gl.glVertex2d(x1, y1); 
                    y1++;
                    x1 = x1 + (1.0/m);
                }
            }else if(y1>y2){ // decremental case
                while(y1>y2){ 
                    gl.glVertex2d(x1, y1);
                    y1--;
                    x1 = x1 - (1.0/m);
                }
            }
            
        }  
        
        gl.glEnd();
    }  
    
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }
    
    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }
    
    public void dispose(GLAutoDrawable arg0) {
    }
}

public class CSE423Lab02ID17101482 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        /*
        // Taking User Input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Value Of x1 : ");
        int x1 = sc.nextInt();
        System.out.println("Enter The Value Of y1 : ");
        int y1 = sc.nextInt();
        System.out.println("Enter The Value Of x2 : ");
        int x2 = sc.nextInt();
        System.out.println("Enter The Value Of y2 : ");
        int y2 = sc.nextInt();
        
        //ThirdGLEventListener t = new ThirdGLEventListener();
        
        // Setting The Values Of x1, y1, x2, y2
        t.x1 = x1;
        t.y1 = y1;
        t.x2 = x2;
        t.y2 = y2;
        */
        
        ThirdGLEventListener t = new ThirdGLEventListener();
        
        glcanvas.addGLEventListener(t);
        glcanvas.setSize(400, 400);
        
        //creating frame
        final JFrame frame = new JFrame("17101482_ShoaibAhmedDipu_Section05_Lab02_CSE423");
        
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}