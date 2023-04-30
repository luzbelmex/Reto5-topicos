package carrera;

import javax.swing.JTextArea;

public class ThreadRunner extends Thread {
    
    private Runner runner;
    private JTextArea textArea;
    
    
    public ThreadRunner (Runner runner, JTextArea textArea ) {
        this.runner = runner;
        this.textArea = textArea;
    }
    
    @Override
    public void run(){
        
        try {
            Thread.sleep(runner.getSpeed() * 1000);
            textArea.append(runner.getName() + " - Tiempo: " + runner.getSpeed() + " segundos \n");
        } catch (InterruptedException e){
            System.out.println("Error: " + e);
        }
        
    }
}
