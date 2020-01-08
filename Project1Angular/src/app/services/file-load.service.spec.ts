import { TestBed } from '@angular/core/testing';

import { FileLoadService } from './file-load.service';

describe('FileLoadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileLoadService = TestBed.get(FileLoadService);
    expect(service).toBeTruthy();
  });
});
