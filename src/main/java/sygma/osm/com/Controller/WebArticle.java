package sygma.osm.com.Controller;

import sygma.osm.com.entity.Article;
import sygma.osm.com.repository.IArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class WebArticle {
	
	
    private final Logger log = LoggerFactory.getLogger(WebArticle.class);
    
    @Autowired
    private IArticle ia;

    @GetMapping("/article")
    ResponseEntity<List<Article>> getAllArticle(){
        log.info("List of all article: {}");
        List<Article> listArticle = ia.findAll();

        if(listArticle == null || listArticle.isEmpty()){
            log.info("no article found");
            return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
            }
        else {
            log.info("there are some item found");
            return new ResponseEntity<List<Article>>(listArticle, HttpStatus.OK);
        }
    }

    @GetMapping("/article/{id}")
    ResponseEntity<Article> getArticle(@PathVariable("id") long id){
        log.debug("One item found");
        Article  article = ia.getOne(id);
        if(article == null || article.equals("")){
            log.info("no Item Found");
        }
        return new ResponseEntity<Article>(article,HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<Void> ajouterArticle(@RequestBody Article article , UriComponentsBuilder ucBuilder){
        log.info("creating new user: {}", article);

        if(article==null){
            log.info("they are ni item to add");
        }
//        else if (articleService.exists(article)){
            //log.info("one item has added " + article);
            ia.save(article);
        //}
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/article/{id}").buildAndExpand(article.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/article")
    public ResponseEntity<Article> modifierArticle(@RequestBody Article article  , UriComponentsBuilder ucBuilder ){
    	ia.save(article);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Article/{id}").buildAndExpand(article.getId()).toUri());
        return new ResponseEntity<Article>(headers,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        Article art=ia.getOne(id);
      //  if (articleService.exists(art)){
        Article a= ia.getOne(id);
            ia.delete(a);
            log.info("item found and deleted");

       // }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
