/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse423lab03id17101482;

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
    public static int zone = -5;
    
    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-1000, -1000, 1000, 1000); // xmin, ymin, xmax, ymax
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
        
        DrawMPL(gl, 0, 93, -25, 0); // Zone : 5
        DrawMPL(gl, 10, -10, 60, -50); // Zone : 7
        DrawMPL(gl, -30, -10, -100, -40); // Zone : 4
        DrawMPL(gl, 97, 0, 0, -157); // Zone : 5
        
    }
    
    public void DrawMPL(GL2 gl, int x1, int y1, int x2, int y2){
        zone = findZone(x1, y1, x2, y2);
        
        int startPairInZone0 [] = convertToZone0(x1, y1, zone);
        int endPairInZone0 [] = convertToZone0(x2, y2, zone);
        
        MidPoint(gl, startPairInZone0[0], startPairInZone0[1], endPairInZone0[0], endPairInZone0[1]);  
    }  
    
    public static int findZone(int x1, int y1, int x2, int y2){
        int dx = x2 - x1;
        int dy = y2 - y1;

        if(Math.abs(dx) > Math.abs(dy)){
            if(dx>0 && dy>0) zone = 0;
            else if(dx<0 && dy>0) zone = 3;
            else if(dx<0 && dy<0) zone = 4;
            else if(dx>0 && dy<0) zone = 7;
        }else{
            if(dx>0 && dy>0) zone = 1;
            else if(dx<0 && dy>0) zone = 2;
            else if(dx<0 && dy<0) zone = 5;
            else if(dx>0 && dy<0) zone = 6;
        }
        
        return zone;
    }
    
    public static int[] convertToZone0(int X, int Y, int zone){
        
        int xZ = 0;
        int yZ = 0;
        
        int pairZ [] = new int[2];
        
        if(zone==1){
            xZ = Y;
            yZ = X;
        }else if(zone==2){
            xZ = Y;
            yZ = -X;
        }else if(zone==3){
            xZ = -X;
            yZ = Y;
        }else if(zone==4){
            xZ = -X;
            yZ = -Y;
        }else if(zone==5){
            xZ = -Y;
            yZ = -X;
        }else if(zone==6){
            xZ = -Y;
            yZ = X;
        }else if(zone==7){
            xZ = X;
            yZ = -Y;
        }
        
        pairZ[0] = xZ;
        pairZ[1] = yZ;
        
        return pairZ;
    }
    
    public static int[] OriginalZone(int X, int Y, int zone){
        
        int xORZ = 0;
        int yORZ = 0;
        
        int pairORZ [] = new int[2];
        
        if(zone==1){
            xORZ = Y;
            yORZ = X;
        }else if(zone==2){
            xORZ = -Y;
            yORZ = X;
        }else if(zone==3){
            xORZ = -X;
            yORZ = Y;
        }else if(zone==4){
            xORZ = -X;
            yORZ = -Y;
        }else if(zone==5){
            xORZ = -Y;
            yORZ = -X;
        }else if(zone==6){
            xORZ = Y;
            yORZ = -X;
        }else if(zone==7){
            xORZ = X;
            yORZ = -Y;
        }
        
        pairORZ[0] = xORZ;
        pairORZ[1] = yORZ;
        
        return pairORZ;
    }
    
    public static void MidPoint(GL2 gl, int x1, int y1, int x2, int y2){
        gl.glPointSize(5.0f);
        gl.glColor3d(1, 1, 1);
        
        gl.glLineWidth(3.0f);
        gl.glColor3d(1, 1, 1);
        
        gl.glBegin(GL2.GL_LINES);
        
        int dy = y2 - y1;
        int dx = x2 - x1;
        
        int D = (2*dy) - dx;
        int NE = 2*(dy-dx);
        int E = 2*dy;
        
        int x = x1;
        int y = y1;
        
        while(x<=x2){
            int PairInOriginalZone [] = OriginalZone(x, y, zone);
            gl.glVertex2d(x, y);
            
            x++;
            if(D>0){
                y++;
                D = D + NE;
            }else{
                D = D + E;
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

public class CSE423Lab03ID17101482 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        ThirdGLEventListener t = new ThirdGLEventListener();
        
        glcanvas.addGLEventListener(t);
        glcanvas.setSize(1000, 1000);
        
        //creating frame
        final JFrame frame = new JFrame("17101482_ShoaibAhmedDipu_Section05_Lab03_CSE423");
        
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}