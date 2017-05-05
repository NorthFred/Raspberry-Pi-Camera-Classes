
/**
 * Test Class for the Raspberry Pi Camera v.2
 * 
 * http://www.raspberrypi-spy.co.uk/2013/05/capturing-hd-video-with-the-pi-camera-module/
 * 
 * @Frederik Vermaete 
 * @v.0.0.5/ 2017.05.05
 */

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class TestCamera {
   
    public static void main(String[] args) throws IOException {
        
        long startTime = 0;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Raspberry Pi v.2 Test Class");
        System.out.println("***************************");
        System.out.println();
        
        System.out.print("Enter video resolution: width (pixels): ");   
        int resWidth = scanner.nextInt();
        System.out.print("Enter video resolution: height (pixels): ");
        int resHeight = scanner.nextInt();
        System.out.println();        
        
        System.out.print("Enter the framerate (fps): ");
        int fps = scanner.nextInt();
        System.out.println();
        
        System.out.print("Enter the duration in ms (e.g. 10000): ");
        int duration = scanner.nextInt();
        System.out.println();        
        
        System.out.println("Overview of the settings:");           
        System.out.println("Resolution: " + resWidth + "x" + resHeight);
        System.out.println("Duration (ms): " + duration);
        System.out.println("fps: " + fps);
        System.out.println();
        
        
        try {
            
            System.out.println("Recording started...");
            startTime = System.currentTimeMillis();
            
            String executeLine = "raspivid -w " + resWidth + " -h " + resHeight + " -fps " + fps + " -n -t " + duration + " -o -";
            Process systemprocess = Runtime.getRuntime().exec(executeLine);
            BufferedInputStream buffer = new BufferedInputStream(systemprocess.getInputStream());
            FileOutputStream streamToFile = new FileOutputStream("testvideo.h264");
            
            int data = buffer.read();
            streamToFile.write(data);
            
            while (data != -1) {
                
                data = buffer.read();
                streamToFile.write(data);
  
            }
            
            buffer.close();
            streamToFile.close();
            System.out.println("Recording stopped.");
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("Duration of recording (ms): " + (System.currentTimeMillis() - startTime));
    }
}

    
