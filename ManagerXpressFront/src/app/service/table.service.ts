import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {TableResponse} from "../models/TableResponse";
import {TableRequest} from "../models/TableRequest";

@Injectable({
  providedIn: 'root'
})
export class TableService {

  private baseUrl: string = 'http://localhost:8080/api/user';

  constructor(private httpClient: HttpClient) { }

  createTable(userTableDTO: TableRequest): Observable<TableResponse> {
    return this.httpClient.post<TableResponse>(`${this.baseUrl}/create-table`, userTableDTO);
  }

  assignUserToTable(user: string, table: string): Observable<TableRequest> {
    return this.httpClient.put<TableRequest>(`${this.baseUrl}/assign/${user}/To/${table}`, {});
  }

  removeUserFromTable(user: string, table: string): Observable<TableRequest> {
    return this.httpClient.put<TableRequest>(`${this.baseUrl}/remove/${user}/From/${table}`, {});
  }

  getUserTableById(tableId: string): Observable<TableResponse> {
    return this.httpClient.get<TableResponse>(`${this.baseUrl}/get-table/${tableId}`);
  }

  getAllAssignedUserTables(): Observable<TableResponse[]> {
    return this.httpClient.get<TableResponse[]>(`${this.baseUrl}/get-assigned-user-tables`);
  }

  getAllUserTables(): Observable<TableResponse[]> {
    return this.httpClient.get<TableResponse[]>(`${this.baseUrl}/get-user-tables`);
  }
  deleteTable(idTable: string): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/removeTable/${idTable}`);
  }
}
