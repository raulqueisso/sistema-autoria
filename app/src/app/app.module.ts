import { ErrorHandler, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CommonModule, DatePipe, registerLocaleData } from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import { HttpService } from './http/http.service';
import { AppConfigService } from './app-config.service';
import { AppConfig } from './app-config';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';

registerLocaleData(ptBr)

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		BrowserModule,
        AppRoutingModule,
        ServiceWorkerModule.register('ngsw-worker.js', { enabled: false, registrationStrategy: 'registerWhenStable:30000' }),
        BrowserAnimationsModule,
        HttpClientModule,
	],
	providers: [
		HttpService,
		AppConfigService,
		DatePipe,
        { provide: LOCALE_ID, useValue: 'pt' }
	],
	bootstrap: [AppComponent]
})
export class AppModule {
	constructor(private config: AppConfig) {
		this.config.init();
	}
}
