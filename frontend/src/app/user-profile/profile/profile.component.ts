import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { IMyDpOptions, IMyDefaultMonth } from 'mydatepicker';
import { ProfileService } from "../user-profile.service";
import { User } from "../models/user"
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  private editName: boolean = false;
  private editAddress: boolean = false;
  private editBMI: boolean = false;
  private checkNumber: number;
  private userRes : User;

  // object = { date: {year: ,month: ,day: }};
  private model: any;

  //array selection
  professions : string[];
  height: number[];
  weight: number[];

  private myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd mmm yyyy',
        editableDateField: false,
        openSelectorOnInputClick: true,
        height: '30px', width: '180px',
        inline: false,
        selectorWidth: '180px',
        selectorHeight: '160px',
        showTodayBtn: false,
        disableSince: { year: 2002, month: 1, day: 1}
    };
    private defaultMonth: IMyDefaultMonth = {
            defMonth: '12/2001'
        }

  constructor( private profileService : ProfileService) {

      this.userRes = new User();
      this.userRes.userId = parseInt ( localStorage.getItem('userId') );
      this.userRes.password = null;
      this.professions = ["Accountant", "Architect", "Clerk", "Worker", "Coder", "Designer", "Doctor", "Lawyer", "Secretary", "Teacher"];
      this.height = this.createArray(150, 180);
      this.weight = this.createArray(40, 80);
   }
   refreshDefaultMonth() {
          this.defaultMonth = {
              defMonth: '12/2001'
          };
      }
  ngOnInit() {
    this.profileService.getProfile()
            .subscribe(
                res => {
                  this.userRes = res
                  console.log(this.userRes)
                  if (this.userRes.birthday != null )
                  {
                    let day = this.userRes.birthday.split('-')
                    this.model = { date: { year: parseInt(day[0]), month: parseInt(day[1]), day: parseInt(day[2]) } }
                  }
                }
            );
  }
  validate(value: number)  {
   value < 0 ? this.checkNumber = 0 : this.checkNumber = value;
 }
  updateUser(user: any) {
    console.log(user)
    this.profileService.updateProfile(user)
      .subscribe(
      (result: any) => {
        console.log(result)

        if ( result != "fail" )
        {
          localStorage.setItem('name', result.name);

        }

      });
  }

  onSubmit(user: NgForm, event: Event) {
    event.preventDefault();

    // stop it conflict from update img;
    this.userRes.userId =  parseInt ( localStorage.getItem('userId') );
    this.userRes.password = null;
    this.userRes.avaImg = null;

   if ( this.model != null ){
     console.log(null)
      this.userRes.birthday = this.model.date.year + "-" + this.model.date.month + "-" + this.model.date.day
    }

    // set input to label
    this.editName = false;
    this.editAddress = false;
    this.editBMI = false;
    console.log(this.userRes)
    this.updateUser(this.userRes);
  }

  createArray( low: number, high: number)
  {
    var items: number[] = [];
    for(var i = low; i <= high; i++){
       items.push(i);
    }

    return items;
  }
}
