import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../models/employee';
import { LoginService } from '../services/login.service';
import { EmployeeService } from '../services/employee.service';
import { Reimbursement } from '../models/reimbursement';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  currentEmployee: Employee;
  pastReimbursements: Reimbursement;
  constructor(private ls: LoginService, private es: EmployeeService, private router: Router) { }

  ngOnInit() {
    let userString: any = sessionStorage.getItem('currentEmployee');
    this.currentEmployee = JSON.parse(userString);
    console.log(this.currentEmployee)
    this.es.pastReimbursement(this.currentEmployee.id).subscribe(
    (response: Reimbursement) => {
      console.log(response)
      let reimbursementString = JSON.stringify(response);
      console.log(reimbursementString);
      this.pastReimbursements = JSON.parse(reimbursementString);
      console.log(this.pastReimbursements);
    }
    );
  }

  logout() {
    this.ls.logout();
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}