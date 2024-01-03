import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../models/LoginRequest";
import {LoginResponse} from "../models/LoginResponse";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginRequest : LoginRequest = {};
  loginResponse : LoginResponse = {};
  message = '';
  async ngOnInit() {
    if (await this.authService.isTokenValid()) {
      this.router.navigate(['dashboard']);
    }
  }
  constructor(
    private authService : AuthenticationService,
    private router : Router,
    private token: TokenStorageService
  ) {
  }


  login(){
    this.authService.login(this.loginRequest).subscribe(
      response => {
        this.loginResponse = response;
        this.token.saveToken(this.loginResponse.accessToken as string);
        this.token.saveUser(response);
        this.router.navigate(['dashboard']);
    },
      error => {
        console.log(error)
        this.message ="wrong username or password";
      });

  }

}
