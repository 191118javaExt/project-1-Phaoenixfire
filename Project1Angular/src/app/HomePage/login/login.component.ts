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

  currentEmployee: Employee;
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
          let userString: any = sessionStorage.getItem('currentEmployee');
          this.currentEmployee = JSON.parse(userString);
          console.log(this.currentEmployee);
          if (this.currentEmployee.roleId == 1) {
            this.router.navigate(['/administrator'])
            
          }
          else this.router.navigate(['/employee']);
        }
      }
    )
  }

}