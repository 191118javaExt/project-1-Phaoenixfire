import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reimbursement } from '../models/reimbursement';

@Injectable({
  providedIn: 'root'
})
export class AdminService {


  constructor(private http: HttpClient) { }

  viewAll(userId: number): Observable<Reimbursement> {
    let body: any =
    {
      userId: userId
    };

    return this.http.post<Reimbursement>('http://localhost:8080/Project1/viewAllPastRequests', body);
  }
  resolveRequest(reimbId: number, userId: number, resolved: number): Observable<Reimbursement> {
    let body: any =
    {
      reimbId:reimbId,
      userId: userId,
      resolved:resolved,

    };
    return this.http.post<Reimbursement>('http://localhost:8080/Project1/manageRequests', body);
  }


}


