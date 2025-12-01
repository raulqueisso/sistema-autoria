import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ScreenService } from 'src/app/service/screen.service';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
    styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit, OnChanges {

    @Input() displayedColumns: any[] = [];

    columns: any[] = [];

    dataSource: MatTableDataSource<any> = new MatTableDataSource();

    @Input() pagination: any = {}

    @Output() onMenuOption = new EventEmitter();

    @Output() onNavigationPage = new EventEmitter();

    @ViewChild(MatPaginator) paginator!: MatPaginator;

    columnsToDisplay: string[] = [];

    @Input() data: any[] = [];

    @Input() sticky: boolean = false;

    @Input() width: number = 0;

    @Input() listInBox: boolean = false;

    @ViewChild(MatTable) table!: MatTable<any>;

    widhtScreen: string = "";

    constructor(private screenService: ScreenService) {
    }

    private generateWidthTable() {
        let widhtScreen = this.screenService.widthScreen();

        if (isNaN(this.width)) {
            this.widhtScreen = "100%";
            return;
        };
        if (this.width < widhtScreen) {
            this.widhtScreen = "100%";
            return;
        }
        this.widhtScreen = this.width + "px";
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.generateWidthTable();
        if (this.dataSource) {
            this.dataSource.data = this.data;
            this.buildDisplayColumns();

            if (this.paginator) {
                this.paginator.length = this.pagination.total;
                this.paginator.pageSize = this.pagination.size;
                this.paginator.pageSizeOptions = this.pagination.page;
                if (this.pagination.index === 0 || this.pagination.index) {
                    this.paginator.pageIndex = this.pagination.index;
                }
            }
        }
        if (this.table) {
            this.table.renderRows();
        }
    }

    isLink(value: any): boolean {
        return value && typeof value === 'object' && 'link' in value;
    }

    ngOnInit(): void {
        this.generateWidthTable();
        this.buildDisplayColumns();
        if (this.dataSource) {
            this.dataSource.data = this.data;
            this.dataSource.paginator = this.paginator;
        }
    }

    private buildDisplayColumns() {
        this.columns = this.displayedColumns.map((def) => {
            return { label: def };
        });

        this.columnsToDisplay = this.displayedColumns.map((def) => {
            return def;
        });
    }

    onMenuClick(element: any, option: string) {
        this.onMenuOption.emit({ element: element, option: option });
    }

    onPaginatorNavigate(event: any) {
        this.onNavigationPage.emit({ pageIndex: event.pageIndex })
    }
}
