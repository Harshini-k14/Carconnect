create database carconnect;
use carconnect;

drop table Customer;
drop table Vehicle;
drop table Reservation;

create table Customer (CustomerId int primary key,FirstName varchar(20),
LastName varchar(20),Email varchar(100),PhoneNumber bigint,Address varchar(100),
Username varchar(50),Password varchar(20),RegistrationDate date);
insert into Customer values
(1, 'Harshini', 'Kukanti', 'harshini@gmail.com', 7548800902, 'no:23 xxx street', 'harsh14', '12345', '2024-03-12'),
(2, 'Aarav', 'Reddy', 'aarav@gmail.com', 9548621463, 'no:27 yyy street', 'aarav10', 'aarav@10', '2024-03-16'),
(3, 'Isha', 'Verma', 'isha@gmail.com', 8541269753, 'no:12 zzz street', 'ishamart', 'isha1234', '2024-02-22'),
(4, 'Arjun', 'Sinha', 'arjun@gmail.com', 9754632846, 'no:28 xyz street', 'arjun@mitch', 'mitch12arjun', '2024-03-17');
ALTER TABLE Customer MODIFY PhoneNumber VARCHAR(20);
 drop table vehicles;
create table Vehicles( VehicleId int primary key,
Model varchar(20),Make varchar(20),
Year int,Color varchar(20),RegistrationNumber varchar(20) unique,
Availability Boolean,DailyRate int);
insert into Vehicles values
(101, 'Sedan', 'Toyota', 2020, 'Blue', 'ABC123', true, 50),
(102, 'SUV', 'Ford', 2019, 'Red', 'XYZ789', false, 60),
(103, 'Compact', 'Honda', 2021, 'Silver', 'PQR456', true, 45),
(104, 'Truck', 'Chevrolet', 2018, 'Black', 'DEF789', false, 80);




 drop table Reservation;
create table Reservation (ReservationId int primary key,
CustomerId int,foreign key(CustomerId) references Customer(CustomerId),
VehicleId int,foreign key(VehicleId) references Vehicles(VehicleId),
StartDate date,EndDate date,TotalCost int,Status varchar(20));
insert into Reservation values
(11,1,101,'2024-03-10','2024-03-15',2500,'Confirmed'),
(12,2,103,'2024-03-12','2024-03-18',3000,'Pending'),
(13,3,102,'2024-03-14','2024-03-20',2000,'Confirmed'),
(14,4,104,'2024-03-16','2024-03-22',4000,'Completed');
ALTER TABLE reservation MODIFY COLUMN ReservationId INT AUTO_INCREMENT;

create table Admin (AdminId int primary key,FirstName varchar(20),
LastName varchar(20),Email varchar(20),PhoneNumber bigint,
Username varchar(20),Password Varchar(20),Role varchar(20),JoinDate date);
insert into Admin values
(1,'Raj','Patil','raj@gmail.com',9876543210,'rajpatil','pass123','superadmin','2024-03-01'),
(2,'Priya','Sharma','priya@gmail.com',8765432109,'priyasharma','pass456','fleetmanager','2024-03-05'),
(3,'Ankit','Kumar','ankit@gmail.com',7654321098,'ankitkumar','pass789','superadmin','2024-03-10'),
(4,'Neha','Singh','neha@gmail.com',6543210987,'nehasingh','passabc','admin','2024-03-15');