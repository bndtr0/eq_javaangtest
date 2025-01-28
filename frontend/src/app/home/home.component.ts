import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-home',
  imports: [ MatInputModule, MatFormFieldModule, FormsModule, CommonModule ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent
{
  xlsxData: any;
  tableData: any = null;
  isTableDataReady: boolean = false;

  constructor(private httpClient: HttpClient){}

  uploadXlsxFile(evt: any)
  {
    /* wire up file reader */
    const target: DataTransfer = <DataTransfer>(evt.target);
    if (target.files.length !== 1) {
      throw new Error('Cannot use multiple files');
    }
    const reader: FileReader = new FileReader();
    reader.readAsBinaryString(target.files[0]);
    reader.onload = (e: any) => {
      /* create workbook */
      const binarystr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(binarystr, { type: 'binary' });

      /* selected the first sheet */
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];

      /* save data */
      this.xlsxData = XLSX.utils.sheet_to_json(ws); // to get 2d array pass 2nd parameter as object {header: 1}
      alert(JSON.stringify(this.xlsxData)); // Data will be logged in array format containing objects
    };
  }

  saveData()
  {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'  });
    let options = { headers: headers };

    this.httpClient.post('http://localhost:8080/user', this.xlsxData, options).subscribe((res: any) => {
      alert(res);
    });
  }

  showData()
  {
    this.isTableDataReady = false;

    this.httpClient.get('http://localhost:8080/user').subscribe((res: any) => {
      this.tableData = JSON.stringify(res);
      this.isTableDataReady = true;
      alert(JSON.stringify(res));
    });
  }
}
