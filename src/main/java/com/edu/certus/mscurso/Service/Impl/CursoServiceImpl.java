package com.edu.certus.mscurso.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.mscurso.Dto.CursoDto;
import com.edu.certus.mscurso.Dto.ResponseDto;
import com.edu.certus.mscurso.Entity.CursoEntity;
import com.edu.certus.mscurso.Repository.CursoRepository;
import com.edu.certus.mscurso.Service.CursoService;
import com.edu.certus.mscurso.util.Constante;
import com.edu.certus.mscurso.util.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	public CursoRepository cursoRepository;

	@Override
	public ResponseDto getAllCurso() {
		try {
			List<CursoEntity> listCursoEntity = cursoRepository.findAll();
			if (listCursoEntity.isEmpty()) {
				return util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			List<CursoDto> list = new ArrayList<CursoDto>();
			for (CursoEntity cursoEntity : listCursoEntity) {
				list.add(CursoDto.builder()
						.id(cursoEntity.getId())
						.descripcion(cursoEntity.getDescripcion())
						.estado(cursoEntity.getEstado())
						.build());
				
			}
			return util.getResponse(true, Constante.OPERATION_SUCCESS, list);
		} catch (Exception e) {
			return util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto getCurso(Long id) {
		try {
			CursoEntity cursoEntity = cursoRepository.findById(id).orElse(null);
			if (null == cursoEntity) {
				return util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			CursoDto cursoDto = CursoDto.builder()
					.id(cursoEntity.getId())
					.descripcion(cursoEntity.getDescripcion())
					.estado(cursoEntity.getEstado())
					.build();
			return util.getResponse(true, Constante.OPERATION_SUCCESS, cursoDto);
		} catch (Exception e) {
			return util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto CreateCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity = CursoEntity.builder()
					.id(curso.getId())
					.descripcion(curso.getDescripcion())
					.estado(true)
					.build();
					cursoRepository.save(cursoEntity);
					curso.setId(cursoEntity.getId());
			return util.getResponse(true, Constante.OPERATION_SUCCESS, curso);
		} catch (Exception e) {
			return util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto updateCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity = cursoRepository.findById(curso.getId()).orElse(null);
			if (null == cursoEntity) {
				return util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			cursoEntity.setId(curso.getId());
			cursoEntity.setDescripcion(curso.getDescripcion());
			cursoEntity.setEstado(curso.getEstado());
			cursoRepository.save(cursoEntity);
			return util.getResponse(true, Constante.OPERATION_SUCCESS, curso);
		} catch (Exception e) {
			return util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto deleteCurso(Long id) {
		try {
			CursoEntity cursoEntity = cursoRepository.findById(id).orElse(null);
			cursoEntity.setEstado(false);
			cursoRepository.save(cursoEntity);
			return util.getResponse(true, Constante.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			log.error(Constante.OPERATION_FAILED, e);
			return util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

}
