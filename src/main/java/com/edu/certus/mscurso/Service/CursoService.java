package com.edu.certus.mscurso.Service;

import com.edu.certus.mscurso.Dto.CursoDto;
import com.edu.certus.mscurso.Dto.ResponseDto;

public interface CursoService {
	public ResponseDto getAllCurso();
	public ResponseDto getCurso(Long id);
	public ResponseDto CreateCurso(CursoDto curso);
	public ResponseDto updateCurso(CursoDto curso);
	public ResponseDto deleteCurso(Long id);
	
}
