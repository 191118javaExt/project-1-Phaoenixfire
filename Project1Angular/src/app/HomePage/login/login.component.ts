import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { LoginService } from 'src/app/services/login.service';
import { EmployeeComponent } from 'src/app/employee/employee.component';
import { Employee } from 'src/app/models/employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';

  constructor(private ls: LoginService, private router: Router) { }

  ngOnInit() {
   
  }
  
  sendLogin(){
    this.ls.login(this.username, this.password).subscribe(
      (response: Employee) => {
        sessionStorage.setItem('currentUser', JSON.stringify(response));
        console.log(response);
        this.router.navigate(['/profile']);
      }
    )
  }
}