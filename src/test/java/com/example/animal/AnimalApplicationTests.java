package com.example.animal;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal.entity.Animal;
import com.example.animal.entity.Owner;
import com.example.animal.entity.Playground;
import com.example.animal.entity.PlaygroundAnimals;
import com.example.animal.repository.PlaygroundRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class AnimalApplicationTests {

	@Autowired PlaygroundRepository playgroundRepository;

	@Test
	@Transactional
	void contextLoads() {
		List<Playground> list = playgroundRepository.findByAddress("용인 처인구");

		Playground pg = list.get(0);

		List<PlaygroundAnimals> pas = pg.getPlaygroundAnimals();
		pas.forEach((pa)->{
			Animal animal = pa.getAnimal();
			String animalName = animal.getName();
			Owner owner = animal.getOwner();
			String owerName = owner.getName();
			System.out.println(animalName + owerName);
		});
	}

}
