import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LinkComponent } from './link.component';
import { LinkRoutingModule } from './link-routing.module';
import { StructureModule } from '../structure/structure.module';
import { MaterialModule } from '../material/material/material.module';
import { FormsModule } from '@angular/forms';



@NgModule({
	declarations: [
		LinkComponent
	],
	imports: [
		CommonModule,
		LinkRoutingModule,
		StructureModule,
		MaterialModule,
		FormsModule
	]
})
export class LinkModule { }
