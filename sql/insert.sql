/* Service Center Schema: |Center_id| Center_name| Center_addr| Center_phonenum| Open_dat| Open Hour| Emp_Quan */
insert into SERVICE_CENTER values ('S0001', 'Downtown Auto Care', '437 Fayetteville St., Raleigh, NC 27601', 18008999999, 'M-F', '8AM-7PM', 7);
insert into SERVICE_CENTER values ('S0002', 'Express Auto Shop', '201 N Tryon St, Charlotte, NC 28202', 17043331555, 'M-F', '8AM-7PM', 7);

/* Distributor Schemas: |distributor_id | distributor_name|*/
insert into DISTRIBUTOR values ('D0001', 'D1');
insert into DISTRIBUTOR values ('D0002', 'D2');

/* Login Schemas: |user_id(emp_id/customer+email) | Role| Password|*/
--Emp:
insert into LOGIN values ('3169785494', 'Manager', '0000');
insert into LOGIN values ('2139882011', 'Receptionist', '0000');
insert into LOGIN values ('4133359523', 'Mechanics', '0000');
insert into LOGIN values ('5634967912', 'Mechanics', '0000');
insert into LOGIN values ('7604579846', 'Mechanics', '0000');
insert into LOGIN values ('829843373', 'Mechanics', '0000');
insert into LOGIN values ('3154851152', 'Mechanics', '0000');
insert into LOGIN values ('9017770492', 'Manager', '0000');
insert into LOGIN values ('9723533691', 'Receptionist', '0000');
insert into LOGIN values ('9564440948', 'Mechanics', '0000');
insert into LOGIN values ('6417529403', 'Mechanics', '0000');
insert into LOGIN values ('9039671809', 'Mechanics', '0000');
insert into LOGIN values ('7578866330', 'Mechanics', '0000');
insert into LOGIN values ('9315391294', 'Mechanics', '0000');
--Customer:
insert into LOGIN values ('ethanhunt@gmail.com', 'Customer', '0000');
insert into LOGIN values ('jarvis@gmail.com', 'Customer', '0000');
insert into LOGIN values ('lovestory@gmail.com', 'Customer', '0000');
insert into LOGIN values ('venus@gmail.com', 'Customer', '0000');

/* Employee Schemas: | emp_id | emp_name | emp_role | emp_addr | emp_email | emp_phone | emp_startdate | emp_wage	*/
/*Center 1's enp*/
insert into EMPLOYEE values (950932130, 'Larry Cohen', 'Manager', '1979 Henry Street, Raleigh, NC 27606', 'lacohen@acme.com', 3169785494, TO_DATE('08/15/2016', 'MM/DD/YYYY'), 12000, 'Monthly','S0001');
insert into EMPLOYEE values (634622236, 'Willis Martin', 'Receptionist', '465 Aviation Way, Raleigh, NC 27601', 'wimartin@acme.com', 2139882011, TO_DATE('12/04/2017', 'MM/DD/YYYY'), 8000, 'Monthly','S0001');
insert into EMPLOYEE values (557279280, 'Jacob Gloss', 'Mechanics', '2014 Leverton Cove Road, Raleigh, NC 27560', 'jagloss@acme.com', 4133359523, TO_DATE('07/29/2018', 'MM/DD/YYYY'), 30, 'Hourly','S0001');
insert into EMPLOYEE values (183683346, 'Anthony Freeman', 'Mechanics', '1188 Summit Street, Raleigh, NC 27627', 'anfreeman@acme.com', 5634967912, TO_DATE('02/09/2015', 'MM/DD/YYYY'), 40, 'Hourly','S0001');
insert into EMPLOYEE values (557279281, 'Eric Fowler', 'Mechanics', '1114 Fincham Road, Raleigh, NC 27545', 'erfowler@acme.com', 7604579846, TO_DATE('05/01/2016', 'MM/DD/YYYY'), 35, 'Hourly','S0001');
insert into EMPLOYEE values (557279282, 'Roland Richmond', 'Mechanics', '1951 Little Acres Lane, Raleigh, NC 27513', 'rorichmond@acme.com', 829843373, TO_DATE('12/28/2017', 'MM/DD/YYYY'), 30, 'Hourly','S0001');
insert into EMPLOYEE values (557279283, 'Peter Fitzpatrick', 'Mechanics','4738 Buckhannan Avenue, Raleigh, NC 27625', 'pefitzpatrick@acme.com', 3154851152, TO_DATE('04/12/2017', 'MM/DD/YYYY'), 34, 'Hourly','S0001');
/*Center 2's emp*/
insert into EMPLOYEE values (291056276, 'Roderick Phillips', 'Manager', '1133 Burton Avenue, Charlotte, NC 28201', 'rophillips@acme.com', 9017770492, TO_DATE('04/15/2017', 'MM/DD/YYYY'), 12000, 'Monthly','S0002');
insert into EMPLOYEE values (911639633, 'Dena Holmes', 'Receptionist', '1382 Whispering Pines Circle, Charlotte, NC 28205', 'deholmes@acme.com', 9723533691, TO_DATE('01/04/2010', 'MM/DD/YYYY'), 9000, 'Monthly','S0002');
insert into EMPLOYEE values (590424694, 'Dustin Esparza', 'Mechanics', '3510 Hemlock Lane, Charlotte, NC 28202', 'duesparza@acme.com', 9564440948, TO_DATE('01/26/2017', 'MM/DD/YYYY'), 35, 'Hourly','S0002');
insert into EMPLOYEE values (401671897, 'Charles Pudilo', 'Mechanics', '196 Park Boulevard, Charlotte, NC 28222', 'chpudilo@acme.com', 6417529403, TO_DATE('07/06/2016', 'MM/DD/YYYY'), 40, 'Hourly','S0002');
insert into EMPLOYEE values (310773348, 'James Rivera', 'Mechanics', '908 Alpha Avenue, Charlotte, NC 28130', 'jarivera@acme.com',  9039671809, TO_DATE('10/29/2013', 'MM/DD/YYYY'), 40, 'Hourly','S0002');
insert into EMPLOYEE values (983204784, 'Willis Morton', 'Mechanics', '404 Tenmile, Charlotte, NC 28134', 'wimorton@acme.com', 7578866330, TO_DATE('10/13/2018', 'MM/DD/YYYY'), 30, 'Hourly','S0002');
insert into EMPLOYEE values (187658163, 'Rickie Henderson', 'Mechanics', '1963 Chenoweth Drive, Charlotte, NC 28228', 'rihenderson@acme.com', 9315391294, TO_DATE('05/29/2017', 'MM/DD/YYYY'), 30, 'Hourly','S0002');

/*  Payroll schems : | PAYROLL_ID | PAYCHECK_DATE | UNITS (WORKDAYS) | PAYTYPE | CUR_EARN | SOFAR_EARN |  */
insert into PAYROLL values ('P0001', TO_DATE('08/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 12000, 84000,950932130);
insert into PAYROLL values ('P0002', TO_DATE('09/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 12000, 96000,950932130);
insert into PAYROLL values ('P0003', TO_DATE('10/1/2018', 'MM/DD/YYYY'), 22, 'Monthly', 12000, 108000,950932130);
insert into PAYROLL values ('P0004', TO_DATE('11/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 12000, 120000,950932130);
insert into PAYROLL values ('P0005', TO_DATE('08/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 8000, 56000,634622236);
insert into PAYROLL values ('P0006', TO_DATE('09/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 8000, 64000,634622236);
insert into PAYROLL values ('P0007', TO_DATE('10/1/2018', 'MM/DD/YYYY'), 22, 'Monthly', 8000, 72000,634622236);
insert into PAYROLL values ('P0008', TO_DATE('11/1/2018', 'MM/DD/YYYY'), 23, 'Monthly', 8000, 80000,634622236);
insert into PAYROLL values ('P0009', TO_DATE('08/1/2018', 'MM/DD/YYYY'), 184, 'Hourly', 5520, 39090,557279280);
insert into PAYROLL values ('P0010', TO_DATE('09/1/2018', 'MM/DD/YYYY'), 189, 'Hourly', 5670, 44760,557279280);
insert into PAYROLL values ('P0011', TO_DATE('10/1/2018', 'MM/DD/YYYY'), 175, 'Hourly', 5250, 50010,557279280);
insert into PAYROLL values ('P0012', TO_DATE('11/1/2018', 'MM/DD/YYYY'), 190, 'Hourly', 5700, 55710,557279280);

/* 	Cartype schema: | Brand | Model | YEAR | ServiceA_mile | ServiceB_mile | Service C mile	*/
insert into CARTYPE values ('Honda', 'Civic',null,14000, 29000, 44000);
insert into CARTYPE values ('Toyota', 'Corolla',null,5000, 25000, 45000);
insert into CARTYPE values ('Nissan', 'Altima',null,10000, 25000, 50000);
insert into CARTYPE values ('Honda', 'Accord',null,15000, 37000, 67000);
insert into CARTYPE values ('Nissan', 'Rogue',null,10000, 37000, 70000);
insert into CARTYPE values ('Toyota', 'Prius',null,10000, 28000, 58000);

/* 	Customer schema: | customer_id | customer_phone | customer_addr | customer_name | customer_email |	*/
insert into CUSTOMER values (1001, 1234567890, '203, Park St, Raleigh, NC - 27603', 'Tom Cruise', 'ethanhunt@gmail.com', 'S0001');
/* 	Vehicle schema: | Plate | purchased_date | mileage | made_year | last_servicetype | last_servicedate	*/	
insert into VEHICLE values ('XYZ-5643', TO_DATE('12/24/2009', 'MM/DD/YYYY'), 90452, 2009, 'Service C', TO_DATE('09/10/2018', 'MM/DD/YYYY'), 'Honda', 'Civic', 1001);
insert into VEHICLE values ('AHS-3132', TO_DATE('01/02/2011', 'MM/DD/YYYY'), 65452, 2007, 'Repair', TO_DATE('08/06/2018', 'MM/DD/YYYY'), 'Toyota', 'Prius', 1001);

insert into CUSTOMER values (1002, 9989877791, '12-A, High St, Raleigh, NC - 27605', 'Robert Downey Jr.', 'jarvis@gmail.com', 'S0001');
insert into VEHICLE values ('IRM-1212', TO_DATE('09/07/2002', 'MM/DD/YYYY'), 210452, 2001, 'Service A', TO_DATE('02/11/2018', 'MM/DD/YYYY'), 'Nissan', 'Altima', 1002);

insert into CUSTOMER values (1003, 8179827199, '3rd Ave, Charlotte, NC - 28134', 'Taylor Swift', 'lovestory@gmail.com', 'S0002');
insert into VEHICLE values ('TSW-3462', TO_DATE('12/09/2015', 'MM/DD/YYYY'), null, 2015, null, null, 'Honda', 'Accord', 1003);
insert into VEHICLE values ('DEL-8888', TO_DATE('05/11/2016', 'MM/DD/YYYY'), 31209, 2014, 'Service A', TO_DATE('02/11/2018', 'MM/DD/YYYY'), 'Nissan', 'Rogue', 1003);

insert into CUSTOMER values (1004, 8179827199, '90, Gorman St, Charlotte, NC - 28201', 'Serena Williams', 'venus@gmail.com', 'S0002');
insert into VEHICLE values ('P11-212A', TO_DATE('04/14/2010', 'MM/DD/YYYY'), 60452, 2009, 'Service A', TO_DATE('09/01/2017', 'MM/DD/YYYY'), 'Honda', 'Accord', 1004);
insert into VEHICLE values ('WIM-BLE5', TO_DATE('03/01/2014', 'MM/DD/YYYY'), 19876, 2013, 'Service B', TO_DATE('11/11/2016', 'MM/DD/YYYY'), 'Toyota', 'Prius', 1004);

/* 	Inventory schema: | PART_ID | Part_Name | Cartype | Part_price | Warranty |	*/	
insert into INVENTORY values ('PH001','Air Filter', 'Honda',  59, 'N/A', 3, 'D0002');
/* 	Center Has Inventory: | Part_Name | Cartype | Center_id | min_quan | min_order | current_quan |	*/		
insert into CENTER_HAS_INVENTORY values ('PH001', 'S0001', 20, 50, 43);
insert into CENTER_HAS_INVENTORY values ('PH001', 'S0002', 35, 6, 46);
/* Inventory by distributor: | Part_name | Part_Brand | Distributor_id | Windows | */
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH001', 'D0002', '3');

insert into INVENTORY values ('PH002','Axel Rod', 'Honda', 141, '2', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH002', 'S0001', 5, 7, 8);
insert into CENTER_HAS_INVENTORY values ('PH002', 'S0002', 6, 10, 7);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH002', 'D0001', '5');

insert into INVENTORY values ('PH003','Battery', 'Honda', 79, '3', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH003', 'S0001', 20, 20, 5);
insert into CENTER_HAS_INVENTORY values ('PH003', 'S0002', 52, 3, 64);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH003', 'D0001', '4');

insert into INVENTORY values ('PH004','Brake Fluid', 'Honda', 75, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH004', 'S0001', 4, 6, 14);
insert into CENTER_HAS_INVENTORY values ('PH004', 'S0002', 3, 3, 15);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH004', 'D0002', '4');

insert into INVENTORY values ('PH005','Brake Shoes', 'Honda', 41, '3', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH005', 'S0001', 4, 3, 8);
insert into CENTER_HAS_INVENTORY values ('PH005', 'S0002', 3, 5, 20);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH005', 'D0002', '5');

insert into INVENTORY values ('PH006','Camshaft', 'Honda', 511, '2', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH006', 'S0001', 20, 5, 22);
insert into CENTER_HAS_INVENTORY values ('PH006', 'S0002', 42, 42, 6);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH006', 'D0001', '4');

insert into INVENTORY values ('PH007','Catalytic converter', 'Honda', 716, '2', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH007', 'S0001', 10, 5, 31);
insert into CENTER_HAS_INVENTORY values ('PH007', 'S0002', 24, 5, 29);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH007', 'D0002', '3');

insert into INVENTORY values ('PH008','Coolant', 'Honda', 10, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH008', 'S0001', 23, 4, 23);
insert into CENTER_HAS_INVENTORY values ('PH008', 'S0002', 12, 4, 21);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH008', 'D0002', '4');

insert into INVENTORY values ('PH009','Drive belt', 'Honda', 1443, '1', 2, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH009', 'S0001', 30, 5, 35);
insert into CENTER_HAS_INVENTORY values ('PH009', 'S0002', 23, 4, 30);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH009', 'D0002', '2');

insert into INVENTORY values ('PH010','Engine oil', 'Honda', 27, 'N/A', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH010', 'S0001', 5, 5, 15);
insert into CENTER_HAS_INVENTORY values ('PH010', 'S0002', 10, 5, 26);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH010', 'D0001', '2');

insert into INVENTORY values ('PH011','Gears', 'Honda', 1344, '3', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH011', 'S0001', 3, 5, 18);
insert into CENTER_HAS_INVENTORY values ('PH011', 'S0002', 7, 5, 70);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH011', 'D0001', '5');

insert into INVENTORY values ('PH012','Light assembly', 'Honda', 1342, '2', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH012', 'S0001', 10, 8, 12);
insert into CENTER_HAS_INVENTORY values ('PH012', 'S0002', 6, 3, 8);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH012', 'D0001', '3');

insert into INVENTORY values ('PH013','Oil Filter', 'Honda', 42, 'N/A', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH013', 'S0001', 4, 4, 11);
insert into CENTER_HAS_INVENTORY values ('PH013', 'S0002', 24, 5, 31);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH013', 'D0001', '4');

insert into INVENTORY values ('PH014','Piston', 'Honda', 1445, '1', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH014', 'S0001', 48, 2, 55);
insert into CENTER_HAS_INVENTORY values ('PH014', 'S0002', 73, 5, 81);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH014', 'D0001', '2');

insert into INVENTORY values ('PH015','Power Steering Fluid', 'Honda', 24, 'N/A', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH015', 'S0001', 7, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PH015', 'S0002', 6, 4, 17);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH015', 'D0001', '5');

insert into INVENTORY values ('PH016','Spark plug', 'Honda', 50, '2', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH016', 'S0001', 42, 5, 45);
insert into CENTER_HAS_INVENTORY values ('PH016', 'S0002', 32, 4, 50);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH016', 'D0002', '5');

insert into INVENTORY values ('PH017','Suspension fluid', 'Honda', 77, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH017', 'S0001', 11, 5, 13);
insert into CENTER_HAS_INVENTORY values ('PH017', 'S0002', 6, 6, 15);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH017', 'D0002', '3');

insert into INVENTORY values ('PH018','Valve', 'Honda', 1261, '2', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PH018', 'S0001', 7, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PH018', 'S0002', 3, 6, 11);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH018', 'D0001', '3');

insert into INVENTORY values ('PH019','Wiper Fluid', 'Honda', 56, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PH019', 'S0001', 5, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PH019', 'S0002', 13, 5, 16);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PH019', 'D0002', '3');

insert into INVENTORY values ('PT001','Air Filter', 'Toyota',  11, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT001', 'S0001', 2, 5, 13);
insert into CENTER_HAS_INVENTORY values ('PT001', 'S0002', 5, 6, 26);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT001', 'D0002', '3');

insert into INVENTORY values ('PT002','Axel Rod', 'Toyota', 123, '2', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT002', 'S0001', 5, 7, 18);
insert into CENTER_HAS_INVENTORY values ('PT002', 'S0002', 6, 10, 27);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT002', 'D0001', '5');

insert into INVENTORY values ('PT003','Battery', 'Toyota', 63, '6', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT003', 'S0001', 2, 5 ,10);
insert into CENTER_HAS_INVENTORY values ('PT003', 'S0002', 2, 3 ,24);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT003', 'D0001', '4');

insert into INVENTORY values ('PT004','Brake Fluid', 'Toyota', 24, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT004', 'S0001', 4, 6, 4);
insert into CENTER_HAS_INVENTORY values ('PT004', 'S0002', 3, 3 ,25);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT004', 'D0002', '4');

insert into INVENTORY values ('PT005','Brake Shoes', 'Toyota', 62, '2', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT005', 'S0001', 4, 3, 18);
insert into CENTER_HAS_INVENTORY values ('PT005', 'S0002', 3, 5 ,20);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT005', 'D0002', '5');

insert into INVENTORY values ('PT006','Camshaft', 'Toyota', 1428, '3', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT006', 'S0001', 20, 5, 22);
insert into CENTER_HAS_INVENTORY values ('PT006', 'S0002', 42, 42 ,6);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT006', 'D0001', '4');

insert into INVENTORY values ('PT007','Catalytic converter', 'Toyota', 801, '1', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT007', 'S0001', 2, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PT007', 'S0002', 2, 6 ,22);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT007', 'D0002', '3');

insert into INVENTORY values ('PT008','Coolant', 'Toyota', 63, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT008', 'S0001', 2, 4, 13);
insert into CENTER_HAS_INVENTORY values ('PT008', 'S0002', 2, 4 ,21);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT008', 'D0002', '4');

insert into INVENTORY values ('PT009','Drive belt', 'Toyota', 528, '1', 2, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT009', 'S0001', 3, 5, 15);
insert into CENTER_HAS_INVENTORY values ('PT009', 'S0002', 3, 5 ,20);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT009', 'D0002', '2');

insert into INVENTORY values ('PT010','Engine oil', 'Toyota', 63, 'N/A', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT010', 'S0001', 5, 5, 15);
insert into CENTER_HAS_INVENTORY values ('PT010', 'S0002', 20, 5 ,26);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT010', 'D0001', '2');

insert into INVENTORY values ('PT011','Gears', 'Toyota', 523, '1', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT011', 'S0001', 3, 5, 8);
insert into CENTER_HAS_INVENTORY values ('PT011', 'S0002', 7, 5 ,20);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT011', 'D0001', '5');

insert into INVENTORY values ('PT012','Light assembly', 'Toyota', 617, '2', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT012', 'S0001', 1, 8, 2);
insert into CENTER_HAS_INVENTORY values ('PT012', 'S0002', 6, 3 ,28);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT012', 'D0001', '3');

insert into INVENTORY values ('PT013','Oil Filter', 'Toyota', 36, 'N/A', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT013', 'S0001', 1, 4, 1);
insert into CENTER_HAS_INVENTORY values ('PT013', 'S0002', 4, 5 ,21);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT013', 'D0001', '4');

insert into INVENTORY values ('PT014','Piston', 'Toyota', 1256, '1', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT014', 'S0001', 4, 2, 15);
insert into CENTER_HAS_INVENTORY values ('PT014', 'S0002', 3, 5 ,21);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT014', 'D0001', '2');

insert into INVENTORY values ('PT015','Power Steering Fluid', 'Toyota', 17, 'N/A', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT015', 'S0001', 1, 5, 2);
insert into CENTER_HAS_INVENTORY values ('PT015', 'S0002', 6, 4 ,27);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT015', 'D0001', '5');

insert into INVENTORY values ('PT016','Spark plug', 'Toyota', 64, '2', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT016', 'S0001', 4, 5, 15);
insert into CENTER_HAS_INVENTORY values ('PT016', 'S0002', 2, 4 ,20);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT016', 'D0002', '5');

insert into INVENTORY values ('PT017','Suspension fluid', 'Toyota', 70, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT017', 'S0001', 1, 5, 3);
insert into CENTER_HAS_INVENTORY values ('PT017', 'S0002', 6, 6 ,25);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT017', 'D0002', '3');

insert into INVENTORY values ('PT018','Valve', 'Toyota', 1338, '1', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PT018', 'S0001', 7, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PT018', 'S0002', 3, 6 ,11);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT018', 'D0001', '3');

insert into INVENTORY values ('PT019','Wiper Fluid', 'Toyota', 28, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PT019', 'S0001', 5, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PT019', 'S0002', 13, 5 ,16);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PT019', 'D0002', '3');

insert into INVENTORY values ('PN001','Air Filter', 'Nissan',  61, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN001', 'S0001', 20, 5 ,33);
insert into CENTER_HAS_INVENTORY values ('PN001', 'S0002', 5, 6 ,16);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PN001', 'D0002', '3');

insert into INVENTORY values ('PN002','Axel Rod', 'Nissan', 241, '3', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN002', 'S0001', 5, 7 ,38);
insert into CENTER_HAS_INVENTORY values ('PN002', 'S0002', 6, 10 ,17);
-- insert into INVENTORY_BY_DISTRIBUTOR values ('PN002', 'D0001', '5');

insert into INVENTORY values ('PN003','Battery', 'Nissan', 14, '3', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN003', 'S0001', 20, 5 ,30);
insert into CENTER_HAS_INVENTORY values ('PN003', 'S0002', 5, 3 ,14);

insert into INVENTORY values ('PN004','Brake Fluid', 'Nissan', 16, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN004', 'S0001', 4, 6, 34);
insert into CENTER_HAS_INVENTORY values ('PN004', 'S0002', 3, 3 ,15);

insert into INVENTORY values ('PN005','Brake Shoes', 'Nissan', 47, '1', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN005', 'S0001', 4, 3, 38);
insert into CENTER_HAS_INVENTORY values ('PN005', 'S0002', 3, 5 ,10);

insert into INVENTORY values ('PN006','Camshaft', 'Nissan', 1295, '2', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN006', 'S0001', 20, 5, 32);
insert into CENTER_HAS_INVENTORY values ('PN006', 'S0002', 4, 6, 12);

insert into INVENTORY values ('PN007','Catalytic converter', 'Nissan', 589, '1', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN007', 'S0001', 10, 5, 31);
insert into CENTER_HAS_INVENTORY values ('PN007', 'S0002', 4, 5 , 19);

insert into INVENTORY values ('PN008','Coolant', 'Nissan', 39, 'N/A', 4, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN008', 'S0001', 23, 4, 33);
insert into CENTER_HAS_INVENTORY values ('PN008', 'S0002', 2, 4 ,11);

insert into INVENTORY values ('PN009','Drive belt', 'Nissan', 1084, '3', 2, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN009', 'S0001', 30, 5, 35);
insert into CENTER_HAS_INVENTORY values ('PN009', 'S0002', 3, 4 ,10);

insert into INVENTORY values ('PN010','Engine oil', 'Nissan', 14, 'N/A', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN010', 'S0001', 5, 5, 35);
insert into CENTER_HAS_INVENTORY values ('PN010', 'S0002', 10, 5 ,16);

insert into INVENTORY values ('PN011','Gears', 'Nissan', 1176, '3', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN011', 'S0001', 3, 5, 38);
insert into CENTER_HAS_INVENTORY values ('PN011', 'S0002', 7, 5 ,10);

insert into INVENTORY values ('PN012','Light assembly', 'Nissan', 1367, '2', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN012', 'S0001', 10, 8, 32);
insert into CENTER_HAS_INVENTORY values ('PN012', 'S0002', 6, 3 ,18);

insert into INVENTORY values ('PN013','Oil Filter', 'Nissan', 61, 'N/A', 4, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN013', 'S0001', 4, 4, 31);
insert into CENTER_HAS_INVENTORY values ('PN013', 'S0002', 4, 5 ,11);

insert into INVENTORY values ('PN014','Piston', 'Nissan', 1341, '3', 2, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN014', 'S0001', 18, 2, 35);
insert into CENTER_HAS_INVENTORY values ('PN014', 'S0002', 7, 5 ,11);

insert into INVENTORY values ('PN015','Power Steering Fluid', 'Nissan', 20, 'N/A', 5, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN015', 'S0001', 7, 5, 32);
insert into CENTER_HAS_INVENTORY values ('PN015', 'S0002', 6, 4 ,17);

insert into INVENTORY values ('PN016', 'Spark plug', 'Nissan', 11, '1', 5, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN016', 'S0001', 12, 5, 35);
insert into CENTER_HAS_INVENTORY values ('PN016', 'S0002', 2, 4 ,10);

insert into INVENTORY values ('PN017', 'Suspension fluid', 'Nissan', 32, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN017', 'S0001', 11, 5, 33);
insert into CENTER_HAS_INVENTORY values ('PN017', 'S0002', 6, 6 ,15);

insert into INVENTORY values ('PN018', 'Valve', 'Nissan', 1013, '1', 3, 'D0001');
insert into CENTER_HAS_INVENTORY values ('PN018', 'S0001', 7, 5, 32);
insert into CENTER_HAS_INVENTORY values ('PN018', 'S0002', 3, 6 ,11);

insert into INVENTORY values ('PN019', 'Wiper Fluid', 'Nissan', 31, 'N/A', 3, 'D0002');
insert into CENTER_HAS_INVENTORY values ('PN019', 'S0001', 5, 5, 12);
insert into CENTER_HAS_INVENTORY values ('PN019', 'S0002', 13, 5 ,16);

/* 	Basic service schema: Basic_service_id | car_model | service_name | charge_level | charge_money | required_time (hr) | part_quan | PART_ID |	*/	
insert into BASIC_SERVICE values ('BSTC001','Corolla','Air filter change', 'low', 50, 0.25, 1,'PT001');
insert into BASIC_SERVICE values ('BSTP001','Prius','Air filter change', 'low', 50, 0.25, 1,'PT001');
insert into BASIC_SERVICE values ('BSHC001','Civic','Air filter change', 'low', 50, 0.25, 1,'PH001');
insert into BASIC_SERVICE values ('BSHA001','Accord','Air filter change', 'low', 50, 0.25, 2,'PH001');
insert into BASIC_SERVICE values ('BSNA001','Altima','Air filter change', 'low', 50,  0.25, 1,'PN001');
insert into BASIC_SERVICE values ('BSNR001','Rogue','Air filter change', 'low', 50, 0.25, 3,'PN001');

insert into BASIC_SERVICE values ('BSTC002','Corolla','Battery replacement', 'low', 50,  0.25, 1,'PT003');
insert into BASIC_SERVICE values ('BSTP002','Prius','Battery replacement', 'low', 50, 0.25, 1,'PT003');
insert into BASIC_SERVICE values ('BSHC002','Civic','Battery replacement', 'low', 50, 0.25, 1,'PH003');
insert into BASIC_SERVICE values ('BSHA002','Accord','Battery replacement', 'low', 50, 0.25, 1,'PH003');
insert into BASIC_SERVICE values ('BSNA002','Altima','Battery replacement', 'low', 50, 0.25, 1,'PN003');
insert into BASIC_SERVICE values ('BSNR002','Rogue','Battery replacement', 'low', 50, 0.25, 2,'PN003');

insert into BASIC_SERVICE values ('BSTC003','Corolla','Brake check', 'low', 50, 0.25, 1,'PT004');
insert into BASIC_SERVICE values ('BSTP003','Prius','Brake check', 'low', 50, 0.25, 1,'PT004');
insert into BASIC_SERVICE values ('BSHC003','Civic','Brake check', 'low', 50, 0.25, 1,'PH004');
insert into BASIC_SERVICE values ('BSHA003','Accord','Brake check', 'low', 50, 0.25, 1,'PH004');
insert into BASIC_SERVICE values ('BSNA003','Altima','Brake check', 'low', 50, 0.25, 1,'PN004');
insert into BASIC_SERVICE values ('BSNR003','Rogue','Brake check', 'low', 50, 0.25, 2,'PN004');

insert into BASIC_SERVICE values ('BSTC004','Corolla','Brake repair', 'low', 50, 0.25, 4,'PT005');
insert into BASIC_SERVICE values ('BSTP004','Prius','Brake repair', 'low', 50, 0.25, 4,'PT005');
insert into BASIC_SERVICE values ('BSHC004','Civic','Brake repair', 'low', 50, 0.25, 4,'PH005');
insert into BASIC_SERVICE values ('BSHA004','Accord','Brake repair', 'low', 50, 0.25, 4,'PH005');
insert into BASIC_SERVICE values ('BSNA004','Altima','Brake repair', 'low', 50, 0.25, 4,'PN005');
insert into BASIC_SERVICE values ('BSNR004','Rogue','Brake repair', 'low', 50, 0.25, 4,'PN005');

insert into BASIC_SERVICE values ('BSTC005','Corolla','Camshaft replacement', 'high', 65, 1, 1,'PT006');
insert into BASIC_SERVICE values ('BSTP005','Prius','Camshaft replacement', 'high', 65, 1, 1,'PT006');
insert into BASIC_SERVICE values ('BSHC005','Civic','Camshaft replacement', 'high', 65, 1, 1,'PH006');
insert into BASIC_SERVICE values ('BSHA005','Accord','Camshaft replacement', 'high', 65, 1, 1,'PH006');
insert into BASIC_SERVICE values ('BSNA005','Altima','Camshaft replacement', 'high', 65, 1, 1,'PN006');
insert into BASIC_SERVICE values ('BSNR005','Rogue','Camshaft replacement', 'high', 65, 1, 2,'PN006');

insert into BASIC_SERVICE values ('BSTC006','Corolla','Catalytic convertor replacement', 'high', 65, 1, 1,'PT007');
insert into BASIC_SERVICE values ('BSTP006','Prius','Catalytic convertor replacement', 'high', 65, 1, 1,'PT007');
insert into BASIC_SERVICE values ('BSHC006','Civic','Catalytic convertor replacement', 'high', 65, 1, 1,'PH007');
insert into BASIC_SERVICE values ('BSHA006','Accord','Catalytic convertor replacement', 'high', 65, 1, 1,'PH007');
insert into BASIC_SERVICE values ('BSNA006','Altima','Catalytic convertor replacement', 'high', 65, 1, 1,'PN007');
insert into BASIC_SERVICE values ('BSNR006','Rogue','Catalytic convertor replacement', 'high', 65, 1, 1,'PN007');

insert into BASIC_SERVICE values ('BSTC007','Corolla','Coolant recycle', 'low', 50, 0.25, 1,'PT008');
insert into BASIC_SERVICE values ('BSTP007','Prius','Coolant recycle', 'low', 50, 0.25, 1,'PT008');
insert into BASIC_SERVICE values ('BSHC007','Civic','Coolant recycle', 'low', 50, 0.25, 1,'PH008');
insert into BASIC_SERVICE values ('BSHA007','Accord','Coolant recycle', 'low', 50, 0.25, 1,'PH008');
insert into BASIC_SERVICE values ('BSNA007','Altima','Coolant recycle', 'low', 50, 0.25, 2,'PN008');
insert into BASIC_SERVICE values ('BSNR007','Rogue','Coolant recycle', 'low', 50, 0.25, 2,'PN008');

insert into BASIC_SERVICE values ('BSTC008','Corolla','Drive belt replacement', 'high', 65, 1, 1,'PT009');
insert into BASIC_SERVICE values ('BSTP008','Prius','Drive belt replacement', 'high', 65, 1, 1,'PT009');
insert into BASIC_SERVICE values ('BSHC008','Civic','Drive belt replacement', 'high', 65, 1, 1,'PH009');
insert into BASIC_SERVICE values ('BSHA008','Accord','Drive belt replacement', 'high', 65, 1, 1,'PH009');
insert into BASIC_SERVICE values ('BSNA008','Altima','Drive belt replacement', 'high', 65, 1, 1,'PN009');
insert into BASIC_SERVICE values ('BSNR008','Rogue','Drive belt replacement', 'high', 65, 1, 1,'PN009');

insert into BASIC_SERVICE values ('BSTC009','Corolla','Engine oil change', 'low', 50, 0.25, 1,'PT010');
insert into BASIC_SERVICE values ('BSTP009','Prius','Engine oil change', 'low', 50, 0.25, 1,'PT010');
insert into BASIC_SERVICE values ('BSHC009','Civic','Engine oil change', 'low', 50, 0.25, 1,'PH010');
insert into BASIC_SERVICE values ('BSHA009','Accord','Engine oil change', 'low', 50, 0.25, 1,'PH010');
insert into BASIC_SERVICE values ('BSNA009','Altima','Engine oil change', 'low', 50, 0.25, 2,'PN010');
insert into BASIC_SERVICE values ('BSNR009','Rogue','Engine oil change', 'low', 50, 0.25, 3,'PN010');

insert into BASIC_SERVICE values ('BSTC010','Corolla','Gearbox repair', 'high', 65, 1, 6,'PT011');
insert into BASIC_SERVICE values ('BSTP010','Prius','Gearbox repair', 'high', 65, 1, 6,'PT011');
insert into BASIC_SERVICE values ('BSHC010','Civic','Gearbox repair', 'high', 65, 1, 12,'PH011');
insert into BASIC_SERVICE values ('BSHA010','Accord','Gearbox repair', 'high', 65, 1, 7,'PH011');
insert into BASIC_SERVICE values ('BSNA010','Altima','Gearbox repair', 'high', 65, 1, 7,'PN011');
insert into BASIC_SERVICE values ('BSNR010','Rogue','Gearbox repair', 'high', 65, 1, 9,'PN011');

insert into BASIC_SERVICE values ('BSTC011','Corolla','Headlights replacement', 'low', 50, 0.5, 2,'PT012');
insert into BASIC_SERVICE values ('BSTP011','Prius','Headlights replacement', 'low', 50, 0.5, 2,'PT012');
insert into BASIC_SERVICE values ('BSHC011','Civic','Headlights replacement', 'low', 50, 0.5, 2,'PH012');
insert into BASIC_SERVICE values ('BSHA011','Accord','Headlights replacement', 'low', 50, 0.5, 2,'PH012');
insert into BASIC_SERVICE values ('BSNA011','Altima','Headlights replacement', 'low', 50, 0.5, 2,'PN012');
insert into BASIC_SERVICE values ('BSNR011','Rogue','Headlights replacement', 'low', 50, 0.5, 2,'PN012');

insert into BASIC_SERVICE values ('BSTC012','Corolla','Oil filter change', 'low', 50, 0.25, 1,'PT013');
insert into BASIC_SERVICE values ('BSTP012','Prius','Oil filter change', 'low', 50, 0.25, 1,'PT013');
insert into BASIC_SERVICE values ('BSHC012','Civic','Oil filter change', 'low', 50, 0.25, 1,'PH013');
insert into BASIC_SERVICE values ('BSHA012','Accord','Oil filter change', 'low', 50, 0.25, 1,'PH013');
insert into BASIC_SERVICE values ('BSNA012','Altima','Oil filter change', 'low', 50, 0.25, 1,'PN013');
insert into BASIC_SERVICE values ('BSNR012','Rogue','Oil filter change', 'low', 50, 0.25, 2,'PN013');

insert into BASIC_SERVICE values ('BSTC013','Corolla','Piston replacement', 'high', 65, 1, 4,'PT014');
insert into BASIC_SERVICE values ('BSTP013','Prius','Piston replacement', 'high', 65, 1, 4,'PT014');
insert into BASIC_SERVICE values ('BSHC013','Civic','Piston replacement', 'high', 65, 1, 4,'PH014');
insert into BASIC_SERVICE values ('BSHA013','Accord','Piston replacement', 'high', 65, 1, 4,'PH014');
insert into BASIC_SERVICE values ('BSNA013','Altima','Piston replacement', 'high', 65, 1, 6,'PN014');
insert into BASIC_SERVICE values ('BSNR013','Rogue','Piston replacement', 'high', 65, 1, 8,'PN014');

insert into BASIC_SERVICE values ('BSTC014','Corolla','Power steering check', 'low', 50, 0.25, 1,'PT015');
insert into BASIC_SERVICE values ('BSTP014','Prius','Power steering check', 'low', 50, 0.25, 1,'PT015');
insert into BASIC_SERVICE values ('BSHC014','Civic','Power steering check', 'low', 50, 0.25, 1,'PH015');
insert into BASIC_SERVICE values ('BSHA014','Accord','Power steering check', 'low', 50, 0.25, 1,'PH015');
insert into BASIC_SERVICE values ('BSNA014','Altima','Power steering check', 'low', 50, 0.25, 1,'PN015');
insert into BASIC_SERVICE values ('BSNR014','Rogue','Power steering check', 'low', 50, 0.25, 1,'PN015');

insert into BASIC_SERVICE values ('BSTC015','Corolla','Spark plugs replacement', 'low', 50, 0.25, 4,'PT016');
insert into BASIC_SERVICE values ('BSTP015','Prius','Spark plugs replacement', 'low', 50, 0.25, 4,'PT016');
insert into BASIC_SERVICE values ('BSHC015','Civic','Spark plugs replacement', 'low', 50, 0.25, 4,'PH016');
insert into BASIC_SERVICE values ('BSHA015','Accord','Spark plugs replacement', 'low', 50, 0.25, 4,'PH016');
insert into BASIC_SERVICE values ('BSNA015','Altima','Spark plugs replacement', 'low', 50, 0.25, 6,'PN016');
insert into BASIC_SERVICE values ('BSNR015','Rogue','Spark plugs replacement', 'low', 50, 0.25, 8,'PN016');

insert into BASIC_SERVICE values ('BSTC016','Corolla','Suspension check', 'low', 50, 0.25, 1,'PT017');
insert into BASIC_SERVICE values ('BSTP016','Prius','Suspension check', 'low', 50, 0.25, 1,'PT017');
insert into BASIC_SERVICE values ('BSHC016','Civic','Suspension check', 'low', 50, 0.25, 1,'PH017');
insert into BASIC_SERVICE values ('BSHA016','Accord','Suspension check', 'low', 50, 0.25, 1,'PH017');
insert into BASIC_SERVICE values ('BSNA016','Altima','Suspension check', 'low', 50, 0.25, 1,'PN017');
insert into BASIC_SERVICE values ('BSNR016','Rogue','Suspension check', 'low', 50, 0.25, 1,'PN017');

insert into BASIC_SERVICE values ('BSTC017','Corolla','Tail lights replacement', 'low', 50, 0.5, 2,'PT012');
insert into BASIC_SERVICE values ('BSTP017','Prius','Tail lights replacement', 'low', 50, 0.5, 2,'PT012');
insert into BASIC_SERVICE values ('BSHC017','Civic','Tail lights replacement', 'low', 50, 0.5, 2,'PH012');
insert into BASIC_SERVICE values ('BSHA017','Accord','Tail lights replacement', 'low', 50, 0.5, 2,'PH012');
insert into BASIC_SERVICE values ('BSNA017','Altima','Tail lights replacement', 'low', 50, 0.5, 2,'PN012');
insert into BASIC_SERVICE values ('BSNR017','Rogue','Tail lights replacement', 'low', 50, 0.5, 2,'PN012');

insert into BASIC_SERVICE values ('BSTC018','Corolla','Turn lights replacement', 'low', 50, 0.5, 4,'PT012');
insert into BASIC_SERVICE values ('BSTP018','Prius','Turn lights replacement', 'low', 50, 0.5, 4,'PT012');
insert into BASIC_SERVICE values ('BSHC018','Civic','Turn lights replacement', 'low', 50, 0.5, 4,'PH012');
insert into BASIC_SERVICE values ('BSHA018','Accord','Turn lights replacement', 'low', 50, 0.5, 4,'PH012');
insert into BASIC_SERVICE values ('BSNA018','Altima','Turn lights replacement', 'low', 50, 0.5, 4,'PN012');
insert into BASIC_SERVICE values ('BSNR018','Rogue','Turn lights replacement', 'low', 50, 0.5, 4,'PN012');

insert into BASIC_SERVICE values ('BSTC019','Corolla','Valve replacement', 'high', 65, 1, 4,'PT018');
insert into BASIC_SERVICE values ('BSTP019','Prius','Valve replacement', 'high', 65, 1, 4,'PT018');
insert into BASIC_SERVICE values ('BSHC019','Civic','Valve replacement', 'high', 65, 1, 4,'PH018');
insert into BASIC_SERVICE values ('BSHA019','Accord','Valve replacement', 'high', 65, 1, 4,'PH018');
insert into BASIC_SERVICE values ('BSNA019','Altima','Valve replacement', 'high', 65, 1, 6,'PN018');
insert into BASIC_SERVICE values ('BSNR019','Rogue','Valve replacement', 'high', 65, 1, 8,'PN018');

insert into BASIC_SERVICE values ('BSTC020','Corolla','Wheel alignment', 'high', 65, 1, 2,'PT002');
insert into BASIC_SERVICE values ('BSTP020','Prius','Wheel alignment', 'high', 65, 1, 2,'PT002');
insert into BASIC_SERVICE values ('BSHC020','Civic','Wheel alignment', 'high', 65, 1, 2,'PH002');
insert into BASIC_SERVICE values ('BSHA020','Accord','Wheel alignment', 'high', 65, 1, 2,'PH002');
insert into BASIC_SERVICE values ('BSNA020','Altima','Wheel alignment', 'high', 65, 1, 2,'PN002');
insert into BASIC_SERVICE values ('BSNR020','Rogue','Wheel alignment', 'high', 65, 1, 2,'PN002');

insert into BASIC_SERVICE values ('BSTC021','Corolla','Wiper check', 'low', 50, 0.25, 1,'PT019');
insert into BASIC_SERVICE values ('BSTP021','Prius','Wiper check', 'low', 50, 0.25, 1,'PT019');
insert into BASIC_SERVICE values ('BSHC021','Civic','Wiper check', 'low', 50, 0.25, 1,'PH019');
insert into BASIC_SERVICE values ('BSHA021','Accord','Wiper check', 'low', 50, 0.25, 1,'PH019');
insert into BASIC_SERVICE values ('BSNA021','Altima','Wiper check', 'low', 50, 0.25, 1,'PN019');
insert into BASIC_SERVICE values ('BSNR021','Rogue','Wiper check', 'low', 50, 0.25, 1,'PN019');

/* CARTYPE_HAS_BASIC_SERVICE: | BRAND | MODEL | BASIC_SERVICE ID | */
/* Toyota Corlloa */

insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service A','BSTC009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service A','BSTC007');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service A','BSTP009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service A','BSTP012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service A','BSTP007');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service A','BSHC007');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service A','BSHC003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service A','BSHC009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service A','BSHA009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service A','BSHA001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service A','BSHA012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service A','BSHA007');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service A','BSNA009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service A','BSNA001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service A','BSNA012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service A','BSNA007');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR009');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service A','BSNR007');

insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service B','BSTC001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service B','BSTC012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service B','BSTC003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service B','BSTC021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service B','BSTC015');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service B','BSTP001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service B','BSTP003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service B','BSTP021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service B','BSTP015');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service B','BSTP002');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service B','BSHC001');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service B','BSHC012');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service B','BSHC004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service B','BSHC021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service B','BSHC015');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service B','BSHA003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service B','BSHA021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service B','BSHA015');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service B','BSNA003');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service B','BSNA021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR021');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR015');

insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service C','BSTC016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service C','BSTC004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Corolla','Service C','BSTC014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service C','BSTP016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service C','BSTP004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Toyota', 'Prius','Service C','BSTP014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service C','BSHC016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Civic','Service C','BSHC014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service C','BSHA016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service C','BSHA004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Honda', 'Accord','Service C','BSHA014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service C','BSNA016');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service C','BSNA004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service C','BSNA014');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Altima','Service C','BSNA015');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service C','BSNR004');
insert into CARTYPE_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service C','BSNR002');


/* appointment schemas: | appointment_id | service_type (M/R) | service_specify | Mechanics | Plate | */
insert into APPOINTMENT values ('SH001', 'Maintenance', 'Service C', 'Jacob Gloss', TO_DATE('09/10/2018', 'MM/DD/YYYY'),'10:00','10:30', 'XYZ-5643', 1001);
insert into APPOINTMENT values ('SH002', 'Maintenance', 'Service B', 'Eric Fowler', TO_DATE('02/25/2018', 'MM/DD/YYYY'),'09:00','10:30', 'XYZ-5643', 1001);
insert into APPOINTMENT values ('SH003', 'Maintenance', 'Service A', 'Anthony Freeman', TO_DATE('10/15/2017', 'MM/DD/YYYY'),'08:00','09:00', 'XYZ-5643', 1001);
insert into APPOINTMENT values ('SH004', 'Repair', 'Battery Does not Hold Charge', 'Roland Richmond',TO_DATE('08/06/2018', 'MM/DD/YYYY'),'08:00','08:30', 'AHS-3132', 1001);
insert into APPOINTMENT values ('SH005', 'Maintenance','Service B', 'Peter Fitzpatrick',TO_DATE('05/15/2018', 'MM/DD/YYYY'),'10:30','12:00', 'AHS-3132', 1001);
insert into APPOINTMENT values ('SH006', 'Maintenance','Service A', 'Peter Fitzpatrick',TO_DATE('01/28/2018', 'MM/DD/YYYY'),'12:00','13:00', 'AHS-3132', 1001);
insert into APPOINTMENT values ('SH007', 'Maintenance','Service A', 'Eric Fowler',TO_DATE('02/11/2018', 'MM/DD/YYYY'),'08:30','09:30', 'IRM-1212', 1002);
insert into APPOINTMENT values ('SH008', 'Maintenance','Service C', 'Eric Fowler',TO_DATE('12/10/2017', 'MM/DD/YYYY'),'09:30','10:00', 'IRM-1212', 1002);
insert into APPOINTMENT values ('SH009', 'Maintenance','Service B', 'Eric Fowler',TO_DATE('01/20/2017', 'MM/DD/YYYY'),'10:00','11:30', 'IRM-1212', 1002);
insert into APPOINTMENT values ('SH010', 'Maintenance','Service A', 'Rickie Henderson',TO_DATE('02/11/2018', 'MM/DD/YYYY'),'08:30','09:30', 'DEL-8888', 1003);
insert into APPOINTMENT values ('SH011', 'Repair', 'Headlamps/Tail lamps not working', 'Charles Pudilo',TO_DATE('11/05/2016', 'MM/DD/YYYY'),'09:00','10:30', 'DEL-8888', 1003);
insert into APPOINTMENT values ('SH012', 'Maintenance','Service B', 'Dustin Esparza',TO_DATE('09/01/2017', 'MM/DD/YYYY'),'09:00','09:30', 'P11-212A', 1004);
insert into APPOINTMENT values ('SH013', 'Maintenance','Service A', 'James Rivera',TO_DATE('06/15/2014', 'MM/DD/YYYY'),'08:30','10:00', 'P11-212A', 1004);
insert into APPOINTMENT values ('SH014', 'Maintenance','Service A', 'James Rivera',TO_DATE('11/11/2016', 'MM/DD/YYYY'),'08:30','09:30', 'WIM-BLE5', 1004);
insert into APPOINTMENT values ('SH015', 'Repair', 'A/C-Heater not working', 'Charles Pudilo',TO_DATE('01/02/2016', 'MM/DD/YYYY'),'14:00','15:30', 'WIM-BLE5', 1004);
insert into APPOINTMENT values ('SH016', 'Repair', 'Engine knock', 'Charles Pudilo',TO_DATE('09/30/2015', 'MM/DD/YYYY'),'11:00','14:30', 'WIM-BLE5', 1004);


/* DATE_STATUS: CENTER_ID | APP_DATE | MAINTENANCE_NUM*/
insert into DATE_STATUS  values ('S0001',TO_DATE('11/13/2018', 'MM/DD/YYYY'),11);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/14/2018', 'MM/DD/YYYY'),10);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/15/2018', 'MM/DD/YYYY'),7);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/16/2018', 'MM/DD/YYYY'),0);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/19/2018', 'MM/DD/YYYY'),13);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/20/2018', 'MM/DD/YYYY'),5);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/21/2018', 'MM/DD/YYYY'),9);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/22/2018', 'MM/DD/YYYY'),12);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/23/2018', 'MM/DD/YYYY'),4);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/26/2018', 'MM/DD/YYYY'),9);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/27/2018', 'MM/DD/YYYY'),13);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/28/2018', 'MM/DD/YYYY'),2);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/29/2018', 'MM/DD/YYYY'),8);
insert into DATE_STATUS  values ('S0001',TO_DATE('11/30/2018', 'MM/DD/YYYY'),6);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/03/2018', 'MM/DD/YYYY'),4);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/04/2018', 'MM/DD/YYYY'),1);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/05/2018', 'MM/DD/YYYY'),7);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/06/2018', 'MM/DD/YYYY'),2);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/07/2018', 'MM/DD/YYYY'),0);
insert into DATE_STATUS  values ('S0001',TO_DATE('12/10/2018', 'MM/DD/YYYY'),0);


insert into DATE_STATUS  values ('S0002',TO_DATE('11/13/2018', 'MM/DD/YYYY'),11);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/14/2018', 'MM/DD/YYYY'),10);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/15/2018', 'MM/DD/YYYY'),7);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/16/2018', 'MM/DD/YYYY'),0);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/19/2018', 'MM/DD/YYYY'),13);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/20/2018', 'MM/DD/YYYY'),5);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/21/2018', 'MM/DD/YYYY'),9);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/22/2018', 'MM/DD/YYYY'),12);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/23/2018', 'MM/DD/YYYY'),4);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/26/2018', 'MM/DD/YYYY'),9);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/27/2018', 'MM/DD/YYYY'),13);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/28/2018', 'MM/DD/YYYY'),2);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/29/2018', 'MM/DD/YYYY'),8);
insert into DATE_STATUS  values ('S0002',TO_DATE('11/30/2018', 'MM/DD/YYYY'),6);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/03/2018', 'MM/DD/YYYY'),4);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/04/2018', 'MM/DD/YYYY'),1);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/05/2018', 'MM/DD/YYYY'),7);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/06/2018', 'MM/DD/YYYY'),2);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/07/2018', 'MM/DD/YYYY'),0);
insert into DATE_STATUS  values ('S0002',TO_DATE('12/10/2018', 'MM/DD/YYYY'),0);

/* TIME_AVALIABLE: CENTER_ID | THE_DATE | BEGINSLOT | ENDSLOT | */
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/13/2018', 'MM/DD/YYYY'),1,4);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/13/2018', 'MM/DD/YYYY'),21,22);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/14/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/15/2018', 'MM/DD/YYYY'),6,12);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/15/2018', 'MM/DD/YYYY'),13,16);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/15/2018', 'MM/DD/YYYY'),19,20);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/16/2018', 'MM/DD/YYYY'),7,16);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/19/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/20/2018', 'MM/DD/YYYY'),3,11);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/21/2018', 'MM/DD/YYYY'),1,3);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/21/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/21/2018', 'MM/DD/YYYY'),14,16);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/21/2018', 'MM/DD/YYYY'),19,20);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/22/2018', 'MM/DD/YYYY'),7,10);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/22/2018', 'MM/DD/YYYY'),13,16);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/23/2018', 'MM/DD/YYYY'),5,13);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/26/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/27/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/28/2018', 'MM/DD/YYYY'),1,7);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/28/2018', 'MM/DD/YYYY'),13,18);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/29/2018', 'MM/DD/YYYY'),4,10);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/29/2018', 'MM/DD/YYYY'),13,15);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/30/2018', 'MM/DD/YYYY'),2,6);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('11/30/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/03/2018', 'MM/DD/YYYY'),1,3);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/03/2018', 'MM/DD/YYYY'),8,12);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/04/2018', 'MM/DD/YYYY'),9,18);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/04/2018', 'MM/DD/YYYY'),20,21);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/05/2018', 'MM/DD/YYYY'),7,12);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/06/2018', 'MM/DD/YYYY'),9,14);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/06/2018', 'MM/DD/YYYY'),16,20);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/07/2018', 'MM/DD/YYYY'),1,23);
insert into TIME_AVALIABLE  values ('S0001',TO_DATE('12/10/2018', 'MM/DD/YYYY'),1,23);

insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/13/2018', 'MM/DD/YYYY'),1,4);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/13/2018', 'MM/DD/YYYY'),21,22);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/14/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/15/2018', 'MM/DD/YYYY'),6,12);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/15/2018', 'MM/DD/YYYY'),13,16);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/15/2018', 'MM/DD/YYYY'),19,20);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/16/2018', 'MM/DD/YYYY'),7,16);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/19/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/20/2018', 'MM/DD/YYYY'),3,11);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/21/2018', 'MM/DD/YYYY'),1,3);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/21/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/21/2018', 'MM/DD/YYYY'),14,16);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/21/2018', 'MM/DD/YYYY'),19,20);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/22/2018', 'MM/DD/YYYY'),7,10);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/22/2018', 'MM/DD/YYYY'),13,16);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/23/2018', 'MM/DD/YYYY'),5,13);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/26/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/27/2018', 'MM/DD/YYYY'),5,9);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/28/2018', 'MM/DD/YYYY'),1,7);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/28/2018', 'MM/DD/YYYY'),13,18);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/29/2018', 'MM/DD/YYYY'),4,10);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/29/2018', 'MM/DD/YYYY'),13,15);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/30/2018', 'MM/DD/YYYY'),2,6);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('11/30/2018', 'MM/DD/YYYY'),9,10);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/03/2018', 'MM/DD/YYYY'),1,3);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/03/2018', 'MM/DD/YYYY'),8,12);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/04/2018', 'MM/DD/YYYY'),9,18);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/04/2018', 'MM/DD/YYYY'),20,21);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/05/2018', 'MM/DD/YYYY'),7,12);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/06/2018', 'MM/DD/YYYY'),9,14);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/06/2018', 'MM/DD/YYYY'),16,20);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/07/2018', 'MM/DD/YYYY'),1,23);
insert into TIME_AVALIABLE  values ('S0002',TO_DATE('12/10/2018', 'MM/DD/YYYY'),1,23);

/* Timeslot : time_id | slot */
insert into TIMESLOT  values (1,'8:00');
insert into TIMESLOT  values (2,'8:30');
insert into TIMESLOT  values (3,'9:00');
insert into TIMESLOT  values (4,'9:30');
insert into TIMESLOT  values (5,'10:00');
insert into TIMESLOT  values (6,'10:30');
insert into TIMESLOT  values (7,'11:00');
insert into TIMESLOT  values (8,'11:30');
insert into TIMESLOT  values (9,'12:00');
insert into TIMESLOT  values (10,'12:30');
insert into TIMESLOT  values (11,'13:00');
insert into TIMESLOT  values (12,'13:30');
insert into TIMESLOT  values (13,'14:00');
insert into TIMESLOT  values (14,'14:30');
insert into TIMESLOT  values (15,'15:00');
insert into TIMESLOT  values (16,'15:30');
insert into TIMESLOT  values (17,'16:00');
insert into TIMESLOT  values (18,'16:30');
insert into TIMESLOT  values (19,'17:00');
insert into TIMESLOT  values (20,'17:30');
insert into TIMESLOT  values (21,'18:00');
insert into TIMESLOT  values (22,'18:30');
insert into TIMESLOT  values (23,'19:00');

/* PORDER */
insert into PORDER values ('O0001', TO_DATE('06/09/2014', 'MM/DD/YYYY'), TO_DATE('06/13/2014', 'MM/DD/YYYY'), TO_DATE('06/18/2014', 'MM/DD/YYYY'), 5, 'D0001', 'S0001', 'Complete','PT003');
insert into PORDER values ('O0002', TO_DATE('09/16/2015', 'MM/DD/YYYY'), TO_DATE('09/21/2015', 'MM/DD/YYYY'), TO_DATE('09/21/2015', 'MM/DD/YYYY'), 5, 'D0002', 'S0001', 'Complete','PH019');
insert into PORDER values ('O0003', TO_DATE('02/10/2016', 'MM/DD/YYYY'), TO_DATE('02/11/2016', 'MM/DD/YYYY'), TO_DATE('02/11/2016', 'MM/DD/YYYY'), 5, 'S0001', 'S0002', 'Complete','PH011');
insert into PORDER values ('O0004', TO_DATE('08/09/2017', 'MM/DD/YYYY'), TO_DATE('08/10/2017', 'MM/DD/YYYY'), TO_DATE('08/11/2017', 'MM/DD/YYYY'), 4, 'D0002', 'S0001', 'Complete','PN008');
insert into PORDER values ('O0005', TO_DATE('10/04/2018', 'MM/DD/YYYY'), TO_DATE('10/05/2018', 'MM/DD/YYYY'), TO_DATE('10/05/2018', 'MM/DD/YYYY'), 6, 'S0001', 'S0002', 'Complete','PT001');
insert into PORDER values ('O0006', TO_DATE('10/26/2018', 'MM/DD/YYYY'), TO_DATE('11/01/2018', 'MM/DD/YYYY'), TO_DATE('11/05/2018', 'MM/DD/YYYY'), 5, 'D0001', 'S0001', 'Complete','PN006');
insert into PORDER values ('O0007', TO_DATE('11/09/2018', 'MM/DD/YYYY'), TO_DATE('11/14/2018', 'MM/DD/YYYY'), null, 7, 'D0002', 'S0002', 'Pending','PH019');
insert into PORDER values ('O0008', TO_DATE('11/07/2018', 'MM/DD/YYYY'), TO_DATE('11/14/2018', 'MM/DD/YYYY'), null, 12, 'D0001', 'S0002', 'Pending','PH002');
insert into PORDER values ('O0009', TO_DATE('11/08/2018', 'MM/DD/YYYY'), TO_DATE('11/14/2018', 'MM/DD/YYYY'), null, 6, 'D0001', 'S0002', 'Pending','PH006');
insert into PORDER values ('O0010', TO_DATE('11/08/2018', 'MM/DD/YYYY'), TO_DATE('11/14/2018', 'MM/DD/YYYY'), null, 5, 'D0001', 'S0002', 'Pending','PH013');
insert into PORDER values ('O0011', TO_DATE('11/08/2018', 'MM/DD/YYYY'), TO_DATE('11/14/2018', 'MM/DD/YYYY'), null, 5, 'D0002', 'S0002', 'Pending','PH004');

/* NOTIFICATION */
insert into NOTIF values ('N0001', TO_DATE('11/05/2018', 'MM/DD/YYYY'), 'S0001', '5 Camshaft from D0001 delayed by 2 business days','O0006');
insert into NOTIF values ('N0002', TO_DATE('08/11/2017', 'MM/DD/YYYY'), 'S0001', '4 Coolant from D0002 delayed by 1 business day','O0004');
insert into NOTIF values ('N0003', TO_DATE('06/18/2014', 'MM/DD/YYYY'), 'S0001', '5 Battery from D0001 delayed by 3 business days','O0001');


/* Car Service schemas: | service_id | start_time | end_time | status | labor fee | total_part_fee | total_service_cost |   */
insert into CARSERVICE values ('CS0001', 'Complete', 'SH001'); 
insert into CARSERVICE values ('CS0002', 'Complete', 'SH002'); 
insert into CARSERVICE values ('CS0003', 'Complete', 'SH003'); 
insert into CARSERVICE values ('CS0004', 'Complete', 'SH004'); 
insert into CARSERVICE values ('CS0005', 'Complete', 'SH005');
insert into CARSERVICE values ('CS0006', 'Complete', 'SH006');  
insert into CARSERVICE values ('CS0007', 'Complete', 'SH007');
insert into CARSERVICE values ('CS0008', 'Complete', 'SH008');
insert into CARSERVICE values ('CS0009', 'Complete', 'SH009');
insert into CARSERVICE values ('CS0010', 'Complete', 'SH010');
insert into CARSERVICE values ('CS0011', 'Complete', 'SH011');
insert into CARSERVICE values ('CS0012', 'Complete', 'SH012');
insert into CARSERVICE values ('CS0013', 'Complete', 'SH013');
insert into CARSERVICE values ('CS0014', 'Complete', 'SH014');
insert into CARSERVICE values ('CS0015', 'Complete', 'SH015');
insert into CARSERVICE values ('CS0016', 'Complete', 'SH016');


/* repair service schemas: | repair_id | repair_code | diagnostic | diagnostic fee |  */
insert into REPAIR_SERVICE values ('R0001', 'Engine knock', 'Timing issue', 75);
insert into REPAIR_SERVICE values ('R0002', 'Car drifts in a particular direction', 'Wheel alignment issue', 50);
insert into REPAIR_SERVICE values ('R0003', 'Battery does not hold charge', 'Battery needs replacement', 25);
insert into REPAIR_SERVICE values ('R0004', 'Black/unclean exhaust', 'Bad catalytic convertor and filters', 75);
insert into REPAIR_SERVICE values ('R0005', 'A/C-Heater not working', 'Diagnostic: Drive belt damaged, coolant not enough, weak battery', 50);
insert into REPAIR_SERVICE values ('R0006', 'Headlamps/Tail lamps not working', 'Diagnostic: Light assembly damaged', 30);
insert into REPAIR_SERVICE values ('R0007', 'Check engine light', 'Gearbox and Torque convertor issue', 100);

/* repair's basic service schemas: | repair_id | car model |basic_service id | */
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Corolla', 'BSTC008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Corolla', 'BSTC015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Corolla', 'BSTC005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Corolla', 'BSTC019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Prius', 'BSTP008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Prius', 'BSTP015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Prius', 'BSTP005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Prius', 'BSTP019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Civic', 'BSHC008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Civic', 'BSHC015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Civic', 'BSHC005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Civic', 'BSHC019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Accord', 'BSHA008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Accord', 'BSHA015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Accord', 'BSHA005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Accord', 'BSHA019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Altima', 'BSNA008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Altima', 'BSNA015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Altima', 'BSNA005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Altima', 'BSNA019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Rogue', 'BSNR008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Rogue', 'BSNR015');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Rogue', 'BSNR005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0001', 'Rogue', 'BSNR019');

insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Corolla', 'BSTC020');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Prius', 'BSTP020');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Civic', 'BSHC020');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Accord', 'BSHA020');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Altima', 'BSNA020');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0002', 'Rogue', 'BSNR020');

insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Corolla', 'BSTC002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Prius', 'BSTP002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Civic', 'BSHC002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Accord', 'BSHA002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Altima', 'BSNA002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0003', 'Rogue', 'BSNR002');

--

insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Corolla', 'BSTC001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Corolla', 'BSTC012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Corolla', 'BSTC006');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Prius', 'BSTP001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Prius', 'BSTP012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Prius', 'BSTP006');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Civic', 'BSHC001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Civic', 'BSHC012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Civic', 'BSHC006');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Accord', 'BSHA001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Accord', 'BSHA012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Accord', 'BSHA006');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Altima', 'BSNA001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Altima', 'BSNA012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Altima', 'BSNA006');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Rogue', 'BSNR001');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Rogue', 'BSNR012');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0004', 'Rogue', 'BSNR006');

insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Corolla', 'BSTC008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Corolla', 'BSTC007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Corolla', 'BSTC002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Prius', 'BSTP008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Prius', 'BSTP007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Prius', 'BSTP002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Civic', 'BSHC008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Civic', 'BSHC007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Civic', 'BSHC002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Accord', 'BSHA008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Accord', 'BSHA007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Accord', 'BSHA002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Altima', 'BSNA008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Altima', 'BSNA007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Altima', 'BSNA002');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Rogue', 'BSNR008');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Rogue', 'BSNR007');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0005', 'Rogue', 'BSNR002');

insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Corolla', 'BSTC011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Corolla', 'BSTC017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Corolla', 'BSTC018');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Prius', 'BSTP011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Prius', 'BSTP017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Prius', 'BSTP018');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Civic', 'BSHC011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Civic', 'BSHC017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Civic', 'BSHC018');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Accord', 'BSHA011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Accord', 'BSHA017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Accord', 'BSHA018');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Altima', 'BSNA011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Altima', 'BSNA017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Altima', 'BSNA018');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Rogue', 'BSNR011');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Rogue', 'BSNR017');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0006', 'Rogue', 'BSNR018');

insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Corolla', 'BSTC013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Corolla', 'BSTC010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Corolla', 'BSTC005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Corolla', 'BSTC019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Prius', 'BSTP013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Prius', 'BSTP010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Prius', 'BSTP005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Prius', 'BSTP019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Civic', 'BSHC013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Civic', 'BSHC010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Civic', 'BSHC005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Civic', 'BSHC019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Accord', 'BSHA013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Accord', 'BSHA010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Accord', 'BSHA005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Accord', 'BSHA019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Altima', 'BSNA013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Altima', 'BSNA010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Altima', 'BSNA005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Altima', 'BSNA019');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Rogue', 'BSNR013');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Rogue', 'BSNR010');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Rogue', 'BSNR005');
insert into REPAIR_HAS_BASIC_SERVICE values ('R0007', 'Rogue', 'BSNR019');


/* Maintenance's basic service schemas: | Maintenance_id | car model | SERVICE_TYPE |basic_service id | */
insert into MAINT_HAS_BASIC_SERVICE values ('M0001', 'Corolla','Service A','BSTC009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0001', 'Corolla','Service A','BSTC007');
insert into MAINT_HAS_BASIC_SERVICE values ('M0004', 'Prius','Service A','BSTP009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0004', 'Prius','Service A','BSTP012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0004', 'Prius','Service A','BSTP007');
insert into MAINT_HAS_BASIC_SERVICE values ('M0007', 'Civic','Service A','BSHC007');
insert into MAINT_HAS_BASIC_SERVICE values ('M0007', 'Civic','Service A','BSHC003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0007', 'Civic','Service A','BSHC009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0010', 'Accord','Service A','BSHA009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0010', 'Accord','Service A','BSHA001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0010', 'Accord','Service A','BSHA012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0010', 'Accord','Service A','BSHA007');
insert into MAINT_HAS_BASIC_SERVICE values ('M0013', 'Altima','Service A','BSNA009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0013', 'Altima','Service A','BSNA001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0013', 'Altima','Service A','BSNA012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0013', 'Altima','Service A','BSNA007');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR009');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0016', 'Rogue','Service A','BSNR007');

insert into MAINT_HAS_BASIC_SERVICE values ('M0002', 'Corolla','Service B','BSTC001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0002', 'Corolla','Service B','BSTC012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0002', 'Corolla','Service B','BSTC003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0002', 'Corolla','Service B','BSTC021');
insert into MAINT_HAS_BASIC_SERVICE values ('M0002', 'Corolla','Service B','BSTC015');
insert into MAINT_HAS_BASIC_SERVICE values ('M0005', 'Prius','Service B','BSTP001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0005', 'Prius','Service B','BSTP003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0005', 'Prius','Service B','BSTP021');
insert into MAINT_HAS_BASIC_SERVICE values ('M0005', 'Prius','Service B','BSTP015');
insert into MAINT_HAS_BASIC_SERVICE values ('M0005', 'Prius','Service B','BSTP002');
insert into MAINT_HAS_BASIC_SERVICE values ('M0008', 'Civic','Service B','BSHC001');
insert into MAINT_HAS_BASIC_SERVICE values ('M0008', 'Civic','Service B','BSHC012');
insert into MAINT_HAS_BASIC_SERVICE values ('M0008', 'Civic','Service B','BSHC004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0008', 'Civic','Service B','BSHC021');
insert into MAINT_HAS_BASIC_SERVICE values ('M0008', 'Civic','Service B','BSHC015');
insert into MAINT_HAS_BASIC_SERVICE values ('M0011', 'Accord','Service B','BSHA003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0011', 'Accord','Service B','BSHA021');
insert into MAINT_HAS_BASIC_SERVICE values ('M0011', 'Accord','Service B','BSHA015');
insert into MAINT_HAS_BASIC_SERVICE values ('M0014', 'Altima','Service B','BSNA003');
insert into MAINT_HAS_BASIC_SERVICE values ('M0014', 'Altima','Service B','BSNA021');
insert into MAINT_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR016');
insert into MAINT_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR021');
insert into MAINT_HAS_BASIC_SERVICE values ('Nissan', 'Rogue','Service B','BSNR015');

insert into MAINT_HAS_BASIC_SERVICE values ('M0003', 'Corolla','Service C','BSTC016');
insert into MAINT_HAS_BASIC_SERVICE values ('M0003', 'Corolla','Service C','BSTC004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0003', 'Corolla','Service C','BSTC014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0006', 'Prius','Service C','BSTP016');
insert into MAINT_HAS_BASIC_SERVICE values ('M0006', 'Prius','Service C','BSTP004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0006', 'Prius','Service C','BSTP014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0009', 'Civic','Service C','BSHC016');
insert into MAINT_HAS_BASIC_SERVICE values ('M0009', 'Civic','Service C','BSHC014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0012', 'Accord','Service C','BSHA016');
insert into MAINT_HAS_BASIC_SERVICE values ('M0012', 'Accord','Service C','BSHA004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0012', 'Accord','Service C','BSHA014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0015', 'Altima','Service C','BSNA016');
insert into MAINT_HAS_BASIC_SERVICE values ('M0015', 'Altima','Service C','BSNA004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0015', 'Altima','Service C','BSNA014');
insert into MAINT_HAS_BASIC_SERVICE values ('M0015', 'Altima','Service C','BSNA015');
insert into MAINT_HAS_BASIC_SERVICE values ('M0018', 'Rogue','Service C','BSNR004');
insert into MAINT_HAS_BASIC_SERVICE values ('M0018', 'Rogue','Service C','BSNR002');

/* NUMERRICAL VALUES */
insert into M_VALUES values ('Corolla', 'Service A', 0.5,25,126);
insert into M_VALUES values ('Prius', 'Service A', 0.75,37.5,162);
insert into M_VALUES values ('Civic', 'Service A', 0.75,37.5,112);
insert into M_VALUES values ('Accord', 'Service A', 1,50,138);
insert into M_VALUES values ('Altima', 'Service A', 1,50,175);
insert into M_VALUES values ('Rogue', 'Service A', 1.5,	75,	211);

insert into M_VALUES values ('Corolla', 'Service B', 1.75,	87.5,	289);
insert into M_VALUES values ('Prius', 'Service B', 2,	100,	352);
insert into M_VALUES values ('Civic', 'Service B', 2.25,	112.5,	360);
insert into M_VALUES values ('Accord', 'Service B', 1.75,	87.5	,319);
insert into M_VALUES values ('Altima', 'Service B', 1.5	,75	,222);
insert into M_VALUES values ('Rogue', 'Service B', 2.25,	112.5,	285);

insert into M_VALUES values ('Corolla', 'Service C', 2.75,	137.5,	220.5);
insert into M_VALUES values ('Prius', 'Service C', 3,	150,	250);
insert into M_VALUES values ('Civic', 'Service C', 2.75,	137.5,	213.5);
insert into M_VALUES values ('Accord', 'Service C', 2.75,	137.5,	229.5);
insert into M_VALUES values ('Altima', 'Service C', 2.75,	137.5,	185);
insert into M_VALUES values ('Rogue', 'Service C', 3,	150,	173.5);

insert into R_VALUES values ('Corolla', 'Engine knock', 3.25,	245,	7564,75);
insert into R_VALUES values ('Prius', 'Engine knock', 3.25,	245,	7564,75);
insert into R_VALUES values ('Civic', 'Engine knock', 3.25,	245,	727,753);
insert into R_VALUES values ('Accord', 'Engine knock', 3.25,	245,	7198,75);
insert into R_VALUES values ('Altima', 'Engine knock', 3.25,	245,	8523,75);
insert into R_VALUES values ('Rogue', 'Engine knock', 3.25,	245,	10571,75);

insert into R_VALUES values ('Corolla', 'Car drift', 1,	65,	246,50);
insert into R_VALUES values ('Prius', 'Car drift', 1,	65,	246,50);
insert into R_VALUES values ('Civic', 'Car drift', 1,	65,	282,50);
insert into R_VALUES values ('Accord', 'Car drift', 1,	65,	282,50);
insert into R_VALUES values ('Altima', 'Car drift', 1,	65,	482,50);
insert into R_VALUES values ('Rogue', 'Car drift', 1,	65,	482,50);

insert into R_VALUES values ('Corolla', 'Bettery not charge', 0.25,	12.5,	63,25);
insert into R_VALUES values ('Prius', 'Bettery not charge', 0.25,	12.5,	63,25);
insert into R_VALUES values ('Civic', 'Bettery not charge', 0.25,	12.5,	104,25);
insert into R_VALUES values ('Accord', 'Bettery not charge', 0.25,	12.5,	79,25);
insert into R_VALUES values ('Altima', 'Bettery not charge', 0.25,	12.5,	14,25);
insert into R_VALUES values ('Rogue', 'Bettery not charge', 0.25,	12.5,	28,25);

insert into R_VALUES values ('Corolla', 'Black exhaust', 1.5,	90,	848,75);
insert into R_VALUES values ('Prius', 'Black exhaust', 1.5,	90,	848,75);
insert into R_VALUES values ('Civic', 'Black exhaust', 1.5,	90,	817,75);
insert into R_VALUES values ('Accord', 'Black exhaust', 1.5	,90,	876,75);
insert into R_VALUES values ('Altima', 'Black exhaust', 1.5,	90,	711,75);
insert into R_VALUES values ('Rogue', 'Black exhaust', 1.5,	90,	894,75);

insert into R_VALUES values ('Corolla', 'AC not working', 1.5,	90,	654,50);
insert into R_VALUES values ('Prius', 'AC not working', 1.5,	90,	654,50);
insert into R_VALUES values ('Civic', 'AC not working', 1.5,	90,	1532,50);
insert into R_VALUES values ('Accord', 'AC not working', 1.5,	90,	1532,50);
insert into R_VALUES values ('Altima', 'AC not working', 1.5,	90,	1176,50);
insert into R_VALUES values ('Rogue', 'AC not working', 1.5,	90,	1190,50);

insert into R_VALUES values ('Corolla', 'lamps not working', 1.5,	75,	1234,30);
insert into R_VALUES values ('Prius', 'lamps not working', 1.5,	75,	1234,30);
insert into R_VALUES values ('Civic', 'lamps not working', 1.5,	75,	2684,30);
insert into R_VALUES values ('Accord', 'lamps not working', 1.5,	75,	2684,30);
insert into R_VALUES values ('Altima', 'lamps not working', 1.5,	75,	2734,30);
insert into R_VALUES values ('Rogue', 'lamps not working', 1.5,	75,	2734,30);

insert into R_VALUES values ('Corolla', 'check engine', 4,	240,	14942,100);
insert into R_VALUES values ('Prius', 'check engine', 4,	240,	14942,100);
insert into R_VALUES values ('Civic', 'check engine', 4	,240,	26263,100);
insert into R_VALUES values ('Accord', 'check engine', 4,	240,	19543,100);
insert into R_VALUES values ('Altima', 'check engine', 4,	240,	23651,100);
insert into R_VALUES values ('Rogue', 'check engine', 4,	240,	32006,100);