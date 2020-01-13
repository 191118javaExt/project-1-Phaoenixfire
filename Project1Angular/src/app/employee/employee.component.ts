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
  pastReimbursements: Reimbursement[];
  selectedFile: ImageData;
  userId: number;
  amount: number;
  description: string;
  reimb_type: number;
  receiptDataUrl: string | ArrayBuffer = null;
  returnImage = new Image();
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
  createReimbursement() {
    this.es.createReimbursement(this.currentEmployee.id, this.amount, this.description, this.reimb_type, this.receiptDataUrl)
      .subscribe()
  }

  processFile(e) {
    let image = e.target.files[0];
    let read = new FileReader();
    read.readAsDataURL(image)
    read.onloadend = () => {
      this.receiptDataUrl = read.result;
    }
  }
  showReceipt(e) {
    for (let r of this.pastReimbursements) {
      if (e.target.attributes.value.value == r.reimb_id) {
        let image = new Image();
        image.src = r.reimb_receipt;
        let w = window.open("");
        w.document.write(image.outerHTML);
      }
    }
  }
}