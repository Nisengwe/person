import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonComponent } from './person/person.component';
import { PersonsComponent } from './persons/persons.component';

const routes: Routes = [
  { path: "person", component: PersonComponent },
  { path: "person/:id", component: PersonComponent },
  { path: "persons", component: PersonsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
