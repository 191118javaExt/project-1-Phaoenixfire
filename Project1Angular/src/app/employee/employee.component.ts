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
  selectedFile: ImageData;
  constructor(private ls: LoginService, private es: EmployeeService, private router: Router) { }
  

  ngOnInit() {
    let userString: any = sessionStorage.getItem('currentEmployee');
    this.currentEmployee = JSON.parse(userString);
    this.es.pastReimbursement(this.currentEmployee.id).subscribe(
    (response: Reimbursement) => {
      let reimbursementString = JSON.stringify(response);
      this.pastReimbursements = JSON.parse(reimbursementString);

    });
  }
}