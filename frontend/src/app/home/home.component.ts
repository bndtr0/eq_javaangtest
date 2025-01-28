import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-home',
  imports: [ MatInputModule, MatFormFieldModule, MatTableModule, FormsModule, CommonModule ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent
{
  xlsxData: any;
  tableData: any = null;
  isTableDataReady: boolean = false;

  columnsToDisplay: string[] = ["Nombre", "Rut", "Campo 1", "Campo 2", "Campo 3"];

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
      this.xlsxData = XLSX.utils.sheet_to_json(ws); 
    };
  }

  saveData()
  {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'});
    let options = { headers: headers };

    this.httpClient.post('http://localhost:8080/user', this.xlsxData, options).subscribe((res: any) => {
      alert('Datos guardados en la base de datos.');
    });
  }

  showData()
  {
    this.isTableDataReady = false;

    this.httpClient.get('http://localhost:8080/user').subscribe((res: any) => {
      this.tableData = res;
      this.isTableDataReady = true;      
      
      alert("¡Datos cargados desde la base de datos con éxito!");
    });
  }
}
