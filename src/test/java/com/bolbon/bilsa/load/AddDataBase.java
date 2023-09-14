package com.bolbon.bilsa.load;

import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.bolbon.utils.PlayerLoad;

@SpringBootTest
public class AddDataBase {
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlayerLoad util = new PlayerLoad();
		util.teamsGenerator(20);
	}

}
