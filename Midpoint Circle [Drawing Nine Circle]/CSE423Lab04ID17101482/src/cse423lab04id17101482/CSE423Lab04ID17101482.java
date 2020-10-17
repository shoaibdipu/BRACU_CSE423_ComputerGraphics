/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse423lab04id17101482;

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
    
    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld){
        
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-1000, -1000, 1000, 1000); // xmin, ymin, xmax, ymax
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -125.0, 125.0);
        
    }
    
    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable){
        
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        
        double r = 120.0;
        double minR = r / 2;
        
        //Master Circle
        drawCircle(gl, r, 0.0, 0.0);
        
        //Circles whose centers are on X-Axis
        drawCircle(gl, minR, minR, 0);
        drawCircle(gl, minR, -minR, 0);
        
        //Circles whose centers are on Y-Axis
        drawCircle(gl, minR, 0, minR);
        drawCircle(gl, minR, 0, -minR);
        
        //Circle whose center is on First Quadrant
        drawCircle(gl, minR, Math.sqrt((Math.pow(minR, 2.0))/2.0), Math.sqrt((Math.pow(minR, 2.0))/2.0));
        
        //Circle whose center is on Second Quadrant
        drawCircle(gl, minR, -(Math.sqrt((Math.pow(minR, 2.0))/2.0)), Math.sqrt((Math.pow(minR, 2.0))/2.0));
        
        //Circle whose center is on Third Quadrant
        drawCircle(gl, minR, -(Math.sqrt((Math.pow(minR, 2.0))/2.0)), -(Math.sqrt((Math.pow(minR, 2.0))/2.0)));
        
        //Circle whose center is on Fourth Quadrant
        drawCircle(gl, minR, Math.sqrt((Math.pow(minR, 2.0))/2.0), -(Math.sqrt((Math.pow(minR, 2.0))/2.0)));
          
    }
    
    private void drawCircle(GL2 gl, double r, double cX, double cY){
        
        double x = 0.0;
        double y = r;
        double d = 1 - r;
        
        draw8Way(gl, x, y, cX, cY);
        
        while(x<y){
            
            if(d>0){ // choose SE
                d = d + ((2*x) - (2*y) + 5);
                x++;
                y--;  
            }else{ // choose E
                d = d + ((2*x) + 3);
                x++;
            }
            draw8Way(gl, x, y, cX, cY); 
        }
        
    }  
    
    private void draw8Way(GL2 gl, double x, double y, double cX, double cY){
        
        gl.glPointSize(1.2f);
        gl.glColor3d(1, 1, 1);
        
        gl.glLineWidth(1.0f);
        gl.glColor3d(1, 1, 1);
        
        gl.glBegin(GL2.GL_POINTS);
        
        gl.glVertex2d(y+cY, x+cX); // Zone 0
        gl.glVertex2d(x+cX, y+cY); // Zone 1
        
        gl.glVertex2d(-x+cX, y+cY); // Zone 2
        gl.glVertex2d(-y+cY, x+cX); // Zone 3
        
        gl.glVertex2d(-y+cY, -x+cX); // Zone 4
        gl.glVertex2d(-x+cX, -y+cY); // Zone 5
        
        gl.glVertex2d(x+cX, -y+cY); // Zone 6
        gl.glVertex2d(y+cY, -x+cX); // Zone 7
        
        gl.glEnd();
        
    }
    
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height){
    }
    
    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged){
    }
    
    public void dispose(GLAutoDrawable arg0){
    }
}


public class CSE423Lab04ID17101482{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        
        //Getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        
        //The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        ThirdGLEventListener t = new ThirdGLEventListener();
        
        glcanvas.addGLEventListener(t);
        glcanvas.setSize(1500, 1500);
        
        //Creating frame
        final JFrame frame = new JFrame("17101482_ShoaibAhmedDipu_Section05_Lab04_CSE423");
        
        //Adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}