import { Component, OnInit } from '@angular/core';
import {TableResponse} from "../models/TableResponse";
import {TableService} from "../service/table.service";
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  userTables: TableResponse[] = [];

  constructor(private tableService: TableService,
              private router : Router,
              private authService : AuthenticationService) { }

  async ngOnInit() {
    this.loadUserTables();
      if (!await this.authService.isTokenValid()) {
        this.router.navigate(['login']);
      }

  }
message : string = '';
  loadUserTables() {
    this.tableService.getAllUserTables().subscribe(
      (tables: TableResponse[]) => {
        this.userTables = tables;
      },
      (error) => {
        console.error('Error fetching user tables', error);
      }
    );
  }
  removeUserTable(idTable : string){
    this.tableService.deleteTable(idTable).subscribe(
      response => {
        if(response){
          this.reloadPage();
        }else {
          this.reloadPage();
          console.error(`Unexpected HTTP status: ${response.status}`);
        }
      },
      (error) => {
        console.error('Error deleting table', error);
      }
    )
  }
  reloadPage(): void {
    window.location.reload();
  }
}
