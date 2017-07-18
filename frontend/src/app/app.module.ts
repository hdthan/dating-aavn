/**
 * Created by lmchuc on 6/2/2017.
 */

// Module import
import { HttpModule } from "@angular/http";
import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { BrowserModule } from "@angular/platform-browser";
import { routes } from './app.routes';
import { FormsModule } from '@angular/forms';
import { LocalStorageModule } from 'angular-2-local-storage';
import { EqualValidator } from './register/equal-validator.directive';

import { MyDatePickerModule } from 'mydatepicker';

// Component import
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { Error404Component } from './home/404.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { UserProfileComponent } from './user-profile/user-profile.component'
import { ImageCropperComponent } from 'ng2-img-cropper';
import { PasswordComponent } from './user-profile/password/password.component';
import { ProfileComponent } from './user-profile/profile/profile.component';
import { UserPageComponent } from './user-page/user-page.component';
import { QuizComponent } from './quiz/quiz.component';
import { TargetListComponent } from './target-list/target-list.component';
import { MatchPageComponent } from './match-page/match-page.component';

// Service import
import { LoginService } from "./login/login.service";
import { RegisterService } from "./register/register.service";
import { ProfileService} from "./user-profile/user-profile.service"
import { Angular2SocialLoginModule } from 'angular2-social-login';
import { Ng2CloudinaryModule } from 'ng2-cloudinary';
import { FileUploadModule } from 'ng2-file-upload';
import { QuizService } from "./quiz/quiz.service";
import { UserPageService } from "./user-page/user-page.service"
import { TargetListService } from "./target-list/target-list.service"
import { MatchPageService } from './match-page/match-page.service';



let providers = {
  "google": {
    "clientId": "559851266325-dhv20id67ppqeettk9hm2muiiv3cm9gi.apps.googleusercontent.com"
  }
};


@NgModule({
  imports: [BrowserModule, Ng2CloudinaryModule ,  FileUploadModule, MyDatePickerModule, HttpModule, RouterModule.forRoot(routes), FormsModule, Angular2SocialLoginModule, LocalStorageModule.withConfig({
    prefix: 'my-app',
    storageType: 'localStorage'
  })],

  declarations: [AppComponent, HomeComponent, Error404Component, LoginComponent, RegisterComponent, TargetListComponent, EqualValidator, UserProfileComponent, ImageCropperComponent, QuizComponent, ProfileComponent, PasswordComponent, UserPageComponent, MatchPageComponent],
  bootstrap: [AppComponent],
  providers: [LoginService, RegisterService, ProfileService, QuizService, UserPageService, TargetListService, MatchPageService]

})

export class AppModule {
}

Angular2SocialLoginModule.loadProvidersScripts(providers);
