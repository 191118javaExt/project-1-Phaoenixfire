import { Component, OnInit } from '@angular/core';
import { Employee } from '../models/employee';
import { AdminService } from '../services/admin.service';
import { Reimbursement } from '../models/reimbursement';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {

  currentEmployee: Employee;
  allReimbursements: Reimbursement[];
  changeRequest: number;
  resolveId: number;
  constructor(private as: AdminService) { }

  ngOnInit() {
    let userString: any = sessionStorage.getItem('currentEmployee');
    this.currentEmployee = JSON.parse(userString);

    this.as.viewAll(this.currentEmployee.id).subscribe(
      (response: Reimbursement) => {
        let reimbursementString = JSON.stringify(response);
        this.allReimbursements = JSON.parse(reimbursementString);
      });
  }
  showReceipt(e) {
    for (let r of this.allReimbursements) {
      if (e.target.attributes.value.value == r.reimb_id) {
        let image = new Image();
        image.src = r.reimb_receipt;
        let w = window.open("");
        w.document.write(image.outerHTML);
      }
    }
  }

  resolveRequest() {
    this.as.resolveRequest(this.resolveId, this.currentEmployee.id, this.changeRequest).subscribe()
    console.log("resolve Sent")
  }
}

