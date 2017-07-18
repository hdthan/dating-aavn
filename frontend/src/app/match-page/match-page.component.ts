import { Component, OnDestroy, OnInit } from '@angular/core';

import { MatchPageService } from './match-page.service';
import { User } from '../user-profile/models/user';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-match-page',
  templateUrl: './match-page.component.html',
  styleUrls: ['./match-page.component.scss']
})
export class MatchPageComponent implements OnInit, OnDestroy {

  link: string;
  sub: any;

  private id : number;
  private percentage : string;
  private showDialog: boolean = false;
  private countMessage: any = '';
  private message: any;
  private userReceive: User;
  private userRes : User;

  private interest : boolean;

  constructor(private matchPageService: MatchPageService, private route: ActivatedRoute) {
    this.userRes = new User();
    this.userReceive = new User();
    this.interest = false;


  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
       this.link = params['id'];

       // In a real app: dispatch action to load the details here.
    });
    // split
    let split  = this.link.split('-')
    this.id = +split[0];
    this.percentage = split[1];

    //get profile
    this.matchPageService.getProfile(this.id)
      .subscribe(
          res =>{
          this.userReceive = res;
          console.log(this.userReceive)


        }
      );


      //  Check interested;
      // this.checkInterest();
      console.log(this.id)
      this.matchPageService.getMessage( parseInt( localStorage.getItem('userId')), this.id)
        .subscribe(
          res => {}, error => {
            console.log("error")
            this.interest = true
          }
        );





  }
  ngOnDestroy(){

  }

  interestShow() {
    this.showDialog = true;
  }
  interestClose() {
    this.showDialog = false;
  }

  getAge(dateString: any) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }

  checkInterest(){

  }
  sendMessage(msg: string) {
    // this.userReceive.interested = true;
    let userId: number = parseInt(localStorage.getItem('userId'));
    this.matchPageService.getUser(userId).subscribe((res) => {

      this.message = {
        'userSend': res,
        'userReceive': this.userReceive,
        'content': msg
      }

      this.matchPageService.sendMessage(this.message).subscribe((res: any) => {
        console.log(res)
      })
    })

    this.interestClose()

  }

}
