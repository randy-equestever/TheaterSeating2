package com.mycomp.process;

import java.util.List;
import java.util.Scanner;

import com.mycomp.business.TheaterSeatingService;
import com.mycomp.business.TheaterSeatingServiceImpl;
import com.mycomp.model.TheaterLayout;
import com.mycomp.model.TheaterRequest;


public class TheatreSeating {
    
    public static void main(String[] args) {
        
        String line;
        StringBuilder layout = new StringBuilder();
        StringBuilder ticketRequests = new StringBuilder();
        boolean isLayoutFinished = false;
        
        System.out.println("Please enter Theater Layout and Ticket requests and then enter 'done'.\n");
        
        Scanner input = new Scanner(System.in);

        while((line = input.nextLine()) != null && !line.equals("done")){
            
            if(line.length() == 0){
                
                isLayoutFinished = true;
                continue;
                
            }
            
            if(!isLayoutFinished){
                
                layout.append(line + System.lineSeparator());
                
            }else{
                
                ticketRequests.append(line + System.lineSeparator());
                
            }
            
        }
        
        input.close();
        
        TheaterSeatingService service = new TheaterSeatingServiceImpl();
        
        try{
        
            TheaterLayout theaterLayout = service.getTheaterLayout(layout.toString());
            
            List<TheaterRequest> requests = service.getTicketRequests(ticketRequests.toString());
            
            service.processTicketRequests(theaterLayout, requests);
            
        }catch(NumberFormatException nfe){
            
            System.out.println(nfe.getMessage());
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }

        
        
    }

}
