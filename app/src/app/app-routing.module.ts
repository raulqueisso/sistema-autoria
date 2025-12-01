import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
	{
        path: '',
        loadChildren: () => import('./node/node.module').then(m => m.NodeModule)
    },
	{
        path: 'link',
        loadChildren: () => import('./link/link.module').then(m => m.LinkModule)
    },
    {
        path: 'historia',
        loadChildren: () => import('./historia/historia.module').then(m => m.HistoriaModule)
    },
    {
        path: '**',
        redirectTo: ''
    }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
