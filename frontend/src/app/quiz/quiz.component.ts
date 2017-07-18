import {
  Component, OnInit, trigger, state, style, transition, animate, keyframes
} from '@angular/core';

import { Router, RouterModule } from '@angular/router';
import { QuizService } from "./quiz.service";
@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  animations: [
    trigger('flyInOut', [
      transition('inactive => active', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(-60%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),

        ])),
      ]),
      transition('active => inactive', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(-60%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),
        ])),
      ]),
      transition('void => *', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(-60%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),
        ])),
      ]),
    ]),
    trigger('flyInOutLine', [
      transition('inactive => active', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(80%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),

        ])),
      ]),
      transition('active => inactive', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(80%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),
        ])),
      ]),
      transition('void => *', [
        animate(400, keyframes([
          style({ opacity: 0, transform: 'translateX(80%)', offset: 0 }),
          style({ opacity: 0.8, transform: 'translateX(0)', offset: 1 }),
        ])),
      ]),
    ])
  ]

})

export class QuizComponent implements OnInit {

  // quizzes = ['I always get my work done before relaxing.', 'You rarely worry about  how actions affect other people.', 'Others would describe me as outgoing and friendly.', 'I would rather be called practical than inventive.', 'When someone has a problem, I start by offering emotional support, rather than advice.',
  //   'My workspace is often messy.', 'When someone has a problem, I start by offering advice, rather than emotional support.', 'I have a hard time making decisions', 'I can start conversations easily, even if it’s with someone I just met.', 'Compassion is more important to me than fairness.', 'After a stressful day, I need some time alone to relax.',
  //   'I sometimes think about things too long, rather than just jumping right into a project.', 'I can’t stand leaving things unfinished.', 'You often think about humankind and its destiny', 'You easily see the general principle behind specific occurrences', 'The more people you speak to, the better you feel',
  //   ' You usually place yourself nearer to the side than in the center of the room', 'You willingly involve yourself in matters which engage your sympathies', 'I prefer to “go with my gut”.', 'Often you prefer to read a book than go to a party']
  quizzes: any = [];
  errorMessage: any;

  agreeLevel = ['Strongly Agree', 'Agree', 'Neutral', 'Disagree', 'Strongly Disagree']
  answers: any;
  answeredQuestion: number;
  isFinished: boolean;
  state: string = 'inactive';
  active: number;

  constructor(private quizService: QuizService, private router: Router) {
  }

  ngOnInit(): void {


    this.answers = localStorage.getItem('answers') ? JSON.parse(localStorage.getItem('answers')) : [];
    this.answeredQuestion = this.answers.length;
    this.active = this.answers.length;
    // console.log(this.answers)
    // console.log(this.answeredQuestion)

    this.isFinished = false;
    this.quizService.getQuiz()
      .subscribe(
      quizzes => this.quizzes = quizzes,
      error => this.errorMessage = <any>error);


  }

  onSelectionChange(questionNum: number, answerNum: number) {

    // console.log(questionNum + " " + answerNum);
    if (questionNum < this.answers.length) {
      this.answers[questionNum - 1].ans = answerNum;
    }
    else {
      this.answers.push({ 'ans': answerNum });
    }
    this.active = questionNum;
    localStorage.setItem('answers', JSON.stringify(this.answers));

    if (questionNum < this.quizzes.length) {
      this.answeredQuestion = this.answers.length;
    }
    else {
      console.log(this.answers)
      this.isFinished = true;
      this.quizService.postQA(this.answers).subscribe(
        res => {
          console.log(res)
          this.router.navigate(['./user-page'])

        },
        error => { });
    }


  }

  navigate(i: number) {
    this.active = i;
  }


  isChoosed(questionNum: number, answerNum: number) {
    if (questionNum > this.answers.length) {
      return false;
    }
    return this.answers[questionNum - 1].ans == answerNum;
  }

  toggleMove() {

    this.state = (this.state === 'inactive' ? 'active' : 'inactive');
  }

}
