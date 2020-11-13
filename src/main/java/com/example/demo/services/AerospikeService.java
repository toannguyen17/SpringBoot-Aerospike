package com.example.demo.services;

import com.aerospike.client.AerospikeClient;
import org.springframework.stereotype.Service;

@Service
public class AerospikeService {

    private AerospikeClient client;

    public void connetion(){
        client = new AerospikeClient(null, "localhost", 3000);
    }

    public void close(){
        client.close();
    }

    public AerospikeClient getClient() {
        if (client == null)
            connetion();

        return client;
    }
}
