package sygma.osm.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import sygma.osm.com.entity.Article;
import sygma.osm.com.repository.IArticle;

@SpringBootApplication

public class SygmaOsmApplication implements CommandLineRunner{
	@Autowired
	private  IArticle ia;
	public static void main(String[] args) {
		SpringApplication.run(SygmaOsmApplication.class, args);
	  	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		  ia.save(new Article("A","A","A")); 
		  ia.save(new Article("B","B","B"));
		  ia.save(new Article("C","C","C"));
		  ia.save(new Article("D","D","D"));
		  System.out.println("ee");

	}

}
