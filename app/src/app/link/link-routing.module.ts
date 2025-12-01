import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LinkComponent } from './link.component';

const authRoutes: Routes = [
    { path:'', component: LinkComponent },
    { path:'register', component: LinkComponent},
  { path:'register/:id', component: LinkComponent},
]

@NgModule({
  imports: [RouterModule.forChild(authRoutes)],
  exports: [RouterModule]
})

export class LinkRoutingModule { }
