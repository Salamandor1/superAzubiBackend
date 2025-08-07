package de.cancom.super_azubi_pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperAzubiPetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperAzubiPetsApplication.class, args);
        /*
         * IF YOU NEED TO CLEAR THE DATABASE USE THE FOLLOWING COMMAND VIA pgAdmin:
         * 
         * DO $$ DECLARE
         * r RECORD;
         * BEGIN
         * -- Drop all tables
         * FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname =
         * current_schema()) LOOP
         * EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
         * END LOOP;
         * END $$;
         * 
         * PUT THIS CODE INTO THE QUERY FIELD IN pgAdmin AND EXECUTE SCRIPT
         * 
         */

    }

}
