import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { NodeComponent } from './node.component';

const authRoutes: Routes = [
    { path:'', component: NodeComponent },
    { path:'register', component: NodeComponent},
	{ path:'register/:id', component: NodeComponent},
]

@NgModule({
	imports: [RouterModule.forChild(authRoutes)],
	exports: [RouterModule]
})
export class NodeRoutingModule { }
