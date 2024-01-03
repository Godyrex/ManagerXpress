import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {TableRequest} from "../../../models/TableRequest";
import {TableService} from "../../../service/table.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent {
  tableRequest : TableRequest = {columns: {}, tableName: ""};
  messagehead ='';
  message = '';
  columnName: string = '';
  columnType: string = '';
  constructor(
    private tableService : TableService,
    private router : Router
  ) {
  }
  addColumn() {
    this.messagehead ='Columns:';
    if (this.columnName && this.columnType) {
      this.tableRequest.columns[this.columnName] = this.columnType;
      this.message += "\r\n" + this.columnName + "|" + this.columnType;
      // Clear the input fields after adding a column
      this.columnName = '';
      this.columnType = '';
    }
  }
  createTable(){
    this.tableService.createTable(this.tableRequest).subscribe(
      response => {
        this.message ='Table '+this.tableRequest.tableName+' created';
      },
      error => {
        console.log(error)
        this.message =error.error.message;
      }
    )
  }
}
