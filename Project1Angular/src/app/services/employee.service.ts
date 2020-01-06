import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reimbursement } from '../models/reimbursement';
import { Observable } from 'rxjs/';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

pastReimbursement(userId: number): Observable<Reimbursement>{
  let body: any =
  {
    userId: userId
  };

  return this.http.post<Reimbursement>('http://localhost:8080/Project1/viewPastRequests', body);
}




}
