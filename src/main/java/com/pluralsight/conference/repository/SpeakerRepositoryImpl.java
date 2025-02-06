package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Speaker> findAll() {
        Speaker speaker = new Speaker();
        speaker.setName("Bryan Hansen");
        speaker.setSkill("Java");
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {

        jdbcTemplate.update("INSERT INTO speaker (name) VALUES (?)", speaker.getName());

//        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//
//        insert.setTableName("speaker");
//
//        List<String> columns = new ArrayList<>();
//        columns.add("name");
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("name", speaker.getName());
//
//        insert.setGeneratedKeyName("id");
//
//        Number key = insert.executeAndReturnKey(data);
//
//        System.out.println(key);

        return null;
    }
}
