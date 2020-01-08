import { Component, OnInit } from '@angular/core';
import { Employee } from '../models/employee';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {

  currentEmployee: Employee;
  constructor() { }

  ngOnInit() {
    let userString: any = sessionStorage.getItem('currentEmployee');
    this.currentEmployee = JSON.parse(userString);
  }

}
