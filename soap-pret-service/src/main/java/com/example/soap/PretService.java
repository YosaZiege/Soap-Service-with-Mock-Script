package com.example.soap;

import com.example.soap.pret.PretRequest;
import com.example.soap.pret.PretResponse;
import org.springframework.stereotype.Service;

@Service
public class PretService {

    public PretResponse checkEligibility(PretRequest request) {
        PretResponse response = new PretResponse();
        
        String nom = request.getNom();
        int age = request.getAge();
        String nationalite = request.getNationalite();
        
        if (age < 18) {
            response.setIsEligible(false);
            response.setReason("Age pas approprie");
            return response;
        }
        
        if (!isMarocan(nationalite)) {
            response.setIsEligible(false);
            response.setReason("Nationalite doit etre marocaine");
            return response;
        }
        
        response.setIsEligible(true);
        response.setReason("Eligible pour le pret");
        
        return response;
    }
    
    private boolean isMarocan(String nationalite) {
        if (nationalite == null) {
            return false;
        }
        
        String lowerNationalite = nationalite.toLowerCase().trim();
        return lowerNationalite.equals("marocan") || 
               lowerNationalite.equals("marocain") || 
               lowerNationalite.equals("marocaine") ||
               lowerNationalite.equals("maroc") ||
               lowerNationalite.equals("moroccan") ||
               lowerNationalite.equals("morocco");
    }
}