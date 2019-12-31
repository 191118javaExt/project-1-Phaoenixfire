import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  Username: string;
  password: string;
  constructor() { }

  ngOnInit() {
  }

  submitForm(){
    const message = `Form submission is working ${this.Username}!`;
    alert(message);

  }
}
