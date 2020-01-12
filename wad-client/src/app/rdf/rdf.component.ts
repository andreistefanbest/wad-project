import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-rdf',
  templateUrl: './rdf.component.html',
  styleUrls: ['./rdf.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RdfComponent implements OnInit {
  gradInLast5Years = [];
  persWithMostInterests = [];
  whoHaveProfession = [];

  whose: string;
  newCompany: string;
  profession: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.info().subscribe((info: any) => {
      this.gradInLast5Years = info.graduated;
      this.persWithMostInterests = info.interests;
    });
  }

  rdfChosen(event) {
    this.uploadFile(event.target.files[0]);
  }

  public uploadFile(file) {
    this.readFileAsByteArray(file).then((fileContent) => {
      this.userService.uploadFile({fileName: file.name, fileContent}).subscribe((res) => {
      });
    });
  }

  public readFileAsByteArray(data) {
    return new Promise((resolve, reject) => {
      let reader = new FileReader();
      reader.readAsArrayBuffer(data);
      reader.onloadend = (arrayBuffer) => {
        resolve(this.buildByteArray(arrayBuffer));
      }
    });
  }

  public buildByteArray(buffer) {
    let fileByteArray = []
    if (buffer.target.readyState == FileReader.DONE) {
      let array = new Uint8Array(buffer.target.result);
      for (let i = 0; i < array.length; i++) {
        fileByteArray.push(array[i]);
      }
    }

    return fileByteArray;
  }

  addEditCompany() {
    this.userService.addEditCompany(this.whose, this.newCompany).subscribe();
  }

  queryProfession() {
    this.userService.queryProfessions(this.profession).subscribe((professions: []) => {
      this.whoHaveProfession = professions;
    });
  }
}
