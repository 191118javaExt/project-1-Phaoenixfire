import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './HomePage/home/home.component';
import { FooterComponent } from './HomePage/footer/footer.component';
import { AdministratorComponent } from './administrator/administrator.component';
import { LoginComponent } from './HomePage/login/login.component';
import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';


const routes: Routes = [
  {
    path: '',
    component:LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path:'administrator',
    component:AdministratorComponent
  },
  {
    path:'employee',
    component:EmployeeComponent
  },
  {
    path:'**',
    redirectTo: 'login',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
