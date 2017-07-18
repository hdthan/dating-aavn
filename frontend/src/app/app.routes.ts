/**
 * Created by lmchuc on 6/2/2017.
 */
import { HomeComponent } from './home/home.component'
import { Error404Component } from './home/404.component'
import { LoginComponent } from './login/login.component'
import { RegisterComponent } from './register/register.component'
import { UserProfileComponent } from './user-profile/user-profile.component'
import { PasswordComponent } from './user-profile/password/password.component'
import { ProfileComponent } from './user-profile/profile/profile.component'
import { QuizComponent } from './quiz/quiz.component'
import { UserPageComponent } from './user-page/user-page.component'
import { TargetListComponent } from './target-list/target-list.component';
import { MatchPageComponent } from './match-page/match-page.component';


export let routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'user-profile', component: UserProfileComponent,
    children: [
      { path: '', redirectTo: 'Profile', pathMatch: 'full' },
      { path: 'Profile', component: ProfileComponent },
      { path: 'Password', component: PasswordComponent }
    ]
  },
  { path: 'match-page/:id', component: MatchPageComponent },
  { path: 'target-list', component: TargetListComponent },
  { path: 'user-page', component: UserPageComponent },
  { path: 'quiz', component: QuizComponent },
  { path: "404", component: Error404Component },
  { path: "**", component: Error404Component }
];
