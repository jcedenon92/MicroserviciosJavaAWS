package com.aforo255.appsecurity.controllers;

import java.util.List;
 
import com.aforo255.appsecurity.cross.JwtTokenCross;
import com.aforo255.appsecurity.dtos.AuthRequest;
import com.aforo255.appsecurity.dtos.AuthResponse;
import com.aforo255.appsecurity.models.AccessModel;
import com.aforo255.appsecurity.services.AuthService;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;
    @Autowired
    private JwtTokenCross jwtTokenCross;  
  
 
    // @GetMapping()
    // public String get(){
    //     return "Running Get ...";
    // }
    @GetMapping()
    public List<AccessModel> get() {
      return authService.getAccess();
    }
  
     
    // @PostMapping()
    // public String post(){
    //     return "Running Post ...";
    // }
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody AuthRequest request) throws Exception {
 
      if (!authService.validatedCredentials(request.getUserName(), request.getPassword())) {
        return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
      }
 
      AuthResponse response = new AuthResponse(jwtTokenCross.generateToken(request), request.getUserName(), "1d");
      return ResponseEntity.ok(response);
    }
  
}