package com.example.demo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DatabaseRepository(val db: JdbcTemplate) {

    fun cleanDatabase() {
        val sql = """
            DELETE FROM public.companies;
            DELETE FROM public.corporate_structures;
            DELETE FROM public.address;
            DELETE FROM public.states;
        """.trimIndent()
        return db.execute(sql)
    }

    fun populateDatabase() {
        val sql = """
            INSERT INTO public.address (id, first_address, second_address, country, state, city, zipcode, createddate)
            VALUES
              (gen_random_uuid(), '123 Main St', 'Apt 4B', 'USA', 'New York', 'New York', '10001', NOW()),
              (gen_random_uuid(), '456 Elm St', NULL, 'USA', 'California', 'Los Angeles', '90001', NOW()),
              (gen_random_uuid(), '789 Oak St', 'Suite 201', 'USA', 'Texas', 'Houston', '77001', NOW()),
              (gen_random_uuid(), '42 Chestnut Ln', 'Unit 5', 'USA', 'Florida', 'Miami', '33101', NOW()),
              (gen_random_uuid(), '987 Pine Ave', NULL, 'USA', 'Illinois', 'Chicago', '60601', NOW()),
              (gen_random_uuid(), '555 Maple Rd', 'Floor 3', 'USA', 'Arizona', 'Phoenix', '85001', NOW()),
              (gen_random_uuid(), '369 Oakwood Dr', 'Apt 8', 'USA', 'Colorado', 'Denver', '80201', NOW()),
              (gen_random_uuid(), '654 Birch St', NULL, 'USA', 'Washington', 'Seattle', '98101', NOW()),
              (gen_random_uuid(), '231 Elmwood Ave', 'Suite 15', 'USA', 'Massachusetts', 'Boston', '02101', NOW()),
              (gen_random_uuid(), '777 Cedar Rd', 'Unit 2', 'USA', 'Nevada', 'Las Vegas', '89101', NOW()),
              (gen_random_uuid(), '888 Willow Ln', 'Floor 4', 'USA', 'Michigan', 'Detroit', '48201', NOW()),
              (gen_random_uuid(), '123 Spruce Dr', 'Apt 12', 'USA', 'Georgia', 'Atlanta', '30301', NOW()),
              (gen_random_uuid(), '456 Sycamore St', NULL, 'USA', 'New Jersey', 'Newark', '07101', NOW()),
              (gen_random_uuid(), '789 Redwood Rd', 'Suite 22', 'USA', 'North Carolina', 'Charlotte', '28201', NOW()),
              (gen_random_uuid(), '101 Pinecone Ave', 'Unit 1', 'USA', 'Ohio', 'Columbus', '43201', NOW()),
              (gen_random_uuid(), '222 Cedarwood Dr', NULL, 'USA', 'Pennsylvania', 'Philadelphia', '19101', NOW()),
              (gen_random_uuid(), '333 Oaklane Rd', 'Floor 2', 'USA', 'Tennessee', 'Nashville', '37201', NOW()),
              (gen_random_uuid(), '444 Maplewood St', 'Apt 7', 'USA', 'Virginia', 'Richmond', '23201', NOW()),
              (gen_random_uuid(), '555 Elm Ave', NULL, 'USA', 'Wisconsin', 'Milwaukee', '53201', NOW()),
              (gen_random_uuid(), '666 Willow Rd', 'Suite 10', 'USA', 'Minnesota', 'Minneapolis', '55401', NOW());

            INSERT INTO public.states (id, name, abbreviation, createddate)
            VALUES
              (gen_random_uuid(), 'New York', 'NY', NOW()),
              (gen_random_uuid(), 'California', 'CA', NOW()),
              (gen_random_uuid(), 'Texas', 'TX', NOW()),
              (gen_random_uuid(), 'Florida', 'FL', NOW()),
              (gen_random_uuid(), 'Illinois', 'IL', NOW()),
              (gen_random_uuid(), 'Arizona', 'AZ', NOW()),
              (gen_random_uuid(), 'Colorado', 'CO', NOW()),
              (gen_random_uuid(), 'Washington', 'WA', NOW()),
              (gen_random_uuid(), 'Massachusetts', 'MA', NOW()),
              (gen_random_uuid(), 'Nevada', 'NV', NOW()),
              (gen_random_uuid(), 'Michigan', 'MI', NOW()),
              (gen_random_uuid(), 'Georgia', 'GA', NOW()),
              (gen_random_uuid(), 'New Jersey', 'NJ', NOW()),
              (gen_random_uuid(), 'North Carolina', 'NC', NOW()),
              (gen_random_uuid(), 'Ohio', 'OH', NOW()),
              (gen_random_uuid(), 'Pennsylvania', 'PA', NOW()),
              (gen_random_uuid(), 'Tennessee', 'TN', NOW()),
              (gen_random_uuid(), 'Virginia', 'VA', NOW()),
              (gen_random_uuid(), 'Wisconsin', 'WI', NOW()),
              (gen_random_uuid(), 'Minnesota', 'MN', NOW());

            INSERT INTO public.corporate_structures (id, name, createddate)
            VALUES
              (gen_random_uuid(), 'LLC', NOW()),
              (gen_random_uuid(), 'Corp', NOW());

            INSERT INTO public.companies (id, state_id, corporate_structure_id, address_id, name, area, area_description, stock_amount, stock_price, is_active, is_register_complete, createddate)
            VALUES
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'NY'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'New York'), 'Company 1', 'Technology', 'Tech company', 1000000.00, 50.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'CA'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Los Angeles'), 'Company 2', 'Finance', 'Financial institution', 500000.00, 75.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'TX'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Houston'), 'Company 3', 'Retail', 'Retail business', 200000.00, 40.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'FL'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Miami'), 'Company 4', 'Healthcare', 'Health services', 750000.00, 60.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'IL'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Chicago'), 'Company 5', 'Education', 'Educational institution', 300000.00, 45.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'AZ'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Phoenix'), 'Company 6', 'Hospitality', 'Hospitality services', 400000.00, 55.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'CO'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Denver'), 'Company 7', 'Automotive', 'Auto repair', 150000.00, 30.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'WA'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Seattle'), 'Company 8', 'Manufacturing', 'Manufacturing industry', 600000.00, 70.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'MA'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Boston'), 'Company 9', 'Real Estate', 'Property management', 900000.00, 80.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'NV'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Las Vegas'), 'Company 10', 'Entertainment', 'Entertainment services', 250000.00, 35.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'MI'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Detroit'), 'Company 11', 'Agriculture', 'Farming', 120000.00, 25.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'GA'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Atlanta'), 'Company 12', 'Consulting', 'Business consulting', 300000.00, 45.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'NJ'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Newark'), 'Company 13', 'Energy', 'Energy solutions', 180000.00, 35.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'NC'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Charlotte'), 'Company 14', 'Food & Beverage', 'Restaurant', 50000.00, 15.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'OH'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Columbus'), 'Company 15', 'Transportation', 'Logistics', 300000.00, 50.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'PA'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Philadelphia'), 'Company 16', 'Media', 'Media production', 150000.00, 40.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'TN'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Nashville'), 'Company 17', 'Fashion', 'Clothing store', 80000.00, 20.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'VA'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Richmond'), 'Company 18', 'Insurance', 'Insurance agency', 600000.00, 70.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'WI'), (SELECT id FROM public.corporate_structures WHERE name = 'LLC'), (SELECT id FROM public.address WHERE city = 'Milwaukee'), 'Company 19', 'Sports', 'Sports club', 75000.00, 15.00, TRUE, TRUE, NOW()),
              (gen_random_uuid(), (SELECT id FROM public.states WHERE abbreviation = 'MN'), (SELECT id FROM public.corporate_structures WHERE name = 'Corp'), (SELECT id FROM public.address WHERE city = 'Minneapolis'), 'Company 20', 'Pharmaceutical', 'Pharmaceuticals', 400000.00, 65.00, TRUE, TRUE, NOW());
        """.trimIndent()
        return db.execute(sql)
    }
}