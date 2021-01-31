import { Component, OnInit } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router'
import { RequestService, Person } from '../service/request.service'

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit {

  message = ""
  person: Person

  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private request: RequestService
  ) {
    this.person = this.getNewPerson()
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params.id) {
        this.request.getPerson(params.id)
          .subscribe(data => {
            this.person = data
          }, error => this.message = error)
      }
    });
  }

  onSubmit() {
    this.submitted = true;

    if (!this.person.id) {
      this.request.createPerson(this.person).subscribe(data => {
        this.message = ''
      }, err => this.message = err)
    } else {
      this.request.updatePerson(this.person).subscribe(data => {
        this.message = ''
      }, err => this.message = err)
    }
  }

  getNewPerson() {
    const newPerson = {
      firstName: "",
      lastName: "",
      address:[]
    }
    return newPerson;
  }

  loadNewPerson() {
    this.person = this.getNewPerson();
  }

}

