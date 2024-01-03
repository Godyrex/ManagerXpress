import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";
import {RegisterRequest} from "../models/RegisterRequest";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  registerRequest : RegisterRequest = {};
  message = '';


  constructor(
    private authService : AuthenticationService,
    private router : Router
  ) {
  }
  async ngOnInit() {
    if (await this.authService.isTokenValid()) {
      this.router.navigate(['dashboard']);
    }
  }
registerUser(){
  this.message = '';
    this.authService.register(this.registerRequest)
        .subscribe(data => {
            console.log(data)
          this.message ='Account created successfully\\nYou will be redirected to the Login page in 3 seconds';
            setTimeout(() => {
              this.router.navigate(['login']);
            }, 3000)
          },
          error => {
          console.log(error)
            this.message =error.error.message;
        });

}


}


