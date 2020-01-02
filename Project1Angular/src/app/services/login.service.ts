import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from 'src/app/models/login/login.model';
import { Observable } from 'rxjs/';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _url: string ='localhost:8080';
  constructor(private http: HttpClient) { }

getLogin(): Observable<Login[]>{
  return this.http.get<Login[]>(this._url);
}



}
