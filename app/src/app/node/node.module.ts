import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NodeComponent } from './node.component';
import { NodeRoutingModule } from './node-routing.module';
import { StructureModule } from '../structure/structure.module';
import { MaterialModule } from '../material/material/material.module';
import { FormsModule } from '@angular/forms';

@NgModule({
	declarations: [
		NodeComponent
	],
	imports: [
		CommonModule,
		NodeRoutingModule,
		StructureModule,
		MaterialModule,
		FormsModule
	]
})
export class NodeModule { }
