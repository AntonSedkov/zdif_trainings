import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {
  text: string = 'My First Component is ready';
  numbers: number[] = [29, 14, 70, 51, 33];
  cash: number = 1.99;
  date = new Date();
  isGreen: boolean = true;
  isDisabled: boolean = false;

  title: string = "This is app title";

  onClick() {
    console.log('Button has clicked');
  }


  constructor() {
  }

  ngOnInit(): void {
  }

}
