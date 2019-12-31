import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeSingleComponent } from './employee-single/employee-single.component';


const routes: Routes = [
{
  path:'employee',
  component:EmployeeListComponent
},
{
  path:':username',
  component:EmployeeSingleComponent
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
