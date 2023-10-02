INSERT INTO flowcrmtut.employee(id, employee_data, company_id) VALUES
                                                                   (gen_random_uuid(), 'FIRST_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'NewCompany')),
                                                                   (gen_random_uuid(), 'SECOND_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'OldCompany')),
                                                                   (gen_random_uuid(), 'THIRD_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'NewCompany')),
                                                                   (gen_random_uuid(), 'FOURTH_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'NewCompany')),
                                                                   (gen_random_uuid(), 'FIFTH_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'NewCompany')),
                                                                   (gen_random_uuid(), 'SIXTH_EMP', (SELECT id FROM flowcrmtut.company WHERE name = 'OldCompany'))


