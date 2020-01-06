import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
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

  sendLogin() {
    this.ls.login(this.username, this.password).subscribe(
      (response: Employee) => {
        if (response === null) {
          this.router.navigate(['/login']);
        } else {
          sessionStorage.setItem('currentEmployee', JSON.stringify(response));
          this.router.navigate(['/employee']);
        }
      }
    )
  }

}