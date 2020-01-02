import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { login, LoginService } from  'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginInfo: String[] = [];
  public username: string;
  public password: string;

  constructor(private login: LoginService) {
    // private testService: TestService
    //     getValue(){
    //       this.testService.getData(Username);
    //     }
  }

  ngOnInit() {
    this.login.getLogin()
    .subscribe(data => this.login = data)
  }

  submitForm() {
    const message = `Form submission is working ${login.username}!`;
    alert(message);

  }
  retrieve(){
    this.login.getLogin().subscribe(
      data =>{

      }
      )
  }

}
