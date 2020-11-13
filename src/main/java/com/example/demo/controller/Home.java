package com.example.demo.controller;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.example.demo.model.Form;
import com.example.demo.model.User;
import com.example.demo.services.AerospikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Home {
    private final String namespace = "test";
    private final String setName   = "demo";

    private int _auto = 1;

    @Autowired
    private AerospikeService aerospikeService;

    @GetMapping
    public ResponseEntity<List<User>> data()
    {
        List list = new ArrayList<User>();

        AerospikeClient client = aerospikeService.getClient();

        for (int i = 1; i <= _auto; ++i){
            Key key = new Key(namespace, setName, i);
            Record record = client.get(null, key);
            if (record != null)
                list.add(new User(record));
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @PostMapping
    public String doPostData(Form form){
        String name  = form.getName().trim();
        String phone = form.getPhone().trim();

        if (name.length() > 0 && phone.length() > 0){
            AerospikeClient client = aerospikeService.getClient();
            int id = _auto++;
            Key key = new Key(namespace, setName, id);

            Bin bin_id    = new Bin("id", id);
            Bin bin_name  = new Bin("name", name);
            Bin bin_phone = new Bin("phone", phone);

            client.put(null, key, bin_id, bin_name, bin_phone);
        }

        return "OK";
    }
}
