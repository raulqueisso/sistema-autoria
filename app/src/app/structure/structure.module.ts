import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PrincipalComponent } from './principal/principal.component';
import { ListComponent } from './list/list.component';
import { MaterialModule } from '../material/material/material.module';
import { DialogModule } from '@angular/cdk/dialog';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [
    PrincipalComponent,
    ListComponent,
    SearchComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    DialogModule,
    FormsModule,
  ],
  exports: [
    PrincipalComponent,
    ListComponent,
    SearchComponent
  ],
  providers: [
    {
      provide: MatPaginatorIntl,
      useClass: MatPaginatorIntl
    }
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StructureModule { }
