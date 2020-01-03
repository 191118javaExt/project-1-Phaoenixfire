import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../models/employee';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  currentEmployee: Employee;
  constructor(private ls: LoginService, private router: Router) { }

  ngOnInit() {
    let userString: string = sessionStorage.getItem('currentUser');
    if(userString === null) {
      this.router.navigate(['/login']);
    } else {
      this.currentEmployee = JSON.parse(userString);
    }
  }

  logout() {
    this.ls.logout();
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}