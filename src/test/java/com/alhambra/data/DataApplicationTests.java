package com.alhambra.data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({ "/schema.sql", "/data.sql" })
class DataApplicationTests {

	@Autowired
	LibroRepository libroRepository;

	@Test
	void buscarTodosLibros() { // contextLoads
		Iterable<Libro> it = libroRepository.findAll();
		List<Libro> listaLibros = new ArrayList<Libro>();
		it.forEach(System.out::println);
		it.forEach(listaLibros::add);
		assertThat(listaLibros.size(), greaterThan(6));
	}

	@Test
	void buscarUnoLibro() {
		Optional<Libro> op = libroRepository.findById("1A");
		if (op.isPresent()) {
			assertThat(op.get().getTitulo(), equalTo("Java"));
		}
	}

	@Test
	void insertarUnoLibro() {
		Libro l = new Libro("1F", "Python", "AutorF", new Date(), 75);
		libroRepository.save(l);
		Optional<Libro> op = libroRepository.findById("1F");
		if (op.isPresent()) {
			System.out.println(l);
			assertThat(op.get().getTitulo(), equalTo("Python"));
		}
	}

	@Test
	void borrarUnoLibro() {
		Libro l = new Libro("1A");
		libroRepository.delete(l);
		Optional<Libro> op = libroRepository.findById("1A");
		assertFalse(op.isPresent());
	}

	@Test
	void buscarVariosLibros() {
		Iterable<Libro> it = libroRepository.findAllById(List.of("1A", "2A"));
		List<Libro> listaLibros = new ArrayList<Libro>();
		it.forEach(System.out::println);
		it.forEach(listaLibros::add);
		assertThat(listaLibros.get(0).getTitulo(), equalTo("Java"));
		assertThat(listaLibros.get(1).getTitulo(), equalTo("Net"));

	}

}
