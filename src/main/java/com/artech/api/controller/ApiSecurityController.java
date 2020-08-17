package com.artech.api.controller;

import com.artech.api.reponse.LoginResponse;
import com.artech.api.reponse.JwtRequest;
import com.artech.api.security.JWTUtils;
import com.artech.api.security.JwtUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api")
public class ApiSecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtTokenUtil;

    @Autowired
    private JwtUserDetailServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if (!userDetails.isAccountNonLocked()) {
            return new ResponseEntity<>(new LoginResponse(HttpStatus.OK.value(),false,null),HttpStatus.BAD_REQUEST);

        }
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setAccess_token(token);
        loginResponse.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
