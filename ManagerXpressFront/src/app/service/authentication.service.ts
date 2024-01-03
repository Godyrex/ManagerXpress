import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequest} from "../models/RegisterRequest";
import {LoginRequest} from "../models/LoginRequest";
import {LoginResponse} from "../models/LoginResponse";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
private baseUrl = 'http://localhost:8080/api/auth';
  constructor(private http: HttpClient,
              private token: TokenStorageService) { }
  register(registerRequest : RegisterRequest){
return this.http.post(`${this.baseUrl}/signup`,registerRequest)
  }
  login(loginRequest : LoginRequest){
    return this.http.post<LoginResponse>(`${this.baseUrl}/signin`,loginRequest);
  }
  validateToken(authToken : string) {
    return this.http.post(`${this.baseUrl}/validate-token`, authToken);
  }
  async isTokenValid():Promise<boolean> {
    if (this.token.getToken()) {
      try {
        const isValid = await this.validateToken(this.token.getToken() as string).toPromise();
        if(isValid){
          return true
        }else {
          return false
        }
      } catch (error) {
        // Handle any errors, if needed
        console.error('Token validation error:', error);
        return false;
      }
    } else {
      return false;
    }
  }

}
