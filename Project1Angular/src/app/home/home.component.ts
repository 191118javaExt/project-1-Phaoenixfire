import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private title = 'Believe in Me';
  public element = "";
  public array:string[] = ['Red','Blue','Bananabox','flag','Pikachu',"I'm a teapot"];



public func() {
  if(this.title === 'Me'){
    this.title = "yourself";
  }else 
  this.title = "Me";
}

  constructor() { }

  ngOnInit() {
  }

}
