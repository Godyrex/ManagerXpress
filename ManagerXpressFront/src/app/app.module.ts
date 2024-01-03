import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {authInterceptorProviders} from "./service/auth.interceptor";
import { CreateComponent } from './dashboard/table/create/create.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    CreateComponent
  ],
    imports: [
        BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule
    ],
  providers: [HttpClient,authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
