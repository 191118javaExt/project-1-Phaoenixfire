import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private ls: LoginService, private router: Router) { }

 
  ngOnInit() {
  } 
  logout() {
    this.ls.logout();
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}
