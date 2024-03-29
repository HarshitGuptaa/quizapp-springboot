package com.harshit.quizapp.service;

import com.harshit.quizapp.model.Question;
import com.harshit.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {

        try{
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestionById(int id) {

        try{
            if(questionDao.existsById(id)){
                questionDao.deleteById(id);
                return new ResponseEntity<>("Deleted question number "+id,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No question present for id: "+id,HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

            return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);

    }
}
