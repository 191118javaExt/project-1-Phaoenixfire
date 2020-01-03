import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from 'src/app/models/login/login.model';
import { Observable } from 'rxjs/';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<Employee> {
    let body: any =
    {
      username: username,
      password: password
    };

    return this.http.post<Employee>('http://localhost:8080/Project1/login', body);
  }

  logout(){
    return this.http.post<void>("http://localhost:8080Project1/logout", {});
  }
}
